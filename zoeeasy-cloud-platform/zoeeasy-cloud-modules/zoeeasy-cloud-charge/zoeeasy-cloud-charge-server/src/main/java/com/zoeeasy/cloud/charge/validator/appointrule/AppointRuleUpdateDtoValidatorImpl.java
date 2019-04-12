package com.zoeeasy.cloud.charge.validator.appointrule;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.charge.appointrule.dto.request.AppointRuleUpdateRequestDto;
import com.zoeeasy.cloud.charge.appointrule.validator.AppointRuleUpdateDtoValidator;
import com.zoeeasy.cloud.charge.cts.AppointConstant;
import org.springframework.stereotype.Component;

/**
 * 新增地区参数校验
 * Created by song on 2018/9/12.
 */
@Component
public class AppointRuleUpdateDtoValidatorImpl extends ValidatorHandler<AppointRuleUpdateRequestDto> implements AppointRuleUpdateDtoValidator {

    @Override
    public boolean validate(ValidatorContext context, AppointRuleUpdateRequestDto requestDto) {
        if (StringUtils.isNotEmpty(requestDto.getDescription()) && requestDto.getDescription().length() > 500) {
            throw new ValidationException(AppointConstant.INPUT_TOO_LONG);
        }
        return true;
    }
}
