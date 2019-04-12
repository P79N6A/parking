package com.zoeeasy.cloud.gather.consumer;

import com.alibaba.fastjson.JSON;
import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.lock.redisson.core.Lock;
import com.scapegoat.infrastructure.lock.redisson.core.LockFactory;
import com.scapegoat.infrastructure.lock.redisson.core.LockInfo;
import com.scapegoat.infrastructure.lock.redisson.enumerate.LockType;
import com.scapegoat.infrastructure.message.RocketMessage;
import com.zoeeasy.cloud.core.cst.MessageQueueDefinitions;
import com.zoeeasy.cloud.core.enums.*;
import com.zoeeasy.cloud.core.utils.EnumConverter;
import com.zoeeasy.cloud.gather.domain.HikvisionVehicleRecordEntity;
import com.zoeeasy.cloud.gather.enums.VehicleProcessStatusEnum;
import com.zoeeasy.cloud.gather.service.HikvisionVehicleRecordCrudService;
import com.zoeeasy.cloud.hikvision.bean.proto.PassVehicleMsg;
import com.zoeeasy.cloud.message.MessageSendService;
import com.zoeeasy.cloud.message.payload.PassingVehiclePayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.BytesMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.Date;


/**
 * 海康过车消息订阅
 *
 * @Date: 2018/7/19
 * @author: wh
 */
@Component
@Slf4j
public class HikMessageConsumer implements MessageListener {

    @Autowired
    private MessageSendService messageSendService;

    @Autowired
    private LockFactory lockFactory;

    @Autowired
    private HikvisionVehicleRecordCrudService hikvisionVehicleRecordCrudService;

    /**
     * @param message
     */
    @Override
    public void onMessage(Message message) {

        try {

            BytesMessage bytesMessage = (BytesMessage) message;
            if (null != bytesMessage) {

                byte[] byteMsg = new byte[(int) bytesMessage.getBodyLength()];
                bytesMessage.readBytes(byteMsg);
                PassVehicleMsg.PassVehicle passVehicle = PassVehicleMsg.PassVehicle.parseFrom(byteMsg);
                //海康过车数据处理
                if (passVehicle != null) {

                    log.debug("------[接收到要处理的海康平台过车消息(消息内容:{})]--------[开始处理时间{}]------", JSON.toJSONString(passVehicle));

                    //获取锁,同一条过车消息只能被一个线程处理
                    LockInfo lockInfo = new LockInfo();
                    lockInfo.setType(LockType.Fair);
                    lockInfo.setName(getPassVehicleLockKey(passVehicle));
                    lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
                    lockInfo.setLeaseTime(LockInfo.DEFAULT_LOCK_LEASE_TIME);
                    Lock lock = lockFactory.getLock(lockInfo);
                    boolean lockAcquired = false;
                    try {
                        lockAcquired = lock.acquire();
                        if (lockAcquired) {
                            //过车时间
                            Date passTime = DateTimeUtils.parseDate(passVehicle.getPassTime(), DateTimeUtils.DATE_FORMAT_DATETIME);
                            HikvisionVehicleRecordEntity hikvisionVehicleRecordExist =
                                    hikvisionVehicleRecordCrudService.findOne(
                                            passVehicle.getUuid(),
                                            passVehicle.getParkCode(),
                                            passVehicle.getPlateNo(),
                                            passVehicle.getDirect(),
                                            passTime
                                    );

                            //如果过车数据已经存在直接忽略
                            if (hikvisionVehicleRecordExist == null) {

                                //保存过车数据
                                HikvisionVehicleRecordEntity vehicleRecord = sealVehicleRecord(passVehicle);
                                boolean retVal = hikvisionVehicleRecordCrudService.insert(vehicleRecord);
                                if (retVal) {
                                    //发送过车队列消息
                                    //不管是否有无车牌,发送过车队列消息
                                    RocketMessage<PassingVehiclePayload> messagePassing = new RocketMessage<>();
                                    messagePassing.setDestination(MessageQueueDefinitions.Topic.PASSING_VEHICLE);
                                    messagePassing.setSender(MessageQueueDefinitions.Sender.GATHER);
                                    messagePassing.setMessageId(StringUtils.getUUID());
                                    messagePassing.setPayload(buildPassingVehiclePayload(vehicleRecord));
                                    //根据ParkCode Hash顺序发送
                                    messagePassing.setHashKey(vehicleRecord.getParkCode());
                                    messageSendService.sendAndSaveOrderlySync(messagePassing);
                                }
                            }
                        } else {
                            log.error("海康平台过车消息处理获取分布式锁时抛错：");
                        }
                    } catch (Exception e) {
                        log.error("海康平台过车消息处理获处理异常,异常信息：{}", e.getMessage());
                    } finally {
                        if (lockAcquired) {
                            lock.release();
                        }
                    }
                    log.debug("------[接收到要处理的海康平台过车消息(Uuin:{},PlateNo:{})]--------[结束处理时间{}]------", passVehicle.getUuid(), passVehicle.getPlateNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
                }
            }
        } catch (Exception e) {
            log.debug("------[海康平台过车消息订阅处理失败:{}", e.getMessage());
        }
    }

    /**
     * 获取过车记录分布式锁键
     *
     * @param passVehicle orderNo
     * @return
     */
    private String getPassVehicleLockKey(PassVehicleMsg.PassVehicle passVehicle) {

        return
                new StringBuilder().append("lock:parking.passing.hikvision.pass_").
                        append(passVehicle.getUuid()).append("_").
                        append(passVehicle.getParkCode()).append("_").
                        append(passVehicle.getPlateNo()).append("_").
                        append(passVehicle.getDirect()).toString();
    }

    /**
     * 封装海康平台过车记录
     *
     * @param vehicleRecordsBean vehicleRecordsBean
     * @return
     */
    private HikvisionVehicleRecordEntity sealVehicleRecord(PassVehicleMsg.PassVehicle vehicleRecordsBean) {

        HikvisionVehicleRecordEntity vehicleRecord = new HikvisionVehicleRecordEntity();
        vehicleRecord.setPassingUuid(StringUtils.getUUID());
        vehicleRecord.setUuid(vehicleRecordsBean.getUuid());
        vehicleRecord.setParkCode(vehicleRecordsBean.getParkCode());
        vehicleRecord.setGateCode(vehicleRecordsBean.getGateCode());
        vehicleRecord.setLaneCode(vehicleRecordsBean.getLaneNo());
        vehicleRecord.setBerthCode(vehicleRecordsBean.getBerthCode());
        vehicleRecord.setPlateNumber(vehicleRecordsBean.getPlateNo());
        //车牌颜色
        vehicleRecord.setPlateColor(vehicleRecordsBean.getPlateColor());
        //车辆类型
        vehicleRecord.setCarType(vehicleRecordsBean.getCarType());
        //过车方向
        vehicleRecord.setPassDirect(vehicleRecordsBean.getDirect());
        vehicleRecord.setPassTime(DateTimeUtils.parseDate(vehicleRecordsBean.getPassTime(), DateTimeUtils.DATE_FORMAT_DATETIME));
        //未处理
        vehicleRecord.setProcessStatus(VehicleProcessStatusEnum.PROCESS_NOT.getValue());
        vehicleRecord.setProcessRemark(VehicleProcessStatusEnum.PROCESS_NOT.getComment());
        return vehicleRecord;
    }

    /**
     * 从海康平台过车数据生成平台过车MQ消息
     *
     * @param vehicleRecord vehicleRecord
     * @return
     */
    private PassingVehiclePayload buildPassingVehiclePayload(HikvisionVehicleRecordEntity vehicleRecord) {

        PassingVehiclePayload payload = new PassingVehiclePayload();
        if (StringUtils.isEmpty(vehicleRecord.getPassingUuid())) {
            payload.setPassingUuid(StringUtils.getUUID());
        } else {
            payload.setPassingUuid(vehicleRecord.getPassingUuid());
        }
        //海康过车数据源
        payload.setDataSource(PassingVehicleDataSourceEnum.HIKVISION.getValue());
        payload.setThirdPassingId(vehicleRecord.getUuid());
        payload.setParkCode(vehicleRecord.getParkCode());
        payload.setParkName(vehicleRecord.getParkName());
        payload.setGateCode(vehicleRecord.getGateCode());
        payload.setGateName(vehicleRecord.getGateName());
        payload.setLaneCode(vehicleRecord.getLaneCode());
        payload.setLaneName(vehicleRecord.getLaneName());
        //接口返回的实际是berthNumber
        payload.setBerthCode(vehicleRecord.getBerthCode());
        payload.setPassTime(vehicleRecord.getPassTime());
        payload.setPlateNumber(vehicleRecord.getPlateNumber());
        //车辆类型
        VehicleLevelEnum carType = VehicleLevelEnum.parse(vehicleRecord.getCarType());
        if (carType != null) {
            CarTypeEnum vehicleLevel = EnumConverter.fromHikCarType(carType);
            payload.setCarType(vehicleLevel.getValue());
        }
        //车牌颜色
        PlateColorStyleEnum plateColorStyle = PlateColorStyleEnum.parse(vehicleRecord.getPlateColor());
        if (plateColorStyle != null) {
            LicensePlateColorEnum plateColor = EnumConverter.fromHikPlateColorStyle(plateColorStyle);
            payload.setPlateColor(plateColor.getValue());
        }
        //过车方向
        PassingDirectionEnum passingDirection = PassingDirectionEnum.parse(vehicleRecord.getPassDirect());
        if (passingDirection != null) {
            PassingTypeEnum passingType = EnumConverter.fromHikPassingDirection(passingDirection);
            payload.setDirect(passingType.getValue());
        }
        return payload;
    }

}
