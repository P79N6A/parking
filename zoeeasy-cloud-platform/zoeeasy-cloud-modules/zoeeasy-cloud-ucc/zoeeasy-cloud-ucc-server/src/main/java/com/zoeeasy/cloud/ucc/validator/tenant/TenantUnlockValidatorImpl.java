package com.zoeeasy.cloud.ucc.validator.tenant;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.ucc.domain.TenantEntity;
import com.zoeeasy.cloud.ucc.enums.TenantStatusEnum;
import com.zoeeasy.cloud.ucc.service.TenantCrudService;
import com.zoeeasy.cloud.ucc.tenant.cst.TenantConstant;
import com.zoeeasy.cloud.ucc.tenant.dto.request.TenantUnlockRequestDto;
import com.zoeeasy.cloud.ucc.tenant.validator.TenantUnlockValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 租户解锁校验合约
 *
 * @author walkman
 * @since 2018-08-28
 */
@Component
public class TenantUnlockValidatorImpl extends ValidatorHandler<TenantUnlockRequestDto> implements TenantUnlockValidator {

    @Autowired
    private TenantCrudService tenantCrudService;

    @Override
    public boolean validate(ValidatorContext context, TenantUnlockRequestDto requestDto) {
        if (requestDto.getId() == null) {
            throw new ValidationException(TenantConstant.TENANT_ID_EMPTY);
        }
        TenantEntity tenantEntity = tenantCrudService.selectById(requestDto.getId());
        if (tenantEntity == null) {
            throw new ValidationException(TenantConstant.TENANT_NOT_FOUND);
        }
        //已注销不允许注销
        if (TenantStatusEnum.CANCELED.getValue().equals(tenantEntity.getStatus())) {
            throw new ValidationException(TenantConstant.TENANT_CANCELED);
        }
        //未锁定不允许解锁
        if (!tenantEntity.getStatus().equals(TenantStatusEnum.LOCKED.getValue())) {
            throw new ValidationException(TenantConstant.TENANT_NOT_LOCKED);
        }
        return super.validate(context, requestDto);
    }

}