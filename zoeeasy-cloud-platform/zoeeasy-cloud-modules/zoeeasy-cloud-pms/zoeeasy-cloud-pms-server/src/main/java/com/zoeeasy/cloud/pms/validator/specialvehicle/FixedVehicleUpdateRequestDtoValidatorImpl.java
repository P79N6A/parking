package com.zoeeasy.cloud.pms.validator.specialvehicle;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.SpecialVehicleEntity;
import com.zoeeasy.cloud.pms.enums.SpecialTypeEnum;
import com.zoeeasy.cloud.pms.service.SpecialVehicleCrudService;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.FixedVehicleUpdateRequestDto;
import com.zoeeasy.cloud.pms.specialvehicle.validator.FixedVehicleUpdateRequestDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @date: 2018/10/22.
 * @author：zm
 */
@Component
public class FixedVehicleUpdateRequestDtoValidatorImpl extends ValidatorHandler<FixedVehicleUpdateRequestDto> implements FixedVehicleUpdateRequestDtoValidator {

    @Autowired
    private SpecialVehicleCrudService specialVehicleCrudService;


    @Override
    public boolean accept(ValidatorContext context, FixedVehicleUpdateRequestDto requestDto) {
        if (requestDto.getId() != null) {
            EntityWrapper<SpecialVehicleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            entityWrapper.eq("specialType", SpecialTypeEnum.FIXED_CAR.getValue());
            SpecialVehicleEntity specialVehicleEntity = specialVehicleCrudService.selectOne(entityWrapper);
            if (specialVehicleEntity == null) {
                throw new ValidationException(SpecialVehicleConstant.FIXED_VEHICLE_NOT_EXIST);
            }
            if (specialVehicleEntity.getBeginTime().compareTo(requestDto.getEndTime()) > 0) {
                throw new ValidationException(SpecialVehicleConstant.BEGIN_TIME_NOT_GT_END_TIME);
            }
        }
        return true;
    }

}
