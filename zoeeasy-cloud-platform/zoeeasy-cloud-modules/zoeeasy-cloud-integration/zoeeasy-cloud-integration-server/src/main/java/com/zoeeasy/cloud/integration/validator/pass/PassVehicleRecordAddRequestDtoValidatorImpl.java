package com.zoeeasy.cloud.integration.validator.pass;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.PlateNumberUtil;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.inspect.cts.InspectConstant;
import com.zoeeasy.cloud.integration.pass.dto.request.PassVehicleRecordAddRequestDto;
import com.zoeeasy.cloud.integration.pass.validator.PassVehicleRecordAddRequestDtoValidator;
import com.zoeeasy.cloud.pms.enums.PmsResultEnum;
import org.springframework.stereotype.Component;

/**
 * @author AkeemSuper
 * @since 2018/10/16 0016
 */
@Component
public class PassVehicleRecordAddRequestDtoValidatorImpl extends ValidatorHandler<PassVehicleRecordAddRequestDto> implements PassVehicleRecordAddRequestDtoValidator {

    @Override
    public boolean validate(ValidatorContext context, PassVehicleRecordAddRequestDto requestDto) {

        String plateNumber = requestDto.getPlateNumber();
        //是否是有效车牌号
        Boolean validPlateNumber = PlateNumberUtil.isPlateNumber(plateNumber);
        if (!validPlateNumber) {
            //无效车牌
            throw new ValidationException(PmsResultEnum.LICENSE_INVALID.getComment());
        }
        if (requestDto.getPassTime().after(DateUtils.now())) {
            throw new ValidationException(InspectConstant.DATE_TIME_NOT_BEFORE_NOW);
        }
        return true;
    }
}
