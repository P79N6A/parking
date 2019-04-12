package com.zoeeasy.cloud.charge.validator.chg;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.charge.chg.dto.request.CalculateAmountRequestDto;
import com.zoeeasy.cloud.charge.chg.validator.CalculateAmountRequestDtoValidator;
import com.zoeeasy.cloud.charge.cts.ChargeConstant;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

/**
 * @author AkeemSuper
 * @date 2018/10/13 0013
 */
@Component
public class CalculateAmountRequestDtoValidatorImpl extends ValidatorHandler<CalculateAmountRequestDto> implements CalculateAmountRequestDtoValidator {

    @Override
    public boolean validate(ValidatorContext context, CalculateAmountRequestDto requestDto) {
        if (CollectionUtils.isEmpty(requestDto.getParkingChargeRules())) {
            throw new ValidationException(ChargeConstant.CHARGE_RULE_NOT_EMPTY);
        }
        if (requestDto.getEndTime().before(requestDto.getStartTime())) {
            throw new ValidationException(ChargeConstant.PASS_TIME_ERROR);
        }
        return true;
    }

}
