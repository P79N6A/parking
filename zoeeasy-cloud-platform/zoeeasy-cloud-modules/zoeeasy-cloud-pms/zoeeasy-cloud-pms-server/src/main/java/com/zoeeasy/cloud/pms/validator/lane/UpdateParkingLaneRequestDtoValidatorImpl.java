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
import com.zoeeasy.cloud.pms.lane.dto.request.UpdateParkingLaneRequestDto;
import com.zoeeasy.cloud.pms.lane.validator.UpdateParkingLaneRequestDtoValidator;
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
public class UpdateParkingLaneRequestDtoValidatorImpl extends ValidatorHandler<UpdateParkingLaneRequestDto> implements UpdateParkingLaneRequestDtoValidator {
    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingLaneInfoCrudService parkingLaneInfoCrudService;

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Autowired
    private ParkingGateInfoCrudService parkingGateInfoCrudService;

    @Override
    public boolean validate(ValidatorContext context, UpdateParkingLaneRequestDto requestDto) {
        //租户
        if (requestDto.getTenantId() == null) {
            throw new ValidationException(GarageConstant.TENANTE_ID_IS_NULL);
        }
        ParkingInfoEntity entity = parkingInfoCrudService.findByCodeAndTenantId(requestDto.getCloudParkingCode(),requestDto.getTenantId());
        if (entity == null) {
            throw new ValidationException(ParkingLaneConstant.PARKING_NOT_EXIT);
        }
        //车道是否存在
        ParkingLaneInfoEntity byParkingIdAndCode = parkingLaneInfoCrudService.selectByParkingIdAndCode(entity.getId(), requestDto.getPlatLaneCode());
        if (byParkingIdAndCode == null) {
            throw new ValidationException(ParkingLaneConstant.PARKING_LANE_NOT_EXIT);
        }
        //出入口
        ParkingGateInfoEntity gateInfoEntity = null;
        ParkingGarageInfoEntity garageInfoEntity = null;
        //车库是否存在
        if (StringUtils.isNotEmpty(requestDto.getCloudGarageCode())) {
            garageInfoEntity = parkingGarageInfoCrudService.findGarageByParkingIdAndCode(entity.getId(), requestDto.getCloudGarageCode());
            if (garageInfoEntity == null) {
                throw new ValidationException(ParkingLaneConstant.PARKING_GARAGE_NOT_EXIT);
            }
        }
        //出入口
        if (StringUtils.isNotEmpty(requestDto.getPlatGateCode())) {
            gateInfoEntity = parkingGateInfoCrudService.selectByParkingIdeAndCode(entity.getId(), requestDto.getPlatGateCode());
            if (gateInfoEntity == null) {
                throw new ValidationException(ParkingLaneConstant.PARKING_GATE_NOT_EXIT);
            }
            //出入口内名称唯一
            ParkingLaneInfoEntity byGateIdAndName = parkingLaneInfoCrudService.selectGateIdAndName(gateInfoEntity.getId(), requestDto.getLaneName());
            if (byGateIdAndName != null && !byGateIdAndName.getCode().equals(requestDto.getPlatLaneCode())) {
                throw new ValidationException(ParkingLaneConstant.PARKING_LANE_NAME_EXIT);
            }
        }
        //车库内名称唯一
        else if (StringUtils.isNotEmpty(requestDto.getCloudGarageCode()) && garageInfoEntity != null) {
            ParkingLaneInfoEntity byGarageIdAndName = parkingLaneInfoCrudService.selectByGarageIdAndName(garageInfoEntity.getId(), requestDto.getLaneName());
            if (byGarageIdAndName != null && !byGarageIdAndName.getCode().equals(requestDto.getPlatLaneCode())) {
                throw new ValidationException(ParkingLaneConstant.PARKING_LANE_NAME_EXIT);
            }
        } else {
            //停车场内名称唯一
            ParkingLaneInfoEntity nameExit = parkingLaneInfoCrudService.selectByParkingIdAndName(entity.getId(), requestDto.getLaneName());
            if (nameExit != null && !nameExit.getCode().equals(requestDto.getPlatLaneCode())) {
                throw new ValidationException(ParkingLaneConstant.PARKING_LANE_NAME_EXIT);
            }
        }
        //出入口是否存在于此车库
        if (gateInfoEntity != null && garageInfoEntity != null) {
            if (!garageInfoEntity.getId().equals(gateInfoEntity.getGarageId())) {
                throw new ValidationException(ParkingLaneConstant.PARKING_GARAGE_NOT_EXIST_PARKING_GATE);
            }
        }
        return true;
    }
}
