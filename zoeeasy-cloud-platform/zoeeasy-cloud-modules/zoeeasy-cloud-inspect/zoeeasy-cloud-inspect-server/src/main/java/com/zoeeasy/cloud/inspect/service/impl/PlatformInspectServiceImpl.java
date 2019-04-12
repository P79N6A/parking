package com.zoeeasy.cloud.inspect.service.impl;

import com.scapegoat.infrastructure.core.base.FundamentalRequestContext;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.inspect.domain.ParkCollectorEntity;
import com.zoeeasy.cloud.inspect.domain.ParkInspectorEntity;
import com.zoeeasy.cloud.inspect.platform.PlatformInspectService;
import com.zoeeasy.cloud.inspect.platform.dto.request.ParkCollectorGetByParkingIdRequestDto;
import com.zoeeasy.cloud.inspect.platform.dto.request.ParkCollectorGetByUserIdRequestDto;
import com.zoeeasy.cloud.inspect.platform.dto.request.ParkInspectorGetByParkingIdRequestDto;
import com.zoeeasy.cloud.inspect.platform.dto.request.ParkInspectorGetByUserIdRequestDto;
import com.zoeeasy.cloud.inspect.platform.dto.result.ParkCollectorInfoResultDto;
import com.zoeeasy.cloud.inspect.platform.dto.result.ParkInspectorInfoResultDto;
import com.zoeeasy.cloud.inspect.service.ParkCollectorCrudService;
import com.zoeeasy.cloud.inspect.service.ParkInspectorCrudService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/11/5 0005
 */
@Service("platformInspectService")
@Slf4j
public class PlatformInspectServiceImpl implements PlatformInspectService {

    @Autowired
    private ParkInspectorCrudService parkInspectorCrudService;

    @Autowired
    private ParkCollectorCrudService parkCollectorCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 通过停车场获取巡检id
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ParkInspectorInfoResultDto> getParkInspectorByParkingId(ParkInspectorGetByParkingIdRequestDto requestDto) {
        ListResultDto<ParkInspectorInfoResultDto> objectResultDto = new ListResultDto<>();
        try {
            List<ParkInspectorEntity> parkInspectorEntitys =
                    parkInspectorCrudService.findByParking(requestDto.getParkingId(),
                            requestDto.getTenantId());
            if (CollectionUtils.isNotEmpty(parkInspectorEntitys)) {
                List<ParkInspectorInfoResultDto> parkInspectorInfoResultDtos = modelMapper.map(parkInspectorEntitys,
                        new TypeToken<List<ParkInspectorInfoResultDto>>() {
                        }.getType());
                objectResultDto.setItems(parkInspectorInfoResultDtos);
            }
            objectResultDto.success();
        } catch (Exception e) {
            objectResultDto.failed();
            log.error("通过停车场获取巡检id失败" + e.getMessage());
        }
        return objectResultDto;
    }

    /**
     * 通过停车场获取收费员id
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ParkCollectorInfoResultDto> getParkCollectorByParkingId(ParkCollectorGetByParkingIdRequestDto requestDto) {
        ListResultDto<ParkCollectorInfoResultDto> objectResultDto = new ListResultDto<>();
        try {
            List<ParkCollectorEntity> parkCollectorEntities =
                    parkCollectorCrudService.findByParking(requestDto.getParkingId(),
                            requestDto.getTenantId());
            if (CollectionUtils.isNotEmpty(parkCollectorEntities)) {
                List<ParkCollectorInfoResultDto> parkCollectorInfoResultDtos = modelMapper.map(parkCollectorEntities,
                        new TypeToken<List<ParkCollectorInfoResultDto>>() {
                        }.getType());
                objectResultDto.setItems(parkCollectorInfoResultDtos);
            }
            objectResultDto.success();
        } catch (Exception e) {
            objectResultDto.failed();
            log.error("通过停车场获取巡检id失败" + e.getMessage());
        }
        return objectResultDto;
    }

    /**
     * 通过userId获取巡检
     *
     * @param requestDto
     */
    @Override
    public ObjectResultDto<ParkInspectorInfoResultDto> getParkInspectorByUserId(ParkInspectorGetByUserIdRequestDto requestDto) {
        ObjectResultDto<ParkInspectorInfoResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkInspectorEntity parkInspectorEntity =
                    parkInspectorCrudService.findByUserId(FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity().getUserId());
            if (null != parkInspectorEntity) {
                ParkInspectorInfoResultDto parkInspectorInfoResultDto = modelMapper.map(parkInspectorEntity, ParkInspectorInfoResultDto.class);
                objectResultDto.setData(parkInspectorInfoResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            objectResultDto.failed();
            log.error("无租户通过userId获取巡检失败" + e.getMessage());
        }
        return objectResultDto;
    }

    /**
     * 通过userId获取收费员
     *
     * @param requestDto
     */
    @Override
    public ObjectResultDto<ParkCollectorInfoResultDto> getParkCollectorByUserId(ParkCollectorGetByUserIdRequestDto requestDto) {
        ObjectResultDto<ParkCollectorInfoResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkCollectorEntity parkCollectorEntity =
                    parkCollectorCrudService.findByUserId(FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity().getUserId());
            if (null != parkCollectorEntity) {
                ParkCollectorInfoResultDto parkCollectorInfoResultDto = modelMapper.map(parkCollectorEntity, ParkCollectorInfoResultDto.class);
                objectResultDto.setData(parkCollectorInfoResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            objectResultDto.failed();
            log.error("无租户通过userId获取收费员失败" + e.getMessage());
        }
        return objectResultDto;
    }

}
