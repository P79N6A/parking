package com.zoeeasy.cloud.pms.validator.lane;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingGarageInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingGateInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingLaneInfoEntity;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import com.zoeeasy.cloud.pms.lane.cst.ParkingLaneConstant;
import com.zoeeasy.cloud.pms.lane.dto.request.AddParkingLaneRequestDto;
import com.zoeeasy.cloud.pms.lane.validator.AddParkingLaneRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingGarageInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingGateInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingLaneInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Date: 2018/11/30
 * @author: lhj
 */
@Component
public class AddParkingLaneRequestDtoValidatorImpl extends ValidatorHandler<AddParkingLaneRequestDto> implements AddParkingLaneRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingLaneInfoCrudService parkingLaneInfoCrudService;

    @Autowired
    private ParkingGateInfoCrudService parkingGateInfoCrudService;

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Override
    public boolean validate(ValidatorContext context, AddParkingLaneRequestDto requestDto) {
        //租户
        if (requestDto.getTenantId() == null) {
            throw new ValidationException(GarageConstant.TENANTE_ID_IS_NULL);
        }
        ParkingInfoEntity entity = parkingInfoCrudService.findByCodeAndTenantId(requestDto.getCloudParkingCode(),requestDto.getTenantId());
        if (entity == null) {
            throw new ValidationException(ParkingLaneConstant.PARKING_NOT_EXIT);
        }
        //客户端编号唯一
        ParkingLaneInfoEntity code = parkingLaneInfoCrudService.selectByLocalCode(requestDto.getLaneCode());
        if (code != null) {
            throw new ValidationException(ParkingLaneConstant.PARKING_LANE_CODE_EXIT);
        }
        //出入口
        if (StringUtils.isNotEmpty(requestDto.getPlatGateCode())) {
            ParkingGateInfoEntity gateInfoEntity = parkingGateInfoCrudService.selectByParkingIdeAndCode(entity.getId(), requestDto.getPlatGateCode());
            if (gateInfoEntity == null) {
                throw new ValidationException(ParkingLaneConstant.PARKING_GATE_NOT_EXIT);
            }
            ParkingLaneInfoEntity byGateIdAndName = parkingLaneInfoCrudService.selectGateIdAndName(gateInfoEntity.getId(), requestDto.getLaneName());
            if (byGateIdAndName != null) {
                throw new ValidationException(ParkingLaneConstant.PARKING_LANE_NAME_EXIT);
            }
        } else if (StringUtils.isNotEmpty(requestDto.getCloudGarageCode())) {
            //车库
            ParkingGarageInfoEntity garageInfoEntity = parkingGarageInfoCrudService.findGarageByParkingIdAndCode(entity.getId(), requestDto.getCloudGarageCode());
            if (garageInfoEntity == null) {
                throw new ValidationException(ParkingLaneConstant.PARKING_GARAGE_NOT_EXIT);
            }
            ParkingLaneInfoEntity byGarageIdAndName = parkingLaneInfoCrudService.selectByGarageIdAndName(garageInfoEntity.getId(), requestDto.getLaneName());
            if (byGarageIdAndName != null) {
                throw new ValidationException(ParkingLaneConstant.PARKING_LANE_NAME_EXIT);
            }
        } else {
            ParkingLaneInfoEntity nameExit = parkingLaneInfoCrudService.selectByParkingIdAndName(entity.getId(), requestDto.getLaneName());
            if (nameExit != null) {
                throw new ValidationException(ParkingLaneConstant.PARKING_LANE_NAME_EXIT);
            }
        }
        return true;
    }
}
