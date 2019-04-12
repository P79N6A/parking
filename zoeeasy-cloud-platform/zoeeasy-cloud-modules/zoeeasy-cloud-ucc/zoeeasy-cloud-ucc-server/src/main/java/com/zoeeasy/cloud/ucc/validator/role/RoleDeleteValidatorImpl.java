package com.zoeeasy.cloud.ucc.validator.role;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.ucc.domain.RoleEntity;
import com.zoeeasy.cloud.ucc.domain.UserRoleEntity;
import com.zoeeasy.cloud.ucc.role.cst.RoleConstant;
import com.zoeeasy.cloud.ucc.role.dto.request.RoleDeleteRequestDto;
import com.zoeeasy.cloud.ucc.role.validator.RoleDeleteValidator;
import com.zoeeasy.cloud.ucc.service.RoleCrudService;
import com.zoeeasy.cloud.ucc.service.UserRoleCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色删除校验器
 *
 * @author walkman
 * @since 2018-08-28
 */
@Component
public class RoleDeleteValidatorImpl extends ValidatorHandler<RoleDeleteRequestDto> implements RoleDeleteValidator {

    @Autowired
    private RoleCrudService roleCrudService;

    @Autowired
    private UserRoleCrudService userRoleCrudService;

    @Override
    public boolean validate(ValidatorContext context, RoleDeleteRequestDto requestDto) {

        RoleEntity roleEntity = this.roleCrudService.selectById(requestDto.getId());
        if (roleEntity == null) {
            throw new ValidationException(RoleConstant.ROLE_NOT_EXIST);
        }
        if (roleEntity.getStaticRole()) {
            throw new ValidationException(RoleConstant.ROLE_STATIC_NOT_TO_DELETE);
        }
        List<UserRoleEntity> userRoleEntityList = this.userRoleCrudService.findByRoleId(requestDto.getId());
        if (!userRoleEntityList.isEmpty()) {
            throw new ValidationException(RoleConstant.ROLE_AUTHORIZED_CANNOT_DELETED);
        }
        //存在拥有此角色的用户禁止删除
        return super.validate(context, requestDto);
    }
}