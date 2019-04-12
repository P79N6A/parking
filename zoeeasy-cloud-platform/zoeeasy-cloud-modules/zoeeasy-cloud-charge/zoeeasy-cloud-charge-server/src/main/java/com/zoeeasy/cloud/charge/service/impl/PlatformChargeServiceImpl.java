package com.zoeeasy.cloud.charge.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.NumberUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleInfoViewResultDto;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleTimeViewResultDto;
import com.zoeeasy.cloud.charge.domain.*;
import com.zoeeasy.cloud.charge.enums.ChargeRuleTypeEnum;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingCurrentChargeInfoRequestDto;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingChargeRuleForUserParkingRecordRequestDto;
import com.zoeeasy.cloud.charge.park.dto.result.ParkingCurrentChargeInfoResultDto;
import com.zoeeasy.cloud.charge.platform.PlatformChargeService;
import com.zoeeasy.cloud.charge.service.*;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.core.enums.CarTypeEnum;
import com.zoeeasy.cloud.core.enums.ParkingLevelEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author AkeemSuper
 * @date 2018/11/2 0002
 */
@Service("platformChargeService")
@Slf4j
public class PlatformChargeServiceImpl implements PlatformChargeService {

    @Autowired
    private ChargeRuleCrudService chargeRuleCrudService;

    @Autowired
    private ChargeRuleTimeCrudService chargeRuleTimeCrudService;

    @Autowired
    private ParkingChargeRuleCrudService parkingChargeRuleCrudService;

    @Autowired
    private HolidayCalendarCrudService holidayCalendarCrudService;

    @Autowired
    private HolidayScheduleCrudService holidayScheduleCrudService;

    @Autowired
    private ModelMapper modelMapper;

    private static final String FREE_START_TIME = "00:00";

    private static final String FREE_END_TIME = "23:59";

    /**
     * 用户停车记录模块查询收费规则
     *
     * @param requestDto
     */
    @Override
    public List<ChargeRuleInfoViewResultDto> getChargeRuleForUserParkingRecord(ParkingChargeRuleForUserParkingRecordRequestDto requestDto) {
        List<ChargeRuleInfoViewResultDto> list = new ArrayList<>();
        try {
            Wrapper<ParkingChargeRuleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("tenantId", requestDto.getTenantId());
            entityWrapper.eq("parkingId", requestDto.getParkingId());
            entityWrapper.le("onlineTime", requestDto.getEndTime());
            entityWrapper.gt("offlineTime", requestDto.getStartTime());
            entityWrapper.orderBy("onlineTime", true);
            if (requestDto.getCarStyle() == null) {
                requestDto.setCarStyle(CarTypeEnum.ALL_TYPE.getValue());
            }
            List<ParkingChargeRuleEntity> parkingChargeRuleEntities = parkingChargeRuleCrudService.selectChargeRule(entityWrapper);
            if (!parkingChargeRuleEntities.isEmpty()) {
                for (ParkingChargeRuleEntity parkingChargeRuleEntity : parkingChargeRuleEntities) {
                    ChargeRuleInfoViewResultDto parkingChargeRuleResultDto = modelMapper.map(parkingChargeRuleEntity, ChargeRuleInfoViewResultDto.class);
                    ChargeRuleEntity selectByRuleId = chargeRuleCrudService.selectByRuleId(parkingChargeRuleEntity.getRuleId(), requestDto.getTenantId());
                    //车辆类型停车场等级
                    if (null != selectByRuleId && (requestDto.getParkingLevel().equals(selectByRuleId.getParkingLevel())
                            || ParkingLevelEnum.EQUALLY.getValue().equals(requestDto.getParkingLevel()) || ParkingLevelEnum.EQUALLY
                            .getValue().equals(selectByRuleId.getParkingLevel())) && (
                            CarTypeEnum.ALL_TYPE.getValue().equals(requestDto.getCarStyle()) || requestDto.getCarStyle()
                                    .equals(selectByRuleId.getCarType()) || CarTypeEnum.ALL_TYPE.getValue()
                                    .equals(selectByRuleId.getCarType()))) {
                        modelMapper.map(selectByRuleId, parkingChargeRuleResultDto);
                        List<ChargeRuleTimeEntity> chargeRuleTimeEntities = chargeRuleTimeCrudService.findRuleId(parkingChargeRuleEntity.getRuleId(), requestDto.getTenantId());
                        if (CollectionUtils.isNotEmpty(chargeRuleTimeEntities)) {
                            List<ChargeRuleTimeViewResultDto> chargeRuleTimeResultDtos = modelMapper.map(chargeRuleTimeEntities, new TypeToken<List<ChargeRuleTimeViewResultDto>>() {
                            }.getType());
                            parkingChargeRuleResultDto.setPartTimes(chargeRuleTimeResultDtos);
                        }
                        list.add(parkingChargeRuleResultDto);
                    }
                }
            }
            return list;
        } catch (Exception e) {
            log.error("用户停车记录模块查询收费规则失败" + e.getMessage());
        }
        return list;
    }

    /**
     * 带租户id获取停车场收费信息
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingCurrentChargeInfoResultDto> getParkingCurrentChargeInfo(ParkingCurrentChargeInfoRequestDto requestDto) {
        ObjectResultDto<ParkingCurrentChargeInfoResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingCurrentChargeInfoResultDto parkingChargeInfo = new ParkingCurrentChargeInfoResultDto();
            Date baseTime;
            if (requestDto.getBaseTime() != null) {
                baseTime = requestDto.getBaseTime();
            } else {
                baseTime = DateUtils.now();
            }
            //查找该时段内有效的收费规则
            EntityWrapper<ParkingChargeRuleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parkingId", requestDto.getParkingId());
            entityWrapper.le("onlineTime", baseTime);
            entityWrapper.gt("offlineTime", baseTime);
            entityWrapper.orderBy("onlineTime", true);
            List<ParkingChargeRuleEntity> parkingChargeRuleEntities = parkingChargeRuleCrudService.selectChargeRule(entityWrapper);
            if (parkingChargeRuleEntities.isEmpty()) {
                //如果当前无有效收费规则
                parkingChargeInfo.setNowFree(Boolean.TRUE);
                parkingChargeInfo.setFreeStartTime(FREE_START_TIME);
                parkingChargeInfo.setFreeEndTime(FREE_END_TIME);
                parkingChargeInfo.setDiscriminateLargeSmall(false);
                parkingChargeInfo.setLargeVehicleRule("");
                parkingChargeInfo.setSmallVehicleRule("");
            } else {
                //获取收费规则
                List<Long> ids = parkingChargeRuleEntities.stream().map(ParkingChargeRuleEntity::getRuleId).collect(Collectors.toList());
                List<ChargeRuleEntity> currentParkingChargeRuleList = chargeRuleCrudService.findByIds(ids);
                if (!CollectionUtils.isEmpty(currentParkingChargeRuleList)) {
                    boolean flag = Boolean.TRUE;
                    //判断当前基准日期是否是节假日
                    EntityWrapper<HolidayCalendarEntity> holidayCalendarEntityWrapper = new EntityWrapper<>();
                    holidayCalendarEntityWrapper.eq("tenantId", requestDto.getTenantId());
                    holidayCalendarEntityWrapper.eq("date", DateUtils.formatDate(baseTime, Const.FORMAT_DATE));
                    HolidayCalendarEntity holidayCalendarEntity =
                            holidayCalendarCrudService.selectDateAndTenantId(holidayCalendarEntityWrapper);
                    if (holidayCalendarEntity != null) {
                        HolidayScheduleEntity holidayScheduleEntity =
                                holidayScheduleCrudService.findById(holidayCalendarEntity.getHolidayId());
                        Optional<ChargeRuleEntity> chargeRuleEntityOptional =
                                currentParkingChargeRuleList.stream().
                                        filter(chargeRule ->
                                                (chargeRule.getHolidayType().equals(holidayScheduleEntity.getHolidayType())
                                                        && chargeRule.getRuleType().equals(ChargeRuleTypeEnum.GIST_TO_FREE.getValue())))
                                        .findFirst();
                        if (chargeRuleEntityOptional.isPresent()) {
                            flag = Boolean.FALSE;
                            parkingChargeInfo.setNowFree(Boolean.TRUE);
                            parkingChargeInfo.setFreeStartTime(FREE_START_TIME);
                            parkingChargeInfo.setFreeEndTime(FREE_END_TIME);
                        }
                    }
                    if (flag) {
                        //找到第一条计时收费的规则
                        Optional<ChargeRuleEntity>
                                timedChargeRuleOptional = currentParkingChargeRuleList.stream().filter(
                                item ->
                                        (item.getRuleType().equals(ChargeRuleTypeEnum.GIST_TO_DATE.getValue())
                                                || item.getRuleType().equals(ChargeRuleTypeEnum.GIST_TO_DATE_TIMES.getValue()))).findFirst();

                        if (timedChargeRuleOptional.isPresent()) {
                            ChargeRuleEntity timedChargeRule = timedChargeRuleOptional.get();
                            parkingChargeInfo.setNowFree(Boolean.FALSE);
                            parkingChargeInfo.setFreeTimeLength(timedChargeRule.getFreeTime());
                            if (timedChargeRule.getInTimeTop()) {
                                parkingChargeInfo.setDayTopAmount(timedChargeRule.getDayTopAmount());
                            }
                            parkingChargeInfo.setChargeRule(getChargeRuleDescription(timedChargeRule));
                        } else {
                            //找到第一条计次
                            Optional<ChargeRuleEntity> countChargeRuleOptional
                                    = currentParkingChargeRuleList.stream().filter(
                                    item -> (item.getRuleType().equals(ChargeRuleTypeEnum.GIST_TO_TIMES.getValue()))).findFirst();
                            if (countChargeRuleOptional.isPresent()) {
                                //如果当前无有效收费规则
                                parkingChargeInfo.setNowFree(Boolean.FALSE);
                                parkingChargeInfo.setChargeRule(getChargeRuleDescription(countChargeRuleOptional.get()));
                            } else {
                                //找到第一条免费
                                Optional<ChargeRuleEntity> freeChargeRuleOptional =
                                        currentParkingChargeRuleList.stream().filter(
                                                item -> (
                                                        item.getRuleType().equals(ChargeRuleTypeEnum.GIST_TO_FREE.getValue()))).findFirst();
                                if (freeChargeRuleOptional.isPresent()) {
                                    //如果当前无有效收费规则
                                    parkingChargeInfo.setNowFree(Boolean.TRUE);
                                    parkingChargeInfo.setFreeStartTime(FREE_START_TIME);
                                    parkingChargeInfo.setFreeEndTime(FREE_END_TIME);
                                }
                            }
                        }
                    }
                    //找到分车型的收费规则
                    List<ChargeRuleEntity>
                            largeSmallRuleList = currentParkingChargeRuleList.stream().filter(
                            item ->
                                    (item.getCarType().equals(CarTypeEnum.SMALL_CAR.getValue())
                                            || item.getCarType().equals(CarTypeEnum.BIG_CAR.getValue()))).collect(Collectors.toList());
                    if (CollectionUtils.isNotEmpty(largeSmallRuleList)) {

                        parkingChargeInfo.setDiscriminateLargeSmall(true);
                        //找到第一条大型车收费规则
                        Optional<ChargeRuleEntity>
                                largeChargeRuleOptional = largeSmallRuleList.stream().filter(
                                item -> (item.getCarType().equals(CarTypeEnum.BIG_CAR.getValue()))).findFirst();
                        if (largeChargeRuleOptional.isPresent()) {
                            parkingChargeInfo.setLargeVehicleRule(getChargeRuleDescription(largeChargeRuleOptional.get()));
                        } else {
                            parkingChargeInfo.setLargeVehicleRule("");
                        }
                        //找到第一条小型车收费规则
                        Optional<ChargeRuleEntity>
                                smallChargeRuleOptional = largeSmallRuleList.stream().filter(
                                item -> (item.getCarType().equals(CarTypeEnum.SMALL_CAR.getValue()))).findFirst();
                        if (smallChargeRuleOptional.isPresent()) {
                            parkingChargeInfo.setSmallVehicleRule(getChargeRuleDescription(smallChargeRuleOptional.get()));
                        } else {
                            parkingChargeInfo.setSmallVehicleRule("");
                        }
                    } else {
                        parkingChargeInfo.setDiscriminateLargeSmall(false);
                    }
                }
            }
            objectResultDto.setData(parkingChargeInfo);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("停车场当前收费信息获取失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取收费描述信息
     *
     * @param chargeRule chargeRule
     */
    private String getChargeRuleDescription(ChargeRuleEntity chargeRule) {
        if (chargeRule == null) {
            return "";
        }
        String chargeDescription = "";
        ChargeRuleTypeEnum chargeRuleType = ChargeRuleTypeEnum.parse(chargeRule.getRuleType());
        switch (chargeRuleType) {
            //获取第一个收费时段
            case GIST_TO_DATE:
            case GIST_TO_DATE_TIMES:
                List<ChargeRuleTimeEntity> chargeRuleTimeEntities = chargeRuleTimeCrudService.findRuleId(chargeRule.getId(), chargeRule.getTenantId());
                if (CollectionUtils.isNotEmpty(chargeRuleTimeEntities)) {
                    ChargeRuleTimeEntity chargeRuleTime = chargeRuleTimeEntities.get(0);
                    chargeDescription = (chargeRuleTime.getPrice() != null ? (NumberUtils.amountFen2Yuan(BigDecimal.valueOf(chargeRuleTime.getPrice()))) : "0") + "元/小时";
                }
                break;
            case GIST_TO_FREE:
                chargeDescription = "免费";
                break;
            case GIST_TO_TIMES:
                chargeDescription = NumberUtils.amountFen2Yuan(chargeRule.getPerPrice()) + "元/次";
                break;
            default:
                break;
        }
        return chargeDescription;
    }
}
