package com.zoeeasy.cloud.integration.validator.appoint;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.charge.appointrule.dto.request.ParkingAppointRuleUpdateRequestDto;
import com.zoeeasy.cloud.integration.appoint.validator.ParkingAppointRuleOperateRequestDtoValidator;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingInfoResultDto;
import com.zoeeasy.cloud.pms.platform.PlatformParkingInfoService;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingInfoGetRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zwq
 * @date 2018/10/15 0015
 */
@Component
public class ParkingAppointRuleOperateRequestDtoValidatarImpl extends ValidatorHandler<ParkingAppointRuleUpdateRequestDto> implements ParkingAppointRuleOperateRequestDtoValidator {

    @Autowired
    private PlatformParkingInfoService platformParkingInfoService;

    @Override
    public boolean validate(ValidatorContext context, ParkingAppointRuleUpdateRequestDto requestDto) {

        ParkingInfoGetRequestDto parkingInfoGetRequestDto = new ParkingInfoGetRequestDto();
        parkingInfoGetRequestDto.setParkingId(requestDto.getParkingId());
        ObjectResultDto<ParkingInfoResultDto> parkingInfoServiceParkInfoById = platformParkingInfoService.getParkInfoById(parkingInfoGetRequestDto);
        if (parkingInfoServiceParkInfoById.isFailed() || parkingInfoServiceParkInfoById.getData() == null) {
            throw new ValidationException(ParkingConstant.PARKING_INFO_PARKING_ID_NOT_NULL);
        }
        return true;
    }
}
