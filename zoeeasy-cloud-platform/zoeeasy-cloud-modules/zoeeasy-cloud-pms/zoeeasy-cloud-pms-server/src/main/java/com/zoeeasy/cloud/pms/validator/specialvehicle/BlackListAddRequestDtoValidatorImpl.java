package com.zoeeasy.cloud.pms.validator.specialvehicle;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.PlateNumberUtil;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.domain.SpecialVehicleEntity;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import com.zoeeasy.cloud.pms.service.SpecialVehicleCrudService;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.BlackListAddRequestDto;
import com.zoeeasy.cloud.pms.specialvehicle.validator.BlackListAddRequestDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by song on 2018/10/19.
 */
@Component
public class BlackListAddRequestDtoValidatorImpl extends ValidatorHandler<BlackListAddRequestDto> implements BlackListAddRequestDtoValidator {

    @Autowired
    private SpecialVehicleCrudService specialVehicleCrudService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Override
    public boolean accept(ValidatorContext context, BlackListAddRequestDto requestDto) {
        boolean plateNumber = PlateNumberUtil.isPlateNumber(requestDto.getPlateNumber());
        if (!plateNumber) {
            throw new ValidationException(ParkingConstant.PLATENUMBER_ILLEGAL);
        }
        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(requestDto.getParkingId());
        if (parkingInfoEntity == null) {
            throw new ValidationException(SpecialVehicleConstant.PARKING_ID_NOT_EXIST);
        }
        if (requestDto.getParkingId() != null && requestDto.getPlateNumber() != null) {
            SpecialVehicleEntity specialVehicleEntity = specialVehicleCrudService.findByPlateNumberAndParkingId(requestDto.getPlateNumber(),
                    requestDto.getParkingId());
            if (specialVehicleEntity != null) {
                throw new ValidationException(SpecialVehicleConstant.SPECIAL_TYPE_EXIST);
            }
        }
        if (requestDto.getBeginTime().compareTo(requestDto.getEndTime()) > 0) {
            throw new ValidationException(SpecialVehicleConstant.BEGIN_TIME_NOT_GT_END_TIME);
        }
        return true;
    }
}
