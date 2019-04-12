package com.zoeeasy.cloud.ucc.validator.menu;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.zoeeasy.cloud.ucc.menu.dto.request.MenuEditRequestDto;
import com.zoeeasy.cloud.ucc.menu.validator.MenuEditDtoValidator;
import org.springframework.stereotype.Component;

/**
 * MenuEditDtoValidatorImpl
 *
 * @author walkman
 */
@Component
public class MenuEditDtoValidatorImpl extends ValidatorHandler<MenuEditRequestDto> implements MenuEditDtoValidator {

    @Override
    public boolean validate(ValidatorContext context, MenuEditRequestDto requestDto) {
        return true;
    }
}
