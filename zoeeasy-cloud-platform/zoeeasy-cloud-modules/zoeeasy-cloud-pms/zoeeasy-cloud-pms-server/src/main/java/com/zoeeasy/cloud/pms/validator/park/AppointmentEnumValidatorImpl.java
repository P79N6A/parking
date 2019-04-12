package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.enums.AppointmentEnum;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.validator.AppointmentEnumValidator;
import org.springframework.stereotype.Component;

/**
 * Created by song on 2018/10/19.
 */
@Component
public class AppointmentEnumValidatorImpl extends ValidatorHandler<Integer> implements AppointmentEnumValidator {

    @Override
    public boolean accept(ValidatorContext context, Integer target) {
        if (target != null){
            AppointmentEnum appointmentEnum = AppointmentEnum.parse(target);
            if (appointmentEnum == null){
                throw new ValidationException(ParkingConstant.APPOINTMENT_ENUM_ERROR);
            }
        }
        return true;
    }
}
