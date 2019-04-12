package com.zoeeasy.cloud.inspect.validator;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.zoeeasy.cloud.inspect.park.dto.request.ParkingByUserRequestDto;
import com.zoeeasy.cloud.inspect.platform.validator.ParkingByUserRequestDtoValidator;
import org.springframework.stereotype.Component;

/**
 * 根据用户Id获取停车场
 *
 * @Date: 2018/11/19
 * @author: lhj
 */
@Component
public class ParkingByUserRequestDtoValidatorImpl extends ValidatorHandler<ParkingByUserRequestDto> implements ParkingByUserRequestDtoValidator {

    @Override
    public boolean validate(ValidatorContext context, ParkingByUserRequestDto requestDto) {
        return true;
    }
}
