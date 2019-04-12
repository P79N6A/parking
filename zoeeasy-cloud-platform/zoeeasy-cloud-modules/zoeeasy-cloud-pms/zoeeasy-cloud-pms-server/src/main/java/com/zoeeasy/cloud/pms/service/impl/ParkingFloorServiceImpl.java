package com.zoeeasy.cloud.pms.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.domain.*;
import com.zoeeasy.cloud.pms.enums.PictureTypeEnum;
import com.zoeeasy.cloud.pms.enums.PmsResultEnum;
import com.zoeeasy.cloud.pms.floor.ParkingFloorService;
import com.zoeeasy.cloud.pms.floor.dto.request.*;
import com.zoeeasy.cloud.pms.floor.dto.result.FloorListResultDto;
import com.zoeeasy.cloud.pms.floor.dto.result.FloorPagedResultDto;
import com.zoeeasy.cloud.pms.floor.dto.result.FloorResultDto;
import com.zoeeasy.cloud.pms.floor.validator.FloorAddRequestDtoValidator;
import com.zoeeasy.cloud.pms.floor.validator.FloorUpdateRequestDtoValidator;
import com.zoeeasy.cloud.pms.garage.ParkingGarageService;
import com.zoeeasy.cloud.pms.garage.dto.request.GarageListGetRequestDto;
import com.zoeeasy.cloud.pms.garage.dto.result.GarageListGetResultDto;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by song on 2019/3/23 9:59
 */
@Service("parkingFloorService")
@Slf4j
public class ParkingFloorServiceImpl implements ParkingFloorService {

    @Autowired
    private ParkingFloorCrudService parkingFloorCrudService;

    @Autowired
    private ParkingPictureCrudService parkingPictureCrudService;

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Autowired
    private ParkingGarageService parkingGarageService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingAreaCrudService parkingAreaCrudService;

    @Autowired
    private ParkingLotInfoCrudService parkingLotInfoCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 添加楼层
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto addFloor(@FluentValid({FloorAddRequestDtoValidator.class})FloorAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingFloorEntity parkingFloorEntity = modelMapper.map(requestDto, ParkingFloorEntity.class);
            parkingFloorCrudService.insert(parkingFloorEntity);
            if (!StringUtils.isEmpty(requestDto.getBleNavigationPic())){
                ParkingPictureEntity parkingPictureEntity = new ParkingPictureEntity();
                parkingPictureEntity.setParkingId(requestDto.getParkingId());
                parkingPictureEntity.setBusinessId(parkingFloorEntity.getId());
                parkingPictureEntity.setUrl(requestDto.getBleNavigationPic());
                parkingPictureEntity.setPictureType(PictureTypeEnum.NAVIGATION.getValue());
                parkingPictureCrudService.insert(parkingPictureEntity);
            }
            resultDto.success();
        } catch (Exception e){
            log.error("添加楼层失败：" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 修改楼层
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto updateFloor(@FluentValid({FloorUpdateRequestDtoValidator.class})FloorUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingFloorEntity parkingFloorEntity = modelMapper.map(requestDto, ParkingFloorEntity.class);
            EntityWrapper<ParkingFloorEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            parkingFloorCrudService.update(parkingFloorEntity, entityWrapper);
            EntityWrapper<ParkingPictureEntity> pictureEntityWrapper = new EntityWrapper<>();
            pictureEntityWrapper.eq("businessId", requestDto.getId());
            pictureEntityWrapper.eq("pictureType", PictureTypeEnum.NAVIGATION.getValue());
            parkingPictureCrudService.delete(pictureEntityWrapper);
            if (StringUtils.isEmpty(requestDto.getBleNavigationPic())){
                ParkingPictureEntity parkingPictureEntity = new ParkingPictureEntity();
                parkingPictureEntity.setParkingId(requestDto.getParkingId());
                parkingPictureEntity.setBusinessId(requestDto.getId());
                parkingPictureEntity.setUrl(requestDto.getBleNavigationPic());
                parkingPictureEntity.setPictureType(PictureTypeEnum.NAVIGATION.getValue());
                parkingPictureCrudService.insert(parkingPictureEntity);
            }
            resultDto.success();
        } catch (Exception e){
            log.error("修改楼层失败：" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取楼层详情
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<FloorResultDto> getFloor(FloorGetRequestDto requestDto) {
        ObjectResultDto<FloorResultDto> resultDto = new ObjectResultDto<>();
        try {
            ParkingFloorEntity parkingFloorEntity = parkingFloorCrudService.selectById(requestDto.getId());
            if (parkingFloorEntity != null){
                FloorResultDto floorResultDto = modelMapper.map(parkingFloorEntity, FloorResultDto.class);
                EntityWrapper<ParkingPictureEntity> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("businessId", parkingFloorEntity.getId());
                entityWrapper.eq("pictureType", PictureTypeEnum.NAVIGATION.getValue());
                ParkingPictureEntity parkingPictureEntity = parkingPictureCrudService.selectOne(entityWrapper);
                if (parkingPictureEntity != null){
                    floorResultDto.setBleNavigationPic(parkingPictureEntity.getUrl());
                }
                resultDto.setData(floorResultDto);
                resultDto.success();
            } else {
                resultDto.makeResult(PmsResultEnum.FLOOR_NOT_FOUND.getValue(), PmsResultEnum.FLOOR_NOT_FOUND.getComment());
            }
        } catch (Exception e){
            log.error("获取楼层详情失败：" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除楼层
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto deleteFloor(FloorGetRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            EntityWrapper<ParkingAreaEntity> parkingAreaEntityEntityWrapper = new EntityWrapper<>();
            parkingAreaEntityEntityWrapper.eq("floorId", requestDto.getId());
            int parkingAreaCount = parkingAreaCrudService.selectCount(parkingAreaEntityEntityWrapper);
            if (parkingAreaCount > 0){
                return resultDto.makeResult(PmsResultEnum.FLOOR_NOT_DELETE.getValue(), PmsResultEnum.FLOOR_NOT_DELETE.getComment());
            }
            EntityWrapper<ParkingLotInfoEntity> parkingLotEntityWrapper = new EntityWrapper<>();
            parkingLotEntityWrapper.eq("floorId", requestDto.getId());
            int parkingLotCount = parkingLotInfoCrudService.selectCount(parkingLotEntityWrapper);
            if (parkingLotCount > 0){
                return resultDto.makeResult(PmsResultEnum.FLOOR_NOT_DELETE.getValue(), PmsResultEnum.FLOOR_NOT_DELETE.getComment());
            }
            parkingFloorCrudService.deleteById(requestDto.getId());
            EntityWrapper<ParkingPictureEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("businessId", requestDto.getId());
            entityWrapper.eq("pictureType", PictureTypeEnum.NAVIGATION.getValue());
            parkingPictureCrudService.delete(entityWrapper);
            resultDto.success();
        } catch (Exception e){
            log.error("删除楼层失败：" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 分页获取楼层
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<FloorPagedResultDto> getFloorPagedList(FloorPagedRequestDto requestDto) {
        PagedResultDto<FloorPagedResultDto> resultDto = new PagedResultDto<>();
        try {
            EntityWrapper<ParkingFloorEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.orderBy("creationTime", false);
            if (!StringUtils.isEmpty(requestDto.getFloorName())){
                entityWrapper.like("floorName", requestDto.getFloorName());
            }
            if (!StringUtils.isEmpty(requestDto.getFloorCode())){
                entityWrapper.like("floorCode", requestDto.getFloorCode());
            }
            List<Long> parkingIds = new ArrayList<>();
            ParkingListGetRequestDto parkingListGetRequestDto = new ParkingListGetRequestDto();
            if (!StringUtils.isEmpty(requestDto.getAreaCode())) {
                parkingListGetRequestDto.setAreaCode(requestDto.getAreaCode());
            }
            if (!StringUtils.isEmpty(requestDto.getParkingName())) {
                parkingListGetRequestDto.setName(requestDto.getParkingName());
            }
            ListResultDto<ParkingListGetResultDto> list = parkingInfoService.getParkingList(parkingListGetRequestDto);
            if (list.getItems().isEmpty()) {
                resultDto = new PagedResultDto(requestDto.getPageNo(), requestDto.getPageSize(), new ArrayList<>(), 0L);
                resultDto.success();
                return resultDto;
            } else {
                for (ParkingListGetResultDto parkingListGetResultDto : list.getItems()) {
                    parkingIds.add(parkingListGetResultDto.getId());
                }
            }
            entityWrapper.in("parkingId", parkingIds);
            List<Long> garageIds = new ArrayList<>();
            GarageListGetRequestDto garageListGetRequestDto = new GarageListGetRequestDto();
            if (!StringUtils.isEmpty(requestDto.getGarageName())){
                garageListGetRequestDto.setName(requestDto.getGarageName());
            }
            ListResultDto<GarageListGetResultDto> garages = parkingGarageService.getGarageList(garageListGetRequestDto);
            if (garages.getItems().isEmpty()){
                resultDto = new PagedResultDto(requestDto.getPageNo(), requestDto.getPageSize(), new ArrayList<>(), 0L);
                resultDto.success();
                return resultDto;
            } else {
                for (GarageListGetResultDto garageListGetResultDto : garages.getItems()){
                    garageIds.add(garageListGetResultDto.getId());
                }
            }
            entityWrapper.in("garageId", garageIds);
            Page<ParkingFloorEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<ParkingFloorEntity> floorPageList = parkingFloorCrudService.selectPage(page, entityWrapper);
            List<FloorPagedResultDto> pageList = new ArrayList<>();
            if (floorPageList != null){
                Map<Long, ParkingInfoEntity> map = new HashMap();
                for (ParkingFloorEntity parkingFloorEntity : floorPageList.getRecords()){
                    FloorPagedResultDto floorPagedResultDto = modelMapper.map(parkingFloorEntity, FloorPagedResultDto.class);
                    if (map.keySet().contains(parkingFloorEntity.getParkingId())){
                        floorPagedResultDto.setParkingCode(map.get(parkingFloorEntity.getParkingId()).getCode());
                        floorPagedResultDto.setParkingName(map.get(parkingFloorEntity.getParkingId()).getFullName());
                    } else {
                        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(parkingFloorEntity.getParkingId());
                        floorPagedResultDto.setParkingName(parkingInfoEntity.getFullName());
                        floorPagedResultDto.setParkingCode(parkingInfoEntity.getCode());
                        map.put(parkingFloorEntity.getParkingId(), parkingInfoEntity);
                    }
                    pageList.add(floorPagedResultDto);
                }
            }
            resultDto.setPageNo(floorPageList.getCurrent());
            resultDto.setPageSize(floorPageList.getSize());
            resultDto.setTotalCount(floorPageList.getTotal());
            resultDto.setItems(pageList);
            resultDto.success();
        } catch (Exception e){
            log.error("分页获取楼层失败：" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取楼层列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<FloorListResultDto> getFloorList(FloorListRequestDto requestDto) {
        ListResultDto<FloorListResultDto> resultDto = new ListResultDto<>();
        try {
            EntityWrapper<ParkingFloorEntity> entityWrapper = new EntityWrapper<>();
            if (requestDto.getParkingId() != null){
                entityWrapper.eq("parkingId", requestDto.getParkingId());
            }
            if (requestDto.getGarageId() != null){
                entityWrapper.eq("garageId", requestDto.getGarageId());
            }
            if (!StringUtils.isEmpty(requestDto.getFloorName())){
                entityWrapper.like("floorName", requestDto.getFloorName());
            }
            List<ParkingFloorEntity> list = parkingFloorCrudService.selectList(entityWrapper);
            List<FloorListResultDto> floorList = modelMapper.map(list, new TypeToken<List<FloorListResultDto>>() {
            }.getType());
            resultDto.setItems(floorList);
            resultDto.success();
        } catch (Exception e){
            log.error("获取楼层列表失败：" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }
}
