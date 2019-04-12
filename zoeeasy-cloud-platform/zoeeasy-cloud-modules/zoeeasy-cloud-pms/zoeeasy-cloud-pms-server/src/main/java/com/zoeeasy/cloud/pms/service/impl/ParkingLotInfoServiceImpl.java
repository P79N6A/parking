package com.zoeeasy.cloud.pms.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pds.magneticdetector.MagneticDetectorService;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.park.MagneticDetectorCodeByParkingAndParkingLotRequestByIdDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.result.park.MagneticDetectorByParkingAndParkingLotResultDto;
import com.zoeeasy.cloud.pms.domain.*;
import com.zoeeasy.cloud.pms.enums.ParkingLotStatusEnum;
import com.zoeeasy.cloud.pms.enums.PmsResultEnum;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.ParkingLotInfoService;
import com.zoeeasy.cloud.pms.park.cst.ColumnConstant;
import com.zoeeasy.cloud.pms.park.dto.request.*;
import com.zoeeasy.cloud.pms.park.dto.result.*;
import com.zoeeasy.cloud.pms.park.validator.ParkingLotAddRequestDtoValidator;
import com.zoeeasy.cloud.pms.park.validator.ParkingLotDeleteRequestDtoValidator;
import com.zoeeasy.cloud.pms.park.validator.ParkingLotUpdateRequestDtoValidator;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingLotInfoGetByCodeRequestDto;
import com.zoeeasy.cloud.pms.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
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
 * 泊位服务
 *
 * @author walkman
 * @date 2017-12-01
 */
@Service("parkingLotInfoService")
@Slf4j
public class ParkingLotInfoServiceImpl implements ParkingLotInfoService {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Autowired
    private ParkingAreaCrudService parkingAreaCrudService;

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Autowired
    private ParkingLotInfoCrudService parkingLotInfoCrudService;

    @Autowired
    private ParkingLotStatusCrudService parkingLotStatusCrudService;

    @Autowired
    private ParkingLotConfigCrudService parkingLotConfigCrudService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MagneticDetectorService magneticDetectorService;

    @Autowired
    private ParkingFloorCrudService parkingFloorCrudService;

    /**
     * 新增车位
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto addParkingLot(@FluentValid({ParkingLotAddRequestDtoValidator.class}) ParkingLotAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingLotInfoEntity parkingLotInfoEntity = modelMapper.map(requestDto, ParkingLotInfoEntity.class);
            parkingLotInfoCrudService.insert(parkingLotInfoEntity);
            ParkingLotStatusEntity parkingLotStatusEntity = new ParkingLotStatusEntity();
            parkingLotStatusEntity.setParkingId(requestDto.getParkingId());
            parkingLotStatusEntity.setParkingLotId(parkingLotInfoEntity.getId());
            parkingLotStatusEntity.setStatus(ParkingLotStatusEnum.FREE.getValue());
            parkingLotStatusCrudService.insert(parkingLotStatusEntity);
            resultDto.success();
        } catch (Exception e) {
            log.error("新增车位失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 更新车位
     */
    @Override
    public ResultDto updateParkingLot(@FluentValid({ParkingLotUpdateRequestDtoValidator.class}) ParkingLotUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingLotInfoEntity parkingLotInfoEntity = modelMapper.map(requestDto, ParkingLotInfoEntity.class);
            EntityWrapper<ParkingLotInfoEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            parkingLotInfoCrudService.update(parkingLotInfoEntity, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("更新车位失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除车位
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto deleteParkingLot(@FluentValid({ParkingLotDeleteRequestDtoValidator.class}) ParkingLotDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingLotStatusEntity parkingLotStatusEntity = parkingLotStatusCrudService.findByParkingLotId(requestDto.getId());
            parkingLotInfoCrudService.deleteById(requestDto.getId());
            parkingLotStatusCrudService.deleteById(parkingLotStatusEntity.getId());
            EntityWrapper<ParkingLotConfigEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parkingLotId", requestDto.getId());
            parkingLotConfigCrudService.delete(entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("删除车位失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取车位
     */
    @Override
    public ObjectResultDto<ParkingLotResultDto> getParkingLot(ParkingLotGetRequestDto requestDto) {
        ObjectResultDto<ParkingLotResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingLotInfoEntity parkingLotInfoEntity = parkingLotInfoCrudService.selectById(requestDto.getId());
            if (parkingLotInfoEntity != null) {
                ParkingLotResultDto parkingLotResultDto = modelMapper.map(parkingLotInfoEntity, ParkingLotResultDto.class);
                ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(parkingLotInfoEntity.getParkingId());
                if (parkingInfoEntity != null) {
                    parkingLotResultDto.setParkingName(parkingInfoEntity.getFullName());
                }
                ParkingGarageInfoEntity parkingGarageInfoEntity = parkingGarageInfoCrudService.selectById(parkingLotInfoEntity.getGarageId());
                if (parkingGarageInfoEntity != null) {
                    parkingLotResultDto.setGarageName(parkingGarageInfoEntity.getName());
                }
                ParkingAreaEntity parkingAreaEntity = parkingAreaCrudService.selectById(parkingLotInfoEntity.getParkingAreaId());
                if (parkingAreaEntity != null) {
                    parkingLotResultDto.setParkingAreaName(parkingAreaEntity.getName());
                }
                ParkingFloorEntity parkingFloorEntity = parkingFloorCrudService.selectById(parkingLotInfoEntity.getFloorId());
                if (parkingFloorEntity != null) {
                    parkingLotResultDto.setFloorName(parkingFloorEntity.getFloorName());
                }
                objectResultDto.setData(parkingLotResultDto);
                objectResultDto.success();
            } else {
                return objectResultDto.makeResult(PmsResultEnum.PARKING_LOT_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_LOT_NOT_FOUND.getComment());
            }
        } catch (Exception e) {
            log.error("获取车位失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 分页获取车位列表
     */
    @Override
    public PagedResultDto<ParkingLotPagedResultDto> getParkingLotPagedList(ParkingLotQueryPagedResultRequestDto requestDto) {
        PagedResultDto<ParkingLotPagedResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<ParkingLotInfoEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.orderBy("creationTime", false);
            List<Long> parkingIds = new ArrayList<>();
            Page<ParkingLotInfoEntity> parkingLotPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            ParkingListGetRequestDto parkingListGetRequestDto = new ParkingListGetRequestDto();
            if (!StringUtils.isEmpty(requestDto.getAreaCode())) {
                parkingListGetRequestDto.setAreaCode(requestDto.getAreaCode());
            }
            if (requestDto.getParkingId() != null) {
                parkingListGetRequestDto.setId(requestDto.getParkingId());
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
            entityWrapper.in(ColumnConstant.PARKING_ID, parkingIds);
            if (!StringUtils.isEmpty(requestDto.getCode())) {
                entityWrapper.like(ColumnConstant.CODE, requestDto.getCode());
            }
            if (!StringUtils.isEmpty(requestDto.getNumber())) {
                entityWrapper.like(ColumnConstant.NUMBER, requestDto.getNumber());
            }
            Page<ParkingLotInfoEntity> parkingLotPageList = parkingLotInfoCrudService.selectPage(parkingLotPage, entityWrapper);
            Map<Long, ParkingInfoEntity> map = new HashMap<>();
            List<ParkingLotPagedResultDto> parkingLots = new ArrayList<>();
            for (ParkingLotInfoEntity parkingLotInfoEntity : parkingLotPageList.getRecords()) {
                ParkingLotPagedResultDto parkingLotPagedResultDto = new ParkingLotPagedResultDto();
                parkingLotPagedResultDto.setId(parkingLotInfoEntity.getId());
                parkingLotPagedResultDto.setNumber(parkingLotInfoEntity.getNumber());
                parkingLotPagedResultDto.setCode(parkingLotInfoEntity.getCode());
                parkingLotPagedResultDto.setCreationTime(parkingLotInfoEntity.getCreationTime());
                setParkingNameAndCode(map, parkingLotPagedResultDto, parkingLotInfoEntity.getParkingId());
                EntityWrapper<ParkingLotStatusEntity> entityEntityWrapper = new EntityWrapper<>();
                entityEntityWrapper.eq(ColumnConstant.PARKING_ID, parkingLotInfoEntity.getParkingId());
                entityEntityWrapper.eq(ColumnConstant.PARKING_LOT_ID, parkingLotInfoEntity.getId());
                ParkingLotStatusEntity parkingLotStatusEntity = parkingLotStatusCrudService.selectOne(entityEntityWrapper);
                if (parkingLotStatusEntity != null) {
                    parkingLotPagedResultDto.setStatus(parkingLotStatusEntity.getStatus());
                }
                parkingLots.add(parkingLotPagedResultDto);
            }
            pagedResultDto.setPageNo(parkingLotPageList.getCurrent());
            pagedResultDto.setPageSize(parkingLotPageList.getSize());
            pagedResultDto.setTotalCount(parkingLotPageList.getTotal());
            pagedResultDto.setItems(parkingLots);
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页获取车位列表失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 设置停车场code和name
     */
    private void setParkingNameAndCode(Map<Long, ParkingInfoEntity> map, ParkingLotPagedResultDto parkingLotPagedResultDto, Long parkingId) {
        if (map.keySet().contains(parkingId)) {
            ParkingInfoEntity parkingInfoEntity = map.get(parkingId);
            parkingLotPagedResultDto.setParkingName(parkingInfoEntity.getFullName());
            parkingLotPagedResultDto.setParkingCode(parkingInfoEntity.getCode());
            parkingLotPagedResultDto.setParkingId(parkingInfoEntity.getId());
        } else {
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(parkingId);
            if (parkingInfoEntity != null) {
                parkingLotPagedResultDto.setParkingName(parkingInfoEntity.getFullName());
                parkingLotPagedResultDto.setParkingCode(parkingInfoEntity.getCode());
                parkingLotPagedResultDto.setParkingId(parkingInfoEntity.getId());
                map.put(parkingId, parkingInfoEntity);
            }
        }
    }

    /**
     * 获取车位列表
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ListResultDto<ParkingLotResultDto> getParkingLotList(ParkingLotListGetRequestDto requestDto) {
        ListResultDto<ParkingLotResultDto> listResultDto = new ListResultDto<>();
        try {
            EntityWrapper<ParkingLotInfoEntity> entityWrapper = new EntityWrapper<>();
            if (requestDto.getId() != null) {
                entityWrapper.eq(ColumnConstant.ID, requestDto.getId());
            }
            if (requestDto.getParkingId() != null) {
                entityWrapper.eq(ColumnConstant.PARKING_ID, requestDto.getParkingId());
            }
            if (!StringUtils.isEmpty(requestDto.getNumber())) {
                entityWrapper.eq(ColumnConstant.NUMBER, requestDto.getNumber());
            }
            if (!StringUtils.isEmpty(requestDto.getName())) {
                entityWrapper.like(ColumnConstant.NAME, requestDto.getName());
            }
            List<ParkingLotInfoEntity> list = parkingLotInfoCrudService.selectList(entityWrapper);
            if (!list.isEmpty()) {
                List<ParkingLotResultDto> parkingLotDtoList = modelMapper.map(list, new TypeToken<List<ParkingLotResultDto>>() {
                }.getType());
                listResultDto.setItems(parkingLotDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取车位列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 根据泊位编号获取泊位列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ParkingLotResultDto> getParkingLotByCodeList(ParkingLotByCodeListGetRequestDto requestDto) {
        ListResultDto<ParkingLotResultDto> listResultDto = new ListResultDto<>();
        try {
            EntityWrapper<ParkingLotInfoEntity> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getCode())) {
                entityWrapper.like(ColumnConstant.CODE, requestDto.getCode());
            }
            List<ParkingLotInfoEntity> infoEntities = parkingLotInfoCrudService.selectList(entityWrapper);
            if (!infoEntities.isEmpty()) {
                List<ParkingLotResultDto> parkingLotDtoList = modelMapper.map(infoEntities, new TypeToken<List<ParkingLotResultDto>>() {
                }.getType());
                listResultDto.setItems(parkingLotDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("根据泊位编号获取泊位列表失败");
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 分页查询泊位配置列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<MagneticDetectorByParkingLotQueryPageResultDto> getMagneticDetectorByParkingLotPagedList(MagneticDetectorByParkingLotQueryPageRequestDto requestDto) {
        PagedResultDto<MagneticDetectorByParkingLotQueryPageResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            List<Long> parkingIds = new ArrayList<>();
            EntityWrapper<ParkingLotInfoEntity> entityWrapper = new EntityWrapper<>();
            ParkingListGetRequestDto parkingListGetRequestDto = new ParkingListGetRequestDto();
            if (StringUtils.isNotEmpty(requestDto.getAreaCode()) || null != requestDto.getParkingId()) {
                if (requestDto.getAreaCode() != null) {
                    parkingListGetRequestDto.setAreaCode(requestDto.getAreaCode());
                }
                if (null != requestDto.getParkingId()) {
                    parkingListGetRequestDto.setId(requestDto.getParkingId());
                }
                ListResultDto<ParkingListGetResultDto> list = parkingInfoService.getParkingList(parkingListGetRequestDto);
                for (ParkingListGetResultDto parkingListGetResultDto : list.getItems()) {
                    parkingIds.add(parkingListGetResultDto.getId());
                }
                entityWrapper.in(ColumnConstant.PARKING_ID, parkingIds);
            }
            if (!StringUtils.isEmpty(requestDto.getCode())) {
                entityWrapper.like(ColumnConstant.CODE, requestDto.getCode());
            }
            if (!StringUtils.isEmpty(requestDto.getNumber())) {
                entityWrapper.like(ColumnConstant.NUMBER, requestDto.getNumber());
            }
            Page<ParkingLotInfoEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<ParkingLotInfoEntity> parkingLotInfoEntityPage = parkingLotInfoCrudService.selectPage(page, entityWrapper);
            List<MagneticDetectorByParkingLotQueryPageResultDto> parkingLotDtoList = new ArrayList<>();
            if (null != parkingLotInfoEntityPage.getRecords()) {
                for (ParkingLotInfoEntity parkingLotInfoEntity : parkingLotInfoEntityPage.getRecords()) {
                    MagneticDetectorByParkingLotQueryPageResultDto parking = getParking(parkingLotInfoEntity);
                    parkingLotDtoList.add(parking);
                }
                pagedResultDto.setPageNo(parkingLotInfoEntityPage.getCurrent());
                pagedResultDto.setPageSize(parkingLotInfoEntityPage.getSize());
                pagedResultDto.setTotalCount(parkingLotInfoEntityPage.getTotal());
                pagedResultDto.setItems(parkingLotDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页查询泊位配置列表失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    public MagneticDetectorByParkingLotQueryPageResultDto getParking(ParkingLotInfoEntity parkingLotInfoEntity) {
        MagneticDetectorByParkingLotQueryPageResultDto magneticDetectorByParkingLotQueryPageResultDto = new MagneticDetectorByParkingLotQueryPageResultDto();
        MagneticDetectorCodeByParkingAndParkingLotRequestByIdDto magneticDetectorCodeByParkingAndParkingLotRequestByIdDto = new MagneticDetectorCodeByParkingAndParkingLotRequestByIdDto();
        ParkingGetRequestDto parkingGetRequestDto = new ParkingGetRequestDto();
        Map<Long, String> map = new HashedMap();
        magneticDetectorByParkingLotQueryPageResultDto.setId(parkingLotInfoEntity.getId());
        if (map.keySet().contains(magneticDetectorByParkingLotQueryPageResultDto.getParkingId())) {
            String parkingName = map.get(magneticDetectorByParkingLotQueryPageResultDto.getParkingId());
            magneticDetectorByParkingLotQueryPageResultDto.setParkingName(parkingName);
            magneticDetectorByParkingLotQueryPageResultDto.setParkingLotCode(parkingLotInfoEntity.getCode());
            magneticDetectorByParkingLotQueryPageResultDto.setParkingLotNumber(parkingLotInfoEntity.getNumber());
            magneticDetectorCodeByParkingAndParkingLotRequestByIdDto.setParkingId(parkingLotInfoEntity.getParkingId());
            magneticDetectorCodeByParkingAndParkingLotRequestByIdDto.setParkingLotId(parkingLotInfoEntity.getId());
        } else {
            magneticDetectorByParkingLotQueryPageResultDto.setParkingId(parkingLotInfoEntity.getParkingId());
            magneticDetectorByParkingLotQueryPageResultDto.setParkingLotCode(parkingLotInfoEntity.getCode());
            magneticDetectorByParkingLotQueryPageResultDto.setParkingLotNumber(parkingLotInfoEntity.getNumber());
            parkingGetRequestDto.setId(parkingLotInfoEntity.getParkingId());
            ObjectResultDto<ParkingResultDto> parking = parkingInfoService.getParking(parkingGetRequestDto);
            if (null != parking.getData()) {
                magneticDetectorCodeByParkingAndParkingLotRequestByIdDto.setParkingId(parkingLotInfoEntity.getParkingId());
                magneticDetectorCodeByParkingAndParkingLotRequestByIdDto.setParkingLotId(parkingLotInfoEntity.getId());
                magneticDetectorByParkingLotQueryPageResultDto.setParkingName(parking.getData().getFullName());
                map.put(magneticDetectorByParkingLotQueryPageResultDto.getParkingId(), parking.getData().getFullName());
            }
        }
        //如果根据泊位、停车场查询到地磁，已关联
        ObjectResultDto<MagneticDetectorByParkingAndParkingLotResultDto> magneticDetectorByParkingAndParkingLot = magneticDetectorService.getMagneticDetectorByParkingAndParkingLot(magneticDetectorCodeByParkingAndParkingLotRequestByIdDto);
        if (null != magneticDetectorByParkingAndParkingLot.getData()) {
            magneticDetectorByParkingLotQueryPageResultDto.setCode(magneticDetectorByParkingAndParkingLot.getData().getCode());
        } else {
            magneticDetectorByParkingLotQueryPageResultDto.setCode(null);
        }
        return magneticDetectorByParkingLotQueryPageResultDto;
    }

    /**
     * 根据泊位code获取泊位
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingLotResultDto> getParkingLotByCode(ParkingLotInfoGetByCodeRequestDto requestDto) {
        ObjectResultDto<ParkingLotResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<ParkingLotInfoEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq(ColumnConstant.CODE, requestDto.getParkingLotCode());
            ParkingLotInfoEntity parkingLotInfoEntity = parkingLotInfoCrudService.selectOne(entityWrapper);
            if (parkingLotInfoEntity != null) {
                ParkingLotResultDto parkingLotResultDto = modelMapper.map(parkingLotInfoEntity, ParkingLotResultDto.class);
                objectResultDto.setData(parkingLotResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("根据泊位code获取泊位失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 根据泊位code获取泊位(无租户)
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingLotResultDto> getParkingLotByLotCode(ParkingLotInfoGetByCodeRequestDto requestDto) {
        ObjectResultDto<ParkingLotResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingLotInfoEntity parkingLotInfoEntity = parkingLotInfoCrudService.findByCode(requestDto.getParkingLotCode());
            if (parkingLotInfoEntity != null) {
                ParkingLotResultDto parkingLotResultDto = modelMapper.map(parkingLotInfoEntity, ParkingLotResultDto.class);
                objectResultDto.setData(parkingLotResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("根据泊位code获取泊位失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

}

