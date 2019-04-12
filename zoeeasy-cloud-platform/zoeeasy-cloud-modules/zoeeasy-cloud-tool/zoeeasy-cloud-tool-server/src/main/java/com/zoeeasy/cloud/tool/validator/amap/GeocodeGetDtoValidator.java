package com.zoeeasy.cloud.tool.validator.amap;

import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.tool.amap.dto.request.GeocodeGetRequestDto;

/**
 * 逆地理获取adcode
 *
 * @author lhj
 */
public class GeocodeGetDtoValidator extends ValidatorHandler<GeocodeGetRequestDto> implements Validator<GeocodeGetRequestDto> {

    @Override
    public boolean accept(ValidatorContext context, GeocodeGetRequestDto requestDto) {
        return true;
    }

    @Override
    public boolean validate(ValidatorContext context, GeocodeGetRequestDto requestDto) {

        if (StringUtils.isEmpty(requestDto.getLocation()))
            throw new ValidationException("location为空");

        return true;
    }
}