package com.zoeeasy.cloud.ucc.validator.tenant;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.ucc.tenant.cst.TenantConstant;
import com.zoeeasy.cloud.ucc.tenant.validator.TenantNameValidator;
import org.springframework.stereotype.Component;

/**
 * 租户名称校验合约
 *
 * @author walkman
 * @since 2018-08-28
 */
@Component
public class TenantNameValidatorImpl extends ValidatorHandler<String> implements TenantNameValidator {

    @Override
    public boolean validate(ValidatorContext context, String requestDto) {

        if (StringUtils.isEmpty(requestDto) || StringUtils.isSpecialSymbols(requestDto)) {
            throw new ValidationException(TenantConstant.TENANT_NAME_ILLEGAL);
        }
        return true;
    }
}