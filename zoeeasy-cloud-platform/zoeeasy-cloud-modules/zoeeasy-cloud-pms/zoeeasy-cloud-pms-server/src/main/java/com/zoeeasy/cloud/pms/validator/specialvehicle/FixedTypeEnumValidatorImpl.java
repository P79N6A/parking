package com.zoeeasy.cloud.pms.validator.specialvehicle;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.enums.FixedTypeEnum;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import com.zoeeasy.cloud.pms.specialvehicle.validator.FixedTypeEnumValidator;
import org.springframework.stereotype.Component;

/**
 * @date: 2018/10/22.
 * @author：zm
 */
@Component
public class FixedTypeEnumValidatorImpl extends ValidatorHandler<Integer> implements FixedTypeEnumValidator {

    @Override
    public boolean accept(ValidatorContext context, Integer target) {
        if (target != null) {
            FixedTypeEnum fixedTypeEnum = FixedTypeEnum.parse(target);
            if (fixedTypeEnum == null) {
                throw new ValidationException(SpecialVehicleConstant.SPECIAL_VEHICLE_FIXED_TYPE_ERROR);

            }
        }
        return true;
    }
}
