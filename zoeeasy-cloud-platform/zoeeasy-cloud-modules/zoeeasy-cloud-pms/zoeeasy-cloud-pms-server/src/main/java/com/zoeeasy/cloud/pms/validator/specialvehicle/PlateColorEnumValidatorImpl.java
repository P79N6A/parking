package com.zoeeasy.cloud.pms.validator.specialvehicle;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.enums.PlateColorEnum;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import com.zoeeasy.cloud.pms.specialvehicle.validator.PlateColorEnumValidator;
import org.springframework.stereotype.Component;

/**
 * Created by song on 2018/10/19.
 */
@Component
public class PlateColorEnumValidatorImpl extends ValidatorHandler<Integer> implements PlateColorEnumValidator {

    @Override
    public boolean accept(ValidatorContext context, Integer target) {
        if (target != null) {
            PlateColorEnum plateColorEnum = PlateColorEnum.parse(target);
            if (plateColorEnum == null) {
                throw new ValidationException(SpecialVehicleConstant.PACKET_VEHICLE_PLATE_COLOR_ERROR);
            }
        }
        return true;
    }
}
