package com.zoeeasy.cloud.pms.validator.floor;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingAreaEntity;
import com.zoeeasy.cloud.pms.domain.ParkingFloorEntity;
import com.zoeeasy.cloud.pms.domain.ParkingGarageInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.floor.dto.request.FloorAddRequestDto;
import com.zoeeasy.cloud.pms.floor.validator.FloorAddRequestDtoValidator;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingLotConstant;
import com.zoeeasy.cloud.pms.parkingarea.cst.ParkingAreaConstant;
import com.zoeeasy.cloud.pms.parkingarea.dto.request.ParkingAreaAddRequestDto;
import com.zoeeasy.cloud.pms.parkingarea.validator.ParkingAreaAddRequestDtoValidator;
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
public class FloorAddRequestDtoValidatorImpl extends ValidatorHandler<FloorAddRequestDto> implements FloorAddRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Autowired
    private ParkingAreaCrudService parkingAreaCrudService;

    @Autowired
    private ParkingFloorCrudService parkingFloorCrudService;

    @Override
    public boolean validate(ValidatorContext context, FloorAddRequestDto requestDto) {
        if (requestDto.getLotFixed() > requestDto.getLotCount()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTFIXED_NOT_GT_PARKING_LOTCOUNT);
        }
        if (requestDto.getLotAvailable() > requestDto.getLotCount()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTAVAILABLE_NOT_GT_PARKING_LOTCOUNT);
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
        EntityWrapper<ParkingFloorEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("floorCode", requestDto.getFloorCode());
        ParkingFloorEntity parkingFloorEntity = parkingFloorCrudService.selectOne(entityWrapper);
        if (parkingFloorEntity != null){
            throw new ValidationException(ParkingAreaConstant.FLOOR_CODE_EXIST);
        }
        Integer lotCountExist = parkingFloorCrudService.findLotCountByParkingId(requestDto.getParkingId());
        Integer lotTotal = requestDto.getLotCount();
        if (lotCountExist != null) {
            lotTotal = lotTotal + lotCountExist;
        }
        if (lotTotal > parkingInfoExist.getLotTotal()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTCOUNT_NOT_GT_PARKING_LOTCOUNT);
        }
        Integer lotFixedTotalExist = parkingFloorCrudService.findLotFixedTotalByParkingId(requestDto.getParkingId());
        Integer lotFixedTotal = requestDto.getLotFixed();
        if (lotFixedTotalExist != null) {
            lotFixedTotal = lotFixedTotal + lotFixedTotalExist;
        }
        if (lotFixedTotal > parkingInfoExist.getLotFixed()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTFIXED_NOT_GT_PARKING_LOTFIXED);
        }

        return true;
    }
}
