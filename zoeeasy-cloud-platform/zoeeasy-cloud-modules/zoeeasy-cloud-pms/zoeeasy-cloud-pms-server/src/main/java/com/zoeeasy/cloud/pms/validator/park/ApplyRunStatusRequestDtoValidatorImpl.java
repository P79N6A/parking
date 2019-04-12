package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.enums.ApplyTypeEnum;
import com.zoeeasy.cloud.pms.enums.AuditStatusEnum;
import com.zoeeasy.cloud.pms.enums.ParkingStatusEnum;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.dto.request.ApplyRunStatusRequestDto;
import com.zoeeasy.cloud.pms.park.validator.ApplyRunStatusRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplyRunStatusRequestDtoValidatorImpl extends ValidatorHandler<ApplyRunStatusRequestDto> implements ApplyRunStatusRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Override
    public boolean accept(ValidatorContext context, ApplyRunStatusRequestDto requestDto) {
        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.getParkInfoById(requestDto.getParkingId());
        if (parkingInfoEntity == null){
            throw new ValidationException(ParkingConstant.PARKING_NOT_FOUND);
        }
        if (parkingInfoEntity.getAuditStatus() != null && parkingInfoEntity.getAuditStatus().equals(AuditStatusEnum.AWAIT.getValue())){
            throw new ValidationException(ParkingConstant.PARKING_APPLYING);
        }
        if (requestDto.getApplyType().equals(ApplyTypeEnum.UP.getValue())
                && parkingInfoEntity.getStatus().equals(ParkingStatusEnum.ON_LINE.getValue())){
            throw new ValidationException(ParkingConstant.PARKING_ALREADY_ON_LINE);
        }
        if (requestDto.getApplyType().equals(ApplyTypeEnum.DOWN.getValue())
                && parkingInfoEntity.getStatus().equals(ParkingStatusEnum.NOT_ON_LINE.getValue())){
            throw new ValidationException(ParkingConstant.PARKING_ALREADY_NOT_ON_LINE);
        }
        return true;
    }
}
