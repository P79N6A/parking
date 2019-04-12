package com.zoeeasy.cloud.tool.validator.amap;

import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.tool.amap.dto.request.LocationConvertRequestDto;

/**
 * 坐标转换
 *
 * @author lhj
 */
public class LocationConvertDtoValidator extends ValidatorHandler<LocationConvertRequestDto> implements Validator<LocationConvertRequestDto> {

    @Override
    public boolean accept(ValidatorContext context, LocationConvertRequestDto requestDto) {
        return true;
    }

    @Override
    public boolean validate(ValidatorContext context, LocationConvertRequestDto requestDto) {

        if (requestDto.getLatitude() == null || requestDto.getLongitude() == null)
            throw new ValidationException("坐标经纬度为空");

        return true;
    }
}
