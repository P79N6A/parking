package com.zoeeasy.cloud.ucc.validator.user;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.ucc.common.UccConstant;
import com.zoeeasy.cloud.ucc.domain.UserEntity;
import com.zoeeasy.cloud.ucc.service.UserCrudService;
import com.zoeeasy.cloud.ucc.user.dto.request.UserPasswordResetRequestDto;
import com.zoeeasy.cloud.ucc.user.validator.UserPasswordResetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 用户修改密码校验合约
 *
 * @author walkman
 */
@Component
public class UserPasswordResetValidatorImpl extends ValidatorHandler<UserPasswordResetRequestDto> implements UserPasswordResetValidator {

    @Autowired
    private UserCrudService userCrudService;

    @Override
    public boolean validate(ValidatorContext context, UserPasswordResetRequestDto requestDto) {
        if (requestDto.getId() == null) {
            throw new ValidationException(UccConstant.USER_ID_NOT_EMPTY);
        }
        UserEntity userEntity = this.userCrudService.selectById(requestDto.getId());
        if (userEntity == null) {
            throw new ValidationException(UccConstant.USER_NOT_FOUND);
        }
        if (userEntity.getAdminUser()) {
            throw new ValidationException(UccConstant.TENANT_ADMIN_USER_CANNOT_RESET_AUTHENTICATION);
        }
        return true;
    }

}