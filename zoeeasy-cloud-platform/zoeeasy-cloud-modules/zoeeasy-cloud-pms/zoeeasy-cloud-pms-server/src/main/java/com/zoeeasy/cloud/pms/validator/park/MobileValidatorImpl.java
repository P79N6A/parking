package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.validator.MobileValidator;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MobileValidatorImpl extends ValidatorHandler<String> implements MobileValidator {

    @Override
    public boolean accept(ValidatorContext context, String str) {
        if (!StringUtils.isEmpty(str)){
            Pattern pattern = Pattern.compile(ParkingConstant.MOBILE_PATTERN);
            Matcher matcher = pattern.matcher(str);
            if (!matcher.matches()){
                throw new ValidationException(ParkingConstant.MOBILE_ILLEGAL);
            }
        }
        return true;
    }
}
