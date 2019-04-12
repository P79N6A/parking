package com.zoeeasy.cloud.pms.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.zoeeasy.cloud.pms.domain.ParkingAreaEntity;
import com.zoeeasy.cloud.pms.domain.ParkingGarageInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.parkingarea.CloudParkingAreaService;
import com.zoeeasy.cloud.pms.parkingarea.dto.request.AddParkingAreaRequestDto;
import com.zoeeasy.cloud.pms.parkingarea.dto.request.UpdateParkingAreaRequestDto;
import com.zoeeasy.cloud.pms.parkingarea.dto.result.AddParkingAreaResultDto;
import com.zoeeasy.cloud.pms.parkingarea.dto.result.UpdateParkingAreaResultDto;
import com.zoeeasy.cloud.pms.parkingarea.validator.AddParkingAreaRequestDtoValidator;
import com.zoeeasy.cloud.pms.parkingarea.validator.UpdateParkingAreaRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingAreaCrudService;
import com.zoeeasy.cloud.pms.service.ParkingGarageInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import com.zoeeasy.cloud.tool.vesta.intf.IdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Date: 2018/12/1
 * @author: lhj
 */
@Service("cloudParkingAreaService")
@Slf4j
public class CloudParkingAreaServiceImpl implements CloudParkingAreaService {

    @Autowired
    private ParkingAreaCrudService parkingAreaCrudService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Autowired
    private IdService idService;

    /**
     * 添加停车区域
     *
     * @param requestDto
     * @return
     */
    @Override
    public AddParkingAreaResultDto addParkingArea(@FluentValid(AddParkingAreaRequestDtoValidator.class) AddParkingAreaRequestDto requestDto) {
        AddParkingAreaResultDto addParkingAreaResultDto = new AddParkingAreaResultDto();
        try {
            String code = String.valueOf(idService.genId());
            ParkingAreaEntity entity = parkingAreaCrudService.selectByCode(code);
            if (entity == null) {
                ParkingAreaEntity parkingAreaEntity = new ParkingAreaEntity();
                parkingAreaEntity.setCode(code);
                //停车场
                ParkingInfoEntity infoEntity = parkingInfoCrudService.findByCodeAndTenantId(requestDto.getCloudParkingCode(), requestDto.getTenantId());
                parkingAreaEntity.setParkingId(infoEntity.getId());
                //车库
                if (StringUtils.isNotEmpty(requestDto.getCloudGarageCode())) {
                    ParkingGarageInfoEntity byCode = parkingGarageInfoCrudService.selectByCode(requestDto.getCloudGarageCode());
                    if (byCode != null) {
                        parkingAreaEntity.setGarageId(byCode.getId());
                    }
                }
                parkingAreaEntity.setLocalCode(requestDto.getParkingAreaCode());
                parkingAreaEntity.setName(requestDto.getParkingAreaName());
                parkingAreaEntity.setLotAvailable(requestDto.getLotAvailable());
                parkingAreaEntity.setLotTotal(requestDto.getLotTotal());
                parkingAreaEntity.setLotFixed(requestDto.getLotFixed());
                parkingAreaEntity.setRemark(requestDto.getRemark());
                parkingAreaEntity.setTenantId(requestDto.getTenantId());
                parkingAreaCrudService.insert(parkingAreaEntity);
                addParkingAreaResultDto.setCloudCode(parkingAreaEntity.getCode());
                addParkingAreaResultDto.success();
            } else {
                addParkingAreaResultDto.failed();
            }
        } catch (Exception e) {
            log.error("添加停车区域失败" + e.getMessage());
            addParkingAreaResultDto.failed();
        }
        return addParkingAreaResultDto;
    }

    /**
     * 修改停车区域
     *
     * @param requestDto
     * @return
     */
    @Override
    public UpdateParkingAreaResultDto updateParkingArea(@FluentValid(UpdateParkingAreaRequestDtoValidator.class) UpdateParkingAreaRequestDto requestDto) {
        UpdateParkingAreaResultDto updateParkingAreaResultDto = new UpdateParkingAreaResultDto();
        try {
            ParkingAreaEntity parkingAreaEntity = new ParkingAreaEntity();
            parkingAreaEntity.setName(requestDto.getParkingAreaName());
            parkingAreaEntity.setLotAvailable(requestDto.getLotAvailable());
            parkingAreaEntity.setLotTotal(requestDto.getLotTotal());
            parkingAreaEntity.setLotFixed(requestDto.getLotFixed());
            parkingAreaEntity.setRemark(requestDto.getRemark());

            //根据云平台停车场修改
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.findByCodeAndTenantId(requestDto.getCloudParkingCode(), requestDto.getTenantId());
            //车库
            if (StringUtils.isNotEmpty(requestDto.getCloudGarageCode())) {
                ParkingGarageInfoEntity parkingGarageInfoEntity = parkingGarageInfoCrudService.findGarageByParkingIdAndCode(parkingInfoEntity.getId(), requestDto.getCloudGarageCode());
                if (parkingGarageInfoEntity != null) {
                    parkingAreaEntity.setGarageId(parkingGarageInfoEntity.getId());
                }
            }
            ParkingAreaEntity parkingAreaExist = parkingAreaCrudService.selectByCodeAndParkingId(requestDto.getPlatAreaCode(), parkingInfoEntity.getId());
            parkingAreaEntity.setId(parkingAreaExist.getId());
            parkingAreaEntity.setLastModificationTime(new Date());
            parkingAreaCrudService.updateParkingAreaNonTenant(parkingAreaEntity);
            updateParkingAreaResultDto.setCloudCode(requestDto.getPlatAreaCode());
            updateParkingAreaResultDto.success();
        } catch (Exception e) {
            log.error("修改停车区域失败" + e.getMessage());
            updateParkingAreaResultDto.failed();
        }
        return updateParkingAreaResultDto;
    }
}
