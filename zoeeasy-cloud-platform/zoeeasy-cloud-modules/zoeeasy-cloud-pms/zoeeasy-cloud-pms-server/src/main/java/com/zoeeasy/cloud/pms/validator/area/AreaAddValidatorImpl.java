package com.zoeeasy.cloud.pms.validator.area;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.area.cst.AreaConstant;
import com.zoeeasy.cloud.pms.area.dto.request.AreaAddRequestDto;
import com.zoeeasy.cloud.pms.area.validator.AreaAddValidator;
import com.zoeeasy.cloud.pms.domain.AreaEntity;
import com.zoeeasy.cloud.pms.service.AreaCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AreaAddValidatorImpl extends ValidatorHandler<AreaAddRequestDto> implements AreaAddValidator {

    @Autowired
    private AreaCrudService areaCrudService;

    @Override
    public boolean validate(ValidatorContext context, AreaAddRequestDto requestDto) {
        if (!StringUtils.isEmpty(requestDto.getCode())) {
            AreaEntity areaExit = areaCrudService.findByCode(requestDto.getCode());
            if (areaExit != null) {
                throw new ValidationException(AreaConstant.AREA_EXIT);
            }
        }

        AreaEntity areaNameExit = areaCrudService.findByName(requestDto.getName());
        if (areaNameExit != null) {
            throw new ValidationException(AreaConstant.AREA_NAME_EXIT);
        }

        return super.validate(context, requestDto);
    }
}
