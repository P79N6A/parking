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
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.WhiteListAddRequestDto;
import com.zoeeasy.cloud.pms.specialvehicle.validator.WhiteListAddRequestDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 新增白名单效验
 *
 * @date: 2018/10/16.
 * @author：zm
 */
@Component
public class WhiteListAddRequestDtoValidatorImpl extends ValidatorHandler<WhiteListAddRequestDto> implements WhiteListAddRequestDtoValidator {

    @Autowired
    private SpecialVehicleCrudService specialVehicleCrudService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;


    @Override
    public boolean validate(ValidatorContext context, WhiteListAddRequestDto requestDto) {
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
