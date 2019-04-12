package com.zoeeasy.cloud.pms.validator.parkingarea;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingAreaEntity;
import com.zoeeasy.cloud.pms.domain.ParkingGarageInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import com.zoeeasy.cloud.pms.lane.cst.ParkingLaneConstant;
import com.zoeeasy.cloud.pms.parkingarea.cst.ParkingAreaConstant;
import com.zoeeasy.cloud.pms.parkingarea.dto.request.UpdateParkingAreaRequestDto;
import com.zoeeasy.cloud.pms.parkingarea.validator.UpdateParkingAreaRequestDtoValidator;
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
public class UpdateParkingAreaRequestDtoValidatorImpl extends ValidatorHandler<UpdateParkingAreaRequestDto> implements UpdateParkingAreaRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingAreaCrudService parkingAreaCrudService;

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Override
    public boolean validate(ValidatorContext context, UpdateParkingAreaRequestDto requestDto) {
        //租户
        if (requestDto.getTenantId() == null) {
            throw new ValidationException(GarageConstant.TENANTE_ID_IS_NULL);
        }
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
        ParkingInfoEntity parkingInfoExist = parkingInfoCrudService.findByCodeAndTenantId(requestDto.getCloudParkingCode(), requestDto.getTenantId());
        if (parkingInfoExist == null) {
            throw new ValidationException(ParkingLaneConstant.PARKING_NOT_EXIT);
        }
        //泊位区域是否存在
        ParkingAreaEntity parkingAreaExist = parkingAreaCrudService.selectByCodeAndParkingId(requestDto.getPlatAreaCode(), parkingInfoExist.getId());
        if (parkingAreaExist == null) {
            throw new ValidationException(ParkingAreaConstant.PARKING_AREA_NOT_EXIST);
        }
        //区域名称唯一
        ParkingAreaEntity parkingAreaEntity = parkingAreaCrudService.selectByNameAndParkingId(requestDto.getParkingAreaName(), parkingAreaExist.getParkingId());
        if (parkingAreaEntity != null && !parkingAreaEntity.getCode().equals(requestDto.getPlatAreaCode())) {
            throw new ValidationException(ParkingAreaConstant.PARKING_AREA_NAME_USED);
        }
        //车库
        if (StringUtils.isNotEmpty(requestDto.getCloudGarageCode())) {
            ParkingGarageInfoEntity parkingGarageInfoEntity = parkingGarageInfoCrudService.findGarageByParkingIdAndCode(parkingInfoExist.getId(), requestDto.getCloudGarageCode());
            if (parkingGarageInfoEntity == null) {
                throw new ValidationException(ParkingLaneConstant.PARKING_GARAGE_NOT_EXIT);
            }
        }

        Integer lotTotalExist = parkingAreaCrudService.findLotTotalByParkingIdNonTenant(parkingAreaExist.getParkingId());
        Integer lotTotal = requestDto.getLotTotal();
        if (lotTotalExist != null) {
            lotTotal = lotTotal + lotTotalExist - parkingAreaExist.getLotTotal();
        }
        if (lotTotal > parkingInfoExist.getLotTotal()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTCOUNT_NOT_GT_PARKING_LOTCOUNT);
        }
        Integer lotFixedTotalExist = parkingAreaCrudService.findLotFixedTotalByParkingIdNonTenant(parkingAreaExist.getParkingId());
        Integer lotFixedTotal = requestDto.getLotFixed();
        if (lotFixedTotalExist != null) {
            lotFixedTotal = lotFixedTotal + lotFixedTotalExist - parkingAreaExist.getLotFixed();
        }
        if (lotFixedTotal > parkingInfoExist.getLotFixed()) {
            throw new ValidationException(GarageConstant.PARKING_GARAGE_GARAGE_LOTFIXED_NOT_GT_PARKING_LOTFIXED);
        }

        return true;
    }
}
