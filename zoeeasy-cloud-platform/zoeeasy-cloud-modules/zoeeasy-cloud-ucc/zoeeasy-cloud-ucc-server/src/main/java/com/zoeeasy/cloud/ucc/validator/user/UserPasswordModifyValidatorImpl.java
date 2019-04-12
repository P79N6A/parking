package com.zoeeasy.cloud.ucc.validator.user;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.ucc.common.UccConstant;
import com.zoeeasy.cloud.ucc.user.dto.request.UserPasswordModifyRequestDto;
import com.zoeeasy.cloud.ucc.user.validator.UserPasswordModifyValidator;
import org.springframework.stereotype.Component;

/**
 * 用户修改密码校验合约
 *
 * @author walkman
 */
@Component
public class UserPasswordModifyValidatorImpl extends ValidatorHandler<UserPasswordModifyRequestDto> implements UserPasswordModifyValidator {

    @Override
    public boolean validate(ValidatorContext context, UserPasswordModifyRequestDto requestDto) {
        if (!requestDto.getNewPassword().equals(requestDto.getNewConfirmPassword())) {
            throw new ValidationException(UccConstant.ACCOUNT_AUTHENTICATION_UNMATCH);
        }
        return true;
    }

}