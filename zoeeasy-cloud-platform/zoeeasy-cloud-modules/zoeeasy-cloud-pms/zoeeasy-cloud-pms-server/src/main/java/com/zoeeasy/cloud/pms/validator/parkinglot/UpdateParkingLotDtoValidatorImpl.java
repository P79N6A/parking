package com.zoeeasy.cloud.pms.validator.parkinglot;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingAreaEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingLotInfoEntity;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingLotConstant;
import com.zoeeasy.cloud.pms.park.dto.request.UpdateParkingLotRequestDto;
import com.zoeeasy.cloud.pms.park.validator.UpdateParkingLotRequestDtoValidator;
import com.zoeeasy.cloud.pms.parkingarea.cst.ParkingAreaConstant;
import com.zoeeasy.cloud.pms.service.ParkingAreaCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingLotInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 泊位添加参数校验
 * Created by song on 2018/9/12.
 */
@Component
public class UpdateParkingLotDtoValidatorImpl extends ValidatorHandler<UpdateParkingLotRequestDto> implements UpdateParkingLotRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingAreaCrudService parkingAreaCrudService;

    @Autowired
    private ParkingLotInfoCrudService parkingLotInfoCrudService;

    @Override
    public boolean validate(ValidatorContext context, UpdateParkingLotRequestDto requestDto) {
        //租户
        if (requestDto.getTenantId() == null) {
            throw new ValidationException(GarageConstant.TENANTE_ID_IS_NULL);
        }
        if (StringUtils.isSpecialSymbols(requestDto.getParkingLotName())) {
            throw new ValidationException(ParkingLotConstant.PARKING_LOT_NAME_ILLEGAL);
        }
        ParkingLotInfoEntity parkingLotInfoExist = parkingLotInfoCrudService.findByCode(requestDto.getPlatLotCode());
        if (parkingLotInfoExist == null) {
            throw new ValidationException(ParkingLotConstant.PARKING_LOT_NOT_FOUND);
        }
        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.findByCodeAndTenantId(requestDto.getCloudParkingCode(), requestDto.getTenantId());
        if (parkingInfoEntity == null) {
            throw new ValidationException(ParkingConstant.PARKING_NOT_FOUND);
        }
        ParkingLotInfoEntity parkingLotInfoEntity = parkingLotInfoCrudService.findByLocalCode(requestDto.getParkingLotCode());
        if (parkingLotInfoEntity == null) {
            throw new ValidationException(ParkingLotConstant.PARKING_LOT_NOT_FOUND);
        }
        if (!StringUtils.isEmpty(requestDto.getPlatAreaCode())) {
            ParkingAreaEntity parkingAreaEntity = parkingAreaCrudService.selectParkingAreaNonTenant(requestDto.getPlatAreaCode());
            if (parkingAreaEntity == null) {
                throw new ValidationException(ParkingAreaConstant.PARKING_AREA_NOT_EXIST);
            }
            if (!parkingAreaEntity.getParkingId().equals(parkingInfoEntity.getId())) {
                throw new ValidationException(ParkingLotConstant.PARKING_NOT_FOUND_PARKINGAREA);
            }
        }
        return true;
    }
}
