package com.zoeeasy.cloud.integration.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.core.base.FundamentalRequestContext;
import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.core.session.SessionParkingInfo;
import com.scapegoat.infrastructure.lock.redisson.core.Lock;
import com.scapegoat.infrastructure.lock.redisson.core.LockFactory;
import com.scapegoat.infrastructure.lock.redisson.core.LockInfo;
import com.scapegoat.infrastructure.lock.redisson.enumerate.LockType;
import com.zoeeasy.cloud.charge.chg.dto.result.CalculateAmountResultDto;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingCurrentChargeInfoRequestDto;
import com.zoeeasy.cloud.charge.park.dto.result.ParkingCurrentChargeInfoResultDto;
import com.zoeeasy.cloud.core.enums.ParkingStatusEnum;
import com.zoeeasy.cloud.core.enums.PassingTypeEnum;
import com.zoeeasy.cloud.core.enums.PassingVehicleDataSourceEnum;
import com.zoeeasy.cloud.core.enums.PayStatusEnum;
import com.zoeeasy.cloud.core.utils.UUIDTool;
import com.zoeeasy.cloud.inspect.enums.InspectOperateResultEnum;
import com.zoeeasy.cloud.inspect.enums.InspectReasonEnum;
import com.zoeeasy.cloud.inspect.enums.InspectResultEnum;
import com.zoeeasy.cloud.inspect.record.InspectRecordService;
import com.zoeeasy.cloud.inspect.record.dto.request.InspectRecordSaveRequestDto;
import com.zoeeasy.cloud.integration.enums.IntegrationResultEnum;
import com.zoeeasy.cloud.integration.inspect.InspectRecordIntegrationService;
import com.zoeeasy.cloud.integration.inspect.dto.request.InspectErrorRecordRequestDto;
import com.zoeeasy.cloud.integration.inspect.dto.request.InspectIntoRecordRequestDto;
import com.zoeeasy.cloud.integration.inspect.dto.request.InspectReasonListGetRequestDto;
import com.zoeeasy.cloud.integration.inspect.validator.InspectErrorRecordRequestDtoValidator;
import com.zoeeasy.cloud.integration.inspect.validator.InspectIntoRecordRequestDtoValidator;
import com.zoeeasy.cloud.integration.message.MessageSendIntegrationService;
import com.zoeeasy.cloud.integration.message.dto.request.ParkingEnterPushMessageRequestDto;
import com.zoeeasy.cloud.integration.message.dto.request.ParkingOrderPushMessageRequestDto;
import com.zoeeasy.cloud.integration.message.dto.request.PassRecordMessageSendRequestDto;
import com.zoeeasy.cloud.integration.message.dto.request.PassRecordNotifySendRequestDto;
import com.zoeeasy.cloud.integration.open.TerminateParkingOrderService;
import com.zoeeasy.cloud.integration.open.dto.request.AnyParkingOrderPlaceRequestDto;
import com.zoeeasy.cloud.integration.open.dto.result.AnyParkingOrderPlaceResultDto;
import com.zoeeasy.cloud.integration.park.CalculateIntegrationService;
import com.zoeeasy.cloud.integration.park.ParkChargeRuleIntegrationService;
import com.zoeeasy.cloud.integration.park.dto.request.ParkingAmountCalculateRequestDto;
import com.zoeeasy.cloud.integration.pass.dto.request.PassVehicleRecordAddRequestDto;
import com.zoeeasy.cloud.integration.pass.validator.PassVehicleRecordAddRequestDtoValidator;
import com.zoeeasy.cloud.order.appoint.AppointmentOrderManagerService;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderUpdateEnterRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderValidPlateRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.result.ParkingAppointmentOrderResultDto;
import com.zoeeasy.cloud.order.enums.ThirdOrderSourceTypeEnum;
import com.zoeeasy.cloud.order.enums.ThirdOrderSyncStatusEnum;
import com.zoeeasy.cloud.order.hikvision.ThirdParkingOrderService;
import com.zoeeasy.cloud.order.hikvision.dto.request.AnyParkingOrderSaveRequestDto;
import com.zoeeasy.cloud.order.parking.ParkingOrderManagerService;
import com.zoeeasy.cloud.order.parking.dto.request.InspectParkingOrderUpdateRequestDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderDeleteRequestDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderGetByRecordNoRequestDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderInsertRequestDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderCreateResultDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderResultDto;
import com.zoeeasy.cloud.pds.magneticdetector.MagneticDetectorService;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.inspect.MagneticDetectorGetByInspectRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.result.inspect.MagneticDetectorGetByInspectResultDto;
import com.zoeeasy.cloud.pds.magneticerrorreport.MagneticErrorReportService;
import com.zoeeasy.cloud.pds.magneticerrorreport.dto.request.MagneticErrorSaveRequestDto;
import com.zoeeasy.cloud.pms.appoint.ParkingAppointmentInfoService;
import com.zoeeasy.cloud.pms.charge.ParkingChargeInfoService;
import com.zoeeasy.cloud.pms.enums.PassDataTypeEnum;
import com.zoeeasy.cloud.pms.enums.PmsResultEnum;
import com.zoeeasy.cloud.pms.park.*;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingLotResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingResultDto;
import com.zoeeasy.cloud.pms.passing.PassingVehicleRecordService;
import com.zoeeasy.cloud.pms.passing.dto.request.PassVehicleGetExceptionRequestDto;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/10/18 0018
 */
@Service(value = "inspectRecordIntegrationService")
@Slf4j
public class InspectRecordIntegrationServiceImpl implements InspectRecordIntegrationService {

    @Autowired
    private IdService idService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LockFactory lockFactory;

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Autowired
    private ParkingOrderManagerService parkingOrderManagerService;

    @Autowired
    private InspectRecordService inspectRecordService;

    @Autowired
    private TerminateParkingOrderService terminateParkingOrderService;

    @Autowired
    private ThirdParkingOrderService thirdParkingOrderService;

    @Autowired
    private ParkingRecordService parkingRecordService;

    @Autowired
    private SpecialVehicleService specialVehicleService;

    @Autowired
    private ParkingLotInfoService parkingLotInfoService;

    @Autowired
    private PlatformProcessService platformProcessService;

    @Autowired
    private MagneticDetectorService magneticDetectorService;

    @Autowired
    private ParkingLotStatusService parkingLotStatusService;

    @Autowired
    private ParkingChargeInfoService parkingChargeInfoService;

    @Autowired
    private MagneticErrorReportService magneticErrorReportService;

    @Autowired
    private ParkingVehicleRecordService parkingVehicleRecordService;

    @Autowired
    private CalculateIntegrationService calculateIntegrationService;

    @Autowired
    private PassingVehicleRecordService passingVehicleRecordService;

    @Autowired
    private ParkingAppointmentInfoService parkingAppointmentInfoService;

    @Autowired
    private MessageSendIntegrationService messageSendIntegrationService;

    @Autowired
    private AppointmentOrderManagerService parkingAppointmentOrderService;

    @Autowired
    private PlatformParkingInfoService platformParkingInfoService;

    @Autowired
    private ParkChargeRuleIntegrationService parkChargeRuleIntegrationService;

    /**
     * 巡检处理入车记录
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto inspectIntoPassRecord(@FluentValid({InspectIntoRecordRequestDtoValidator.class}) InspectIntoRecordRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            SessionParkingInfo currentParking = FundamentalRequestContext.getFundamentalRequestContext().getCurrentParking();
            if (null == currentParking) {
                return resultDto.makeResult(PmsResultEnum.PARKING_NOT_EXIST.getValue(), PmsResultEnum.PARKING_NOT_EXIST.getComment());
            }
            Long parkingId = currentParking.getParkingId();
            //根据过车记录号获取过车记录
            PassingVehicleRecordGetRequestDto passingVehicleRecordGetRequestDto = new PassingVehicleRecordGetRequestDto();
            passingVehicleRecordGetRequestDto.setPassingNo(requestDto.getPassingNo());
            passingVehicleRecordGetRequestDto.setParkingId(parkingId);
            ObjectResultDto<PassingVehicleRecordResultDto> vehicleRecordServicePassRecordByPassNo = passingVehicleRecordService.getByPassNo(passingVehicleRecordGetRequestDto);
            if (vehicleRecordServicePassRecordByPassNo.isFailed() || vehicleRecordServicePassRecordByPassNo.getData() == null) {
                return resultDto.makeResult(InspectResultEnum.PARKINGRECORD_EMPTY.getValue(), InspectResultEnum.PARKINGRECORD_EMPTY.getComment());
            }
            PassingVehicleRecordResultDto passVehicleRecord = vehicleRecordServicePassRecordByPassNo.getData();
            if (!passVehicleRecord.getParkingId().equals(parkingId)) {
                return resultDto.makeResult(InspectResultEnum.PARK_NOT_UPDATE.getValue(), InspectResultEnum.PARK_NOT_UPDATE.getComment());
            }
            // 停车场
            ParkingGetRequestDto parkingInfoGetRequestDto = new ParkingGetRequestDto();
            parkingInfoGetRequestDto.setId(parkingId);
            ObjectResultDto<ParkingResultDto> parkingInfoServiceParkInfoById = parkingInfoService.getParkingInfo(parkingInfoGetRequestDto);
            if (parkingInfoServiceParkInfoById.isFailed() || parkingInfoServiceParkInfoById.getData() == null) {
                return resultDto.makeResult(PmsResultEnum.PARKING_NOT_FOUND.getValue(), PmsResultEnum.PARKING_NOT_FOUND.getComment());
            }
            ParkingResultDto parkingInfo = parkingInfoServiceParkInfoById.getData();
            //泊位
            ParkingLotInfoGetByCodeRequestDto parkingLotInfoGetByCodeRequestDto = new ParkingLotInfoGetByCodeRequestDto();
            parkingLotInfoGetByCodeRequestDto.setParkingLotCode(requestDto.getParkingLotCode());
            ObjectResultDto<ParkingLotResultDto> parkingLotById = parkingLotInfoService.getParkingLotByCode(parkingLotInfoGetByCodeRequestDto);
            if (parkingLotById.isFailed() || parkingLotById.getData() == null) {
                return resultDto.makeResult(PmsResultEnum.PARKING_LOT_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_LOT_NOT_FOUND.getComment());
            }
            ParkingLotResultDto parkingLotInfo = parkingLotById.getData();
            //停车类型
            Integer parkingType = null;
            SpecialTypeGetRequestDto specialTypeGetRequestDto = new SpecialTypeGetRequestDto();
            specialTypeGetRequestDto.setPlateNumber(requestDto.getPlateNumber());
            specialTypeGetRequestDto.setParkingId(parkingInfo.getId());
            ObjectResultDto<SpecialVehicleTypeResultDto> specialTypeByPlateNo = specialVehicleService.findSpecialTypeByPlateNo(specialTypeGetRequestDto);
            if (specialTypeByPlateNo.isSuccess() && specialTypeByPlateNo.getData() != null) {
                SpecialVehicleTypeResultDto specialTypeByPlateNoData = specialTypeByPlateNo.getData();
                parkingType = specialTypeByPlateNoData.getSpecialType();
            }
            //根据过车记录号获取停车记录
            ParkingRecordGetByIntoRecordNoRequestDto parkingRecordGetByIntoRecordNoRequestDto = new ParkingRecordGetByIntoRecordNoRequestDto();
            parkingRecordGetByIntoRecordNoRequestDto.setPassingNo(passVehicleRecord.getPassingNo());
            parkingRecordGetByIntoRecordNoRequestDto.setPassType(PassingTypeEnum.ENTRY.getValue());
            parkingRecordGetByIntoRecordNoRequestDto.setParkingId(parkingId);
            parkingRecordGetByIntoRecordNoRequestDto.setParkingLotId(parkingLotInfo.getId());
            parkingRecordGetByIntoRecordNoRequestDto.setTenantId(parkingInfo.getTenantId());
            ObjectResultDto<ParkingRecordResultDto> recordServiceByIntoRecordId = platformProcessService.getByIntoRecordNo(parkingRecordGetByIntoRecordNoRequestDto);
            if (recordServiceByIntoRecordId.isFailed() || recordServiceByIntoRecordId.getData() == null) {
                return resultDto.makeResult(PmsResultEnum.PARKING_RECORD_NOT_FOUND.getValue(), PmsResultEnum.PARKING_RECORD_NOT_FOUND.getComment());
            }
            ParkingRecordResultDto parkingRecord = recordServiceByIntoRecordId.getData();
            Integer dataType = passVehicleRecord.getDataType();
            //更新过车记录
            PassingRecordUpdateRequestDto passingRecordUpdateRequestDto = new PassingRecordUpdateRequestDto();
            passingRecordUpdateRequestDto.setPassingNo(passVehicleRecord.getPassingNo());
            passingRecordUpdateRequestDto.setPlateNumber(requestDto.getPlateNumber());
            passingRecordUpdateRequestDto.setPlateColor(requestDto.getPlateColor());
            passingRecordUpdateRequestDto.setCarType(requestDto.getCarType());
            passingRecordUpdateRequestDto.setPlateNoExist(Boolean.TRUE);
            passingRecordUpdateRequestDto.setParkingId(parkingId);
            passingRecordUpdateRequestDto.setParkingLotId(parkingLotInfo.getId());
            passingRecordUpdateRequestDto.setPassTime(requestDto.getStartTime());
            passingRecordUpdateRequestDto.setEntryTime(requestDto.getStartTime());
            passingRecordUpdateRequestDto.setParkingId(parkingId);
            updatePassVehicleRecordLock(passingRecordUpdateRequestDto, getUpdatePassVehicleRecordLockKey(requestDto.getPassingNo()));
            //获取在停车辆记录
            ParkingVehicleRecordGetByIntoPassNoRequestDto parkingVehicleRecordGetByPassNoRequestDto = new ParkingVehicleRecordGetByIntoPassNoRequestDto();
            parkingVehicleRecordGetByPassNoRequestDto.setParkingId(parkingId);
            parkingVehicleRecordGetByPassNoRequestDto.setIntoRecordNo(passVehicleRecord.getPassingNo());
            ObjectResultDto<ParkingVehicleRecordResultDto> byIntoRecordNo = parkingVehicleRecordService.getByIntoRecordNo(parkingVehicleRecordGetByPassNoRequestDto);
            if (byIntoRecordNo.isFailed() || byIntoRecordNo.getData() == null) {
                return resultDto.makeResult(PmsResultEnum.PARKING_VEHICLE_RECORD_EMPTY.getValue(), PmsResultEnum.PARKING_VEHICLE_RECORD_EMPTY.getComment());
            }
            ParkingVehicleRecordResultDto parkingVehicle = byIntoRecordNo.getData();
            // 是否预约停车
            Boolean isAppoint;
            AppointOrderValidPlateRequestDto appointOrderValidPlateRequestDto = new AppointOrderValidPlateRequestDto();
            appointOrderValidPlateRequestDto.setParkingId(parkingInfo.getId());
            appointOrderValidPlateRequestDto.setPlateNumber(requestDto.getPlateNumber());
            appointOrderValidPlateRequestDto.setDeadlineTime(requestDto.getStartTime());
            ObjectResultDto<ParkingAppointmentOrderResultDto> appointmentOrderResult = parkingAppointmentOrderService.getValidOrderList(appointOrderValidPlateRequestDto);
            ParkingAppointmentOrderResultDto appointmentOrder = null;
            if (appointmentOrderResult.isSuccess() && appointmentOrderResult.getData() != null) {
                isAppoint = Boolean.TRUE;
                //更新预约车辆已入场
                appointmentOrder = appointmentOrderResult.getData();
                AppointOrderUpdateEnterRequestDto appointOrderEnterRequestDto = new AppointOrderUpdateEnterRequestDto();
                appointOrderEnterRequestDto.setOrderNo(appointmentOrder.getOrderNo());
                appointOrderEnterRequestDto.setEnterTime(requestDto.getStartTime());
                updatePassVehicleRecordLock(appointOrderEnterRequestDto, getEnterAppointLockKey(appointmentOrder.getOrderNo()));
            } else {
                isAppoint = Boolean.FALSE;
            }
            //预约收费规则
            ParkingAppointInfoResultDto appointmentInfo = null;
            ParkingAppointInfoGetByParkingIdRequestDto parkAppointInfoGetByParkingIdRequestDto = new ParkingAppointInfoGetByParkingIdRequestDto();
            parkAppointInfoGetByParkingIdRequestDto.setParkingId(parkingInfo.getId());
            ObjectResultDto<ParkingAppointInfoResultDto> parkingAppointmentInfoByParkingId = parkingAppointmentInfoService.getAppointmentInfoByParkingId(parkAppointInfoGetByParkingIdRequestDto);
            if (parkingAppointmentInfoByParkingId.isSuccess() || parkingAppointmentInfoByParkingId.getData() != null) {
                appointmentInfo = parkingAppointmentInfoByParkingId.getData();
            }
            //收费信息
            ParkingChargeInfoGetByParkingIdRequestDto parkingChargeInfoGetByParkingIdRequestDto = new ParkingChargeInfoGetByParkingIdRequestDto();
            parkingChargeInfoGetByParkingIdRequestDto.setParkingId(parkingInfo.getId());
            ObjectResultDto<ParkingChargeInfoResultDto> parkChargeInfoByParkingId = parkingChargeInfoService.getParkChargeInfoByParkingId(parkingChargeInfoGetByParkingIdRequestDto);
            if (parkChargeInfoByParkingId.isFailed() || parkChargeInfoByParkingId.getData() == null) {
                return resultDto.makeResult(IntegrationResultEnum.PARKING_CHARGE_INFO_EMPTY.getValue(), IntegrationResultEnum.PARKING_CHARGE_INFO_EMPTY.getComment());
            }
            ParkingChargeInfoResultDto chargeInfo = parkChargeInfoByParkingId.getData();
            //是否出车
            if (!requestDto.getTurnOut()) {
                //未出车
                //在停车辆记录修改
                ParkingVehicleRecordUpdateRequestDto parkingVehicleRecordUpDate = new ParkingVehicleRecordUpdateRequestDto();
                parkingVehicleRecordUpDate.setId(parkingVehicle.getId());
                parkingVehicleRecordUpDate.setPlateNumber(requestDto.getPlateNumber());
                parkingVehicleRecordUpDate.setPlateColor(requestDto.getPlateColor());
                parkingVehicleRecordUpDate.setCarType(requestDto.getCarType());
                parkingVehicleRecordUpDate.setParkingId(parkingId);
                parkingVehicleRecordUpDate.setParkingLotId(parkingLotInfo.getId());
                parkingVehicleRecordUpDate.setStartTime(requestDto.getStartTime());
                parkingVehicleRecordUpDate.setParkingId(parkingId);
                updateParkVehicleRecordLock(parkingVehicleRecordUpDate, getParkVehicleRecordLockKey(passVehicleRecord.getPassingNo()));
                //停车记录修改
                ParkingRecordUpdateIntegrationRequestDto parkingRecordUpdateRequestDto = new ParkingRecordUpdateIntegrationRequestDto();
                parkingRecordUpdateRequestDto.setParkingId(parkingId);
                parkingRecordUpdateRequestDto.setId(parkingRecord.getId());
                parkingRecordUpdateRequestDto.setParkingId(parkingId);
                parkingRecordUpdateRequestDto.setCarType(requestDto.getCarType());
                parkingRecordUpdateRequestDto.setPlateColor(requestDto.getPlateColor());
                parkingRecordUpdateRequestDto.setPlateNumber(requestDto.getPlateNumber());
                parkingRecordUpdateRequestDto.setStartTime(requestDto.getStartTime());
                parkingRecordUpdateRequestDto.setAppointed(isAppoint);
                if (isAppoint) {
                    parkingRecordUpdateRequestDto.setAppointOrderNo(appointmentOrder.getOrderNo());
                    if (appointmentInfo != null) {
                        parkingRecordUpdateRequestDto.setAppointRuleId(appointmentInfo.getId());
                    }
                }
                if (null != parkingType) {
                    parkingRecordUpdateRequestDto.setParkingType(parkingType);
                }
                updateParkingRecordWithLock(parkingRecordUpdateRequestDto, getParkRecordLockKey(parkingRecord.getRecordNo()));
                if (!dataType.equals(PassDataTypeEnum.MAGNETIC.getValue())) {
                    //根据停车记录号获取订单
                    ParkingOrderGetByRecordNoRequestDto parkingOrderGetByRecordNoRequestDto = new ParkingOrderGetByRecordNoRequestDto();
                    parkingOrderGetByRecordNoRequestDto.setRecordNo(parkingRecord.getRecordNo());
                    ObjectResultDto<ParkingOrderResultDto> parkingOrderResultDto = parkingOrderManagerService.getParkingOrderByRecordNo(parkingOrderGetByRecordNoRequestDto);
                    if (parkingOrderResultDto.isFailed() || parkingOrderResultDto.getData() == null) {
                        //订单获取失败
                        //创建订单
                        ParkingOrderInsertRequestDto parkingOrderSaveRequestDto = createIntoInspectOrder(requestDto, parkingInfo, parkingLotInfo
                        );
                        createAnyOrder(parkingInfo, dataType, parkingOrderSaveRequestDto);
                        parkingOrderSaveRequestDto.setAppointed(isAppoint);
                        if (isAppoint) {
                            parkingOrderSaveRequestDto.setAppointOrderNo(appointmentOrder.getOrderNo());
                        }
                        parkingOrderSaveRequestDto.setChargeInfoId(chargeInfo.getId());
                        parkingOrderSaveRequestDto.setRecordNo(parkingRecord.getRecordNo());
                        parkingOrderManagerService.createParkingOrder(parkingOrderSaveRequestDto);
                    }
                    ParkingOrderResultDto parkingOrder = parkingOrderResultDto.getData();
                    //判断车牌号是否改变,如果改变删除原订单,创建新订单,如果未改变更新订单
                    if (!passVehicleRecord.getPlateNumber().equals(requestDto.getPlateNumber())) {
                        //删除原订单
                        ParkingOrderDeleteRequestDto parkingOrderDeleteRequestDto = new ParkingOrderDeleteRequestDto();
                        parkingOrderDeleteRequestDto.setOrderNo(parkingOrder.getOrderNo());
                        parkingOrderDeleteRequestDto.setPlateNumber(parkingOrder.getPlateNumber());
                        parkingOrderManagerService.deleteParkingOrder(parkingOrderDeleteRequestDto);
                        //创建新订单
                        ParkingOrderInsertRequestDto parkingOrderSaveRequestDto = createIntoInspectOrder(requestDto, parkingInfo, parkingLotInfo
                        );
                        createAnyOrder(parkingInfo, dataType, parkingOrderSaveRequestDto);
                        parkingOrderSaveRequestDto.setAppointed(isAppoint);
                        if (isAppoint) {
                            parkingOrderSaveRequestDto.setAppointOrderNo(appointmentOrder.getOrderNo());
                        }
                        parkingOrderSaveRequestDto.setChargeInfoId(chargeInfo.getId());
                        parkingOrderSaveRequestDto.setRecordNo(parkingRecord.getRecordNo());
                        parkingOrderManagerService.createParkingOrder(parkingOrderSaveRequestDto);
                    } else {
                        //更新停车订单
                        InspectParkingOrderUpdateRequestDto parkingOrderUpdateRequestDto = new InspectParkingOrderUpdateRequestDto();
                        parkingOrderUpdateRequestDto.setChargeInfoId(parkingOrder.getChargeInfoId());
                        parkingOrderUpdateRequestDto.setOrderNo(parkingOrder.getOrderNo());
                        parkingOrderUpdateRequestDto.setParkingId(parkingId);
                        parkingOrderUpdateRequestDto.setRecordNo(parkingOrder.getRecordNo());
                        parkingOrderUpdateRequestDto.setParkingId(parkingInfo.getId());
                        parkingOrderUpdateRequestDto.setChargeInfoId(chargeInfo.getId());
                        parkingOrderUpdateRequestDto.setParkingName(parkingInfo.getFullName());
                        parkingOrderUpdateRequestDto.setParkingLotId(parkingLotInfo.getId());
                        parkingOrderUpdateRequestDto.setParkingLotNumber(parkingLotInfo.getNumber());
                        parkingOrderUpdateRequestDto.setPlateNumber(requestDto.getPlateNumber());
                        parkingOrderUpdateRequestDto.setPlateColor(requestDto.getPlateColor());
                        parkingOrderUpdateRequestDto.setCarStyle(requestDto.getCarType());
                        parkingOrderUpdateRequestDto.setStartTime(requestDto.getStartTime());
                        parkingOrderUpdateRequestDto.setAppointed(isAppoint);
                        if (isAppoint) {
                            parkingOrderUpdateRequestDto.setAppointOrderNo(appointmentOrder.getOrderNo());
                        }
                        //更新客户端账单
                        updateAnyOrder(requestDto, passVehicleRecord, parkingInfo, dataType, parkingOrder, parkingOrderUpdateRequestDto);
                        updateParkOrderLock(parkingOrderUpdateRequestDto, getParkOrderLockKey(parkingOrder.getOrderNo()));
                    }

                } else {
                    //地磁过车数据
                    //生成订单
                    ParkingOrderInsertRequestDto parkingOrderSaveRequestDto = createIntoInspectOrder(requestDto,
                            parkingInfo, parkingLotInfo);
                    parkingOrderSaveRequestDto.setAppointed(isAppoint);
                    if (isAppoint) {
                        parkingOrderSaveRequestDto.setAppointOrderNo(appointmentOrder.getOrderNo());
                        //更新预约车位数量
                        ParkingLotAppointUpdateRequestDto parkingLotAppointUpdateRequestDto = new ParkingLotAppointUpdateRequestDto();
                        parkingLotAppointUpdateRequestDto.setParkingId(parkingInfo.getId());
                        parkingLotAppointUpdateRequestDto.setIncrease(Boolean.FALSE);
                        updateParkingLotAppointWithLock(parkingLotAppointUpdateRequestDto, getParkingLotAppointLockKey(parkingInfo.getCode()));
                    }
                    parkingOrderSaveRequestDto.setChargeInfoId(chargeInfo.getId());
                    parkingOrderSaveRequestDto.setRecordNo(parkingRecord.getRecordNo());
                    ObjectResultDto<ParkingOrderCreateResultDto> orderSaveResultDtoObjectResultDto = parkingOrderManagerService.createParkingOrder(parkingOrderSaveRequestDto);
                    //发送停车入场消息
                    ParkingEnterPushMessageRequestDto parkingEnterPushMessageRequestDto = new ParkingEnterPushMessageRequestDto();
                    parkingEnterPushMessageRequestDto.setOrderId(orderSaveResultDtoObjectResultDto.getData().getId());
                    parkingEnterPushMessageRequestDto.setOrderNo(parkingOrderSaveRequestDto.getOrderNo());
                    parkingEnterPushMessageRequestDto.setParkingName(parkingOrderSaveRequestDto.getParkingName());
                    parkingEnterPushMessageRequestDto.setPlateColor(parkingOrderSaveRequestDto.getPlateColor());
                    parkingEnterPushMessageRequestDto.setPlateNumber(parkingOrderSaveRequestDto.getPlateNumber());
                    parkingEnterPushMessageRequestDto.setStartTime(parkingOrderSaveRequestDto.getStartTime());
                    messageSendIntegrationService.sendParkingEnterPushMessage(parkingEnterPushMessageRequestDto);
                }
            } else {
                //新增出车过车记录
                PassingVehicleRecordCreateRequestDto outPassingVehicleRecord = createPassRecord(requestDto.getPlateNumber(), requestDto.getPlateColor(), requestDto.getCarType(),
                        parkingInfo, parkingLotInfo);
                outPassingVehicleRecord.setPassTime(requestDto.getEndTime());
                outPassingVehicleRecord.setExitTime(requestDto.getEndTime());
                outPassingVehicleRecord.setPassingType(PassingTypeEnum.EXIT.getValue());
                outPassingVehicleRecord.setDataSource(PassingVehicleDataSourceEnum.MANUAL.getValue());
                outPassingVehicleRecord.setDataType(PassDataTypeEnum.ARTIFICIAL.getValue());
                if (null != parkingType) {
                    outPassingVehicleRecord.setParkingType(parkingType);
                }
                passingVehicleRecordService.savePassRecord(outPassingVehicleRecord);
                //删除在停车辆
                ParkingVehicleRecordDeleteRequestDto deleteParking = new ParkingVehicleRecordDeleteRequestDto();
                deleteParking.setId(parkingVehicle.getId());
                deleteParking.setParkingId(parkingId);
                parkingVehicleRecordService.deleteParkVehicleRecord(deleteParking);
                //停车记录更新
                ParkingRecordUpdateIntegrationRequestDto updateIntegrationRequestDto = new ParkingRecordUpdateIntegrationRequestDto();
                updateIntegrationRequestDto.setId(parkingRecord.getId());
                updateIntegrationRequestDto.setParkingId(parkingId);
                updateIntegrationRequestDto.setCarType(requestDto.getCarType());
                updateIntegrationRequestDto.setPlateColor(requestDto.getPlateColor());
                updateIntegrationRequestDto.setPlateNumber(requestDto.getPlateNumber());
                updateIntegrationRequestDto.setStartTime(requestDto.getStartTime());
                updateIntegrationRequestDto.setAppointed(isAppoint);
                updateIntegrationRequestDto.setParkingId(parkingId);
                if (isAppoint) {
                    //更新预约车位数量
                    ParkingLotAppointUpdateRequestDto parkingLotAppointUpdateRequestDto = new ParkingLotAppointUpdateRequestDto();
                    parkingLotAppointUpdateRequestDto.setParkingId(parkingInfo.getId());
                    parkingLotAppointUpdateRequestDto.setIncrease(Boolean.TRUE);
                    updateParkingLotAppointWithLock(parkingLotAppointUpdateRequestDto, getParkingLotAppointLockKey(parkingInfo.getCode()));
                    updateIntegrationRequestDto.setAppointOrderNo(appointmentOrder.getOrderNo());
                    if (appointmentInfo != null) {
                        updateIntegrationRequestDto.setAppointRuleId(appointmentInfo.getId());
                    }
                }
                if (null != parkingType) {
                    updateIntegrationRequestDto.setParkingType(parkingType);
                }
                updateIntegrationRequestDto.setOutRecordNo(outPassingVehicleRecord.getPassingNo());
                updateIntegrationRequestDto.setEndTime(outPassingVehicleRecord.getPassTime());
                updateIntegrationRequestDto.setStatus(ParkingStatusEnum.OUTING.getValue());
                updateParkingRecordWithLock(updateIntegrationRequestDto, getParkRecordLockKey(parkingRecord.getRecordNo()));
                //更新停车订单并计算
                if (!dataType.equals(PassDataTypeEnum.MAGNETIC.getValue())) {
                    //根据停车记录号获取订单
                    ParkingOrderGetByRecordNoRequestDto parkingOrderGetByRecordNoRequestDto = new ParkingOrderGetByRecordNoRequestDto();
                    parkingOrderGetByRecordNoRequestDto.setRecordNo(parkingRecord.getRecordNo());
                    ObjectResultDto<ParkingOrderResultDto> parkingOrderResultDto = parkingOrderManagerService.getParkingOrderByRecordNo(parkingOrderGetByRecordNoRequestDto);
                    if (parkingOrderResultDto.isFailed() || parkingOrderResultDto.getData() == null) {
                        //创建并结算订单
                        createOrderAndSettle(requestDto, parkingInfo, parkingLotInfo,
                                parkingRecord.getRecordNo(), isAppoint, appointmentOrder,
                                chargeInfo.getId(), dataType);
                    } else {
                        ParkingOrderResultDto parkingOrder = parkingOrderResultDto.getData();
                        if (!passVehicleRecord.getPlateNumber().equals(requestDto.getPlateNumber())) {
                            //删除原订单
                            ParkingOrderDeleteRequestDto parkingOrderDeleteRequestDto = new ParkingOrderDeleteRequestDto();
                            parkingOrderDeleteRequestDto.setOrderNo(parkingOrder.getOrderNo());
                            parkingOrderDeleteRequestDto.setPlateNumber(parkingOrder.getPlateNumber());
                            parkingOrderManagerService.deleteParkingOrder(parkingOrderDeleteRequestDto);
                            //创建新订单
                            createOrderAndSettle(requestDto, parkingInfo, parkingLotInfo, parkingRecord.getRecordNo(),
                                    isAppoint, appointmentOrder, chargeInfo.getId(), dataType);
                        } else {
                            //更新停车订单
                            InspectParkingOrderUpdateRequestDto parkingOrderUpdateRequestDto = new InspectParkingOrderUpdateRequestDto();
                            parkingOrderUpdateRequestDto.setChargeInfoId(parkingOrder.getChargeInfoId());
                            parkingOrderUpdateRequestDto.setOrderNo(parkingOrder.getOrderNo());
                            parkingOrderUpdateRequestDto.setParkingId(parkingId);
                            parkingOrderUpdateRequestDto.setRecordNo(parkingOrder.getRecordNo());
                            parkingOrderUpdateRequestDto.setParkingId(parkingInfo.getId());
                            parkingOrderUpdateRequestDto.setChargeInfoId(chargeInfo.getId());
                            parkingOrderUpdateRequestDto.setParkingName(parkingInfo.getFullName());
                            parkingOrderUpdateRequestDto.setParkingLotId(parkingLotInfo.getId());
                            parkingOrderUpdateRequestDto.setParkingLotNumber(parkingLotInfo.getNumber());
                            parkingOrderUpdateRequestDto.setPlateNumber(requestDto.getPlateNumber());
                            parkingOrderUpdateRequestDto.setPlateColor(requestDto.getPlateColor());
                            parkingOrderUpdateRequestDto.setCarStyle(requestDto.getCarType());
                            parkingOrderUpdateRequestDto.setStartTime(requestDto.getStartTime());
                            parkingOrderUpdateRequestDto.setEndTime(requestDto.getEndTime());
                            parkingOrderUpdateRequestDto.setAppointed(isAppoint);
                            if (isAppoint) {
                                parkingOrderUpdateRequestDto.setAppointOrderNo(appointmentOrder.getOrderNo());
                            }
                            //计算订单
                            updateOrderAndSettleOrder(parkingOrder,
                                    parkingInfo, parkingLotInfo, requestDto.getEndTime(), parkingOrderUpdateRequestDto);
                            //更新客户端账单
                            updateAnyOrder(requestDto, passVehicleRecord, parkingInfo, dataType, parkingOrder, parkingOrderUpdateRequestDto);
                            updateParkOrderLock(parkingOrderUpdateRequestDto, getParkOrderLockKey(parkingOrder.getOrderNo()));
                            pushNewOrder(parkingOrder.getId(), parkingOrder.getOrderNo(), parkingInfo.getFullName(),
                                    requestDto.getPlateColor(), requestDto.getPlateNumber(), parkingOrder.getStartTime());
                        }
                    }
                } else {
                    //创建并结算订单
                    createOrderAndSettle(requestDto, parkingInfo, parkingLotInfo, parkingRecord.getRecordNo(),
                            isAppoint,
                            appointmentOrder, chargeInfo.getId(), dataType);

                }
            }
            //生成巡检记录
            InspectRecordSaveRequestDto inspectRecordSaveRequestDto = createInspectRecord(requestDto.getPlateNumber()
                    , requestDto.getPlateColor(), requestDto.getStartTime(), requestDto.getCarType(), parkingType,
                    parkingInfo,
                    parkingLotInfo.getId(), parkingRecord.getId(), parkingRecord.getRecordNo());
            inspectRecordService.saveInspectRecord(inspectRecordSaveRequestDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("巡检入车记录操作失败" + e.getMessage());
            resultDto.makeResult(InspectResultEnum.INSPECT_INTO_RECORD_ERR.getValue(), InspectResultEnum.INSPECT_INTO_RECORD_ERR.getComment());
        }
        return resultDto;
    }

    private void updateAnyOrder(InspectIntoRecordRequestDto requestDto, PassingVehicleRecordResultDto passVehicleRecord,
                                ParkingResultDto parkingInfo, Integer dataType, ParkingOrderResultDto parkingOrder,
                                InspectParkingOrderUpdateRequestDto parkingOrderUpdateRequestDto) {
        if (dataType.equals(PassDataTypeEnum.CLIENT.getValue())) {
            AnyParkingOrderPlaceRequestDto anyOrderCreateRequestDto = new AnyParkingOrderPlaceRequestDto();
            anyOrderCreateRequestDto.setParkingId(parkingInfo.getId());
            anyOrderCreateRequestDto.setTenantId(parkingInfo.getTenantId());
            anyOrderCreateRequestDto.setPlateNumber(requestDto.getPlateNumber());
            anyOrderCreateRequestDto.setPlateColor(passVehicleRecord.getPlateColor());
            anyOrderCreateRequestDto.setCarType(passVehicleRecord.getCarType());
            anyOrderCreateRequestDto.setOrderNo(parkingOrder.getOrderNo());
            anyOrderCreateRequestDto.setRecordNo(parkingOrder.getRecordNo());
            AnyParkingOrderPlaceResultDto anyParkingOrder = this.createAnyParkingOrder(anyOrderCreateRequestDto);
            if (anyParkingOrder != null) {
                parkingOrderUpdateRequestDto.setThirdBillNo(anyParkingOrder.getBillCode());
                parkingOrderUpdateRequestDto.setThirdBillSyncStatus(ThirdOrderSyncStatusEnum.CREATED.getValue());
                parkingOrderUpdateRequestDto.setActualPayAmount(anyParkingOrder.getPayAmount());
                parkingOrderUpdateRequestDto.setParkingLength(anyParkingOrder.getCostTime());
            } else {
                parkingOrderUpdateRequestDto.setThirdBillSyncStatus(ThirdOrderSyncStatusEnum.UNCREATED.getValue());
                parkingOrderUpdateRequestDto.setThirdBillNo("");
            }
        }
    }

    private void createAnyOrder(ParkingResultDto parkingInfo, Integer dataType, ParkingOrderInsertRequestDto parkingOrderSaveRequestDto) {
        if (dataType.equals(PassDataTypeEnum.CLIENT.getValue())) {
            AnyParkingOrderPlaceRequestDto anyOrderCreateRequestDto = new AnyParkingOrderPlaceRequestDto();
            anyOrderCreateRequestDto.setRecordNo(parkingOrderSaveRequestDto.getRecordNo());
            anyOrderCreateRequestDto.setTenantId(parkingInfo.getTenantId());
            anyOrderCreateRequestDto.setParkingId(parkingInfo.getId());
            anyOrderCreateRequestDto.setOrderNo(parkingOrderSaveRequestDto.getOrderNo());
            anyOrderCreateRequestDto.setPlateNumber(parkingOrderSaveRequestDto.getPlateNumber());
            anyOrderCreateRequestDto.setPlateColor(parkingOrderSaveRequestDto.getPlateColor());
            anyOrderCreateRequestDto.setCarType(parkingOrderSaveRequestDto.getCarStyle());
            AnyParkingOrderPlaceResultDto anyParkingOrder = this.createAnyParkingOrder(anyOrderCreateRequestDto);
            if (anyParkingOrder != null) {
                parkingOrderSaveRequestDto.setActualPayAmount(anyParkingOrder.getPayAmount());
                parkingOrderSaveRequestDto.setParkingLength(anyParkingOrder.getCostTime());
                parkingOrderSaveRequestDto.setThirdBillNo(anyParkingOrder.getBillCode());
                parkingOrderSaveRequestDto.setThirdBillSyncStatus(ThirdOrderSyncStatusEnum.CREATED.getValue());
                parkingOrderSaveRequestDto.setThirdBillSourceType(ThirdOrderSourceTypeEnum.ANYPARKING.getValue());
            } else {
                parkingOrderSaveRequestDto.setThirdBillSyncStatus(ThirdOrderSyncStatusEnum.UNCREATED.getValue());
                parkingOrderSaveRequestDto.setThirdBillNo("");
            }
        }
    }

    /**
     * 误报巡检
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto inspectErrorRecord(@FluentValid({InspectErrorRecordRequestDtoValidator.class}) InspectErrorRecordRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (requestDto.getId() == null) {
                return resultDto.makeResult(InspectResultEnum.EMPTY_PARKING_LOT_NOT_OPERATOR.getValue(), InspectResultEnum.EMPTY_PARKING_LOT_NOT_OPERATOR.getComment());
            }
            SessionParkingInfo currentParking = FundamentalRequestContext.getFundamentalRequestContext().getCurrentParking();
            if (null == currentParking) {
                return resultDto.makeResult(PmsResultEnum.PARKING_NOT_EXIST.getValue(), PmsResultEnum.PARKING_NOT_EXIST.getComment());
            }
            Long parkingId = currentParking.getParkingId();
            //停车场
            ParkingGetRequestDto parkingInfoGetRequestDto = new ParkingGetRequestDto();
            parkingInfoGetRequestDto.setId(parkingId);
            ObjectResultDto<ParkingResultDto> parkingInfoServiceParkInfoById = parkingInfoService.getParkingInfo(parkingInfoGetRequestDto);
            if (parkingInfoServiceParkInfoById.isFailed() || parkingInfoServiceParkInfoById.getData() == null) {
                return resultDto.makeResult(PmsResultEnum.PARKING_NOT_FOUND.getValue(), PmsResultEnum.PARKING_NOT_FOUND.getComment());
            }
            ParkingResultDto parkingInfo = parkingInfoServiceParkInfoById.getData();
            //泊位
            ParkingLotInfoGetByCodeRequestDto parkingLotInfoGetByCodeRequestDto = new ParkingLotInfoGetByCodeRequestDto();
            parkingLotInfoGetByCodeRequestDto.setParkingLotCode(requestDto.getParkingLotCode());
            ObjectResultDto<ParkingLotResultDto> parkingLotById = parkingLotInfoService.getParkingLotByCode(parkingLotInfoGetByCodeRequestDto);
            if (parkingLotById.isFailed() || parkingLotById.getData() == null) {
                return resultDto.makeResult(PmsResultEnum.PARKING_LOT_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_LOT_NOT_FOUND.getComment());
            }
            ParkingLotResultDto parkingLotInfo = parkingLotById.getData();
            //获取检测器
            MagneticDetectorGetByInspectRequestDto magneticDetectorGetByInspectRequestDto = new MagneticDetectorGetByInspectRequestDto();
            magneticDetectorGetByInspectRequestDto.setParkingId(parkingInfo.getId());
            magneticDetectorGetByInspectRequestDto.setParkingLotId(parkingLotInfo.getId());
            ObjectResultDto<MagneticDetectorGetByInspectResultDto> detectorByInspect = magneticDetectorService.getMagneticDetectorByInspect(magneticDetectorGetByInspectRequestDto);
            if (detectorByInspect.isFailed() || detectorByInspect.getData() == null) {
                return resultDto.makeResult(InspectResultEnum.MAGNETIC_DETECTOR_EMPTY.getValue(), InspectResultEnum.MAGNETIC_DETECTOR_EMPTY.getComment());
            }
            //维护地磁误报
            MagneticDetectorGetByInspectResultDto magneticDetector = detectorByInspect.getData();
            MagneticErrorSaveRequestDto magneticErrorSaveRequestDto = new MagneticErrorSaveRequestDto();
            magneticErrorSaveRequestDto.setParkingId(parkingInfo.getId());
            magneticErrorSaveRequestDto.setParkingLotId(parkingLotInfo.getId());
            magneticErrorSaveRequestDto.setDetectorId(magneticDetector.getId());
            magneticErrorSaveRequestDto.setProvider(magneticDetector.getProvider());
            magneticErrorSaveRequestDto.setSerialNumber(magneticDetector.getSerialNumber());
            magneticErrorSaveRequestDto.setProcessReason(requestDto.getProcessReason());
            magneticErrorSaveRequestDto.setInspectUserId(FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity().getUserId());
            magneticErrorSaveRequestDto.setInspectUserName(FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity().getUsername());
            magneticErrorSaveRequestDto.setProcessReason(requestDto.getProcessReason());
            magneticErrorReportService.save(magneticErrorSaveRequestDto);
            //删除该泊位上的在停车辆
            ParkVehicleDeleteByLotRequestDto parkVehicleDeleteByLotRequestDto = new ParkVehicleDeleteByLotRequestDto();
            parkVehicleDeleteByLotRequestDto.setParkingId(parkingInfo.getId());
            parkVehicleDeleteByLotRequestDto.setParkingLotId(parkingLotInfo.getId());
            parkVehicleDeleteByLotRequestDto.setParkVehicleId(requestDto.getId());
            platformProcessService.deleteParkVehicleByLot(parkVehicleDeleteByLotRequestDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("误报巡检操作失败" + e.getMessage());
            resultDto.makeResult(InspectResultEnum.INSPECT_ERROR_RECORD_ERR.getValue(), InspectResultEnum.INSPECT_ERROR_RECORD_ERR.getComment());
        }
        return resultDto;
    }

    /**
     * 人工添加过车记录
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto addPassRecord(@FluentValid({PassVehicleRecordAddRequestDtoValidator.class}) PassVehicleRecordAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            SessionParkingInfo currentParking = FundamentalRequestContext.getFundamentalRequestContext().getCurrentParking();
            if (null == currentParking) {
                return resultDto.makeResult(PmsResultEnum.PARKING_NOT_EXIST.getValue(), PmsResultEnum.PARKING_NOT_EXIST.getComment());
            }
            Long parkingId = currentParking.getParkingId();
            ParkingGetRequestDto parkingInfoGetRequestDto = new ParkingGetRequestDto();
            parkingInfoGetRequestDto.setId(parkingId);
            ObjectResultDto<ParkingResultDto> parkingInfoServiceParkInfoById = parkingInfoService.getParkingInfo(parkingInfoGetRequestDto);
            if (parkingInfoServiceParkInfoById.isFailed() || parkingInfoServiceParkInfoById.getData() == null) {
                return resultDto.makeResult(PmsResultEnum.PARKING_NOT_FOUND.getValue(), PmsResultEnum.PARKING_NOT_FOUND.getComment());
            }
            ParkingResultDto parkingInfo = parkingInfoServiceParkInfoById.getData();
            //泊位
            ParkingLotInfoGetByCodeRequestDto parkingLotInfoGetByCodeRequestDto = new ParkingLotInfoGetByCodeRequestDto();
            parkingLotInfoGetByCodeRequestDto.setParkingLotCode(requestDto.getParkingLotCode());
            ObjectResultDto<ParkingLotResultDto> parkingLotById = parkingLotInfoService.getParkingLotByCode(parkingLotInfoGetByCodeRequestDto);
            if (parkingLotById.isFailed() || parkingLotById.getData() == null) {
                return resultDto.makeResult(PmsResultEnum.PARKING_LOT_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_LOT_NOT_FOUND.getComment());
            }
            ParkingLotResultDto parkingLotInfo = parkingLotById.getData();
            //停车类型
            Integer parkingType = null;
            SpecialTypeGetRequestDto specialTypeGetRequestDto = new SpecialTypeGetRequestDto();
            specialTypeGetRequestDto.setPlateNumber(requestDto.getPlateNumber());
            specialTypeGetRequestDto.setParkingId(parkingInfo.getId());
            ObjectResultDto<SpecialVehicleTypeResultDto> specialTypeByPlateNo = specialVehicleService.findSpecialTypeByPlateNo(specialTypeGetRequestDto);
            if (specialTypeByPlateNo.isSuccess() && specialTypeByPlateNo.getData() != null) {
                SpecialVehicleTypeResultDto specialTypeByPlateNoData = specialTypeByPlateNo.getData();
                parkingType = specialTypeByPlateNoData.getSpecialType();
            }
            Integer plateColor = requestDto.getPlateColor();
            String plateNumber = requestDto.getPlateNumber();
            Integer carType = requestDto.getCarType();

            // 是否预约停车
            Boolean isAppoint;
            AppointOrderValidPlateRequestDto appointOrderValidPlateRequestDto = new AppointOrderValidPlateRequestDto();
            appointOrderValidPlateRequestDto.setParkingId(parkingInfo.getId());
            appointOrderValidPlateRequestDto.setPlateNumber(requestDto.getPlateNumber());
            appointOrderValidPlateRequestDto.setDeadlineTime(requestDto.getPassTime());
            ObjectResultDto<ParkingAppointmentOrderResultDto> appointmentOrderResult =
                    parkingAppointmentOrderService.getValidOrderList(appointOrderValidPlateRequestDto);
            ParkingAppointmentOrderResultDto appointmentOrder = null;
            if (appointmentOrderResult.isSuccess() && appointmentOrderResult.getData() != null) {
                isAppoint = Boolean.TRUE;
                //更新预约车辆已入场
                appointmentOrder = appointmentOrderResult.getData();
                AppointOrderUpdateEnterRequestDto appointOrderEnterRequestDto = new AppointOrderUpdateEnterRequestDto();
                appointOrderEnterRequestDto.setOrderNo(appointmentOrder.getOrderNo());
                appointOrderEnterRequestDto.setEnterTime(requestDto.getPassTime());
                updatePassVehicleRecordLock(appointOrderEnterRequestDto, getEnterAppointLockKey(appointmentOrder.getOrderNo()));
            } else {
                isAppoint = Boolean.FALSE;
            }
            //预约收费规则
            ParkingAppointInfoResultDto appointmentInfo = null;
            ParkingAppointInfoGetByParkingIdRequestDto parkAppointInfoGetByParkingIdRequestDto = new ParkingAppointInfoGetByParkingIdRequestDto();
            parkAppointInfoGetByParkingIdRequestDto.setParkingId(parkingInfo.getId());
            ObjectResultDto<ParkingAppointInfoResultDto> parkingAppointmentInfoByParkingId = parkingAppointmentInfoService.getAppointmentInfoByParkingId(parkAppointInfoGetByParkingIdRequestDto);
            if (parkingAppointmentInfoByParkingId.isSuccess() || parkingAppointmentInfoByParkingId.getData() != null) {
                appointmentInfo = parkingAppointmentInfoByParkingId.getData();
            }
            //收费信息
            ParkingChargeInfoGetByParkingIdRequestDto parkingChargeInfoGetByParkingIdRequestDto = new ParkingChargeInfoGetByParkingIdRequestDto();
            parkingChargeInfoGetByParkingIdRequestDto.setParkingId(parkingInfo.getId());
            ObjectResultDto<ParkingChargeInfoResultDto> parkChargeInfoByParkingId = parkingChargeInfoService.getParkChargeInfoByParkingId(parkingChargeInfoGetByParkingIdRequestDto);
            if (parkChargeInfoByParkingId.isFailed() || parkChargeInfoByParkingId.getData() == null) {
                return resultDto.makeResult(IntegrationResultEnum.PARKING_CHARGE_INFO_EMPTY.getValue(),
                        IntegrationResultEnum.PARKING_CHARGE_INFO_EMPTY.getComment());
            }
            ParkingChargeInfoResultDto chargeInfo = parkChargeInfoByParkingId.getData();
            //添加过车记录
            PassingVehicleRecordCreateRequestDto passRecord = createPassRecord(plateNumber, plateColor, carType, parkingInfo, parkingLotInfo);
            passRecord.setPassingType(PassingTypeEnum.ENTRY.getValue());
            passRecord.setPassTime(requestDto.getPassTime());
            passRecord.setDataSource(PassingVehicleDataSourceEnum.MANUAL.getValue());
            if (null != parkingType) {
                passRecord.setParkingType(parkingType);
            }
            passingVehicleRecordService.savePassRecord(passRecord);
            //查找是否有异常的过车记录
            PassVehicleGetExceptionRequestDto passingVehicleExceptionRecordResultDto = new PassVehicleGetExceptionRequestDto();
            passingVehicleExceptionRecordResultDto.setParkingId(parkingInfo.getId());
            passingVehicleExceptionRecordResultDto.setPlateNumber(plateNumber);
            passingVehicleExceptionRecordResultDto.setParkingLotId(parkingLotInfo.getId());
            passingVehicleExceptionRecordResultDto.setPassingType(PassingTypeEnum.EXIT.getValue());
            passingVehicleExceptionRecordResultDto.setPassTime(requestDto.getPassTime());
            ListResultDto<PassingVehicleRecordResultDto> outPassExceptionDtos = passingVehicleRecordService.getExceptionPassRecord(passingVehicleExceptionRecordResultDto);
            if (outPassExceptionDtos.isSuccess() && CollectionUtils.isNotEmpty(outPassExceptionDtos.getItems())) {
                //存在异常出车
                PassingVehicleRecordResultDto passingVehicleExceptionRecord = outPassExceptionDtos.getItems().get(0);
                if (requestDto.getPassTime().before(passingVehicleExceptionRecord.getPassTime())) {
                    //生成停车记录
                    ParkingRecordAddRequestDto parkingRecordAddRequestDto = buildParkingRecord(requestDto, parkingInfo, parkingType, parkingLotInfo, isAppoint, appointmentOrder,
                            appointmentInfo, chargeInfo, passRecord);
                    parkingRecordAddRequestDto.setEndTime(passingVehicleExceptionRecord.getPassTime());
                    parkingRecordAddRequestDto.setPeriodLength((int) DateTimeUtils.getSecondDiff(requestDto.getPassTime(),
                            passingVehicleExceptionRecord.getPassTime()));
                    parkingRecordAddRequestDto.setOutRecordNo(passingVehicleExceptionRecord.getPassingNo());
                    ObjectResultDto<SaveParkingRecordResultDto> saveParkingRecordResult = parkingRecordService.saveParkingRecord(parkingRecordAddRequestDto);
                    //生成订单
                    ParkingOrderInsertRequestDto parkingOrderSaveRequestDto = new ParkingOrderInsertRequestDto();
                    parkingOrderSaveRequestDto.setOrderNo(String.valueOf(this.idService.genId()));
                    parkingOrderSaveRequestDto.setRecordNo(parkingRecordAddRequestDto.getRecordNo());
                    parkingOrderSaveRequestDto.setParkingId(parkingInfo.getId());
                    parkingOrderSaveRequestDto.setChargeInfoId(chargeInfo.getId());
                    parkingOrderSaveRequestDto.setParkingName(parkingInfo.getFullName());
                    parkingOrderSaveRequestDto.setParkingLotId(parkingLotInfo.getId());
                    parkingOrderSaveRequestDto.setParkingLotNumber(parkingLotInfo.getNumber());
                    parkingOrderSaveRequestDto.setPlateNumber(plateNumber);
                    parkingOrderSaveRequestDto.setPlateColor(plateColor);
                    parkingOrderSaveRequestDto.setCarStyle(carType);
                    parkingOrderSaveRequestDto.setStartTime(requestDto.getPassTime());
                    parkingOrderSaveRequestDto.setEndTime(passingVehicleExceptionRecord.getPassTime());
                    parkingOrderSaveRequestDto.setArtificial(Boolean.TRUE);
                    parkingOrderSaveRequestDto.setParkingLength(DateTimeUtils.getSecondDiff(requestDto.getPassTime(),
                            passingVehicleExceptionRecord.getPassTime()));
                    parkingOrderSaveRequestDto.setPayStatus(PayStatusEnum.CREATED.getValue());
                    //计算金额
                    Integer amount = calculateOrder(requestDto.getPassTime(),
                            passingVehicleExceptionRecord.getPassTime(), carType,
                            parkingInfo.getId(), parkingLotInfo.getId(), parkingOrderSaveRequestDto);
                    parkingOrderSaveRequestDto.setSettle(Boolean.TRUE);
                    parkingOrderSaveRequestDto.setSettleAmount(amount);
                    parkingOrderSaveRequestDto.setSettleTime(DateUtils.now());
                    ObjectResultDto<ParkingOrderCreateResultDto> parkingOrderSaveResultDtoObjectResultDto = parkingOrderManagerService.createParkingOrder(parkingOrderSaveRequestDto);
                    pushNewOrder(parkingOrderSaveResultDtoObjectResultDto.getData().getId(),
                            parkingOrderSaveRequestDto.getOrderNo(), parkingInfo.getFullName(), requestDto.getPlateColor(),
                            requestDto.getPlateNumber(), requestDto.getPassTime());
                    //巡检记录
                    InspectRecordSaveRequestDto inspectRecord = createInspectRecord(plateNumber, plateColor, requestDto.getPassTime(), carType, parkingType, parkingInfo,
                            parkingLotInfo.getId(), saveParkingRecordResult.getData().getId(),
                            parkingRecordAddRequestDto.getRecordNo());
                    inspectRecord.setEndTime(passingVehicleExceptionRecord.getPassTime());
                    inspectRecordService.saveInspectRecord(inspectRecord);
                }
            } else {
                //添加在场车辆
                ParkingVehicleRecordSaveRequestDto parkingVehicleRecordSaveRequestDto = new ParkingVehicleRecordSaveRequestDto();
                parkingVehicleRecordSaveRequestDto.setParkingId(parkingInfo.getId());
                parkingVehicleRecordSaveRequestDto.setParkingName(parkingInfo.getFullName());
                parkingVehicleRecordSaveRequestDto.setParkingCode(parkingInfo.getCode());
                parkingVehicleRecordSaveRequestDto.setParkingLotId(parkingLotInfo.getId());
                parkingVehicleRecordSaveRequestDto.setParkingLotNumber(parkingLotInfo.getCode());
                parkingVehicleRecordSaveRequestDto.setParkingLotNumber(parkingLotInfo.getNumber());
                parkingVehicleRecordSaveRequestDto.setIntoRecordNo(passRecord.getPassingNo());
                parkingVehicleRecordSaveRequestDto.setPlateNumber(plateNumber);
                parkingVehicleRecordSaveRequestDto.setPlateColor(plateColor);
                parkingVehicleRecordSaveRequestDto.setCarType(carType);
                parkingVehicleRecordSaveRequestDto.setStartTime(requestDto.getPassTime());
                parkingVehicleRecordService.saveParkingVehicleRecord(parkingVehicleRecordSaveRequestDto);
                //车位状态变更
                ParkingLotDecreaseRequestDto parkingLotIncreaseRequestDto = new ParkingLotDecreaseRequestDto();
                parkingLotIncreaseRequestDto.setTenantId(parkingInfo.getTenantId());
                parkingLotIncreaseRequestDto.setParkingId(parkingInfo.getId());
                parkingLotIncreaseRequestDto.setParkingLotId(parkingLotInfo.getId());
                decreaseParkingLotWithLock(parkingLotIncreaseRequestDto, getParkLotStatusLockKey(parkingInfo.getCode()));
                if (isAppoint) {
                    //更新预约车位数量
                    ParkingLotAppointUpdateRequestDto parkingLotAppointUpdateRequestDto = new ParkingLotAppointUpdateRequestDto();
                    parkingLotAppointUpdateRequestDto.setParkingId(parkingInfo.getId());
                    parkingLotAppointUpdateRequestDto.setIncrease(Boolean.TRUE);
                    updateParkingLotAppointWithLock(parkingLotAppointUpdateRequestDto, getParkingLotAppointLockKey(parkingInfo.getCode()));
                }
                //生成停车记录
                ParkingRecordAddRequestDto parkingRecordAddRequestDto = buildParkingRecord(requestDto, parkingInfo, parkingType, parkingLotInfo, isAppoint, appointmentOrder, appointmentInfo, chargeInfo, passRecord);
                ObjectResultDto<SaveParkingRecordResultDto> saveParkingRecordResult = parkingRecordService.saveParkingRecord(parkingRecordAddRequestDto);
                //生成停车账单
                ParkingOrderInsertRequestDto parkingOrderSaveRequestDto = buildParkingOrder(requestDto, parkingInfo, parkingLotInfo, isAppoint, appointmentOrder, chargeInfo, parkingRecordAddRequestDto);
                ObjectResultDto<ParkingOrderCreateResultDto> parkingOrderSaveResultDtoObjectResultDto =
                        parkingOrderManagerService.createParkingOrder(parkingOrderSaveRequestDto);
                //发送停车入场消息
                ParkingEnterPushMessageRequestDto parkingEnterPushMessageRequestDto = new ParkingEnterPushMessageRequestDto();
                parkingEnterPushMessageRequestDto.setOrderId(parkingOrderSaveResultDtoObjectResultDto.getData().getId());
                parkingEnterPushMessageRequestDto.setOrderNo(parkingOrderSaveRequestDto.getOrderNo());
                parkingEnterPushMessageRequestDto.setParkingName(parkingInfo.getFullName());
                parkingEnterPushMessageRequestDto.setPlateColor(plateColor);
                parkingEnterPushMessageRequestDto.setPlateNumber(plateNumber);
                parkingEnterPushMessageRequestDto.setStartTime(requestDto.getPassTime());
                messageSendIntegrationService.sendParkingEnterPushMessage(parkingEnterPushMessageRequestDto);
                //过车记录推送巡检
                PassRecordMessageSendRequestDto passRecordMessageSendRequestDto = modelMapper.map(passRecord,
                        PassRecordMessageSendRequestDto.class);
                passRecordMessageSendRequestDto.setParkingLotCode(parkingLotInfo.getCode());
                messageSendIntegrationService.sendInspectEnterPushMessage(passRecordMessageSendRequestDto);
                //过车巡检消息发送
                PassRecordNotifySendRequestDto passRecordNotifySendRequestDto = modelMapper.map(passRecord, PassRecordNotifySendRequestDto.class);
                passRecordNotifySendRequestDto.setParkingLotCode(parkingLotInfo.getCode());
                messageSendIntegrationService.sendPassRecordNotify(passRecordNotifySendRequestDto);
                //巡检记录
                InspectRecordSaveRequestDto inspectRecord = createInspectRecord(plateNumber, plateColor, requestDto.getPassTime(), carType, parkingType, parkingInfo,
                        parkingLotInfo.getId(), saveParkingRecordResult.getData().getId(),
                        parkingRecordAddRequestDto.getRecordNo());
                inspectRecordService.saveInspectRecord(inspectRecord);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("人工添加过车记录失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 构建停车订单
     *
     * @param requestDto
     * @param parkingInfo
     * @param parkingLotInfo
     * @param isAppoint
     * @param appointmentOrder
     * @param chargeInfo
     * @param parkingRecordAddRequestDto
     * @return
     */
    private ParkingOrderInsertRequestDto buildParkingOrder(PassVehicleRecordAddRequestDto
                                                                   requestDto, ParkingResultDto parkingInfo, ParkingLotResultDto parkingLotInfo, Boolean
                                                                   isAppoint, ParkingAppointmentOrderResultDto appointmentOrder, ParkingChargeInfoResultDto
                                                                   chargeInfo, ParkingRecordAddRequestDto parkingRecordAddRequestDto) {
        ParkingOrderInsertRequestDto parkingOrderSaveRequestDto = new ParkingOrderInsertRequestDto();
        parkingOrderSaveRequestDto.setRecordNo(parkingRecordAddRequestDto.getRecordNo());
        parkingOrderSaveRequestDto.setOrderNo(String.valueOf(this.idService.genId()));
        parkingOrderSaveRequestDto.setParkingId(parkingInfo.getId());
        parkingOrderSaveRequestDto.setChargeInfoId(chargeInfo.getId());
        parkingOrderSaveRequestDto.setParkingName(parkingInfo.getFullName());
        parkingOrderSaveRequestDto.setParkingLotId(parkingLotInfo.getId());
        parkingOrderSaveRequestDto.setParkingLotNumber(parkingLotInfo.getNumber());
        parkingOrderSaveRequestDto.setPlateNumber(requestDto.getPlateNumber());
        parkingOrderSaveRequestDto.setPlateColor(requestDto.getPlateColor());
        parkingOrderSaveRequestDto.setCarStyle(requestDto.getCarType());
        parkingOrderSaveRequestDto.setStartTime(requestDto.getPassTime());
        parkingOrderSaveRequestDto.setEndTime(DateTimeUtils.getDateMax());
        parkingOrderSaveRequestDto.setArtificial(Boolean.TRUE);
        parkingOrderSaveRequestDto.setAppointed(isAppoint);
        if (isAppoint) {
            parkingOrderSaveRequestDto.setAppointOrderNo(appointmentOrder.getOrderNo());
        }
        parkingOrderSaveRequestDto.setSettle(Boolean.FALSE);
        return parkingOrderSaveRequestDto;
    }

    private ParkingRecordAddRequestDto buildParkingRecord(PassVehicleRecordAddRequestDto
                                                                  requestDto, ParkingResultDto parkingInfo, Integer parkingType, ParkingLotResultDto parkingLotInfo, Boolean
                                                                  isAppoint, ParkingAppointmentOrderResultDto appointmentOrder, ParkingAppointInfoResultDto
                                                                  appointmentInfo, ParkingChargeInfoResultDto chargeInfo, PassingVehicleRecordCreateRequestDto passRecord) {
        ParkingRecordAddRequestDto parkingRecordAddRequestDto = new ParkingRecordAddRequestDto();
        parkingRecordAddRequestDto.setRecordNo(String.valueOf(this.idService.genId()));
        parkingRecordAddRequestDto.setParkingId(parkingInfo.getId());
        parkingRecordAddRequestDto.setParkingName(parkingInfo.getFullName());
        parkingRecordAddRequestDto.setParkingCode(parkingInfo.getCode());
        parkingRecordAddRequestDto.setParkingLotId(parkingLotInfo.getId());
        parkingRecordAddRequestDto.setParkingLotCode(parkingLotInfo.getCode());
        parkingRecordAddRequestDto.setParkingLotNumber(parkingLotInfo.getNumber());
        parkingRecordAddRequestDto.setIntoRecordNo(passRecord.getPassingNo());
        parkingRecordAddRequestDto.setPlateNumber(requestDto.getPlateNumber());
        parkingRecordAddRequestDto.setPlateColor(requestDto.getPlateColor());
        parkingRecordAddRequestDto.setCarType(requestDto.getCarType());
        if (parkingType != null) {
            parkingRecordAddRequestDto.setParkingType(parkingType);
        }
        parkingRecordAddRequestDto.setStartTime(requestDto.getPassTime());
        parkingRecordAddRequestDto.setAppointed(isAppoint);
        if (isAppoint) {
            parkingRecordAddRequestDto.setAppointOrderNo(appointmentOrder.getOrderNo());
            if (appointmentInfo != null) {
                parkingRecordAddRequestDto.setAppointRuleId(appointmentInfo.getId());
            }
        }
        parkingRecordAddRequestDto.setChargeId(chargeInfo.getId());
        return parkingRecordAddRequestDto;
    }

    /**
     * 新账单推送
     *
     * @param orderId
     * @param orderNo
     * @param parkingName
     * @param plateColor
     * @param plateNumber
     * @param startTime
     */
    private void pushNewOrder(Long orderId, String orderNo, String parkingName, Integer plateColor, String
            plateNumber, Date startTime) {
        ParkingOrderPushMessageRequestDto parkingOrderPushMessageRequestDto = new ParkingOrderPushMessageRequestDto();
        parkingOrderPushMessageRequestDto.setOrderId(orderId);
        parkingOrderPushMessageRequestDto.setParkingName(parkingName);
        parkingOrderPushMessageRequestDto.setPlateColor(plateColor);
        parkingOrderPushMessageRequestDto.setPlateNumber(plateNumber);
        parkingOrderPushMessageRequestDto.setStartTime(startTime);
        parkingOrderPushMessageRequestDto.setOrderNo(orderNo);
        messageSendIntegrationService.sendParkingOrderPushMessage(parkingOrderPushMessageRequestDto);
    }

    /**
     * 创建订单并结算
     *
     * @param requestDto
     * @param parkingInfo
     * @param parkingLotInfo
     * @param recordNo
     * @param isAppoint
     * @param appointmentOrderNo
     * @param chargeInfoId
     */
    private void createOrderAndSettle(InspectIntoRecordRequestDto requestDto, ParkingResultDto parkingInfo,
                                      ParkingLotResultDto parkingLotInfo, String recordNo, Boolean isAppoint,
                                      ParkingAppointmentOrderResultDto appointmentOrderNo, Long chargeInfoId, Integer dataType) {
        ParkingOrderInsertRequestDto parkingOrderSaveRequestDto = createIntoInspectOrder(requestDto, parkingInfo, parkingLotInfo);
        createAnyOrder(parkingInfo, dataType, parkingOrderSaveRequestDto);
        parkingOrderSaveRequestDto.setAppointed(isAppoint);
        if (isAppoint) {
            parkingOrderSaveRequestDto.setAppointOrderNo(appointmentOrderNo.getOrderNo());
        }
        parkingOrderSaveRequestDto.setRecordNo(recordNo);
        parkingOrderSaveRequestDto.setChargeInfoId(chargeInfoId);
        parkingOrderSaveRequestDto.setEndTime(requestDto.getEndTime());
        parkingOrderSaveRequestDto.setArtificial(Boolean.TRUE);
        //结算订单
        Integer amount = calculateOrder(requestDto.getStartTime(), requestDto.getEndTime(), requestDto.getCarType(),
                parkingInfo.getId(),
                parkingLotInfo.getId(),
                parkingOrderSaveRequestDto);
        parkingOrderSaveRequestDto.setSettle(Boolean.TRUE);
        parkingOrderSaveRequestDto.setSettleAmount(amount);
        parkingOrderSaveRequestDto.setSettleTime(DateUtils.now());
        ObjectResultDto<ParkingOrderCreateResultDto> parkingOrderSaveResultDtoObjectResultDto =
                parkingOrderManagerService.createParkingOrder(parkingOrderSaveRequestDto);
        pushNewOrder(parkingOrderSaveResultDtoObjectResultDto.getData().getId(),
                parkingOrderSaveRequestDto.getOrderNo(), parkingInfo.getFullName(), requestDto.getPlateColor(),
                requestDto.getPlateNumber(), requestDto.getStartTime());
    }

    /**
     * 计算订单
     *
     * @param parkingLotId
     * @param parkingOrderSaveRequestDto
     * @return
     */
    private Integer calculateOrder(Date startTime, Date endTime, Integer carType, Long parkingId, Long
            parkingLotId, ParkingOrderInsertRequestDto parkingOrderSaveRequestDto) {
        //结算订单
        ParkingAmountCalculateRequestDto calculateAmountRequestDto = new ParkingAmountCalculateRequestDto();
        calculateAmountRequestDto.setParkingId(parkingId);
        calculateAmountRequestDto.setStartTime(startTime);
        calculateAmountRequestDto.setEndTime(endTime);
        calculateAmountRequestDto.setCarStyle(carType);
        ObjectResultDto<CalculateAmountResultDto> calculateAmount = calculateIntegrationService.calculateAmount(calculateAmountRequestDto);
        if (null == calculateAmount.getData() || calculateAmount.isFailed()) {
            log.error("停车订单{}结算金额计算失败:{}", parkingOrderSaveRequestDto.getOrderNo(), calculateAmount.getMessage());
            return null;
        }
        Date now = DateUtils.now();
        CalculateAmountResultDto calculateAmountData = calculateAmount.getData();
        parkingOrderSaveRequestDto.setSettle(Boolean.TRUE);
        parkingOrderSaveRequestDto.setSettleTime(now);
        Integer amount = calculateAmountData.getAmount();
        //目前结算金额同应付金额
        parkingOrderSaveRequestDto.setSettleAmount(amount);
        parkingOrderSaveRequestDto.setPayableAmount(amount);
        //收费时长
        parkingOrderSaveRequestDto.setChargeLength(calculateAmountData.getChargeDuration());
        //免费时长
        parkingOrderSaveRequestDto.setFreeLength(calculateAmountData.getFreeDuration());
        //停车时长
        parkingOrderSaveRequestDto.setParkingLength(DateTimeUtils.getSecondDiff(parkingOrderSaveRequestDto.getStartTime(), endTime));
        //订单金额为0，无需支付
        if (calculateAmountData.getAmount().compareTo(0) <= 0) {
            parkingOrderSaveRequestDto.setNeedPay(Boolean.FALSE);
            parkingOrderSaveRequestDto.setFreePayReason("限时免费");
            //自动支付完成
            if (!parkingOrderSaveRequestDto.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
                parkingOrderSaveRequestDto.setPayable(Boolean.FALSE);
                parkingOrderSaveRequestDto.setActualPayAmount(0);
                parkingOrderSaveRequestDto.setPayStatus(PayStatusEnum.PAY_SUCCESS.getValue());
                parkingOrderSaveRequestDto.setPayTime(now);
            }
        } else {
            //订单金额大于0
            parkingOrderSaveRequestDto.setNeedPay(Boolean.TRUE);
            parkingOrderSaveRequestDto.setFreePayReason("");
            if (!parkingOrderSaveRequestDto.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
                parkingOrderSaveRequestDto.setPayable(Boolean.TRUE);
            }
        }
        return amount;
    }

    /**
     * 创建过车记录
     *
     * @param parkingInfo
     * @param parkingLotInfo
     * @return
     */
    private PassingVehicleRecordCreateRequestDto createPassRecord(String plateNumber, Integer plateColor,
                                                                  Integer carType,
                                                                  ParkingResultDto parkingInfo,
                                                                  ParkingLotResultDto parkingLotInfo) {
        PassingVehicleRecordCreateRequestDto passingVehicleRecordAddRequestDto = new PassingVehicleRecordCreateRequestDto();
        passingVehicleRecordAddRequestDto.setPassingNo(String.valueOf(this.idService.genId()));
        passingVehicleRecordAddRequestDto.setPassingUuid(UUIDTool.getUUID());
        passingVehicleRecordAddRequestDto.setParkingId(parkingInfo.getId());
        passingVehicleRecordAddRequestDto.setParkingName(parkingInfo.getFullName());
        passingVehicleRecordAddRequestDto.setParkingCode(parkingInfo.getCode());
        passingVehicleRecordAddRequestDto.setParkingLotCode(parkingLotInfo.getCode());
        passingVehicleRecordAddRequestDto.setParkingLotNumber(parkingLotInfo.getNumber());
        passingVehicleRecordAddRequestDto.setParkingLotId(parkingLotInfo.getId());
        passingVehicleRecordAddRequestDto.setGateId(parkingLotInfo.getGarageId());
        passingVehicleRecordAddRequestDto.setPlateNumber(plateNumber);
        passingVehicleRecordAddRequestDto.setPlateNoExist(Boolean.TRUE);
        passingVehicleRecordAddRequestDto.setPlateColor(plateColor);
        passingVehicleRecordAddRequestDto.setCarType(carType);
        return passingVehicleRecordAddRequestDto;
    }

    /**
     * 生成巡检记录
     *
     * @return
     */
    private InspectRecordSaveRequestDto createInspectRecord(String plateNumber, Integer plateColor, Date startTime,
                                                            Integer catType, Integer parkingType, ParkingResultDto parkingInfo, Long parkingLotId,
                                                            Long parkingRecordId, String parkingRecordNo) {
        InspectRecordSaveRequestDto inspectRecordSaveRequestDto = new InspectRecordSaveRequestDto();
        inspectRecordSaveRequestDto.setRecordId(parkingRecordId);
        inspectRecordSaveRequestDto.setRecordNo(parkingRecordNo);
        inspectRecordSaveRequestDto.setParkingId(parkingInfo.getId());
        inspectRecordSaveRequestDto.setParkingLotId(parkingLotId);
        inspectRecordSaveRequestDto.setPlateNumber(plateNumber);
        inspectRecordSaveRequestDto.setPlateColor(plateColor);
        inspectRecordSaveRequestDto.setCarType(catType);
        inspectRecordSaveRequestDto.setParkingType(parkingType);
        inspectRecordSaveRequestDto.setStartTime(startTime);
        inspectRecordSaveRequestDto.setInspectUserId(FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity().getUserId());
        inspectRecordSaveRequestDto.setInspectTime(DateUtils.now());
        inspectRecordSaveRequestDto.setInspectResult(InspectOperateResultEnum.PROCESSED.getValue());
        return inspectRecordSaveRequestDto;
    }

    /**
     * 生成地磁停车订单
     *
     * @param requestDto
     * @param parkingInfo
     * @param parkingLotInfo
     * @return
     */
    private ParkingOrderInsertRequestDto createIntoInspectOrder(InspectIntoRecordRequestDto requestDto,
                                                                ParkingResultDto parkingInfo, ParkingLotResultDto parkingLotInfo) {
        String parkingOrderNo = String.valueOf(this.idService.genId());
        ParkingOrderInsertRequestDto parkingOrderSaveRequestDto = new ParkingOrderInsertRequestDto();
        parkingOrderSaveRequestDto.setOrderNo(parkingOrderNo);
        parkingOrderSaveRequestDto.setParkingId(parkingInfo.getId());
        parkingOrderSaveRequestDto.setParkingLotId(parkingLotInfo.getId());
        parkingOrderSaveRequestDto.setParkingLotNumber(parkingLotInfo.getNumber());
        parkingOrderSaveRequestDto.setPlateNumber(requestDto.getPlateNumber());
        parkingOrderSaveRequestDto.setPlateColor(requestDto.getPlateColor());
        parkingOrderSaveRequestDto.setCarStyle(requestDto.getCarType());
        parkingOrderSaveRequestDto.setStartTime(requestDto.getStartTime());
        parkingOrderSaveRequestDto.setEndTime(DateTimeUtils.getDateMax());
        parkingOrderSaveRequestDto.setArtificial(Boolean.FALSE);
        parkingOrderSaveRequestDto.setParkingLength(0L);
        parkingOrderSaveRequestDto.setFreeLength(0L);
        parkingOrderSaveRequestDto.setChargeLength(0L);
        parkingOrderSaveRequestDto.setChargeMode(parkingInfo.getChargeMode());
        parkingOrderSaveRequestDto.setStatus(ParkingStatusEnum.PARKING.getValue());

        parkingOrderSaveRequestDto.setSettle(Boolean.FALSE);
        ParkingCurrentChargeInfoRequestDto parkingChargeInfoGetRequestDto = new ParkingCurrentChargeInfoRequestDto();
        parkingChargeInfoGetRequestDto.setParkingId(parkingInfo.getId());
        parkingChargeInfoGetRequestDto.setBaseTime(requestDto.getStartTime());
        parkingChargeInfoGetRequestDto.setTenantId(parkingInfo.getTenantId());
        ObjectResultDto<ParkingCurrentChargeInfoResultDto>
                currentParkingChargeRule = parkChargeRuleIntegrationService.getParkingChargeRuleCurrentInfo(parkingChargeInfoGetRequestDto);
        if (currentParkingChargeRule.isSuccess() && currentParkingChargeRule.getData() != null) {
            ParkingCurrentChargeInfoResultDto parkingChargeInfoResultDto = currentParkingChargeRule.getData();
            parkingOrderSaveRequestDto.setLimitFree(parkingChargeInfoResultDto.getNowFree());
        } else {
            parkingOrderSaveRequestDto.setLimitFree(false);
        }
        parkingOrderSaveRequestDto.setPayStatus(PayStatusEnum.CREATED.getValue());
        return parkingOrderSaveRequestDto;
    }

    /**
     * 更新停车账单时费用结算
     *
     * @param parkingOrder   parkingRecord
     * @param parkingInfo    parkingInfo
     * @param parkingLotInfo parkingLotInfo
     * @return
     */
    private void updateOrderAndSettleOrder(ParkingOrderResultDto parkingOrder, ParkingResultDto parkingInfo,
                                           ParkingLotResultDto parkingLotInfo, Date endTime, InspectParkingOrderUpdateRequestDto parkingOrderUpdate) {

        //如果已经结算,则不再结算
        if (parkingOrder.getSettle()) {
            return;
        }
        //如果未结算计算结算费用
        parkingOrderUpdate.setParkingLotId(parkingLotInfo.getId());
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
        calculateAmountRequestDto.setEndTime(endTime);
        calculateAmountRequestDto.setCarStyle(parkingOrder.getCarStyle());
        ObjectResultDto<CalculateAmountResultDto> calculateAmount = calculateIntegrationService.calculateAmount(calculateAmountRequestDto);
        if (null == calculateAmount.getData() || calculateAmount.isFailed()) {
            log.error("停车订单{}结算金额计算失败:{}", parkingOrder.getOrderNo(), calculateAmount.getMessage());
            return;
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
        parkingOrderUpdate.setParkingLength(DateTimeUtils.getSecondDiff(parkingOrder.getStartTime(), endTime));
        //根据停车场id获取停车场收费信息
        ParkingChargeInfoGetByParkingIdRequestDto parkingChargeInfoGetByParkingIdRequestDto = new ParkingChargeInfoGetByParkingIdRequestDto();
        parkingChargeInfoGetByParkingIdRequestDto.setParkingId(parkingInfo.getId());
        ObjectResultDto<ParkingChargeInfoResultDto> parkChargeInfoByParkingId = parkingChargeInfoService.getParkChargeInfoByParkingId(parkingChargeInfoGetByParkingIdRequestDto);
        if (parkChargeInfoByParkingId.isSuccess() && parkChargeInfoByParkingId.getData() != null) {
            ParkingChargeInfoResultDto parkingChargeInfoResultDto = parkChargeInfoByParkingId.getData();
            //收费详情
            parkingOrderUpdate.setChargeInfoId(parkingChargeInfoResultDto.getId());
        }
        //订单金额为0，无需支付
        if (calculateAmountData.getAmount().compareTo(0) <= 0) {
            parkingOrderUpdate.setNeedPay(Boolean.FALSE);
            parkingOrderUpdate.setFreePayReason("限时免费");
            //自动支付完成
            if (!parkingOrder.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
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
     * 获取过车记录锁
     *
     * @param passNo
     * @return
     */
    private String getUpdatePassVehicleRecordLockKey(String passNo) {
        return "lock:zoeeasy.inspect.update.pass_vehicle_record" + passNo;
    }

    /**
     * 获取入车更新预约订单
     *
     * @param appointOrderNo
     * @return
     */
    private String getEnterAppointLockKey(String appointOrderNo) {
        return "lock:zoeeasy.inspect.update.enter_appoint_order" + appointOrderNo;
    }

    /**
     * 在停车辆更新
     *
     * @param passNo
     * @return
     */
    private String getParkVehicleRecordLockKey(String passNo) {
        return "lock:zoeeasy.inspect.update.park_vehicle_record" + passNo;
    }

    /**
     * 停车记录更新
     *
     * @param recordNo
     * @return
     */
    private String getParkRecordLockKey(String recordNo) {
        return "lock:zoeeasy.inspect.update.park_record" + recordNo;
    }

    /**
     * 停车订单
     *
     * @param orderNo
     * @return
     */
    private String getParkOrderLockKey(String orderNo) {
        return "lock:zoeeasy.inspect.update.park_order" + orderNo;
    }

    /**
     * 停车记录更新
     *
     * @param parkLotId
     * @return
     */
    private String getParkLotStatusLockKey(String parkLotId) {
        return "lock:zoeeasy.inspect.update.park_lot_status" + parkLotId;
    }

    private String getParkingLotAppointLockKey(String parkCode) {
        return new StringBuilder().append("lock:zoeeasy.cloud").
                append("update_appoint_lot").append("_").
                append(parkCode).toString();
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
                passingVehicleRecordService.updatePassVehicleRecord(passingRecordUpdateRequestDto);
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
     * 更新停车记录
     *
     * @param requestDto
     * @param parkRecordNo
     * @return
     */
    private Integer updateParkingRecordWithLock(ParkingRecordUpdateIntegrationRequestDto requestDto, String parkRecordNo) {
        LockInfo lockInfo = new LockInfo();
        lockInfo.setType(LockType.Fair);
        lockInfo.setName(parkRecordNo);
        lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
        lockInfo.setLeaseTime(LockInfo.DEFAULT_LOCK_LEASE_TIME);
        Lock lock = lockFactory.getLock(lockInfo);
        boolean lockAcquired = false;
        try {
            lockAcquired = lock.acquire();
            if (lockAcquired) {
                parkingRecordService.updateParkingRecord(requestDto);
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
     * 更新停车记录
     *
     * @param requestDto
     * @param orderNo
     * @return
     */
    private Integer updateParkOrderLock(InspectParkingOrderUpdateRequestDto requestDto, String orderNo) {
        LockInfo lockInfo = new LockInfo();
        lockInfo.setType(LockType.Fair);
        lockInfo.setName(orderNo);
        lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
        lockInfo.setLeaseTime(LockInfo.DEFAULT_LOCK_LEASE_TIME);
        Lock lock = lockFactory.getLock(lockInfo);
        boolean lockAcquired = false;
        try {
            lockAcquired = lock.acquire();
            if (lockAcquired) {
                parkingOrderManagerService.update(requestDto);
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
     * 更新过车记录分布式锁
     *
     * @param appointOrderUpdateEnterRequestDto passingRecordUpdateRequestDto
     * @param appointOrderNo                    passRecordLockKey
     * @return Integer
     */
    private Integer updatePassVehicleRecordLock(AppointOrderUpdateEnterRequestDto
                                                        appointOrderUpdateEnterRequestDto, String appointOrderNo) {
        LockInfo lockInfo = new LockInfo();
        lockInfo.setType(LockType.Fair);
        lockInfo.setName(appointOrderNo);
        lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
        lockInfo.setLeaseTime(LockInfo.DEFAULT_LOCK_LEASE_TIME);
        Lock lock = lockFactory.getLock(lockInfo);
        boolean lockAcquired = false;
        try {
            lockAcquired = lock.acquire();
            if (lockAcquired) {
                ResultDto appointOrderEnterResult = parkingAppointmentOrderService.updateEnterAppointOrder(appointOrderUpdateEnterRequestDto);
                if (appointOrderEnterResult.isFailed()) {
                    log.error("------[更新预约车辆已入场失败]-----------", JSON.toJSONString(appointOrderUpdateEnterRequestDto));
                }
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
     * 在停车辆更新
     *
     * @param parkingVehicleRecordUpdateRequestDto passingRecordUpdateRequestDto
     * @param passNo                               passRecordLockKey
     * @return Integer
     */
    private Integer updateParkVehicleRecordLock(ParkingVehicleRecordUpdateRequestDto
                                                        parkingVehicleRecordUpdateRequestDto, String passNo) {
        LockInfo lockInfo = new LockInfo();
        lockInfo.setType(LockType.Fair);
        lockInfo.setName(passNo);
        lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
        lockInfo.setLeaseTime(LockInfo.DEFAULT_LOCK_LEASE_TIME);
        Lock lock = lockFactory.getLock(lockInfo);
        boolean lockAcquired = false;
        try {
            lockAcquired = lock.acquire();
            if (lockAcquired) {
                parkingVehicleRecordService.updateParkingVehicle(parkingVehicleRecordUpdateRequestDto);
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
     * @param
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
                parkingLotStatusService.decreaseParkingLotAvailable(parkingLotDecreaseRequestDto);
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
     * @param
     * @param parkingLotDecreaseRequestDto parkingLotDecreaseRequestDto
     * @return
     */
    private Integer updateParkingLotAppointWithLock(ParkingLotAppointUpdateRequestDto
                                                            parkingLotDecreaseRequestDto,
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
                platformParkingInfoService.updateLotAppointAvailable(parkingLotDecreaseRequestDto);
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

    private AnyParkingOrderPlaceResultDto createAnyParkingOrder(AnyParkingOrderPlaceRequestDto requestDto) {
        AnyParkingOrderPlaceRequestDto anyParkingOrderPlaceRequestDto = new AnyParkingOrderPlaceRequestDto();
        anyParkingOrderPlaceRequestDto.setPlateColor(requestDto.getPlateColor());
        anyParkingOrderPlaceRequestDto.setPlateNumber(requestDto.getPlateNumber());
        anyParkingOrderPlaceRequestDto.setCarType(requestDto.getCarType());
        anyParkingOrderPlaceRequestDto.setParkingId(requestDto.getParkingId());
        anyParkingOrderPlaceRequestDto.setOrderNo(requestDto.getOrderNo());
        AnyParkingOrderPlaceResultDto cloudAddOrderResultDto = null;
        ResultDto objectResultDto = terminateParkingOrderService.placeAynParkingOrder(anyParkingOrderPlaceRequestDto);
        if (objectResultDto.isSuccess()) {
            //保存三方订单到thirdOrder
            AnyParkingOrderSaveRequestDto anyParkingOrderSaveRequestDto = new AnyParkingOrderSaveRequestDto();
            anyParkingOrderSaveRequestDto.setTenantId(requestDto.getTenantId());
            anyParkingOrderSaveRequestDto.setParkingId(requestDto.getParkingId());
            anyParkingOrderSaveRequestDto.setBillNo(cloudAddOrderResultDto.getBillCode());
            anyParkingOrderSaveRequestDto.setCarType(requestDto.getCarType());
            anyParkingOrderSaveRequestDto.setAmount(cloudAddOrderResultDto.getPayAmount());
            anyParkingOrderSaveRequestDto.setOrderNo(requestDto.getOrderNo());
            anyParkingOrderSaveRequestDto.setParkingLength(cloudAddOrderResultDto.getCostTime());
            anyParkingOrderSaveRequestDto.setPlateColor(requestDto.getPlateColor());
            anyParkingOrderSaveRequestDto.setPlateNo(requestDto.getPlateNumber());
            anyParkingOrderSaveRequestDto.setRecordNo(requestDto.getRecordNo());
            thirdParkingOrderService.saveAnyParkingOrder(anyParkingOrderSaveRequestDto);
        }
        return cloudAddOrderResultDto;
    }

    /**
     * 获取巡检异常原因列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getInspectReason(InspectReasonListGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (InspectReasonEnum inspectReasonEnum : InspectReasonEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf(inspectReasonEnum.getValue()), inspectReasonEnum.getComment(), false);
                list.add(comboboxItemDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取巡检异常原因列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

}
