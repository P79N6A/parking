package com.zoeeasy.cloud.integration.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.charge.chg.dto.request.ChargeRuleGetListRequestDto;
import com.zoeeasy.cloud.inspect.cts.InspectConstant;
import com.zoeeasy.cloud.integration.park.validator.ChargeRuleGetListRequestDtoValidator;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author AkeemSuper
 * @date 2018/11/30 0030
 */
@Component
public class ChargeRuleGetListRequestDtoValidatorImpl extends ValidatorHandler<ChargeRuleGetListRequestDto> implements ChargeRuleGetListRequestDtoValidator {
    @Autowired
    private ParkingInfoService parkingInfoService;

    @Override
    public boolean accept(ValidatorContext context, ChargeRuleGetListRequestDto requestDto) {
        ParkingGetRequestDto parkingInfoGetRequestDto = new ParkingGetRequestDto();
        parkingInfoGetRequestDto.setId(requestDto.getParkingId());
        ObjectResultDto<ParkingResultDto> parkingInfo = parkingInfoService.getParkingInfo(parkingInfoGetRequestDto);
        if (parkingInfo.isFailed() || parkingInfo.getData() == null) {
            throw new ValidationException(InspectConstant.PARKING_ID_NONENTITY);
        }
        return true;
    }
}
