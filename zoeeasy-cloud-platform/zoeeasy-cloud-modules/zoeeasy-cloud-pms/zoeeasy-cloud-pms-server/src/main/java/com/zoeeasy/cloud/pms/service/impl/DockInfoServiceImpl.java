package com.zoeeasy.cloud.pms.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.pms.dock.DockInfoService;
import com.zoeeasy.cloud.pms.dock.cts.DockConstant;
import com.zoeeasy.cloud.pms.dock.dto.request.DockInfoGetByIdRequestDto;
import com.zoeeasy.cloud.pms.dock.dto.request.RegisterParkingRequestDto;
import com.zoeeasy.cloud.pms.dock.dto.result.DockInfoResultDto;
import com.zoeeasy.cloud.pms.dock.validator.RegisterParkingRequestDtoValidator;
import com.zoeeasy.cloud.pms.domain.DockInfoEntity;
import com.zoeeasy.cloud.pms.garage.dto.result.CloudResultDto;
import com.zoeeasy.cloud.pms.service.DockInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import com.zoeeasy.cloud.tool.vesta.intf.IdService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 场库系统对接服务
 *
 * @author walkman
 */
@Service("dockInfoService")
@Slf4j
public class DockInfoServiceImpl implements DockInfoService {

    @Autowired
    private DockInfoCrudService dockInfoCrudService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IdService idService;

    /**
     * 客户端系统注册
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CloudResultDto registerParkingSystem(@FluentValid({RegisterParkingRequestDtoValidator.class}) RegisterParkingRequestDto requestDto) {
        CloudResultDto cloudResultDto = new CloudResultDto();
        try {
            if (null != requestDto) {
                EntityWrapper<DockInfoEntity> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("terminateCode", requestDto.getTerminateCode());
                entityWrapper.eq("terminateVersion", requestDto.getTerminateCode());
                DockInfoEntity dockInfo = dockInfoCrudService.findOne(entityWrapper);
                if (dockInfo != null) {
                    //该客户端已经注册
                    cloudResultDto.failed(DockConstant.CLIENT_EXIST);
                    return cloudResultDto;
                }
                String platformCode = String.valueOf(idService.genId());
                DockInfoEntity dockInfoEntity = buildDockInfoEntity(requestDto);
                dockInfoEntity.setPlatformCode(platformCode);
                dockInfoEntity.setPlatformName("逐一科技");
                if (StringUtils.isNotBlank(requestDto.getStartTime())) {
                    Date startTime = DateTimeUtils.parseDate(requestDto.getStartTime(), Const.FORMAT_DATETIME);
                    if (null != startTime) {
                        dockInfoEntity.setStartTime(startTime);
                    } else {
                        cloudResultDto.failed(DockConstant.TOKEN_START_TIME_NOT_STANDARD);
                        return cloudResultDto;
                    }
                }
                if (StringUtils.isNotBlank(requestDto.getEndTime())) {
                    Date endTIme = DateTimeUtils.parseDate(requestDto.getEndTime(), Const.FORMAT_DATETIME);
                    if (null != endTIme) {
                        dockInfoEntity.setEndTime(endTIme);
                    } else {
                        cloudResultDto.failed(DockConstant.TOKEN_END_TIME_NOT_STANDARD);
                        return cloudResultDto;
                    }
                }
                boolean insert = dockInfoCrudService.save(dockInfoEntity);
                if (insert) {
                    cloudResultDto.setCloudCode(platformCode);
                }
            }
            cloudResultDto.success();
        } catch (Exception e) {
            log.error("客户端系统注册失败" + e.getMessage());
            cloudResultDto.failed();
        }
        return cloudResultDto;
    }

    private DockInfoEntity buildDockInfoEntity(RegisterParkingRequestDto requestDto) {
        DockInfoEntity dockInfoEntity = new DockInfoEntity();
        dockInfoEntity.setTerminateCode(requestDto.getTerminateCode());
        dockInfoEntity.setTerminateName(requestDto.getTerminateName());
        dockInfoEntity.setTerminateVersion(requestDto.getTerminateVersion());
        if (StringUtils.isNotBlank(requestDto.getUserName())) {
            dockInfoEntity.setUserName(requestDto.getUserName());
        }
        if (StringUtils.isNotBlank(requestDto.getPassword())) {
            dockInfoEntity.setPassword(requestDto.getPassword());
        }
        if (StringUtils.isNotBlank(requestDto.getToken())) {
            dockInfoEntity.setToken(requestDto.getToken());
        }
        if (requestDto.getNetZoneType() != null) {
            dockInfoEntity.setNetZoneType(requestDto.getNetZoneType());
        }
        dockInfoEntity.setPlatformIp(requestDto.getPlatformIp());
        dockInfoEntity.setPlatformPort(requestDto.getPlatformPort());
        if (StringUtils.isNotBlank(requestDto.getProtocolVersion())) {
            dockInfoEntity.setProtocolVersion(requestDto.getProtocolVersion());
        }
        dockInfoEntity.setPlaceOrderUrl(requestDto.getPlaceOrderUrl());
        dockInfoEntity.setNotifyOrderUrl(requestDto.getNotifyOrderUrl());
        dockInfoEntity.setOpenStrobeUrl(requestDto.getOpenStrobeUrl());
        dockInfoEntity.setHeartBeatInterval(requestDto.getHeartBeatInterval());
        return dockInfoEntity;
    }

    /**
     * 无租户
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<DockInfoResultDto> getDockById(DockInfoGetByIdRequestDto requestDto) {
        ObjectResultDto<DockInfoResultDto> resultDto = new ObjectResultDto<>();
        try {
            DockInfoEntity dockInfoEntity = dockInfoCrudService.findById(requestDto.getId());
            if (dockInfoEntity != null) {
                DockInfoResultDto map = modelMapper.map(dockInfoEntity, DockInfoResultDto.class);
                resultDto.setData(map);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("获取客户端注册信息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }
}
