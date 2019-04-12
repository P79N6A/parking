package com.zoeeasy.cloud.pms.validator.platform;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingLotInfoEntity;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingLotDecreaseRequestDto;
import com.zoeeasy.cloud.pms.platform.validator.ParkingLotDecreaseValidator;
import com.zoeeasy.cloud.pms.service.ParkingCurrentInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingLotInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author walkman
 * @since 2018/11/14
 */
@Component
public class ParkingLotDecreaseValidatorImpl extends ValidatorHandler<ParkingLotDecreaseRequestDto> implements ParkingLotDecreaseValidator {

    @Autowired
    private ParkingCurrentInfoCrudService parkingCurrentInfoCrudService;

    @Autowired
    private ParkingLotInfoCrudService parkingLotInfoCrudService;

    @Override
    public boolean validate(ValidatorContext context, ParkingLotDecreaseRequestDto requestDto) {

        if (requestDto.getParkingLotId() != null) {
            ParkingLotInfoEntity parkingLotInfoEntity = parkingLotInfoCrudService.findByParkingId(requestDto.getParkingId(),
                    requestDto.getParkingLotId(), requestDto.getTenantId());
            if (parkingLotInfoEntity == null) {
                throw new ValidationException(ParkingConstant.PARKING_LOT_INFO_NOT_FOUND);
            }
        }
        return super.validate(context, requestDto);
    }
}