package com.zoeeasy.cloud.pms.validator.platform;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingCurrentInfoEntity;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingByParkingIdGetRequestDto;
import com.zoeeasy.cloud.pms.platform.validator.ParkingByParkingIdGetRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingCurrentInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Date: 2018/11/17
 * @author: lhj
 */
@Component
public class ParkingByParkingIdGetRequestDtoValidatorImpl extends ValidatorHandler<ParkingByParkingIdGetRequestDto> implements ParkingByParkingIdGetRequestDtoValidator {

    @Autowired
    private ParkingCurrentInfoCrudService parkingCurrentInfoCrudService;

    @Override
    public boolean validate(ValidatorContext context, ParkingByParkingIdGetRequestDto requestDto) {
        ParkingCurrentInfoEntity currentInfoEntity = parkingCurrentInfoCrudService.selectByParkingId(requestDto.getParkingId());
        if (currentInfoEntity == null) {
            throw new ValidationException(ParkingConstant.PARKING_NOT_FOUND);
        }
        return true;
    }
}
