package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingApplyInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.enums.AuditStatusEnum;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.dto.request.RepealApplyRequestDto;
import com.zoeeasy.cloud.pms.park.validator.RepealApplyRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingApplyInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepealApplyRequestDtoValidatorImpl extends ValidatorHandler<RepealApplyRequestDto> implements RepealApplyRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingApplyInfoCrudService parkingApplyInfoCrudService;

    @Override
    public boolean accept(ValidatorContext context, RepealApplyRequestDto requestDto) {
        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(requestDto.getParkingId());
        if (parkingInfoEntity == null) {
            throw new ValidationException(ParkingConstant.PARKING_NOT_FOUND);
        }
        ParkingApplyInfoEntity parkingApplyInfoExist = parkingApplyInfoCrudService.selectParkingApplyByParkingId(requestDto.getParkingId());
        if (parkingApplyInfoExist == null) {
            throw new ValidationException(ParkingConstant.PARKING_APPLY_NOT_NULL);
        }
        if (!parkingApplyInfoExist.getAuditStatus().equals(AuditStatusEnum.AWAIT.getValue())) {
            throw new ValidationException(ParkingConstant.APPLY_NOT_REPEAL);
        }
        return true;
    }
}
