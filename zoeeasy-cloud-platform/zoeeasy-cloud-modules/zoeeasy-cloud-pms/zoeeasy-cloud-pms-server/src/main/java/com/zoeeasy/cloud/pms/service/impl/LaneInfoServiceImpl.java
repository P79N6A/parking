package com.zoeeasy.cloud.pms.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.zoeeasy.cloud.pms.domain.ParkingGarageInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingGateInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingLaneInfoEntity;
import com.zoeeasy.cloud.pms.lane.LaneInfoService;
import com.zoeeasy.cloud.pms.lane.cst.ParkingLaneConstant;
import com.zoeeasy.cloud.pms.lane.dto.request.AddParkingLaneRequestDto;
import com.zoeeasy.cloud.pms.lane.dto.request.UpdateParkingLaneRequestDto;
import com.zoeeasy.cloud.pms.lane.dto.result.AddParkingLaneResultDto;
import com.zoeeasy.cloud.pms.lane.dto.result.UpdateParkingLaneResultDto;
import com.zoeeasy.cloud.pms.lane.validator.AddParkingLaneRequestDtoValidator;
import com.zoeeasy.cloud.pms.lane.validator.UpdateParkingLaneRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingGarageInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingGateInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingLaneInfoCrudService;
import com.zoeeasy.cloud.tool.vesta.intf.IdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Date: 2018/11/30
 * @author: lhj
 */
@Service("laneInfoService")
@Slf4j
public class LaneInfoServiceImpl implements LaneInfoService {

    @Autowired
    private ParkingLaneInfoCrudService parkingLaneInfoCrudService;

    @Autowired
    private ParkingGateInfoCrudService parkingGateInfoCrudService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Autowired
    private IdService idService;

    /**
     * 添加车道
     *
     * @param requestDto
     * @return
     */
    @Override
    public AddParkingLaneResultDto addParkingLane(@FluentValid(AddParkingLaneRequestDtoValidator.class) AddParkingLaneRequestDto requestDto) {
        AddParkingLaneResultDto addParkingLaneResultDto = new AddParkingLaneResultDto();
        try {
            String code = String.valueOf(idService.genId());
            ParkingLaneInfoEntity entity = parkingLaneInfoCrudService.selectByCode(code);
            if (entity == null) {
                ParkingLaneInfoEntity parkingLaneInfoEntity = new ParkingLaneInfoEntity();
                parkingLaneInfoEntity.setTenantId(requestDto.getTenantId());
                parkingLaneInfoEntity.setCode(code);
                parkingLaneInfoEntity.setLocalCode(requestDto.getLaneCode());
                parkingLaneInfoEntity.setName(requestDto.getLaneName());
                parkingLaneInfoEntity.setDirection(requestDto.getDirection());
                parkingLaneInfoEntity.setRemarks(requestDto.getRemark());
                //停车场
                ParkingInfoEntity infoEntity = parkingInfoCrudService.findByCodeAndTenantId(requestDto.getCloudParkingCode(),requestDto.getTenantId());
                if (infoEntity != null) {
                    parkingLaneInfoEntity.setParkingId(infoEntity.getId());
                }
                //车库
                if (StringUtils.isNotEmpty(requestDto.getCloudGarageCode())) {
                    ParkingGarageInfoEntity byCode = parkingGarageInfoCrudService.selectByCode(requestDto.getCloudGarageCode());
                    if (byCode != null) {
                        parkingLaneInfoEntity.setGarageId(byCode.getId());
                    }
                }
                //出入口
                if (StringUtils.isNotEmpty(requestDto.getPlatGateCode())) {
                    ParkingGateInfoEntity gateInfoEntity = parkingGateInfoCrudService.selectGateByCode(requestDto.getPlatGateCode());
                    if (gateInfoEntity != null) {
                        parkingLaneInfoEntity.setGateId(gateInfoEntity.getId());
                    }
                }
                parkingLaneInfoCrudService.insert(parkingLaneInfoEntity);
                //如果归属于gate
                if (StringUtils.isNotEmpty(requestDto.getPlatGateCode())) {
                    ParkingGateInfoEntity parkingGateInfoEntity = parkingGateInfoCrudService.selectGateByCode(requestDto.getPlatGateCode());
                    parkingGateInfoEntity.setLaneCount(parkingGateInfoEntity.getLaneCount() + ParkingLaneConstant.PARKING_LANE_COUNT_CHANGE);
                    ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.findByCodeAndTenantId(requestDto.getCloudParkingCode(),requestDto.getTenantId());
                    Wrapper<ParkingGateInfoEntity> entityWrapper = new EntityWrapper<>();
                    entityWrapper.eq("parkingId", parkingInfoEntity.getId());
                    entityWrapper.eq("code", requestDto.getPlatGateCode());
                    parkingGateInfoCrudService.update(parkingGateInfoEntity, entityWrapper);
                }
                addParkingLaneResultDto.setCloudCode(parkingLaneInfoEntity.getCode());
                addParkingLaneResultDto.success();
            } else {
                addParkingLaneResultDto.failed();
            }
        } catch (Exception e) {
            log.error("添加车道失败" + e.getMessage());
            addParkingLaneResultDto.failed();
        }
        return addParkingLaneResultDto;
    }

    /**
     * 修改车道
     *
     * @param requestDto
     * @return
     */
    @Override
    public UpdateParkingLaneResultDto updateParkingLane(@FluentValid(UpdateParkingLaneRequestDtoValidator.class) UpdateParkingLaneRequestDto requestDto) {
        UpdateParkingLaneResultDto updateParkingLaneResultDto = new UpdateParkingLaneResultDto();
        try {
            ParkingLaneInfoEntity parkingLaneInfoEntity = new ParkingLaneInfoEntity();
            parkingLaneInfoEntity.setTenantId(requestDto.getTenantId());
            parkingLaneInfoEntity.setName(requestDto.getLaneName());
            parkingLaneInfoEntity.setDirection(requestDto.getDirection());
            parkingLaneInfoEntity.setRemarks(requestDto.getRemark());
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.findByCodeAndTenantId(requestDto.getCloudParkingCode(),requestDto.getTenantId());
            //车库、出入口
            if (StringUtils.isNotEmpty(requestDto.getCloudGarageCode()) && StringUtils.isNotEmpty(requestDto.getPlatGateCode())) {
                ParkingGarageInfoEntity parkingGarageInfoEntity = parkingGarageInfoCrudService.findGarageByParkingIdAndCode(parkingInfoEntity.getId(), requestDto.getCloudGarageCode());
                if (parkingGarageInfoEntity != null) {
                    parkingLaneInfoEntity.setGarageId(parkingGarageInfoEntity.getId());
                    ParkingGateInfoEntity byGarageIdAndCode = parkingGateInfoCrudService.findByGarageIdAndCode(parkingGarageInfoEntity.getId(), requestDto.getPlatGateCode());
                    if (byGarageIdAndCode != null) {
                        parkingLaneInfoEntity.setGateId(byGarageIdAndCode.getId());
                    }
                }
            }
            //车库
            else if (StringUtils.isNotEmpty(requestDto.getCloudGarageCode())) {
                ParkingGarageInfoEntity parkingGarageInfoEntity = parkingGarageInfoCrudService.findGarageByParkingIdAndCode(parkingInfoEntity.getId(), requestDto.getCloudGarageCode());
                if (parkingGarageInfoEntity != null) {
                    parkingLaneInfoEntity.setGarageId(parkingGarageInfoEntity.getId());
                }
            }  //出入口
            else if (StringUtils.isNotEmpty(requestDto.getPlatGateCode())) {
                ParkingGateInfoEntity byParkingIdeAndCode = parkingGateInfoCrudService.selectByParkingIdeAndCode(parkingInfoEntity.getId(), requestDto.getPlatGateCode());
                if (byParkingIdeAndCode != null) {
                    parkingLaneInfoEntity.setGateId(byParkingIdeAndCode.getId());
                }
            }
            ParkingLaneInfoEntity lane = parkingLaneInfoCrudService.selectByParkingIdAndCode(parkingInfoEntity.getId(), requestDto.getPlatLaneCode());
            parkingLaneInfoEntity.setId(lane.getId());
            parkingLaneInfoEntity.setLastModificationTime(new Date());
            parkingLaneInfoCrudService.updateParkingLaneNonTenant(parkingLaneInfoEntity);
            updateParkingLaneResultDto.setCloudCode(requestDto.getPlatLaneCode());
            updateParkingLaneResultDto.success();
        } catch (Exception e) {
            log.error("修改车道失败" + e.getMessage());
            updateParkingLaneResultDto.failed();
        }
        return updateParkingLaneResultDto;
    }
}
