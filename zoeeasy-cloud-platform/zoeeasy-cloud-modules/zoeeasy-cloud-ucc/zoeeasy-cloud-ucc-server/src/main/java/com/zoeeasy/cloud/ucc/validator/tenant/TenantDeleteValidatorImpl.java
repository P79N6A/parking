package com.zoeeasy.cloud.ucc.validator.tenant;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.ucc.domain.TenantEntity;
import com.zoeeasy.cloud.ucc.service.TenantCrudService;
import com.zoeeasy.cloud.ucc.tenant.cst.TenantConstant;
import com.zoeeasy.cloud.ucc.tenant.dto.request.TenantDeleteRequestDto;
import com.zoeeasy.cloud.ucc.tenant.validator.TenantDeleteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 租户删除校验合约
 *
 * @author walkman
 * @since 2018-08-28
 */
@Component
public class TenantDeleteValidatorImpl extends ValidatorHandler<TenantDeleteRequestDto> implements TenantDeleteValidator {

    @Autowired
    private TenantCrudService tenantCrudService;

    @Override
    public boolean validate(ValidatorContext context, TenantDeleteRequestDto requestDto) {
        if (requestDto.getId() == null) {
            throw new ValidationException(TenantConstant.TENANT_ID_EMPTY);
        }
        TenantEntity tenantEntity = tenantCrudService.selectById(requestDto.getId());
        if (tenantEntity == null) {
            throw new ValidationException(TenantConstant.TENANT_NOT_FOUND);
        }
        return super.validate(context, requestDto);
    }
}