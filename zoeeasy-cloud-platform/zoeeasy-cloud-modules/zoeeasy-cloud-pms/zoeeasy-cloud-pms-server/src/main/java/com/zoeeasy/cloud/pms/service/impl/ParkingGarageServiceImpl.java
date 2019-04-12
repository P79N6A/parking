package com.zoeeasy.cloud.pms.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.domain.ParkingGarageInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.enums.PmsResultEnum;
import com.zoeeasy.cloud.pms.garage.ParkingGarageService;
import com.zoeeasy.cloud.pms.garage.dto.request.*;
import com.zoeeasy.cloud.pms.garage.dto.result.GarageListGetResultDto;
import com.zoeeasy.cloud.pms.garage.dto.result.GarageQueryPagedResultDto;
import com.zoeeasy.cloud.pms.garage.dto.result.GarageResultDto;
import com.zoeeasy.cloud.pms.garage.validator.GarageAddRequestDtoValidator;
import com.zoeeasy.cloud.pms.garage.validator.GarageUpdateRequestDtoValidator;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingListGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingListGetResultDto;
import com.zoeeasy.cloud.pms.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车库管理服务
 * Created by song on 2018/9/20.
 */
@Service("parkingGarageService")
@Slf4j
public class ParkingGarageServiceImpl implements ParkingGarageService {

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Autowired
    private ParkingAreaCrudService parkingAreaCrudService;

    @Autowired
    private ParkingGateInfoCrudService parkingGateInfoCrudService;

    @Autowired
    private ParkingLaneInfoCrudService parkingLaneInfoCrudService;

    @Autowired
    private ParkingLotInfoCrudService parkingLotInfoCrudService;

    @Autowired
    private ParkingLotStatusCrudService parkingLotStatusCrudService;

    @Autowired
    private AreaCrudService areaCrudService;

    @Autowired
    private ParkingFloorCrudService parkingFloorCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 添加车库
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addGarage(@FluentValid({GarageAddRequestDtoValidator.class}) GarageAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingGarageInfoEntity parkingGarageInfoEntity = modelMapper.map(requestDto, ParkingGarageInfoEntity.class);
            parkingGarageInfoCrudService.insert(parkingGarageInfoEntity);
            resultDto.success();
        } catch (Exception e) {
            log.error("添加车库失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 修改车库
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateGarage(@FluentValid({GarageUpdateRequestDtoValidator.class}) GarageUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingGarageInfoEntity parkingGarageInfoEntity = modelMapper.map(requestDto, ParkingGarageInfoEntity.class);
            EntityWrapper<ParkingGarageInfoEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            parkingGarageInfoCrudService.update(parkingGarageInfoEntity, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("修改车库失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除车库
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto deleteGarage(GarageDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            int laneGarageCount = parkingLaneInfoCrudService.findCountByGarageId(requestDto.getId());
            if (laneGarageCount > 0) {
                return resultDto.makeResult(PmsResultEnum.PARKING_GARAGE_NOT_DELETE.getValue(),
                        PmsResultEnum.PARKING_GARAGE_NOT_DELETE.getComment());
            }
            int gateGarageCount = parkingGateInfoCrudService.findCountByGarageId(requestDto.getId());
            if (gateGarageCount > 0) {
                return resultDto.makeResult(PmsResultEnum.PARKING_GARAGE_NOT_DELETE.getValue(),
                        PmsResultEnum.PARKING_GARAGE_NOT_DELETE.getComment());
            }
            int parkingAreaGarageCount = parkingAreaCrudService.findCountByGarageId(requestDto.getId());
            if (parkingAreaGarageCount > 0) {
                return resultDto.makeResult(PmsResultEnum.PARKING_GARAGE_NOT_DELETE.getValue(),
                        PmsResultEnum.PARKING_GARAGE_NOT_DELETE.getComment());
            }
            int parkingLotGarageCount = parkingLotInfoCrudService.findCountByGarageId(requestDto.getId());
            if (parkingLotGarageCount > 0) {
                return resultDto.makeResult(PmsResultEnum.PARKING_GARAGE_NOT_DELETE.getValue(),
                        PmsResultEnum.PARKING_GARAGE_NOT_DELETE.getComment());
            }
            int floorGarageCount = parkingFloorCrudService.findCountByGarageId(requestDto.getId());
            if (floorGarageCount > 0) {
                return resultDto.makeResult(PmsResultEnum.PARKING_GARAGE_NOT_DELETE.getValue(),
                        PmsResultEnum.PARKING_GARAGE_NOT_DELETE.getComment());
            }
            EntityWrapper<ParkingGarageInfoEntity> garageWrapper = new EntityWrapper<>();
            garageWrapper.eq("id", requestDto.getId());
            parkingGarageInfoCrudService.delete(garageWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("删除车库失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取车库
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<GarageResultDto> getGarage(GarageGetRequestDto requestDto) {
        ObjectResultDto<GarageResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingGarageInfoEntity parkingGarageInfoEntity = parkingGarageInfoCrudService.selectById(requestDto.getId());
            if (parkingGarageInfoEntity != null) {
                GarageResultDto garageResultDto = modelMapper.map(parkingGarageInfoEntity, GarageResultDto.class);
                ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(parkingGarageInfoEntity.getParkingId());
                if (parkingInfoEntity != null) {
                    garageResultDto.setParkingName(parkingInfoEntity.getFullName());
                }
                objectResultDto.setData(garageResultDto);
                objectResultDto.success();
            } else {
                return objectResultDto.makeResult(PmsResultEnum.PARKING_GARAGE_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_GARAGE_NOT_FOUND.getComment());
            }
        } catch (Exception e) {
            log.error("获取车库失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 分页获取车库列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<GarageQueryPagedResultDto> getGaragePagedList(GarageQueryPagedRequestDto requestDto) {
        PagedResultDto<GarageQueryPagedResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<ParkingGarageInfoEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.orderBy("creationTime", false);
            List<Long> parkingIds = new ArrayList<>();
            Page<ParkingGarageInfoEntity> garagePage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            ParkingListGetRequestDto parkingListGetRequestDto = new ParkingListGetRequestDto();
            if (!StringUtils.isEmpty(requestDto.getAreaCode())) {
                parkingListGetRequestDto.setAreaCode(requestDto.getAreaCode());
            }
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
            if (!StringUtils.isEmpty(requestDto.getName())) {
                entityWrapper.like("name", requestDto.getName());
            }
            if (!StringUtils.isEmpty(requestDto.getCode())) {
                entityWrapper.like("code", requestDto.getCode());
            }
            Page<ParkingGarageInfoEntity> garagePageList = parkingGarageInfoCrudService.selectPage(garagePage, entityWrapper);
            List<GarageQueryPagedResultDto> garages = new ArrayList<>();
            Map<Long, ParkingInfoEntity> map = new HashMap();
            for (ParkingGarageInfoEntity parkingGarageInfoEntity : garagePageList.getRecords()) {
                GarageQueryPagedResultDto garageQueryPagedResultDto = new GarageQueryPagedResultDto();
                garageQueryPagedResultDto.setId(parkingGarageInfoEntity.getId());
                garageQueryPagedResultDto.setCode(parkingGarageInfoEntity.getCode());
                garageQueryPagedResultDto.setName(parkingGarageInfoEntity.getName());
                garageQueryPagedResultDto.setLotCount(parkingGarageInfoEntity.getLotCount());
                garageQueryPagedResultDto.setLotFixed(parkingGarageInfoEntity.getLotFixed());
                garageQueryPagedResultDto.setLotAvailable(parkingGarageInfoEntity.getLotAvailable());
                setParkingNameAndCode(map, garageQueryPagedResultDto, parkingGarageInfoEntity.getParkingId());
                garages.add(garageQueryPagedResultDto);
            }
            pagedResultDto.setPageNo(garagePageList.getCurrent());
            pagedResultDto.setPageSize(garagePageList.getSize());
            pagedResultDto.setTotalCount(garagePageList.getTotal());
            pagedResultDto.setItems(garages);
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页获取车库列表失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 设置停车场code和name
     */
    private void setParkingNameAndCode(Map<Long, ParkingInfoEntity> map, GarageQueryPagedResultDto garageQueryPagedResultDto, Long parkingId) {
        if (map.keySet().contains(parkingId)) {
            ParkingInfoEntity parkingInfoEntity = map.get(parkingId);
            garageQueryPagedResultDto.setParkingName(parkingInfoEntity.getFullName());
            garageQueryPagedResultDto.setParkingCode(parkingInfoEntity.getCode());
        } else {
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(parkingId);
            if (parkingInfoEntity != null) {
                garageQueryPagedResultDto.setParkingName(parkingInfoEntity.getFullName());
                garageQueryPagedResultDto.setParkingCode(parkingInfoEntity.getCode());
                map.put(parkingId, parkingInfoEntity);
            }
        }
    }

    /**
     * 获取车库列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<GarageListGetResultDto> getGarageList(GarageListGetRequestDto requestDto) {
        ListResultDto<GarageListGetResultDto> listResultDto = new ListResultDto<>();
        try {
            EntityWrapper<ParkingGarageInfoEntity> entityWrapper = new EntityWrapper<>();
            if (requestDto.getParkingId() != null) {
                entityWrapper.eq("parkingId", requestDto.getParkingId());
            }
            if (!StringUtils.isEmpty(requestDto.getName())) {
                entityWrapper.like("name", requestDto.getName());
            }
            List<ParkingGarageInfoEntity> parkingGarages = parkingGarageInfoCrudService.selectList(entityWrapper);
            if (!parkingGarages.isEmpty()) {
                List<GarageListGetResultDto> garageDtoList = modelMapper.map(parkingGarages, new TypeToken<List<GarageListGetResultDto>>() {
                }.getType());
                listResultDto.setItems(garageDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取车库列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }
}
