package com.zoeeasy.cloud.charge.validator.appointrule;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.charge.appointrule.dto.request.ParkingAppointRuleUpdateRequestDto;
import com.zoeeasy.cloud.charge.appointrule.validator.ParkingAppointRuleUpdateRequestDtoValidator;
import com.zoeeasy.cloud.charge.cts.AppointConstant;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

/**
 * @author zwq
 * @date 2018/10/15 0015
 */
@Component
public class ParkingAppointRuleUpdateRequestDtoValidatorImpl extends ValidatorHandler<ParkingAppointRuleUpdateRequestDto> implements ParkingAppointRuleUpdateRequestDtoValidator {

    @Override
    public boolean validate(ValidatorContext context, ParkingAppointRuleUpdateRequestDto requestDto) {
        if (CollectionUtils.isEmpty(requestDto.getRules())) {
            throw new ValidationException(AppointConstant.RULES_EMPTY);
        }

        return true;
    }
}
