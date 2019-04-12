package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.enums.PayModeEnum;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.validator.PayModeEnumValidator;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Created by song on 2018/10/19.
 */
@Component
public class PayModeEnumValidatorImpl extends ValidatorHandler<String> implements PayModeEnumValidator {

    @Override
    public boolean accept(ValidatorContext context, String target) {
        if (!StringUtils.isEmpty(target)){
            PayModeEnum payModeEnum = PayModeEnum.parse(Integer.valueOf(target));
            if (payModeEnum == null){
                throw new ValidationException(ParkingConstant.PAY_MODE_ENUM_ERROR);
            }
        }
        return true;
    }
}
