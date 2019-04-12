package com.zoeeasy.cloud.pms.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingLotInfoEntity;
import com.zoeeasy.cloud.pms.domain.SpecialVehicleEntity;
import com.zoeeasy.cloud.pms.enums.EffectedStatusEnum;
import com.zoeeasy.cloud.pms.enums.RelevanceEnum;
import com.zoeeasy.cloud.pms.enums.SpecialTypeEnum;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingInfoGetByIdRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingListGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingByIdResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingListGetResultDto;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingLotInfoCrudService;
import com.zoeeasy.cloud.pms.service.SpecialVehicleCrudService;
import com.zoeeasy.cloud.pms.specialvehicle.FixedVehicleService;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.FixedVehicleQueryPagedResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.FixedVehicleResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.validator.FixedVehicleAddRequestDtoValidator;
import com.zoeeasy.cloud.pms.specialvehicle.validator.FixedVehicleDeleteRequestDtoValidator;
import com.zoeeasy.cloud.pms.specialvehicle.validator.FixedVehicleUpdateRequestDtoValidator;
import com.zoeeasy.cloud.pms.specialvehicle.validator.RelevanceRequestDtoValidator;
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
 * @date: 2018/10/17.
 * @author：zm
 */
@Service("fixedVehicleService")
@Slf4j
public class FixedVehicleServiceImpl implements FixedVehicleService {

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private SpecialVehicleCrudService specialVehicleCrudService;

    @Autowired
    private ParkingLotInfoCrudService parkingLotInfoCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 添加固定车
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addFixedVehicle(@FluentValid(value = {FixedVehicleAddRequestDtoValidator.class}) FixedVehicleAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            SpecialVehicleEntity specialVehicleEntity = modelMapper.map(requestDto, SpecialVehicleEntity.class);
            specialVehicleEntity.setSpecialType(SpecialTypeEnum.FIXED_CAR.getValue());
            specialVehicleCrudService.insert(specialVehicleEntity);
            resultDto.success();
        } catch (Exception e) {
            log.error("添加固定车失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除固定车
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto deleteFixedVehicle(@FluentValid(value = {FixedVehicleDeleteRequestDtoValidator.class}) FixedVehicleDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            specialVehicleCrudService.deleteById(requestDto.getId());
            resultDto.success();
        } catch (Exception e) {
            log.error("删除固定车失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 关联泊位
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto relevanceParkingLotId(@FluentValid(value = {RelevanceRequestDtoValidator.class}) RelevanceParkingLotIdRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            SpecialVehicleEntity specialVehicleEntity = new SpecialVehicleEntity();
            specialVehicleEntity.setParkingLotId(requestDto.getParkingLotId());
            specialVehicleEntity.setParkingLotNumber(requestDto.getParkingLotNumber());
            EntityWrapper<SpecialVehicleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            specialVehicleCrudService.update(specialVehicleEntity, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("关联泊位失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 修改固定车
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateFixedVehicle(@FluentValid(value = {FixedVehicleUpdateRequestDtoValidator.class}) FixedVehicleUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            SpecialVehicleEntity specialVehicleEntity = modelMapper.map(requestDto, SpecialVehicleEntity.class);
            EntityWrapper<SpecialVehicleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            specialVehicleCrudService.update(specialVehicleEntity, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("修改固定车失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取固定车详情
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<FixedVehicleResultDto> getFixedVehicle(FixedVehicleGetRequestDto requestDto) {
        ObjectResultDto<FixedVehicleResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            SpecialVehicleEntity specialVehicleEntity = specialVehicleCrudService.selectById(requestDto.getId());
            FixedVehicleResultDto fixedVehicleResultDto = modelMapper.map(specialVehicleEntity, FixedVehicleResultDto.class);
            if (specialVehicleEntity.getParkingLotId() != null && !StringUtils.isEmpty(specialVehicleEntity.getParkingLotNumber())) {
                ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(specialVehicleEntity.getParkingId());
                fixedVehicleResultDto.setParkingName(parkingInfoEntity.getFullName());
                fixedVehicleResultDto.setParkingCode(parkingInfoEntity.getCode());
                ParkingInfoGetByIdRequestDto parkingInfoGetByIdRequestDto = new ParkingInfoGetByIdRequestDto();
                parkingInfoGetByIdRequestDto.setId(specialVehicleEntity.getParkingId());
                ObjectResultDto<ParkingByIdResultDto> parkingById = parkingInfoService.getParkingById(parkingInfoGetByIdRequestDto);
                if (parkingById.getData() != null) {
                    fixedVehicleResultDto.setAreaName(parkingById.getData().getAreaPath());
                }
                ParkingLotInfoEntity parkingLotInfoEntity = parkingLotInfoCrudService.selectById(specialVehicleEntity.getParkingLotId());
                fixedVehicleResultDto.setCode(parkingLotInfoEntity.getCode());
                fixedVehicleResultDto.setParkingLotNumber(parkingLotInfoEntity.getNumber());
                fixedVehicleResultDto.setRelevance(RelevanceEnum.YES.getValue());
            } else {
                fixedVehicleResultDto.setRelevance(RelevanceEnum.NO.getValue());

            }
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(specialVehicleEntity.getParkingId());
            if (parkingInfoEntity != null) {
                fixedVehicleResultDto.setParkingName(parkingInfoEntity.getFullName());
            }
            objectResultDto.setData(fixedVehicleResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取固定车失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 分页获取固定车列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<FixedVehicleQueryPagedResultDto> getFixedVehiclePagedList(FixedVehicleQueryPagedRequestDto requestDto) {
        PagedResultDto<FixedVehicleQueryPagedResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<SpecialVehicleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.orderBy("creationTime", false);
            List<Long> parkingIds = new ArrayList<>();
            entityWrapper.eq("specialType", SpecialTypeEnum.FIXED_CAR.getValue());
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
            if (requestDto.getFixedType() != null) {
                entityWrapper.eq("fixedType", requestDto.getFixedType());
            }
            Page<SpecialVehicleEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<SpecialVehicleEntity> specialVehiclePageList = specialVehicleCrudService.selectPage(page, entityWrapper);
            List<FixedVehicleQueryPagedResultDto> fixedVehicleQueryPagedResultDtoList = new ArrayList<>();
            for (SpecialVehicleEntity specialVehicleEntity : specialVehiclePageList.getRecords()) {
                FixedVehicleQueryPagedResultDto fixedVehicleQueryPagedResultDto = get(specialVehicleEntity);
                fixedVehicleQueryPagedResultDtoList.add(fixedVehicleQueryPagedResultDto);
            }
            pagedResultDto.setPageNo(specialVehiclePageList.getCurrent());
            pagedResultDto.setPageSize(specialVehiclePageList.getSize());
            pagedResultDto.setTotalCount(specialVehiclePageList.getTotal());
            pagedResultDto.setItems(fixedVehicleQueryPagedResultDtoList);
            pagedResultDto.success();
        } catch (Exception e) {
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 获取固定车信息
     *
     * @param specialVehicleEntity
     * @return
     */
    private FixedVehicleQueryPagedResultDto get(SpecialVehicleEntity specialVehicleEntity) {
        FixedVehicleQueryPagedResultDto fixedVehicleQueryPagedResultDto = new FixedVehicleQueryPagedResultDto();
        fixedVehicleQueryPagedResultDto.setId(specialVehicleEntity.getId());
        fixedVehicleQueryPagedResultDto.setPlateNumber(specialVehicleEntity.getPlateNumber());
        fixedVehicleQueryPagedResultDto.setPlateColor(specialVehicleEntity.getPlateColor());
        fixedVehicleQueryPagedResultDto.setPlateType(specialVehicleEntity.getPlateType());
        fixedVehicleQueryPagedResultDto.setCarColor(specialVehicleEntity.getCarColor());
        fixedVehicleQueryPagedResultDto.setCarType(specialVehicleEntity.getCarType());
        fixedVehicleQueryPagedResultDto.setFixedType(specialVehicleEntity.getFixedType());
        fixedVehicleQueryPagedResultDto.setOwnerName(specialVehicleEntity.getOwnerName());
        fixedVehicleQueryPagedResultDto.setOwnerPhone(specialVehicleEntity.getOwnerPhone());
        fixedVehicleQueryPagedResultDto.setStatus(EffectedStatusEnum.parse(specialVehicleEntity.getStatus()).getComment());
        fixedVehicleQueryPagedResultDto.setParkingId(specialVehicleEntity.getParkingId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        fixedVehicleQueryPagedResultDto.setValidTime(simpleDateFormat.format(specialVehicleEntity.getBeginTime()) + " 至 " +
                simpleDateFormat.format(specialVehicleEntity.getEndTime()));
        if (specialVehicleEntity.getParkingLotId() != null && !StringUtils.isEmpty(specialVehicleEntity.getParkingLotNumber())) {
            fixedVehicleQueryPagedResultDto.setRelevance(RelevanceEnum.YES.getComment());
        } else {
            fixedVehicleQueryPagedResultDto.setRelevance(RelevanceEnum.NO.getComment());
        }
        Map<Long, ParkingInfoEntity> map = new HashMap<>();
        if (map.containsKey(specialVehicleEntity.getParkingId())) {
            ParkingInfoEntity parkingInfoEntity = map.get(specialVehicleEntity.getParkingId());
            fixedVehicleQueryPagedResultDto.setParkingName(parkingInfoEntity.getFullName());
        } else {
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(specialVehicleEntity.getParkingId());
            if (parkingInfoEntity != null) {
                fixedVehicleQueryPagedResultDto.setParkingName(parkingInfoEntity.getFullName());
                map.put(parkingInfoEntity.getId(), parkingInfoEntity);
            }
        }
        return fixedVehicleQueryPagedResultDto;
    }
}