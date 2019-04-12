package com.zoeeasy.cloud.ucc.validator.user;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.ucc.common.UccConstant;
import com.zoeeasy.cloud.ucc.domain.UserEntity;
import com.zoeeasy.cloud.ucc.enums.UserStatusEnum;
import com.zoeeasy.cloud.ucc.service.UserCrudService;
import com.zoeeasy.cloud.ucc.user.dto.request.UserCloseRequestDto;
import com.zoeeasy.cloud.ucc.user.validator.UserCloseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户注销校验合约
 *
 * @author walkman
 */
@Component
public class UserCloseValidatorImpl extends ValidatorHandler<UserCloseRequestDto> implements UserCloseValidator {

    @Autowired
    private UserCrudService userCrudService;

    @Override
    public boolean validate(ValidatorContext context, UserCloseRequestDto requestDto) {
        if (requestDto.getId() == null) {
            throw new ValidationException(UccConstant.USER_ID_NOT_EMPTY);
        }
        UserEntity userEntity = this.userCrudService.selectById(requestDto.getId());
        if (userEntity == null) {
            throw new ValidationException(UccConstant.USER_NOT_FOUND);
        }
        if (userEntity.getAdminUser()) {
            throw new ValidationException(UccConstant.TENANT_ADMIN_USER_CANNOT_CLOSED);
        }
        //已注销不允许注销
        if (UserStatusEnum.CANCELED.getValue().equals(userEntity.getStatus())) {
            throw new ValidationException(UccConstant.USER_CANCELED);
        }
        return true;
    }

}