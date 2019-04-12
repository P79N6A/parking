package com.zoeeasy.cloud.pms.validator.gate;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.enums.ParkingGateTypeEnum;
import com.zoeeasy.cloud.pms.gate.cst.ParkingGateConstant;
import com.zoeeasy.cloud.pms.gate.validator.ParkingGateTypeValidator;
import org.springframework.stereotype.Component;


@Component
public class ParkingGateTypeValidatorImpl extends ValidatorHandler<Integer> implements ParkingGateTypeValidator {
    @Override
    public boolean validate(ValidatorContext context, Integer direction) {
        for (ParkingGateTypeEnum parkingGateTypeEnum : ParkingGateTypeEnum.values()) {
            if (direction .equals(parkingGateTypeEnum.getValue()) ) {
                return true;
            }
        }
        throw new ValidationException(ParkingGateConstant.PARKING_GATE_TYPE_NOT_EXIT);
    }
}
