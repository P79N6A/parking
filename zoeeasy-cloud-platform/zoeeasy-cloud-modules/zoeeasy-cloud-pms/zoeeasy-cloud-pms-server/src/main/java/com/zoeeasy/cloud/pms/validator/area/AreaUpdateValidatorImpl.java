package com.zoeeasy.cloud.pms.validator.area;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.area.cst.AreaConstant;
import com.zoeeasy.cloud.pms.area.dto.request.AreaUpdateRequestDto;
import com.zoeeasy.cloud.pms.area.validator.AreaUpdateValidator;
import com.zoeeasy.cloud.pms.domain.AreaEntity;
import com.zoeeasy.cloud.pms.service.AreaCrudService;
import com.zoeeasy.cloud.tool.enums.RegionLevelEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AreaUpdateValidatorImpl extends ValidatorHandler<AreaUpdateRequestDto> implements AreaUpdateValidator {

    @Autowired
    private AreaCrudService areaCrudService;

    @Override
    public boolean validate(ValidatorContext context, AreaUpdateRequestDto requestDto) {
        if (!requestDto.getLevel().equals(RegionLevelEnum.CUSTOM.getValue())) {
            throw new ValidationException(AreaConstant.AREA_LEVEL_NON_EDITABLE);
        }
        AreaEntity entity = areaCrudService.findByCode(requestDto.getCode());
        if (entity == null) {
            throw new ValidationException(AreaConstant.AREA_NOT_EXIT);
        }
        if (!entity.getLevel().equals(RegionLevelEnum.CUSTOM.getValue())) {
            throw new ValidationException(AreaConstant.AREA_LEVEL_NON_EDITABLE);
        }
        AreaEntity areaNameExit = areaCrudService.findByName(requestDto.getName());
        if (areaNameExit != null && !areaNameExit.getCode().equals(entity.getCode())) {
            throw new ValidationException(AreaConstant.AREA_NAME_EXIT);
        }
        return super.validate(context, requestDto);
    }
}
