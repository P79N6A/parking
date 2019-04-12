package com.zoeeasy.cloud.pms.validator.garage;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingGarageInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import com.zoeeasy.cloud.pms.garage.dto.request.AddGarageInfoRequestDto;
import com.zoeeasy.cloud.pms.garage.validator.AddGarageInfoRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingGarageInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Date: 2018/11/30
 * @author: lhj
 */
@Component
public class AddGarageInfoRequestDtoValidatorImpl extends ValidatorHandler<AddGarageInfoRequestDto> implements AddGarageInfoRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Override
    public boolean validate(ValidatorContext context, AddGarageInfoRequestDto requestDto) {
        //租户
        if (requestDto.getTenantId() == null) {
            throw new ValidationException(GarageConstant.TENANTE_ID_IS_NULL);
        }
        //停车场是否存在
        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.findByCodeAndTenantId(requestDto.getCloudParkingCode(), requestDto.getTenantId());
        if (parkingInfoEntity == null) {
            throw new ValidationException(GarageConstant.PARKING_NOT_FOUND);
        }
        //管理系统车库编号唯一
        ParkingGarageInfoEntity byCode = parkingGarageInfoCrudService.findByCode(requestDto.getGarageCode());
        if (byCode != null) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_CODE_USED);
        }
        //判断车库名称在停车场内唯一
        ParkingGarageInfoEntity parkingGarageInfoExist = parkingGarageInfoCrudService.selectGarageByNameAndParkingId(requestDto.getGarageName(), parkingInfoEntity.getId());
        if (parkingGarageInfoExist != null) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_NAME_USED);
        }
        //停车场内所有车库车位总数
        Integer lotTotalExist = parkingGarageInfoCrudService.selectGarageLotTotalByParkingId(parkingInfoEntity.getId());
        Integer lotTotal = requestDto.getLotTotal();
        if (lotTotalExist != null) {
            lotTotal = lotTotal + lotTotalExist;
        }
        if (lotTotal > parkingInfoEntity.getLotTotal()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTCOUNT_NOT_GT_PARKING_LOTCOUNT);
        }
        //判断固定车位数
        Integer lotFixedTotalExist = parkingGarageInfoCrudService.selectGarageLotFixedTotalByParkingId(parkingInfoEntity.getId());
        Integer lotFixedTotal = requestDto.getLotFixed();
        if (lotFixedTotalExist != null) {
            lotFixedTotal = lotFixedTotal + lotFixedTotalExist;
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
        //车库名称是否合格
        if (StringUtils.isSpecialSymbols(requestDto.getGarageName())) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_NAME_ILLEGAL);
        }
        return true;
    }
}
