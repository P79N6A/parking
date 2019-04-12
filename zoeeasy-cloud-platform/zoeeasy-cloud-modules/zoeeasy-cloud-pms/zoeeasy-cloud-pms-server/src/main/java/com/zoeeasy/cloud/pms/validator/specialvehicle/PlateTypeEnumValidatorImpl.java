package com.zoeeasy.cloud.pms.validator.specialvehicle;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.core.enums.LicensePlateTypeEnum;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import com.zoeeasy.cloud.pms.specialvehicle.validator.PlateTypeEnumValidator;
import org.springframework.stereotype.Component;

/**
 * @date: 2018/10/22.
 * @author：zm
 */
@Component
public class PlateTypeEnumValidatorImpl extends ValidatorHandler<String> implements PlateTypeEnumValidator {

    @Override
        public boolean accept(ValidatorContext context, String target) {
        if (target != null) {
            LicensePlateTypeEnum licensePlateTypeEnum = LicensePlateTypeEnum.parse(target);
            if (licensePlateTypeEnum == null) {
                throw new ValidationException(SpecialVehicleConstant.SPECIAL_VEHICLE_PLATE_TYPE_ERROR);
            }
        }
        return true;
    }
}
