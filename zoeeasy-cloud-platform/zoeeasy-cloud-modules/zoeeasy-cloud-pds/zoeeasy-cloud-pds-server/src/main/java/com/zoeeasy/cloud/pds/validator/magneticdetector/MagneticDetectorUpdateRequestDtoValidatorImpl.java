package com.zoeeasy.cloud.pds.validator.magneticdetector;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pds.domain.MagneticDetectorEntity;
import com.zoeeasy.cloud.pds.magneticdetector.cst.MagneticDetectorConstant;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.park.MagneticDetectorUpdateRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.validator.MagneticDetectorUpdateRequestDtoValidator;
import com.zoeeasy.cloud.pds.service.MagneticDetectorCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 修改地磁
 *
 * @Date: 2018/9/28
 * @author: lhj
 */
@Component
public class MagneticDetectorUpdateRequestDtoValidatorImpl extends ValidatorHandler<MagneticDetectorUpdateRequestDto> implements MagneticDetectorUpdateRequestDtoValidator {

    @Autowired
    private MagneticDetectorCrudService magneticDetectorCrudService;

    @Override
    public boolean validate(ValidatorContext context, MagneticDetectorUpdateRequestDto requestDto) {
        if (null == requestDto.getId()) {
            throw new ValidationException(MagneticDetectorConstant.DETECTOR_ID_NOT_NULL);
        }
        MagneticDetectorEntity magneticDetectorEntity = magneticDetectorCrudService.selectById(requestDto.getId());
        if (magneticDetectorEntity == null) {
            throw new ValidationException(MagneticDetectorConstant.MAGNETIC_DETECTOR_NONENTITY);
        }
        return true;
    }
}
