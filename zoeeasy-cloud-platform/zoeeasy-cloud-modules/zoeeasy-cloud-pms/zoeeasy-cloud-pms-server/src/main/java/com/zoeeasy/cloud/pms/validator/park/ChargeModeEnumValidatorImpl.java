package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.core.enums.ChargeModeEnum;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.validator.ChargeModeEnumValidator;
import org.springframework.stereotype.Component;

/**
 * Created by song on 2018/10/19.
 */
@Component
public class ChargeModeEnumValidatorImpl extends ValidatorHandler<Integer> implements ChargeModeEnumValidator {

    @Override
    public boolean accept(ValidatorContext context, Integer target) {
        if (target != null && !target.equals(ParkingConstant.SELECTED)) {
            ChargeModeEnum chargeModeEnum = ChargeModeEnum.parse(target);
            if (chargeModeEnum == null) {
                throw new ValidationException(ParkingConstant.CHARGE_MODE_ENUM_ERROR);
            }
        }
        return true;
    }
}
