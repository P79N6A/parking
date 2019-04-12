package com.zoeeasy.cloud.pms.validator.parkingarea;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingAreaEntity;
import com.zoeeasy.cloud.pms.domain.ParkingGarageInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.parkingarea.cst.ParkingAreaConstant;
import com.zoeeasy.cloud.pms.parkingarea.dto.request.AddParkingAreaRequestDto;
import com.zoeeasy.cloud.pms.parkingarea.validator.AddParkingAreaRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingAreaCrudService;
import com.zoeeasy.cloud.pms.service.ParkingGarageInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Date: 2018/12/1
 * @author: lhj
 */
@Component
public class AddParkingAreaRequestDtoValidatorImpl extends ValidatorHandler<AddParkingAreaRequestDto> implements AddParkingAreaRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Autowired
    private ParkingAreaCrudService parkingAreaCrudService;

    @Override
    public boolean validate(ValidatorContext context, AddParkingAreaRequestDto requestDto) {
        //租户
        if (requestDto.getTenantId() == null) {
            throw new ValidationException(GarageConstant.TENANTE_ID_IS_NULL);
        }
        //车位数量校验
        if (requestDto.getLotFixed() > requestDto.getLotTotal()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTFIXED_NOT_GT_PARKING_LOTCOUNT);
        }
        if (requestDto.getLotAvailable() > requestDto.getLotTotal()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTAVAILABLE_NOT_GT_PARKING_LOTCOUNT);
        }
        //姓名是否合法
        if (StringUtils.isSpecialSymbols(requestDto.getParkingAreaName())) {
            throw new ValidationException(ParkingAreaConstant.PARKING_AREA_NAME_ILLEGAL);
        }
        //停车场是否存在
        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.findByCodeAndTenantId(requestDto.getCloudParkingCode(), requestDto.getTenantId());
        if (parkingInfoEntity == null) {
            throw new ValidationException(ParkingConstant.PARKING_NOT_FOUND);
        }
        //车库是否存在
        if (StringUtils.isNotEmpty(requestDto.getCloudGarageCode())) {
            ParkingGarageInfoEntity garageInfoEntity = parkingGarageInfoCrudService.findGarageByParkingIdAndCode(parkingInfoEntity.getId(), requestDto.getCloudGarageCode());
            if (garageInfoEntity == null) {
                throw new ValidationException(GarageConstant.PARKING_GARAGE_NOT_FOUND);
            }
            //区域名称唯一
            ParkingAreaEntity parkingAreaEntity = parkingAreaCrudService.selectByNameAndGarageId(requestDto.getParkingAreaName(), garageInfoEntity.getId());
            if (parkingAreaEntity != null) {
                throw new ValidationException(ParkingAreaConstant.PARKING_AREA_NAME_USED);
            }
        } else {
            ParkingAreaEntity parkingAreaEntity = parkingAreaCrudService.selectByNameAndParkingId(requestDto.getParkingAreaName(), parkingInfoEntity.getId());
            if (parkingAreaEntity != null) {
                throw new ValidationException(ParkingAreaConstant.PARKING_AREA_NAME_USED);
            }
        }
        //编号唯一
        if (!StringUtils.isEmpty(requestDto.getParkingAreaCode())) {
            ParkingAreaEntity parkingAreaEntity = parkingAreaCrudService.selectByCode(requestDto.getParkingAreaCode());
            if (parkingAreaEntity != null) {
                throw new ValidationException(ParkingAreaConstant.PARKING_AREA_CODE_USED);
            }
        }
        Integer lotTotalExist = parkingAreaCrudService.findLotTotalByParkingIdNonTenant(parkingInfoEntity.getId());
        //车位总数
        Integer lotTotal = requestDto.getLotTotal();
        if (lotTotalExist != null) {
            lotTotal = lotTotal + lotTotalExist;
        }
        if (lotTotal > parkingInfoEntity.getLotTotal()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTCOUNT_NOT_GT_PARKING_LOTCOUNT);
        }
        Integer lotFixedTotalExist = parkingAreaCrudService.findLotFixedTotalByParkingIdNonTenant(parkingInfoEntity.getId());
        Integer lotFixedTotal = requestDto.getLotFixed();
        if (lotFixedTotalExist != null) {
            lotFixedTotal = lotFixedTotal + lotFixedTotalExist;
        }
        if (lotFixedTotal > parkingInfoEntity.getLotFixed()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTFIXED_NOT_GT_PARKING_LOTFIXED);
        }
        return true;
    }
}
