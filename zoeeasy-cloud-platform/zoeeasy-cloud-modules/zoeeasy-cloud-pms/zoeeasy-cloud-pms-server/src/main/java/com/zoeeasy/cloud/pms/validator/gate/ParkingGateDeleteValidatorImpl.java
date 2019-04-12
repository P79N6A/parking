package com.zoeeasy.cloud.pms.validator.gate;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingGateInfoEntity;
import com.zoeeasy.cloud.pms.gate.cst.ParkingGateConstant;
import com.zoeeasy.cloud.pms.gate.dto.request.ParkingGateDeleteRequestDto;
import com.zoeeasy.cloud.pms.gate.validator.ParkingGateDeleteValidator;
import com.zoeeasy.cloud.pms.service.ParkingGateInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingLaneInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkingGateDeleteValidatorImpl extends ValidatorHandler<ParkingGateDeleteRequestDto> implements ParkingGateDeleteValidator {

    @Autowired
    private ParkingGateInfoCrudService parkingGateInfoCrudService;

    @Autowired
    private ParkingLaneInfoCrudService parkingLaneInfoCrudService;

    @Override
    public boolean validate(ValidatorContext context, ParkingGateDeleteRequestDto requestDto) {

        if (requestDto.getId() == null) {
            throw new ValidationException(ParkingGateConstant.PARKING_GATE_ID_CAN_NOT_NULL);
        }
        ParkingGateInfoEntity parkingGateInfoEntity = parkingGateInfoCrudService.selectById(requestDto.getId());
        if (parkingGateInfoEntity == null) {
            throw new ValidationException(ParkingGateConstant.PARKING_GATE_NOT_EXIT);
        }

        Integer count = parkingLaneInfoCrudService.findByGateId(requestDto.getId());
        if (count > 0) {
            throw new ValidationException(ParkingGateConstant.PARKING_GATE_HAVE_SUBSET);
        }
        return super.validate(context, requestDto);
    }
}
