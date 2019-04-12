package com.zoeeasy.cloud.pms.validator.specialvehicle;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.enums.AllParkingEnum;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import com.zoeeasy.cloud.pms.specialvehicle.validator.AllParkingEnumValidator;
import org.springframework.stereotype.Component;

/**
 * Created by song on 2018/10/19.
 */
@Component
public class AllParkingEnumValidatorImpl extends ValidatorHandler<Integer> implements AllParkingEnumValidator {

    @Override
    public boolean accept(ValidatorContext context, Integer target) {
        if (target != null) {
            AllParkingEnum allParkingEnum = AllParkingEnum.parse(target);
            if (allParkingEnum == null) {
                throw new ValidationException(SpecialVehicleConstant.PACKET_VEHICLE_ALL_PARKING_ERROR);
            }
        }
        return true;
    }
}
