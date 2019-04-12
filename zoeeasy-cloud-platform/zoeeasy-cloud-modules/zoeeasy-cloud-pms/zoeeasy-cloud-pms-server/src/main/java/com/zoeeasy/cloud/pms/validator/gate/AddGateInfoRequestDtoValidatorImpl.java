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
import com.zoeeasy.cloud.pms.gate.dto.request.AddGateInfoRequestDto;
import com.zoeeasy.cloud.pms.gate.validator.AddGateInfoRequestDtoValidator;
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
public class AddGateInfoRequestDtoValidatorImpl extends ValidatorHandler<AddGateInfoRequestDto> implements AddGateInfoRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingGateInfoCrudService parkingGateInfoCrudService;

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Override
    public boolean validate(ValidatorContext context, AddGateInfoRequestDto requestDto) {
        //租户
        if (requestDto.getTenantId() == null) {
            throw new ValidationException(GarageConstant.TENANTE_ID_IS_NULL);
        }
        //停车场是否存在
        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.findByCodeAndTenantId(requestDto.getCloudParkingCode(),requestDto.getTenantId());
        if (parkingInfoEntity == null) {
            throw new ValidationException(ParkingGateConstant.PARKING_NOT_EXIT);
        }
        //客户端出入口编号名称唯一
        ParkingGateInfoEntity codeExit = parkingGateInfoCrudService.selectByCode(requestDto.getGateCode());
        if (codeExit != null) {
            throw new ValidationException(ParkingGateConstant.PARKING_GATE_CODE_EXIT);
        }
        //车库是否存在
        ParkingGarageInfoEntity parkingGarageInfoEntity;
        if (StringUtils.isNotEmpty(requestDto.getCloudGarageCode())) {
            parkingGarageInfoEntity = parkingGarageInfoCrudService.findGarageByParkingIdAndCode(parkingInfoEntity.getId(), requestDto.getCloudGarageCode());
            if (parkingGarageInfoEntity == null) {
                throw new ValidationException(ParkingGateConstant.PARKING_GARAGE_NOT_EXIT);
            }
            //车库内车入口名称唯一
            ParkingGateInfoEntity nameExit = parkingGateInfoCrudService.findByGarageIdeAndName(parkingGarageInfoEntity.getId(), requestDto.getGateName());
            if (nameExit != null) {
                throw new ValidationException(ParkingGateConstant.PARKING_GATE_NAME_EXIT);
            }
        } else {
            //名称停车场内唯一
            ParkingGateInfoEntity byParkingIdeAndName = parkingGateInfoCrudService.findByParkingIdeAndName(parkingInfoEntity.getId(), requestDto.getGateName());
            if (byParkingIdeAndName != null) {
                throw new ValidationException(ParkingGateConstant.PARKING_GATE_NAME_EXIT);
            }
        }
        return true;
    }
}
