package com.zoeeasy.cloud.pms.validator.gate;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingGarageInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingGateInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.gate.cst.ParkingGateConstant;
import com.zoeeasy.cloud.pms.gate.dto.request.ParkingGateAddRequestDto;
import com.zoeeasy.cloud.pms.gate.validator.ParkingGateAddValidator;
import com.zoeeasy.cloud.pms.service.ParkingGarageInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingGateInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 出入口添加校验
 * created by kane 2018/10/8
 */
@Component
public class ParkingGateAddValidatorImpl extends ValidatorHandler<ParkingGateAddRequestDto> implements ParkingGateAddValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingGateInfoCrudService parkingGateInfoCrudService;

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Override
    public boolean validate(ValidatorContext context, ParkingGateAddRequestDto requestDto) {
            ParkingInfoEntity entity = parkingInfoCrudService.selectById(requestDto.getParkingId());
            if (entity == null) {
                throw new ValidationException(ParkingGateConstant.PARKING_NOT_EXIT);
            }
        if (requestDto.getGarageId() != null) {
            ParkingGarageInfoEntity garageInfoEntity = parkingGarageInfoCrudService.selectById(requestDto.getGarageId());
            if (garageInfoEntity == null) {
                throw new ValidationException(ParkingGateConstant.PARKING_GARAGE_NOT_EXIT);
            }
        }
        ParkingGateInfoEntity codeExit = parkingGateInfoCrudService.findByCode(requestDto.getCode());

        if (codeExit != null) {
            throw new ValidationException(ParkingGateConstant.PARKING_GATE_CODE_EXIT);
        }
        if (requestDto.getGarageId() != null) {
            ParkingGateInfoEntity nameExit = parkingGateInfoCrudService.findByNameAndGarageId(requestDto.getName(), requestDto.getGarageId());
            if (nameExit != null) {
                throw new ValidationException(ParkingGateConstant.PARKING_GATE_NAME_EXIT);
            }
        }
        ParkingGateInfoEntity nameExit = parkingGateInfoCrudService.findByNameAndParkingId(requestDto.getName(), requestDto.getParkingId());
        if (nameExit != null) {
            throw new ValidationException(ParkingGateConstant.PARKING_GATE_NAME_EXIT);
        }
        return super.validate(context, requestDto);
    }

}
