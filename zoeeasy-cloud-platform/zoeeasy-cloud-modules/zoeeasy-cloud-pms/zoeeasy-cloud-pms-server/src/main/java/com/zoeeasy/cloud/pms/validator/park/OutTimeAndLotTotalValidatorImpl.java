package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.validator.OutTimeAndLotTotalValidator;
import org.springframework.stereotype.Component;

@Component
public class OutTimeAndLotTotalValidatorImpl extends ValidatorHandler<Integer> implements OutTimeAndLotTotalValidator {

    @Override
    public boolean accept(ValidatorContext context, Integer target) {
        if (target != null){
            if (target < ParkingConstant.PARKING_NUMBER_ONE || target > ParkingConstant.PARKING_LOTAVAILABLE_MAX){
                throw new ValidationException(ParkingConstant.NUMBER_MESSAGE);
            }
        }
        return true;
    }
}
