package com.zoeeasy.cloud.pms.validator.specialvehicle;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.core.enums.CarTypeEnum;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import com.zoeeasy.cloud.pms.specialvehicle.validator.CarTypeEnumValidator;
import org.springframework.stereotype.Component;

/**
 * @date: 2018/10/22.
 * @author：zm
 */
@Component
public class CarTypeEnumValidatorImpl extends ValidatorHandler<Integer> implements CarTypeEnumValidator {

    @Override
    public boolean accept(ValidatorContext context, Integer target) {
        if (target != null) {
            CarTypeEnum carTypeEnum = CarTypeEnum.parse(target);
            if (carTypeEnum == null) {
                throw new ValidationException(SpecialVehicleConstant.SPECIAL_VEHICLE_CAR_TYPE_ERROR);
            }
        }
        return true;
    }
}
