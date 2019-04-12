package com.zoeeasy.cloud.pms.validator.parkinglot;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pds.magneticdetector.MagneticDetectorService;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.park.MagneticDetectorParkingLotGetRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.result.park.MagneticDetectorByCodeResultDto;
import com.zoeeasy.cloud.pms.domain.ParkingLotInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingLotStatusEntity;
import com.zoeeasy.cloud.pms.enums.ParkingLotStatusEnum;
import com.zoeeasy.cloud.pms.park.cst.ParkingLotConstant;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingLotDeleteRequestDto;
import com.zoeeasy.cloud.pms.park.validator.ParkingLotDeleteRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingLotInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingLotStatusCrudService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 泊位删除校验
 * Created by song on 2018/9/12.
 */
@Component
public class ParkingLotDeleteDtoValidatorImpl extends ValidatorHandler<ParkingLotDeleteRequestDto> implements ParkingLotDeleteRequestDtoValidator {

    @Autowired
    private ParkingLotInfoCrudService parkingLotInfoCrudService;

    @Autowired
    private ParkingLotStatusCrudService parkingLotStatusCrudService;

    @Autowired
    private MagneticDetectorService magneticDetectorService;

    @Override
    public boolean validate(ValidatorContext context, ParkingLotDeleteRequestDto requestDto) {
        if (requestDto.getId() == null) {
            throw new ValidationException(ParkingLotConstant.PARKING_LOT_ID_NOT_NULL);
        }
        ParkingLotInfoEntity parkingLotInfoEntity = parkingLotInfoCrudService.selectById(requestDto.getId());
        if (parkingLotInfoEntity == null) {
            throw new ValidationException(ParkingLotConstant.PARKING_LOT_NOT_FOUND);
        }
        ParkingLotStatusEntity parkingLotStatusEntity = parkingLotStatusCrudService.findByParkingLotId(requestDto.getId());
        if (parkingLotStatusEntity == null) {
            throw new ValidationException(ParkingLotConstant.PARKING_LOT_STATUS_NOT_NULL);
        }
        if (!parkingLotStatusEntity.getStatus().equals(ParkingLotStatusEnum.FREE.getValue())) {
            throw new ValidationException(ParkingLotConstant.PARKING_LOT_BE_USING_NOT_DELETE);
        }
        MagneticDetectorParkingLotGetRequestDto magneticDetectorParkingLotGetRequestDto = new MagneticDetectorParkingLotGetRequestDto();
        magneticDetectorParkingLotGetRequestDto.setParkingLotId(requestDto.getId());
        ListResultDto<MagneticDetectorByCodeResultDto> dtos = magneticDetectorService.getMagneticDetectorListByParkingLotId(magneticDetectorParkingLotGetRequestDto);
        if (CollectionUtils.isNotEmpty(dtos.getItems())) {
            throw new ValidationException(ParkingLotConstant.PARKING_LOT_RELEVANCE);
        }
        return true;
    }
}
