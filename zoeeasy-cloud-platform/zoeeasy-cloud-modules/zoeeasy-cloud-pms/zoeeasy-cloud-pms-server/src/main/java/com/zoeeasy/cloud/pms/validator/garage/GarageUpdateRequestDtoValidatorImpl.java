package com.zoeeasy.cloud.pms.validator.garage;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingGarageInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import com.zoeeasy.cloud.pms.garage.dto.request.GarageUpdateRequestDto;
import com.zoeeasy.cloud.pms.garage.validator.GarageUpdateRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingGarageInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by song on 2018/9/28.
 */
@Component
public class GarageUpdateRequestDtoValidatorImpl extends ValidatorHandler<GarageUpdateRequestDto> implements GarageUpdateRequestDtoValidator {

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Override
    public boolean validate(ValidatorContext context, GarageUpdateRequestDto requestDto) {
        if (requestDto.getId() == null){
            throw new ValidationException(GarageConstant.PARKING_GARAGE_ID_NOT_NULL);
        }
        if (requestDto.getLotFixed() > requestDto.getLotCount()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTFIXED_NOT_GT_PARKING_LOTCOUNT);
        }
        if (requestDto.getLotAvailable() > requestDto.getLotCount()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTAVAILABLE_NOT_GT_PARKING_LOTCOUNT);
        }
        ParkingGarageInfoEntity parkingGarageExist = parkingGarageInfoCrudService.selectById(requestDto.getId());
        if (parkingGarageExist == null) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_NOT_FOUND);
        }
        ParkingGarageInfoEntity parkingGarageInfoEntity = parkingGarageInfoCrudService.findGarageByNameAndParkingId(requestDto.getName(), parkingGarageExist.getParkingId());
        if (parkingGarageInfoEntity != null && !parkingGarageInfoEntity.getId().equals(requestDto.getId())) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_NAME_USED);
        }
        ParkingInfoEntity parkingInfoExist = parkingInfoCrudService.selectById(parkingGarageExist.getParkingId());
        if (parkingInfoExist != null){
            Integer lotTotalExist = parkingGarageInfoCrudService.findGarageLotTotalByParkingId(parkingGarageExist.getParkingId());
            Integer lotTotal = requestDto.getLotCount();
            if (lotTotalExist != null) {
                lotTotal = lotTotal + lotTotalExist - parkingGarageExist.getLotCount();
            }
            if (lotTotal > parkingInfoExist.getLotTotal()) {
                throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTCOUNT_NOT_GT_PARKING_LOTCOUNT);
            }
            Integer lotFixedTotalExist = parkingGarageInfoCrudService.findGarageLotFixedTotalByParkingId(parkingGarageExist.getParkingId());
            Integer lotFixedTotal = requestDto.getLotFixed();
            if (lotFixedTotalExist != null) {
                lotFixedTotal = lotFixedTotal + lotFixedTotalExist - parkingGarageExist.getLotFixed();
            }
            if (lotFixedTotal > parkingInfoExist.getLotFixed()) {
                throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTFIXED_NOT_GT_PARKING_LOTFIXED);
            }
        }

        return true;
    }
}
