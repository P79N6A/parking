package com.zoeeasy.cloud.pms.validator.parkingarea;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingAreaEntity;
import com.zoeeasy.cloud.pms.domain.ParkingFloorEntity;
import com.zoeeasy.cloud.pms.domain.ParkingGarageInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
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
public class ParkingAreaAddRequestDtoValidatorImpl extends ValidatorHandler<ParkingAreaAddRequestDto> implements ParkingAreaAddRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Autowired
    private ParkingAreaCrudService parkingAreaCrudService;

    @Autowired
    private ParkingFloorCrudService parkingFloorCrudService;

    @Override
    public boolean validate(ValidatorContext context, ParkingAreaAddRequestDto requestDto) {
        if (requestDto.getLotFixed() > requestDto.getLotTotal()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTFIXED_NOT_GT_PARKING_LOTCOUNT);
        }
        if (requestDto.getLotAvailable() > requestDto.getLotTotal()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTAVAILABLE_NOT_GT_PARKING_LOTCOUNT);
        }
        ParkingInfoEntity parkingInfoExist = parkingInfoCrudService.selectById(requestDto.getParkingId());
        if (parkingInfoExist == null) {
            throw new ValidationException(ParkingConstant.PARKING_NOT_FOUND);
        }
        if (requestDto.getGarageId() != null){
            ParkingGarageInfoEntity parkingGarageExist = parkingGarageInfoCrudService.selectById(requestDto.getGarageId());
            if (parkingGarageExist == null) {
                throw new ValidationException(GarageConstant.PARKING_GARAGE_NOT_FOUND);
            }
            if (!parkingInfoExist.getId().equals(parkingGarageExist.getParkingId())) {
                throw new ValidationException(ParkingAreaConstant.PARKING_NOT_FOUND_GARAGE);
            }
            ParkingAreaEntity parkingAreaEntity = parkingAreaCrudService.findByNameAndGarageId(requestDto.getName(), requestDto.getGarageId());
            if (parkingAreaEntity != null){
                throw new ValidationException(ParkingAreaConstant.PARKING_AREA_NAME_USED);
            }
        } else {
            ParkingAreaEntity parkingAreaEntity = parkingAreaCrudService.findByNameAndParkingId(requestDto.getName(), requestDto.getParkingId());
            if (parkingAreaEntity != null){
                throw new ValidationException(ParkingAreaConstant.PARKING_AREA_NAME_USED);
            }
        }
        ParkingAreaEntity parkingAreaEntity = parkingAreaCrudService.findByCode(requestDto.getCode());
        if (parkingAreaEntity != null) {
            throw new ValidationException(ParkingAreaConstant.PARKING_AREA_CODE_USED);
        }
        Integer lotTotalExist = parkingAreaCrudService.findLotTotalByParkingId(requestDto.getParkingId());
        Integer lotTotal = requestDto.getLotTotal();
        if (lotTotalExist != null) {
            lotTotal = lotTotal + lotTotalExist;
        }
        if (lotTotal > parkingInfoExist.getLotTotal()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTCOUNT_NOT_GT_PARKING_LOTCOUNT);
        }
        Integer lotFixedTotalExist = parkingAreaCrudService.findLotFixedTotalByParkingId(requestDto.getParkingId());
        Integer lotFixedTotal = requestDto.getLotFixed();
        if (lotFixedTotalExist != null) {
            lotFixedTotal = lotFixedTotal + lotFixedTotalExist;
        }
        if (lotFixedTotal > parkingInfoExist.getLotFixed()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTFIXED_NOT_GT_PARKING_LOTFIXED);
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
