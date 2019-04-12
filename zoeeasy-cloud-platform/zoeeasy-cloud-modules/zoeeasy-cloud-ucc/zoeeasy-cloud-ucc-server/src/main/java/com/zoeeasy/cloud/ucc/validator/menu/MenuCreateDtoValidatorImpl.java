package com.zoeeasy.cloud.ucc.validator.menu;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.zoeeasy.cloud.ucc.menu.dto.request.MenuCreateRequestDto;
import com.zoeeasy.cloud.ucc.menu.validator.MenuCreateDtoValidator;
import org.springframework.stereotype.Component;

/**
 * MenuCreateDtoValidatorImpl
 *
 * @author walkman
 */
@Component
public class MenuCreateDtoValidatorImpl extends ValidatorHandler<MenuCreateRequestDto> implements MenuCreateDtoValidator {

    @Override
    public boolean validate(ValidatorContext context, MenuCreateRequestDto requestDto) {
        return true;
    }
}
