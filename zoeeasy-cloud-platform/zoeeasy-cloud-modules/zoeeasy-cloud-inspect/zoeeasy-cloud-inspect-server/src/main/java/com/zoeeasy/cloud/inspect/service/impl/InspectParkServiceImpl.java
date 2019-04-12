package com.zoeeasy.cloud.inspect.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.core.base.FundamentalRequestContext;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.zoeeasy.cloud.inspect.domain.ParkCollectorEntity;
import com.zoeeasy.cloud.inspect.domain.ParkInspectorEntity;
import com.zoeeasy.cloud.inspect.park.InspectParkService;
import com.zoeeasy.cloud.inspect.park.dto.request.ParkingByUserRequestDto;
import com.zoeeasy.cloud.inspect.platform.dto.result.ParkInspectorInfoResultDto;
import com.zoeeasy.cloud.inspect.platform.validator.ParkingByUserRequestDtoValidator;
import com.zoeeasy.cloud.inspect.service.ParkCollectorCrudService;
import com.zoeeasy.cloud.inspect.service.ParkInspectorCrudService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/11/21 0021
 */
@Service("inspectParkService")
@Slf4j
public class InspectParkServiceImpl implements InspectParkService {
    @Autowired
    private ParkInspectorCrudService parkInspectorCrudService;

    @Autowired
    private ParkCollectorCrudService parkCollectorCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 通过userId获取停车场
     *
     * @param requestDto ParkingByUserRequestDto
     * @return ParkInspectorInfoResultDto
     */
    @Override
    public ListResultDto<ParkInspectorInfoResultDto> getParkingByUserId(@FluentValid(ParkingByUserRequestDtoValidator.class) ParkingByUserRequestDto requestDto) {
        ListResultDto<ParkInspectorInfoResultDto> listResultDto = new ListResultDto<>();
        try {
            log.error("session：" + FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity());
            Long userId = FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity().getUserId();
            List<ParkInspectorInfoResultDto> item = new ArrayList<>();
            List<ParkInspectorInfoResultDto> parkInspectorInfoResultList;
            List<ParkInspectorInfoResultDto> parkInspectorInfoResultDtos = new ArrayList<>();
            log.error("userId：" + userId);
            //巡检员
            EntityWrapper<ParkInspectorEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("userId", userId);
            List<ParkInspectorEntity> parkingByUserId = parkInspectorCrudService.selectList(entityWrapper);
            if (CollectionUtils.isNotEmpty(parkingByUserId)) {
                item = modelMapper.map(parkingByUserId, new TypeToken<List<ParkInspectorInfoResultDto>>() {
                }.getType());
                parkInspectorInfoResultDtos.addAll(item);
            }
            //收费员
            EntityWrapper<ParkCollectorEntity> wrapper = new EntityWrapper<>();
            wrapper.eq("userId", userId);
            List<ParkCollectorEntity> parkCollectorEntities = parkCollectorCrudService.selectList(wrapper);
            if (CollectionUtils.isNotEmpty(parkCollectorEntities)) {
                parkInspectorInfoResultList = modelMapper.map(parkCollectorEntities, new TypeToken<List<ParkInspectorInfoResultDto>>() {
                }.getType());
                if (CollectionUtils.isNotEmpty(item)) {
                    parkInspectorInfoResultList.removeAll(item);
                }
                parkInspectorInfoResultDtos.addAll(parkInspectorInfoResultList);
            }
            listResultDto.setItems(parkInspectorInfoResultDtos);
            listResultDto.success();
        } catch (Exception e) {
            listResultDto.failed();
            log.error("通过userId获取停车场失败" + e.getMessage());
        }
        return listResultDto;
    }
}
