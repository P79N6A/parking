package com.zoeeasy.cloud.pms.validator.parkingarea;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingAreaEntity;
import com.zoeeasy.cloud.pms.domain.ParkingFloorEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingLotConstant;
import com.zoeeasy.cloud.pms.parkingarea.cst.ParkingAreaConstant;
import com.zoeeasy.cloud.pms.parkingarea.dto.request.ParkingAreaUpdateRequestDto;
import com.zoeeasy.cloud.pms.parkingarea.validator.ParkingAreaUpdateRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingAreaCrudService;
import com.zoeeasy.cloud.pms.service.ParkingFloorCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by song on 2018/9/28.
 */
@Component
public class ParkingAreaUpdateRequestDtoValidatorImpl extends ValidatorHandler<ParkingAreaUpdateRequestDto> implements ParkingAreaUpdateRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingAreaCrudService parkingAreaCrudService;

    @Autowired
    private ParkingFloorCrudService parkingFloorCrudService;

    @Override
    public boolean validate(ValidatorContext context, ParkingAreaUpdateRequestDto requestDto) {
        if (requestDto.getId() == null){
            throw new ValidationException(ParkingAreaConstant.PARKING_AREA_ID_NOT_NULL);
        }
        if (requestDto.getLotFixed() > requestDto.getLotTotal()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTFIXED_NOT_GT_PARKING_LOTCOUNT);
        }
        if (requestDto.getLotAvailable() > requestDto.getLotTotal()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTAVAILABLE_NOT_GT_PARKING_LOTCOUNT);
        }
        ParkingAreaEntity parkingAreaExist = parkingAreaCrudService.selectById(requestDto.getId());
        if (parkingAreaExist == null) {
            throw new ValidationException(ParkingAreaConstant.PARKING_AREA_NOT_EXIST);
        }
        if (parkingAreaExist.getGarageId() != null) {
            ParkingAreaEntity parkingAreaEntity = parkingAreaCrudService.findByNameAndGarageId(requestDto.getName(), parkingAreaExist.getGarageId());
            if (parkingAreaEntity != null && !parkingAreaEntity.getId().equals(requestDto.getId())) {
                throw new ValidationException(ParkingAreaConstant.PARKING_AREA_NAME_USED);
            }
        } else {
            ParkingAreaEntity parkingAreaEntity = parkingAreaCrudService.findByNameAndParkingId(requestDto.getName(), parkingAreaExist.getParkingId());
            if (parkingAreaEntity != null && !parkingAreaEntity.getId().equals(requestDto.getId())) {
                throw new ValidationException(ParkingAreaConstant.PARKING_AREA_NAME_USED);
            }
        }
        ParkingInfoEntity parkingInfoExist = parkingInfoCrudService.selectById(parkingAreaExist.getParkingId());
        if (parkingInfoExist != null){
            Integer lotTotalExist = parkingAreaCrudService.findLotTotalByParkingId(parkingAreaExist.getParkingId());
            Integer lotTotal = requestDto.getLotTotal();
            if (lotTotalExist != null) {
                lotTotal = lotTotal + lotTotalExist - parkingAreaExist.getLotTotal();
            }
            if (lotTotal > parkingInfoExist.getLotTotal()) {
                throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTCOUNT_NOT_GT_PARKING_LOTCOUNT);
            }
            Integer lotFixedTotalExist = parkingAreaCrudService.findLotFixedTotalByParkingId(parkingAreaExist.getParkingId());
            Integer lotFixedTotal = requestDto.getLotFixed();
            if (lotFixedTotalExist != null) {
                lotFixedTotal = lotFixedTotal + lotFixedTotalExist - parkingAreaExist.getLotFixed();
            }
            if (lotFixedTotal > parkingInfoExist.getLotFixed()) {
                throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTFIXED_NOT_GT_PARKING_LOTFIXED);
            }
        }
        if (requestDto.getFloorId() != null){
            ParkingFloorEntity parkingFloorEntity = parkingFloorCrudService.selectById(requestDto.getFloorId());
            if (parkingFloorEntity == null){
                throw new ValidationException(ParkingLotConstant.FLOOR_NOT_FOUND);
            }
        }
        return true;
    }
}
