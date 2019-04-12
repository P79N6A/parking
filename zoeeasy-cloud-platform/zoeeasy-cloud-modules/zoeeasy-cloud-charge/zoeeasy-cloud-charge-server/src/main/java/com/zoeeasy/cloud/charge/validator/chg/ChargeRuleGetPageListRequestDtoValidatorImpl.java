package com.zoeeasy.cloud.charge.validator.chg;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.charge.chg.dto.request.ChargeRuleGetPageListRequestDto;
import com.zoeeasy.cloud.charge.chg.validator.ChargeRuleGetPageListRequestDtoValidator;
import com.zoeeasy.cloud.charge.cts.ChargeConstant;
import com.zoeeasy.cloud.charge.enums.ChargeRuleTypeEnum;
import com.zoeeasy.cloud.core.enums.CarTypeEnum;
import com.zoeeasy.cloud.core.enums.ParkingLevelEnum;
import org.springframework.stereotype.Component;

/**
 * @author AkeemSuper
 * @date 2018/10/29 0029
 */
@Component
public class ChargeRuleGetPageListRequestDtoValidatorImpl extends ValidatorHandler<ChargeRuleGetPageListRequestDto> implements ChargeRuleGetPageListRequestDtoValidator {

    @Override
    public boolean validate(ValidatorContext context, ChargeRuleGetPageListRequestDto requestDto) {
        if (null != requestDto.getRuleType()) {
            ChargeRuleTypeEnum ruleTypeEnum = ChargeRuleTypeEnum.parse(requestDto.getRuleType());
            if (null == ruleTypeEnum) {
                throw new ValidationException(ChargeConstant.CHARGE_RULE_TYPE_NOT_STANDARD);
            }
        }
        if (null != requestDto.getCarType()) {
            CarTypeEnum carTypeEnum = CarTypeEnum.parse(requestDto.getCarType());
            if (null == carTypeEnum) {
                throw new ValidationException(ChargeConstant.CHARGE_CAR_TYPE_NOT_STANDARD);
            }
        }
        if (null != requestDto.getParkingLevel()) {
            ParkingLevelEnum parkingLevelEnum = ParkingLevelEnum.parse(requestDto.getParkingLevel());
            if (null == parkingLevelEnum) {
                throw new ValidationException(ChargeConstant.CHARGE_PARKING_LEVEL_NOT_STANDARD);
            }
        }
        if (StringUtils.isSpecialSymbols(requestDto.getRuleName())) {
            throw new ValidationException(ChargeConstant.CHARGE_NAME_NOT_STANDARD);
        }
        return true;
    }
}
