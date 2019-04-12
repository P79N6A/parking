package com.zoeeasy.cloud.pds.validator.magneticmanager;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pds.domain.MagneticDetectorEntity;
import com.zoeeasy.cloud.pds.domain.MagneticManagerEntity;
import com.zoeeasy.cloud.pds.magneticdetector.cst.MagneticDetectorConstant;
import com.zoeeasy.cloud.pds.magneticmanager.cst.MagneticManagerConstant;
import com.zoeeasy.cloud.pds.magneticmanager.dto.request.MagneticManagerIdBatchUpdateRequestDto;
import com.zoeeasy.cloud.pds.magneticmanager.validator.MagneticManagerIdBatchUpdateRequestDtoValidator;
import com.zoeeasy.cloud.pds.service.MagneticDetectorCrudService;
import com.zoeeasy.cloud.pds.service.MagneticManagerCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 批量更新设备的管理器
 *
 * @author lhj
 */
@Component
public class MagneticManagerIdBatchUpdateRequestDtoValidatorImpl extends ValidatorHandler<MagneticManagerIdBatchUpdateRequestDto> implements MagneticManagerIdBatchUpdateRequestDtoValidator {

    @Autowired
    private MagneticManagerCrudService magneticManagerCrudService;

    @Autowired
    private MagneticDetectorCrudService magneticDetectorCrudService;

    @Override
    public boolean validate(ValidatorContext context, MagneticManagerIdBatchUpdateRequestDto requestDto) {
        //管理器ID
        MagneticManagerEntity magneticManagerEntity = magneticManagerCrudService.selectById(requestDto.getManagerId());
        if (null == magneticManagerEntity) {
            throw new ValidationException(MagneticManagerConstant.MAGNETIC_MANAGER_NONENTITY);
        }
        //code
        if (null != requestDto.getCodes()) {
            for (String code : requestDto.getCodes()) {
                EntityWrapper<MagneticDetectorEntity> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("code", code);
                MagneticDetectorEntity detectorEntity = magneticDetectorCrudService.selectOne(entityWrapper);
                if (null == detectorEntity) {
                    throw new ValidationException(MagneticDetectorConstant.MAGNETIC_DETECTOR_NONENTITY);
                }
                //管理器、地磁停车场是否对应
                if (!detectorEntity.getParkingId().equals(magneticManagerEntity.getParkingId())) {
                    throw new ValidationException(MagneticDetectorConstant.PARKING_MISMATCHING);
                }
            }
        }
        return true;
    }
}
