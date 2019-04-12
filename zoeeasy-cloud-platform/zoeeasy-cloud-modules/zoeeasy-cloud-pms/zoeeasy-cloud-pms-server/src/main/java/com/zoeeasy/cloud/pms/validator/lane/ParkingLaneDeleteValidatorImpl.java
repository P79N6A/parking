package com.zoeeasy.cloud.pms.validator.lane;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingLaneInfoEntity;
import com.zoeeasy.cloud.pms.lane.cst.ParkingLaneConstant;
import com.zoeeasy.cloud.pms.lane.dto.request.ParkingLaneDeleteRequestDto;
import com.zoeeasy.cloud.pms.lane.validator.ParkingLaneDeleteValidator;
import com.zoeeasy.cloud.pms.service.ParkingLaneInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkingLaneDeleteValidatorImpl extends ValidatorHandler<ParkingLaneDeleteRequestDto> implements ParkingLaneDeleteValidator {

    @Autowired
    private ParkingLaneInfoCrudService parkingLaneInfoCrudService;

    @Override
    public boolean validate(ValidatorContext context, ParkingLaneDeleteRequestDto requestDto) {
        if (requestDto.getId() == null) {
            throw new ValidationException(ParkingLaneConstant.PARKING_LANE_ID_CAN_NOT_NULL);
        }
        ParkingLaneInfoEntity parkingLaneInfoEntity = parkingLaneInfoCrudService.selectById(requestDto.getId());
        if (parkingLaneInfoEntity == null) {
            throw new ValidationException(ParkingLaneConstant.PARKING_LANE_NOT_EXIT);
        }
        return super.validate(context, requestDto);
    }
}
