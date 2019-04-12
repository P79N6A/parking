package com.zoeeasy.cloud.integration.validator.open;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.charge.cts.ChargeConstant;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.core.enums.CarTypeEnum;
import com.zoeeasy.cloud.core.enums.LicensePlateColorEnum;
import com.zoeeasy.cloud.core.enums.PassingDirectionEnum;
import com.zoeeasy.cloud.core.enums.PassingTypeEnum;
import com.zoeeasy.cloud.integration.cts.IntegrationConstant;
import com.zoeeasy.cloud.integration.open.dto.request.CloudAddPassRecordRequestDto;
import com.zoeeasy.cloud.integration.open.validator.CloudAddPassRecordRequestValidator;
import com.zoeeasy.cloud.pms.enums.SpecialTypeEnum;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/12/12 0012
 */
@Component
public class CloudAddPassRecordRequestValidatorImpl extends ValidatorHandler<CloudAddPassRecordRequestDto> implements CloudAddPassRecordRequestValidator {

    @Override
    public boolean accept(ValidatorContext context, CloudAddPassRecordRequestDto requestDto) {
        Date passTime = DateTimeUtils.parseDate(requestDto.getPassTime(), Const.FORMAT_DATETIME);
        if (null == passTime) {
            throw new ValidationException(IntegrationConstant.PASS_TIME_NOT_STANDARD);
        }
        PassingTypeEnum passingTypeEnum = PassingTypeEnum.parse(requestDto.getDirection());
        if (null == passingTypeEnum) {
            throw new ValidationException(IntegrationConstant.PASS_DIRECTION_NOT_STANDARD);
        }
        LicensePlateColorEnum licensePlateColorEnum = LicensePlateColorEnum.parse(requestDto.getPlateColor());
        if (null == licensePlateColorEnum) {
            throw new ValidationException(IntegrationConstant.PLATE_COLOR_NOT_STANDARD);
        }
        if (null == requestDto.getCarType()) {
            requestDto.setCarType(CarTypeEnum.OTHER_CAR.getValue());
        }
        CarTypeEnum carTypeEnum = CarTypeEnum.parse(requestDto.getCarType());
        if (null == carTypeEnum) {
            throw new ValidationException(ChargeConstant.CHARGE_CAR_TYPE_NOT_STANDARD);
        }
        SpecialTypeEnum specialTypeEnum = SpecialTypeEnum.parse(requestDto.getCarParkType());
        if (null == specialTypeEnum) {
            throw new ValidationException(IntegrationConstant.SPECIAL_TYPE_NOT_STANDARD);
        }
        return true;
    }
}
