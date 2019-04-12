package com.zoeeasy.cloud.pds.validator.magneticdetector;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pds.domain.MagneticDetectorEntity;
import com.zoeeasy.cloud.pds.magneticdetector.cst.MagneticDetectorConstant;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.park.DetectorParkingLotIdBatchUpdateRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.validator.DetectorParkingLotIdBatchUpdateRequestDtoValidator;
import com.zoeeasy.cloud.pds.service.MagneticDetectorCrudService;
import com.zoeeasy.cloud.pms.park.ParkingLotInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingLotGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingLotResultDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 更新设备泊位Id
 *
 * @Date: 2018/10/17
 * @author: lhj
 */
@Component
public class DetectorParkingLotIdBatchUpdateRequestDtoValidatorImpl extends ValidatorHandler<DetectorParkingLotIdBatchUpdateRequestDto> implements DetectorParkingLotIdBatchUpdateRequestDtoValidator {

    @Autowired
    private ParkingLotInfoService parkingLotInfoService;

    @Autowired
    private MagneticDetectorCrudService magneticDetectorCrudService;

    @Override
    public boolean validate(ValidatorContext context, DetectorParkingLotIdBatchUpdateRequestDto requestDto) {
        EntityWrapper<MagneticDetectorEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parkingLotId", requestDto.getParkingLotId());
        //泊位是否存在
        ParkingLotGetRequestDto parkingLotGetRequestDto = new ParkingLotGetRequestDto();
        parkingLotGetRequestDto.setId(requestDto.getParkingLotId());
        ObjectResultDto<ParkingLotResultDto> parkingLot = parkingLotInfoService.getParkingLot(parkingLotGetRequestDto);
        if (null == parkingLot.getData()) {
            throw new ValidationException(MagneticDetectorConstant.PARKING_LOT_NONENTITY);
        }
        //地磁是否存在
        if (!StringUtils.isEmpty(requestDto.getCode())) {
            EntityWrapper<MagneticDetectorEntity> wrapper = new EntityWrapper<>();
            wrapper.eq("code", requestDto.getCode());
            MagneticDetectorEntity magneticDetectorEntity = magneticDetectorCrudService.selectOne(wrapper);
            if (null == magneticDetectorEntity) {
                throw new ValidationException(MagneticDetectorConstant.MAGNETIC_DETECTOR_NONENTITY);
            }
            //泊位停车场是否对应
            if (!parkingLot.getData().getParkingId().equals(magneticDetectorEntity.getParkingId())) {
                throw new ValidationException(MagneticDetectorConstant.PARKING_AND_PARKING_LOT_MISMATCHING);
            }
            //地磁是否关联泊位
            if (magneticDetectorEntity.getParkingLotId() != 0) {
                throw new ValidationException(MagneticDetectorConstant.MAGNETIC_DETECTOR_YET_RELEVANCE_PARKING_LOT);
            }
        }
        return true;
    }
}
