package com.zoeeasy.cloud.ucc.validator.tenant;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.ucc.common.UccConstant;
import com.zoeeasy.cloud.ucc.domain.TenantEntity;
import com.zoeeasy.cloud.ucc.domain.UserEntity;
import com.zoeeasy.cloud.ucc.service.TenantCrudService;
import com.zoeeasy.cloud.ucc.service.UserCrudService;
import com.zoeeasy.cloud.ucc.tenant.cst.TenantConstant;
import com.zoeeasy.cloud.ucc.tenant.dto.request.TenantPasswordResetRequestDto;
import com.zoeeasy.cloud.ucc.tenant.validator.TenantPasswordResetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 租户管理员面重置校验合约
 *
 * @author walkman
 * @since 2018-08-28
 */
@Component
public class TenantPasswordResetValidatorImpl extends ValidatorHandler<TenantPasswordResetRequestDto> implements TenantPasswordResetValidator {

    @Autowired
    private TenantCrudService tenantCrudService;

    @Autowired
    private UserCrudService userCrudService;

    @Override
    public boolean validate(ValidatorContext context, TenantPasswordResetRequestDto requestDto) {
        if (!requestDto.getAdminPassword().equals(requestDto.getAdminConfirmedPassword())) {
            throw new ValidationException(UccConstant.ACCOUNT_AUTHENTICATION_UNMATCH);
        }
        TenantEntity tenantEntity = tenantCrudService.selectById(requestDto.getId());
        if (tenantEntity == null) {
            throw new ValidationException(TenantConstant.TENANT_NOT_FOUND);
        }
        UserEntity userEntity = this.userCrudService.finByUserId(tenantEntity.getAdminUserId());
        if (userEntity == null) {
            throw new ValidationException(TenantConstant.TENANT_NOT_FOUND);
        }
        return super.validate(context, requestDto);
    }

}

