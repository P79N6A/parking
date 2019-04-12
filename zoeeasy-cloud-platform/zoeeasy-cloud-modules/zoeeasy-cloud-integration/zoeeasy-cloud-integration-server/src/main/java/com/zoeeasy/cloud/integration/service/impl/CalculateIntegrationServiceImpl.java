package com.zoeeasy.cloud.integration.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.scapegoat.infrastructure.common.utils.NumberUtils;
import com.scapegoat.infrastructure.common.utils.PlateNumberUtil;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.charge.appointrule.AppointRuleService;
import com.zoeeasy.cloud.charge.appointrule.ParkingAppointRuleService;
import com.zoeeasy.cloud.charge.appointrule.dto.request.AppointRuleGetRequestDto;
import com.zoeeasy.cloud.charge.appointrule.dto.request.ParkingAppointRuleGetByVaildTimeRequestDto;
import com.zoeeasy.cloud.charge.appointrule.dto.result.AppointRuleResultDto;
import com.zoeeasy.cloud.charge.appointrule.dto.result.ParkingAppointRuleViewResultDto;
import com.zoeeasy.cloud.charge.chg.CalculateAmountService;
import com.zoeeasy.cloud.charge.chg.dto.request.CalculateAmountRequestDto;
import com.zoeeasy.cloud.charge.chg.dto.result.CalculateAmountResultDto;
import com.zoeeasy.cloud.charge.chg.dto.result.CalculateAmountViewResultDto;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleInfoViewResultDto;
import com.zoeeasy.cloud.charge.cts.ChargeConstant;
import com.zoeeasy.cloud.charge.enums.ChargeFreeTypeEnum;
import com.zoeeasy.cloud.charge.park.ParkChargeRuleService;
import com.zoeeasy.cloud.charge.park.dto.request.ParkChargeRuleCalculateTryRequestDto;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingChargeRuleForUserParkingRecordRequestDto;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingChargeRuleTryRequestDto;
import com.zoeeasy.cloud.charge.park.dto.validator.ParkChargeRuleCalculateTryValidator;
import com.zoeeasy.cloud.integration.appoint.dto.request.AppointAmountCalculateRequestDto;
import com.zoeeasy.cloud.integration.appoint.dto.result.AppointAmountCalculateResultDto;
import com.zoeeasy.cloud.integration.park.CalculateIntegrationService;
import com.zoeeasy.cloud.integration.park.ParkChargeRuleIntegrationService;
import com.zoeeasy.cloud.integration.park.dto.request.ParkingAmountCalculateRequestDto;
import com.zoeeasy.cloud.integration.park.validator.ParkingAmountCalculateRequestDtoValidator;
import com.zoeeasy.cloud.pms.enums.PmsResultEnum;
import com.zoeeasy.cloud.pms.enums.SpecialTypeEnum;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingInfoResultDto;
import com.zoeeasy.cloud.pms.platform.PlatformParkingInfoService;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingInfoGetRequestDto;
import com.zoeeasy.cloud.pms.specialvehicle.SpecialVehicleService;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.SpecialTypeGetRequestDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.SpecialVehicleInfoResultDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 计算停车金额服务
 * @author: AkeemSuper
 * @date: 2018/3/15 0015
 */
@Service(value = "calculateIntegrationService")
@Slf4j
public class CalculateIntegrationServiceImpl implements CalculateIntegrationService {

    @Autowired
    private ParkChargeRuleIntegrationService parkChargeRuleIntegrationService;

    @Autowired
    private PlatformParkingInfoService platformParkingInfoService;

    @Autowired
    private SpecialVehicleService specialVehicleService;

    @Autowired
    private CalculateAmountService calculateAmountService;

    @Autowired
    private AppointRuleService appointRuleService;

    @Autowired
    private ParkChargeRuleService parkChargeRuleService;

    @Autowired
    private ParkingAppointRuleService parkingAppointRuleService;

    @Override
    public ObjectResultDto<CalculateAmountResultDto> calculateAmount(@FluentValid(ParkingAmountCalculateRequestDtoValidator.class) ParkingAmountCalculateRequestDto requestDto) {
        ObjectResultDto<CalculateAmountResultDto> resultDto = new ObjectResultDto<>();
        try {
            Long parkingId = requestDto.getParkingId();
            Date endTime = requestDto.getEndTime();
            Date startTime = requestDto.getStartTime();
            Integer carType = requestDto.getCarStyle();
            //获取停车场
            ParkingInfoGetRequestDto parkingInfoGetRequestDto = new ParkingInfoGetRequestDto();
            parkingInfoGetRequestDto.setParkingId(parkingId);
            ObjectResultDto<ParkingInfoResultDto> parkingInfoServiceParkInfoById = platformParkingInfoService.getParkInfoById(parkingInfoGetRequestDto);
            if (parkingInfoServiceParkInfoById.isFailed() || parkingInfoServiceParkInfoById.getData() == null) {
                return resultDto.makeResult(PmsResultEnum.PARKING_NOT_EXIST.getValue(), PmsResultEnum.PARKING_NOT_EXIST.getComment());
            }
            ParkingInfoResultDto parkingInfo = parkingInfoServiceParkInfoById.getData();
            if (StringUtils.isEmpty(requestDto.getPlateNumber()) || !PlateNumberUtil.isPlateNumber(requestDto.getPlateNumber())) {
                List<ChargeRuleInfoViewResultDto> parkingChargeRuleResultDtos = getChargeRuleInfoList(carType,
                        parkingInfo, startTime, endTime);
                return getCalculateAmountResultDto(startTime, endTime, parkingChargeRuleResultDtos, parkingInfo.getTenantId());
            }
            //特殊车辆判断
            SpecialTypeGetRequestDto specialTypeGetRequestDto = new SpecialTypeGetRequestDto();
            specialTypeGetRequestDto.setParkingId(requestDto.getParkingId());
            specialTypeGetRequestDto.setPlateNumber(requestDto.getPlateNumber());
            ObjectResultDto<SpecialVehicleInfoResultDto> specialTypeByPlateNo = specialVehicleService.selectSpecialTypeByPlateNo(specialTypeGetRequestDto);
            Integer parkingType;
            if (specialTypeByPlateNo.isSuccess() && specialTypeByPlateNo.getData() != null) {
                parkingType = specialTypeByPlateNo.getData().getSpecialType();
            } else {
                parkingType = SpecialTypeEnum.TEMPORTARY_CAR.getValue();
            }
            if (parkingType.equals(SpecialTypeEnum.TEMPORTARY_CAR.getValue()) || parkingType.equals(SpecialTypeEnum.BLACK_LIST.getValue())) {
                //非特殊车辆,黑名单,临时车为普通计算
                List<ChargeRuleInfoViewResultDto> parkingChargeRuleResultDtos = getChargeRuleInfoList(carType,
                        parkingInfo, startTime, endTime);
                return getCalculateAmountResultDto(startTime, endTime, parkingChargeRuleResultDtos, parkingInfo.getTenantId());
            } else {
                //特殊车辆有效期内免费
                SpecialVehicleInfoResultDto specialTypeByPlateNoData = specialTypeByPlateNo.getData();
                Date validStartTime = specialTypeByPlateNoData.getValidStartTime();
                Date validEndTime = specialTypeByPlateNoData.getValidEndTime();
                if (requestDto.getStartTime().compareTo(validStartTime) >= 0 && requestDto.getEndTime().compareTo(validEndTime) <= 0) {
                    CalculateAmountResultDto calculateAmountResultDto = new CalculateAmountResultDto();
                    if (parkingType.equals(SpecialTypeEnum.WHITE_LIST.getValue())) {
                        calculateAmountResultDto.setFreeType(ChargeFreeTypeEnum.WHITE_CAR_FREE.getValue());
                    } else if (parkingType.equals(SpecialTypeEnum.FIXED_CAR.getValue())) {
                        calculateAmountResultDto.setFreeType(ChargeFreeTypeEnum.FIX_CAR_FREE.getValue());
                    } else if (parkingType.equals(SpecialTypeEnum.PACKET_CAR.getValue())) {
                        calculateAmountResultDto.setFreeType(ChargeFreeTypeEnum.PACKET_CAR_FREE.getValue());
                    } else if (parkingType.equals(SpecialTypeEnum.VISITOR_CAR.getValue())) {
                        calculateAmountResultDto.setFreeType(ChargeFreeTypeEnum.VISIT_CAR_FREE.getValue());
                    }
                    Duration duration = new Duration(new DateTime(startTime), new DateTime(endTime));
                    Long parkingDuration = (duration.getMillis()) / (long) 1000;
                    calculateAmountResultDto.setFreeDuration(parkingDuration);
                    calculateAmountResultDto.setChargeDuration(0L);
                    calculateAmountResultDto.setAmount(0);
                    resultDto.setData(calculateAmountResultDto);
                    resultDto.success();
                }
                if (requestDto.getStartTime().compareTo(validStartTime) >= 0 && requestDto.getEndTime().compareTo(validEndTime) >= 0 && requestDto.getStartTime().compareTo(validEndTime) <= 0) {
                    List<ChargeRuleInfoViewResultDto> chargeRuleInfoList = getChargeRuleInfoList(carType, parkingInfo, validEndTime, endTime);
                    resultDto = getCalculateAmountResultDto(validEndTime, endTime, chargeRuleInfoList, parkingInfo.getTenantId());
                } else if (validStartTime.compareTo(requestDto.getStartTime()) >= 0 && validStartTime.compareTo(endTime) <= 0 && validEndTime.compareTo(endTime) >= 0) {
                    List<ChargeRuleInfoViewResultDto> chargeRuleInfoList = getChargeRuleInfoList(carType, parkingInfo, startTime, validStartTime);
                    resultDto = getCalculateAmountResultDto(startTime, validStartTime, chargeRuleInfoList, parkingInfo.getTenantId());
                } else {
                    CalculateAmountResultDto calculateAmountResultDto = new CalculateAmountResultDto();
                    List<ChargeRuleInfoViewResultDto> beforeChargeRuleList = getChargeRuleInfoList(carType, parkingInfo
                            , startTime, validStartTime);
                    ObjectResultDto<CalculateAmountResultDto> beforeAmountResult = getCalculateAmountResultDto(startTime, validStartTime, beforeChargeRuleList, parkingInfo.getTenantId());

                    List<ChargeRuleInfoViewResultDto> afterChargeRuleList = getChargeRuleInfoList(carType, parkingInfo,
                            validEndTime, endTime);
                    ObjectResultDto<CalculateAmountResultDto> afterAmountResult = getCalculateAmountResultDto(validEndTime, endTime, afterChargeRuleList, parkingInfo.getTenantId());
                    if (beforeAmountResult.isSuccess() && afterAmountResult.isSuccess()) {

                        Integer beforeAmount = beforeAmountResult.getData().getAmount();
                        Integer afterAmount = afterAmountResult.getData().getAmount();
                        Integer totalAmount = beforeAmount + afterAmount;
                        if (totalAmount.compareTo(0) <= 0) {
                            Duration duration = new Duration(new DateTime(startTime), new DateTime(endTime));
                            Long parkingDuration = (duration.getMillis()) / (long) 1000;
                            calculateAmountResultDto.setFreeType(ChargeFreeTypeEnum.CALCULATE_FREE.getValue());
                            calculateAmountResultDto.setChargeDuration(0L);
                            calculateAmountResultDto.setFreeDuration(parkingDuration);
                            calculateAmountResultDto.setAmount(0);
                        } else {
                            Duration duration = new Duration(new DateTime(startTime), new DateTime(endTime));
                            Long parkingDuration = (duration.getMillis()) / (long) 1000;
                            Long beforeChargeDuration = beforeAmountResult.getData().getChargeDuration();
                            Long afterChargeDuration = afterAmountResult.getData().getChargeDuration();
                            Long totalChargeDuration = beforeChargeDuration + afterChargeDuration;
                            calculateAmountResultDto.setAmount(totalAmount);
                            calculateAmountResultDto.setChargeDuration(totalChargeDuration);
                            calculateAmountResultDto.setFreeDuration(parkingDuration - totalChargeDuration);
                        }
                        resultDto.setData(calculateAmountResultDto);
                        resultDto.success();
                    } else {
                        resultDto.failed();
                    }
                }
            }
        } catch (Exception e) {
            resultDto.failed();
            log.error("计算停车金额失败" + e.getMessage());
        }
        return resultDto;
    }

    /**
     * 停车场收费规则试算
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<CalculateAmountViewResultDto> parkChargeRuleCalculateTry(@FluentValid({ParkChargeRuleCalculateTryValidator.class}) ParkChargeRuleCalculateTryRequestDto requestDto) {
        ObjectResultDto<CalculateAmountViewResultDto> resultDto = new ObjectResultDto<>();
        try {
            Long parkingId = requestDto.getParkingId();
            Date endTime = requestDto.getTryEndTime();
            Date startTime = requestDto.getTryStartTime();
            List<ParkingChargeRuleTryRequestDto> parkingChargeRuleTry = requestDto.getParkingChargeRuleTry();
            Date minOnlineTime =
                    parkingChargeRuleTry.stream().sorted(Comparator.comparing(ParkingChargeRuleTryRequestDto::getOnlineTime))
                            .collect(Collectors.toList()).get(0).getOnlineTime();
            Date maxOffLineTime =
                    parkingChargeRuleTry.stream().sorted(Comparator.comparing(ParkingChargeRuleTryRequestDto::getOfflineTime).reversed())
                            .collect(Collectors.toList()).get(0).getOfflineTime();
            if (startTime.before(minOnlineTime) || endTime.after(maxOffLineTime)) {
                resultDto.failed(ChargeConstant.TRY_PARKING_TIME_NOT_STANDARD);
                return resultDto;
            }
            //获取停车场
            ParkingInfoGetRequestDto parkingInfoGetRequestDto = new ParkingInfoGetRequestDto();
            parkingInfoGetRequestDto.setParkingId(parkingId);
            ObjectResultDto<ParkingInfoResultDto> parkingInfoServiceParkInfoById = platformParkingInfoService.getParkInfoById(parkingInfoGetRequestDto);
            if (parkingInfoServiceParkInfoById.isFailed() || parkingInfoServiceParkInfoById.getData() == null) {
                return resultDto.makeResult(PmsResultEnum.PARKING_NOT_EXIST.getValue(), PmsResultEnum.PARKING_NOT_EXIST.getComment());
            }
            ParkingInfoResultDto parkingInfo = parkingInfoServiceParkInfoById.getData();
            if (StringUtils.isEmpty(requestDto.getTryPlateNumber())) {
                List<ChargeRuleInfoViewResultDto> parkingChargeRuleResultDtos = getChargeRuleInfoList(requestDto.getTryCarType(),
                        parkingInfo, startTime, endTime);
                getCalculateAmountResultDto(startTime, endTime, parkingChargeRuleResultDtos, parkingInfo.getTenantId());
            }
            SpecialTypeGetRequestDto specialTypeGetRequestDto = new SpecialTypeGetRequestDto();
            specialTypeGetRequestDto.setParkingId(requestDto.getParkingId());
            specialTypeGetRequestDto.setPlateNumber(requestDto.getTryPlateNumber());
            ObjectResultDto<SpecialVehicleInfoResultDto> specialTypeByPlateNo = specialVehicleService.selectSpecialTypeByPlateNo(specialTypeGetRequestDto);
            Integer parkingType;
            if (specialTypeByPlateNo.isSuccess() && specialTypeByPlateNo.getData() != null) {
                parkingType = specialTypeByPlateNo.getData().getSpecialType();
            } else {
                parkingType = SpecialTypeEnum.TEMPORTARY_CAR.getValue();
            }
            if (parkingType.equals(SpecialTypeEnum.TEMPORTARY_CAR.getValue()) || parkingType.equals(SpecialTypeEnum.BLACK_LIST.getValue())) {
                //非特殊车辆,黑名单,临时车为普通计算
                return parkChargeRuleService.parkChargeRuleCalculateTry(requestDto);
            } else {
                //特殊车辆有效期内免费
                SpecialVehicleInfoResultDto specialTypeByPlateNoData = specialTypeByPlateNo.getData();
                Date validStartTime = specialTypeByPlateNoData.getValidStartTime();
                Date validEndTime = specialTypeByPlateNoData.getValidEndTime();
                if (startTime.compareTo(validStartTime) >= 0 && endTime.compareTo(validEndTime) <= 0) {
                    CalculateAmountViewResultDto calculateAmountResultDto = new CalculateAmountViewResultDto();
                    if (parkingType.equals(SpecialTypeEnum.WHITE_LIST.getValue())) {
                        calculateAmountResultDto.setFreeType(ChargeFreeTypeEnum.WHITE_CAR_FREE.getValue());
                    } else if (parkingType.equals(SpecialTypeEnum.FIXED_CAR.getValue())) {
                        calculateAmountResultDto.setFreeType(ChargeFreeTypeEnum.FIX_CAR_FREE.getValue());
                    } else if (parkingType.equals(SpecialTypeEnum.PACKET_CAR.getValue())) {
                        calculateAmountResultDto.setFreeType(ChargeFreeTypeEnum.PACKET_CAR_FREE.getValue());
                    } else if (parkingType.equals(SpecialTypeEnum.VISITOR_CAR.getValue())) {
                        calculateAmountResultDto.setFreeType(ChargeFreeTypeEnum.VISIT_CAR_FREE.getValue());
                    }
                    Duration duration = new Duration(new DateTime(startTime), new DateTime(endTime));
                    Long parkingDuration = (duration.getMillis()) / (long) 1000;
                    calculateAmountResultDto.setFreeDuration(parkingDuration);
                    calculateAmountResultDto.setChargeDuration(0L);
                    calculateAmountResultDto.setAmount(0);
                    resultDto.setData(calculateAmountResultDto);
                    resultDto.success();
                }
                if (startTime.compareTo(validStartTime) >= 0 && endTime.compareTo(validEndTime) >= 0 && startTime.compareTo(validEndTime) <= 0) {
                    ParkChargeRuleCalculateTryRequestDto parkChargeRuleCalculateTryRequestDto = buildParkChargeRuleCalculateTry(requestDto, validStartTime, endTime);
                    return parkChargeRuleService.parkChargeRuleCalculateTry(parkChargeRuleCalculateTryRequestDto);
                } else if (validStartTime.compareTo(startTime) >= 0 && validStartTime.compareTo(endTime) <= 0 && validEndTime.compareTo(endTime) >= 0) {
                    ParkChargeRuleCalculateTryRequestDto parkChargeRuleCalculateTryRequestDto = buildParkChargeRuleCalculateTry(requestDto, startTime, validStartTime);
                    return parkChargeRuleService.parkChargeRuleCalculateTry(parkChargeRuleCalculateTryRequestDto);
                } else {
                    CalculateAmountViewResultDto calculateAmountResultDto = new CalculateAmountViewResultDto();
                    ParkChargeRuleCalculateTryRequestDto beforeParkChargeRuleCalculateTry =
                            buildParkChargeRuleCalculateTry(requestDto, startTime, validStartTime);
                    ObjectResultDto<CalculateAmountViewResultDto> beforeAmountResult = parkChargeRuleService.parkChargeRuleCalculateTry(beforeParkChargeRuleCalculateTry);
                    ParkChargeRuleCalculateTryRequestDto afterParkChargeRuleCalculateTry =
                            buildParkChargeRuleCalculateTry(requestDto, validEndTime, endTime);
                    ObjectResultDto<CalculateAmountViewResultDto> afterAmountResult = parkChargeRuleService.parkChargeRuleCalculateTry(afterParkChargeRuleCalculateTry);
                    if (beforeAmountResult.isSuccess() && afterAmountResult.isSuccess()) {
                        Integer beforeAmount = beforeAmountResult.getData().getAmount();
                        Integer afterAmount = afterAmountResult.getData().getAmount();
                        Integer totalAmount = beforeAmount + afterAmount;
                        if (totalAmount.compareTo(0) <= 0) {
                            Duration duration = new Duration(new DateTime(startTime), new DateTime(endTime));
                            Long parkingDuration = (duration.getMillis()) / (long) 1000;
                            calculateAmountResultDto.setFreeType(ChargeFreeTypeEnum.CALCULATE_FREE.getValue());
                            calculateAmountResultDto.setChargeDuration(0L);
                            calculateAmountResultDto.setFreeDuration(parkingDuration);
                            calculateAmountResultDto.setAmount(0);
                        } else {
                            Duration duration = new Duration(new DateTime(startTime), new DateTime(endTime));
                            Long parkingDuration = (duration.getMillis()) / (long) 1000;
                            Long beforeChargeDuration = beforeAmountResult.getData().getChargeDuration();
                            Long afterChargeDuration = afterAmountResult.getData().getChargeDuration();
                            Long totalChargeDuration = beforeChargeDuration + afterChargeDuration;
                            calculateAmountResultDto.setAmount(totalAmount);
                            calculateAmountResultDto.setChargeDuration(totalChargeDuration);
                            calculateAmountResultDto.setFreeDuration(parkingDuration - totalChargeDuration);
                        }
                        resultDto.setData(calculateAmountResultDto);
                        resultDto.success();
                    } else {
                        resultDto.failed();
                    }
                }
            }
        } catch (Exception e) {
            log.error("停车场收费规则试算失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    private ParkChargeRuleCalculateTryRequestDto buildParkChargeRuleCalculateTry(ParkChargeRuleCalculateTryRequestDto requestDto, Date startTime, Date endTime) {
        ParkChargeRuleCalculateTryRequestDto parkChargeRuleCalculateTryRequestDto = new ParkChargeRuleCalculateTryRequestDto();
        parkChargeRuleCalculateTryRequestDto.setParkingId(requestDto.getParkingId());
        parkChargeRuleCalculateTryRequestDto.setParkingChargeRuleTry(requestDto.getParkingChargeRuleTry());
        parkChargeRuleCalculateTryRequestDto.setTryCarType(requestDto.getTryCarType());
        parkChargeRuleCalculateTryRequestDto.setTryPlateColor(requestDto.getTryPlateColor());
        parkChargeRuleCalculateTryRequestDto.setTryStartTime(startTime);
        parkChargeRuleCalculateTryRequestDto.setTryEndTime(endTime);
        return parkChargeRuleCalculateTryRequestDto;
    }

    /**
     * 根据收费规则和时间计算收费费用
     *
     * @param startTime
     * @param endTime
     * @param parkingChargeRuleResultDtos
     * @param tenantId
     * @return
     */
    private ObjectResultDto<CalculateAmountResultDto> getCalculateAmountResultDto(Date startTime,
                                                                                  Date endTime,
                                                                                  List<ChargeRuleInfoViewResultDto> parkingChargeRuleResultDtos, Long tenantId) {
        ObjectResultDto<CalculateAmountResultDto> resultDto = new ObjectResultDto<>();
        //总停车时长
        Duration duration = new Duration(new DateTime(startTime), new DateTime(endTime));
        Long parkingDuration = (duration.getMillis()) / (long) 1000;
        if (parkingDuration.compareTo(0L) <= 0) {
            CalculateAmountResultDto calculateAmountResultDto = new CalculateAmountResultDto();
            calculateAmountResultDto.setFreeType(ChargeFreeTypeEnum.CALCULATE_FREE.getValue());
            calculateAmountResultDto.setAmount(0);
            calculateAmountResultDto.setChargeDuration(0L);
            calculateAmountResultDto.setFreeDuration(0L);
            resultDto.setData(calculateAmountResultDto);
            resultDto.success();
            return resultDto;
        }
        if (CollectionUtils.isEmpty(parkingChargeRuleResultDtos)) {
            CalculateAmountResultDto calculateAmountResultDto = new CalculateAmountResultDto();
            //停车场收费规则不存在，默认不收费
            calculateAmountResultDto.setFreeType(ChargeFreeTypeEnum.CHARGE_RULE_EMPTY_FREE.getValue());
            calculateAmountResultDto.setFreeDuration(parkingDuration);
            calculateAmountResultDto.setChargeDuration(0L);
            calculateAmountResultDto.setAmount(0);
            resultDto.setData(calculateAmountResultDto);
            return resultDto.success();
        } else {
            CalculateAmountRequestDto calculateAmountRequestDto = new CalculateAmountRequestDto();
            calculateAmountRequestDto.setStartTime(startTime);
            calculateAmountRequestDto.setEndTime(endTime);
            calculateAmountRequestDto.setParkingChargeRules(parkingChargeRuleResultDtos);
            calculateAmountRequestDto.setTenantId(tenantId);
            return calculateAmountService.calculateAmount(calculateAmountRequestDto);
        }
    }

    /**
     * 根据车辆类型和停车场信息,开始结束时间寻找收费规则
     *
     * @param carType
     * @param parkingInfo
     * @param startTime
     * @param endTime
     * @return
     */
    private List<ChargeRuleInfoViewResultDto> getChargeRuleInfoList(Integer carType, ParkingInfoResultDto parkingInfo
            , Date startTime, Date endTime) {
        ParkingChargeRuleForUserParkingRecordRequestDto parkingRecordRequestDto = new ParkingChargeRuleForUserParkingRecordRequestDto();
        parkingRecordRequestDto.setParkingId(parkingInfo.getId());
        parkingRecordRequestDto.setStartTime(startTime);
        parkingRecordRequestDto.setEndTime(endTime);
        parkingRecordRequestDto.setCarStyle(carType);
        parkingRecordRequestDto.setParkingLevel(parkingInfo.getLevel());
        parkingRecordRequestDto.setTenantId(parkingInfo.getTenantId());
        return parkChargeRuleIntegrationService.getChargeRuleForUserParkingRecord(parkingRecordRequestDto);
    }

    /**
     * 计算预约费用
     */
    @Override
    public ObjectResultDto<AppointAmountCalculateResultDto> calculateAppointAmount(AppointAmountCalculateRequestDto requestDto) {
        ObjectResultDto<AppointAmountCalculateResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            String dateStr = DateTimeUtils.formatDate(requestDto.getAppointTime(), DateTimeUtils.DATE_FORMAT_DATEONLY);
            Date realDate = DateTimeUtils.parseDate(dateStr, DateTimeUtils.DATE_FORMAT_DATEONLY);
            ParkingAppointRuleGetByVaildTimeRequestDto parkingAppointRuleGetByVaildTimeRequestDto = new ParkingAppointRuleGetByVaildTimeRequestDto();
            parkingAppointRuleGetByVaildTimeRequestDto.setOnlineTime(realDate);
            parkingAppointRuleGetByVaildTimeRequestDto.setOfflineTime(realDate);
            parkingAppointRuleGetByVaildTimeRequestDto.setParkingId(requestDto.getParkingId());
            ObjectResultDto<ParkingAppointRuleViewResultDto> parkingAppointmentRuleObject = parkingAppointRuleService.getParkingAppointRule(parkingAppointRuleGetByVaildTimeRequestDto);
            AppointAmountCalculateResultDto appointAmountCalculateResultDto = new AppointAmountCalculateResultDto();
            if (parkingAppointmentRuleObject.isFailed() || null == parkingAppointmentRuleObject.getData()) {
                appointAmountCalculateResultDto.setAmount(BigDecimal.valueOf(0));
            } else {
                AppointRuleGetRequestDto appointRuleGetRequestDto = new AppointRuleGetRequestDto();
                appointRuleGetRequestDto.setRuleId(parkingAppointmentRuleObject.getData().getRuleId());
                ObjectResultDto<AppointRuleResultDto> appointmentRuleObj = appointRuleService.getAppointRuleByIdNoTalentId(appointRuleGetRequestDto);
                if (appointmentRuleObj.isFailed() || null == appointmentRuleObj.getData()) {
                    appointAmountCalculateResultDto.setAmount(BigDecimal.valueOf(0));
                } else {
                    appointAmountCalculateResultDto.setAmount(NumberUtils.amountFen2Yuan(BigDecimal.valueOf(appointmentRuleObj.getData().getFee())));
                }
            }
            objectResultDto.setData(appointAmountCalculateResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("预约费用计算失败" + e.getMessage());
            objectResultDto.makeResult(PmsResultEnum.FEE_CALCULATE_ERR.getValue(), PmsResultEnum.FEE_CALCULATE_ERR.getComment());
        }
        return objectResultDto;
    }
}
