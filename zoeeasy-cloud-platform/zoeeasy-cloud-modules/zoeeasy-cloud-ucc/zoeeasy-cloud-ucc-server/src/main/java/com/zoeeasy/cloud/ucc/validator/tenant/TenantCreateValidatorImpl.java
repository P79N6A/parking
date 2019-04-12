package com.zoeeasy.cloud.ucc.validator.tenant;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.zoeeasy.cloud.ucc.common.UccConstant;
import com.zoeeasy.cloud.ucc.domain.TenantEntity;
import com.zoeeasy.cloud.ucc.domain.UserEntity;
import com.zoeeasy.cloud.ucc.service.TenantCrudService;
import com.zoeeasy.cloud.ucc.service.UserCrudService;
import com.zoeeasy.cloud.ucc.tenant.cst.TenantConstant;
import com.zoeeasy.cloud.ucc.tenant.dto.request.TenantCreateRequestDto;
import com.zoeeasy.cloud.ucc.tenant.validator.TenantCreateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 租户创建校验合约
 *
 * @author walkman
 */
@Component
public class TenantCreateValidatorImpl extends ValidatorHandler<TenantCreateRequestDto> implements TenantCreateValidator {

    @Autowired
    private TenantCrudService tenantCrudService;

    @Autowired
    private UserCrudService userCrudService;

    @Override
    public boolean validate(ValidatorContext context, TenantCreateRequestDto requestDto) {
        if (!requestDto.getAdminPassword().equals(requestDto.getAdminConfirmedPassword())) {
            context.addErrorMsg(UccConstant.ACCOUNT_AUTHENTICATION_UNMATCH);
            return false;
        }
        TenantEntity tenantEntity = tenantCrudService.findByTenantName(requestDto.getName());
        if (tenantEntity != null) {
            context.addErrorMsg(TenantConstant.TENANT_NAME_EXIST);
            return false;
        }
        UserEntity userEntity = userCrudService.getUserByAccount(requestDto.getAdminAccount());
        if (userEntity != null) {
            context.addErrorMsg(UccConstant.ACCOUNT_NAME_EXIST);
            return false;
        }
        return true;
    }

}
