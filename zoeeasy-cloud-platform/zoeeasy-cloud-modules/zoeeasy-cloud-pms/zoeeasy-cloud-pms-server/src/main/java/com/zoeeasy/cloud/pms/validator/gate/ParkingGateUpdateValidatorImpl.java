package com.zoeeasy.cloud.pms.validator.gate;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingGarageInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingGateInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.gate.cst.ParkingGateConstant;
import com.zoeeasy.cloud.pms.gate.dto.request.ParkingGateUpdateRequestDto;
import com.zoeeasy.cloud.pms.gate.validator.ParkingGateUpdateValidator;
import com.zoeeasy.cloud.pms.service.ParkingGarageInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingGateInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkingGateUpdateValidatorImpl extends ValidatorHandler<ParkingGateUpdateRequestDto> implements ParkingGateUpdateValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingGateInfoCrudService parkingGateInfoCrudService;

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Override
    public boolean validate(ValidatorContext context, ParkingGateUpdateRequestDto requestDto) {

        if (requestDto.getId() == null) {
            throw new ValidationException(ParkingGateConstant.PARKING_GATE_ID_CAN_NOT_NULL);
        }

            ParkingInfoEntity entity = parkingInfoCrudService.selectById(requestDto.getParkingId());
            if (entity == null) {
                throw new ValidationException(ParkingGateConstant.PARKING_NOT_EXIT);
            }

        if (requestDto.getGarageId() != null) {
            ParkingGarageInfoEntity garageInfoEntity = parkingGarageInfoCrudService.selectById(requestDto.getGarageId());
            if (garageInfoEntity == null) {
                throw new ValidationException(ParkingGateConstant.PARKING_GARAGE_NOT_EXIT);
            }
        }
        if (requestDto.getId() != null) {
            ParkingGateInfoEntity gateInfoEntity = parkingGateInfoCrudService.selectById(requestDto.getId());
            if (gateInfoEntity == null) {
                throw new ValidationException(ParkingGateConstant.PARKING_GATE_NOT_EXIT);
            }
        }

        if (requestDto.getGarageId() != null) {
            ParkingGateInfoEntity nameExit = parkingGateInfoCrudService.findByNameAndGarageId(requestDto.getName(), requestDto.getGarageId());
            if (nameExit != null && !nameExit.getCode().equals(requestDto.getCode())) {
                throw new ValidationException(ParkingGateConstant.PARKING_GATE_NAME_EXIT);
            }
        }
        ParkingGateInfoEntity nameExit = parkingGateInfoCrudService.findByNameAndParkingId(requestDto.getName(), requestDto.getParkingId());
        if (nameExit != null &&! nameExit.getCode().equals(requestDto.getCode())) {
            throw new ValidationException(ParkingGateConstant.PARKING_GATE_NAME_EXIT);
        }
        return super.validate(context, requestDto);
    }
}
