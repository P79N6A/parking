package com.zoeeasy.cloud.charge.validator.holiday;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.charge.cts.ChargeConstant;
import com.zoeeasy.cloud.charge.holiday.validator.HolidayNameValidator;
import org.springframework.stereotype.Component;

/**
 * @author AkeemSuper
 * @date 2018/10/29 0029
 */
@Component
public class HolidayNameValidatorImpl extends ValidatorHandler<String> implements HolidayNameValidator {

    @Override
    public boolean accept(ValidatorContext context, String holidayName) {
        String regx = "^[0-9\\u4e00-\\u9fa5]{2,8}";
        if (!holidayName.matches(regx)) {
            throw new ValidationException(ChargeConstant.HOLIDAY_NAME_NOT_STANDARD);
        }
        return true;
    }

}
