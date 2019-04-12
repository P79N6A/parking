package com.zoeeasy.cloud.tool.validator.region;

import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.tool.region.dto.RegionAddRequestDto;

/**
 * 新增地区参数校验
 * Created by song on 2018/9/12.
 */
public class RegionAddDtoValidator extends ValidatorHandler<RegionAddRequestDto> implements Validator<RegionAddRequestDto> {

    @Override
    public boolean accept(ValidatorContext context, RegionAddRequestDto requestDto) {
        return true;
    }

    @Override
    public boolean validate(ValidatorContext context, RegionAddRequestDto requestDto) {
        if (StringUtils.isEmpty(requestDto.getCode())) {
            throw new ValidationException("区域代码为空");
        } else if (StringUtils.isEmpty(requestDto.getName())) {
            throw new ValidationException("区域名称为空");
        }
        return true;
    }
}
