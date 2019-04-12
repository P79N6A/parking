package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.enums.LotTypeEnum;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.validator.LotTypeEnumValidator;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Created by song on 2018/10/19.
 */
@Component
public class LotTypeEnumValidatorImpl extends ValidatorHandler<String> implements LotTypeEnumValidator {

    @Override
    public boolean accept(ValidatorContext context, String target) {
        if (!StringUtils.isEmpty(target)) {
            String[] strs = target.split(",");
            for (String str : strs) {
                LotTypeEnum lotTypeEnum = LotTypeEnum.parse(Integer.valueOf(str));
                if (lotTypeEnum == null) {
                    throw new ValidationException(ParkingConstant.LOTTYPE_ENUM_ERROR);
                }
            }
        }
        return true;
    }
}
