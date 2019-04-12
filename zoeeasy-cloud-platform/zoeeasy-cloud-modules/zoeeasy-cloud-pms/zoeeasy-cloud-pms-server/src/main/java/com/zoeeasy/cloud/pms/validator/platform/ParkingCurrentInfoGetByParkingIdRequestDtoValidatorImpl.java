package com.zoeeasy.cloud.pms.validator.platform;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.enums.ParkingStatusEnum;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingCurrentInfoGetByParkingIdRequestDto;
import com.zoeeasy.cloud.pms.platform.validator.ParkingCurrentInfoGetByParkingIdRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author AkeemSuper
 * @date 2018/9/29 0029
 */
@Component
public class ParkingCurrentInfoGetByParkingIdRequestDtoValidatorImpl extends ValidatorHandler<ParkingCurrentInfoGetByParkingIdRequestDto> implements ParkingCurrentInfoGetByParkingIdRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Override
    public boolean validate(ValidatorContext context, ParkingCurrentInfoGetByParkingIdRequestDto requestDto) {
        EntityWrapper<ParkingInfoEntity> entity = new EntityWrapper<>();
        entity.eq("id", requestDto.getParkingId());
        entity.eq("status", ParkingStatusEnum.ON_LINE.getValue());
        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectPlatformParkingInfo(entity);
        if (parkingInfoEntity == null) {
            throw new ValidationException(ParkingConstant.PARKING_NOT_FOUND);
        }
        return true;
    }
}
