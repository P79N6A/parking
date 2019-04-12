package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.enums.PutAwayStatusEnum;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.validator.PutAwayStatusEnumValidator;
import org.springframework.stereotype.Component;

/**
 * Created by song on 2018/10/19.
 */
@Component
public class PutAwayStatusEnumValidatorImpl extends ValidatorHandler<Integer> implements PutAwayStatusEnumValidator {

    @Override
    public boolean accept(ValidatorContext context, Integer target) {
        if (target != null) {
            PutAwayStatusEnum putAwayStatusEnum = PutAwayStatusEnum.parse(target);
            if (putAwayStatusEnum == null) {
                throw new ValidationException(ParkingConstant.PUT_AWAY_STATUS_ERROR);
            }
        }
        return true;
    }
}
