package com.zoeeasy.cloud.ucc.validator.tenant;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.ucc.domain.TenantEntity;
import com.zoeeasy.cloud.ucc.enums.TenantStatusEnum;
import com.zoeeasy.cloud.ucc.service.TenantCrudService;
import com.zoeeasy.cloud.ucc.tenant.cst.TenantConstant;
import com.zoeeasy.cloud.ucc.tenant.dto.request.TenantLockRequestDto;
import com.zoeeasy.cloud.ucc.tenant.validator.TenantLockValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 租户锁定校验合约
 *
 * @author walkman
 * @since 2018-08-28
 */
@Component
public class TenantLockValidatorImpl extends ValidatorHandler<TenantLockRequestDto> implements TenantLockValidator {

    @Autowired
    private TenantCrudService tenantCrudService;

    @Override
    public boolean validate(ValidatorContext context, TenantLockRequestDto requestDto) {
        if (requestDto.getId() == null) {
            throw new ValidationException(TenantConstant.TENANT_ID_EMPTY);
        }
        TenantEntity tenantEntity = tenantCrudService.selectById(requestDto.getId());
        if (tenantEntity == null) {
            throw new ValidationException(TenantConstant.TENANT_NOT_FOUND);
        }
        //已注销不允许锁定
        if (TenantStatusEnum.CANCELED.getValue().equals(tenantEntity.getStatus())) {
            throw new ValidationException(TenantConstant.TENANT_CANCELED);
        }
        //已锁定不允许锁定
        if (tenantEntity.getStatus().equals(TenantStatusEnum.LOCKED.getValue())) {
            throw new ValidationException(TenantConstant.TENANT_LOCKED);
        }
        return super.validate(context, requestDto);
    }
}
