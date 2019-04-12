package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingApplyInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.enums.AuditStatusEnum;
import com.zoeeasy.cloud.pms.enums.ParkingStatusEnum;
import com.zoeeasy.cloud.pms.enums.RunStatusEnum;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingLotConstant;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingDeleteRequestDto;
import com.zoeeasy.cloud.pms.park.validator.ParkingDeleteRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingApplyInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingLotStatusCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkingDeleteRequestDtoValidatorImpl extends ValidatorHandler<ParkingDeleteRequestDto> implements ParkingDeleteRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingApplyInfoCrudService parkingApplyInfoCrudService;

    @Autowired
    private ParkingLotStatusCrudService parkingLotStatusCrudService;

    @Override
    public boolean accept(ValidatorContext context, ParkingDeleteRequestDto requestDto) {
        if (requestDto.getId() == null){
            throw new ValidationException(ParkingConstant.PARKING_INFO_PARKING_ID_NOT_NULL);
        }
        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(requestDto.getId());
        if (parkingInfoEntity == null){
            throw new ValidationException(ParkingConstant.PARKING_NOT_FOUND);
        }
        if (parkingInfoEntity.getStatus().equals(ParkingStatusEnum.ON_LINE.getValue())){
            throw new ValidationException(ParkingConstant.PARKING_CANNOT_DELETE);
        }
        ParkingApplyInfoEntity parkingApplyInfoExist = parkingApplyInfoCrudService.selectParkingApplyByParkingId(requestDto.getId());
        if (parkingApplyInfoExist != null){
            if (parkingApplyInfoExist.getAuditStatus().equals(AuditStatusEnum.AWAIT.getValue())){
                throw new ValidationException(ParkingConstant.PARKING_CANNOT_DELETE);
            }
            if (parkingApplyInfoExist.getAuditStatus().equals(AuditStatusEnum.PASS.getValue())
                    && parkingApplyInfoExist.getRunStatus().equals(RunStatusEnum.NOT_UP.getValue())){
                throw new ValidationException(ParkingConstant.PARKING_CANNOT_DELETE);
            }
        }
        int parkingLotStatusCount = parkingLotStatusCrudService.findCountByParkingId(requestDto.getId());
        if (parkingLotStatusCount > 0) {
            throw new ValidationException(ParkingLotConstant.PARKING_LOT_BE_USING);
        }
        return true;
    }
}
