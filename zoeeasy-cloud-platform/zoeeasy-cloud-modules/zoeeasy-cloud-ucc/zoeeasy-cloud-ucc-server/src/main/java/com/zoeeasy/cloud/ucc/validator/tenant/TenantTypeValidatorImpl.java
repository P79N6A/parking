package com.zoeeasy.cloud.ucc.validator.tenant;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.ucc.enums.TenantTypeEnum;
import com.zoeeasy.cloud.ucc.tenant.validator.TenantTypeValidator;
import org.springframework.stereotype.Component;


/**
 * 租户类型校验合约
 *
 * @author walkman
 * @since 2018-08-28
 */
@Component
public class TenantTypeValidatorImpl extends ValidatorHandler<Integer> implements TenantTypeValidator {

    @Override
    public boolean validate(ValidatorContext context, Integer requestDto) {

        TenantTypeEnum typeEnum = TenantTypeEnum.parse(requestDto);
        if (typeEnum == null) {
            throw new ValidationException("租户类型无效");
        }
        return true;
    }
}