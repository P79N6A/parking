package com.zoeeasy.cloud.pms.validator.lane;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.enums.ParkingLaneTypeEnum;
import com.zoeeasy.cloud.pms.lane.cst.ParkingLaneConstant;
import com.zoeeasy.cloud.pms.lane.validator.ParkingLaneTypeValidator;
import org.springframework.stereotype.Component;

@Component
public class ParkingLaneTypeValidatorImpl extends ValidatorHandler<Integer> implements ParkingLaneTypeValidator {

    @Override
    public boolean validate(ValidatorContext context, Integer direction) {
        for (ParkingLaneTypeEnum parkingLaneTypeEnum : ParkingLaneTypeEnum.values()) {
            if (direction.equals(parkingLaneTypeEnum.getValue())) {
                return true;
            }
        }
        throw new ValidationException(ParkingLaneConstant.PARKING_LANE_TYPE_NOT_EXIT );
    }
}
