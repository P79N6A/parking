package com.zoeeasy.cloud.pms.validator.area;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.area.cst.AreaConstant;
import com.zoeeasy.cloud.pms.area.validator.AreaLevelValidator;
import com.zoeeasy.cloud.tool.enums.RegionLevelEnum;
import org.springframework.stereotype.Component;

@Component
public class AreaLevelValidatorImpl extends ValidatorHandler<Integer> implements AreaLevelValidator {
    @Override
    public boolean validate(ValidatorContext context, Integer level) {
        for (RegionLevelEnum regionLevelEnum : RegionLevelEnum.values()) {
            if (level.equals(regionLevelEnum.getValue()) && !level.equals(RegionLevelEnum.COUNTY.getValue())
                    && !level.equals(RegionLevelEnum.COUNTRY.getValue())) {
                return true;
            }
        }
        throw new ValidationException(AreaConstant.AREA_LEVEL_OUT);
    }
}
