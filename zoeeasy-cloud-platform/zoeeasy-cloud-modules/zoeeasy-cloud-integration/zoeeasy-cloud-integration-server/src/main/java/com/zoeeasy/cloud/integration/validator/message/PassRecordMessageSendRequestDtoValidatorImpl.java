package com.zoeeasy.cloud.integration.validator.message;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.integration.message.dto.request.PassRecordMessageSendRequestDto;
import com.zoeeasy.cloud.integration.message.validator.PassRecordMessageSendRequestDtoValidator;
import com.zoeeasy.cloud.pms.platform.PlatformParkingInfoService;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingInfoGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingInfoResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author AkeemSuper
 * @date 2018/10/17 0017
 */
@Component
public class PassRecordMessageSendRequestDtoValidatorImpl extends ValidatorHandler<PassRecordMessageSendRequestDto> implements PassRecordMessageSendRequestDtoValidator {

    @Autowired
    private PlatformParkingInfoService platformParkingInfoService;

    @Override
    public boolean validate(ValidatorContext context, PassRecordMessageSendRequestDto requestDto) {
        ParkingInfoGetRequestDto parkingInfoGetRequestDto = new ParkingInfoGetRequestDto();
        parkingInfoGetRequestDto.setParkingId(requestDto.getParkingId());
        ObjectResultDto<ParkingInfoResultDto> parkingInfoServiceParkInfoById = platformParkingInfoService.getParkInfoById(parkingInfoGetRequestDto);
        if (parkingInfoServiceParkInfoById.isFailed() || parkingInfoServiceParkInfoById.getData() == null) {
            throw new ValidationException(ParkingConstant.PARKING_INFO_PARKING_ID_NOT_NULL);
        }
        return true;
    }
}
