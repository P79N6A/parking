package com.zoeeasy.cloud.charge.validator.appointrule;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.charge.appointrule.dto.request.AppointRuleAddRequestDto;
import com.zoeeasy.cloud.charge.appointrule.validator.AppointRuleAddDtoValidator;
import com.zoeeasy.cloud.charge.cts.AppointConstant;
import org.springframework.stereotype.Component;

/**
 * 新增地区参数校验
 * Created by song on 2018/9/12.
 */
@Component
public class AppointRuleAddDtoValidatorImpl extends ValidatorHandler<AppointRuleAddRequestDto> implements AppointRuleAddDtoValidator {

    @Override
    public boolean validate(ValidatorContext context, AppointRuleAddRequestDto requestDto) {
        if (requestDto.getRuleCode().length() > 20) {
            throw new ValidationException(AppointConstant.INPUT_TOO_LONG);
        }
        if (requestDto.getRuleName().length() > 20) {
            throw new ValidationException(AppointConstant.INPUT_TOO_LONG);
        }
        String reg = "^(([0-1][0-9])|2[0-3]):[0-5][0-9]$";
        if (!requestDto.getStartTime().matches(reg) || !requestDto.getEndTime().matches(reg) || requestDto.getStartTime().compareTo(requestDto.getEndTime()) >= 0) {
            throw new ValidationException(AppointConstant.TIME_ILLEGAL);
        }
        if (requestDto.getDescription().length() > 500) {
            throw new ValidationException(AppointConstant.INPUT_TOO_LONG);
        }
        return true;
    }
}
