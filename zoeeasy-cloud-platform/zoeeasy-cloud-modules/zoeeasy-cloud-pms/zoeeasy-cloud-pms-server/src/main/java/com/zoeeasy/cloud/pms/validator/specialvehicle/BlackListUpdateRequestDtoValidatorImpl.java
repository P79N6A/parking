package com.zoeeasy.cloud.pms.validator.specialvehicle;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.SpecialVehicleEntity;
import com.zoeeasy.cloud.pms.enums.SpecialTypeEnum;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import com.zoeeasy.cloud.pms.service.SpecialVehicleCrudService;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.BlackListUpdateRequestDto;
import com.zoeeasy.cloud.pms.specialvehicle.validator.BlackListUpdateRequestDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by song on 2018/10/19.
 */
@Component
public class BlackListUpdateRequestDtoValidatorImpl extends ValidatorHandler<BlackListUpdateRequestDto> implements BlackListUpdateRequestDtoValidator {

    @Autowired
    private SpecialVehicleCrudService specialVehicleCrudService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Override
    public boolean accept(ValidatorContext context, BlackListUpdateRequestDto requestDto) {
        if (requestDto.getId() != null) {
            EntityWrapper<SpecialVehicleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            entityWrapper.eq("specialType", SpecialTypeEnum.BLACK_LIST.getValue());
            SpecialVehicleEntity specialVehicleEntity = specialVehicleCrudService.selectOne(entityWrapper);
            if (specialVehicleEntity == null) {
                throw new ValidationException(SpecialVehicleConstant.BLACKLIST_NOT_EXIST);
            }
            if (specialVehicleEntity.getBeginTime().compareTo(requestDto.getEndTime()) > 0) {
                throw new ValidationException(SpecialVehicleConstant.BEGIN_TIME_NOT_GT_END_TIME);
            }
        }
        return true;
    }
}
