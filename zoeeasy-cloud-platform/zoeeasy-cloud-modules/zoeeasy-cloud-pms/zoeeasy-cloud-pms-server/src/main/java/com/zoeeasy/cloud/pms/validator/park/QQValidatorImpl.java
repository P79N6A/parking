package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.validator.QQValidator;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class QQValidatorImpl extends ValidatorHandler<String> implements QQValidator {

    @Override
    public boolean accept(ValidatorContext context, String str) {
        if (!StringUtils.isEmpty(str)) {
            Pattern pattern = Pattern.compile(ParkingConstant.QQ_PATTERN);
            Matcher matcher = pattern.matcher(str);
            if (!matcher.matches()) {
                throw new ValidationException(ParkingConstant.QQ_ILLEGAL);
            }
        }
        return true;
    }
}
