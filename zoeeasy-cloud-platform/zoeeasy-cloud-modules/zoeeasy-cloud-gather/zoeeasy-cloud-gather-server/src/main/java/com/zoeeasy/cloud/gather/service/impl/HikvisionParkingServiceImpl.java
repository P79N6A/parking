package com.zoeeasy.cloud.gather.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.common.utils.ThreadUtils;
import com.scapegoat.infrastructure.common.utils.TimeUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.message.RocketMessage;
import com.zoeeasy.cloud.core.cst.MessageQueueDefinitions;
import com.zoeeasy.cloud.core.enums.*;
import com.zoeeasy.cloud.core.utils.EnumConverter;
import com.zoeeasy.cloud.gather.domain.HikvisionVehicleLogEntity;
import com.zoeeasy.cloud.gather.domain.HikvisionVehicleRecordEntity;
import com.zoeeasy.cloud.gather.enums.HikvisionSyncTypeEnum;
import com.zoeeasy.cloud.gather.enums.MessageLogStatusEnum;
import com.zoeeasy.cloud.gather.enums.VehicleProcessStatusEnum;
import com.zoeeasy.cloud.gather.hikvision.HikvisionParkingService;
import com.zoeeasy.cloud.gather.hikvision.dto.request.*;
import com.zoeeasy.cloud.gather.hikvision.dto.result.HikvisionVehicleRecordResultDto;
import com.zoeeasy.cloud.gather.hikvision.dto.result.PassingImageFetchResultDto;
import com.zoeeasy.cloud.gather.hikvision.dto.result.PassingVehicleCollateTimeResultDto;
import com.zoeeasy.cloud.gather.hikvision.dto.result.PassingVehicleSyncTimeResultDto;
import com.zoeeasy.cloud.gather.service.HikvisionVehicleLogCrudService;
import com.zoeeasy.cloud.gather.service.HikvisionVehicleRecordCrudService;
import com.zoeeasy.cloud.hikvision.bean.VehicleRecordBean;
import com.zoeeasy.cloud.hikvision.config.HikvisionConfiguration;
import com.zoeeasy.cloud.hikvision.constant.HikvisionConstants;
import com.zoeeasy.cloud.hikvision.dto.request.PassPicByUuidParams;
import com.zoeeasy.cloud.hikvision.dto.request.VehicleRecordsParams;
import com.zoeeasy.cloud.hikvision.dto.result.PassPicByUuidResult;
import com.zoeeasy.cloud.hikvision.dto.result.VehicleRecordsResult;
import com.zoeeasy.cloud.hikvision.enums.ResultCodeEnum;
import com.zoeeasy.cloud.hikvision.service.HikvisionService;
import com.zoeeasy.cloud.message.MessageSendService;
import com.zoeeasy.cloud.message.payload.PassingVehiclePayload;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 海康平台数据相关服务
 *
 * @author walkman
 */
@Service("hikvisionParkingService")
@Slf4j
public class HikvisionParkingServiceImpl implements HikvisionParkingService {

    private static final Integer DEFAULT_HIK_VISION_VEHICLE_RECORD_PAGE_SIZE = 500;

    @Autowired
    private HikvisionConfiguration hikvisionProperty;

    @Autowired
    private HikvisionVehicleRecordCrudService hikvisionVehicleRecordCrudService;

    @Autowired
    private HikvisionVehicleLogCrudService hikvisionVehicleLogCrudService;

    @Autowired
    private HikvisionService hikvisionService;

    @Autowired
    private MessageSendService messageSendService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 获取最后一次海康接口同步结束时间
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<PassingVehicleSyncTimeResultDto> getLastSyncEndTime(PassingVehicleSyncTimeGetRequestDto requestDto) {
        ObjectResultDto<PassingVehicleSyncTimeResultDto> resultDto = new ObjectResultDto<>();
        try {
            Date lastEndTime = hikvisionVehicleLogCrudService.getLastSyncEndTime();
            PassingVehicleSyncTimeResultDto passingVehicleSyncTimeResultDto = new PassingVehicleSyncTimeResultDto();
            if (lastEndTime != null) {
                passingVehicleSyncTimeResultDto.setSyncEndTime(lastEndTime);
            } else {
                Calendar calendar = Calendar.getInstance();
                calendar.set(2018, Calendar.APRIL, 1, 0, 0, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                passingVehicleSyncTimeResultDto.setSyncEndTime(calendar.getTime());
            }
            resultDto.setData(passingVehicleSyncTimeResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("查询最后同步结束时间失败,异常信息:{}", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取最后一次海康接口校对结束时间
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ObjectResultDto<PassingVehicleCollateTimeResultDto> getLastCollateEndTime(PassingVehicleCollateTimeGetRequestDto requestDto) {
        ObjectResultDto<PassingVehicleCollateTimeResultDto> resultDto = new ObjectResultDto<>();
        try {
            Date lastEndTime = hikvisionVehicleLogCrudService.selectLastCollateTime();
            PassingVehicleCollateTimeResultDto passingVehicleCollateTimeResultDto = new PassingVehicleCollateTimeResultDto();
            if (lastEndTime != null) {
                passingVehicleCollateTimeResultDto.setCollateEndTime(lastEndTime);
            } else {
                Calendar calendar = Calendar.getInstance();
                calendar.set(2018, Calendar.DECEMBER, 20, 0, 0, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                passingVehicleCollateTimeResultDto.setCollateEndTime(calendar.getTime());
            }
            resultDto.setData(passingVehicleCollateTimeResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("查询最后校对结束时间失败,异常信息:{}", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 同步海康平台过车数据
     *
     * @param requestDto requestDto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto syncHikvisionVehicleRecord(HikVehicleRecordSyncRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            List<VehicleRecordBean> vehicleRecordsBeanList = new ArrayList<>();
            VehicleRecordsParams vehicleRecordsParams = new VehicleRecordsParams();
            vehicleRecordsParams.setPageNo(1);
            vehicleRecordsParams.setPageSize(DEFAULT_HIK_VISION_VEHICLE_RECORD_PAGE_SIZE);
            if (requestDto.getStartTime() != null) {
                vehicleRecordsParams.setStartTime(requestDto.getStartTime().getTime());
            }
            if (requestDto.getEndTime() != null) {
                vehicleRecordsParams.setEndTime(requestDto.getEndTime().getTime());
            }
            Date requestTime = new Date();

            boolean allsuccess = false;
            VehicleRecordsResult vehicleRecordsResult = hikvisionService.getVehicleRecords(vehicleRecordsParams);

            log.debug("海康过车记录请求参数:{},请求结果：{}", JSON.toJSONString(vehicleRecordsParams), JSON.toJSONString(vehicleRecordsResult));

            //调用海康过车记录接口,添加海康接口调用记录
            HikvisionVehicleLogEntity hikvisionVehicleLog = new HikvisionVehicleLogEntity();

            String ip = hikvisionProperty.getHikvisionIp();
            String url = HikvisionConstants.GET_VEHICLE_RECORDS_URL;

            hikvisionVehicleLog.setIp(ip);
            hikvisionVehicleLog.setUrl(url);
            hikvisionVehicleLog.setParameter(JSON.toJSONString(vehicleRecordsParams));
            hikvisionVehicleLog.setRequestTime(requestTime);

            if (vehicleRecordsResult != null) {
                Map<String, String> result = new HashMap<>(16);
                result.put("errorCode", vehicleRecordsResult.getErrorCode().toString());
                result.put("errorMessage", vehicleRecordsResult.getErrorMessage());
                if (vehicleRecordsResult.getData() != null) {
                    result.put("total", vehicleRecordsResult.getData().getTotal().toString());
                    result.put("pageNo", vehicleRecordsResult.getData().getPageNo().toString());
                    result.put("pageSize", vehicleRecordsResult.getData().getPageSize().toString());
                }
                hikvisionVehicleLog.setResult(JSON.toJSONString(result));
            }

            hikvisionVehicleLog.setStartTime(requestDto.getStartTime());
            hikvisionVehicleLog.setEndTime(requestDto.getEndTime());

            if (vehicleRecordsResult != null
                    && ResultCodeEnum.SUCCESS.getValue().equals(vehicleRecordsResult.getErrorCode())) {

                //请求成功
                allsuccess = true;

                if (vehicleRecordsResult.getData() != null
                        && !CollectionUtil.isEmpty(vehicleRecordsResult.getData().getList())) {

                    vehicleRecordsBeanList.addAll(vehicleRecordsResult.getData().getList());
                    //计算总页数，继续请求
                    Integer total = vehicleRecordsResult.getData().getTotal();
                    Integer pageSize = vehicleRecordsResult.getData().getPageSize();
                    Integer pageCount = pageSize == 0 ? 1 : (int) Math.ceil((double) total / (double) pageSize);
                    //多余一页，从第二页开始请求
                    if (pageCount > 1) {
                        Integer pageNo = 2;
                        do {

                            vehicleRecordsParams = new VehicleRecordsParams();
                            vehicleRecordsParams.setPageNo(pageNo);
                            vehicleRecordsParams.setPageSize(pageSize);
                            if (requestDto.getStartTime() != null) {
                                vehicleRecordsParams.setStartTime(requestDto.getStartTime().getTime());
                            }
                            if (requestDto.getEndTime() != null) {
                                vehicleRecordsParams.setEndTime(requestDto.getEndTime().getTime());
                            }
                            //延迟1秒钟
                            ThreadUtils.sleep(TimeUtils.MILLIS_OF_SECOND);

                            vehicleRecordsResult = hikvisionService.getVehicleRecords(vehicleRecordsParams);
                            log.debug("海康过车记录请求参数:{},请求结果：{}", JSON.toJSONString(vehicleRecordsParams), JSON.toJSONString(vehicleRecordsResult));

                            if (vehicleRecordsResult != null
                                    && ResultCodeEnum.SUCCESS.getValue().equals(vehicleRecordsResult.getErrorCode())) {

                                if (vehicleRecordsResult.getData() != null &&
                                        !CollectionUtil.isEmpty(vehicleRecordsResult.getData().getList())) {
                                    vehicleRecordsBeanList.addAll(vehicleRecordsResult.getData().getList());
                                }
                            } else {
                                allsuccess = false;
                                //请求失败
                                break;
                            }
                            pageNo = pageNo + 1;
                        }
                        while (pageNo <= pageCount);
                    }
                }
            }
            if (allsuccess) {
                if (!vehicleRecordsBeanList.isEmpty()) {

                    //由于海康返回的过车数据按时间逆序排列,因此首先需要再次排序
                    List<VehicleRecordBean> sortedVehicleRecordsBeanList = vehicleRecordsBeanList.stream().
                            sorted(Comparator.comparing(VehicleRecordBean::getPassTime)).collect(Collectors.toList());

                    //保存并发送海康平台过车数据
                    resultDto = saveAndSendVehicleRecordList(sortedVehicleRecordsBeanList);
                }
            }
            if (allsuccess) {
                hikvisionVehicleLog.setStatus(MessageLogStatusEnum.SUCCESS.getValue());
            } else {
                hikvisionVehicleLog.setStatus(MessageLogStatusEnum.FAILED.getValue());
            }
            Date responseTime = new Date();
            hikvisionVehicleLog.setResponseTime(responseTime);
            HikvisionSyncTypeEnum logType = HikvisionSyncTypeEnum.parse(requestDto.getSyncType());
            if (logType != null) {
                hikvisionVehicleLog.setLogType(logType.getValue());
            } else {
                hikvisionVehicleLog.setLogType(HikvisionSyncTypeEnum.SYNC.getValue());
            }
            //海康接口调用日志
            hikvisionVehicleLogCrudService.insert(hikvisionVehicleLog);

            resultDto.success();
        } catch (Exception e) {
            log.error("同步海康云平台过车数据失败,异常信息:{}", e.getMessage());
        }
        return resultDto;
    }

    /**
     * 封装海康平台过车记录
     *
     * @param vehicleRecordsBean vehicleRecordsBean
     * @return
     */
    private HikvisionVehicleRecordEntity sealVehicleRecord(VehicleRecordBean vehicleRecordsBean) {

        HikvisionVehicleRecordEntity vehicleRecord = new HikvisionVehicleRecordEntity();
        vehicleRecord.setPassingUuid(StringUtils.getUUID());
        vehicleRecord.setUuid(vehicleRecordsBean.getUnid());
        vehicleRecord.setParkCode(vehicleRecordsBean.getParkCode());
        vehicleRecord.setParkName(vehicleRecordsBean.getParkName());
        vehicleRecord.setGateCode(vehicleRecordsBean.getGateCode());
        vehicleRecord.setGateName(vehicleRecordsBean.getGateName());
        vehicleRecord.setLaneCode(vehicleRecordsBean.getLaneNo());
        vehicleRecord.setLaneName(vehicleRecordsBean.getLaneName());
        //接口返回的实际是berthNumber
        vehicleRecord.setBerthNumber(vehicleRecordsBean.getBerthCode());
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
     * 保存海康平台过车记录
     *
     * @param vehicleRecordsBeanList vehicleRecordsBeanList
     */
    private ResultDto saveAndSendVehicleRecordList(List<VehicleRecordBean> vehicleRecordsBeanList) {
        ResultDto resultDto = new ResultDto();
        if (vehicleRecordsBeanList == null || vehicleRecordsBeanList.isEmpty()) {
            return resultDto.success();
        }
        for (int i = 0; i < vehicleRecordsBeanList.size(); i++) {

            VehicleRecordBean vehicleRecordsBean = vehicleRecordsBeanList.get(i);

            Date passTime = DateTimeUtils.parseDate(vehicleRecordsBean.getPassTime(), DateTimeUtils.DATE_FORMAT_DATETIME);
            //如果过车数据已经存在
            Integer existCount =
                    hikvisionVehicleRecordCrudService.findCount(
                            vehicleRecordsBean.getUnid(),
                            vehicleRecordsBean.getParkCode(),
                            vehicleRecordsBean.getPlateNo(),
                            vehicleRecordsBean.getDirect(),
                            passTime
                    );
            if (existCount.compareTo(0) <= 0) {
                //构造海康过车数据
                HikvisionVehicleRecordEntity vehicleRecord = sealVehicleRecord(vehicleRecordsBean);
                //保存过车数据
                boolean retVal = hikvisionVehicleRecordCrudService.insert(vehicleRecord);
                if (retVal) {
                    //不管是否有无车牌,发送过车队列消息
                    RocketMessage<PassingVehiclePayload> message = new RocketMessage<>();
                    message.setMessageId(StringUtils.getUUID());
                    message.setDestination(MessageQueueDefinitions.Topic.PASSING_VEHICLE);
                    message.setSender(MessageQueueDefinitions.Sender.GATHER);
                    message.setPayload(buildPassingVehiclePayload(vehicleRecord));
                    //根据ParkCode Hash顺序发送
                    message.setHashKey(vehicleRecord.getParkCode());
                    messageSendService.sendAndSaveOrderlySync(message);
                }
            }
        }
        return resultDto.success();
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
        payload.setBerthNumber(vehicleRecord.getBerthCode());
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

    /**
     * 添加海康过车记录
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addHikvisionVehicleRecord(HikvisionVehicleRecordAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            HikvisionVehicleRecordEntity vehicleRecordEntity = modelMapper.map(requestDto, HikvisionVehicleRecordEntity.class);
            boolean insert = hikvisionVehicleRecordCrudService.insert(vehicleRecordEntity);
            if (!insert) {
                resultDto.failed();
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("查询海康过车记录失败,异常信息:{}", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 更新海康过车记录处理状态
     *
     * @param requestDto
     */
    @Override
    public ResultDto updateProcessStatus(HikVehicleRecordUpProcessStatusDateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Integer integer = hikvisionVehicleRecordCrudService.updateProcessStatus(requestDto.getPassingUuid(), requestDto.getProcessStatus(), requestDto.getProcessRemark());
            if (integer.compareTo(0) == 0) {
                return resultDto.failed();
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("更新海康过车记录处理状态失败,异常信息:{}", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 查询海康过车记录
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<HikvisionVehicleRecordResultDto> getHikvisionVehicleRecord(HikvisionVehicleRecordGetRequestDto requestDto) {

        ObjectResultDto<HikvisionVehicleRecordResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            HikvisionVehicleRecordEntity recordEntity = hikvisionVehicleRecordCrudService.findOne(requestDto.getUnid(), requestDto.getParkCode(), requestDto.getPlateNo(), requestDto.getDirect(), requestDto.getPassTime());
            if (null != recordEntity) {
                HikvisionVehicleRecordResultDto hikvisionVehicleRecordResultDto = modelMapper.map(recordEntity, HikvisionVehicleRecordResultDto.class);
                objectResultDto.setData(hikvisionVehicleRecordResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            objectResultDto.failed();
            log.error("查询海康过车记录失败,异常信息:{}", e.getMessage());
        }
        return objectResultDto;
    }

    /**
     * 获取海康过车图像
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ObjectResultDto<PassingImageFetchResultDto> fetchPassingImage(PassingImageFetchRequestDto requestDto) {
        ObjectResultDto<PassingImageFetchResultDto> objectResultDto = new ObjectResultDto<>();
        try {

            PassPicByUuidParams passPicByUuidParams = new PassPicByUuidParams();
            passPicByUuidParams.setUnid(requestDto.getHikPassingUuid());
            passPicByUuidParams.setParkCode(requestDto.getParkCode());

            //调用海康的根据过车记录UUID获取过车图片接口
            PassPicByUuidResult passPicByUuid = hikvisionService.getPassPicByUuid(passPicByUuidParams);
            boolean success = false;
            if (passPicByUuid != null && passPicByUuid.getErrorCode().equals(ResultCodeEnum.SUCCESS.getValue())) {
                success = true;
            }
            if (success && passPicByUuid.getData() != null) {
                PassingImageFetchResultDto imageFetchResultDto = new PassingImageFetchResultDto();
                imageFetchResultDto.setImages(Lists.newArrayList(passPicByUuid.getData()));
                objectResultDto.setData(imageFetchResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("海康平台过车图片获取失败,异常信息:{}", e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }
}
