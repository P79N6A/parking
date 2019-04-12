package com.zoeeasy.cloud.pds.validator.magneticdetector;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pds.magneticdetector.cst.MagneticDetectorConstant;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.park.MagneticDetectorStatusUpdateRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.validator.MagneticDetectorStatusUpdateRequestDtoValidator;

/**
 * 修改地磁检测器状态
 *
 * @Date: 2018/10/18
 * @author: lhj
 */
public class MagneticDetectorStatusUpdateRequestDtoValidatorImpl extends ValidatorHandler<MagneticDetectorStatusUpdateRequestDto> implements MagneticDetectorStatusUpdateRequestDtoValidator {

    @Override
    public boolean validate(ValidatorContext context, MagneticDetectorStatusUpdateRequestDto requestDto) {
        if (null == requestDto.getId()) {
            throw new ValidationException(MagneticDetectorConstant.DETECTOR_ID_NOT_NULL);
        }
        return true;
    }
}
