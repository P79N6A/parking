package com.zoeeasy.cloud.pms.validator.specialvehicle;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.enums.RejectReasonEnum;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import com.zoeeasy.cloud.pms.specialvehicle.validator.RejectReasonEnumValidator;
import org.springframework.stereotype.Component;

/**
 * Created by song on 2018/10/19.
 */
@Component
public class RejectReasonEnumValidatorImpl extends ValidatorHandler<Integer> implements RejectReasonEnumValidator {

    @Override
    public boolean accept(ValidatorContext context, Integer target) {
        if (target != null) {
            RejectReasonEnum rejectReasonEnum = RejectReasonEnum.parse(target);
            if (rejectReasonEnum == null) {
                throw new ValidationException(SpecialVehicleConstant.REJECT_REASON_ENUM_ERROR);
            }
        }
        return true;
    }
}
