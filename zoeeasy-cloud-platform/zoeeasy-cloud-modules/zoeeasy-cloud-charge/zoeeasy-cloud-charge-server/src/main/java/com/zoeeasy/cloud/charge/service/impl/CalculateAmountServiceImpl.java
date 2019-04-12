package com.zoeeasy.cloud.charge.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.core.base.FundamentalRequestContext;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.charge.bean.ChargePartTime;
import com.zoeeasy.cloud.charge.chg.CalculateAmountService;
import com.zoeeasy.cloud.charge.chg.CalculateAmountStrategy;
import com.zoeeasy.cloud.charge.chg.StrategyService;
import com.zoeeasy.cloud.charge.chg.dto.request.CalculateAmountRequestDto;
import com.zoeeasy.cloud.charge.chg.dto.request.ChargeRuleCalculateTryRequestDto;
import com.zoeeasy.cloud.charge.chg.dto.result.CalculateAmountResultDto;
import com.zoeeasy.cloud.charge.chg.dto.result.CalculateAmountViewResultDto;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleInfoViewResultDto;
import com.zoeeasy.cloud.charge.chg.validator.CalculateAmountRequestDtoValidator;
import com.zoeeasy.cloud.charge.chg.validator.ChargeRuleCalculateTryRequestDtoValidator;
import com.zoeeasy.cloud.charge.cts.ChargeConstant;
import com.zoeeasy.cloud.charge.domain.HolidayCalendarEntity;
import com.zoeeasy.cloud.charge.domain.HolidayScheduleEntity;
import com.zoeeasy.cloud.charge.enums.ChargeFreeTypeEnum;
import com.zoeeasy.cloud.charge.enums.ChargeRuleTypeEnum;
import com.zoeeasy.cloud.charge.enums.HolidayTypeEnum;
import com.zoeeasy.cloud.charge.service.ChargeRuleCrudService;
import com.zoeeasy.cloud.charge.service.ChargeRuleTimeCrudService;
import com.zoeeasy.cloud.charge.service.HolidayCalendarCrudService;
import com.zoeeasy.cloud.charge.service.HolidayScheduleCrudService;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.core.enums.CarTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author AkeemSuper
 * @date 2018/10/13 0013
 */
@Service(value = "calculateAmountService")
@Slf4j
public class CalculateAmountServiceImpl implements CalculateAmountService {

    @Autowired
    private StrategyService strategyService;

    @Autowired
    private HolidayCalendarCrudService holidayCalendarCrudService;

    @Autowired
    private HolidayScheduleCrudService holidayScheduleCrudService;

    @Autowired
    private ChargeRuleCrudService chargeRuleCrudService;

    @Autowired
    private ChargeRuleTimeCrudService chargeRuleTimeCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 计算收费规则
     *
     * @param requestDto CalculateAmountRequestDto
     * @return ObjectResultDto<CalculateAmountResultDto>
     */
    @Override
    public ObjectResultDto<CalculateAmountResultDto> calculateAmount(
            @FluentValid(CalculateAmountRequestDtoValidator.class) CalculateAmountRequestDto requestDto) {
        ObjectResultDto<CalculateAmountResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            Date endTime = requestDto.getEndTime();
            Date startTime = requestDto.getStartTime();
            Duration duration = new Duration(new DateTime(startTime), new DateTime(endTime));
            Long parkingDuration = (duration.getMillis()) / (long) 1000;
            Long chargeDuration = 0L;
            Integer totalAmount = 0;
            //按假日类型对找到的收费规则倒序排序
            ChargePartTime partTime = new ChargePartTime();
            partTime.setPartStartTime(requestDto.getStartTime());
            partTime.setPartEndTime(requestDto.getEndTime());
            List<ChargePartTime> tempChargePartTime = new ArrayList<>();
            tempChargePartTime.add(partTime);
            List<ChargeRuleInfoViewResultDto> chargeRuleResultDtoList = requestDto.getParkingChargeRules().stream().
                    sorted(Comparator.comparing(ChargeRuleInfoViewResultDto::getHolidayType).reversed().
                            thenComparing(ChargeRuleInfoViewResultDto::getOnlineTime)).collect(Collectors.toList());
            //遍历找到的收费规则
            for (ChargeRuleInfoViewResultDto parkingChargeRuleResultDto : chargeRuleResultDtoList) {
                //规则在停车时间段内的使用时间
                Date partStartTime;
                Date partEndTime;
                if (parkingChargeRuleResultDto.getOnlineTime().compareTo(requestDto.getStartTime()) < 0) {
                    partStartTime = requestDto.getStartTime();
                } else {
                    partStartTime = parkingChargeRuleResultDto.getOnlineTime();
                }
                if (parkingChargeRuleResultDto.getOfflineTime().compareTo(requestDto.getEndTime()) > 0) {
                    partEndTime = requestDto.getEndTime();
                } else {
                    partEndTime = parkingChargeRuleResultDto.getOfflineTime();
                }
                if (partStartTime.compareTo(parkingChargeRuleResultDto.getOfflineTime()) >= 0) {
                    continue;
                }
                //该假期类型的日期
                if (parkingChargeRuleResultDto.getHolidayType().equals(HolidayTypeEnum.WORK_DAY.getValue()) || parkingChargeRuleResultDto.getHolidayType().equals(HolidayTypeEnum.ALL.getValue())) {
                    List<ChargePartTime> partTimes = new ArrayList<>(tempChargePartTime);
                    for (ChargePartTime part : partTimes) {
                        if (part.getPartEndTime().compareTo(partStartTime) < 0 || part.getPartStartTime().compareTo(partEndTime) > 0) {
                            continue;
                        } else if (partStartTime.compareTo(part.getPartStartTime()) <= 0 && partEndTime.compareTo(part.getPartEndTime()) >= 0) {
                            Object[] objects = calculatePartTime(parkingChargeRuleResultDto, part.getPartStartTime(), part.getPartEndTime());
                            totalAmount += (int) objects[0];
                            chargeDuration += (long) objects[1];
                        } else if (partStartTime.compareTo(part.getPartStartTime()) <= 0 && partEndTime.compareTo(part.getPartEndTime()) < 0) {
                            ChargePartTime after = new ChargePartTime();
                            after.setPartStartTime(partEndTime);
                            after.setPartEndTime(part.getPartEndTime());
                            tempChargePartTime.add(after);
                            Object[] objects = calculatePartTime(parkingChargeRuleResultDto, part.getPartStartTime(), partEndTime);
                            totalAmount += (int) objects[0];
                            chargeDuration += (long) objects[1];
                        } else if (partStartTime.compareTo(part.getPartStartTime()) > 0 && partEndTime.compareTo(part.getPartEndTime()) >= 0) {
                            ChargePartTime before = new ChargePartTime();
                            before.setPartStartTime(part.getPartStartTime());
                            before.setPartEndTime(partStartTime);
                            tempChargePartTime.add(before);
                            Object[] objects = calculatePartTime(parkingChargeRuleResultDto, partStartTime, part.getPartEndTime());
                            totalAmount += (int) objects[0];
                            chargeDuration += (long) objects[1];
                        } else if (partStartTime.compareTo(part.getPartStartTime()) > 0 && partEndTime.compareTo(part.getPartEndTime()) < 0) {
                            Object[] objects = calculatePartTime(parkingChargeRuleResultDto, partStartTime, partEndTime);
                            totalAmount += (int) objects[0];
                            chargeDuration += (long) objects[1];
                        }
                        tempChargePartTime.removeIf(removePartTime ->
                                removePartTime.getPartEndTime().compareTo(part.getPartEndTime()) == 0 && removePartTime.getPartStartTime().compareTo(part.getPartStartTime()) == 0);
                    }
                } else {
                    Wrapper<HolidayCalendarEntity> entityEntityWrapper = new EntityWrapper<>();
                    entityEntityWrapper.eq("tenantId", requestDto.getTenantId());
                    entityEntityWrapper.ge("date", DateUtils.formatDate(partStartTime, Const.FORMAT_DATE));
                    ZoneId zoneId = ZoneId.systemDefault();
                    LocalDateTime localPartEndTime = LocalDateTime.ofInstant(partEndTime.toInstant(), zoneId);
                    if (LocalTime.MIN.equals(localPartEndTime.toLocalTime())) {
                        entityEntityWrapper.lt("date", DateUtils.formatDate(partEndTime, Const.FORMAT_DATE));
                    } else {
                        entityEntityWrapper.le("date", DateUtils.formatDate(partEndTime, Const.FORMAT_DATE));
                    }
                    List<HolidayCalendarEntity> holidayCalendarList = holidayCalendarCrudService.selectDate(entityEntityWrapper);
                    if (CollectionUtils.isNotEmpty(holidayCalendarList)) {
                        List<Long> holidayScheduleIds =
                                holidayCalendarList.stream().map(HolidayCalendarEntity::getHolidayId).distinct().collect(Collectors.toList());
                        List<HolidayScheduleEntity> holidayScheduleEntities = holidayScheduleCrudService.findByIdAndHolidayType(parkingChargeRuleResultDto.getHolidayType()
                                , requestDto.getTenantId(), holidayScheduleIds);
                        if (CollectionUtils.isNotEmpty(holidayScheduleEntities)) {
                            List<Long> holidayId =
                                    holidayScheduleEntities.stream().distinct().map(HolidayScheduleEntity::getId).collect(Collectors.toList());
                            entityEntityWrapper.in("holidayId", holidayId);
                            entityEntityWrapper.orderBy("date", true);
                            List<HolidayCalendarEntity> holidayCalendarEntities = holidayCalendarCrudService.selectDate(entityEntityWrapper);
                            if (CollectionUtils.isNotEmpty(holidayCalendarEntities)) {
                                Map<Long, List<HolidayCalendarEntity>> collect =
                                        holidayCalendarEntities.stream().collect(Collectors.groupingBy(HolidayCalendarEntity::getHolidayId));
                                for (Map.Entry<Long, List<HolidayCalendarEntity>> entry : collect.entrySet()) {
                                    List<HolidayCalendarEntity> dates = entry.getValue();
                                    List<HolidayCalendarEntity> calendarEntities = dates.stream().sorted(Comparator.comparing(HolidayCalendarEntity::getDate)).collect(Collectors.toList());
                                    Date holidayStartTime = DateUtils.parseDate(calendarEntities.get(0).getDate(), Const.FORMAT_DATE);
                                    Date holidayEndTime =
                                            DateUtils.addDays(DateUtils.parseDate(calendarEntities.get(calendarEntities.size() - 1).getDate(), Const.FORMAT_DATE), 1);
                                    List<ChargePartTime> partTimes = new ArrayList<>(tempChargePartTime);
                                    for (ChargePartTime part : partTimes) {
                                        if (part.getPartEndTime().compareTo(holidayStartTime) < 0 || part.getPartStartTime().compareTo(holidayEndTime) > 0) {
                                            continue;
                                        } else if (part.getPartStartTime().compareTo(holidayStartTime) < 0 && part.getPartEndTime().compareTo(holidayEndTime) > 0) {
                                            ChargePartTime before = new ChargePartTime();
                                            before.setPartStartTime(part.getPartStartTime());
                                            before.setPartEndTime(holidayStartTime);
                                            ChargePartTime after = new ChargePartTime();
                                            after.setPartStartTime(holidayEndTime);
                                            after.setPartEndTime(part.getPartEndTime());
                                            tempChargePartTime.add(after);
                                            tempChargePartTime.add(before);
                                            Object[] objects = calculatePartTime(parkingChargeRuleResultDto, holidayStartTime, holidayEndTime);
                                            totalAmount += (int) objects[0];
                                            chargeDuration += (long) objects[1];
                                        } else if (part.getPartStartTime().compareTo(holidayStartTime) < 0 && part.getPartEndTime().compareTo(holidayEndTime) <= 0) {
                                            ChargePartTime before = new ChargePartTime();
                                            before.setPartStartTime(part.getPartStartTime());
                                            before.setPartEndTime(holidayStartTime);
                                            tempChargePartTime.add(before);
                                            Object[] objects = calculatePartTime(parkingChargeRuleResultDto, holidayStartTime, part.getPartEndTime());
                                            totalAmount += (int) objects[0];
                                            chargeDuration += (long) objects[1];
                                        } else if (part.getPartStartTime().compareTo(holidayStartTime) >= 0 && part.getPartEndTime().compareTo(holidayEndTime) > 0) {
                                            ChargePartTime after = new ChargePartTime();
                                            after.setPartStartTime(holidayEndTime);
                                            after.setPartEndTime(part.getPartEndTime());
                                            tempChargePartTime.add(after);
                                            Object[] objects = calculatePartTime(parkingChargeRuleResultDto, part.getPartStartTime(), holidayEndTime);
                                            totalAmount += (int) objects[0];
                                            chargeDuration += (long) objects[1];
                                        } else if (part.getPartStartTime().compareTo(holidayStartTime) >= 0 && part.getPartEndTime().compareTo(holidayEndTime) <= 0) {
                                            Object[] objects = calculatePartTime(parkingChargeRuleResultDto, part.getPartStartTime(), part.getPartEndTime());
                                            totalAmount += (int) objects[0];
                                            chargeDuration += (long) objects[1];
                                        }
                                        tempChargePartTime.removeIf(removePartTime ->
                                                removePartTime.getPartEndTime().compareTo(part.getPartEndTime()) == 0 && removePartTime.getPartStartTime().compareTo(part.getPartStartTime()) == 0);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            CalculateAmountResultDto calculateAmountResultDto = new CalculateAmountResultDto();
            if (totalAmount == 0) {
                calculateAmountResultDto.setFreeType(ChargeFreeTypeEnum.CALCULATE_FREE.getValue());
            }
            calculateAmountResultDto.setAmount(totalAmount);
            calculateAmountResultDto.setChargeDuration(chargeDuration);
            if ((parkingDuration - chargeDuration) < 0) {
                calculateAmountResultDto.setFreeDuration(0L);
            } else {
                calculateAmountResultDto.setFreeDuration(parkingDuration - chargeDuration);
            }
            objectResultDto.setData(calculateAmountResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            objectResultDto.failed();
            log.error("计算停车金额失败" + e.getMessage());
        }
        return objectResultDto;
    }

    /**
     * 收费规则试算
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<CalculateAmountViewResultDto> chargeRuleCalculateTry(@FluentValid({ChargeRuleCalculateTryRequestDtoValidator.class}) ChargeRuleCalculateTryRequestDto requestDto) {
        ObjectResultDto<CalculateAmountViewResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            CalculateAmountViewResultDto calculateAmountResultDto = new CalculateAmountViewResultDto();
            if (requestDto == null) {
                calculateAmountResultDto.setAmount(0);
                calculateAmountResultDto.setFreeDuration(0L);
                calculateAmountResultDto.setChargeDuration(0L);
                objectResultDto.setData(calculateAmountResultDto);
                objectResultDto.success();
                return objectResultDto;
            }
            ChargeRuleInfoViewResultDto chargeRuleInfoViewResultDto = modelMapper.map(requestDto, ChargeRuleInfoViewResultDto.class);
            if (!requestDto.getTryCarType().equals(chargeRuleInfoViewResultDto.getCarType()) && !chargeRuleInfoViewResultDto.getCarType().equals(CarTypeEnum.ALL_TYPE.getValue())) {
                objectResultDto.failed(ChargeConstant.TRY_CAR_TYPE_NOT_MATCH);
                return objectResultDto;
            }
            CalculateAmountRequestDto calculateAmountRequestDto = new CalculateAmountRequestDto();
            chargeRuleInfoViewResultDto.setOnlineTime(requestDto.getTryStartTime());
            chargeRuleInfoViewResultDto.setOfflineTime(requestDto.getTryEndTime());
            calculateAmountRequestDto.setTenantId(FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity().getTenantId());
            calculateAmountRequestDto.setStartTime(requestDto.getTryStartTime());
            calculateAmountRequestDto.setEndTime(requestDto.getTryEndTime());
            List<ChargeRuleInfoViewResultDto> parkingChargeRules = new ArrayList<>();
            parkingChargeRules.add(chargeRuleInfoViewResultDto);
            calculateAmountRequestDto.setParkingChargeRules(parkingChargeRules);
            ObjectResultDto<CalculateAmountResultDto> calculateAmountResult = calculateAmount(calculateAmountRequestDto);
            if (calculateAmountResult.isSuccess() && calculateAmountResult.getData() != null) {
                CalculateAmountResultDto data = calculateAmountResult.getData();
                calculateAmountResultDto.setAmount(data.getAmount());
                calculateAmountResultDto.setChargeDuration(data.getChargeDuration());
                calculateAmountResultDto.setFreeDuration(data.getFreeDuration());
                calculateAmountResultDto.setFreeType(data.getFreeType());
                objectResultDto.setData(calculateAmountResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            objectResultDto.failed();
            log.error("收费规则试算失败" + e.getMessage());
        }
        return objectResultDto;
    }

    /**
     * 计算计费时长
     *
     * @param amount              Integer
     * @param chargeRuleResultDto ChargeRuleInfoViewResultDto
     * @param startTime           DateTime
     * @param endTime             DateTime
     * @return Long
     */
    private Long calculateChargeDuration(Integer amount, ChargeRuleInfoViewResultDto chargeRuleResultDto,
                                         DateTime startTime, DateTime endTime) {
        Long chargeDuration = 0L;
        Duration duration = new Duration(startTime, endTime);
        Long parkingDurationSecond = duration.getMillis() / 1000L;
        Long parkingDurationMinute = parkingDurationSecond / 60;
        if (amount == 0) {
            chargeDuration += 0L;
        } else {
            if (chargeRuleResultDto.getRuleType().equals(ChargeRuleTypeEnum.GIST_TO_TIMES.getValue())
                    || chargeRuleResultDto.getOverFreeTime() || chargeRuleResultDto.getRuleType().equals(ChargeRuleTypeEnum.GIST_TO_DATE_TIMES.getValue())) {
                //超过免费时长收费
                chargeDuration += parkingDurationSecond;
            } else {
                //超过免费时长不收费
                Integer freeTime = chargeRuleResultDto.getFreeTime();
                if (parkingDurationMinute > freeTime) {
                    // 停车时长大于免费时长
                    chargeDuration += parkingDurationSecond - (freeTime * 60);
                }
            }
        }
        return chargeDuration;
    }

    /**
     * 给一段时间和规则,计算该规则下该时间段的费用和收费时长
     *
     * @param parkingChargeRuleResultDto
     * @param partStartTime
     * @param partEndTime
     * @return
     */
    private Object[] calculatePartTime(ChargeRuleInfoViewResultDto parkingChargeRuleResultDto, Date partStartTime, Date partEndTime) {
        Object[] objects = new Object[2];
        CalculateAmountStrategy calculateAmountStrategy = strategyService.createCalculateStrategy(parkingChargeRuleResultDto.getRuleType());
        Integer amount = calculateAmountStrategy.calculateAmount(new DateTime(partStartTime), new DateTime(partEndTime), parkingChargeRuleResultDto);
        Long chargeDuration = calculateChargeDuration(amount, parkingChargeRuleResultDto, new DateTime(partStartTime), new DateTime(partEndTime));
        objects[0] = amount;
        objects[1] = chargeDuration;
        return objects;
    }

}
