package com.zoeeasy.cloud.pms.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.domain.SpecialVehicleEntity;
import com.zoeeasy.cloud.pms.enums.EffectedStatusEnum;
import com.zoeeasy.cloud.pms.enums.SpecialTypeEnum;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingListGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingListGetResultDto;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import com.zoeeasy.cloud.pms.service.SpecialVehicleCrudService;
import com.zoeeasy.cloud.pms.specialvehicle.WhiteListService;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.WhiteListQueryPagedResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.WhiteListResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.validator.WhiteListAddRequestDtoValidator;
import com.zoeeasy.cloud.pms.specialvehicle.validator.WhiteListDeleteRequestDtoValidator;
import com.zoeeasy.cloud.pms.specialvehicle.validator.WhiteListUpdateRequestDtoValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date: 2018/10/16.
 * @author：zm
 */
@Service("whiteListService")
@Slf4j
public class WhiteListServiceImpl implements WhiteListService {

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private SpecialVehicleCrudService specialVehicleCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 添加白名单
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addWhiteList(@FluentValid(value = {WhiteListAddRequestDtoValidator.class}) WhiteListAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            SpecialVehicleEntity specialVehicleEntity = modelMapper.map(requestDto, SpecialVehicleEntity.class);
            specialVehicleCrudService.insert(specialVehicleEntity);
            resultDto.success();
        } catch (Exception e) {
            log.error("添加白名单失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除白名单
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto deleteWhiteList(@FluentValid(value = {WhiteListDeleteRequestDtoValidator.class}) WhiteListDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            EntityWrapper<SpecialVehicleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            specialVehicleCrudService.delete(entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("删除白名单失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 修改白名单
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateWhiteList(@FluentValid(value = {WhiteListUpdateRequestDtoValidator.class}) WhiteListUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            SpecialVehicleEntity specialVehicleEntity = modelMapper.map(requestDto, SpecialVehicleEntity.class);
            EntityWrapper<SpecialVehicleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            specialVehicleCrudService.update(specialVehicleEntity, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("修改白名单失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取白名单
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<WhiteListResultDto> getWhiteList(WhiteListGetRequestDto requestDto) {
        ObjectResultDto<WhiteListResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            SpecialVehicleEntity specialVehicleEntity = specialVehicleCrudService.selectById(requestDto.getId());
            WhiteListResultDto whiteListResultDto = modelMapper.map(specialVehicleEntity, WhiteListResultDto.class);
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(specialVehicleEntity.getParkingId());
            if (parkingInfoEntity != null) {
                whiteListResultDto.setParkingName(parkingInfoEntity.getFullName());
            }
            objectResultDto.setData(whiteListResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取白名单失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 分页获取白名单列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<WhiteListQueryPagedResultDto> getWhiteListPagedList(WhiteListQueryPagedRequestDto requestDto) {
        PagedResultDto<WhiteListQueryPagedResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<SpecialVehicleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.orderBy("creationTime", false);
            List<Long> parkingIds = new ArrayList<>();
            entityWrapper.eq("specialType", SpecialTypeEnum.WHITE_LIST.getValue());
            ParkingListGetRequestDto parkingEListGetRequestDto = new ParkingListGetRequestDto();
            if (StringUtils.isNotEmpty(requestDto.getAreaCode())) {
                parkingEListGetRequestDto.setAreaCode(requestDto.getAreaCode());
            }
            if (!StringUtils.isEmpty(requestDto.getParkingName())) {
                parkingEListGetRequestDto.setName(requestDto.getParkingName());
            }
            ListResultDto<ParkingListGetResultDto> list = parkingInfoService.getParkingList(parkingEListGetRequestDto);
            for (ParkingListGetResultDto parkingListGetResultDto : list.getItems()) {
                parkingIds.add(parkingListGetResultDto.getId());
            }
            if (CollectionUtils.isEmpty(parkingIds)) {
                pagedResultDto = new PagedResultDto(requestDto.getPageNo(), requestDto.getPageSize(), new ArrayList<>(), 0L);
                pagedResultDto.success();
                return pagedResultDto;
            }
            entityWrapper.in("parkingId", parkingIds);
            if (!StringUtils.isEmpty(requestDto.getPlateNumber())) {
                entityWrapper.like("plateNumber", requestDto.getPlateNumber());
            }
            if (requestDto.getStatus() != null) {
                entityWrapper.eq("status", requestDto.getStatus());
            }
            Page<SpecialVehicleEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<SpecialVehicleEntity> specialVehiclePageList = specialVehicleCrudService.selectPage(page, entityWrapper);
            Map<Long, ParkingInfoEntity> map = new HashMap<>();
            List<WhiteListQueryPagedResultDto> whiteListQueryPagedResultDtoList = new ArrayList<>();
            for (SpecialVehicleEntity specialVehicleEntity : specialVehiclePageList.getRecords()) {
                WhiteListQueryPagedResultDto whiteListQueryPagedResultDto = new WhiteListQueryPagedResultDto();
                whiteListQueryPagedResultDto.setId(specialVehicleEntity.getId());
                whiteListQueryPagedResultDto.setPlateColor(specialVehicleEntity.getPlateColor());
                whiteListQueryPagedResultDto.setPlateType(specialVehicleEntity.getPlateType());
                whiteListQueryPagedResultDto.setCarColor(specialVehicleEntity.getCarColor());
                whiteListQueryPagedResultDto.setCarType(specialVehicleEntity.getCarType());
                whiteListQueryPagedResultDto.setPlateNumber(specialVehicleEntity.getPlateNumber());
                whiteListQueryPagedResultDto.setOwnerName(specialVehicleEntity.getOwnerName());
                whiteListQueryPagedResultDto.setOwnerPhone(specialVehicleEntity.getOwnerPhone());
                whiteListQueryPagedResultDto.setStatus(EffectedStatusEnum.parse(specialVehicleEntity.getStatus()).getComment());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                whiteListQueryPagedResultDto.setValidTime(simpleDateFormat.format(specialVehicleEntity.getBeginTime()) + " 至 " +
                        simpleDateFormat.format(specialVehicleEntity.getEndTime()));
                setParkingName(map, whiteListQueryPagedResultDto, specialVehicleEntity.getParkingId());
                whiteListQueryPagedResultDtoList.add(whiteListQueryPagedResultDto);
            }
            pagedResultDto.setPageNo(specialVehiclePageList.getCurrent());
            pagedResultDto.setPageSize(specialVehiclePageList.getSize());
            pagedResultDto.setTotalCount(specialVehiclePageList.getTotal());
            pagedResultDto.setItems(whiteListQueryPagedResultDtoList);
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页获取白名单列表失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 获取停车场名称
     *
     * @param map
     * @param whiteListQueryPagedResultDto
     * @param parkingId
     */
    private void setParkingName(Map<Long, ParkingInfoEntity> map, WhiteListQueryPagedResultDto whiteListQueryPagedResultDto, Long parkingId) {
        if (map.keySet().contains(parkingId)) {
            ParkingInfoEntity parkingInfoEntity = map.get(parkingId);
            whiteListQueryPagedResultDto.setParkingName(parkingInfoEntity.getFullName());
        } else {
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(parkingId);
            if (parkingInfoEntity != null) {
                whiteListQueryPagedResultDto.setParkingName(parkingInfoEntity.getFullName());
                map.put(parkingInfoEntity.getId(), parkingInfoEntity);
            }
        }
    }

}
