package com.zoeeasy.cloud.pms.validator.specialvehicle;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.enums.CarColorEnum;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import com.zoeeasy.cloud.pms.specialvehicle.validator.CarColorEnumValidator;
import org.springframework.stereotype.Component;

/**
 * @date: 2018/10/22.
 * @author：zm
 */
@Component
public class CarColorEnumValidatorImpl extends ValidatorHandler<Integer> implements CarColorEnumValidator {

    @Override
    public boolean accept(ValidatorContext context, Integer target) {
        if (target != null) {
            CarColorEnum carColorEnum = CarColorEnum.parse(target);
            if (carColorEnum == null) {
                throw new ValidationException(SpecialVehicleConstant.SPECIAL_VEHICLE_CAR_COLOR_ERROR);
            }
        }
        return true;
    }
}
