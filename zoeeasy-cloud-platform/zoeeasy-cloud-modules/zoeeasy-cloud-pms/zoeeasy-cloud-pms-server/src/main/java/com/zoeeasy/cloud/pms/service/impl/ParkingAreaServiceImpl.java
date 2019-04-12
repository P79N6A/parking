package com.zoeeasy.cloud.pms.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.domain.ParkingAreaEntity;
import com.zoeeasy.cloud.pms.domain.ParkingFloorEntity;
import com.zoeeasy.cloud.pms.domain.ParkingGarageInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.enums.PmsResultEnum;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingListGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingListGetResultDto;
import com.zoeeasy.cloud.pms.parkingarea.ParkingAreaService;
import com.zoeeasy.cloud.pms.parkingarea.dto.request.*;
import com.zoeeasy.cloud.pms.parkingarea.dto.result.ParkingAreaListResultDto;
import com.zoeeasy.cloud.pms.parkingarea.dto.result.ParkingAreaQueryPagedResultDto;
import com.zoeeasy.cloud.pms.parkingarea.dto.result.ParkingAreaResultDto;
import com.zoeeasy.cloud.pms.parkingarea.validator.ParkingAreaAddRequestDtoValidator;
import com.zoeeasy.cloud.pms.parkingarea.validator.ParkingAreaUpdateRequestDtoValidator;
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
 * 泊位区域管理服务
 * Created by song on 2018/9/21.
 */
@Service("parkingAreaService")
@Slf4j
public class ParkingAreaServiceImpl implements ParkingAreaService {

    @Autowired
    private ParkingAreaCrudService parkingAreaCrudService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Autowired
    private ParkingLotInfoCrudService parkingLotInfoCrudService;

    @Autowired
    private ParkingLotStatusCrudService parkingLotStatusCrudService;

    @Autowired
    private ParkingFloorCrudService parkingFloorCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 添加泊位区域
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addParkingArea(@FluentValid({ParkingAreaAddRequestDtoValidator.class}) ParkingAreaAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingAreaEntity parkingAreaEntity = modelMapper.map(requestDto, ParkingAreaEntity.class);
            parkingAreaCrudService.insert(parkingAreaEntity);
            resultDto.success();
        } catch (Exception e) {
            log.error("添加泊位区域失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除泊位区域
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto deleteParkingArea(ParkingAreaDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            int count = parkingLotInfoCrudService.findCountByParkingAreaId(requestDto.getId());
            if (count > 0) {
                return resultDto.makeResult(PmsResultEnum.PARKING_AREA_NOT_DELETE.getValue(), PmsResultEnum.PARKING_AREA_NOT_DELETE.getComment());
            }
            EntityWrapper<ParkingAreaEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            parkingAreaCrudService.delete(entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("删除泊位区域失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 修改泊位区域
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateParkingArea(@FluentValid({ParkingAreaUpdateRequestDtoValidator.class}) ParkingAreaUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingAreaEntity parkingAreaEntity = modelMapper.map(requestDto, ParkingAreaEntity.class);
            EntityWrapper<ParkingAreaEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            parkingAreaCrudService.update(parkingAreaEntity, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("修改泊位区域失败" + e.getMessage());
        }
        return resultDto;
    }

    /**
     * 分页获取泊位区域
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<ParkingAreaQueryPagedResultDto> getParkingAreaPagedList(ParkingAreaQueryPagedRequestDto requestDto) {
        PagedResultDto<ParkingAreaQueryPagedResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<ParkingAreaEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.orderBy("creationTime", false);
            List<Long> parkingIds = new ArrayList<>();
            Page<ParkingAreaEntity> parkingAreaPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());

            ParkingListGetRequestDto parkingEListGetRequestDto = new ParkingListGetRequestDto();
            if (requestDto.getAreaCode() != null) {
                parkingEListGetRequestDto.setAreaCode(requestDto.getAreaCode());
            }
            if (!StringUtils.isEmpty(requestDto.getParkingName())) {
                parkingEListGetRequestDto.setName(requestDto.getParkingName());
            }
            if (!StringUtils.isEmpty(requestDto.getLotType())) {
                parkingEListGetRequestDto.setLotType(requestDto.getLotType());
            }
            ListResultDto<ParkingListGetResultDto> list = parkingInfoService.getParkingList(parkingEListGetRequestDto);
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
            Page<ParkingAreaEntity> parkingAreaPageList = parkingAreaCrudService.selectPage(parkingAreaPage, entityWrapper);
            List<ParkingAreaQueryPagedResultDto> parkingAreaDtoList = new ArrayList<>();
            Map<Long, ParkingInfoEntity> map = new HashMap<>();
            for (ParkingAreaEntity parkingAreaEntity : parkingAreaPageList.getRecords()) {
                ParkingAreaQueryPagedResultDto parkingAreaQueryPagedResultDto = new ParkingAreaQueryPagedResultDto();
                parkingAreaQueryPagedResultDto.setId(parkingAreaEntity.getId());
                parkingAreaQueryPagedResultDto.setCode(parkingAreaEntity.getCode());
                parkingAreaQueryPagedResultDto.setName(parkingAreaEntity.getName());
                parkingAreaQueryPagedResultDto.setLotTotal(parkingAreaEntity.getLotTotal());
                parkingAreaQueryPagedResultDto.setLotFixed(parkingAreaEntity.getLotFixed());
                parkingAreaQueryPagedResultDto.setLotAvailable(parkingAreaEntity.getLotAvailable());
                setParkingNameAndCode(map, parkingAreaQueryPagedResultDto, parkingAreaEntity.getParkingId());
                parkingAreaDtoList.add(parkingAreaQueryPagedResultDto);
            }
            pagedResultDto.setPageNo(parkingAreaPageList.getCurrent());
            pagedResultDto.setPageSize(parkingAreaPageList.getSize());
            pagedResultDto.setTotalCount(parkingAreaPageList.getTotal());
            pagedResultDto.setItems(parkingAreaDtoList);

            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页获取泊位区域失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 设置停车场code和name
     */
    private void setParkingNameAndCode(Map<Long, ParkingInfoEntity> map, ParkingAreaQueryPagedResultDto parkingAreaQueryPagedResultDto, Long parkingId) {
        if (map.keySet().contains(parkingId)) {
            ParkingInfoEntity parkingInfoEntity = map.get(parkingId);
            parkingAreaQueryPagedResultDto.setParkingName(parkingInfoEntity.getFullName());
            parkingAreaQueryPagedResultDto.setParkingCode(parkingInfoEntity.getCode());
        } else {
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(parkingId);
            if (parkingInfoEntity != null) {
                parkingAreaQueryPagedResultDto.setParkingName(parkingInfoEntity.getFullName());
                parkingAreaQueryPagedResultDto.setParkingCode(parkingInfoEntity.getCode());
                map.put(parkingId, parkingInfoEntity);
            }
        }
    }

    /**
     * 获取泊位区域
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingAreaResultDto> getParkingArea(ParkingAreaGetRequestDto requestDto) {
        ObjectResultDto<ParkingAreaResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingAreaEntity parkingAreaEntity = parkingAreaCrudService.getParkingAreaInfo(requestDto.getId());
            if (parkingAreaEntity != null) {
                ParkingAreaResultDto parkingAreaResultDto = modelMapper.map(parkingAreaEntity, ParkingAreaResultDto.class);
                ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(parkingAreaEntity.getParkingId());
                if (parkingInfoEntity != null) {
                    parkingAreaResultDto.setParkingName(parkingInfoEntity.getFullName());
                }
                ParkingGarageInfoEntity parkingGarageInfoEntity = parkingGarageInfoCrudService.selectById(parkingAreaEntity.getGarageId());
                if (parkingGarageInfoEntity != null) {
                    parkingAreaResultDto.setGarageName(parkingGarageInfoEntity.getName());
                }
                ParkingFloorEntity parkingFloorEntity = parkingFloorCrudService.selectById(parkingAreaEntity.getFloorId());
                if (parkingFloorEntity != null){
                    parkingAreaResultDto.setFloorName(parkingFloorEntity.getFloorName());
                }
                objectResultDto.setData(parkingAreaResultDto);
                objectResultDto.success();
            } else {
                return objectResultDto.makeResult(PmsResultEnum.PARKING_AREA_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_AREA_NOT_FOUND.getComment());
            }
        } catch (Exception e) {
            log.error("获取泊位区域失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取泊车区域列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ParkingAreaListResultDto> getParkingAreaList(ParkingAreaListRequestDto requestDto) {
        ListResultDto<ParkingAreaListResultDto> listResultDto = new ListResultDto<>();
        try {
            EntityWrapper<ParkingAreaEntity> entityWrapper = new EntityWrapper<>();
            if (requestDto.getParkingId() != null) {
                entityWrapper.eq("parkingId", requestDto.getParkingId());
            }
            if (requestDto.getGarageId() != null) {
                entityWrapper.eq("garageId", requestDto.getGarageId());
            }
            if (!StringUtils.isEmpty(requestDto.getName())) {
                entityWrapper.like("name", requestDto.getName());
            }
            List<ParkingAreaEntity> parkingAreaEntities = parkingAreaCrudService.selectList(entityWrapper);
            if (!parkingAreaEntities.isEmpty()) {
                List<ParkingAreaListResultDto> parkingAreaDtoList = modelMapper.map(parkingAreaEntities, new TypeToken<List<ParkingAreaListResultDto>>() {
                }.getType());
                listResultDto.setItems(parkingAreaDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取泊车区域列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
    * @Description:    TODO
    * @Author:         qhxu
    * @CreateDate:     2019/3/23 16:29
    * @UpdateUser:     qhxu
    * @UpdateDate:     2019/3/23 16:29
    * @UpdateRemark:   修改内容
    * @Version:        1.0
    */
    @Override
    public ObjectResultDto<ParkingAreaResultDto> getParkingArea(ParkingAreaMyGetRequestDto requestDto) {
        ObjectResultDto<ParkingAreaResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingAreaEntity parkingAreaEntity = parkingAreaCrudService.selectByCode(requestDto.getParkingLotCode());
            if (parkingAreaEntity != null) {
                ParkingAreaResultDto parkingAreaResultDto = modelMapper.map(parkingAreaEntity, ParkingAreaResultDto.class);
                ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(parkingAreaEntity.getParkingId());
                if (parkingInfoEntity != null) {
                    parkingAreaResultDto.setParkingName(parkingInfoEntity.getFullName());
                }
                ParkingGarageInfoEntity parkingGarageInfoEntity = parkingGarageInfoCrudService.selectById(parkingAreaEntity.getGarageId());
                if (parkingGarageInfoEntity != null) {
                    parkingAreaResultDto.setGarageName(parkingGarageInfoEntity.getName());
                }
                objectResultDto.setData(parkingAreaResultDto);
                objectResultDto.success();
            } else {
                return objectResultDto.makeResult(PmsResultEnum.PARKING_AREA_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_AREA_NOT_FOUND.getComment());
            }
        } catch (Exception e) {
            log.error("获取泊位区域失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    @Override
    public ObjectResultDto<ParkingAreaResultDto> getParkingAreaInfo(ParkingAreaGetRequestDto requestDto) {
        ObjectResultDto<ParkingAreaResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingAreaEntity parkingAreaEntity = parkingAreaCrudService.getParkingAreaInfo(requestDto.getId());
            if (parkingAreaEntity != null) {
                ParkingAreaResultDto parkingAreaResultDto = modelMapper.map(parkingAreaEntity, ParkingAreaResultDto.class);
                ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(parkingAreaEntity.getParkingId());
                if (parkingInfoEntity != null) {
                    parkingAreaResultDto.setParkingName(parkingInfoEntity.getFullName());
                }
                ParkingGarageInfoEntity parkingGarageInfoEntity = parkingGarageInfoCrudService.selectById(parkingAreaEntity.getGarageId());
                if (parkingGarageInfoEntity != null) {
                    parkingAreaResultDto.setGarageName(parkingGarageInfoEntity.getName());
                }
                ParkingFloorEntity parkingFloorEntity = parkingFloorCrudService.selectById(parkingAreaEntity.getFloorId());
                if (parkingFloorEntity != null){
                    parkingAreaResultDto.setFloorName(parkingFloorEntity.getFloorName());
                }
                objectResultDto.setData(parkingAreaResultDto);
                objectResultDto.success();
            } else {
                return objectResultDto.makeResult(PmsResultEnum.PARKING_AREA_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_AREA_NOT_FOUND.getComment());
            }
        } catch (Exception e) {
            log.error("获取泊位区域失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }
}
