package com.zoeeasy.cloud.pms.validator.gate;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingGarageInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingGateInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import com.zoeeasy.cloud.pms.gate.cst.ParkingGateConstant;
import com.zoeeasy.cloud.pms.gate.dto.request.UpdateGateInfoRequestDto;
import com.zoeeasy.cloud.pms.gate.validator.UpdateGateInfoRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingGarageInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingGateInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Date: 2018/11/30
 * @author: lhj
 */
@Component
public class UpdateGateInfoRequestDtoValidatorImpl extends ValidatorHandler<UpdateGateInfoRequestDto> implements UpdateGateInfoRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingGateInfoCrudService parkingGateInfoCrudService;

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Override
    public boolean validate(ValidatorContext context, UpdateGateInfoRequestDto requestDto) {
        //租户
        if (requestDto.getTenantId() == null) {
            throw new ValidationException(GarageConstant.TENANTE_ID_IS_NULL);
        }
        //停车场是否存在
        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.findByCodeAndTenantId(requestDto.getCloudParkingCode(),requestDto.getTenantId());
        if (parkingInfoEntity == null) {
            throw new ValidationException(ParkingGateConstant.PARKING_NOT_EXIT);
        }
        //出入口是否存在
        ParkingGateInfoEntity byParkingIdeAndCode = parkingGateInfoCrudService.selectByParkingIdeAndCode(parkingInfoEntity.getId(), requestDto.getPlatGateCode());
        if (byParkingIdeAndCode == null) {
            throw new ValidationException(ParkingGateConstant.PARKING_GATE_NOT_EXIT);
        }
        //车库
        ParkingGarageInfoEntity byCode;
        if (StringUtils.isNotEmpty(requestDto.getCloudGarageCode())) {
            byCode = parkingGarageInfoCrudService.findGarageByParkingIdAndCode(parkingInfoEntity.getId(), requestDto.getCloudGarageCode());
            if (byCode == null) {
                throw new ValidationException(ParkingGateConstant.PARKING_GARAGE_NOT_EXIT);
            }
            //车库内名称唯一
            ParkingGateInfoEntity byGarageIdeAndName = parkingGateInfoCrudService.findByGarageIdeAndName(byCode.getId(), requestDto.getGateName());
            if (byGarageIdeAndName != null && !byGarageIdeAndName.getCode().equals(requestDto.getPlatGateCode())) {
                throw new ValidationException(ParkingGateConstant.PARKING_GATE_NAME_EXIT);
            }
        } else {
            //停车场内名称唯一
            ParkingGateInfoEntity byParkingIdeAndName = parkingGateInfoCrudService.findByParkingIdeAndName(parkingInfoEntity.getId(), requestDto.getGateName());
            if (byParkingIdeAndName != null && !byParkingIdeAndName.getCode().equals(requestDto.getPlatGateCode())) {
                throw new ValidationException(ParkingGateConstant.PARKING_GATE_NAME_EXIT);
            }
        }
        return true;
    }
}
