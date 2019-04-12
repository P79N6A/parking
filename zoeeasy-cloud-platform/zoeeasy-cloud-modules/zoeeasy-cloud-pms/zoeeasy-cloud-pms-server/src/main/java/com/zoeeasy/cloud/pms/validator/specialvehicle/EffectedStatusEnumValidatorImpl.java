package com.zoeeasy.cloud.pms.validator.specialvehicle;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.enums.EffectedStatusEnum;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import com.zoeeasy.cloud.pms.specialvehicle.validator.EffectedStatusEnumValidator;
import org.springframework.stereotype.Component;

/**
 * @date: 2018/10/22.
 * @author：zm
 */
@Component
public class EffectedStatusEnumValidatorImpl extends ValidatorHandler<Integer> implements EffectedStatusEnumValidator {

    @Override
    public boolean accept(ValidatorContext context, Integer target) {
        if (target != null) {
            EffectedStatusEnum effectedStatusEnum = EffectedStatusEnum.parse(target);
            if (effectedStatusEnum == null) {
                throw new ValidationException(SpecialVehicleConstant.EFFECTED_STATUS_ERROR);
            }
        }
        return true;
    }
}
