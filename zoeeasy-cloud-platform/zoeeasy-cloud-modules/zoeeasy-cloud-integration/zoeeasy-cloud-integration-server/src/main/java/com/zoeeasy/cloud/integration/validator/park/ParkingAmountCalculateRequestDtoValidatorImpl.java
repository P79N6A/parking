package com.zoeeasy.cloud.integration.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.PlateNumberUtil;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.charge.cts.ChargeConstant;
import com.zoeeasy.cloud.integration.park.dto.request.ParkingAmountCalculateRequestDto;
import com.zoeeasy.cloud.integration.park.validator.ParkingAmountCalculateRequestDtoValidator;
import org.springframework.stereotype.Component;

/**
 * @author AkeemSuper
 * @date 2018/10/15 0015
 */
@Component
public class ParkingAmountCalculateRequestDtoValidatorImpl extends ValidatorHandler<ParkingAmountCalculateRequestDto> implements ParkingAmountCalculateRequestDtoValidator {

    @Override
    public boolean validate(ValidatorContext context, ParkingAmountCalculateRequestDto requestDto) {
        if (requestDto.getEndTime().before(requestDto.getStartTime())) {
            throw new ValidationException(ChargeConstant.PASS_TIME_ERROR);
        }

        if (StringUtils.isNotEmpty(requestDto.getPlateNumber()) && !PlateNumberUtil.isPlateNumber(requestDto.getPlateNumber())) {
            throw new ValidationException(ChargeConstant.PLATE_NUMBER_NOT_STANDARD);
        }
        return true;
    }
}
