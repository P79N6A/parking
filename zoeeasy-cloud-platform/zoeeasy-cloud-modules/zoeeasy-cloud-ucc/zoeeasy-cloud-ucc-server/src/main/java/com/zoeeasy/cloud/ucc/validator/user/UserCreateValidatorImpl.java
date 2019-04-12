package com.zoeeasy.cloud.ucc.validator.user;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.ucc.common.UccConstant;
import com.zoeeasy.cloud.ucc.domain.UserEntity;
import com.zoeeasy.cloud.ucc.service.UserCrudService;
import com.zoeeasy.cloud.ucc.user.dto.request.UserCreateRequestDto;
import com.zoeeasy.cloud.ucc.user.validator.UserCreateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户创建校验合约
 *
 * @author walkman
 */
@Component
public class UserCreateValidatorImpl extends ValidatorHandler<UserCreateRequestDto> implements UserCreateValidator {

    @Autowired
    private UserCrudService userCrudService;

    @Override
    public boolean validate(ValidatorContext context, UserCreateRequestDto requestDto) {
        if (!requestDto.getConfirmPassword().equals(requestDto.getPassword())) {
            throw new ValidationException(UccConstant.ACCOUNT_AUTHENTICATION_UNMATCH);
        }
        if (!StringUtils.isNotEmpty(requestDto.getRealName()) && StringUtils.isSpecialSymbols(requestDto.getRealName())) {
            throw new ValidationException(UccConstant.USER_REAL_NAME_ILLEGAL);
        }
        UserEntity tenantEntity = userCrudService.findByUserName(requestDto.getUserName());
        if (tenantEntity != null) {
            throw new ValidationException(UccConstant.ACCOUNT_NAME_EXIST);
        }
        return true;
    }

}