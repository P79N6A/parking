package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.enums.OperationStateEnum;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.validator.OperationStateEnumValidator;
import org.springframework.stereotype.Component;

/**
 * Created by song on 2018/10/19.
 */
@Component
public class OperationStateEnumValidatorImpl extends ValidatorHandler<Integer> implements OperationStateEnumValidator {

    @Override
    public boolean accept(ValidatorContext context, Integer target) {
        if (target != null) {
            OperationStateEnum operationStateEnum = OperationStateEnum.parse(target);
            if (operationStateEnum == null){
                throw new ValidationException(ParkingConstant.OPERATION_STATE_ENUM_ERROR);
            }
        }
        return true;
    }
}
