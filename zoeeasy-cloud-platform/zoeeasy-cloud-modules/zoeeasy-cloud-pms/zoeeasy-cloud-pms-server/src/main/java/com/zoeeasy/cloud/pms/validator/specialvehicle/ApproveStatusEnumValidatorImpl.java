package com.zoeeasy.cloud.pms.validator.specialvehicle;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.enums.ApproveStatusEnum;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import com.zoeeasy.cloud.pms.specialvehicle.validator.ApproveStatusEnumValidator;
import org.springframework.stereotype.Component;

/**
 * Created by song on 2018/10/19.
 */
@Component
public class ApproveStatusEnumValidatorImpl extends ValidatorHandler<Integer> implements ApproveStatusEnumValidator {

    @Override
    public boolean accept(ValidatorContext context, Integer target) {
        if (target != null) {
            ApproveStatusEnum approveStatusEnum = ApproveStatusEnum.parse(target);
            if (approveStatusEnum == null) {
                throw new ValidationException(SpecialVehicleConstant.APPROVE_STATUS_ENUM_ERROR);
            }
        }
        return true;
    }
}
