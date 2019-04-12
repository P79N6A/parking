package com.zoeeasy.cloud.pms.validator.specialvehicle;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.enums.VisitTypeEnum;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import com.zoeeasy.cloud.pms.specialvehicle.validator.VisitTypeEnumValidator;
import org.springframework.stereotype.Component;

/**
 * @date: 2018/10/22.
 * @author：zm
 */
@Component
public class VisitTypeEnumValidatorImpl extends ValidatorHandler<Integer> implements VisitTypeEnumValidator {

    @Override
    public boolean accept(ValidatorContext context, Integer target) {
        if (target != null) {
            VisitTypeEnum visitTypeEnum = VisitTypeEnum.parse(target);
            if (visitTypeEnum == null) {
                throw new ValidationException(SpecialVehicleConstant.SPECIAL_VEHICLE_VISIT_TYPE_ERROR);
            }
        }
        return true;
    }
}
