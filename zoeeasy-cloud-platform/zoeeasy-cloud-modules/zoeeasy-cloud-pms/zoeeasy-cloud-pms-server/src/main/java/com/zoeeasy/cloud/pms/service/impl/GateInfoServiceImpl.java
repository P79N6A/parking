package com.zoeeasy.cloud.pms.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.zoeeasy.cloud.pms.domain.ParkingGarageInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingGateInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.gate.GateInfoService;
import com.zoeeasy.cloud.pms.gate.dto.request.AddGateInfoRequestDto;
import com.zoeeasy.cloud.pms.gate.dto.request.UpdateGateInfoRequestDto;
import com.zoeeasy.cloud.pms.gate.dto.result.AddGateInfoResultDto;
import com.zoeeasy.cloud.pms.gate.dto.result.UpdateGateInfoResultDto;
import com.zoeeasy.cloud.pms.gate.validator.AddGateInfoRequestDtoValidator;
import com.zoeeasy.cloud.pms.gate.validator.UpdateGateInfoRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingGarageInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingGateInfoCrudService;
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
@Service("gateInfoService")
@Slf4j
public class GateInfoServiceImpl implements GateInfoService {

    @Autowired
    private ParkingGateInfoCrudService parkingGateInfoCrudService;

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private IdService idService;

    /**
     * 添加出入口
     *
     * @param requestDto
     * @return
     */
    @Override
    public AddGateInfoResultDto addGateInfo(@FluentValid(AddGateInfoRequestDtoValidator.class) AddGateInfoRequestDto requestDto) {
        AddGateInfoResultDto addGateInfoResultDto = new AddGateInfoResultDto();
        try {
            String code = String.valueOf(idService.genId());
            ParkingGateInfoEntity entity = parkingGateInfoCrudService.selectGateByCode(code);
            if (entity == null) {
                ParkingGateInfoEntity parkingGateInfoEntity = new ParkingGateInfoEntity();
                parkingGateInfoEntity.setTenantId(requestDto.getTenantId());
                parkingGateInfoEntity.setLocalCode(requestDto.getGateCode());
                parkingGateInfoEntity.setCode(code);
                parkingGateInfoEntity.setName(requestDto.getGateName());
                parkingGateInfoEntity.setDirection(requestDto.getDirection());
                parkingGateInfoEntity.setRemarks(requestDto.getRemark());
                //停车场
                ParkingInfoEntity infoEntity = parkingInfoCrudService.findByCodeAndTenantId(requestDto.getCloudParkingCode(), requestDto.getTenantId());
                parkingGateInfoEntity.setParkingId(infoEntity.getId());
                //车库
                if (StringUtils.isNotEmpty(requestDto.getCloudGarageCode())) {
                    ParkingGarageInfoEntity byCode = parkingGarageInfoCrudService.selectByCode(requestDto.getCloudGarageCode());
                    if (byCode != null) {
                        parkingGateInfoEntity.setGarageId(byCode.getId());
                    }
                }
                parkingGateInfoCrudService.insert(parkingGateInfoEntity);
                addGateInfoResultDto.setCloudCode(parkingGateInfoEntity.getCode());
                addGateInfoResultDto.success();
            } else {
                addGateInfoResultDto.failed();
            }
        } catch (Exception e) {
            log.error("添加出入口失败" + e.getMessage());
            addGateInfoResultDto.failed();
        }
        return addGateInfoResultDto;
    }

    /**
     * 修改出入口
     *
     * @param requestDto
     * @return
     */
    @Override
    public UpdateGateInfoResultDto updateGateInfo(@FluentValid(UpdateGateInfoRequestDtoValidator.class) UpdateGateInfoRequestDto requestDto) {
        UpdateGateInfoResultDto updateGateInfoResultDto = new UpdateGateInfoResultDto();
        try {
            ParkingGateInfoEntity parkingGateInfoEntity = new ParkingGateInfoEntity();
            parkingGateInfoEntity.setName(requestDto.getGateName());
            parkingGateInfoEntity.setDirection(requestDto.getDirection());
            parkingGateInfoEntity.setRemarks(requestDto.getRemark());

            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.findByCodeAndTenantId(requestDto.getCloudParkingCode(), requestDto.getTenantId());
            //车库
            if (StringUtils.isNotEmpty(requestDto.getCloudGarageCode())) {
                ParkingGarageInfoEntity byCode = parkingGarageInfoCrudService.findGarageByParkingIdAndCode(parkingInfoEntity.getId(), requestDto.getCloudGarageCode());
                if (byCode != null) {
                    parkingGateInfoEntity.setGarageId(byCode.getId());
                }
            }
            ParkingGateInfoEntity gate = parkingGateInfoCrudService.selectByParkingIdeAndCode(parkingInfoEntity.getId(), requestDto.getPlatGateCode());
            parkingGateInfoEntity.setId(gate.getId());
            parkingGateInfoEntity.setLastModificationTime(new Date());
            parkingGateInfoCrudService.updateParkingGateNonTenant(parkingGateInfoEntity);
            updateGateInfoResultDto.setCloudCode(requestDto.getPlatGateCode());
            updateGateInfoResultDto.success();
        } catch (Exception e) {
            log.error("修改出入口失败" + e.getMessage());
            updateGateInfoResultDto.failed();
        }
        return updateGateInfoResultDto;
    }
}
