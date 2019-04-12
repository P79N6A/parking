package com.zoeeasy.cloud.integration.validator.inspect;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.PlateNumberUtil;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.charge.cts.ChargeConstant;
import com.zoeeasy.cloud.core.enums.CarTypeEnum;
import com.zoeeasy.cloud.inspect.cts.InspectConstant;
import com.zoeeasy.cloud.integration.inspect.dto.request.InspectIntoRecordRequestDto;
import com.zoeeasy.cloud.integration.inspect.validator.InspectIntoRecordRequestDtoValidator;
import com.zoeeasy.cloud.pms.enums.PlateColorEnum;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import org.springframework.stereotype.Component;

/**
 * @author AkeemSuper
 * @date 2018/10/19 0019
 */
@Component
public class InspectIntoRecordRequestDtoValidatorImpl extends ValidatorHandler<InspectIntoRecordRequestDto> implements InspectIntoRecordRequestDtoValidator {

    @Override
    public boolean validate(ValidatorContext context, InspectIntoRecordRequestDto requestDto) {
        if (null != requestDto.getPlateNumber()) {
            boolean plateNumber = PlateNumberUtil.isPlateNumber(requestDto.getPlateNumber());
            if (!plateNumber) {
                throw new ValidationException(ParkingConstant.PLATENUMBER_ILLEGAL);
            }
        }
        if (null != requestDto.getCarType()) {
            CarTypeEnum carTypeEnum = CarTypeEnum.parse(requestDto.getCarType());
            if (carTypeEnum == null) {
                throw new ValidationException(ChargeConstant.CHARGE_CAR_TYPE_NOT_STANDARD);
            }
        }
        if (null != requestDto.getPlateColor()) {
            PlateColorEnum plateColorEnum = PlateColorEnum.parse(requestDto.getPlateColor());
            if (plateColorEnum == null) {
                throw new ValidationException(ChargeConstant.PLATE_NUMBER_TYPE_NOT_STANDARD);
            }

        }
        if (requestDto.getStartTime().after(DateUtils.now())) {
            throw new ValidationException(InspectConstant.DATE_TIME_NOT_BEFORE_NOW);
        }
        if (requestDto.getTurnOut()) {
            if (null == requestDto.getEndTime()) {
                throw new ValidationException(InspectConstant.OUT_PASS_TIME_NOT_EMPTY);
            }
            if (requestDto.getEndTime().after(DateUtils.now())) {
                throw new ValidationException(InspectConstant.DATE_TIME_NOT_BEFORE_NOW);
            }
        }

        return true;
    }
}
