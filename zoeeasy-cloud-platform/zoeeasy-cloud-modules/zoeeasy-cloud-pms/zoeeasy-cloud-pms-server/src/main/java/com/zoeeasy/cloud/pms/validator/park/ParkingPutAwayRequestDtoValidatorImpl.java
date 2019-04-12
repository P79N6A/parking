package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingApplyInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.enums.ApplyTypeEnum;
import com.zoeeasy.cloud.pms.enums.AuditStatusEnum;
import com.zoeeasy.cloud.pms.enums.ParkingStatusEnum;
import com.zoeeasy.cloud.pms.enums.RunStatusEnum;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingPutAwayRequestDto;
import com.zoeeasy.cloud.pms.park.validator.ParkingPutAwayRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingApplyInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkingPutAwayRequestDtoValidatorImpl extends ValidatorHandler<ParkingPutAwayRequestDto> implements ParkingPutAwayRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingApplyInfoCrudService parkingApplyInfoCrudService;

    @Override
    public boolean accept(ValidatorContext context, ParkingPutAwayRequestDto requestDto) {
        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.getParkInfoById(requestDto.getParkingId());
        if (parkingInfoEntity == null){
            throw new ValidationException(ParkingConstant.PARKING_NOT_FOUND);
        }
        ParkingApplyInfoEntity parkingApplyInfoEntity = parkingApplyInfoCrudService.selectParkingApplyByParkingId(requestDto.getParkingId());
        //无租户id 是第三方停车场，无需审核即无申请审核记录
        if (parkingInfoEntity.getTenantId() != null){
            if (parkingApplyInfoEntity == null){
                throw new ValidationException(ParkingConstant.PARKING_APPLY_NOT_NULL);
            }
            if (requestDto.getStatus().equals(ParkingStatusEnum.ON_LINE.getValue())
                    && !parkingApplyInfoEntity.getApplyType().equals(ApplyTypeEnum.UP.getValue())){
                throw new ValidationException(ParkingConstant.PARKING_CANNOT_UP);
            }
            if (requestDto.getStatus().equals(ParkingStatusEnum.NOT_ON_LINE.getValue())
                    && !parkingApplyInfoEntity.getApplyType().equals(ApplyTypeEnum.DOWN.getValue())){
                throw new ValidationException(ParkingConstant.PARKING_CANNOT_DOWN);
            }
            if (!(parkingApplyInfoEntity.getAuditStatus().equals(AuditStatusEnum.PASS.getValue())
                    && parkingInfoEntity.getAuditStatus().equals(AuditStatusEnum.PASS.getValue()))){
                throw new ValidationException(ParkingConstant.APPLY_NOT_PASS);
            }
            if (requestDto.getStatus().equals(ParkingStatusEnum.ON_LINE.getValue())
                    && !parkingApplyInfoEntity.getRunStatus().equals(RunStatusEnum.NOT_UP.getValue())) {
                throw new ValidationException(ParkingConstant.PARKING_RUN_STATUS_ERROR);
            }
            if (requestDto.getStatus().equals(ParkingStatusEnum.NOT_ON_LINE.getValue())
                    && !parkingApplyInfoEntity.getRunStatus().equals(RunStatusEnum.ALREADY_UP.getValue())) {
                throw new ValidationException(ParkingConstant.PARKING_RUN_STATUS_ERROR);
            }
        }
        if (requestDto.getStatus().equals(ParkingStatusEnum.ON_LINE.getValue())
                && parkingInfoEntity.getStatus().equals(ParkingStatusEnum.ON_LINE.getValue())){
            throw new ValidationException(ParkingConstant.PARKING_ALREADY_ON_LINE);
        }
        if (requestDto.getStatus().equals(ParkingStatusEnum.NOT_ON_LINE.getValue())
                && parkingInfoEntity.getStatus().equals(ParkingStatusEnum.NOT_ON_LINE.getValue())){
            throw new ValidationException(ParkingConstant.PARKING_ALREADY_NOT_ON_LINE);
        }
        return true;
    }
}
