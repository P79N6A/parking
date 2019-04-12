package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.enums.PayTypeEnum;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.validator.PayTypeEnumValidator;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Created by song on 2018/10/19.
 */
@Component
public class PayTypeEnumValidatorImpl extends ValidatorHandler<String> implements PayTypeEnumValidator {

    @Override
    public boolean accept(ValidatorContext context, String target) {
        if (!StringUtils.isEmpty(target)){
            String[] strs = target.split(",");
            for (String str : strs){
                PayTypeEnum payTypeEnum = PayTypeEnum.parse(Integer.valueOf(str));
                if (payTypeEnum == null){
                    throw new ValidationException(ParkingConstant.PAY_TYPE_ENUM_ERROR);
                }
            }
        }
        return true;
    }
}
