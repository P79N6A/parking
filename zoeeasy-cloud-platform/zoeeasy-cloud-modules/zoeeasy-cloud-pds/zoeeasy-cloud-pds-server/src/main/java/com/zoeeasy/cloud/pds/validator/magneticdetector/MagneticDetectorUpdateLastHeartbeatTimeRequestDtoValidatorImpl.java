package com.zoeeasy.cloud.pds.validator.magneticdetector;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pds.magneticdetector.cst.MagneticDetectorConstant;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.airjoy.MagneticDetectorUpdateLastHeartbeatTimeRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.validator.MagneticDetectorUpdateLastHeartbeatTimeRequestDtoValidator;
import org.springframework.stereotype.Component;

/**
 * 维护地磁检测器心跳时间
 *
 * @author lhj
 */
@Component
public class MagneticDetectorUpdateLastHeartbeatTimeRequestDtoValidatorImpl extends ValidatorHandler<MagneticDetectorUpdateLastHeartbeatTimeRequestDto> implements MagneticDetectorUpdateLastHeartbeatTimeRequestDtoValidator {

    @Override
    public boolean validate(ValidatorContext context, MagneticDetectorUpdateLastHeartbeatTimeRequestDto requestDto) {
        if (null == requestDto.getId()) {
            throw new ValidationException(MagneticDetectorConstant.DETECTOR_ID_NOT_NULL);
        }
        return true;
    }
}