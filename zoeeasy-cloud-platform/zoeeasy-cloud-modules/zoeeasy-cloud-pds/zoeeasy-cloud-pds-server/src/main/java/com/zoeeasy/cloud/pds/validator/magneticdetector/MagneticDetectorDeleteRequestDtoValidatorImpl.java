package com.zoeeasy.cloud.pds.validator.magneticdetector;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pds.magneticdetector.cst.MagneticDetectorConstant;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.park.MagneticDetectorDeleteRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.validator.MagneticDetectorDeleteRequestDtoValidator;
import com.zoeeasy.cloud.pds.service.MagneticDetectorCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 删除地磁
 *
 * @author lhj
 */
@Component
public class MagneticDetectorDeleteRequestDtoValidatorImpl extends ValidatorHandler<MagneticDetectorDeleteRequestDto> implements MagneticDetectorDeleteRequestDtoValidator {

    @Autowired
    private MagneticDetectorCrudService magneticDetectorCrudService;

    @Override
    public boolean validate(ValidatorContext context, MagneticDetectorDeleteRequestDto requestDto) {
        if (null == requestDto.getId()) {
            throw new ValidationException(MagneticDetectorConstant.DETECTOR_ID_NOT_NULL);
        }
        return true;
    }
}