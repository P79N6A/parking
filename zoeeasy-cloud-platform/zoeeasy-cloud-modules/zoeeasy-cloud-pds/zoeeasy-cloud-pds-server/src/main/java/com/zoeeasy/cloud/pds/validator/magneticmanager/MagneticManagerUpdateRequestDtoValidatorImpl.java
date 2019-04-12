package com.zoeeasy.cloud.pds.validator.magneticmanager;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pds.domain.MagneticManagerEntity;
import com.zoeeasy.cloud.pds.magneticmanager.cst.MagneticManagerConstant;
import com.zoeeasy.cloud.pds.magneticmanager.dto.request.MagneticManagerUpdateRequestDto;
import com.zoeeasy.cloud.pds.magneticmanager.validator.MagneticManagerUpdateRequestDtoValidator;
import com.zoeeasy.cloud.pds.service.MagneticManagerCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 更新停车设备管理器
 *
 * @author lhj
 */
@Component
public class MagneticManagerUpdateRequestDtoValidatorImpl extends ValidatorHandler<MagneticManagerUpdateRequestDto> implements MagneticManagerUpdateRequestDtoValidator {

    @Autowired
    private MagneticManagerCrudService magneticManagerCrudService;

    @Override
    public boolean validate(ValidatorContext context, MagneticManagerUpdateRequestDto requestDto) {
        if (null == requestDto.getId()) {
            throw new ValidationException(MagneticManagerConstant.MAGNETIC_MANAGER_ID_NOT_NULL);
        }
        //管理器是否存在
        MagneticManagerEntity magneticManagerEntity = magneticManagerCrudService.selectById(requestDto.getId());
        if (null == magneticManagerEntity) {
            throw new ValidationException(MagneticManagerConstant.MAGNETIC_MANAGER_NONENTITY);
        }
        return true;
    }
}