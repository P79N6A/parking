package com.zoeeasy.cloud.pms.validator.specialvehicle;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.SpecialVehicleEntity;
import com.zoeeasy.cloud.pms.enums.SpecialTypeEnum;
import com.zoeeasy.cloud.pms.service.SpecialVehicleCrudService;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.VisitVehicleDeleteRequestDto;
import com.zoeeasy.cloud.pms.specialvehicle.validator.VisitVehicleDeleteRequestDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @date: 2018/10/24.
 * @author：zm
 */
@Component
public class VisitVehicleDeleteRequestDtoValidatorImpl extends ValidatorHandler<VisitVehicleDeleteRequestDto> implements VisitVehicleDeleteRequestDtoValidator {

    @Autowired
    private SpecialVehicleCrudService specialVehicleCrudService;

    @Override
    public boolean validate(ValidatorContext context, VisitVehicleDeleteRequestDto requestDto) {
        EntityWrapper<SpecialVehicleEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("id", requestDto.getId());
        entityWrapper.eq("specialType", SpecialTypeEnum.VISITOR_CAR.getValue());
        SpecialVehicleEntity specialVehicleEntity = specialVehicleCrudService.selectOne(entityWrapper);
        if (specialVehicleEntity == null) {
            throw new ValidationException(SpecialVehicleConstant.VISIT_VEHICLE_NOT_EXIST);
        }
        return true;
    }

}
