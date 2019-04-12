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
import com.zoeeasy.cloud.pms.enums.PlateColorEnum;
import com.zoeeasy.cloud.pms.enums.SpecialTypeEnum;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingListGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingListGetResultDto;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import com.zoeeasy.cloud.pms.service.SpecialVehicleCrudService;
import com.zoeeasy.cloud.pms.specialvehicle.BlackListService;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.BlackListGetResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.BlackListPagedResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.validator.BlackListAddRequestDtoValidator;
import com.zoeeasy.cloud.pms.specialvehicle.validator.BlackListDeleteRequestDtoValidator;
import com.zoeeasy.cloud.pms.specialvehicle.validator.BlackListUpdateRequestDtoValidator;
import lombok.extern.slf4j.Slf4j;
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
 * Created by song on 2018/10/18.
 */
@Service("blackListService")
@Slf4j
public class BlackListServiceImpl implements BlackListService {

    @Autowired
    private SpecialVehicleCrudService specialVehicleCrudService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 添加黑名单
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addBlackList(@FluentValid(BlackListAddRequestDtoValidator.class) BlackListAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            SpecialVehicleEntity specialVehicleEntity = modelMapper.map(requestDto, SpecialVehicleEntity.class);
            specialVehicleEntity.setSpecialType(SpecialTypeEnum.BLACK_LIST.getValue());
            specialVehicleCrudService.insert(specialVehicleEntity);
            resultDto.success();
        } catch (Exception e) {
            log.error("添加黑名单失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 修改黑名单
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateBlackList(@FluentValid({BlackListUpdateRequestDtoValidator.class}) BlackListUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            SpecialVehicleEntity specialVehicleEntity = modelMapper.map(requestDto, SpecialVehicleEntity.class);
            EntityWrapper<SpecialVehicleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            specialVehicleCrudService.update(specialVehicleEntity, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("修改黑名单失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取黑名单详情
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<BlackListGetResultDto> getBlackList(BlackListGetRequestDto requestDto) {
        ObjectResultDto<BlackListGetResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            SpecialVehicleEntity specialVehicleEntity = specialVehicleCrudService.selectById(requestDto.getId());
            BlackListGetResultDto blackListGetResultDto = modelMapper.map(specialVehicleEntity, BlackListGetResultDto.class);
            if (specialVehicleEntity != null) {
                ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(specialVehicleEntity.getParkingId());
                if (parkingInfoEntity != null) {
                    blackListGetResultDto.setParkingName(parkingInfoEntity.getFullName());
                }
            }
            objectResultDto.setData(blackListGetResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取黑名单详情失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 删除黑名单失败
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto deleteBlackList(@FluentValid({BlackListDeleteRequestDtoValidator.class}) BlackListDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            specialVehicleCrudService.deleteById(requestDto.getId());
            resultDto.success();
        } catch (Exception e) {
            log.error("删除黑名单失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 分页获取黑名单
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<BlackListPagedResultDto> getBlackListPagedList(BlackListQueryPagedRequestDto requestDto) {
        PagedResultDto<BlackListPagedResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<SpecialVehicleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.orderBy("creationTime", false);
            entityWrapper.eq("specialType", SpecialTypeEnum.BLACK_LIST.getValue());
            if (requestDto.getPlateColor() != null) {
                entityWrapper.eq("plateColor", requestDto.getPlateColor());
            }
            if (!StringUtils.isEmpty(requestDto.getPlateNumber())) {
                entityWrapper.like("plateNumber", requestDto.getPlateNumber());
            }
            if (requestDto.getStatus() != null) {
                entityWrapper.eq("status", requestDto.getStatus());
            }
            List<Long> parkingIds = new ArrayList<>();
            ParkingListGetRequestDto parkingListGetRequestDto = new ParkingListGetRequestDto();
            if (!StringUtils.isEmpty(requestDto.getParkingName())) {
                parkingListGetRequestDto.setName(requestDto.getParkingName());
            }
            ListResultDto<ParkingListGetResultDto> list = parkingInfoService.getParkingList(parkingListGetRequestDto);
            if (!list.getItems().isEmpty()) {
                for (ParkingListGetResultDto parkingListGetResultDto : list.getItems()) {
                    parkingIds.add(parkingListGetResultDto.getId());
                }
            } else {
                pagedResultDto = new PagedResultDto(requestDto.getPageNo(), requestDto.getPageSize(), new ArrayList<>(), 0L);
                pagedResultDto.success();
                return pagedResultDto;
            }
            entityWrapper.in("parkingId", parkingIds);
            Page<SpecialVehicleEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<SpecialVehicleEntity> specialVehiclePageList = specialVehicleCrudService.selectPage(page, entityWrapper);
            List<BlackListPagedResultDto> blackListPagedResultDtos = new ArrayList<>();
            Map<Long, ParkingInfoEntity> map = new HashMap<>();
            for (SpecialVehicleEntity specialVehicleEntity : specialVehiclePageList.getRecords()) {
                BlackListPagedResultDto blackListPagedResultDto = new BlackListPagedResultDto();
                blackListPagedResultDto.setId(specialVehicleEntity.getId());
                blackListPagedResultDto.setPlateNumber(specialVehicleEntity.getPlateNumber());
                blackListPagedResultDto.setOwnerName(specialVehicleEntity.getOwnerName());
                blackListPagedResultDto.setOwnerPhone(specialVehicleEntity.getOwnerPhone());
                blackListPagedResultDto.setStatus(EffectedStatusEnum.parse(specialVehicleEntity.getStatus()).getComment());
                blackListPagedResultDto.setPlateColor(PlateColorEnum.parse(specialVehicleEntity.getPlateColor()).getComment());
                blackListPagedResultDto.setRemark(specialVehicleEntity.getRemark());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                blackListPagedResultDto.setValidTime(simpleDateFormat.format(specialVehicleEntity.getBeginTime()) + " 至 " +
                        simpleDateFormat.format(specialVehicleEntity.getEndTime()));
                setParkingName(map, blackListPagedResultDto, specialVehicleEntity.getParkingId());
                blackListPagedResultDtos.add(blackListPagedResultDto);
            }
            pagedResultDto.setPageNo(specialVehiclePageList.getCurrent());
            pagedResultDto.setPageSize(specialVehiclePageList.getSize());
            pagedResultDto.setTotalCount(specialVehiclePageList.getTotal());
            pagedResultDto.setItems(blackListPagedResultDtos);
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页获取黑名单失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 设置停车场name
     */
    private void setParkingName(Map<Long, ParkingInfoEntity> map, BlackListPagedResultDto blackListPagedResultDto, Long parkingId) {
        if (map.keySet().contains(parkingId)) {
            ParkingInfoEntity parkingInfoEntity = map.get(parkingId);
            blackListPagedResultDto.setParkingName(parkingInfoEntity.getFullName());
        } else {
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(parkingId);
            if (parkingInfoEntity != null) {
                blackListPagedResultDto.setParkingName(parkingInfoEntity.getFullName());
                map.put(parkingId, parkingInfoEntity);
            }
        }
    }
}
