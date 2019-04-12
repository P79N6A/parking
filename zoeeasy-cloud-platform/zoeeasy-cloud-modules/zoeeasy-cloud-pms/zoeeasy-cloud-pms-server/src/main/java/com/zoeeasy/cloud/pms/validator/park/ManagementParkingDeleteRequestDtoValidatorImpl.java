package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.enums.ParkingStatusEnum;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingDeleteRequestDto;
import com.zoeeasy.cloud.pms.park.validator.ManagementParkingDeleteRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManagementParkingDeleteRequestDtoValidatorImpl extends ValidatorHandler<ParkingDeleteRequestDto> implements ManagementParkingDeleteRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Override
    public boolean accept(ValidatorContext context, ParkingDeleteRequestDto requestDto) {
        if (requestDto.getId() == null) {
            throw new ValidationException(ParkingConstant.PARKING_INFO_PARKING_ID_NOT_NULL);
        }
        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.getParkInfoById(requestDto.getId());
        if (parkingInfoEntity == null) {
            throw new ValidationException(ParkingConstant.PARKING_NOT_FOUND);
        }
        if (parkingInfoEntity.getTenantId() != null) {
            throw new ValidationException(ParkingConstant.PARKING_CANNOT_DELETE);
        }
        if (parkingInfoEntity.getStatus().equals(ParkingStatusEnum.ON_LINE.getValue())) {
            throw new ValidationException(ParkingConstant.PARKING_CANNOT_DELETE);
        }
        return true;
    }
}
