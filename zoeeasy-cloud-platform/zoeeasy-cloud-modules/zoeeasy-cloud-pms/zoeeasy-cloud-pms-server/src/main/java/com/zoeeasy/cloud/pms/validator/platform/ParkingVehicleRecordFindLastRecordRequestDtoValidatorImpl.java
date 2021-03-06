package com.zoeeasy.cloud.pms.validator.platform;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingLotInfoEntity;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingLotConstant;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingVehicleRecordFindLastRecordRequestDto;
import com.zoeeasy.cloud.pms.platform.validator.ParkingVehicleRecordFindLastRecordRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingLotInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author AkeemSuper
 * @date 2018/9/29 0029
 */
@Component
public class ParkingVehicleRecordFindLastRecordRequestDtoValidatorImpl extends ValidatorHandler<ParkingVehicleRecordFindLastRecordRequestDto> implements ParkingVehicleRecordFindLastRecordRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;
    @Autowired
    private ParkingLotInfoCrudService parkingLotInfoCrudService;

    @Override
    public boolean validate(ValidatorContext context, ParkingVehicleRecordFindLastRecordRequestDto requestDto) {
        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.getParkInfoById(requestDto.getParkingId());
        if (parkingInfoEntity == null) {
            throw new ValidationException(ParkingConstant.PARKING_NOT_FOUND);
        }
        if (null != requestDto.getParkingLotId()) {
            ParkingLotInfoEntity lotInfoEntity = parkingLotInfoCrudService.findByParkingId(requestDto.getParkingId(), requestDto.getParkingLotId(), requestDto.getTenantId());
            if (null == lotInfoEntity) {
                throw new ValidationException(ParkingLotConstant.PARKING_LOT_NOT_FOUND);
            }
        }
        if (null == requestDto.getEndTime()) {
            throw new ValidationException(ParkingConstant.PARKING_VEHICLE_RECORD_END_TIME_NULL);
        }
        return true;
    }
}
