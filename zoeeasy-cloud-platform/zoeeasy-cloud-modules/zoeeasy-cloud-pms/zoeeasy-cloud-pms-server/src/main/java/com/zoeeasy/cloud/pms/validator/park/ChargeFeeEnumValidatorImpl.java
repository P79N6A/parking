package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.enums.ChargeFeeEnum;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.validator.ChargeFeeEnumValidator;
import org.springframework.stereotype.Component;

/**
 * Created by song on 2018/10/19.
 */
@Component
public class ChargeFeeEnumValidatorImpl extends ValidatorHandler<Integer> implements ChargeFeeEnumValidator {

    @Override
    public boolean accept(ValidatorContext context, Integer target) {
        if (target != null){
            ChargeFeeEnum chargeFeeEnum = ChargeFeeEnum.parse(target);
            if (chargeFeeEnum == null){
                throw new ValidationException(ParkingConstant.CHARGE_FEE_ENUM_ERROR);
            }
        }
        return true;
    }
}
