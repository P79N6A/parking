package com.zoeeasy.cloud.notify.validator;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.notify.cts.NotifyConstant;
import com.zoeeasy.cloud.notify.enums.TagTypeEnum;
import com.zoeeasy.cloud.notify.platform.validator.TagTypeValidator;

/**
 * @author AkeemSuper
 * @date 2018/11/12 0012
 */
public class TagTypeValidatorImpl extends ValidatorHandler<Integer> implements TagTypeValidator {

    @Override
    public boolean validate(ValidatorContext context, Integer integer) {
        if (null != integer) {
            TagTypeEnum tagTypeEnum = TagTypeEnum.parse(integer);
            if (tagTypeEnum == null) {
                throw new ValidationException(NotifyConstant.TAG_TYPE_ERR);
            }
        }
        return true;
    }
}
