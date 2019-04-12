package com.zoeeasy.cloud.pms.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.zoeeasy.cloud.pms.domain.ParkingGarageInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.garage.GarageService;
import com.zoeeasy.cloud.pms.garage.dto.request.AddGarageInfoRequestDto;
import com.zoeeasy.cloud.pms.garage.dto.request.UpdateGarageInfoRequestDto;
import com.zoeeasy.cloud.pms.garage.dto.result.AddGarageInfoResultDto;
import com.zoeeasy.cloud.pms.garage.dto.result.UpdateGarageInfoResultDto;
import com.zoeeasy.cloud.pms.garage.validator.AddGarageInfoRequestDtoValidator;
import com.zoeeasy.cloud.pms.garage.validator.UpdateGarageInfoRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingGarageInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import com.zoeeasy.cloud.tool.vesta.intf.IdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Date: 2018/11/30
 * @author: lhj
 */
@Service("garageService")
@Slf4j
public class GarageServiceImpl implements GarageService {

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private IdService idService;

    /**
     * 添加车库
     *
     * @param requestDto
     * @return
     */
    @Override
    public AddGarageInfoResultDto addGarageInfo(@FluentValid(AddGarageInfoRequestDtoValidator.class) AddGarageInfoRequestDto requestDto) {
        AddGarageInfoResultDto addGarageInfoResultDto = new AddGarageInfoResultDto();
        try {
            //云平台code
            String code = String.valueOf(idService.genId());
            //车库编号唯一
            ParkingGarageInfoEntity parkingGarageInfoCrudServiceByCode = parkingGarageInfoCrudService.selectByCode(code);
            if (parkingGarageInfoCrudServiceByCode == null) {
                ParkingGarageInfoEntity parkingGarageInfoEntity = new ParkingGarageInfoEntity();
                parkingGarageInfoEntity.setTenantId(requestDto.getTenantId());
                parkingGarageInfoEntity.setLotCount(requestDto.getLotTotal());
                parkingGarageInfoEntity.setLotAvailable(requestDto.getLotAvailable());
                parkingGarageInfoEntity.setGateCount(requestDto.getPortNumber());
                //客户端code
                parkingGarageInfoEntity.setLocalCode(requestDto.getGarageCode());
                parkingGarageInfoEntity.setCode(code);
                parkingGarageInfoEntity.setLotFixed(requestDto.getLotFixed());
                parkingGarageInfoEntity.setName(requestDto.getGarageName());
                parkingGarageInfoEntity.setRemark(requestDto.getRemark());
                ParkingInfoEntity infoEntity = parkingInfoCrudService.findByCodeAndTenantId(requestDto.getCloudParkingCode(), requestDto.getTenantId());
                parkingGarageInfoEntity.setParkingId(infoEntity.getId());
                parkingGarageInfoCrudService.insert(parkingGarageInfoEntity);
                addGarageInfoResultDto.setCloudCode(parkingGarageInfoEntity.getCode());
                addGarageInfoResultDto.success();
            } else {
                addGarageInfoResultDto.failed();
            }
        } catch (Exception e) {
            log.error("添加车库失败" + e.getMessage());
            addGarageInfoResultDto.failed();
        }
        return addGarageInfoResultDto;
    }

    /**
     * 修改车库信息
     *
     * @param requestDto
     * @return
     */
    @Override
    public UpdateGarageInfoResultDto updateGarageInfo(@FluentValid(UpdateGarageInfoRequestDtoValidator.class) UpdateGarageInfoRequestDto requestDto) {
        UpdateGarageInfoResultDto updateGarageInfoResultDto = new UpdateGarageInfoResultDto();
        try {
            ParkingGarageInfoEntity parkingGarageInfoEntity = new ParkingGarageInfoEntity();
            parkingGarageInfoEntity.setLotCount(requestDto.getLotTotal());
            parkingGarageInfoEntity.setLotAvailable(requestDto.getLotAvailable());
            parkingGarageInfoEntity.setGateCount(requestDto.getPortNumber());
            parkingGarageInfoEntity.setLotFixed(requestDto.getLotFixed());
            parkingGarageInfoEntity.setName(requestDto.getGarageName());
            parkingGarageInfoEntity.setRemark(requestDto.getRemark());
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.findByCodeAndTenantId(requestDto.getCloudParkingCode(), requestDto.getTenantId());
            ParkingGarageInfoEntity garage = parkingGarageInfoCrudService.findGarageByParkingIdAndCode(parkingInfoEntity.getId(), requestDto.getCloudGarageCode());
            parkingGarageInfoEntity.setId(garage.getId());
            parkingGarageInfoEntity.setLastModificationTime(new Date());
            parkingGarageInfoCrudService.updateGarageNonTenant(parkingGarageInfoEntity);
            updateGarageInfoResultDto.setCloudCode(requestDto.getCloudGarageCode());
            updateGarageInfoResultDto.success();
        } catch (Exception e) {
            log.error("修改车库信息失败" + e.getMessage());
            updateGarageInfoResultDto.failed();
        }
        return updateGarageInfoResultDto;
    }
}
