package com.zoeeasy.cloud.charge.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.PlateNumberUtil;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.charge.cts.ChargeConstant;
import com.zoeeasy.cloud.charge.park.dto.request.ParkChargeRuleCalculateTryRequestDto;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingChargeRuleTryRequestDto;
import com.zoeeasy.cloud.charge.park.dto.validator.ParkChargeRuleCalculateTryValidator;
import com.zoeeasy.cloud.core.enums.CarTypeEnum;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/12/10 0010
 */
@Component
public class ParkChargeRuleCalculateTryValidatorImpl extends ValidatorHandler<ParkChargeRuleCalculateTryRequestDto> implements ParkChargeRuleCalculateTryValidator {

    @Override
    public boolean accept(ValidatorContext context, ParkChargeRuleCalculateTryRequestDto requestDto) {
        if (StringUtils.isNotEmpty(requestDto.getTryPlateNumber())) {
            if (!PlateNumberUtil.isPlateNumber(requestDto.getTryPlateNumber())) {
                throw new ValidationException(ChargeConstant.PLATE_NUMBER_NOT_STANDARD);
            }
        }
        CarTypeEnum parse = CarTypeEnum.parse(requestDto.getTryCarType());
        if (null == parse || parse.equals(CarTypeEnum.ALL_TYPE)) {
            throw new ValidationException(ChargeConstant.CHARGE_CAR_TYPE_NOT_STANDARD);
        }
        List<ParkingChargeRuleTryRequestDto> parkingChargeRuleTry = requestDto.getParkingChargeRuleTry();
        if (CollectionUtils.isEmpty(parkingChargeRuleTry)) {
            throw new ValidationException(ChargeConstant.PARKING_CHARGE_RULE_TRY_LIST_EMPTY);
        }
        if (requestDto.getTryStartTime().after(requestDto.getTryEndTime())) {
            throw new ValidationException(ChargeConstant.TRY_PARKING_TIME_NOT_STANDARD);
        }
        return true;
    }
}
