package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.enums.TypeEnum;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.validator.TypeEnumValidator;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Created by song on 2018/10/19.
 */
@Component
public class TypeEnumValidatorImpl extends ValidatorHandler<String> implements TypeEnumValidator {

    @Override
    public boolean accept(ValidatorContext context, String target) {
        if (!StringUtils.isEmpty(target)) {
            String[] strs = target.split(",");
            for (String str : strs) {
                TypeEnum typeEnum = TypeEnum.parse(Integer.valueOf(str));
                if (typeEnum == null) {
                    throw new ValidationException(ParkingConstant.TYPE_ERROR);
                }
            }
        }
        return true;
    }
}
