package com.zoeeasy.cloud.pms.validator.lane;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingGarageInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingGateInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingLaneInfoEntity;
import com.zoeeasy.cloud.pms.lane.cst.ParkingLaneConstant;
import com.zoeeasy.cloud.pms.lane.dto.request.ParkingLaneUpdateRequestDto;
import com.zoeeasy.cloud.pms.lane.validator.ParkingLaneUpdateValidator;
import com.zoeeasy.cloud.pms.service.ParkingGarageInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingGateInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingLaneInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkingLaneUpdateValidatorImpl extends ValidatorHandler<ParkingLaneUpdateRequestDto> implements ParkingLaneUpdateValidator {
    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingLaneInfoCrudService parkingLaneInfoCrudService;

    @Autowired
    private ParkingGateInfoCrudService parkingGateInfoCrudService;

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Override
    public boolean validate(ValidatorContext context, ParkingLaneUpdateRequestDto requestDto) {
        if (requestDto.getId() == null) {
            throw new ValidationException(ParkingLaneConstant.PARKING_LANE_ID_CAN_NOT_NULL);
        }
        if (requestDto.getId() != null) {
            ParkingLaneInfoEntity laneInfoEntity = parkingLaneInfoCrudService.selectById(requestDto.getId());
            if (laneInfoEntity == null) {
                throw new ValidationException(ParkingLaneConstant.PARKING_LANE_NOT_EXIT);
            }
        }
        if (requestDto.getGarageId() != null) {
            ParkingGarageInfoEntity garageInfoEntity = parkingGarageInfoCrudService.selectById(requestDto.getGarageId());
            if (garageInfoEntity == null) {
                throw new ValidationException(ParkingLaneConstant.PARKING_GARAGE_NOT_EXIT);
            }
        }
        if (requestDto.getGateId() != null) {
            ParkingGateInfoEntity gateInfoEntity = parkingGateInfoCrudService.selectById(requestDto.getGateId());
            if (gateInfoEntity == null) {
                throw new ValidationException(ParkingLaneConstant.PARKING_GATE_NOT_EXIT);
            }
        }
        if (requestDto.getParkingId() != null) {
            ParkingInfoEntity entity = parkingInfoCrudService.selectById(requestDto.getParkingId());
            if (entity == null) {
                throw new ValidationException(ParkingLaneConstant.PARKING_NOT_EXIT);
            }
        }
        if (requestDto.getGateId() != null) {
            ParkingLaneInfoEntity nameExit = parkingLaneInfoCrudService.findByNameAndGateId(requestDto.getName(), requestDto.getGateId());
            if (nameExit != null && !nameExit.getCode().equals(requestDto.getCode())) {
                throw new ValidationException(ParkingLaneConstant.PARKING_LANE_NAME_EXIT);
            }
        }
        ParkingLaneInfoEntity nameExit = parkingLaneInfoCrudService.findByNameAndParkingId(requestDto.getName(), requestDto.getParkingId());
        if (nameExit != null && !nameExit.getCode().equals(requestDto.getCode())) {
            throw new ValidationException(ParkingLaneConstant.PARKING_LANE_NAME_EXIT);
        }

        return super.validate(context, requestDto);
    }

}

