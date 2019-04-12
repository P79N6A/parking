package com.zoeeasy.cloud.pds.validator.magneticmanager;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pds.magneticmanager.cst.MagneticManagerConstant;
import com.zoeeasy.cloud.pds.magneticmanager.validator.LongitudeValidator;
import org.springframework.stereotype.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Date: 2018/12/18
 * @author: lhj
 */
@Component
public class LongitudeValidatorImpl extends ValidatorHandler<Double> implements LongitudeValidator {

    @Override
    public boolean accept(ValidatorContext context, Double target) {
        if (target != null) {
            Pattern pattern = Pattern.compile(MagneticManagerConstant.LONGITUDE_PATTERN);
            Matcher match = pattern.matcher(String.valueOf(target));
            if (!match.matches()) {
                throw new ValidationException(MagneticManagerConstant.LONGITUDE_ILLEGAL);
            }
        }
        return true;
    }
}