package com.zoeeasy.cloud.pms.validator.garage;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingGarageInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import com.zoeeasy.cloud.pms.garage.dto.request.GarageAddRequestDto;
import com.zoeeasy.cloud.pms.garage.validator.GarageAddRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingGarageInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 车库添加校验
 * Created by song on 2018/9/28.
 */
@Component
public class GarageAddRequestDtoValidatorImpl extends ValidatorHandler<GarageAddRequestDto> implements GarageAddRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Override
    public boolean validate(ValidatorContext context, GarageAddRequestDto requestDto) {
        //车位数量校验
        if (requestDto.getLotFixed() > requestDto.getLotCount()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTFIXED_NOT_GT_PARKING_LOTCOUNT);
        }
        if (requestDto.getLotAvailable() > requestDto.getLotCount()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTAVAILABLE_NOT_GT_PARKING_LOTCOUNT);
        }
        //停车场是否存在
        ParkingInfoEntity parkingInfoExist = parkingInfoCrudService.selectById(requestDto.getParkingId());
        if (parkingInfoExist == null) {
            throw new ValidationException(GarageConstant.PARKING_NOT_FOUND);
        }
        //code是否被占用
        ParkingGarageInfoEntity parkingGarageInfoEntity = parkingGarageInfoCrudService.findByCode(requestDto.getCode());
        if (parkingGarageInfoEntity != null) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_CODE_USED);
        }
        //判断车库名称唯一性
        ParkingGarageInfoEntity parkingGarageInfoExist = parkingGarageInfoCrudService.findGarageByNameAndParkingId(requestDto.getName(), requestDto.getParkingId());
        if (parkingGarageInfoExist != null){
            throw new ValidationException(GarageConstant.PARKING_GARAGE_NAME_USED);
        }

        Integer lotTotalExist = parkingGarageInfoCrudService.findGarageLotTotalByParkingId(requestDto.getParkingId());
        Integer lotTotal = requestDto.getLotCount();
        if (lotTotalExist != null) {
            lotTotal = lotTotal + lotTotalExist;
        }
        if (lotTotal > parkingInfoExist.getLotTotal()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTCOUNT_NOT_GT_PARKING_LOTCOUNT);
        }
        Integer lotFixedTotalExist = parkingGarageInfoCrudService.findGarageLotFixedTotalByParkingId(requestDto.getParkingId());
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
