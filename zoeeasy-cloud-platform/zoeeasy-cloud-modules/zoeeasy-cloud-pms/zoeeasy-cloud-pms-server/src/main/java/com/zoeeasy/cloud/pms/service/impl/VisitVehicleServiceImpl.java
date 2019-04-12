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
import com.zoeeasy.cloud.pms.specialvehicle.VisitVehicleService;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.VisitVehicleQueryPagedResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.VisitVehicleResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.validator.VisitVehicleAddRequestDtoValidator;
import com.zoeeasy.cloud.pms.specialvehicle.validator.VisitVehicleDeleteRequestDtoValidator;
import com.zoeeasy.cloud.pms.specialvehicle.validator.VisitVehicleUpdateRequestDtoValidator;
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
 * @date: 2018/10/18.
 * @author：zm
 */
@Service("visitVehicleService")
@Slf4j
public class VisitVehicleServiceImpl implements VisitVehicleService {

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private SpecialVehicleCrudService specialVehicleCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 添加访客车
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addVisitVehicle(@FluentValid(value = {VisitVehicleAddRequestDtoValidator.class}) VisitVehicleAddRequestDto requestDto) {

        ResultDto resultDto = new ResultDto();
        try {
            SpecialVehicleEntity specialVehicleEntity = modelMapper.map(requestDto, SpecialVehicleEntity.class);
            specialVehicleEntity.setSpecialType(SpecialTypeEnum.VISITOR_CAR.getValue());
            specialVehicleCrudService.insert(specialVehicleEntity);
            resultDto.success();
        } catch (Exception e) {
            log.error("添加访客车失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除访客车
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto deleteVisitVehicle(@FluentValid(value = {VisitVehicleDeleteRequestDtoValidator.class}) VisitVehicleDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            specialVehicleCrudService.deleteById(requestDto.getId());
            resultDto.success();
        } catch (Exception e) {
            log.error("删除访客车失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 修改访客车
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateVisitVehicle(@FluentValid(value = {VisitVehicleUpdateRequestDtoValidator.class}) VisitVehicleUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            SpecialVehicleEntity specialVehicleEntity = modelMapper.map(requestDto, SpecialVehicleEntity.class);
            EntityWrapper<SpecialVehicleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            specialVehicleCrudService.update(specialVehicleEntity, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("修改访客车失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 分页获取访客车
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<VisitVehicleQueryPagedResultDto> getVisitVehiclePagedList(VisitVehicleQueryPagedRequestDto requestDto) {
        PagedResultDto<VisitVehicleQueryPagedResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<SpecialVehicleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.orderBy("creationTime", false);
            List<Long> parkingIds = new ArrayList<>();
            entityWrapper.eq("specialType", SpecialTypeEnum.VISITOR_CAR.getValue());
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
            Page<SpecialVehicleEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<SpecialVehicleEntity> specialVehiclePageList = specialVehicleCrudService.selectPage(page, entityWrapper);
            List<VisitVehicleQueryPagedResultDto> visitVehicleQueryPagedResultDtoList = new ArrayList<>();
            Map<Long, ParkingInfoEntity> map = new HashMap<>();
            for (SpecialVehicleEntity specialVehicleEntity : specialVehiclePageList.getRecords()) {
                VisitVehicleQueryPagedResultDto visitVehicleQueryPagedResultDto = new VisitVehicleQueryPagedResultDto();
                visitVehicleQueryPagedResultDto.setId(specialVehicleEntity.getId());
                visitVehicleQueryPagedResultDto.setPlateColor(specialVehicleEntity.getPlateColor());
                visitVehicleQueryPagedResultDto.setPlateType(specialVehicleEntity.getPlateType());
                visitVehicleQueryPagedResultDto.setCarColor(specialVehicleEntity.getCarColor());
                visitVehicleQueryPagedResultDto.setCarType(specialVehicleEntity.getCarType());
                visitVehicleQueryPagedResultDto.setVisitType(specialVehicleEntity.getVisitType());
                visitVehicleQueryPagedResultDto.setPlateNumber(specialVehicleEntity.getPlateNumber());
                visitVehicleQueryPagedResultDto.setOwnerName(specialVehicleEntity.getOwnerName());
                visitVehicleQueryPagedResultDto.setOwnerPhone(specialVehicleEntity.getOwnerPhone());
                visitVehicleQueryPagedResultDto.setStatus(EffectedStatusEnum.parse(specialVehicleEntity.getStatus()).getComment());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                visitVehicleQueryPagedResultDto.setValidTime(simpleDateFormat.format(specialVehicleEntity.getBeginTime()) + " 至 " +
                        simpleDateFormat.format(specialVehicleEntity.getEndTime()));
                setParkingName(map, visitVehicleQueryPagedResultDto, specialVehicleEntity.getParkingId());
                visitVehicleQueryPagedResultDtoList.add(visitVehicleQueryPagedResultDto);
            }
            pagedResultDto.setPageNo(specialVehiclePageList.getCurrent());
            pagedResultDto.setPageSize(specialVehiclePageList.getSize());
            pagedResultDto.setTotalCount(specialVehiclePageList.getTotal());
            pagedResultDto.setItems(visitVehicleQueryPagedResultDtoList);
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页获取访客车列表失败" + e.getMessage());
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
    private void setParkingName(Map<Long, ParkingInfoEntity> map, VisitVehicleQueryPagedResultDto whiteListQueryPagedResultDto, Long parkingId) {
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

    /**
     * 获取访客车
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<VisitVehicleResultDto> getVisitVehicle(VisitVehicleGetRequestDto requestDto) {
        ObjectResultDto<VisitVehicleResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            SpecialVehicleEntity specialVehicleEntity = specialVehicleCrudService.selectById(requestDto.getId());
            VisitVehicleResultDto visitVehicleResultDto = modelMapper.map(specialVehicleEntity, VisitVehicleResultDto.class);
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(specialVehicleEntity.getParkingId());
            if (parkingInfoEntity != null) {
                visitVehicleResultDto.setParkingName(parkingInfoEntity.getFullName());
            }
            objectResultDto.setData(visitVehicleResultDto);
            objectResultDto.success();

        } catch (Exception e) {
            log.error("获取访客车失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }
}
