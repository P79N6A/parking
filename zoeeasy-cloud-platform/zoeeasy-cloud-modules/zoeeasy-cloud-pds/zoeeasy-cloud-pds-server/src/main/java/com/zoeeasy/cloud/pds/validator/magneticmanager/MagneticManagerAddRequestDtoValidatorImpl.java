package com.zoeeasy.cloud.pds.validator.magneticmanager;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pds.domain.MagneticManagerEntity;
import com.zoeeasy.cloud.pds.magneticmanager.cst.MagneticManagerConstant;
import com.zoeeasy.cloud.pds.magneticmanager.dto.request.MagneticManagerAddRequestDto;
import com.zoeeasy.cloud.pds.magneticmanager.validator.MagneticManagerAddRequestDtoValidator;
import com.zoeeasy.cloud.pds.service.MagneticManagerCrudService;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 添加停车设备管理器
 *
 * @author lhj
 */
@Component
public class MagneticManagerAddRequestDtoValidatorImpl extends ValidatorHandler<MagneticManagerAddRequestDto> implements MagneticManagerAddRequestDtoValidator {

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Autowired
    private MagneticManagerCrudService magneticManagerCrudService;

    @Override
    public boolean validate(ValidatorContext context, MagneticManagerAddRequestDto requestDto) {

        EntityWrapper<MagneticManagerEntity> entityWrapper = new EntityWrapper<>();
        ParkingGetRequestDto parkingGetRequestDto = new ParkingGetRequestDto();
        parkingGetRequestDto.setId(requestDto.getParkingId());
        ObjectResultDto<ParkingResultDto> parking = parkingInfoService.getParking(parkingGetRequestDto);
        //停车场是否存在
        if (null == parking.getData()) {
            throw new ValidationException(MagneticManagerConstant.PARKING_NONENTITY);
        }
        //序列号
        entityWrapper.eq("serialNumber", requestDto.getSerialNumber());
        entityWrapper.eq("provider", requestDto.getProvider());
        MagneticManagerEntity magneticManagerEntity = magneticManagerCrudService.selectOne(entityWrapper);
        if (null != magneticManagerEntity) {
            throw new ValidationException(MagneticManagerConstant.MAGNETIC_MANAGER_SERIAL_NUMBER_YET_EXIST);
        }
        //编号
        EntityWrapper<MagneticManagerEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("code",requestDto.getCode());
        MagneticManagerEntity entity = magneticManagerCrudService.selectOne(wrapper);
        if (null != entity) {
            throw new ValidationException(MagneticManagerConstant.MAGNETIC_MANAGER_CODE_EXIST);
        }
        return true;

    }
}

