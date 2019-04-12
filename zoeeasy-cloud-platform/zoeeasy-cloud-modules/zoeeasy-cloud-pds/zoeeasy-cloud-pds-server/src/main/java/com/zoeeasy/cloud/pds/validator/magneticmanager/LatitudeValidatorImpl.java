package com.zoeeasy.cloud.pds.validator.magneticmanager;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pds.magneticmanager.cst.MagneticManagerConstant;
import com.zoeeasy.cloud.pds.magneticmanager.validator.LatitudeValidator;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Date: 2018/12/18
 * @author: lhj
 */
@Component
public class LatitudeValidatorImpl extends ValidatorHandler<Double> implements LatitudeValidator {

    @Override
    public boolean accept(ValidatorContext context, Double target) {
        if (target != null) {
            Pattern pattern = Pattern.compile(MagneticManagerConstant.LATITUDE_PATTERN);
            Matcher match = pattern.matcher(String.valueOf(target));
            if (!match.matches()) {
                throw new ValidationException(MagneticManagerConstant.LATITUDE_ILLEGAL);
            }
        }
        return true;
    }
}
