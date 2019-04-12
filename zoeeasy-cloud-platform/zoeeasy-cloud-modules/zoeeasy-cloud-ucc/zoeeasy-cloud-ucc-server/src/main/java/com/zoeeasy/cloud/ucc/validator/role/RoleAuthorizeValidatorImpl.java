package com.zoeeasy.cloud.ucc.validator.role;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.ucc.domain.RoleEntity;
import com.zoeeasy.cloud.ucc.role.cst.RoleConstant;
import com.zoeeasy.cloud.ucc.role.dto.request.RoleAuthorizeRequestDto;
import com.zoeeasy.cloud.ucc.role.validator.RoleAuthorizeValidator;
import com.zoeeasy.cloud.ucc.service.RoleCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 角色授权校验合约
 *
 * @author walkman
 * @since 2018-08-28
 */
@Component
public class RoleAuthorizeValidatorImpl extends ValidatorHandler<RoleAuthorizeRequestDto> implements RoleAuthorizeValidator {

    @Autowired
    private RoleCrudService roleCrudService;

    @Override
    public boolean validate(ValidatorContext context, RoleAuthorizeRequestDto requestDto) {
        RoleEntity roleEntity = this.roleCrudService.selectById(requestDto.getRoleId());
        if (roleEntity == null) {
            throw new ValidationException(RoleConstant.ROLE_CODE_EXIST);
        }
        if (roleEntity.getAdminRole()) {
            throw new ValidationException(RoleConstant.ROLE_ADMIN_CAN_NOT_AUTHORIZE);
        }
        return super.validate(context, requestDto);
    }

}