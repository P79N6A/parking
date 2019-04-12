package com.zoeeasy.cloud.pms.validator.parkinglot;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.*;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingLotConstant;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingLotAddRequestDto;
import com.zoeeasy.cloud.pms.park.validator.ParkingLotAddRequestDtoValidator;
import com.zoeeasy.cloud.pms.parkingarea.cst.ParkingAreaConstant;
import com.zoeeasy.cloud.pms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 泊位添加参数校验
 * Created by song on 2018/9/12.
 */
@Component
public class ParkingLotAddDtoValidatorImpl extends ValidatorHandler<ParkingLotAddRequestDto> implements ParkingLotAddRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Autowired
    private ParkingAreaCrudService parkingAreaCrudService;

    @Autowired
    private ParkingLotInfoCrudService parkingLotInfoCrudService;

    @Autowired
    private ParkingFloorCrudService parkingFloorCrudService;

    @Override
    public boolean validate(ValidatorContext context, ParkingLotAddRequestDto requestDto) {

        ParkingLotInfoEntity parkingLotExist = parkingLotInfoCrudService.findByCode(requestDto.getCode());
        if (parkingLotExist != null) {
            throw new ValidationException(ParkingLotConstant.PARKING_LOT_CODE_USED);
        }
        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(requestDto.getParkingId());
        if (parkingInfoEntity == null) {
            throw new ValidationException(ParkingConstant.PARKING_NOT_FOUND);
        }
        if (requestDto.getParkingAreaId() != null){
            ParkingAreaEntity parkingAreaEntity = parkingAreaCrudService.selectById(requestDto.getParkingAreaId());
            if (parkingAreaEntity == null){
                throw new ValidationException(ParkingAreaConstant.PARKING_AREA_NOT_EXIST);
            }
        }
        if (requestDto.getGarageId() != null){
            ParkingGarageInfoEntity parkingGarageInfoEntity = parkingGarageInfoCrudService.selectById(requestDto.getGarageId());
            if (parkingGarageInfoEntity == null){
                throw new ValidationException(GarageConstant.PARKING_GARAGE_NOT_FOUND);
            }
            if (!parkingGarageInfoEntity.getParkingId().equals(parkingInfoEntity.getId())) {
                throw new ValidationException(ParkingLotConstant.PARKING_NOT_FOUND_GARAGE);
            }
            if (requestDto.getParkingAreaId() != null) {
                ParkingAreaEntity parkingAreaEntity = parkingAreaCrudService.selectById(requestDto.getParkingAreaId());
                if (parkingAreaEntity != null) {
                    if (!parkingAreaEntity.getParkingId().equals(parkingInfoEntity.getId())) {
                        throw new ValidationException(ParkingLotConstant.PARKING_NOT_FOUND_PARKINGAREA);
                    }
                    if (!parkingAreaEntity.getGarageId().equals(parkingGarageInfoEntity.getId())) {
                        throw new ValidationException(ParkingLotConstant.GARAGE_NOT_FOUND_PARKINGAREA);
                    }
                }
            }
        }
        if (requestDto.getParkingAreaId() != null){
            ParkingLotInfoEntity parkingLotInfoEntity = parkingLotInfoCrudService.findByNumberAndParkingAreaId(requestDto.getNumber(), requestDto.getParkingAreaId());
            if (parkingLotInfoEntity != null){
                throw new ValidationException(ParkingLotConstant.PARKING_LOT_NUMBER_USED);
            }
        } else if (requestDto.getGarageId() != null){
            ParkingLotInfoEntity parkingLotInfoEntity = parkingLotInfoCrudService.findByNumberAndGarageId(requestDto.getNumber(), requestDto.getGarageId());
            if (parkingLotInfoEntity != null){
                throw new ValidationException(ParkingLotConstant.PARKING_LOT_NUMBER_USED);
            }
        } else {
            ParkingLotInfoEntity parkingLotInfoEntity = parkingLotInfoCrudService.findByNumberAndParkingId(requestDto.getNumber(), requestDto.getParkingId());
            if (parkingLotInfoEntity != null){
                throw new ValidationException(ParkingLotConstant.PARKING_LOT_NUMBER_USED);
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
