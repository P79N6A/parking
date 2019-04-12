package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.validator.LongitudeValidator;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by song on 2018/10/19.
 */
@Component
public class LongitudeValidatorImpl extends ValidatorHandler<Double> implements LongitudeValidator {

    @Override
    public boolean accept(ValidatorContext context, Double target) {
        Pattern pattern=Pattern.compile(ParkingConstant.LONGITUDE_PATTERN);
        Matcher match=pattern.matcher(String.valueOf(target));
        if (!match.matches()){
            throw new ValidationException(ParkingConstant.LONGITUDE_ILLEGAL);
        }
        return true;
    }
}
