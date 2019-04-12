package com.zoeeasy.cloud.pds.validator.magneticdetector;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.core.enums.DeviceProviderEnum;
import com.zoeeasy.cloud.pds.magneticdetector.cst.MagneticDetectorConstant;
import com.zoeeasy.cloud.pds.magneticdetector.validator.ProviderEnumValidator;
import org.springframework.stereotype.Component;

/**
 * @Date: 2018/11/1
 * @author: lhj
 */
@Component
public class ProviderEnumValidatorImpl extends ValidatorHandler<Integer> implements ProviderEnumValidator {
    @Override
    public boolean accept(ValidatorContext context, Integer provider) {
       if (provider != null) {
           DeviceProviderEnum providerEnum = DeviceProviderEnum.parse(provider);
           if (providerEnum == null){
               throw new ValidationException(MagneticDetectorConstant.PROVIDER_NONENTITY);
           }
       }
        return true;
    }

}
