package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.dto.request.LotAvailableUpdateRequestDto;
import com.zoeeasy.cloud.pms.park.validator.LotAvailableUpdateRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @date: 2018/12/3.
 * @author：zm
 */
@Component
public class LotAvailableUpdateRequestDtoValidatorImpl extends ValidatorHandler<LotAvailableUpdateRequestDto> implements LotAvailableUpdateRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Override
    public boolean validate(ValidatorContext context, LotAvailableUpdateRequestDto requestDto) {
        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(requestDto.getParkingId());
        if (parkingInfoEntity == null) {
            throw new ValidationException(ParkingConstant.PARKING_NOT_FOUND);
        }
        if (requestDto.getLotAvailable() != null && requestDto.getLotAvailable() > parkingInfoEntity.getLotTotal()) {
            throw new ValidationException(ParkingConstant.PARKING_LOTAVAILABLE_NOT_GT_LOTTOTAL);
        }
        return true;
    }
}
