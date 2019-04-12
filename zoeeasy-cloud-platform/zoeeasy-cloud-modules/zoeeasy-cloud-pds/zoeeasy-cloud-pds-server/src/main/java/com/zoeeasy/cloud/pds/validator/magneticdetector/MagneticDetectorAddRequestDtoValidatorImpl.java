package com.zoeeasy.cloud.pds.validator.magneticdetector;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pds.domain.MagneticDetectorEntity;
import com.zoeeasy.cloud.pds.magneticdetector.cst.MagneticDetectorConstant;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.park.MagneticDetectorAddRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.validator.MagneticDetectorAddRequestDtoValidator;
import com.zoeeasy.cloud.pds.service.MagneticDetectorCrudService;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 添加地磁
 *
 * @author lhj
 */
@Component
public class MagneticDetectorAddRequestDtoValidatorImpl extends ValidatorHandler<MagneticDetectorAddRequestDto> implements MagneticDetectorAddRequestDtoValidator {

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Autowired
    private MagneticDetectorCrudService magneticDetectorCrudService;

    @Override
    public boolean validate(ValidatorContext context, MagneticDetectorAddRequestDto requestDto) {
        EntityWrapper<MagneticDetectorEntity> entityWrapper = new EntityWrapper<>();
        //停车场
        ParkingGetRequestDto parkingGetRequestDto = new ParkingGetRequestDto();
        parkingGetRequestDto.setId(requestDto.getParkingId());
        ObjectResultDto<ParkingResultDto> parking = parkingInfoService.getParkingInfo(parkingGetRequestDto);
        if (null == parking.getData()) {
            throw new ValidationException(MagneticDetectorConstant.PARKING_NONENTITY);
        }
        //设备序列号
        entityWrapper.eq("serialNumber", requestDto.getSerialNumber());
        entityWrapper.eq("provider", requestDto.getProvider());
        MagneticDetectorEntity magneticDetectorEntity = magneticDetectorCrudService.selectOne(entityWrapper);
        if (null != magneticDetectorEntity) {
            throw new ValidationException(MagneticDetectorConstant.MAGNETIC_DETECTOR_SERIAL_NUMBER_NONENTITY);
        }
        //地磁编号
        EntityWrapper<MagneticDetectorEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("code", requestDto.getCode());
        MagneticDetectorEntity detectorEntity = magneticDetectorCrudService.selectOne(wrapper);
        if (null != detectorEntity) {
            throw new ValidationException(MagneticDetectorConstant.MAGNETIC_DETECTOR_CODE_EXIST);
        }
        return true;
    }
}