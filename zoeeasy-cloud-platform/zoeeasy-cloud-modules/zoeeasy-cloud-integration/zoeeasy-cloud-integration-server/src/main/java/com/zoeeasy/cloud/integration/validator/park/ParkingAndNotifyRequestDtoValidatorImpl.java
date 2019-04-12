package com.zoeeasy.cloud.integration.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.inspect.cts.InspectConstant;
import com.zoeeasy.cloud.integration.platform.dto.request.ParkingAndNotifyRequestDto;
import com.zoeeasy.cloud.integration.platform.validator.ParkingAndNotifyRequestDtoValidator;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Date: 2018/11/20
 * @author: lhj
 */
@Component
public class ParkingAndNotifyRequestDtoValidatorImpl extends ValidatorHandler<ParkingAndNotifyRequestDto> implements ParkingAndNotifyRequestDtoValidator {

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Override
    public boolean validate(ValidatorContext context, ParkingAndNotifyRequestDto requestDto) {
        ParkingGetRequestDto parkingInfoGetRequestDto = new ParkingGetRequestDto();
        parkingInfoGetRequestDto.setId(requestDto.getParkingId());
        ObjectResultDto<ParkingResultDto> parkingInfo = parkingInfoService.getParkingInfo(parkingInfoGetRequestDto);
        if (parkingInfo.isFailed() || parkingInfo.getData() == null) {
            throw new ValidationException(InspectConstant.PARKING_ID_NONENTITY);
        }
        return true;
    }
}