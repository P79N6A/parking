package com.zoeeasy.cloud.pms.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.zoeeasy.cloud.pms.domain.ParkingAreaEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingLotInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingLotStatusEntity;
import com.zoeeasy.cloud.pms.enums.ParkingLotStatusEnum;
import com.zoeeasy.cloud.pms.park.CloudParkingLotInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.AddParkingLotRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.UpdateParkingLotRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.AddParkingLotResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.UpdateParkingLotResultDto;
import com.zoeeasy.cloud.pms.park.validator.AddParkingLotRequestDtoValidator;
import com.zoeeasy.cloud.pms.park.validator.UpdateParkingLotRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingAreaCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingLotInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingLotStatusCrudService;
import com.zoeeasy.cloud.tool.vesta.intf.IdService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("cloudParkingLotInfoService")
@Slf4j
public class CloudParkingLotInfoServiceImpl implements CloudParkingLotInfoService {

    @Autowired
    private ParkingLotInfoCrudService parkingLotInfoCrudService;

    @Autowired
    private ParkingLotStatusCrudService parkingLotStatusCrudService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingAreaCrudService parkingAreaCrudService;

    @Autowired
    private IdService idService;

    /**
     * 停车场客户端添加泊位
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AddParkingLotResultDto addParkingLot(@FluentValid({AddParkingLotRequestDtoValidator.class}) AddParkingLotRequestDto requestDto) {
        AddParkingLotResultDto resultDto = new AddParkingLotResultDto();
        try {
            String cloudCode = String.valueOf(idService.genId());
            ParkingLotInfoEntity parkingLotInfoExist = parkingLotInfoCrudService.findByCode(cloudCode);
            if (parkingLotInfoExist == null) {
                ParkingLotInfoEntity parkingLotInfoEntity = new ParkingLotInfoEntity();
                ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.findByCodeAndTenantId(requestDto.getCloudParkingCode(), requestDto.getTenantId());
                parkingLotInfoEntity.setParkingId(parkingInfoEntity.getId());
                if (!StringUtils.isEmpty(requestDto.getPlatAreaCode())) {
                    ParkingAreaEntity parkingAreaEntity = parkingAreaCrudService.selectParkingAreaNonTenant(requestDto.getPlatAreaCode());
                    parkingLotInfoEntity.setParkingAreaId(parkingAreaEntity.getId());
                }
                parkingLotInfoEntity.setCode(cloudCode);
                parkingLotInfoEntity.setLocalCode(requestDto.getParkingLotCode());
                parkingLotInfoEntity.setName(requestDto.getParkingLotName());
                parkingLotInfoEntity.setDescription(requestDto.getRemark());
                parkingLotInfoEntity.setTenantId(requestDto.getTenantId());
                parkingLotInfoCrudService.insert(parkingLotInfoEntity);
                ParkingLotStatusEntity parkingLotStatusEntity = new ParkingLotStatusEntity();
                parkingLotStatusEntity.setParkingId(parkingInfoEntity.getId());
                parkingLotStatusEntity.setParkingLotId(parkingLotInfoEntity.getId());
                parkingLotStatusEntity.setStatus(ParkingLotStatusEnum.FREE.getValue());
                parkingLotStatusEntity.setTenantId(requestDto.getTenantId());
                parkingLotStatusCrudService.insert(parkingLotStatusEntity);
                resultDto.setCloudCode(cloudCode);
                resultDto.success();
            } else {
                resultDto.failed();
            }
        } catch (Exception e) {
            log.error("停车场客户端添加泊位失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 停车场客户端修改泊位
     *
     * @param requestDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public UpdateParkingLotResultDto updateParkingLot(@FluentValid({UpdateParkingLotRequestDtoValidator.class}) UpdateParkingLotRequestDto requestDto) {
        UpdateParkingLotResultDto resultDto = new UpdateParkingLotResultDto();
        try {
            ParkingLotInfoEntity parkingLotInfoEntity = new ParkingLotInfoEntity();
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.findByCodeAndTenantId(requestDto.getCloudParkingCode(), requestDto.getTenantId());
            parkingLotInfoEntity.setParkingId(parkingInfoEntity.getId());
            if (!StringUtils.isEmpty(requestDto.getPlatAreaCode())) {
                ParkingAreaEntity parkingAreaEntity = parkingAreaCrudService.selectParkingAreaNonTenant(requestDto.getPlatAreaCode());
                parkingLotInfoEntity.setParkingAreaId(parkingAreaEntity.getId());
            }
            parkingLotInfoEntity.setName(requestDto.getParkingLotName());
            parkingLotInfoEntity.setDescription(requestDto.getRemark());
            ParkingLotInfoEntity parkingLotInfoExist = parkingLotInfoCrudService.findByCode(requestDto.getPlatLotCode());
            parkingLotInfoEntity.setId(parkingLotInfoExist.getId());
            parkingLotInfoEntity.setLastModificationTime(new Date());
            parkingLotInfoCrudService.updateParkingLotInfoNonTenant(parkingLotInfoEntity);
            resultDto.setCloudCode(requestDto.getPlatLotCode());
            resultDto.success();
        } catch (Exception e) {
            log.error("停车场客户端修改泊位失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }
}
