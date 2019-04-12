package com.zoeeasy.cloud.ucc.validator.role;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.ucc.domain.RoleEntity;
import com.zoeeasy.cloud.ucc.role.cst.RoleConstant;
import com.zoeeasy.cloud.ucc.role.dto.request.RoleEditRequestDto;
import com.zoeeasy.cloud.ucc.role.validator.RoleEditValidator;
import com.zoeeasy.cloud.ucc.service.RoleCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleEditValidatorImpl extends ValidatorHandler<RoleEditRequestDto> implements RoleEditValidator {

    @Autowired
    private RoleCrudService roleCrudService;

    @Override
    public boolean validate(ValidatorContext context, RoleEditRequestDto requestDto) {
        RoleEntity roleEntity = this.roleCrudService.selectById(requestDto.getId());
        if (roleEntity == null) {
            throw new ValidationException(RoleConstant.ROLE_NOT_EXIST);
        }
        if (roleEntity.getStaticRole()) {
            throw new ValidationException(RoleConstant.ROLE_STATIC_NOT_TO_EDIT);
        }
        //如果名称改变，校验是否名称已存在
        if (!requestDto.getName().equals(roleEntity.getName())) {
            RoleEntity sameNameEntity = this.roleCrudService.getByName(requestDto.getName());
            if (sameNameEntity != null && !sameNameEntity.getId().equals(roleEntity.getId()))
                throw new ValidationException(RoleConstant.ROLE_NAME_EXIST);
        }
        return super.validate(context, requestDto);
    }
}