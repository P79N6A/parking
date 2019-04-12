package com.zoeeasy.cloud.pms.validator.platform;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingLotInfoGetByParkingIdRequestDto;
import com.zoeeasy.cloud.pms.platform.validator.ParkingLotInfoGetByParkingIdRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author AkeemSuper
 * @date 2018/9/28 0028
 */
@Component
public class ParkingLotInfoGetByParkingIdRequestDtoValidatorImpl extends ValidatorHandler<ParkingLotInfoGetByParkingIdRequestDto> implements ParkingLotInfoGetByParkingIdRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Override
    public boolean validate(ValidatorContext context, ParkingLotInfoGetByParkingIdRequestDto requestDto) {
        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.getParkInfoById(requestDto.getParkingId());
        if (parkingInfoEntity == null) {
            throw new ValidationException(ParkingConstant.PARKING_NOT_FOUND);
        }
        return true;
    }
}
