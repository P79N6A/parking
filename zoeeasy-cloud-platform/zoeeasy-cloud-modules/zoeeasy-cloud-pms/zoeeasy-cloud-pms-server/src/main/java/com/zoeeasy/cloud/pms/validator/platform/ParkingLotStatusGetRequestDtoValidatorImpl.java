package com.zoeeasy.cloud.pms.validator.platform;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingLotInfoEntity;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingLotConstant;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingLotStatusGetRequestDto;
import com.zoeeasy.cloud.pms.platform.validator.ParkingLotStatusGetRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingLotInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author AkeemSuper
 * @date 2018/9/26 0026
 */
@Component
public class ParkingLotStatusGetRequestDtoValidatorImpl extends ValidatorHandler<ParkingLotStatusGetRequestDto> implements ParkingLotStatusGetRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;
    @Autowired
    private ParkingLotInfoCrudService parkingLotInfoCrudService;

    @Override
    public boolean validate(ValidatorContext context, ParkingLotStatusGetRequestDto requestDto) {
        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.getParkInfoById(requestDto.getParkingId());
        if (parkingInfoEntity == null) {
            throw new ValidationException(ParkingConstant.PARKING_NOT_FOUND);
        }
        ParkingLotInfoEntity lotInfoEntity = parkingLotInfoCrudService.findByParkingId(requestDto.getParkingId(), requestDto.getParkingLotId(),requestDto.getTenantId());
        if (null == lotInfoEntity) {
            throw new ValidationException(ParkingLotConstant.PARKING_LOT_NOT_FOUND);
        }
        return true;
    }

}
