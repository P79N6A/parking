package com.zoeeasy.cloud.pms.validator.floor;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingAreaEntity;
import com.zoeeasy.cloud.pms.domain.ParkingFloorEntity;
import com.zoeeasy.cloud.pms.domain.ParkingGarageInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.floor.dto.request.FloorUpdateRequestDto;
import com.zoeeasy.cloud.pms.floor.validator.FloorUpdateRequestDtoValidator;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingLotConstant;
import com.zoeeasy.cloud.pms.parkingarea.cst.ParkingAreaConstant;
import com.zoeeasy.cloud.pms.parkingarea.dto.request.ParkingAreaUpdateRequestDto;
import com.zoeeasy.cloud.pms.service.ParkingAreaCrudService;
import com.zoeeasy.cloud.pms.service.ParkingFloorCrudService;
import com.zoeeasy.cloud.pms.service.ParkingGarageInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by song on 2018/9/28.
 */
@Component
public class FloorUpdateRequestDtoValidatorImpl extends ValidatorHandler<FloorUpdateRequestDto> implements FloorUpdateRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingFloorCrudService parkingFloorCrudService;

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Override
    public boolean validate(ValidatorContext context, FloorUpdateRequestDto requestDto) {
        if (requestDto.getId() == null){
            throw new ValidationException(GarageConstant.ID_NOT_NULL);
        }
        if (requestDto.getLotFixed() > requestDto.getLotCount()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTFIXED_NOT_GT_PARKING_LOTCOUNT);
        }
        if (requestDto.getLotAvailable() > requestDto.getLotCount()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTAVAILABLE_NOT_GT_PARKING_LOTCOUNT);
        }
        ParkingFloorEntity parkingFloorEntity = parkingFloorCrudService.selectById(requestDto.getId());
        if (parkingFloorEntity == null){
            throw new ValidationException(ParkingLotConstant.FLOOR_NOT_FOUND);
        }
        EntityWrapper<ParkingFloorEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("floorCode", requestDto.getFloorCode());
        ParkingFloorEntity parkingFloorEntityByCode = parkingFloorCrudService.selectOne(entityWrapper);
        if (parkingFloorEntityByCode != null && !parkingFloorEntityByCode.getId().equals(requestDto.getId())) {
            throw new ValidationException(ParkingAreaConstant.FLOOR_CODE_EXIST);
        }
        ParkingInfoEntity parkingInfoExist = parkingInfoCrudService.selectById(requestDto.getParkingId());
        if (parkingInfoExist == null) {
            throw new ValidationException(ParkingConstant.PARKING_NOT_FOUND);
        }
        ParkingGarageInfoEntity parkingGarageExist = parkingGarageInfoCrudService.selectById(requestDto.getGarageId());
        if (parkingGarageExist == null) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_NOT_FOUND);
        }
        if (!parkingInfoExist.getId().equals(parkingGarageExist.getParkingId())) {
            throw new ValidationException(ParkingAreaConstant.PARKING_NOT_FOUND_GARAGE);
        }
        Integer lotCountExist = parkingFloorCrudService.findLotCountByParkingId(requestDto.getParkingId());
        Integer lotTotal = requestDto.getLotCount();
        if (lotCountExist != null) {
            lotTotal = lotTotal + lotCountExist - parkingFloorEntity.getLotCount();
        }
        if (lotTotal > parkingInfoExist.getLotTotal()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTCOUNT_NOT_GT_PARKING_LOTCOUNT);
        }
        Integer lotFixedTotalExist = parkingFloorCrudService.findLotFixedTotalByParkingId(requestDto.getParkingId());
        Integer lotFixedTotal = requestDto.getLotFixed();
        if (lotFixedTotalExist != null) {
            lotFixedTotal = lotFixedTotal + lotFixedTotalExist - parkingFloorEntity.getLotFixed();
        }
        if (lotFixedTotal > parkingInfoExist.getLotFixed()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTFIXED_NOT_GT_PARKING_LOTFIXED);
        }
        return true;
    }
}
