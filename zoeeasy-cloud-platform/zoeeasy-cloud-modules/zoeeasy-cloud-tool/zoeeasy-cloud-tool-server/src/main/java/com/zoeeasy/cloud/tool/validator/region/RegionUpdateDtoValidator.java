package com.zoeeasy.cloud.tool.validator.region;

import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.tool.region.dto.RegionUpdateRequestDto;

/**
 * 地区修改参数校验
 * Created by song on 2018/9/12.
 */
public class RegionUpdateDtoValidator extends ValidatorHandler<RegionUpdateRequestDto> implements Validator<RegionUpdateRequestDto> {

    @Override
    public boolean accept(ValidatorContext context, RegionUpdateRequestDto requestDto) {
        return true;
    }

    @Override
    public boolean validate(ValidatorContext context, RegionUpdateRequestDto requestDto) {
        if (StringUtils.isEmpty(requestDto.getCode())) {
            throw new ValidationException("地区代码为空");
        } else if (StringUtils.isEmpty(requestDto.getName())) {
            throw new ValidationException("地区名称为空");
        } else if (StringUtils.isEmpty(requestDto.getFullName())) {
            throw new ValidationException("地区全称为空");
        }
        return true;
    }
}
