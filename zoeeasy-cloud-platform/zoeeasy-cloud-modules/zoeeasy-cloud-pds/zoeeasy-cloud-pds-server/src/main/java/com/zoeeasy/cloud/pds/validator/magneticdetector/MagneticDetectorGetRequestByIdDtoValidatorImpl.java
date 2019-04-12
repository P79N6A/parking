package com.zoeeasy.cloud.pds.validator.magneticdetector;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pds.domain.MagneticDetectorEntity;
import com.zoeeasy.cloud.pds.magneticdetector.cst.MagneticDetectorConstant;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.park.MagneticDetectorGetRequestByIdDto;
import com.zoeeasy.cloud.pds.magneticdetector.validator.MagneticDetectorGetRequestByIdDtoValidator;
import com.zoeeasy.cloud.pds.service.MagneticDetectorCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 根据ID获取地磁
 *
 * @Date: 2018/10/8
 * @author: lhj
 */
@Component
public class MagneticDetectorGetRequestByIdDtoValidatorImpl extends ValidatorHandler<MagneticDetectorGetRequestByIdDto> implements MagneticDetectorGetRequestByIdDtoValidator {

    @Autowired
    private MagneticDetectorCrudService magneticDetectorCrudService;

    @Override
    public boolean validate(ValidatorContext context, MagneticDetectorGetRequestByIdDto requestDto) {
        if (null == requestDto.getId()) {
            throw new ValidationException(MagneticDetectorConstant.DETECTOR_ID_NOT_NULL);
        }
        //地磁是否存在
        MagneticDetectorEntity magneticDetectorEntity = magneticDetectorCrudService.selectById(requestDto.getId());
        if (null == magneticDetectorEntity) {
            throw new ValidationException(MagneticDetectorConstant.MAGNETIC_DETECTOR_NONENTITY);
        }
        return true;

    }
}
