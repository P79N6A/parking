package com.zoeeasy.cloud.pds.validator.magneticdetector;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pds.magneticdetector.validator.HeartBeatIntervalValidator;
import com.zoeeasy.cloud.pds.magneticmanager.cst.MagneticManagerConstant;
import org.springframework.stereotype.Component;

/**
 * @Date: 2018/12/19
 * @author: lhj
 */
@Component
public class HeartBeatIntervalValidatorImpl extends ValidatorHandler<Integer> implements HeartBeatIntervalValidator {
    @Override
    public boolean accept(ValidatorContext context, Integer heartBeatInterval) {
        if (heartBeatInterval < MagneticManagerConstant.MAGNETIC_MANAGER_HEARTBEAT_INTEVAL_MIN_LENGTH || heartBeatInterval > MagneticManagerConstant.MAGNETIC_MANAGER_HEARTBEAT_INTEVAL_MAX_LENGTH) {
            throw new ValidationException(MagneticManagerConstant.MAGNETIC_MANAGER_HEARTBEAT_INTEVAL_LENGTH);
        }
        return true;
    }
}
