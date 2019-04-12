package com.zoeeasy.cloud.ucc.validator.role;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.ucc.domain.RoleEntity;
import com.zoeeasy.cloud.ucc.role.cst.RoleConstant;
import com.zoeeasy.cloud.ucc.role.dto.request.RoleCreateRequestDto;
import com.zoeeasy.cloud.ucc.role.validator.RoleCreateValidator;
import com.zoeeasy.cloud.ucc.service.RoleCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleCreateValidatorImpl extends ValidatorHandler<RoleCreateRequestDto> implements RoleCreateValidator {

    @Autowired
    private RoleCrudService roleCrudService;

    @Override
    public boolean validate(ValidatorContext context, RoleCreateRequestDto requestDto) {
        RoleEntity roleEntity = this.roleCrudService.getByCode(requestDto.getCode());
        if (roleEntity != null) {
            throw new ValidationException(RoleConstant.ROLE_CODE_EXIST);
        }
        roleEntity = this.roleCrudService.getByName(requestDto.getName());
        if (roleEntity != null) {
            throw new ValidationException(RoleConstant.ROLE_NAME_EXIST);
        }
        return super.validate(context, requestDto);
    }

}