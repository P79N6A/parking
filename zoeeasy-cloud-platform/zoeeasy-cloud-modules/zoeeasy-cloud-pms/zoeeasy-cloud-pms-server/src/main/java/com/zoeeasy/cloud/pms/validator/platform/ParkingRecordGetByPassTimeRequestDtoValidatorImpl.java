package com.zoeeasy.cloud.pms.validator.platform;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.PlateNumberUtil;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingRecordGetByPassTimeRequestDto;
import com.zoeeasy.cloud.pms.platform.validator.ParkingRecordGetByPassTimeRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author AkeemSuper
 * @date 2018/10/17 0017
 */
@Component
public class ParkingRecordGetByPassTimeRequestDtoValidatorImpl extends ValidatorHandler<ParkingRecordGetByPassTimeRequestDto> implements ParkingRecordGetByPassTimeRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Override
    public boolean validate(ValidatorContext context, ParkingRecordGetByPassTimeRequestDto requestDto) {
        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.getParkInfoById(requestDto.getParkingId());
        if (parkingInfoEntity == null) {
            throw new ValidationException(ParkingConstant.PARKING_NOT_FOUND);
        }
        boolean plateNumber = PlateNumberUtil.isPlateNumber(requestDto.getPlateNumber());
        if (!plateNumber) {
            throw new ValidationException(ParkingConstant.PLATENUMBER_ILLEGAL);
        }
        return true;
    }
}