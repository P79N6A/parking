package com.zoeeasy.cloud.pms.validator.garage;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingGarageInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import com.zoeeasy.cloud.pms.garage.dto.request.UpdateGarageInfoRequestDto;
import com.zoeeasy.cloud.pms.garage.validator.UpdateGarageInfoRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingGarageInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Date: 2018/11/30
 * @author: lhj
 */
@Component
public class UpdateGarageInfoRequestDtoValidatorImpl extends ValidatorHandler<UpdateGarageInfoRequestDto> implements UpdateGarageInfoRequestDtoValidator {

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Override
    public boolean validate(ValidatorContext context, UpdateGarageInfoRequestDto requestDto) {
        //租户
        if (requestDto.getTenantId() == null) {
            throw new ValidationException(GarageConstant.TENANTE_ID_IS_NULL);
        }
        //停车场是否存在
        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectByCode(requestDto.getCloudParkingCode());
        if (parkingInfoEntity == null) {
            throw new ValidationException(GarageConstant.PARKING_NOT_FOUND);
        }
        ParkingGarageInfoEntity parkingGarageInfoEntity = parkingGarageInfoCrudService.findGarageByParkingIdAndCode(parkingInfoEntity.getId(), requestDto.getCloudGarageCode());
        if (parkingGarageInfoEntity == null) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_NOT_FOUND);
        }
        //车库名称是否合格
        if (StringUtils.isSpecialSymbols(requestDto.getGarageName())) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_NAME_ILLEGAL);
        }
        //车库名称是否占用（停车场唯一）
        ParkingGarageInfoEntity parkingGarageInfoExist = parkingGarageInfoCrudService.selectGarageByNameAndParkingId(requestDto.getGarageName(), parkingInfoEntity.getId());
        if (parkingGarageInfoExist != null && !parkingGarageInfoExist.getCode().equals(requestDto.getCloudGarageCode())) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_NAME_USED);
        }
        Integer lotTotalExist = parkingGarageInfoCrudService.selectGarageLotTotalByParkingId(parkingInfoEntity.getId());
        Integer lotTotal = requestDto.getLotTotal();
        if (lotTotalExist != null && parkingGarageInfoEntity != null) {
            lotTotal = lotTotal + lotTotalExist - parkingGarageInfoEntity.getLotCount();
        }
        if (lotTotal > parkingInfoEntity.getLotTotal()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTCOUNT_NOT_GT_PARKING_LOTCOUNT);
        }
        Integer lotFixedTotalExist = parkingGarageInfoCrudService.selectGarageLotFixedTotalByParkingId(parkingInfoEntity.getId());
        Integer lotFixedTotal = requestDto.getLotFixed();
        if (lotFixedTotalExist != null && parkingGarageInfoEntity != null) {
            lotFixedTotal = lotFixedTotal + lotFixedTotalExist - parkingGarageInfoEntity.getLotFixed();
        }
        if (lotFixedTotal > parkingInfoEntity.getLotFixed()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTFIXED_NOT_GT_PARKING_LOTFIXED);
        }
        if (requestDto.getLotFixed() > requestDto.getLotTotal()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTFIXED_NOT_GT_PARKING_LOTCOUNT);
        }
        if (requestDto.getLotAvailable() > requestDto.getLotTotal()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTAVAILABLE_NOT_GT_PARKING_LOTCOUNT);
        }
        return true;
    }
}
