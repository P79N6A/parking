package com.zoeeasy.cloud.charge.validator.chg;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.charge.chg.dto.request.ChargeRuleTimeUpdateRequestDto;
import com.zoeeasy.cloud.charge.chg.dto.request.ChargeRuleUpdateRequestDto;
import com.zoeeasy.cloud.charge.chg.validator.ChargeRuleUpdateRequestDtoValidator;
import com.zoeeasy.cloud.charge.cts.ChargeConstant;
import com.zoeeasy.cloud.charge.enums.ChargeResultEnum;
import com.zoeeasy.cloud.charge.enums.ChargeRuleTypeEnum;
import com.zoeeasy.cloud.core.cst.Const;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/10/29 0029
 */
@Component
public class ChargeRuleUpdateRequestDtoValidatorImpl extends ValidatorHandler<ChargeRuleUpdateRequestDto> implements ChargeRuleUpdateRequestDtoValidator {

    @Override
    public boolean validate(ValidatorContext context, ChargeRuleUpdateRequestDto requestDto) {
        if (requestDto.getId() == null) {
            throw new ValidationException(ChargeConstant.CHARGE_RULE_ID_EMPTY);
        }
        ChargeRuleTypeEnum chargeRuleType = ChargeRuleTypeEnum.parse(requestDto.getRuleType());
        if (null == chargeRuleType) {
            throw new ValidationException(ChargeConstant.CHARGE_RULE_TYPE_NOT_STANDARD);
        }
        if (requestDto.getFreeTime() == null) {
            requestDto.setFreeTime(0);
        }
        //分时段计费时段有效性判断
        if (chargeRuleType.equals(ChargeRuleTypeEnum.GIST_TO_DATE) || chargeRuleType.equals(ChargeRuleTypeEnum.GIST_TO_DATE_TIMES)) {
            if (requestDto.getPartTimes() == null || requestDto.getPartTimes().isEmpty()) {
                throw new ValidationException(ChargeResultEnum.CHARGE_RULE_PART_TIMES_EMPTY.getComment());
            }
            if (null == requestDto.getUnitTime()) {
                requestDto.setUnitTime(Const.HALF_HOUR);
            }
            if (!requestDto.getUnitTime().equals(Const.HALF_HOUR) && !requestDto.getUnitTime().equals(Const.HOUR)) {
                throw new ValidationException(ChargeResultEnum.CHARGE_RULE_UNIT_TIME_NOT_STANDARD.getComment());
            }
            List<ChargeRuleTimeUpdateRequestDto> partTimes = requestDto.getPartTimes();
            if (CollectionUtils.isEmpty(partTimes)) {
                throw new ValidationException(ChargeResultEnum.CHARGE_RULE_PART_TIMES_EMPTY.getComment());
            }
            for (int i = 0; i < partTimes.size() - 1; i++) {
                ChargeRuleTimeUpdateRequestDto timeItem = partTimes.get(i);
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
        if (requestDto.getInTimeTop() && requestDto.getDayTopAmount() == null) {
            throw new ValidationException(ChargeConstant.DAY_TOP_AMOUNT_EMPTY);
        }
        if (requestDto.getTopStatus() && requestDto.getTopAmount() == null) {
            throw new ValidationException(ChargeConstant.TOP_AMOUNT_EMPTY);
        }
        return true;
    }
}
