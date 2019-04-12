package com.zoeeasy.cloud.ucc.validator.menu;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.zoeeasy.cloud.ucc.menu.dto.request.MenuDeleteRequestDto;
import com.zoeeasy.cloud.ucc.menu.validator.MenuDeleteDtoValidator;
import org.springframework.stereotype.Component;

/**
 * MenuDeleteDtoValidatorImpl
 *
 * @author walkman
 */
@Component
public class MenuDeleteDtoValidatorImpl extends ValidatorHandler<MenuDeleteRequestDto> implements MenuDeleteDtoValidator {

    @Override
    public boolean validate(ValidatorContext context, MenuDeleteRequestDto requestDto) {
        return true;
    }
}
