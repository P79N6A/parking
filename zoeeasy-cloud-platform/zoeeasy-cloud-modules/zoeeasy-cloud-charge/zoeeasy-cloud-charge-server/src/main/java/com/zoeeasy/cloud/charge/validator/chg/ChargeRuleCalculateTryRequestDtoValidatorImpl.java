package com.zoeeasy.cloud.charge.validator.chg;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.charge.chg.dto.request.ChargeRuleCalculateTryRequestDto;
import com.zoeeasy.cloud.charge.chg.dto.request.ChargeRuleTimeAddRequestDto;
import com.zoeeasy.cloud.charge.chg.validator.ChargeRuleCalculateTryRequestDtoValidator;
import com.zoeeasy.cloud.charge.cts.ChargeConstant;
import com.zoeeasy.cloud.charge.enums.ChargeResultEnum;
import com.zoeeasy.cloud.charge.enums.ChargeRuleTypeEnum;
import com.zoeeasy.cloud.charge.enums.HolidayTypeEnum;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.core.enums.CarTypeEnum;
import com.zoeeasy.cloud.core.enums.LicensePlateColorEnum;
import com.zoeeasy.cloud.core.enums.ParkingLevelEnum;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/11/28 0028
 */
@Component
public class ChargeRuleCalculateTryRequestDtoValidatorImpl extends ValidatorHandler<ChargeRuleCalculateTryRequestDto> implements ChargeRuleCalculateTryRequestDtoValidator {

    @Override
    public boolean accept(ValidatorContext context, ChargeRuleCalculateTryRequestDto requestDto) {
        LicensePlateColorEnum plateColorStyleEnum = LicensePlateColorEnum.parse(requestDto.getTryPlateColor());
        if (plateColorStyleEnum == null) {
            throw new ValidationException(ChargeConstant.PLATE_COLOR_NOT_STANDARD);
        }
        CarTypeEnum tryCarType = CarTypeEnum.parse(requestDto.getTryCarType());
        if (tryCarType == null || CarTypeEnum.ALL_TYPE.getValue().equals(tryCarType.getValue())) {
            throw new ValidationException(ChargeConstant.TRY_CAR_TYPE_NOT_STANDARD);
        }
        if (requestDto.getTryEndTime().compareTo(requestDto.getTryStartTime()) < 0) {
            throw new ValidationException(ChargeConstant.TRY_PARKING_TIME_NOT_STANDARD);
        }
        if (requestDto.getFreeTime() == null) {
            requestDto.setFreeTime(0);
        }
        ChargeRuleTypeEnum chargeRuleType = ChargeRuleTypeEnum.parse(requestDto.getRuleType());
        if (chargeRuleType == null) {
            throw new ValidationException(ChargeResultEnum.CHARGE_RULE_RULE_TYPE_EMPTY.getComment());
        }
        CarTypeEnum carTypeEnum = CarTypeEnum.parse(requestDto.getCarType());
        if (carTypeEnum == null) {
            throw new ValidationException(ChargeResultEnum.CHARGE_RULE_CAR_TYPE_EMPTY.getComment());
        }
        ParkingLevelEnum parkingLevelEnum = ParkingLevelEnum.parse(requestDto.getParkingLevel());
        if (parkingLevelEnum == null) {
            throw new ValidationException(ChargeResultEnum.CHARGE_RULE_PARKING_LEVEL_EMPTY.getComment());
        }
        HolidayTypeEnum holidayTypeEnum = HolidayTypeEnum.parse(requestDto.getHolidayType());
        if (holidayTypeEnum == null) {
            throw new ValidationException(ChargeResultEnum.CHARGE_RULE_HOLIDAY_TYPE_EMPTY.getComment());
        }
        //分时段计费时段有效性判断
        if (chargeRuleType.equals(ChargeRuleTypeEnum.GIST_TO_DATE) || chargeRuleType.equals(ChargeRuleTypeEnum.GIST_TO_DATE_TIMES)) {
            if (requestDto.getPartTimes() == null || requestDto.getPartTimes().isEmpty()) {
                throw new ValidationException(ChargeResultEnum.CHARGE_RULE_PART_TIMES_EMPTY.getComment());
            }
            if (requestDto.getUnitTime() == null) {
                //设置最小计时单位默认值
                requestDto.setUnitTime(Const.HALF_HOUR);
            }
            if (!requestDto.getUnitTime().equals(Const.HALF_HOUR) && !requestDto.getUnitTime().equals(Const.HOUR)) {
                throw new ValidationException(ChargeResultEnum.CHARGE_RULE_UNIT_TIME_NOT_STANDARD.getComment());
            }
            List<ChargeRuleTimeAddRequestDto> partTimes = requestDto.getPartTimes();
            for (int i = 0; i < partTimes.size() - 1; i++) {
                ChargeRuleTimeAddRequestDto timeItem = partTimes.get(i);
                if (timeItem.getTimePart() == null || timeItem.getTimePart().compareTo(0) < 0) {
                    throw new ValidationException(ChargeResultEnum.CHARGE_RULE_PART_TIMES_TIME_ERROR.getComment());
                }
                if (timeItem.getPrice() == null || timeItem.getPrice().compareTo(0) < 0) {
                    throw new ValidationException(ChargeResultEnum.CHARGE_RULE_PART_TIMES_PRICE_ERROR.getComment());
                }
                for (int j = partTimes.size() - 1; j > i; j--) {
                    if (partTimes.get(j).equals(partTimes.get(i))) {
                        throw new ValidationException(ChargeResultEnum.CHARGE_RULE_PART_TIMES_REPEAT.getComment());
                    }
                }
            }
        }
        //日夜时分计时的日期有效性
        if (chargeRuleType.equals(ChargeRuleTypeEnum.GIST_TO_DATE_TIMES)) {
            if (StringUtils.isEmpty(requestDto.getPerStartTime()) || StringUtils.isEmpty(requestDto.getPerEndTime())) {
                throw new ValidationException(ChargeResultEnum.CHARGE_RULE_TIMES_COUNT_ERROR.getComment());
            }
            if (StringUtils.isEmpty(requestDto.getTimesStartTime()) || StringUtils.isEmpty(requestDto.getTimesEndTime())) {
                throw new ValidationException(ChargeResultEnum.CHARGE_RULE_TIMES_RANGE_ERROR.getComment());
            }
        }
        return true;
    }
}
