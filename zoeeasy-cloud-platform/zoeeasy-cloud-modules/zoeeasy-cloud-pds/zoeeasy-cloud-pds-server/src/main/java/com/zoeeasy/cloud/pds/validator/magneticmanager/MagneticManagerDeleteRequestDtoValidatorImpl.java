package com.zoeeasy.cloud.pds.validator.magneticmanager;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pds.domain.MagneticDetectorEntity;
import com.zoeeasy.cloud.pds.magneticdetector.MagneticDetectorService;
import com.zoeeasy.cloud.pds.magneticmanager.cst.MagneticManagerConstant;
import com.zoeeasy.cloud.pds.magneticmanager.dto.request.MagneticManagerDeleteRequestDto;
import com.zoeeasy.cloud.pds.magneticmanager.validator.MagneticManagerDeleteRequestDtoValidator;
import com.zoeeasy.cloud.pds.service.MagneticDetectorCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 删除设备管理器
 *
 * @author lhj
 */
@Component
public class MagneticManagerDeleteRequestDtoValidatorImpl extends ValidatorHandler<MagneticManagerDeleteRequestDto> implements MagneticManagerDeleteRequestDtoValidator {

    @Autowired
    private MagneticDetectorService magneticDetectorService;

    @Autowired
    private MagneticDetectorCrudService magneticDetectorCrudService;

    @Override
    public boolean validate(ValidatorContext context, MagneticManagerDeleteRequestDto requestDto) {
        if (null == requestDto.getId()) {
            throw new ValidationException(MagneticManagerConstant.MAGNETIC_MANAGER_ID_NOT_NULL);
        }
        //判断是否有检测器关联
        EntityWrapper<MagneticDetectorEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("managerId", requestDto.getId());
        int i = magneticDetectorCrudService.selectCount(wrapper);
        if (i != 0) {
            throw new ValidationException(MagneticManagerConstant.UNBIND_MAGNETIC_DETECTOR_MANAGER);
        }
        return true;
    }
}