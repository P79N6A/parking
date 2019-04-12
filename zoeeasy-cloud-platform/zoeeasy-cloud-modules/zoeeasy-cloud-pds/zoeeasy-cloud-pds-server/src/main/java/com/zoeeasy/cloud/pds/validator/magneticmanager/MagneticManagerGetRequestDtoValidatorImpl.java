package com.zoeeasy.cloud.pds.validator.magneticmanager;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pds.magneticmanager.cst.MagneticManagerConstant;
import com.zoeeasy.cloud.pds.magneticmanager.dto.request.MagneticManagerGetRequestDto;
import com.zoeeasy.cloud.pds.magneticmanager.validator.MagneticManagerGetRequestDtoValidator;
import org.springframework.stereotype.Component;

/**
 * 获取设备管理器
 *
 * @author lhj
 */
@Component
public class MagneticManagerGetRequestDtoValidatorImpl extends ValidatorHandler<MagneticManagerGetRequestDto> implements MagneticManagerGetRequestDtoValidator {

    @Override
    public boolean validate(ValidatorContext context, MagneticManagerGetRequestDto requestDto) {

        if (null == requestDto.getId()) {
            throw new ValidationException(MagneticManagerConstant.MAGNETIC_MANAGER_ID_NOT_NULL);
        }
        return true;
    }
}
