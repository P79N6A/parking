package com.zoeeasy.cloud.pms.validator.specialvehicle;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingLotInfoEntity;
import com.zoeeasy.cloud.pms.domain.SpecialVehicleEntity;
import com.zoeeasy.cloud.pms.enums.SpecialTypeEnum;
import com.zoeeasy.cloud.pms.service.ParkingLotInfoCrudService;
import com.zoeeasy.cloud.pms.service.SpecialVehicleCrudService;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.RelevanceParkingLotIdRequestDto;
import com.zoeeasy.cloud.pms.specialvehicle.validator.RelevanceRequestDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @date: 2018/11/5.
 * @author：zm
 */
@Component
public class RelevanceRequestDtoValidatorImpl extends ValidatorHandler<RelevanceParkingLotIdRequestDto> implements RelevanceRequestDtoValidator {

    @Autowired
    private ParkingLotInfoCrudService parkingLotInfoCrudService;


    @Autowired
    private SpecialVehicleCrudService specialVehicleCrudService;

    @Override
    public boolean validate(ValidatorContext context, RelevanceParkingLotIdRequestDto requestDto) {
        SpecialVehicleEntity specialVehicleEntity = specialVehicleCrudService.findByIdAndSpecialType(requestDto.getId(), SpecialTypeEnum.FIXED_CAR.getValue());
        if (specialVehicleEntity == null) {
            throw new ValidationException(SpecialVehicleConstant.FIXED_VEHICLE_NOT_EXIST);
        }
        if (requestDto.getParkingLotId() != null && !StringUtils.isEmpty(requestDto.getParkingLotNumber())) {
            ParkingLotInfoEntity parkingLotInfoEntity = parkingLotInfoCrudService.findByIdAndCode(requestDto.getParkingLotId(), specialVehicleEntity.getParkingId(), requestDto.getParkingLotNumber());
            if (parkingLotInfoEntity == null) {
                throw new ValidationException(SpecialVehicleConstant.PARKING_LOT_NOT_EXIST);
            }
            SpecialVehicleEntity specialVehicle = specialVehicleCrudService.findByParkingLotId(requestDto.getParkingLotId(), requestDto.getParkingLotNumber());
            if (specialVehicle != null) {
                throw new ValidationException(SpecialVehicleConstant.PARKING_LOT_BINDING);
            }
        }
        return true;
    }
}
