package com.zoeeasy.cloud.integration.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.charge.cts.ChargeConstant;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingChargeRuleItemUpdateRequestDto;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingChargeRuleUpdateRequestDto;
import com.zoeeasy.cloud.integration.park.validator.ParkingChargeRuleUpdateRequestDtoValidator;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingInfoGetByIdRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingByIdResultDto;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/11/28 0028
 */
@Component
public class ParkingChargeRuleUpdateRequestDtoValidatorImpl extends ValidatorHandler<ParkingChargeRuleUpdateRequestDto> implements ParkingChargeRuleUpdateRequestDtoValidator {

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Override
    public boolean accept(ValidatorContext context, ParkingChargeRuleUpdateRequestDto requestDto) {
        ParkingInfoGetByIdRequestDto parkingInfoGetByIdRequestDto = new ParkingInfoGetByIdRequestDto();
        parkingInfoGetByIdRequestDto.setId(requestDto.getParkingId());
        ObjectResultDto<ParkingByIdResultDto> parkingById = parkingInfoService.getParkingById(parkingInfoGetByIdRequestDto);
        if (parkingById.isFailed() || parkingById.getData() == null) {
            throw new ValidationException(ParkingConstant.PARKING_NOT_FOUND);
        }
        if (CollectionUtils.isNotEmpty(requestDto.getRules())) {
            List<ParkingChargeRuleItemUpdateRequestDto> rules = requestDto.getRules();
            for (ParkingChargeRuleItemUpdateRequestDto rule : rules) {
                if (rule.getOnlineTime().compareTo(rule.getOfflineTime()) >= 0) {
                    throw new ValidationException(ChargeConstant.PARKING_CHARGE_RULE_TIME_NOT_STANDARD);
                }
            }
        }
        return true;
    }
}
