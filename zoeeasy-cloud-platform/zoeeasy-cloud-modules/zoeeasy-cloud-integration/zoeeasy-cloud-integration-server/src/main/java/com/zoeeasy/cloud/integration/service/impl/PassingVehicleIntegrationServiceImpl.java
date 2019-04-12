package com.zoeeasy.cloud.integration.service.impl;

import com.alibaba.fastjson.JSON;
import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.PlateNumberUtil;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.lock.redisson.core.Lock;
import com.scapegoat.infrastructure.lock.redisson.core.LockFactory;
import com.scapegoat.infrastructure.lock.redisson.core.LockInfo;
import com.scapegoat.infrastructure.lock.redisson.enumerate.LockType;
import com.scapegoat.infrastructure.message.RocketMessage;
import com.zoeeasy.cloud.charge.chg.dto.result.CalculateAmountResultDto;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingCurrentChargeInfoRequestDto;
import com.zoeeasy.cloud.charge.park.dto.result.ParkingCurrentChargeInfoResultDto;
import com.zoeeasy.cloud.core.cst.MessageQueueDefinitions;
import com.zoeeasy.cloud.core.enums.*;
import com.zoeeasy.cloud.core.utils.UUIDTool;
import com.zoeeasy.cloud.gather.enums.VehicleProcessStatusEnum;
import com.zoeeasy.cloud.gather.hikvision.HikvisionParkingService;
import com.zoeeasy.cloud.gather.hikvision.dto.request.HikVehicleRecordUpProcessStatusDateRequestDto;
import com.zoeeasy.cloud.integration.message.MessageSendIntegrationService;
import com.zoeeasy.cloud.integration.message.dto.request.*;
import com.zoeeasy.cloud.integration.open.TerminateParkingOrderService;
import com.zoeeasy.cloud.integration.order.ParkingOrderIntegrationService;
import com.zoeeasy.cloud.integration.order.dto.result.ParkingOrderSyncResultDto;
import com.zoeeasy.cloud.integration.park.CalculateIntegrationService;
import com.zoeeasy.cloud.integration.park.ParkChargeRuleIntegrationService;
import com.zoeeasy.cloud.integration.park.dto.request.ParkingAmountCalculateRequestDto;
import com.zoeeasy.cloud.integration.pass.PassingVehicleIntegrationService;
import com.zoeeasy.cloud.integration.pass.dto.result.PassingVehicleProcessResultDto;
import com.zoeeasy.cloud.message.MessageSendService;
import com.zoeeasy.cloud.message.payload.AnyPassRecordImagePayload;
import com.zoeeasy.cloud.message.payload.MagneticPassingPayload;
import com.zoeeasy.cloud.message.payload.PassingVehiclePayload;
import com.zoeeasy.cloud.order.appoint.AppointmentOrderManagerService;
import com.zoeeasy.cloud.order.appoint.PlatformAppointOrderService;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderEnterRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderValidPlateRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.result.ParkingAppointmentOrderResultDto;
import com.zoeeasy.cloud.order.enums.ThirdOrderSyncStatusEnum;
import com.zoeeasy.cloud.order.hikvision.ThirdParkingOrderService;
import com.zoeeasy.cloud.order.parking.PlatformParkingOrderService;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderCreateRequestDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderGetByRecordNoRequestDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderUpdateByPlateNumberRequestDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderCreateResultDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderResultDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderSettleResultDto;
import com.zoeeasy.cloud.pms.enums.ParkingImageTypeEnum;
import com.zoeeasy.cloud.pms.enums.PassDataTypeEnum;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingInfoResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingLotResultDto;
import com.zoeeasy.cloud.pms.platform.PlatformParkingInfoService;
import com.zoeeasy.cloud.pms.platform.PlatformProcessService;
import com.zoeeasy.cloud.pms.platform.dto.request.*;
import com.zoeeasy.cloud.pms.platform.dto.result.*;
import com.zoeeasy.cloud.pms.specialvehicle.SpecialVehicleService;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.SpecialTypeGetRequestDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.SpecialVehicleTypeResultDto;
import com.zoeeasy.cloud.tool.vesta.intf.IdService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 过车集成服务
 *
 * @author AkeemSuper
 * @date 2018/9/26 0026
 */
@Service(value = "passingVehicleIntegrationService")
@Slf4j
public class PassingVehicleIntegrationServiceImpl implements PassingVehicleIntegrationService {

    /**
     * 连续入车有效时间(5分钟)
     */
    private static final Integer DEFAULT_VALID_ENTRY_PARKING_WINDOW_TIME = 5 * 60;

    private static final String PLATE_NUMBER_EMPTY = "无车牌";

    @Autowired
    private PlatformParkingInfoService platformParkingInfoService;

    @Autowired
    private PlatformParkingOrderService platformParkingOrderService;

    @Autowired
    private PlatformAppointOrderService platformAppointOrderService;

    @Autowired
    private PlatformProcessService platformProcessService;

    @Autowired
    private MessageSendService messageSendService;

    @Autowired
    private ParkChargeRuleIntegrationService parkChargeRuleIntegrationService;

    @Autowired
    private AppointmentOrderManagerService appointmentOrderManagerService;

    @Autowired
    private ThirdParkingOrderService thirdParkingOrderService;

    @Autowired
    private HikvisionParkingService hikvisionParkingService;

    @Autowired
    private MessageSendIntegrationService messageSendIntegrationService;

    @Autowired
    private CalculateIntegrationService calculateIntegrationService;

    @Autowired
    private SpecialVehicleService specialVehicleService;

    @Autowired
    private TerminateParkingOrderService terminateParkingOrderService;

    @Autowired
    private ParkingOrderIntegrationService parkingOrderIntegrationService;

    @Autowired
    private IdService idService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LockFactory lockFactory;

    /**
     * 处理道闸过车数据消息
     *
     * @param passingVehiclePayload passingVehiclePayload
     * @return ResultDto
     * @throws BusinessException exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResultDto<PassingVehicleProcessResultDto> processPassingVehicleRecord(PassingVehiclePayload passingVehiclePayload) throws BusinessException {
        ObjectResultDto<PassingVehicleProcessResultDto> resultDto = new ObjectResultDto<>();
        if (passingVehiclePayload == null) {
            return resultDto.success();
        }
        try {
            //是否异常过车数据
            boolean exceptionPassing = false;
            Integer exceptionType = PassingExceptionTypeEnum.NOT_EXCEPTION.getValue();
            //异常原因
            String exceptionMessage = "";
            //平台停车场
            ParkingInfoResultDto parkingInfo = this.getParkingInfo(passingVehiclePayload);
            if (parkingInfo == null) {
                exceptionPassing = true;
                exceptionType = PassingExceptionTypeEnum.PARKING_NOT_FOUND.getValue();
                exceptionMessage = "停车场不存在(停车场编号" + passingVehiclePayload.getParkCode() + "),停车场名称(" + passingVehiclePayload.getParkName() + ")";
            }
            //平台泊位
            ParkingLotResultDto parkingLotInfo = null;
            if (!exceptionPassing) {
                //平台泊位不存在，不作为异常过车记录
                parkingLotInfo = this.getParkingLotInfo(parkingInfo, passingVehiclePayload);
            }
            if (exceptionPassing) {
                //创建平台异常过车记录
                PassingVehicleRecordCreateRequestDto passingVehicleExceptionRecord = buildPassingVehicleRecord(
                        passingVehiclePayload, parkingInfo, parkingLotInfo, exceptionType, exceptionMessage);
                platformProcessService.savePassingVehicleRecord(passingVehicleExceptionRecord);
                //海康过车数据标记处理失败
                if (passingVehiclePayload.getDataSource().equals(PassingVehicleDataSourceEnum.HIKVISION.getValue())) {
                    updatePassingVehicleProcessStatus(passingVehiclePayload, VehicleProcessStatusEnum.PROCESS_FAIL.getValue(), exceptionMessage);
                }
                return resultDto.success();
            }
            if (PassingTypeEnum.ENTRY.getValue().equals(passingVehiclePayload.getDirect())) {
                //入车记录处理
                resultDto = processIntoPassVehicleRecord(passingVehiclePayload, parkingInfo, parkingLotInfo);
            } else if (PassingTypeEnum.EXIT.getValue().equals(passingVehiclePayload.getDirect())) {
                //出车记录处理
                resultDto = processOutPassVehicleRecord(passingVehiclePayload, parkingInfo, parkingLotInfo);
            }
            if (PassingVehicleDataSourceEnum.HIKVISION.getValue().equals(passingVehiclePayload.getDataSource())) {
                //发送过车图像获取消息
                if (resultDto.isSuccess() && resultDto.getData() != null) {
                    PassingVehicleProcessResultDto vehicleProcessResultDto = resultDto.getData();
                    HikImageMessageSendRequestDto hikImageMessageSendRequestDto = new HikImageMessageSendRequestDto();
                    hikImageMessageSendRequestDto.setTenantId(parkingInfo.getTenantId());
                    hikImageMessageSendRequestDto.setParkingId(parkingInfo.getId());
                    hikImageMessageSendRequestDto.setPassingId(vehicleProcessResultDto.getPassingId());
                    hikImageMessageSendRequestDto.setPassingNo(vehicleProcessResultDto.getPassingNo());
                    hikImageMessageSendRequestDto.setHikPassingUuid(passingVehiclePayload.getThirdPassingId());
                    hikImageMessageSendRequestDto.setParkCode(passingVehiclePayload.getParkCode());
                    this.messageSendIntegrationService.sendHikImageMessage(hikImageMessageSendRequestDto);
                }
            }
        } catch (Exception e) {
            if (passingVehiclePayload.getDataSource() != null && passingVehiclePayload.getDataSource().equals(PassingVehicleDataSourceEnum.HIKVISION.getValue())) {
                updatePassingVehicleProcessStatus(passingVehiclePayload, VehicleProcessStatusEnum.PROCESS_FAIL.getValue(), "过车数据处理异常");
            }
        }
        return resultDto;
    }

    /**
     * 道闸入车记录处理
     *
     * @param passingVehiclePayload 停车过车消息
     * @param parkingInfo           停车场信息
     * @param parkingLotInfo        车位信息
     * @return
     */
    private ObjectResultDto<PassingVehicleProcessResultDto> processIntoPassVehicleRecord(PassingVehiclePayload passingVehiclePayload,
                                                                                         ParkingInfoResultDto parkingInfo,
                                                                                         ParkingLotResultDto parkingLotInfo) throws BusinessException {
        ObjectResultDto<PassingVehicleProcessResultDto> resultDto = new ObjectResultDto<>();
        try {
            PassingVehicleProcessResultDto passingVehicleProcessResultDto = new PassingVehicleProcessResultDto();
            boolean exceptionPassing = false;
            Integer exceptionType = PassingExceptionTypeEnum.NOT_EXCEPTION.getValue();
            String exceptionMessage = "";
            String plateNumber = passingVehiclePayload.getPlateNumber();
            Integer plateColor = passingVehiclePayload.getPlateColor();
            boolean validPlateNumber = PlateNumberUtil.isPlateNumber(passingVehiclePayload.getPlateNumber());
            if (validPlateNumber) {
                //1、找在场记录
                //根据车牌找到最近的相应的在场记录,存在异常过车记录
                ParkingVehicleRecordFindLastRecordRequestDto lastRecordRequestDto = new ParkingVehicleRecordFindLastRecordRequestDto();
                lastRecordRequestDto.setTenantId(parkingInfo.getTenantId());
                lastRecordRequestDto.setParkingId(parkingInfo.getId());
                lastRecordRequestDto.setPlateNumber(plateNumber);
                lastRecordRequestDto.setPlateColor(plateColor);
                lastRecordRequestDto.setEndTime(passingVehiclePayload.getPassTime());
                ObjectResultDto<ParkingVehicleRecordResultDto> lastParkingRecord = platformProcessService.findLastParkingRecord(lastRecordRequestDto);
                if (lastParkingRecord.isSuccess() && lastParkingRecord.getData() != null
                        && DateTimeUtils.getSecondDiff(lastParkingRecord.getData().getStartTime(),
                        passingVehiclePayload.getPassTime()) <= DEFAULT_VALID_ENTRY_PARKING_WINDOW_TIME) {
                    //0.时间窗口内连续入车的话，忽略掉
                    exceptionPassing = true;
                    exceptionType = PassingExceptionTypeEnum.PASSING_LESS_THAN_WINDOW.getValue();
                    exceptionMessage = "车辆五分钟内连续入场";
                }
            }
            if (exceptionPassing) {
                // 如果是异常入车,直接记录到异常过车记录，不做处理
                PassingVehicleRecordCreateRequestDto passingVehicleExceptionRecord = buildPassingVehicleRecord(passingVehiclePayload,
                        parkingInfo, parkingLotInfo, exceptionType, exceptionMessage);
                ObjectResultDto<PassingVehicleRecordCreatedResultDto> passingVehicleRecordSaveResult = platformProcessService.savePassingVehicleRecord(passingVehicleExceptionRecord);
                if (passingVehicleRecordSaveResult.isFailed() || passingVehicleRecordSaveResult.getData() == null) {
                    throw new BusinessException("入车过车记录处理失败,异常过车数据保存失败");
                }
                PassingVehicleRecordCreatedResultDto createdPassingVehicleRecord = passingVehicleRecordSaveResult.getData();
                passingVehicleProcessResultDto.setPassingId(createdPassingVehicleRecord.getId());
                passingVehicleProcessResultDto.setPassingNo(passingVehicleExceptionRecord.getPassingNo());
                log.warn("------[车辆五分钟内连续入场]--------[过车数据{}]------", JSON.toJSONString(passingVehiclePayload));
            } else {
                //1、保存平台过车记录
                PassingVehicleRecordCreateRequestDto passingVehicleRecord = buildPassingVehicleRecord(passingVehiclePayload,
                        parkingInfo, parkingLotInfo, exceptionType, "");
                ObjectResultDto<PassingVehicleRecordCreatedResultDto> passingVehicleRecordSaveResult =
                        platformProcessService.savePassingVehicleRecord(passingVehicleRecord);
                if (passingVehicleRecordSaveResult.isFailed() || passingVehicleRecordSaveResult.getData() == null) {
                    throw new BusinessException("入车过车记录处理失败,过车数据保存失败");
                }

                //2、生成在场车辆
                ParkingVehicleRecordSaveRequestDto parkingVehicleRecord = buildParkingVehicleRecord(
                        passingVehiclePayload, parkingInfo, parkingLotInfo);
                if (parkingVehicleRecord != null) {
                    //入车过车流水号
                    parkingVehicleRecord.setIntoRecordNo(passingVehicleRecord.getPassingNo());
                    platformProcessService.saveParkingVehicleRecord(parkingVehicleRecord);
                }
                //3、入车时可用车位数及泊位状态变更
                ParkingLotDecreaseRequestDto parkingLotIncreaseRequestDto = new ParkingLotDecreaseRequestDto();
                parkingLotIncreaseRequestDto.setTenantId(parkingInfo.getTenantId());
                parkingLotIncreaseRequestDto.setParkingId(parkingInfo.getId());
                if (parkingLotInfo != null) {
                    parkingLotIncreaseRequestDto.setParkingLotId(parkingLotInfo.getId());
                }
                decreaseParkingLotWithLock(parkingLotIncreaseRequestDto, getParkingInfoLockKey(parkingInfo.getCode()));

                //4、生成停车记录
                ParkingRecordAddRequestDto parkingRecord = buildParkingRecord(passingVehiclePayload, parkingInfo, parkingLotInfo);
                parkingRecord.setIntoRecordNo(passingVehicleRecord.getPassingNo());
                ObjectResultDto<SaveParkingRecordResultDto> saveParkingRecordResultDto = platformProcessService.saveParkingRecord(parkingRecord);
                if (parkingRecord.getAppointed()) {
                    ParkingLotAppointUpdateRequestDto parkingLotAppointUpdateRequestDto = new ParkingLotAppointUpdateRequestDto();
                    parkingLotAppointUpdateRequestDto.setParkingId(parkingInfo.getId());
                    parkingLotAppointUpdateRequestDto.setIncrease(Boolean.FALSE);
                    updateParkingLotAppointWithLock(parkingLotAppointUpdateRequestDto, getParkingLotAppointLockKey(parkingInfo.getCode()));
                }

                if (saveParkingRecordResultDto.isFailed() || saveParkingRecordResultDto.getData() == null) {
                    throw new BusinessException("入车过车记录处理失败,停车记录保存失败");
                }
                //如果车牌有效，根据停车记录初步产生停车账单
                if (validPlateNumber) {
                    //生成停车账单
                    ParkingOrderCreateRequestDto parkingOrder = generateParkingOrder(parkingRecord, parkingInfo, parkingLotInfo);
                    parkingOrder.setThirdBillSyncStatus(ThirdOrderSyncStatusEnum.UNCREATED.getValue());
                    parkingOrder.setThirdBillNo("");
                    ObjectResultDto<ParkingOrderCreateResultDto> orderCreateObjectResultDto = platformParkingOrderService.saveParkingOrder(parkingOrder);
                    if (orderCreateObjectResultDto.isSuccess() && orderCreateObjectResultDto.getData() != null) {
                        ParkingOrderCreateResultDto parkingOrderCreated = orderCreateObjectResultDto.getData();
                        //过车记录推送巡检
                        PassRecordMessageSendRequestDto passRecordMessageSendRequestDto = modelMapper.map(passingVehicleRecord, PassRecordMessageSendRequestDto.class);
                        if (parkingLotInfo != null) {
                            passRecordMessageSendRequestDto.setParkingLotCode(parkingLotInfo.getCode());
                            messageSendIntegrationService.sendInspectEnterPushMessage(passRecordMessageSendRequestDto);
                        }
                        //过车巡检消息发送
                        PassRecordNotifySendRequestDto passRecordNotifySendRequestDto = modelMapper.map(passingVehicleRecord, PassRecordNotifySendRequestDto.class);
                        if (parkingLotInfo != null) {
                            passRecordNotifySendRequestDto.setParkingLotNumber(parkingLotInfo.getNumber());
                            passRecordNotifySendRequestDto.setParkingLotCode(parkingLotInfo.getCode());
                            messageSendIntegrationService.sendPassRecordNotify(passRecordNotifySendRequestDto);
                        }
                        //发送停车入场消息
                        ParkingEnterPushMessageRequestDto parkingEnterPushMessageRequestDto = new ParkingEnterPushMessageRequestDto();
                        parkingEnterPushMessageRequestDto.setOrderId(parkingOrderCreated.getId());
                        parkingEnterPushMessageRequestDto.setOrderNo(parkingOrder.getOrderNo());
                        parkingEnterPushMessageRequestDto.setParkingName(parkingInfo.getFullName());
                        parkingEnterPushMessageRequestDto.setPlateNumber(plateNumber);
                        parkingEnterPushMessageRequestDto.setPlateColor(plateColor);
                        parkingEnterPushMessageRequestDto.setStartTime(parkingOrder.getStartTime());
                        messageSendIntegrationService.sendParkingEnterPushMessage(parkingEnterPushMessageRequestDto);
                    } else {
                        throw new BusinessException("入车过车记录处理失败,订单创建失败");
                    }
                }
                //过车消息处理结果
                PassingVehicleRecordCreatedResultDto createdPassingVehicleRecord = passingVehicleRecordSaveResult.getData();
                passingVehicleProcessResultDto.setPassingId(createdPassingVehicleRecord.getId());
                passingVehicleProcessResultDto.setPassingNo(passingVehicleRecord.getPassingNo());
            }
            //海康过车数据标记处理成功
            if (passingVehiclePayload.getDataSource().equals(PassingVehicleDataSourceEnum.HIKVISION.getValue())) {
                updatePassingVehicleProcessStatus(passingVehiclePayload, VehicleProcessStatusEnum.PROCESS_SUCCESS.getValue(), exceptionMessage);
            }
            resultDto.setData(passingVehicleProcessResultDto);
            resultDto.success();
        } catch (Exception e) {
            resultDto.failed();
            log.error("入车记录处理失败" + e.getMessage());
            throw new BusinessException("入车记录处理失败,异常原因{}", e);
        }
        return resultDto;
    }

    /**
     * 道闸出车记录处理
     *
     * @param passingVehiclePayload 停车过车消息
     * @param parkingInfo           停车场信息
     * @param parkingLotInfo        车位信息
     */
    private ObjectResultDto<PassingVehicleProcessResultDto> processOutPassVehicleRecord(PassingVehiclePayload passingVehiclePayload,
                                                                                        ParkingInfoResultDto parkingInfo,
                                                                                        ParkingLotResultDto parkingLotInfo) throws BusinessException {
        ObjectResultDto<PassingVehicleProcessResultDto> resultDto = new ObjectResultDto<>();

        try {
            //是否异常过车
            boolean exceptionPassing = false;
            Integer exceptionType = PassingExceptionTypeEnum.NOT_EXCEPTION.getValue();
            //异常过车原因
            String exceptionMessage = "";
            //出场
            //如果车牌有效，更新停车记录
            String plateNumber = passingVehiclePayload.getPlateNumber();
            Integer plateColor = passingVehiclePayload.getPlateColor();
            boolean validPlateNumber = PlateNumberUtil.isPlateNumber(plateNumber);
            //过车数据处理结果
            PassingVehicleProcessResultDto passingVehicleProcessResultDto = new PassingVehicleProcessResultDto();

            PassRecordNotifySendRequestDto passRecordNotifySendRequestDto = null;
            if (validPlateNumber) {
                //1、找到在场记录
                ParkingVehicleRecordFindLastRecordRequestDto lastRecordRequestDto = new ParkingVehicleRecordFindLastRecordRequestDto();
                lastRecordRequestDto.setTenantId(parkingInfo.getTenantId());
                lastRecordRequestDto.setParkingId(parkingInfo.getId());
                lastRecordRequestDto.setPlateNumber(plateNumber);
                lastRecordRequestDto.setPlateColor(plateColor);
                lastRecordRequestDto.setEndTime(passingVehiclePayload.getPassTime());
                ObjectResultDto<ParkingVehicleRecordResultDto> lastParkingRecord = platformProcessService.findLastParkingRecord(lastRecordRequestDto);
                if (lastParkingRecord.isSuccess() && lastParkingRecord.getData() != null) {
                    //在场记录存在
                    ParkingVehicleRecordResultDto parkingVehicleRecordResultDto = lastParkingRecord.getData();
                    //2、找到相应的入场记录
                    PassingVehicleRecordGetRequestDto passingVehicleRecordGetRequestDto = new PassingVehicleRecordGetRequestDto();
                    passingVehicleRecordGetRequestDto.setParkingId(parkingInfo.getId());
                    passingVehicleRecordGetRequestDto.setPassingNo(parkingVehicleRecordResultDto.getIntoRecordNo());
                    ObjectResultDto<PassingVehicleRecordResultDto> passVehicleRecord = platformProcessService.getPassVehicleRecord(passingVehicleRecordGetRequestDto);
                    if (passVehicleRecord.isSuccess() && passVehicleRecord.getData() != null) {
                        //入场记录
                        PassingVehicleRecordResultDto intoPassingVehicleRecordResultDto = passVehicleRecord.getData();
                        //3、删除在场车辆记录
                        ParkingVehicleRecordDeleteRequestDto parkingVehicleRecordDeleteRequestDto = new ParkingVehicleRecordDeleteRequestDto();
                        parkingVehicleRecordDeleteRequestDto.setId(parkingVehicleRecordResultDto.getId());
                        platformProcessService.deleteParkVehicleRecord(parkingVehicleRecordDeleteRequestDto);
                        //根据入车记录找到停车记录
                        ParkingRecordGetByIntoRecordNoRequestDto parkingRecordGetByIntoRecordNoRequestDto = new ParkingRecordGetByIntoRecordNoRequestDto();
                        parkingRecordGetByIntoRecordNoRequestDto.setTenantId(parkingInfo.getTenantId());
                        parkingRecordGetByIntoRecordNoRequestDto.setParkingId(parkingInfo.getId());
                        parkingRecordGetByIntoRecordNoRequestDto.setPassingNo(intoPassingVehicleRecordResultDto.getPassingNo());
                        parkingRecordGetByIntoRecordNoRequestDto.setPassType(PassingTypeEnum.ENTRY.getValue());
                        ObjectResultDto<ParkingRecordResultDto> recordServiceByIntoRecordId = platformProcessService.getByIntoRecordNo(parkingRecordGetByIntoRecordNoRequestDto);
                        if (recordServiceByIntoRecordId.isFailed() || recordServiceByIntoRecordId.getData() == null) {
                            exceptionPassing = true;
                            exceptionType = PassingExceptionTypeEnum.VEHICLE_NOT_PARKING.getValue();
                            exceptionMessage = "停车记录不存在";
                        } else {
                            //创建出场记录
                            PassingVehicleRecordCreateRequestDto outPassingVehicleRecord =
                                    buildPassingVehicleRecord(passingVehiclePayload, parkingInfo, parkingLotInfo, exceptionType, exceptionMessage);
                            ObjectResultDto<PassingVehicleRecordCreatedResultDto> outPassingVehicleRecordResultDto = platformProcessService.savePassingVehicleRecord(outPassingVehicleRecord);
                            if (outPassingVehicleRecordResultDto.isFailed() || outPassingVehicleRecordResultDto.getData() == null) {
                                throw new BusinessException("出车消息处理失败:过车记录创建失败");
                            }
                            //过车消息处理结果
                            PassingVehicleRecordCreatedResultDto createdPassingVehicleRecord = outPassingVehicleRecordResultDto.getData();
                            passingVehicleProcessResultDto.setPassingId(createdPassingVehicleRecord.getId());
                            passingVehicleProcessResultDto.setPassingNo(outPassingVehicleRecord.getPassingNo());
                            //更新停车场可预约车位数量
                            ParkingRecordResultDto parkingRecordResultDto = recordServiceByIntoRecordId.getData();
                            if (parkingRecordResultDto.getAppointed()) {
                                ParkingLotAppointUpdateRequestDto parkingLotAppointUpdateRequestDto = new ParkingLotAppointUpdateRequestDto();
                                parkingLotAppointUpdateRequestDto.setParkingId(parkingInfo.getId());
                                parkingLotAppointUpdateRequestDto.setIncrease(Boolean.TRUE);
                                updateParkingLotAppointWithLock(parkingLotAppointUpdateRequestDto, getParkingLotAppointLockKey(parkingInfo.getCode()));
                            }
                            //更新停车记录
                            ParkingRecordUpdateIntegrationRequestDto updateIntegrationRequestDto = new ParkingRecordUpdateIntegrationRequestDto();
                            updateIntegrationRequestDto.setId(parkingRecordResultDto.getId());
                            updateIntegrationRequestDto.setParkingId(parkingInfo.getId());
                            updateIntegrationRequestDto.setParkingId(parkingInfo.getId());
                            updateIntegrationRequestDto.setOutRecordNo(outPassingVehicleRecord.getPassingNo());
                            updateIntegrationRequestDto.setEndTime(passingVehiclePayload.getPassTime());
                            updateIntegrationRequestDto.setStatus(ParkingStatusEnum.OUTING.getValue());
                            updateParkingRecordWithLock(updateIntegrationRequestDto, getHikParkingRecordLockKey(parkingRecordResultDto.getRecordNo()));
                            //根据停车记录找到停车账单
                            ParkingOrderGetByRecordNoRequestDto parkingOrderGetByRecordNoRequestDto = new ParkingOrderGetByRecordNoRequestDto();
                            parkingOrderGetByRecordNoRequestDto.setTenantId(parkingInfo.getTenantId());
                            parkingOrderGetByRecordNoRequestDto.setRecordNo(parkingRecordResultDto.getRecordNo());
                            ObjectResultDto<ParkingOrderResultDto> parkingOrderResultDtoObjectResultDto = platformParkingOrderService.getParkingOrderByRecordNo(parkingOrderGetByRecordNoRequestDto);
                            ParkingOrderResultDto parkingOrderResultDto;
                            if (parkingOrderResultDtoObjectResultDto.isFailed() || parkingOrderResultDtoObjectResultDto.getData() == null) {
                                //账单不存在，创建账单
                                ParkingRecordAddRequestDto recordAddRequestDto = modelMapper.map(parkingRecordResultDto, ParkingRecordAddRequestDto.class);
                                ParkingOrderCreateRequestDto parkingOrderSaveRequestDto = generateParkingOrder(recordAddRequestDto, parkingInfo, parkingLotInfo);
                                parkingOrderSaveRequestDto.setThirdBillSyncStatus(ThirdOrderSyncStatusEnum.UNCREATED.getValue());
                                parkingOrderSaveRequestDto.setThirdBillNo("");
                                parkingOrderSaveRequestDto.setTenantId(parkingInfo.getTenantId());
                                ParkingOrderCreateResultDto data = null;
                                ObjectResultDto<ParkingOrderCreateResultDto> parkingOrderCreateResultDto = platformParkingOrderService.saveParkingOrder(parkingOrderSaveRequestDto);
                                if (parkingOrderCreateResultDto.isSuccess() && parkingOrderCreateResultDto.getData() != null) {
                                    data = parkingOrderCreateResultDto.getData();
                                }
                                parkingOrderResultDto = modelMapper.map(parkingOrderSaveRequestDto, ParkingOrderResultDto.class);
                                if (data != null) {
                                    parkingOrderResultDto.setId(data.getId());
                                }
                            } else {
                                parkingOrderResultDto = parkingOrderResultDtoObjectResultDto.getData();
                            }
                            ParkingOrderUpdateByPlateNumberRequestDto parkingOrderUpdate = new ParkingOrderUpdateByPlateNumberRequestDto();

                            //6.更新账单信息
                            parkingOrderUpdate.setId(parkingOrderResultDto.getId());
                            parkingOrderUpdate.setOrderNo(parkingOrderResultDto.getOrderNo());
                            parkingOrderUpdate.setPlateNumber(parkingOrderResultDto.getPlateNumber());
                            parkingOrderUpdate.setEndTime(passingVehiclePayload.getPassTime());
                            parkingOrderUpdate.setStatus(ParkingStatusEnum.OUTING.getValue());

                            //7.账单结算
                            if (!parkingOrderResultDto.getSettle()) {
                                settleParkingOrder(parkingOrderResultDto, parkingInfo, parkingLotInfo, passingVehiclePayload.getPassTime(), parkingOrderUpdate);
                            }

                            //同步第三方账单
                            if (StringUtils.isNotEmpty(parkingInfo.getLocalCode()) || StringUtils.isNotEmpty(parkingInfo.getHikParkId())) {
                                ObjectResultDto<ParkingOrderSyncResultDto> parkingOrderSyncObjectResultDto = parkingOrderIntegrationService.syncParkingOrder(parkingOrderResultDto, parkingInfo);
                                if (parkingOrderSyncObjectResultDto.isSuccess() && parkingOrderSyncObjectResultDto.getData() != null) {
                                    ParkingOrderSyncResultDto parkingOrderSyncResultDto = parkingOrderSyncObjectResultDto.getData();
                                    //更新第三账单信息
                                    parkingOrderUpdate.setThirdBillNo(parkingOrderSyncResultDto.getThirdBillNo());
                                    parkingOrderUpdate.setThirdBillSourceType(parkingOrderSyncResultDto.getThirdBillSourceType());
                                    parkingOrderUpdate.setThirdBillSyncStatus(parkingOrderSyncResultDto.getThirdBillSyncStatus());
                                    if (parkingOrderSyncResultDto.getTotalCost() != null) {
                                        parkingOrderUpdate.setPayableAmount(parkingOrderSyncResultDto.getTotalCost());
                                    }
                                    if (parkingOrderSyncResultDto.getParkingLength() != null) {
                                        parkingOrderUpdate.setParkingLength(parkingOrderSyncResultDto.getParkingLength().longValue());
                                    }
                                }
                            }

                            //更新为出车泊位信息
                            if (parkingLotInfo != null) {
                                parkingOrderUpdate.setParkingLotId(parkingLotInfo.getId());
                                parkingOrderUpdate.setParkingLotCode(parkingLotInfo.getCode());
                                parkingOrderUpdate.setParkingLotNumber(parkingLotInfo.getNumber());
                            }
                            //9.更新账单信息
                            updateParkingOrderWithLock(parkingOrderUpdate);
                            //10.推送新账单通知
                            //是否需要推送车主用户通知消息
                            //目前仅需要支付的订单才需要通知
                            boolean needPush = false;
                            if (parkingOrderUpdate.getPayable() && parkingOrderUpdate.getNeedPay()) {
                                needPush = true;
                            }
                            if (needPush) {
                                //推送新账单通知消息
                                ParkingOrderPushMessageRequestDto parkingOrderPushMessageRequestDto = new ParkingOrderPushMessageRequestDto();
                                parkingOrderPushMessageRequestDto.setOrderId(parkingOrderResultDto.getId());
                                parkingOrderPushMessageRequestDto.setOrderNo(parkingOrderResultDto.getOrderNo());
                                parkingOrderPushMessageRequestDto.setParkingName(parkingOrderResultDto.getParkingName());
                                parkingOrderPushMessageRequestDto.setPlateColor(parkingOrderResultDto.getPlateColor());
                                parkingOrderPushMessageRequestDto.setPlateNumber(parkingOrderResultDto.getPlateNumber());
                                parkingOrderPushMessageRequestDto.setStartTime(parkingOrderResultDto.getStartTime());
                                messageSendIntegrationService.sendParkingOrderPushMessage(parkingOrderPushMessageRequestDto);
                            }
                            //过车巡检消息发送
                            passRecordNotifySendRequestDto = modelMapper.map(outPassingVehicleRecord, PassRecordNotifySendRequestDto.class);
                        }
                    } else {
                        //入场记录不存在
                        exceptionPassing = true;
                        exceptionType = PassingExceptionTypeEnum.PASSING_ENTER_NOT_FOUNT.getValue();
                        exceptionMessage = "入场记录不存在";
                    }
                } else {
                    //车辆不在场
                    exceptionPassing = true;
                    exceptionType = PassingExceptionTypeEnum.VEHICLE_NOT_PARKING.getValue();
                    exceptionMessage = "车辆不在场";
                }
                if (exceptionPassing) {
                    //6.相应的入场记录不存在,异常过车记录
                    exceptionMessage = "入场记录不存在";
                    PassingVehicleRecordCreateRequestDto outPassingVehicleRecord = buildPassingVehicleRecord(passingVehiclePayload,
                            parkingInfo, parkingLotInfo, exceptionType, exceptionMessage);
                    ObjectResultDto<PassingVehicleRecordCreatedResultDto> outPassingVehicleRecordResultDto =
                            platformProcessService.savePassingVehicleRecord(outPassingVehicleRecord);
                    if (outPassingVehicleRecordResultDto.isFailed() || outPassingVehicleRecordResultDto.getData() == null) {
                        throw new BusinessException("出车消息处理失败:过车记录创建失败");
                    }
                    //过车消息处理结果
                    PassingVehicleRecordCreatedResultDto createdPassingVehicleRecord = outPassingVehicleRecordResultDto.getData();
                    passingVehicleProcessResultDto.setPassingId(createdPassingVehicleRecord.getId());
                    passingVehicleProcessResultDto.setPassingNo(outPassingVehicleRecord.getPassingNo());

                    //过车巡检消息发送
                    passRecordNotifySendRequestDto = modelMapper.map(outPassingVehicleRecord, PassRecordNotifySendRequestDto.class);

                    log.info("------[入场记录不存在]--------[平台过车数据{}]------", JSON.toJSONString(passingVehiclePayload));
                }
            } else {
                //无车牌过车消息处理
                PassingVehicleRecordCreateRequestDto outPassingVehicleRecord =
                        buildPassingVehicleRecord(passingVehiclePayload, parkingInfo, parkingLotInfo, exceptionType, exceptionMessage);
                ObjectResultDto<PassingVehicleRecordCreatedResultDto> outPassingVehicleRecordResultDto = platformProcessService.savePassingVehicleRecord(outPassingVehicleRecord);
                if (outPassingVehicleRecordResultDto.isFailed() || outPassingVehicleRecordResultDto.getData() == null) {
                    throw new BusinessException("出车消息处理失败:过车记录创建失败");
                }
                //过车消息处理结果
                PassingVehicleRecordCreatedResultDto createdPassingVehicleRecord = outPassingVehicleRecordResultDto.getData();
                passingVehicleProcessResultDto.setPassingId(createdPassingVehicleRecord.getId());
                passingVehicleProcessResultDto.setPassingNo(outPassingVehicleRecord.getPassingNo());
            }
            //无论有无车牌,出车可用车位数及泊位状态变更
            ParkingLotIncreaseRequestDto parkingLotIncreaseRequestDto = new ParkingLotIncreaseRequestDto();
            parkingLotIncreaseRequestDto.setTenantId(parkingInfo.getTenantId());
            parkingLotIncreaseRequestDto.setParkingId(parkingInfo.getId());
            if (parkingLotInfo != null) {
                parkingLotIncreaseRequestDto.setParkingLotId(parkingLotInfo.getId());
            }
            increaseParkingLotWithLock(parkingLotIncreaseRequestDto, getParkingInfoLockKey(parkingInfo.getCode()));
            //发送出车巡检消息
            if (passRecordNotifySendRequestDto != null) {
                if (parkingLotInfo != null) {
                    passRecordNotifySendRequestDto.setParkingLotCode(parkingLotInfo.getCode());
                    passRecordNotifySendRequestDto.setParkingLotNumber(parkingLotInfo.getNumber());
                }
                messageSendIntegrationService.sendPassRecordNotify(passRecordNotifySendRequestDto);
            }

            //海康过车数据标记处理成功
            updatePassingVehicleProcessStatus(passingVehiclePayload, VehicleProcessStatusEnum.PROCESS_SUCCESS.getValue(), exceptionMessage);

            resultDto.setData(passingVehicleProcessResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("出车消息处理失败,异常信息:{}", e.getMessage());
            resultDto.failed();
            throw new BusinessException("出车消息处理失败", e);
        }
        return resultDto;
    }

    /**
     * 根据过车消息获取停车场信息
     *
     * @param passingVehiclePayload passingVehiclePayload
     * @return
     */
    private ParkingInfoResultDto getParkingInfo(PassingVehiclePayload passingVehiclePayload) {

        if (PassingVehicleDataSourceEnum.HIKVISION.getValue().equals(passingVehiclePayload.getDataSource())) {
            //海康过车数据源
            ParkingInfoGetByHikParkIdRequestDto parkingInfoGetByHikParkIdRequestDto = new ParkingInfoGetByHikParkIdRequestDto();
            parkingInfoGetByHikParkIdRequestDto.setHikParkId(passingVehiclePayload.getParkCode());
            ObjectResultDto<ParkingInfoResultDto> parkInfoByHikParkId = platformParkingInfoService.getParkInfoByHikParkId(parkingInfoGetByHikParkIdRequestDto);
            if (parkInfoByHikParkId.isFailed() || parkInfoByHikParkId.getData() == null) {
                log.warn("------[平台停车场不存在]--------[过车数据{}]------", JSON.toJSONString(passingVehiclePayload));
                return null;
            }
            return parkInfoByHikParkId.getData();
        } else if (PassingVehicleDataSourceEnum.ANY_PARKING.getValue().equals(passingVehiclePayload.getDataSource()) || PassingVehicleDataSourceEnum.FUSHANG.getValue().equals(passingVehiclePayload.getDataSource())) {
            ParkingInfoGetByCodeRequestDto parkingGetByCodeRequestDto = new ParkingInfoGetByCodeRequestDto();
            parkingGetByCodeRequestDto.setCode(passingVehiclePayload.getCloudParkingCode());
            ObjectResultDto<ParkingInfoResultDto> parkInfoByLocalCode = platformParkingInfoService.getParkInfoByCode(parkingGetByCodeRequestDto);
            if (parkInfoByLocalCode.isFailed() || parkInfoByLocalCode.getData() == null) {
                log.warn("------[平台停车场不存在]--------[过车数据{}]------", JSON.toJSONString(passingVehiclePayload));
                return null;
            }
            return parkInfoByLocalCode.getData();
        }
        return null;
    }

    /**
     * 根据过车消息获取泊位信息
     *
     * @param parkingInfo           parkingInfo
     * @param passingVehiclePayload passingVehiclePayload
     * @return
     */
    private ParkingLotResultDto getParkingLotInfo(ParkingInfoResultDto parkingInfo, PassingVehiclePayload passingVehiclePayload) {
        if (PassingVehicleDataSourceEnum.HIKVISION.getValue().equals(passingVehiclePayload.getDataSource())) {
            //海康过车数据源
            if (StringUtils.isNotEmpty(passingVehiclePayload.getBerthCode())
                    || StringUtils.isNotEmpty(passingVehiclePayload.getBerthNumber())) {
                ParkingLotInfoGetForPassVehicleRequestDto getForPassVehicleRequestDto = new ParkingLotInfoGetForPassVehicleRequestDto();
                getForPassVehicleRequestDto.setTenantId(parkingInfo.getTenantId());
                getForPassVehicleRequestDto.setParkingId(parkingInfo.getId());
                getForPassVehicleRequestDto.setHikParkLotId(passingVehiclePayload.getBerthCode());
                getForPassVehicleRequestDto.setBerthNumber(passingVehiclePayload.getBerthNumber());
                ObjectResultDto<ParkingLotResultDto> parkingLotForPassVehicle = platformParkingInfoService.getParkingLotForPassVehicle(getForPassVehicleRequestDto);
                if (parkingLotForPassVehicle.isFailed() || parkingLotForPassVehicle.getData() == null) {
                    log.warn("------[平台泊位不存在]--------[过车数据{}]------", JSON.toJSONString(passingVehiclePayload));
                    return null;
                } else {
                    return parkingLotForPassVehicle.getData();
                }
            }
        } else if (PassingVehicleDataSourceEnum.ANY_PARKING.getValue().equals(passingVehiclePayload.getDataSource()) ||
                PassingVehicleDataSourceEnum.FUSHANG.getValue().equals(passingVehiclePayload.getDataSource())) {
            if (StringUtils.isEmpty(passingVehiclePayload.getCloudLotCode()) && StringUtils.isEmpty(passingVehiclePayload.getCloudLotNumber())) {
                log.warn("------[平台泊位不存在]--------[过车数据{}]------", JSON.toJSONString(passingVehiclePayload));
                return null;
            }
            ParkingLotInfoGetByLocalRequestDto parkingLotInfoGetByLocalRequestDto = new ParkingLotInfoGetByLocalRequestDto();
            parkingLotInfoGetByLocalRequestDto.setTenantId(parkingInfo.getTenantId());
            parkingLotInfoGetByLocalRequestDto.setParkingId(parkingInfo.getId());
            if (StringUtils.isNotEmpty(passingVehiclePayload.getCloudLotCode())) {
                parkingLotInfoGetByLocalRequestDto.setLocalCode(passingVehiclePayload.getCloudLotCode());
            }
            if (StringUtils.isNotEmpty(passingVehiclePayload.getCloudLotNumber())) {
                parkingLotInfoGetByLocalRequestDto.setNumber(passingVehiclePayload.getCloudLotNumber());
            }
            ObjectResultDto<ParkingLotResultDto> parkingLotByLocal = platformParkingInfoService.getParkingLotByLocal(parkingLotInfoGetByLocalRequestDto);
            if (parkingLotByLocal.isFailed() || parkingLotByLocal.getData() == null) {
                log.warn("------[平台泊位不存在]--------[过车数据{}]------", JSON.toJSONString(passingVehiclePayload));
                return null;
            } else {
                return parkingLotByLocal.getData();
            }
        }
        return null;
    }

    /**
     * 处理地磁过车数据消息
     *
     * @param magneticMessagePayLoad passingVehiclePayload
     * @return ResultDto
     * @throws BusinessException exception
     */
    @Override
    public ResultDto processMagneticVehicleRecord(MagneticPassingPayload magneticMessagePayLoad) throws BusinessException {
        ResultDto resultDto = new ResultDto();
        if (magneticMessagePayLoad == null) {
            return resultDto.success();
        }
        try {
            Integer exceptionType = PassingExceptionTypeEnum.NOT_EXCEPTION.getValue();
            //异常原因
            String exceptionMessage = "";
            ParkingInfoGetRequestDto parkingInfoGetRequestDto = new ParkingInfoGetRequestDto();
            parkingInfoGetRequestDto.setParkingId(magneticMessagePayLoad.getParkingId());
            ObjectResultDto<ParkingInfoResultDto> parkingInfoObjectResultDto = platformParkingInfoService.getParkInfoById(parkingInfoGetRequestDto);
            if (parkingInfoObjectResultDto.isFailed() || parkingInfoObjectResultDto.getData() == null) {
                exceptionType = PassingExceptionTypeEnum.PARKING_NOT_FOUND.getValue();
                exceptionMessage = "平台停车场不存在";
                log.warn("------[平台停车场不存在]--------[过车数据{}]------", JSON.toJSONString(magneticMessagePayLoad));
                PassingVehicleRecordCreateRequestDto passingVehicleExceptionRecordAddRequestDto =
                        buildMagneticPassingVehicleRecord(magneticMessagePayLoad, null, null, exceptionType, exceptionMessage);
                passingVehicleExceptionRecordAddRequestDto.setDataType(PassDataTypeEnum.MAGNETIC.getValue());
                platformProcessService.savePassingVehicleRecord(passingVehicleExceptionRecordAddRequestDto);
                return resultDto.success();
            }
            ParkingInfoResultDto parkingInfo = parkingInfoObjectResultDto.getData();
            //平台泊位
            //如果车位不空
            boolean exceptionProcess = false;
            ParkingLotResultDto parkinglotInfo = null;
            if (null == magneticMessagePayLoad.getParkingLotId()) {
                exceptionType = PassingExceptionTypeEnum.PARKING_LOT_NOT_FOUND.getValue();
                exceptionProcess = true;
            } else {
                ParkingLotInfoGetByParkingIdRequestDto getForPassVehicleRequestDto = new ParkingLotInfoGetByParkingIdRequestDto();
                getForPassVehicleRequestDto.setParkingId(magneticMessagePayLoad.getParkingId());
                getForPassVehicleRequestDto.setParkingLotId(magneticMessagePayLoad.getParkingLotId());
                getForPassVehicleRequestDto.setTenantId(parkingInfo.getTenantId());
                ObjectResultDto<ParkingLotResultDto> parkingLotForPassVehicle = platformParkingInfoService.getParkingLotByParkingId(getForPassVehicleRequestDto);
                if (parkingLotForPassVehicle.isFailed() || parkingLotForPassVehicle.getData() == null) {
                    exceptionType = PassingExceptionTypeEnum.PARKING_LOT_NOT_FOUND.getValue();
                    exceptionProcess = true;
                    exceptionMessage = "平台泊位不存在";
                } else {
                    parkinglotInfo = parkingLotForPassVehicle.getData();
                }
            }
            //是否异常过车数据
            if (exceptionProcess) {
                PassingVehicleRecordCreateRequestDto passingVehicleExceptionRecordAddRequestDto =
                        buildMagneticPassingVehicleRecord(magneticMessagePayLoad, parkingInfo, parkinglotInfo, exceptionType, exceptionMessage);
                passingVehicleExceptionRecordAddRequestDto.setDataType(PassDataTypeEnum.MAGNETIC.getValue());
                passingVehicleExceptionRecordAddRequestDto.setTenantId(parkingInfo.getTenantId());
                platformProcessService.savePassingVehicleRecord(passingVehicleExceptionRecordAddRequestDto);
                return resultDto.success();
            }
            //存入过车记录
            PassingVehicleRecordCreateRequestDto passingVehicleRecordEntity = buildMagneticPassingVehicleRecord(magneticMessagePayLoad, parkingInfo, parkinglotInfo,
                    PassingExceptionTypeEnum.NOT_EXCEPTION.getValue(), "");
            passingVehicleRecordEntity.setGateId(parkinglotInfo.getGarageId());
            passingVehicleRecordEntity.setParkingLotCode(parkinglotInfo.getCode());
            passingVehicleRecordEntity.setParkingLotNumber(parkinglotInfo.getNumber());
            passingVehicleRecordEntity.setDataType(PassDataTypeEnum.MAGNETIC.getValue());
            platformProcessService.savePassingVehicleRecord(passingVehicleRecordEntity);
            //判断过车类型
            if (magneticMessagePayLoad.getPassingType().compareTo(PassingTypeEnum.ENTRY.getValue()) == 0) {
                //地磁入车过车数据处理
                resultDto = intoMagneticPassVehicleDispose(magneticMessagePayLoad, parkingInfo, parkinglotInfo, passingVehicleRecordEntity);
            } else if (magneticMessagePayLoad.getPassingType().compareTo(PassingTypeEnum.EXIT.getValue()) == 0) {
                //地磁处理出车过车数据
                resultDto = processOutMagneticPassVehicle(magneticMessagePayLoad, parkingInfo, parkinglotInfo, passingVehicleRecordEntity);
            }
        } catch (Exception e) {
            resultDto.failed();
            log.error("处理地磁过车记录服务失败" + e.getMessage());
        }

        return resultDto;
    }

    /**
     * 任意停车平台图片消息处理
     *
     * @param anyPassRecordImagePayload
     * @return
     * @throws BusinessException
     */
    @Override
    public ResultDto anyPassRecordImageProcess(AnyPassRecordImagePayload anyPassRecordImagePayload) throws BusinessException {
        return anyRecordImageProcess(anyPassRecordImagePayload, MessageQueueDefinitions.Topic.ANY_PARKING_PASSING_RECORD_IMAGE, ParkingImageTypeEnum.PASSING);

    }


    /**
     * 地磁入车过车数据处理
     *
     * @param magneticMessagePayLoad     地磁消息
     * @param parkingInfo                停车场信息
     * @param passingVehicleRecordEntity 过车信息
     */
    private ResultDto intoMagneticPassVehicleDispose(MagneticPassingPayload magneticMessagePayLoad,
                                                     ParkingInfoResultDto parkingInfo,
                                                     ParkingLotResultDto parkingLotInfo,
                                                     PassingVehicleRecordCreateRequestDto passingVehicleRecordEntity) {
        ResultDto resultDto = new ResultDto();
        Integer exceptionType;
        try {
            boolean exceptionPassing = false;
            //入车
            //0.时间窗口内连续入车的话，忽略掉
            //1、找在场记录
            ParkingVehicleRecordFindLastRecordRequestDto parkingVehicleRecordFindLastRecordRequestDto = new ParkingVehicleRecordFindLastRecordRequestDto();
            parkingVehicleRecordFindLastRecordRequestDto.setTenantId(parkingInfo.getTenantId());
            parkingVehicleRecordFindLastRecordRequestDto.setParkingId(magneticMessagePayLoad.getParkingId());
            parkingVehicleRecordFindLastRecordRequestDto.setParkingLotId(magneticMessagePayLoad.getParkingLotId());
            parkingVehicleRecordFindLastRecordRequestDto.setEndTime(magneticMessagePayLoad.getPassTime());
            ObjectResultDto<ParkingVehicleRecordResultDto> lastParkingRecord = platformProcessService.findLastParkingRecord(parkingVehicleRecordFindLastRecordRequestDto);
            if (lastParkingRecord.isSuccess() && lastParkingRecord.getData() != null
                    && DateTimeUtils.getSecondDiff(lastParkingRecord.getData().getStartTime(), magneticMessagePayLoad.getPassTime()) <= DEFAULT_VALID_ENTRY_PARKING_WINDOW_TIME) {
                exceptionPassing = true;
            }
            //如果是异常入车
            if (exceptionPassing) {
                //6.相应的入场记录不存在,异常过车记录
                exceptionType = PassingExceptionTypeEnum.PASSING_LESS_THAN_WINDOW.getValue();
                String message = "车辆两分钟内连续入场";
                PassingVehicleRecordCreateRequestDto passingVehicleExceptionRecordAddRequestDto =
                        buildMagneticPassingVehicleRecord(magneticMessagePayLoad, parkingInfo, parkingLotInfo, exceptionType, message);
                passingVehicleExceptionRecordAddRequestDto.setPassingNo(passingVehicleRecordEntity.getPassingNo());
                passingVehicleExceptionRecordAddRequestDto.setTenantId(parkingInfo.getTenantId());
                passingVehicleExceptionRecordAddRequestDto.setParkingName(parkingInfo.getFullName());
                passingVehicleExceptionRecordAddRequestDto.setParkingCode(parkingInfo.getCode());
                passingVehicleExceptionRecordAddRequestDto.setParkingLotCode(parkingLotInfo.getCode());
                passingVehicleExceptionRecordAddRequestDto.setParkingLotNumber(parkingLotInfo.getNumber());
                platformProcessService.savePassingVehicleRecord(passingVehicleExceptionRecordAddRequestDto);
                log.warn("------[车辆两分钟内连续入场]--------[地磁过车数据{}]------", JSON.toJSONString(magneticMessagePayLoad));
            } else {
                //1、生成在场车辆
                ParkingVehicleRecordSaveRequestDto parkingVehicleRecordEntity = sealMagneticParkingVehicleRecord(passingVehicleRecordEntity);
                if (parkingVehicleRecordEntity != null) {
                    parkingVehicleRecordEntity.setIntoRecordNo(passingVehicleRecordEntity.getPassingNo());
                    parkingVehicleRecordEntity.setParkingName(parkingInfo.getFullName());
                    parkingVehicleRecordEntity.setParkingCode(parkingInfo.getCode());
                    parkingVehicleRecordEntity.setParkingLotCode(parkingLotInfo.getCode());
                    parkingVehicleRecordEntity.setParkingLotNumber(parkingLotInfo.getNumber());
                    platformProcessService.saveParkingVehicleRecord(parkingVehicleRecordEntity);
                }
                //入车可用车位数及泊位状态变更
                ParkingLotDecreaseRequestDto parkingLotIncreaseRequestDto = new ParkingLotDecreaseRequestDto();
                parkingLotIncreaseRequestDto.setTenantId(parkingInfo.getTenantId());
                parkingLotIncreaseRequestDto.setParkingId(parkingInfo.getId());
                parkingLotIncreaseRequestDto.setParkingLotId(parkingLotInfo.getId());
                decreaseParkingLotWithLock(parkingLotIncreaseRequestDto, getParkingInfoLockKey(parkingInfo.getCode()));

                //4、生成停车记录
                ParkingRecordAddRequestDto parkingRecordEntity = generateMagneticParkingRecord(passingVehicleRecordEntity);
                parkingRecordEntity.setIntoRecordNo(passingVehicleRecordEntity.getPassingNo());
                parkingRecordEntity.setParkingName(parkingInfo.getFullName());
                parkingRecordEntity.setParkingCode(parkingInfo.getCode());
                parkingRecordEntity.setParkingLotCode(parkingLotInfo.getCode());
                parkingRecordEntity.setParkingLotNumber(parkingLotInfo.getNumber());
                platformProcessService.saveParkingRecord(parkingRecordEntity);
            }
            //过车数据推送巡检
            PassRecordMessageSendRequestDto passRecordMessageSendRequestDto = modelMapper.map(passingVehicleRecordEntity, PassRecordMessageSendRequestDto.class);
            passRecordMessageSendRequestDto.setParkingLotCode(parkingLotInfo.getCode());
            messageSendIntegrationService.sendInspectEnterPushMessage(passRecordMessageSendRequestDto);
            //过车巡检消息发送
            PassRecordNotifySendRequestDto passRecordNotifySendRequestDto = modelMapper.map(passingVehicleRecordEntity, PassRecordNotifySendRequestDto.class);
            passRecordNotifySendRequestDto.setParkingLotCode(parkingLotInfo.getCode());
            messageSendIntegrationService.sendPassRecordNotify(passRecordNotifySendRequestDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("处理地磁入车记录失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 地磁处理出车过车数据
     *
     * @param magneticMessagePayLoad     地磁消息队列
     * @param parkingInfo                停车场信息
     * @param passingVehicleRecordEntity 过车信息
     * @return
     */
    private ResultDto processOutMagneticPassVehicle(MagneticPassingPayload magneticMessagePayLoad,
                                                    ParkingInfoResultDto parkingInfo,
                                                    ParkingLotResultDto parkingLotInfo,
                                                    PassingVehicleRecordCreateRequestDto passingVehicleRecordEntity) {
        ResultDto resultDto = new ResultDto();

        try {
            String exceptionMessage = "";
            boolean exceptionPassing = false;
            Integer exceptionType = PassingExceptionTypeEnum.NOT_EXCEPTION.getValue();
            //出车
            Boolean passInspectNotify = true;
            //1、找到在场记录
            //1、找在场记录
            ParkingVehicleRecordFindLastRecordRequestDto parkingVehicleRecordFindLastRecordRequestDto = new ParkingVehicleRecordFindLastRecordRequestDto();
            parkingVehicleRecordFindLastRecordRequestDto.setTenantId(parkingInfo.getTenantId());
            parkingVehicleRecordFindLastRecordRequestDto.setParkingId(magneticMessagePayLoad.getParkingId());
            parkingVehicleRecordFindLastRecordRequestDto.setParkingLotId(magneticMessagePayLoad.getParkingLotId());
            parkingVehicleRecordFindLastRecordRequestDto.setEndTime(magneticMessagePayLoad.getPassTime());
            ObjectResultDto<ParkingVehicleRecordResultDto> lastParkingRecord = platformProcessService.findLastParkingRecord(parkingVehicleRecordFindLastRecordRequestDto);
            if (lastParkingRecord.isSuccess() && lastParkingRecord.getData() != null) {
                //在场记录存在
                ParkingVehicleRecordResultDto parkingVehicleRecordResultDto = lastParkingRecord.getData();
                //2、找到相应的入场记录
                PassingVehicleRecordGetRequestDto passingVehicleRecordGetRequestDto = new PassingVehicleRecordGetRequestDto();
                passingVehicleRecordGetRequestDto.setPassingNo(parkingVehicleRecordResultDto.getIntoRecordNo());
                passingVehicleRecordGetRequestDto.setParkingId(parkingInfo.getId());
                ObjectResultDto<PassingVehicleRecordResultDto> recordServicePassVehicleRecord = platformProcessService.getPassVehicleRecord(passingVehicleRecordGetRequestDto);
                if (recordServicePassVehicleRecord.isSuccess() && recordServicePassVehicleRecord.getData() != null) {
                    PassingVehicleRecordResultDto passVehicleRecordData = recordServicePassVehicleRecord.getData();
                    if (passVehicleRecordData.getPlateNoExist()) {
                        //发送带车牌的出车巡检消息
                        PassRecordNotifySendRequestDto passRecordNotifySendRequestDto = modelMapper.map(passingVehicleRecordEntity, PassRecordNotifySendRequestDto.class);
                        passRecordNotifySendRequestDto.setPlateNumber(passVehicleRecordData.getPlateNumber());
                        passRecordNotifySendRequestDto.setPlateColor(passVehicleRecordData.getPlateColor());
                        passRecordNotifySendRequestDto.setCarType(passVehicleRecordData.getCarType());
                        passRecordNotifySendRequestDto.setParkingLotCode(parkingLotInfo.getCode());
                        passInspectNotify = false;
                        messageSendIntegrationService.sendPassRecordNotify(passRecordNotifySendRequestDto);
                        //更新出车记录
                        PassingRecordUpdateRequestDto passingRecordUpdateRequestDto = new PassingRecordUpdateRequestDto();
                        passingRecordUpdateRequestDto.setPassingNo(passingVehicleRecordEntity.getPassingNo());
                        passingRecordUpdateRequestDto.setPlateNumber(passVehicleRecordData.getPlateNumber());
                        passingRecordUpdateRequestDto.setPlateColor(passVehicleRecordData.getPlateColor());
                        passingRecordUpdateRequestDto.setCarType(passVehicleRecordData.getCarType());
                        passingRecordUpdateRequestDto.setParkingId(parkingInfo.getId());
                        updatePassVehicleRecordLock(passingRecordUpdateRequestDto,
                                getUpdatePassVehicleRecordLockKey(passingVehicleRecordEntity.getPassingNo()));
                        //3、删除在场车辆记录
                        ParkingVehicleRecordDeleteRequestDto parkingVehicleRecordDeleteRequestDto = new ParkingVehicleRecordDeleteRequestDto();
                        parkingVehicleRecordDeleteRequestDto.setId(parkingVehicleRecordResultDto.getId());
                        platformProcessService.deleteParkVehicleRecord(parkingVehicleRecordDeleteRequestDto);
                        //4、停车记录更新
                        ParkingRecordGetByIntoRecordNoRequestDto parkingRecordGetByIntoRecordNoRequestDto = new ParkingRecordGetByIntoRecordNoRequestDto();
                        parkingRecordGetByIntoRecordNoRequestDto.setPassingNo(passVehicleRecordData.getPassingNo());
                        parkingRecordGetByIntoRecordNoRequestDto.setParkingId(magneticMessagePayLoad.getParkingId());
                        parkingRecordGetByIntoRecordNoRequestDto.setParkingLotId(magneticMessagePayLoad.getParkingLotId());
                        parkingRecordGetByIntoRecordNoRequestDto.setPassType(PassingTypeEnum.ENTRY.getValue());
                        parkingRecordGetByIntoRecordNoRequestDto.setTenantId(parkingInfo.getTenantId());
                        ObjectResultDto<ParkingRecordResultDto> recordServiceByIntoRecordId = platformProcessService.getByIntoRecordNo(parkingRecordGetByIntoRecordNoRequestDto);
                        if (recordServiceByIntoRecordId.isFailed() || recordServiceByIntoRecordId.getData() == null) {
                            exceptionType = PassingExceptionTypeEnum.PARK_RECORD_NOT_FOUNT.getValue();
                            exceptionPassing = true;
                            exceptionMessage = "停车记录不存在";
                        } else {
                            ParkingRecordResultDto parkingRecord = recordServiceByIntoRecordId.getData();
                            if (parkingRecord.getAppointed()) {
                                ParkingLotAppointUpdateRequestDto parkingLotAppointUpdateRequestDto = new ParkingLotAppointUpdateRequestDto();
                                parkingLotAppointUpdateRequestDto.setParkingId(parkingInfo.getId());
                                parkingLotAppointUpdateRequestDto.setIncrease(Boolean.TRUE);
                                updateParkingLotAppointWithLock(parkingLotAppointUpdateRequestDto, getParkingLotAppointLockKey(parkingInfo.getCode()));
                            }
                            ParkingRecordUpdateIntegrationRequestDto parkingRecordUpdate = new ParkingRecordUpdateIntegrationRequestDto();
                            parkingRecordUpdate.setParkingId(parkingInfo.getId());
                            parkingRecordUpdate.setId(parkingRecord.getId());
                            parkingRecordUpdate.setParkingId(parkingInfo.getId());
                            parkingRecordUpdate.setOutRecordNo(passingVehicleRecordEntity.getPassingNo());
                            parkingRecordUpdate.setEndTime(passingVehicleRecordEntity.getPassTime());
                            parkingRecordUpdate.setStatus(ParkingStatusEnum.OUTING.getValue());
                            updateParkingRecordWithLock(parkingRecordUpdate, getMagneticParkingRecordLockKey(parkingRecord.getId()));

                            //找到停车账单
                            ParkingOrderGetByRecordNoRequestDto parkingOrderGetByRecordNoRequestDto = new ParkingOrderGetByRecordNoRequestDto();
                            parkingOrderGetByRecordNoRequestDto.setTenantId(parkingInfo.getTenantId());
                            parkingOrderGetByRecordNoRequestDto.setRecordNo(parkingRecord.getRecordNo());
                            ObjectResultDto<ParkingOrderResultDto> byRecordNo = platformParkingOrderService.getParkingOrderByRecordNo(parkingOrderGetByRecordNoRequestDto);
                            ParkingOrderResultDto parkingOrderResultDto;
                            if (byRecordNo.isFailed() || byRecordNo.getData() == null) {
                                //账单不存在,创建新账单
                                ParkingRecordAddRequestDto recordAddRequestDto = modelMapper.map(parkingRecord, ParkingRecordAddRequestDto.class);
                                ParkingOrderCreateRequestDto parkingOrderSaveRequestDto = generateParkingOrder(recordAddRequestDto, parkingInfo, parkingLotInfo);
                                parkingOrderSaveRequestDto.setTenantId(parkingInfo.getTenantId());
                                ObjectResultDto<ParkingOrderCreateResultDto> parkingOrderCreateResultDto = platformParkingOrderService.saveParkingOrder(parkingOrderSaveRequestDto);
                                ParkingOrderCreateResultDto data = null;
                                if (parkingOrderCreateResultDto.isSuccess() && parkingOrderCreateResultDto.getData() != null) {
                                    data = parkingOrderCreateResultDto.getData();
                                }
                                parkingOrderResultDto = modelMapper.map(parkingOrderSaveRequestDto,
                                        ParkingOrderResultDto.class);
                                if (data != null) {
                                    parkingOrderResultDto.setId(data.getId());
                                }
                            } else {
                                parkingOrderResultDto = byRecordNo.getData();
                            }
                            ParkingOrderUpdateByPlateNumberRequestDto parkingOrderUpdate = new ParkingOrderUpdateByPlateNumberRequestDto();
                            parkingOrderUpdate.setId(parkingOrderResultDto.getId());
                            parkingOrderUpdate.setOrderNo(parkingOrderResultDto.getOrderNo());

                            //6.更新账单信息
                            parkingOrderUpdate.setEndTime(magneticMessagePayLoad.getPassTime());
                            parkingOrderUpdate.setStatus(ParkingStatusEnum.OUTING.getValue());
                            //7.账单结算
                            settleParkingOrder(parkingOrderResultDto, parkingInfo, parkingLotInfo, magneticMessagePayLoad.getPassTime(), parkingOrderUpdate);
                            //9.更新账单信息
                            updateParkingOrderWithLock(parkingOrderUpdate);
                            //10.推送新账单通知
                            boolean needPush = false;
                            if (parkingOrderUpdate.getPayable() && parkingOrderUpdate.getNeedPay()) {
                                needPush = true;
                            }
                            if (needPush) {
                                //推送新账单通知消息
                                ParkingOrderPushMessageRequestDto parkingOrderPushMessageRequestDto = new ParkingOrderPushMessageRequestDto();
                                parkingOrderPushMessageRequestDto.setOrderId(parkingOrderResultDto.getId());
                                parkingOrderPushMessageRequestDto.setOrderNo(parkingOrderResultDto.getOrderNo());
                                parkingOrderPushMessageRequestDto.setParkingName(parkingOrderResultDto.getParkingName());
                                parkingOrderPushMessageRequestDto.setPlateColor(parkingOrderResultDto.getPlateColor());
                                parkingOrderPushMessageRequestDto.setPlateNumber(parkingOrderResultDto.getPlateNumber());
                                parkingOrderPushMessageRequestDto.setStartTime(parkingOrderResultDto.getStartTime());
                                messageSendIntegrationService.sendParkingOrderPushMessage(parkingOrderPushMessageRequestDto);
                            }
                        }
                    } else {
                        exceptionType = PassingExceptionTypeEnum.NOT_INSPECT_PASS.getValue();
                        //入车未巡检出车
                        exceptionPassing = true;
                        exceptionMessage = "入车未巡检出车";
                    }

                } else {
                    exceptionType = PassingExceptionTypeEnum.PASSING_ENTER_NOT_FOUNT.getValue();
                    //入场记录不存在
                    exceptionPassing = true;
                    exceptionMessage = "入场记录不存在";
                }
            } else {
                //车辆不在场
                //更新过车记录为异常放行
                //更新过车记录
                PassingRecordUpdateRequestDto passingRecordUpdateRequestDto = new PassingRecordUpdateRequestDto();
                passingRecordUpdateRequestDto.setAbnormalType(PassingExceptionTypeEnum.NOT_EXCEPTION.getValue());
                passingRecordUpdateRequestDto.setPassingNo(passingVehicleRecordEntity.getPassingNo());
                passingRecordUpdateRequestDto.setParkingId(parkingInfo.getId());
                updatePassVehicleRecordLock(passingRecordUpdateRequestDto,
                        getUpdatePassVehicleRecordLockKey(passingVehicleRecordEntity.getPassingNo()));
                exceptionPassing = true;
                exceptionMessage = "车辆不在场";
                exceptionType = PassingExceptionTypeEnum.VEHICLE_NOT_PARKING.getValue();
            }
            if (exceptionPassing) {
                //写入平台异常过车记录
                PassingVehicleRecordCreateRequestDto passingVehicleExceptionRecordAddRequestDto =
                        buildMagneticPassingVehicleRecord(magneticMessagePayLoad, parkingInfo, parkingLotInfo, exceptionType, exceptionMessage);
                passingVehicleExceptionRecordAddRequestDto.setTenantId(parkingInfo.getTenantId());
                passingVehicleExceptionRecordAddRequestDto.setParkingName(parkingInfo.getFullName());
                passingVehicleExceptionRecordAddRequestDto.setParkingCode(parkingInfo.getCode());
                passingVehicleExceptionRecordAddRequestDto.setParkingLotCode(parkingLotInfo.getCode());
                passingVehicleExceptionRecordAddRequestDto.setParkingLotNumber(parkingLotInfo.getNumber());
                passingVehicleExceptionRecordAddRequestDto.setPassingNo(passingVehicleRecordEntity.getPassingNo());
                platformProcessService.savePassingVehicleRecord(passingVehicleExceptionRecordAddRequestDto);
                log.warn("------[异常过车记录]--------[地磁过车数据{}]------", JSON.toJSONString(passingVehicleRecordEntity));
            }
            //发送过车巡检消息
            if (passInspectNotify) {
                PassRecordNotifySendRequestDto passRecordNotifySendRequestDto = modelMapper.map(passingVehicleRecordEntity, PassRecordNotifySendRequestDto.class);
                passRecordNotifySendRequestDto.setParkingLotCode(parkingLotInfo.getCode());
                messageSendIntegrationService.sendPassRecordNotify(passRecordNotifySendRequestDto);
            }
            //出车可用车位数及泊位状态变更
            ParkingLotIncreaseRequestDto parkingLotIncreaseRequestDto = new ParkingLotIncreaseRequestDto();
            parkingLotIncreaseRequestDto.setTenantId(parkingInfo.getTenantId());
            parkingLotIncreaseRequestDto.setParkingId(parkingInfo.getId());
            parkingLotIncreaseRequestDto.setParkingLotId(parkingLotInfo.getId());
            increaseParkingLotWithLock(parkingLotIncreaseRequestDto, getParkingInfoLockKey(parkingInfo.getCode()));

            resultDto.success();
        } catch (Exception e) {
            log.error("处理地磁出车记录失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 海康过车处理状态更新
     *
     * @param passingVehiclePayload passingVehiclePayload
     * @param processStatus         processStatus
     * @param exceptionMessage      exceptionMessage
     */
    private void updatePassingVehicleProcessStatus(PassingVehiclePayload passingVehiclePayload, Integer processStatus, String exceptionMessage) {
        if (PassingVehicleDataSourceEnum.HIKVISION.getValue().equals(passingVehiclePayload.getDataSource())) {
            //海康过车数据标记处理失败
            HikVehicleRecordUpProcessStatusDateRequestDto processStatusDateRequestDto = new HikVehicleRecordUpProcessStatusDateRequestDto();
            processStatusDateRequestDto.setPassingUuid(passingVehiclePayload.getPassingUuid());
            processStatusDateRequestDto.setProcessRemark(exceptionMessage);
            processStatusDateRequestDto.setProcessStatus(processStatus);
            hikvisionParkingService.updateProcessStatus(processStatusDateRequestDto);
        }
    }

    /**
     * 从海康平台入车过车记录生成停车记录
     *
     * @param passingVehiclePayload passingVehiclePayload
     * @param parkingInfo           parkingInfo
     * @param parkingLotInfo        parkingLotInfo
     * @return
     */
    private ParkingRecordAddRequestDto buildParkingRecord(PassingVehiclePayload passingVehiclePayload,
                                                          ParkingInfoResultDto parkingInfo,
                                                          ParkingLotResultDto parkingLotInfo) {

        ParkingRecordAddRequestDto parkingRecord = new ParkingRecordAddRequestDto();
        String parkingRecordNo = String.valueOf(this.idService.genId());
        parkingRecord.setTenantId(parkingInfo.getTenantId());
        parkingRecord.setParkingId(parkingInfo.getId());
        parkingRecord.setParkingCode(parkingInfo.getCode());
        parkingRecord.setParkingName(parkingInfo.getFullName());
        parkingRecord.setRecordNo(parkingRecordNo);
        //入车过车ID
        if (parkingLotInfo != null) {
            parkingRecord.setParkingLotId(parkingLotInfo.getId());
            parkingRecord.setParkingLotCode(parkingLotInfo.getCode());
            parkingRecord.setParkingLotNumber(parkingLotInfo.getNumber());
        }
        parkingRecord.setPlateNumber(passingVehiclePayload.getPlateNumber());
        parkingRecord.setPlateColor(passingVehiclePayload.getPlateColor());
        parkingRecord.setCarType(passingVehiclePayload.getCarType());
        parkingRecord.setStatus(ParkingStatusEnum.PARKING.getValue());
        parkingRecord.setStartTime(passingVehiclePayload.getPassTime());
        //停车类型
        if (null != passingVehiclePayload.getParkingType()) {
            parkingRecord.setParkingType(passingVehiclePayload.getParkingType());
        } else {
            SpecialTypeGetRequestDto specialTypeGetRequestDto = new SpecialTypeGetRequestDto();
            specialTypeGetRequestDto.setParkingId(parkingInfo.getId());
            specialTypeGetRequestDto.setPlateNumber(passingVehiclePayload.getPlateNumber());
            ObjectResultDto<SpecialVehicleTypeResultDto> specialTypeByPlateNo = specialVehicleService.getSpecialTypeByPlateNo(specialTypeGetRequestDto);
            if (specialTypeByPlateNo.isSuccess() && specialTypeByPlateNo.getData() != null) {
                SpecialVehicleTypeResultDto specialTypeByPlateNoData = specialTypeByPlateNo.getData();
                parkingRecord.setParkingType(specialTypeByPlateNoData.getSpecialType());
            }
        }
        //出车时间默认最大
        parkingRecord.setEndTime(DateTimeUtils.getDateMax());
        // 是否预约停车
        AppointOrderValidPlateRequestDto appointOrderValidPlateRequestDto = new AppointOrderValidPlateRequestDto();
        appointOrderValidPlateRequestDto.setParkingId(parkingInfo.getId());
        appointOrderValidPlateRequestDto.setPlateNumber(passingVehiclePayload.getPlateNumber());
        appointOrderValidPlateRequestDto.setDeadlineTime(passingVehiclePayload.getPassTime());
        ObjectResultDto<ParkingAppointmentOrderResultDto> appointmentOrderResult =
                platformAppointOrderService.getValidOrderList(appointOrderValidPlateRequestDto);
        if (appointmentOrderResult.isSuccess() && appointmentOrderResult.getData() != null) {
            ParkingAppointmentOrderResultDto appointmentOrder = appointmentOrderResult.getData();
            parkingRecord.setAppointed(Boolean.TRUE);
            parkingRecord.setAppointOrderNo(appointmentOrder.getOrderNo());
            //更新预约车辆已入场
            AppointOrderEnterRequestDto appointOrderEnterRequestDto = new AppointOrderEnterRequestDto();
            appointOrderEnterRequestDto.setOrderNo(appointmentOrder.getOrderNo());
            appointOrderEnterRequestDto.setEnterTime(passingVehiclePayload.getPassTime());
            ResultDto appointOrderEnterResult = appointmentOrderManagerService.enterAppointOrder(appointOrderEnterRequestDto);
            if (appointOrderEnterResult.isFailed()) {
                log.error("------[更新预约车辆已入场失败]-----------", JSON.toJSONString(appointOrderEnterRequestDto));
            }
            //预约收费规则
            ParkingAppointInfoGetByParkingIdRequestDto parkingAppointInfoGetByParkingIdRequestDto = new ParkingAppointInfoGetByParkingIdRequestDto();
            parkingAppointInfoGetByParkingIdRequestDto.setParkingId(parkingInfo.getId());
            ObjectResultDto<ParkingAppointInfoResultDto> parkingAppointmentInfoByParkingId = platformParkingInfoService.getAppointmentInfoByParkingId(parkingAppointInfoGetByParkingIdRequestDto);
            if (parkingAppointmentInfoByParkingId.isSuccess() && parkingAppointmentInfoByParkingId.getData() != null) {
                ParkingAppointInfoResultDto parkingAppointInfoResultDto = parkingAppointmentInfoByParkingId.getData();
                parkingRecord.setAppointRuleId(parkingAppointInfoResultDto.getId());
            }
            //收费规则
            ParkingChargeInfoGetByParkingIdRequestDto parkingChargeInfoGetByParkingIdRequestDto = new ParkingChargeInfoGetByParkingIdRequestDto();
            parkingChargeInfoGetByParkingIdRequestDto.setParkingId(parkingInfo.getId());
            ObjectResultDto<ParkingChargeInfoResultDto> parkChargeInfoByParkingId = platformParkingInfoService.getParkChargeInfoByParkingId(parkingChargeInfoGetByParkingIdRequestDto);
            if (parkChargeInfoByParkingId.isSuccess() && parkChargeInfoByParkingId.getData() != null) {
                ParkingChargeInfoResultDto parkingChargeInfoResultDto = parkChargeInfoByParkingId.getData();
                parkingRecord.setChargeId(parkingChargeInfoResultDto.getId());
            }
        } else {
            parkingRecord.setAppointed(Boolean.FALSE);
            parkingRecord.setAppointOrderNo("");
        }
        return parkingRecord;
    }

    /**
     * 封装平台异常过车记录
     *
     * @param parkingInfo          parkingInfo
     * @param parkingLotInfo       parkingLotInfo
     * @param exceptionType        exceptionType
     * @param exceptionDescription description
     * @return
     */
    private PassingVehicleRecordCreateRequestDto buildPassingVehicleRecord(
            PassingVehiclePayload passingVehiclePayload,
            ParkingInfoResultDto parkingInfo,
            ParkingLotResultDto parkingLotInfo,
            Integer exceptionType, String exceptionDescription) {

        String exceptionMessage = exceptionDescription;
        PassingVehicleRecordCreateRequestDto passingVehicleRecord = new PassingVehicleRecordCreateRequestDto();
        String passingNo = String.valueOf(this.idService.genId());
        passingVehicleRecord.setThirdPassingId(passingVehiclePayload.getThirdPassingId());
        passingVehicleRecord.setPassingUuid(passingVehiclePayload.getPassingUuid());
        passingVehicleRecord.setPassingNo(passingNo);
        //过车数据源默认海康
        PassingVehicleDataSourceEnum passingVehicleDataSource = PassingVehicleDataSourceEnum.parse(passingVehiclePayload.getDataSource());
        if (passingVehicleDataSource != null) {
            passingVehicleRecord.setDataSource(passingVehicleDataSource.getValue());
            if (passingVehicleDataSource.getValue().equals(PassingVehicleDataSourceEnum.HIKVISION.getValue())) {
                passingVehicleRecord.setDataType(PassDataTypeEnum.VIDEO.getValue());
            } else if (passingVehicleDataSource.getValue().equals(PassingVehicleDataSourceEnum.ANY_PARKING.getValue())) {
                passingVehicleRecord.setDataType(PassDataTypeEnum.CLIENT.getValue());
            }
        } else {
            passingVehicleRecord.setDataSource(PassingVehicleDataSourceEnum.UNKNOWN.getValue());
            if (StringUtils.isEmpty(exceptionMessage)) {
                exceptionMessage = "过车数据源未知(过车数据源:" + passingVehiclePayload.getDataSource() + ")";
            }
        }
        passingVehicleRecord.setProofStatus(Boolean.FALSE);
        if (parkingInfo != null) {
            passingVehicleRecord.setTenantId(parkingInfo.getTenantId());
            passingVehicleRecord.setParkingId(parkingInfo.getId());
            passingVehicleRecord.setParkingCode(parkingInfo.getCode());
            passingVehicleRecord.setParkingName(parkingInfo.getFullName());
        } else {
            passingVehicleRecord.setTenantId(0L);
            passingVehicleRecord.setParkingId(0L);
            passingVehicleRecord.setParkingCode("");
            passingVehicleRecord.setParkingName("");
            if (StringUtils.isEmpty(exceptionMessage)) {
                exceptionMessage = "停车场不存在(停车场编号" + passingVehiclePayload.getParkCode() +
                        "),停车场名称(" + passingVehiclePayload.getParkName() + ")";
            }
        }
        //如果车位不空
        if (parkingLotInfo != null) {
            passingVehicleRecord.setParkingLotId(parkingLotInfo.getId());
            passingVehicleRecord.setParkingLotCode(parkingLotInfo.getCode());
            passingVehicleRecord.setParkingLotNumber(parkingLotInfo.getNumber());
        } else {
            if (StringUtils.isEmpty(exceptionMessage)
                    && (StringUtils.isNotEmpty(passingVehiclePayload.getBerthCode())
                    || StringUtils.isNotEmpty(passingVehiclePayload.getBerthNumber())
                    || StringUtils.isNotEmpty(passingVehiclePayload.getCloudLotCode())
                    || StringUtils.isNotEmpty(passingVehiclePayload.getCloudLotNumber()))) {
                exceptionMessage = "泊位不存在(停车场泊位编号" + passingVehiclePayload.getBerthCode() +
                        "),停车场名称(" + passingVehiclePayload.getParkName() + ")";
            }
        }
        if (PlateNumberUtil.isPlateNumber(passingVehiclePayload.getPlateNumber())) {
            passingVehicleRecord.setPlateNoExist(Boolean.TRUE);
            passingVehicleRecord.setPlateNumber(passingVehiclePayload.getPlateNumber());
            if (null != passingVehiclePayload.getParkingType()) {
                passingVehicleRecord.setParkingType(passingVehiclePayload.getParkingType());
            } else if (parkingInfo != null) {
                SpecialTypeGetRequestDto specialTypeGetRequestDto = new SpecialTypeGetRequestDto();
                specialTypeGetRequestDto.setParkingId(parkingInfo.getId());
                specialTypeGetRequestDto.setPlateNumber(passingVehiclePayload.getPlateNumber());
                ObjectResultDto<SpecialVehicleTypeResultDto> specialTypeByPlateNo = specialVehicleService.getSpecialTypeByPlateNo(specialTypeGetRequestDto);
                if (specialTypeByPlateNo.isSuccess() && specialTypeByPlateNo.getData() != null) {
                    SpecialVehicleTypeResultDto specialTypeByPlateNoData = specialTypeByPlateNo.getData();
                    passingVehicleRecord.setParkingType(specialTypeByPlateNoData.getSpecialType());
                }
            }
        } else {
            exceptionType = PassingExceptionTypeEnum.PLATE_NUMBER_NOT_STANDARD.getValue();
            exceptionMessage = "车牌不符合规范";
            passingVehicleRecord.setPlateNoExist(Boolean.FALSE);
            passingVehicleRecord.setPlateNumber(PLATE_NUMBER_EMPTY);
        }
        //车牌颜色
        passingVehicleRecord.setPlateColor(passingVehiclePayload.getPlateColor());
        //车辆类型
        passingVehicleRecord.setCarType(passingVehiclePayload.getCarType());
        //过车类型
        passingVehicleRecord.setPassingType(passingVehiclePayload.getDirect());
        passingVehicleRecord.setPassTime(passingVehiclePayload.getPassTime());
        if (PassingTypeEnum.ENTRY.getValue().equals(passingVehiclePayload.getDirect())) {
            //入车记录处理
            passingVehicleRecord.setEntryTime(passingVehiclePayload.getPassTime());
        } else if (PassingTypeEnum.EXIT.getValue().equals(passingVehiclePayload.getDirect())) {
            //出车记录处理
            passingVehicleRecord.setExitTime(passingVehiclePayload.getPassTime());
        }
        passingVehicleRecord.setPhotoUploaded(Boolean.FALSE);
        passingVehicleRecord.setPhotoCount(0);
        //异常过车信息
        if (null == exceptionType) {
            passingVehicleRecord.setAbnormalType(PassingExceptionTypeEnum.NOT_EXCEPTION.getValue());
        } else {
            passingVehicleRecord.setAbnormalType(exceptionType);
        }
        if (StringUtils.isNotEmpty(exceptionMessage) && StringUtils.isNotEmpty(passingVehicleRecord.getAbnormalReason())) {
            passingVehicleRecord.setAbnormalReason(passingVehicleRecord.getAbnormalReason() + ";" + exceptionMessage);
        } else {
            passingVehicleRecord.setAbnormalReason(exceptionMessage);
        }
        return passingVehicleRecord;
    }

    /**
     * 根据平台入车记录产生在停记录
     *
     * @param passingVehiclePayload passingVehiclePayload
     * @param parkingInfo           parkingInfo
     * @param parkingLotInfo        parkingLotInfo
     * @return
     */
    private ParkingVehicleRecordSaveRequestDto buildParkingVehicleRecord(
            PassingVehiclePayload passingVehiclePayload, ParkingInfoResultDto parkingInfo, ParkingLotResultDto parkingLotInfo) {

        if (passingVehiclePayload == null || parkingInfo == null) {
            return null;
        }
        ParkingVehicleRecordSaveRequestDto parkingVehicleRecord = new ParkingVehicleRecordSaveRequestDto();
        parkingVehicleRecord.setTenantId(parkingInfo.getTenantId());
        parkingVehicleRecord.setParkingId(parkingInfo.getId());
        parkingVehicleRecord.setParkingCode(parkingInfo.getCode());
        parkingVehicleRecord.setParkingName(parkingInfo.getFullName());
        if (parkingLotInfo != null) {
            parkingVehicleRecord.setParkingLotId(parkingLotInfo.getId());
            parkingVehicleRecord.setParkingLotCode(parkingLotInfo.getCode());
            parkingVehicleRecord.setParkingLotNumber(parkingLotInfo.getNumber());
        }
        parkingVehicleRecord.setPlateNumber(passingVehiclePayload.getPlateNumber());
        parkingVehicleRecord.setPlateColor(passingVehiclePayload.getPlateColor());
        parkingVehicleRecord.setCarType(passingVehiclePayload.getCarType());
        parkingVehicleRecord.setStartTime(passingVehiclePayload.getPassTime());
        return parkingVehicleRecord;
    }

    /**
     * 获取停车场状态的分布式锁key
     *
     * @param parkCode parkingCode
     * @return
     */
    private String getParkingInfoLockKey(String parkCode) {
        return new StringBuilder().append("lock:zoeeasy.cloud").
                append("parking_info").append("_").
                append(parkCode).toString();
    }

    private String getParkingLotAppointLockKey(String parkCode) {
        return new StringBuilder().append("lock:zoeeasy.cloud").
                append("update_appoint_lot").append("_").
                append(parkCode).toString();
    }

    /**
     * @param parkingRecordNo 停车记录流水号
     * @return
     */
    private String getHikParkingRecordLockKey(String parkingRecordNo) {
        return new StringBuilder().append("lock:zoeeasy.cloud").
                append("hikvision_parking_record").append("_").
                append(parkingRecordNo).toString();
    }

    /**
     * @param parkingOrder parkingOrder
     * @return
     */
    private String getParkingOrderLockKey(String parkingOrder) {
        return new StringBuilder().append("lock:zoeeasy.cloud").
                append("parking_order").append("_").
                append(parkingOrder).toString();
    }

    /**
     * 使用分布式锁更新停车记录状态
     *
     * @param updateIntegrationRequestDto updateIntegrationRequestDto
     * @return
     */
    private Integer updateParkingRecordWithLock(ParkingRecordUpdateIntegrationRequestDto updateIntegrationRequestDto, String parkingRecordWithLock) {
        LockInfo lockInfo = new LockInfo();
        lockInfo.setType(LockType.Fair);
        lockInfo.setName(parkingRecordWithLock);
        lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
        lockInfo.setLeaseTime(LockInfo.DEFAULT_LOCK_LEASE_TIME);
        Lock lock = lockFactory.getLock(lockInfo);
        boolean lockAcquired = false;
        try {
            lockAcquired = lock.acquire();
            if (lockAcquired) {
                platformProcessService.updateParkingRecord(updateIntegrationRequestDto);
            }
        } catch (Exception e) {
            log.error("获取分布式锁时抛错：", e);
        } finally {
            if (lockAcquired) {
                lock.release();
            }
        }
        return 0;
    }

    /**
     * 使用分布式锁更新停车记录状态
     *
     * @param parkingOrder parkingOrder
     * @return
     */
    private Integer updateParkingOrderWithLock(ParkingOrderUpdateByPlateNumberRequestDto parkingOrder) {

        LockInfo lockInfo = new LockInfo();
        lockInfo.setType(LockType.Fair);
        lockInfo.setName(getParkingOrderLockKey(parkingOrder.getOrderNo()));
        lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
        lockInfo.setLeaseTime(LockInfo.DEFAULT_LOCK_LEASE_TIME);
        Lock lock = lockFactory.getLock(lockInfo);
        boolean lockAcquired = false;
        try {
            lockAcquired = lock.acquire();
            if (lockAcquired) {
                platformParkingOrderService.updateParkingOrderByPlatNumber(parkingOrder);
            }
        } catch (Exception e) {
            log.error("获取分布式锁时抛错：", e);
        } finally {
            if (lockAcquired) {
                lock.release();
            }
        }
        return 0;
    }

    /**
     * 地磁封装停车记录
     *
     * @param passingVehicleRecordEntity
     * @return
     */
    private ParkingRecordAddRequestDto generateMagneticParkingRecord
    (PassingVehicleRecordCreateRequestDto
             passingVehicleRecordEntity) {
        ParkingRecordAddRequestDto parkingRecord = new ParkingRecordAddRequestDto();
        String parkingRecordNo = String.valueOf(this.idService.genId());
        parkingRecord.setRecordNo(parkingRecordNo);
        parkingRecord.setParkingId(passingVehicleRecordEntity.getParkingId());
        parkingRecord.setTenantId(passingVehicleRecordEntity.getTenantId());
        //入车过车ID
        parkingRecord.setParkingLotId(passingVehicleRecordEntity.getParkingLotId());
        parkingRecord.setStatus(ParkingStatusEnum.PARKING.getValue());
        parkingRecord.setStartTime(passingVehicleRecordEntity.getPassTime());
        parkingRecord.setPeriodLength(0);
        parkingRecord.setPayableAmount(0);
        //收费规则
        ParkingChargeInfoGetByParkingIdRequestDto parkingChargeInfoGetByParkingIdRequestDto = new ParkingChargeInfoGetByParkingIdRequestDto();
        parkingChargeInfoGetByParkingIdRequestDto.setParkingId(passingVehicleRecordEntity.getParkingId());
        ObjectResultDto<ParkingChargeInfoResultDto> parkChargeInfoByParkingId =
                platformParkingInfoService.getParkChargeInfoByParkingId(parkingChargeInfoGetByParkingIdRequestDto);
        if (parkChargeInfoByParkingId.isSuccess() && parkChargeInfoByParkingId.getData() != null) {
            ParkingChargeInfoResultDto parkingChargeInfoResultDto = parkChargeInfoByParkingId.getData();
            parkingRecord.setChargeId(parkingChargeInfoResultDto.getId());
        }
        //出车时间默认最大
        parkingRecord.setEndTime(DateTimeUtils.getDateMax());
        parkingRecord.setPlateNumber(PLATE_NUMBER_EMPTY);
        return parkingRecord;
    }

    /**
     * 地磁生成在场车辆
     *
     * @param
     * @return
     */
    private ParkingVehicleRecordSaveRequestDto sealMagneticParkingVehicleRecord(PassingVehicleRecordCreateRequestDto passingVehicleRecordEntity) {
        if (passingVehicleRecordEntity == null) {
            return null;
        }
        ParkingVehicleRecordSaveRequestDto vehicleRecordEntity = new ParkingVehicleRecordSaveRequestDto();
        vehicleRecordEntity.setTenantId(passingVehicleRecordEntity.getTenantId());
        vehicleRecordEntity.setParkingId(passingVehicleRecordEntity.getParkingId());
        vehicleRecordEntity.setParkingLotId(passingVehicleRecordEntity.getParkingLotId());
        vehicleRecordEntity.setStartTime(passingVehicleRecordEntity.getEntryTime());
        vehicleRecordEntity.setPlateNumber(PLATE_NUMBER_EMPTY);
        return vehicleRecordEntity;
    }

    /**
     * 地磁封装异常过车记录
     *
     * @param exceptionMessage
     * @return
     */
    private PassingVehicleRecordCreateRequestDto buildMagneticPassingVehicleRecord(MagneticPassingPayload magneticPassingPayload,
                                                                                   ParkingInfoResultDto parkingInfo,
                                                                                   ParkingLotResultDto parkingLotInfo,
                                                                                   Integer exceptionPassing,
                                                                                   String exceptionMessage) {
        PassingVehicleRecordCreateRequestDto passingVehicleRecord = new PassingVehicleRecordCreateRequestDto();

        //停车记录流水号
        String passingRecordNo = String.valueOf(this.idService.genId());
        //平台过车唯一编号
        String passingUuid = UUIDTool.getUUID();
        if (parkingInfo != null) {
            passingVehicleRecord.setTenantId(parkingInfo.getTenantId());
            passingVehicleRecord.setParkingCode(parkingInfo.getCode());
            passingVehicleRecord.setParkingId(magneticPassingPayload.getParkingId());
            passingVehicleRecord.setParkingName(parkingInfo.getFullName());
        }
        if (parkingLotInfo != null) {
            passingVehicleRecord.setParkingLotId(magneticPassingPayload.getParkingLotId());
        }
        passingVehicleRecord.setPassingNo(passingRecordNo);
        passingVehicleRecord.setPassingUuid(passingUuid);
        passingVehicleRecord.setPassingType(magneticPassingPayload.getPassingType());
        passingVehicleRecord.setPassTime(magneticPassingPayload.getPassTime());
        passingVehicleRecord.setPlateNoExist(Boolean.FALSE);
        if (PassingTypeEnum.ENTRY.getValue().equals(magneticPassingPayload.getPassingType())) {
            //入车
            passingVehicleRecord.setEntryTime(magneticPassingPayload.getPassTime());
        } else if (PassingTypeEnum.EXIT.getValue().equals(magneticPassingPayload.getPassingType())) {
            //出车
            passingVehicleRecord.setExitTime(magneticPassingPayload.getPassTime());
        }
        passingVehicleRecord.setDataSource(PassingVehicleDataSourceEnum.ANY_PARKING.getValue());
        passingVehicleRecord.setAbnormalType(exceptionPassing);
        passingVehicleRecord.setAbnormalReason(exceptionMessage);
        passingVehicleRecord.setPlateNumber(PLATE_NUMBER_EMPTY);
        passingVehicleRecord.setPlateNoExist(Boolean.FALSE);
        return passingVehicleRecord;
    }

    /**
     * 地磁获取停车记录锁
     *
     * @param parkingRecordId parkingRecord
     * @return
     */
    private String getMagneticParkingRecordLockKey(Long parkingRecordId) {
        return new StringBuilder().append("lock:zoeeasy.integration.magnetic").
                append("parking_record").append("_").
                append(UUIDTool.getUUID()).append("_").
                append(parkingRecordId).append("_").toString();
    }

    /**
     * 获取过车记录锁
     *
     * @param passNo
     * @return
     */
    private String getUpdatePassVehicleRecordLockKey(String passNo) {
        return new StringBuilder().append("lock:zoeeasy.inspect").
                append("update.pass_vehicle_record").append("_").
                append(UUIDTool.getUUID()).append("_").
                append(passNo).append("_").toString();
    }

    /**
     * 从停车记录生成停车账单
     *
     * @param parkingRecord  parkingRecord
     * @param parkingInfo    parkingInfo
     * @param parkingLotInfo parkingLotInfo
     * @return
     */
    private ParkingOrderCreateRequestDto generateParkingOrder(ParkingRecordAddRequestDto parkingRecord,
                                                              ParkingInfoResultDto parkingInfo,
                                                              ParkingLotResultDto parkingLotInfo) throws BusinessException {

        ParkingOrderCreateRequestDto parkingOrder = new ParkingOrderCreateRequestDto();
        if (parkingInfo == null) {
            throw new BusinessException("停车场不存在");
        }
        parkingOrder.setTenantId(parkingInfo.getTenantId());
        parkingOrder.setParkingId(parkingInfo.getId());
        parkingOrder.setParkingName(parkingInfo.getFullName());
        parkingOrder.setParkingCode(parkingInfo.getCode());
        String parkingOrderNo = String.valueOf(this.idService.genId());
        parkingOrder.setRecordNo(parkingRecord.getRecordNo());
        parkingOrder.setOrderNo(parkingOrderNo);
        if (parkingLotInfo != null) {
            parkingOrder.setParkingLotId(parkingLotInfo.getId());
            parkingOrder.setParkingLotNumber(parkingLotInfo.getNumber());
        }
        parkingOrder.setPlateNumber(parkingRecord.getPlateNumber());
        parkingOrder.setPlateColor(parkingRecord.getPlateColor());
        parkingOrder.setCarStyle(parkingRecord.getCarType());
        parkingOrder.setStartTime(parkingRecord.getStartTime());
        //最大停车日期
        parkingOrder.setEndTime(DateTimeUtils.getDateMax());
        parkingOrder.setStatus(ParkingStatusEnum.PARKING.getValue());
        parkingOrder.setParkingLength(0L);
        parkingOrder.setPayStatus(PayStatusEnum.CREATED.getValue());
        parkingOrder.setPayTime(DateTimeUtils.getDateMax());
        //默认未结算
        parkingOrder.setSettle(Boolean.FALSE);
        parkingOrder.setSettleTime(DateTimeUtils.getDateMax());
        parkingOrder.setNeedPay(Boolean.FALSE);
        //先支付后离场允许支付
        if (parkingInfo.getChargeMode().compareTo(ChargeModeEnum.AFTER.getValue()) == 0) {
            parkingOrder.setPayable(Boolean.FALSE);
        } else if (parkingInfo.getChargeMode().compareTo(ChargeModeEnum.BEFORE.getValue()) == 0) {
            parkingOrder.setPayable(Boolean.TRUE);
        }
        parkingOrder.setChargeMode(parkingInfo.getChargeMode());
        //获取入场时停车场收费信息
        ParkingChargeInfoGetByParkingIdRequestDto parkingChargeInfoGetByParkingIdRequestDto = new ParkingChargeInfoGetByParkingIdRequestDto();
        parkingChargeInfoGetByParkingIdRequestDto.setParkingId(parkingInfo.getId());
        ObjectResultDto<ParkingChargeInfoResultDto> parkChargeInfoByParkingId = platformParkingInfoService.getParkChargeInfoByParkingId(parkingChargeInfoGetByParkingIdRequestDto);
        if (parkChargeInfoByParkingId.isSuccess() && parkChargeInfoByParkingId.getData() != null) {
            ParkingChargeInfoResultDto parkingChargeInfoResultDto = parkChargeInfoByParkingId.getData();
            parkingOrder.setChargeInfoId(parkingChargeInfoResultDto.getId());
        }
        //预约停车
        parkingOrder.setAppointed(parkingRecord.getAppointed());
        parkingOrder.setAppointOrderNo(parkingRecord.getAppointOrderNo());
        //是否限免时段停车
        ParkingCurrentChargeInfoRequestDto parkingChargeInfoGetRequestDto = new ParkingCurrentChargeInfoRequestDto();
        parkingChargeInfoGetRequestDto.setParkingId(parkingInfo.getId());
        parkingChargeInfoGetRequestDto.setBaseTime(parkingRecord.getStartTime());
        parkingChargeInfoGetRequestDto.setTenantId(parkingInfo.getTenantId());
        ObjectResultDto<ParkingCurrentChargeInfoResultDto>
                currentParkingChargeRule = parkChargeRuleIntegrationService.getParkingChargeRuleCurrentInfo(parkingChargeInfoGetRequestDto);
        if (currentParkingChargeRule.isSuccess() && currentParkingChargeRule.getData() != null) {
            ParkingCurrentChargeInfoResultDto parkingChargeInfoResultDto = currentParkingChargeRule.getData();
            parkingOrder.setLimitFree(parkingChargeInfoResultDto.getNowFree());
        } else {
            parkingOrder.setLimitFree(Boolean.FALSE);
        }
        parkingOrder.setFreeLength(0L);
        parkingOrder.setChargeLength(0L);
        return parkingOrder;
    }

    /**
     * 停车账单费用结算
     *
     * @param parkingOrder   parkingRecord
     * @param parkingInfo    parkingInfo
     * @param parkingLotInfo parkingLotInfo
     * @return
     */
    private void settleParkingOrder(ParkingOrderResultDto parkingOrder,
                                    ParkingInfoResultDto parkingInfo,
                                    ParkingLotResultDto parkingLotInfo,
                                    Date passTime,
                                    ParkingOrderUpdateByPlateNumberRequestDto parkingOrderUpdate) {

        ParkingOrderSettleResultDto settleResultDto = new ParkingOrderSettleResultDto();
        //如果未结算计算结算费用
        //如果尚未离场,暂不计算结算金额
        //默认缴费后离场
        parkingOrderUpdate.setPayable(Boolean.TRUE);
        parkingOrderUpdate.setChargeMode(parkingInfo.getChargeMode());
        parkingOrderUpdate.setSettle(Boolean.FALSE);
        //已经离场
        //计算金额
        ParkingAmountCalculateRequestDto calculateAmountRequestDto = new ParkingAmountCalculateRequestDto();
        calculateAmountRequestDto.setParkingId(parkingOrder.getParkingId());
        calculateAmountRequestDto.setStartTime(parkingOrder.getStartTime());
        calculateAmountRequestDto.setEndTime(passTime);
        calculateAmountRequestDto.setCarStyle(parkingOrder.getCarStyle());
        calculateAmountRequestDto.setPlateNumber(parkingOrder.getPlateNumber());
        ObjectResultDto<CalculateAmountResultDto> calculateAmount = calculateIntegrationService.calculateAmount(calculateAmountRequestDto);
        if (null == calculateAmount.getData() || calculateAmount.isFailed()) {
            log.error("停车订单{}结算金额计算失败:{}", parkingOrder.getOrderNo(), calculateAmount.getMessage());
        }
        Date now = DateUtils.now();
        CalculateAmountResultDto calculateAmountData = calculateAmount.getData();
        parkingOrderUpdate.setSettle(Boolean.TRUE);
        parkingOrderUpdate.setSettleTime(now);
        Integer amount = calculateAmountData.getAmount();
        //目前结算金额同应付金额
        parkingOrderUpdate.setSettleAmount(amount);
        parkingOrderUpdate.setPayableAmount(amount);
        //收费时长
        parkingOrderUpdate.setChargeLength(calculateAmountData.getChargeDuration());
        //免费时长
        parkingOrderUpdate.setFreeLength(calculateAmountData.getFreeDuration());
        //停车时长
        parkingOrderUpdate.setParkingLength(DateTimeUtils.getSecondDiff(parkingOrder.getStartTime(), passTime));
        //根据停车场id获取停车场收费信息
        ParkingChargeInfoGetByParkingIdRequestDto parkingChargeInfoGetByParkingIdRequestDto = new ParkingChargeInfoGetByParkingIdRequestDto();
        parkingChargeInfoGetByParkingIdRequestDto.setParkingId(parkingInfo.getId());
        ObjectResultDto<ParkingChargeInfoResultDto> parkChargeInfoByParkingId = platformParkingInfoService.getParkChargeInfoByParkingId(parkingChargeInfoGetByParkingIdRequestDto);
        if (parkChargeInfoByParkingId.isSuccess() && parkChargeInfoByParkingId.getData() != null) {
            ParkingChargeInfoResultDto parkingChargeInfoResultDto = parkChargeInfoByParkingId.getData();
            //收费详情
            parkingOrderUpdate.setChargeInfoId(parkingChargeInfoResultDto.getId());
        }
        //订单金额为0，无需支付
        //订单结算记录
        if (calculateAmountData.getAmount().compareTo(0) <= 0) {
            parkingOrderUpdate.setNeedPay(Boolean.FALSE);
            parkingOrderUpdate.setFreePayReason("限时免费");
            //自动支付完成
            if (!PayStatusEnum.PAY_SUCCESS.getValue().equals(parkingOrder.getPayStatus())) {
                parkingOrderUpdate.setPayable(Boolean.FALSE);
                parkingOrderUpdate.setActualPayAmount(0);
                parkingOrderUpdate.setPayStatus(PayStatusEnum.PAY_SUCCESS.getValue());
                parkingOrderUpdate.setPayTime(now);
            }
        } else {
            //订单金额大于0
            parkingOrderUpdate.setNeedPay(Boolean.TRUE);
            parkingOrderUpdate.setFreePayReason("");
            if (!parkingOrder.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
                parkingOrderUpdate.setPayable(Boolean.TRUE);
            }
        }
    }

    /**
     * 更新过车记录分布式锁
     *
     * @param passingRecordUpdateRequestDto passingRecordUpdateRequestDto
     * @param passRecordLockKey             passRecordLockKey
     * @return Integer
     */
    private Integer updatePassVehicleRecordLock(PassingRecordUpdateRequestDto passingRecordUpdateRequestDto,
                                                String passRecordLockKey) {
        LockInfo lockInfo = new LockInfo();
        lockInfo.setType(LockType.Fair);
        lockInfo.setName(passRecordLockKey);
        lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
        lockInfo.setLeaseTime(LockInfo.DEFAULT_LOCK_LEASE_TIME);
        Lock lock = lockFactory.getLock(lockInfo);
        boolean lockAcquired = false;
        try {
            lockAcquired = lock.acquire();
            if (lockAcquired) {
                platformProcessService.updatePassVehicleRecord(passingRecordUpdateRequestDto);
            }
        } catch (Exception e) {
            log.error("获取分布式锁时抛错：", e);
        } finally {
            if (lockAcquired) {
                lock.release();
            }
        }
        return 0;
    }

    /**
     * 分布式锁递减停车场可用泊位数量并占用泊位
     *
     * @param parkingCurrentInfoLockKey    parkingCurrentInfoLockKey
     * @param parkingLotDecreaseRequestDto parkingLotDecreaseRequestDto
     * @return
     */
    private Integer decreaseParkingLotWithLock(ParkingLotDecreaseRequestDto parkingLotDecreaseRequestDto,
                                               String parkingCurrentInfoLockKey) {
        LockInfo lockInfo = new LockInfo();
        lockInfo.setType(LockType.Fair);
        lockInfo.setName(parkingCurrentInfoLockKey);
        lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
        lockInfo.setLeaseTime(LockInfo.DEFAULT_LOCK_LEASE_TIME);
        Lock lock = lockFactory.getLock(lockInfo);
        boolean lockAcquired = false;
        try {
            lockAcquired = lock.acquire();
            if (lockAcquired) {
                platformParkingInfoService.decreaseParkingLotAvailable(parkingLotDecreaseRequestDto);
            }
        } catch (Exception e) {
            log.error("获取分布式锁时抛错：", e);
        } finally {
            if (lockAcquired) {
                lock.release();
            }
        }
        return 0;
    }

    /**
     * 分布式锁更细停车场可预约车位
     *
     * @param requestDto                ParkingLotAppointUpdateRequestDto
     * @param parkingCurrentInfoLockKey parkingCurrentInfoLockKey
     * @return Integer
     */
    private Integer updateParkingLotAppointWithLock(ParkingLotAppointUpdateRequestDto requestDto,
                                                    String parkingCurrentInfoLockKey) {
        LockInfo lockInfo = new LockInfo();
        lockInfo.setType(LockType.Fair);
        lockInfo.setName(parkingCurrentInfoLockKey);
        lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
        lockInfo.setLeaseTime(LockInfo.DEFAULT_LOCK_LEASE_TIME);
        Lock lock = lockFactory.getLock(lockInfo);
        boolean lockAcquired = false;
        try {
            lockAcquired = lock.acquire();
            if (lockAcquired) {
                platformParkingInfoService.updateLotAppointAvailable(requestDto);
            }
        } catch (Exception e) {
            log.error("获取分布式锁时抛错：", e);
        } finally {
            if (lockAcquired) {
                lock.release();
            }
        }
        return 0;
    }

    /**
     * 分布式锁递增停车场可用泊位数量并释放泊位
     *
     * @param parkingCurrentInfoLockKey parkingCurrentInfoLockKey
     * @param requestDto                ParkingLotIncreaseRequestDto
     * @return Integer
     */
    private Integer increaseParkingLotWithLock(ParkingLotIncreaseRequestDto requestDto,
                                               String parkingCurrentInfoLockKey) {
        LockInfo lockInfo = new LockInfo();
        lockInfo.setType(LockType.Fair);
        lockInfo.setName(parkingCurrentInfoLockKey);
        lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
        lockInfo.setLeaseTime(LockInfo.DEFAULT_LOCK_LEASE_TIME);
        Lock lock = lockFactory.getLock(lockInfo);
        boolean lockAcquired = false;
        try {
            lockAcquired = lock.acquire();
            if (lockAcquired) {
                platformParkingInfoService.increaseParkingLotAvailable(requestDto);
            }
        } catch (Exception e) {
            log.error("获取分布式锁时抛错：", e);
        } finally {
            if (lockAcquired) {
                lock.release();
            }
        }
        return 0;
    }


    @Override
    public ResultDto anyRecordImageProcess(AnyPassRecordImagePayload anyPassRecordImagePayload, String retryTopic, ParkingImageTypeEnum parkingImageTypeEnum) throws BusinessException {
        ResultDto resultDto = new ResultDto();
        try {
            //根据三方记录编号获取过车记录
            PassRecordGetByThirdNoRequestDto passRecordGetByThirdNoRequestDto = new PassRecordGetByThirdNoRequestDto();
            passRecordGetByThirdNoRequestDto.setThirdPassingId(anyPassRecordImagePayload.getPassingCode());
            ObjectResultDto<PassingVehicleRecordResultDto> passRecordByThirdNo = platformProcessService.getPassRecordByThirdNoAndCloudParkingCode(passRecordGetByThirdNoRequestDto);
            if (passRecordByThirdNo.isSuccess() && passRecordByThirdNo.getData() != null) {
                PassingVehicleRecordResultDto data = passRecordByThirdNo.getData();
                if (StringUtils.isNotEmpty(anyPassRecordImagePayload.getFullImage())) {
                    ParkingRecordImageSaveRequestDto parkingRecordImageSaveRequestDto = new ParkingRecordImageSaveRequestDto();
                    parkingRecordImageSaveRequestDto.setParkingId(data.getParkingId());
                    parkingRecordImageSaveRequestDto.setTenantId(data.getTenantId());
                    parkingRecordImageSaveRequestDto.setBizId(data.getId());
                    parkingRecordImageSaveRequestDto.setBizNo(data.getPassingNo());
                    parkingRecordImageSaveRequestDto.setBizType(parkingImageTypeEnum.getValue());
                    parkingRecordImageSaveRequestDto.setUrl(anyPassRecordImagePayload.getFullImage());
                    parkingRecordImageSaveRequestDto.setCreationTime(DateUtils.now());
                    platformProcessService.saveParkingRecordImage(parkingRecordImageSaveRequestDto);
                }
                if (StringUtils.isNotEmpty(anyPassRecordImagePayload.getPlateImage())) {
                    ParkingRecordImageSaveRequestDto parkingRecordImageSaveRequestDto = new ParkingRecordImageSaveRequestDto();
                    parkingRecordImageSaveRequestDto.setParkingId(data.getParkingId());
                    parkingRecordImageSaveRequestDto.setTenantId(data.getTenantId());
                    parkingRecordImageSaveRequestDto.setBizId(data.getId());
                    parkingRecordImageSaveRequestDto.setBizNo(data.getPassingNo());
                    parkingRecordImageSaveRequestDto.setBizType(parkingImageTypeEnum.getValue());
                    parkingRecordImageSaveRequestDto.setUrl(anyPassRecordImagePayload.getPlateImage());
                    parkingRecordImageSaveRequestDto.setCreationTime(DateUtils.now());
                    platformProcessService.saveParkingRecordImage(parkingRecordImageSaveRequestDto);
                }
            } else {
                //重新放入队列
                RocketMessage<AnyPassRecordImagePayload> imageMessage = new RocketMessage<>();
                imageMessage.setDestination(retryTopic);
                imageMessage.setSender(MessageQueueDefinitions.Sender.INTEGRATION);
                imageMessage.setMessageId(StringUtils.getUUID());
                imageMessage.setHashKey(anyPassRecordImagePayload.getPassingCode());
                imageMessage.setPayload(anyPassRecordImagePayload);
                messageSendService.sendAndSaveOrderlySync(imageMessage);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("任意停车平台图片消息处理失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }


    @Override
    public ResultDto anyParkingRecordImageProcess(AnyPassRecordImagePayload anyPassRecordImagePayload) throws BusinessException {
        //根据三方记录编号获取过车记录
        PassRecordGetByThirdNoRequestDto passRecordGetByThirdNoRequestDto = new PassRecordGetByThirdNoRequestDto();
        passRecordGetByThirdNoRequestDto.setThirdPassingId(anyPassRecordImagePayload.getPassingCode());
        ResultDto resultDto = anyRecordImageProcess(anyPassRecordImagePayload, MessageQueueDefinitions.Topic.ANY_PARKING_PASSING_RECORD_IMAGE, ParkingImageTypeEnum.PASSING);
        ObjectResultDto<PassingVehicleRecordResultDto> passRecordByThirdNo = platformProcessService.getPassRecordByThirdNoAndCloudParkingCode(passRecordGetByThirdNoRequestDto);
        if (passRecordByThirdNo.isSuccess() && passRecordByThirdNo.getData() != null) {
            PassingVehicleRecordResultDto passingVehicleRecordResultDto = passRecordByThirdNo.getData();
            // passRecordByThirdNo
            return resultDto;
        }
        //  platformProcessService.updateParkingRecord();
        return resultDto;
    }


}
