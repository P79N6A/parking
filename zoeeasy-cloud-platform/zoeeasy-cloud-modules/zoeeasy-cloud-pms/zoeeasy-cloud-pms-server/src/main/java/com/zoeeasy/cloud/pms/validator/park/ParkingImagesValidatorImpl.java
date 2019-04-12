package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.validator.ParkingImagesValidator;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by song on 2018/10/19.
 */
@Component
public class ParkingImagesValidatorImpl extends ValidatorHandler<List<String>> implements ParkingImagesValidator {

    @Override
    public boolean accept(ValidatorContext context, List<String> target) {
        if (CollectionUtils.isNotEmpty(target)) {
            if (target.size() > ParkingConstant.PARKING_PICTURE_MAX_NUM) {
                throw new ValidationException(ParkingConstant.PARKING_PICTURE_OVER_MAX_NUM);
            }
            Pattern pattern = Pattern.compile(ParkingConstant.URL_PATTERN);
            for (String str : target) {
                Matcher matcher = pattern.matcher(str);
                if (!matcher.matches()) {
                    throw new ValidationException(ParkingConstant.PARKING_IMAGE_URL_ILLEGAL);
                }
            }
        }
        return true;
    }
}
