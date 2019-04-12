package com.zoeeasy.cloud.integration.validator.appoint;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.integration.appoint.dto.request.CustomerAppointOrderPlaceRequestDto;
import com.zoeeasy.cloud.integration.appoint.validator.AppointOrderPlaceRequestDtoValidator;
import com.zoeeasy.cloud.integration.cts.IntegrationConstant;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zwq
 * @date 2018/10/15 0015
 */
@Component
public class AppointOrderPlaceRequestDtoValidatorImpl extends ValidatorHandler<CustomerAppointOrderPlaceRequestDto> implements AppointOrderPlaceRequestDtoValidator {

    @Override
    public boolean validate(ValidatorContext context, CustomerAppointOrderPlaceRequestDto requestDto) {

        if (requestDto.getScheduleTime().compareTo(new Date()) < 0) {
            throw new ValidationException(IntegrationConstant.SCHEDULETIME_LESS_THAN_NOW);
        }

        return true;
    }
}
