package com.zoeeasy.cloud.pds.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.lock.redisson.core.Lock;
import com.scapegoat.infrastructure.lock.redisson.core.LockFactory;
import com.scapegoat.infrastructure.lock.redisson.core.LockInfo;
import com.scapegoat.infrastructure.lock.redisson.enumerate.LockType;
import com.zoeeasy.cloud.core.enums.DeviceProviderEnum;
import com.zoeeasy.cloud.pds.domain.MagneticDetectorEntity;
import com.zoeeasy.cloud.pds.domain.MagneticManagerEntity;
import com.zoeeasy.cloud.pds.enums.BatteryTypeEnum;
import com.zoeeasy.cloud.pds.enums.MagneticDetectorEnum;
import com.zoeeasy.cloud.pds.enums.MagneticDetectorRegisteredStatusEnum;
import com.zoeeasy.cloud.pds.enums.MagneticDetectorStatusEnum;
import com.zoeeasy.cloud.pds.magneticdetector.MagneticDetectorService;
import com.zoeeasy.cloud.pds.magneticdetector.cst.ColumnConstant;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.inspect.MagneticDetectorGetByInspectRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.park.*;
import com.zoeeasy.cloud.pds.magneticdetector.dto.result.inspect.MagneticDetectorGetByInspectResultDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.result.park.*;
import com.zoeeasy.cloud.pds.magneticdetector.validator.*;
import com.zoeeasy.cloud.pds.magneticmanager.dto.request.MagneticManagerIdBatchUpdateRequestDto;
import com.zoeeasy.cloud.pds.magneticmanager.validator.MagneticManagerIdBatchUpdateRequestDtoValidator;
import com.zoeeasy.cloud.pds.service.MagneticDetectorCrudService;
import com.zoeeasy.cloud.pds.service.MagneticManagerCrudService;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.ParkingLotInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingInfoGetByIdRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingListGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingLotListGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingByIdResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingListGetResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingLotResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingResultDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 地磁相关服务
 *
 * @author lhj
 */
@Service("magneticDetectorService")
@Slf4j
public class MagneticDetectorServiceImpl implements MagneticDetectorService {

    @Autowired
    private MagneticDetectorCrudService magneticDetectorCrudService;

    @Autowired
    private MagneticManagerCrudService magneticManagerCrudService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Autowired
    private ParkingLotInfoService parkingLotInfoService;

    @Autowired
    private LockFactory lockFactory;

    /**
     * 添加地磁检测器
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addMagneticDetector(@FluentValid({MagneticDetectorAddRequestDtoValidator.class}) MagneticDetectorAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        LockInfo lockInfo = new LockInfo();
        lockInfo.setType(LockType.Fair);
        lockInfo.setName(getMagneticLockKey(requestDto.getCode()));
        lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
        lockInfo.setLeaseTime(LockInfo.DEFAULT_LOCK_LEASE_TIME);
        Lock lock = lockFactory.getLock(lockInfo);
        boolean lockAcquired = false;
        try {
            MagneticDetectorEntity magneticDetectorEntity = modelMapper.map(requestDto, MagneticDetectorEntity.class);
            //是否注册，默认未注册
            magneticDetectorEntity.setRegistered(Boolean.FALSE);
            //地磁检测器状态，默认正常
            magneticDetectorEntity.setStatus(MagneticDetectorStatusEnum.NORMAL.getValue());
            lockAcquired = lock.acquire();
            if (lockAcquired) {
                magneticDetectorCrudService.insert(magneticDetectorEntity);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("地磁检测器添加失败" + e.getMessage());
            resultDto.failed();
        } finally {
            if (lockAcquired) {
                lock.release();
            }
        }
        return resultDto;
    }

    private String getMagneticLockKey(String code) {
        return "lock:zoeeasy.pds.magnetic.magnetic_add_" + code;
    }

    /**
     * 更新地磁检测器
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateMagneticDetector(@FluentValid({MagneticDetectorUpdateRequestDtoValidator.class}) MagneticDetectorUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            MagneticDetectorEntity updateMagneticDetectorEntity = modelMapper.map(requestDto, MagneticDetectorEntity.class);
            magneticDetectorCrudService.updateMagneticDetector(updateMagneticDetectorEntity);
            resultDto.success();
        } catch (Exception e) {
            log.error("设备更新失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除地磁检测器
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto deleteMagneticDetector(@FluentValid({MagneticDetectorDeleteRequestDtoValidator.class}) MagneticDetectorDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            magneticDetectorCrudService.deleteById(requestDto.getId());
            resultDto.success();
        } catch (Exception e) {
            log.error("设备删除失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 根据ID查询地磁检测器详情
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<MagneticDetectorResultDto> getMagneticDetectorById(@FluentValid({MagneticDetectorGetRequestByIdDtoValidator.class}) MagneticDetectorGetRequestByIdDto requestDto) {
        ObjectResultDto<MagneticDetectorResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            MagneticDetectorEntity magneticDetectorEntity = magneticDetectorCrudService.selectById(requestDto.getId());
            MagneticDetectorResultDto magneticDetectorResultDto = modelMapper.map(magneticDetectorEntity, MagneticDetectorResultDto.class);
            magneticDetectorResultDto.setCode(magneticDetectorEntity.getCode());
            //厂商
            String provider = getProvider(magneticDetectorEntity);
            magneticDetectorResultDto.setProvider(provider);
            objectResultDto.setData(magneticDetectorResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("查询设备失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取地磁检测器列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<MagneticDetectorByCodeResultDto> getMagneticDetectorList(MagneticDetectorListResultRequestDto requestDto) {
        ListResultDto<MagneticDetectorByCodeResultDto> listResultDto = new ListResultDto<>();
        ParkingListGetRequestDto parkingListGetRequestDto = new ParkingListGetRequestDto();
        try {
            EntityWrapper<MagneticDetectorEntity> entityWrapper = new EntityWrapper();
            List<Long> parkingIds = new ArrayList<>();
            if (!StringUtils.isEmpty(requestDto.getAreaCode())) {
                parkingListGetRequestDto.setAreaCode(requestDto.getAreaCode());
                ListResultDto<ParkingListGetResultDto> parkingList = parkingInfoService.getParkingList(parkingListGetRequestDto);
                if (CollectionUtils.isNotEmpty(parkingList.getItems())) {
                    for (ParkingListGetResultDto parkingListGetResultDto : parkingList.getItems()) {
                        parkingIds.add(parkingListGetResultDto.getId());
                    }
                    entityWrapper.in(ColumnConstant.PARKING_ID, parkingIds);
                }
            }
            //停车场
            if (null != requestDto.getParkingId()) {
                entityWrapper.eq(ColumnConstant.PARKING_ID, requestDto.getParkingId());
            }
            if (null != requestDto.getParkingLotId()) {
                entityWrapper.eq(ColumnConstant.PARKING_LOT_ID, requestDto.getParkingLotId());
            }
            if (null != requestDto.getManagerId()) {
                entityWrapper.eq(ColumnConstant.MAGNETIC_MANAGET_ID, requestDto.getManagerId());
            }
            List<MagneticDetectorEntity> detectorEntities = magneticDetectorCrudService.selectList(entityWrapper);
            if (!detectorEntities.isEmpty()) {
                List<MagneticDetectorByCodeResultDto> magneticDetectorResultDtoList = modelMapper.map(detectorEntities, new TypeToken<List<MagneticDetectorByCodeResultDto>>() {
                }.getType());
                if (CollectionUtils.isNotEmpty(magneticDetectorResultDtoList)) {
                    listResultDto.setItems(magneticDetectorResultDtoList);
                }
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取地磁检测器列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 根据停车场泊位查询地磁
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<MagneticDetectorByParkingAndParkingLotResultDto> getMagneticDetectorByParkingAndParkingLot(MagneticDetectorCodeByParkingAndParkingLotRequestByIdDto requestDto) {
        ObjectResultDto<MagneticDetectorByParkingAndParkingLotResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<MagneticDetectorEntity> entityEntityWrapper = new EntityWrapper<>();
            entityEntityWrapper.eq(ColumnConstant.PARKING_ID, requestDto.getParkingId());
            entityEntityWrapper.eq(ColumnConstant.PARKING_LOT_ID, requestDto.getParkingLotId());
            MagneticDetectorEntity magneticDetectorEntity = magneticDetectorCrudService.selectOne(entityEntityWrapper);
            if (null != magneticDetectorEntity) {
                MagneticDetectorByParkingAndParkingLotResultDto lotResultDto = modelMapper.map(magneticDetectorEntity, MagneticDetectorByParkingAndParkingLotResultDto.class);
                lotResultDto.setCode(magneticDetectorEntity.getCode());
                objectResultDto.setData(lotResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("根据停车场泊位查询地磁失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 根据地磁检测器编号查询
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<MagneticDetectorByCodeResultDto> getMagneticDetector(MagneticDetectorGetRequestDto requestDto) {
        ObjectResultDto<MagneticDetectorByCodeResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            MagneticDetectorEntity magneticDetectorEntity = magneticDetectorCrudService.findMagneticDetectorByCode(requestDto.getCode());
            if (null == magneticDetectorEntity) {
                return objectResultDto.makeResult(MagneticDetectorEnum.MAGNETIC_DETECTOR_NOT_FOUND.getValue(),
                        MagneticDetectorEnum.MAGNETIC_DETECTOR_NOT_FOUND.getComment());
            }
            MagneticDetectorByCodeResultDto magneticDetectorResultDto = modelMapper.map(magneticDetectorEntity, MagneticDetectorByCodeResultDto.class);
            //厂商
            String provider = getProvider(magneticDetectorEntity);
            magneticDetectorResultDto.setProvider(provider);
            //注册状态
            if (magneticDetectorEntity.getRegistered() == Boolean.TRUE) {
                magneticDetectorResultDto.setRegistered(MagneticDetectorRegisteredStatusEnum.REGISTERED.getComment());
            } else {
                magneticDetectorResultDto.setRegistered(MagneticDetectorRegisteredStatusEnum.UNREGISTERED.getComment());
            }
            //地磁检测器状态
            String status = getMagneticDetectorStatus(magneticDetectorEntity);
            magneticDetectorResultDto.setStatus(status);
            objectResultDto.setData(magneticDetectorResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("查询设备失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 根据区域分页查询设备
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<MagneticDetectorByCodeResultDto> getMagneticDetectorPagedList(MagneticDetectorQueryPagedResultRequestDto requestDto) {
        PagedResultDto<MagneticDetectorByCodeResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<MagneticDetectorEntity> entityWrapper = new EntityWrapper();
            List<Long> parkingIds = new ArrayList<>();
            //区域
            if (!StringUtils.isEmpty(requestDto.getAreaCode())) {
                ParkingListGetRequestDto parkingListGetRequestDto = new ParkingListGetRequestDto();
                parkingListGetRequestDto.setAreaCode(requestDto.getAreaCode());
                ListResultDto<ParkingListGetResultDto> parkingList = parkingInfoService.getParkingList(parkingListGetRequestDto);
                for (ParkingListGetResultDto parkingListGetResultDto : parkingList.getItems()) {
                    parkingIds.add(parkingListGetResultDto.getId());
                }
                if (CollectionUtils.isEmpty(parkingIds)) {
                    pagedResultDto = new PagedResultDto(requestDto.getPageNo(), requestDto.getPageSize(), new ArrayList<>(), 0L);
                    pagedResultDto.success();
                    return pagedResultDto;
                }
                entityWrapper.in(ColumnConstant.PARKING_ID, parkingIds);
            }
            //停车场
            if (null != requestDto.getParkingId()) {
                entityWrapper.eq(ColumnConstant.PARKING_ID, requestDto.getParkingId());
            }
            if (!StringUtils.isEmpty(requestDto.getCode())) {
                entityWrapper.like(ColumnConstant.CODE, requestDto.getCode());
            }
            Page<MagneticDetectorEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<MagneticDetectorEntity> magneticDetectorEntityPage = magneticDetectorCrudService.selectPage(page, entityWrapper);
            if (magneticDetectorEntityPage.getRecords() != null) {
                List<MagneticDetectorByCodeResultDto> magneticDetectorResultDtoList = new ArrayList<>();
                for (MagneticDetectorEntity magneticDetectorEntity : magneticDetectorEntityPage.getRecords()) {
                    MagneticDetectorByCodeResultDto magneticDetectorResultDto = new MagneticDetectorByCodeResultDto();
                    magneticDetectorResultDto.setId(magneticDetectorEntity.getId());
                    magneticDetectorResultDto.setCode(magneticDetectorEntity.getCode());
                    magneticDetectorResultDto.setSerialNumber(magneticDetectorEntity.getSerialNumber());
                    magneticDetectorResultDto.setHeartBeatInterval(magneticDetectorEntity.getHeartBeatInterval());
                    magneticDetectorResultDto.setIpAddress(magneticDetectorEntity.getIpAddress());
                    //富尚电量状态
                    if (magneticDetectorEntity.getProvider().equals(DeviceProviderEnum.FUSHANG.getValue())) {
                        BatteryTypeEnum batteryTypeEnum = BatteryTypeEnum.parse(magneticDetectorEntity.getElectricity());
                        if (batteryTypeEnum != null) {
                            magneticDetectorResultDto.setElectricity(batteryTypeEnum.getComment());
                        }
                    } else {
                        if (magneticDetectorEntity.getElectricity() == null) {
                            magneticDetectorResultDto.setElectricity("0");
                        } else {
                            magneticDetectorResultDto.setElectricity(String.valueOf(magneticDetectorEntity.getElectricity()));
                        }
                    }
                    magneticDetectorResultDto.setInstallationTime(magneticDetectorEntity.getInstallationTime());
                    magneticDetectorResultDto.setInstallPosition(magneticDetectorEntity.getInstallPosition());
                    magneticDetectorResultDto.setVersionNumber(magneticDetectorEntity.getVersionNumber());
                    magneticDetectorResultDto.setPort(magneticDetectorEntity.getPort());
                    magneticDetectorResultDto.setSimNo(magneticDetectorEntity.getSimNo());
                    magneticDetectorResultDto.setLastHeartbeatTime(magneticDetectorEntity.getLastHeartbeatTime());
                    //厂商
                    String provider = getProvider(magneticDetectorEntity);
                    magneticDetectorResultDto.setProvider(provider);
                    //注册状态
                    if (magneticDetectorEntity.getRegistered() == Boolean.TRUE) {
                        magneticDetectorResultDto.setRegistered(MagneticDetectorRegisteredStatusEnum.REGISTERED.getComment());
                    } else {
                        magneticDetectorResultDto.setRegistered(MagneticDetectorRegisteredStatusEnum.UNREGISTERED.getComment());
                    }
                    //地磁检测器状态
                    String status = getMagneticDetectorStatus(magneticDetectorEntity);
                    magneticDetectorResultDto.setStatus(status);
                    magneticDetectorResultDtoList.add(magneticDetectorResultDto);
                }
                pagedResultDto.setPageNo(magneticDetectorEntityPage.getCurrent());
                pagedResultDto.setPageSize(magneticDetectorEntityPage.getSize());
                pagedResultDto.setTotalCount(magneticDetectorEntityPage.getTotal());
                pagedResultDto.setItems(magneticDetectorResultDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页查询设备列表失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 关联设备管理器
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto batchUpdateMagneticManagerId(@FluentValid(MagneticManagerIdBatchUpdateRequestDtoValidator.class) MagneticManagerIdBatchUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            List<String> remList = new ArrayList<>();
            List<String> addList = requestDto.getCodes();
            List<String> delList = new ArrayList<>();
            //已关联此管理器的地磁
            EntityWrapper<MagneticDetectorEntity> wrapper = new EntityWrapper<>();
            wrapper.eq(ColumnConstant.MAGNETIC_MANAGET_ID, requestDto.getManagerId());
            List<MagneticDetectorEntity> detectorEntities = magneticDetectorCrudService.selectList(wrapper);
            Set set = null;
            if (null != requestDto.getCodes()) {
                set = new HashSet(requestDto.getCodes());
            }
            if (CollectionUtils.isEmpty(detectorEntities) && CollectionUtils.isNotEmpty(requestDto.getCodes())) {
                EntityWrapper<MagneticDetectorEntity> wrapper1 = new EntityWrapper<>();
                wrapper1.in(ColumnConstant.CODE, requestDto.getCodes());
                MagneticDetectorEntity magneticDetectorEntity = new MagneticDetectorEntity();
                magneticDetectorEntity.setManagerId(requestDto.getManagerId());
                magneticDetectorCrudService.update(magneticDetectorEntity, wrapper1);
            }
            for (MagneticDetectorEntity entity : detectorEntities) {
                if (set != null && set.contains(entity.getCode())) {
                    remList.add(entity.getCode());
                    //移除
                    addList.removeAll(remList);
                } else {
                    delList.add(entity.getCode());
                }
            }
            //关联
            if (CollectionUtils.isNotEmpty(addList)) {
                EntityWrapper<MagneticDetectorEntity> wrapper1 = new EntityWrapper<>();
                wrapper1.in(ColumnConstant.CODE, addList);
                MagneticDetectorEntity magneticDetectorEntity = new MagneticDetectorEntity();
                magneticDetectorEntity.setManagerId(requestDto.getManagerId());
                magneticDetectorCrudService.update(magneticDetectorEntity, wrapper1);
            }
            //解绑
            if (CollectionUtils.isNotEmpty(delList)) {
                EntityWrapper<MagneticDetectorEntity> wrapper1 = new EntityWrapper<>();
                wrapper1.in(ColumnConstant.CODE, delList);
                MagneticDetectorEntity magneticDetectorEntity = new MagneticDetectorEntity();
                magneticDetectorEntity.setManagerId(0L);
                magneticDetectorCrudService.update(magneticDetectorEntity, wrapper1);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("批量更新设备管理器Id失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 关联设备泊位
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto updateDetectorParkingLotId(@FluentValid({DetectorParkingLotIdBatchUpdateRequestDtoValidator.class}) DetectorParkingLotIdBatchUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            //查询已关联泊位
            EntityWrapper<MagneticDetectorEntity> wrapper = new EntityWrapper<>();
            wrapper.eq(ColumnConstant.PARKING_LOT_ID, requestDto.getParkingLotId());
            MagneticDetectorEntity entity = magneticDetectorCrudService.selectOne(wrapper);
            if (entity != null) {
                if (!entity.getCode().equals(requestDto.getCode())) {
                    //解绑原先的
                    EntityWrapper<MagneticDetectorEntity> wrapper1 = new EntityWrapper<>();
                    wrapper1.eq(ColumnConstant.CODE, entity.getCode());
                    MagneticDetectorEntity detectorEntity = new MagneticDetectorEntity();
                    detectorEntity.setParkingLotId(0L);
                    magneticDetectorCrudService.update(detectorEntity, wrapper1);
                    //关联现在的
                    EntityWrapper<MagneticDetectorEntity> entityWrapper = new EntityWrapper<>();
                    entityWrapper.eq(ColumnConstant.CODE, requestDto.getCode());
                    MagneticDetectorEntity magneticDetectorEntity = new MagneticDetectorEntity();
                    magneticDetectorEntity.setParkingLotId(requestDto.getParkingLotId());
                    magneticDetectorCrudService.update(magneticDetectorEntity, entityWrapper);
                }
            } else {
                EntityWrapper<MagneticDetectorEntity> wrapper1 = new EntityWrapper<>();
                wrapper1.eq(ColumnConstant.CODE, requestDto.getCode());
                MagneticDetectorEntity magneticDetectorEntity = new MagneticDetectorEntity();
                magneticDetectorEntity.setParkingLotId(requestDto.getParkingLotId());
                magneticDetectorCrudService.update(magneticDetectorEntity, wrapper1);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("更新设备泊位Id失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 分页查询与管理器未关联设备列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<MagneticDetectorRelevanceResultDto> getNotRelevanceMagneticDetectorPagedList(MagneticDetectorNotRelevanceQueryPageRequestDto requestDto) {
        PagedResultDto<MagneticDetectorRelevanceResultDto> pagedResultDto = new PagedResultDto<>();
        EntityWrapper<MagneticDetectorEntity> entityWrapper = new EntityWrapper<>();
        ParkingGetRequestDto parkingGetRequestDto = new ParkingGetRequestDto();
        ParkingLotListGetRequestDto parkingLotListGetRequestDto = new ParkingLotListGetRequestDto();
        try {
            entityWrapper.eq(ColumnConstant.PARKING_ID, requestDto.getParkingId());
            entityWrapper.eq(ColumnConstant.MAGNETIC_MANAGET_ID, 0L);
            if (null != requestDto.getCode()) {
                entityWrapper.like(ColumnConstant.CODE, requestDto.getCode());
            }
            Page<MagneticDetectorEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<MagneticDetectorEntity> magneticDetectorEntityPage = magneticDetectorCrudService.selectPage(page, entityWrapper);
            List<MagneticDetectorRelevanceResultDto> magneticDetectorNotRelevanceResultDtoList = new ArrayList<>();
            if (null != magneticDetectorEntityPage.getRecords()) {
                for (MagneticDetectorEntity magneticDetectorEntity : magneticDetectorEntityPage.getRecords()) {
                    MagneticDetectorRelevanceResultDto magneticDetectorNotRelevanceResultDto = new MagneticDetectorRelevanceResultDto();
                    magneticDetectorNotRelevanceResultDto.setCode(magneticDetectorEntity.getCode());
                    //停车场
                    parkingGetRequestDto.setId(magneticDetectorEntity.getParkingId());
                    ObjectResultDto<ParkingResultDto> parking = parkingInfoService.getParkingInfo(parkingGetRequestDto);
                    if (null != parking.getData()) {
                        magneticDetectorNotRelevanceResultDto.setParkingName(parking.getData().getFullName());
                    }
                    //泊位
                    if (0 != magneticDetectorEntity.getParkingLotId()) {
                        parkingLotListGetRequestDto.setParkingId(parking.getData().getId());
                        parkingLotListGetRequestDto.setId(magneticDetectorEntity.getParkingLotId());
                        ListResultDto<ParkingLotResultDto> parkingLotResultDtoListResultDto = parkingLotInfoService.getParkingLotList(parkingLotListGetRequestDto);
                        magneticDetectorNotRelevanceResultDto.setParkingLotNumber(parkingLotResultDtoListResultDto.getItems().get(0).getNumber());
                    }
                    //厂商
                    String provider = getProvider(magneticDetectorEntity);
                    magneticDetectorNotRelevanceResultDto.setProvider(provider);
                    magneticDetectorNotRelevanceResultDto.setCode(magneticDetectorEntity.getCode());
                    magneticDetectorNotRelevanceResultDto.setElectricity(magneticDetectorEntity.getElectricity());
                    magneticDetectorNotRelevanceResultDto.setSerialNumber(magneticDetectorEntity.getSerialNumber());
                    //状态
                    if (null != magneticDetectorEntity.getStatus()) {
                        String status = getMagneticDetectorStatus(magneticDetectorEntity);
                        magneticDetectorNotRelevanceResultDto.setStatus(status);
                    }
                    magneticDetectorNotRelevanceResultDtoList.add(magneticDetectorNotRelevanceResultDto);
                }
                pagedResultDto.setPageNo(magneticDetectorEntityPage.getCurrent());
                pagedResultDto.setPageSize(magneticDetectorEntityPage.getSize());
                pagedResultDto.setTotalCount(magneticDetectorEntityPage.getTotal());
                pagedResultDto.setItems(magneticDetectorNotRelevanceResultDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页查询未关联设备列表失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 分页查询已关联管理器设备
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<MagneticDetectorRelevanceResultDto> getRelevanceMagneticDetectorPagedList(MagneticDetectorRelevanceQueryPageRequestDto requestDto) {
        PagedResultDto<MagneticDetectorRelevanceResultDto> pagedResultDto = new PagedResultDto<>();
        EntityWrapper<MagneticDetectorEntity> entityWrapper = new EntityWrapper<>();
        ParkingGetRequestDto parkingGetRequestDto = new ParkingGetRequestDto();
        ParkingLotListGetRequestDto parkingLotListGetRequestDto = new ParkingLotListGetRequestDto();
        try {
            if (requestDto.getManagerId() != 0) {
                entityWrapper.eq(ColumnConstant.MAGNETIC_MANAGET_ID, requestDto.getManagerId());
                entityWrapper.eq(ColumnConstant.PARKING_ID, requestDto.getParkingId());
            } else {
                pagedResultDto = new PagedResultDto(requestDto.getPageNo(), requestDto.getPageSize(), new ArrayList<>(), 0L);
                pagedResultDto.success();
                return pagedResultDto;
            }
            Page<MagneticDetectorEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<MagneticDetectorEntity> magneticDetectorEntityPage = magneticDetectorCrudService.selectPage(page, entityWrapper);
            List<MagneticDetectorRelevanceResultDto> magneticDetectorRelevanceResultDtoList = new ArrayList<>();
            if (null != magneticDetectorEntityPage.getRecords()) {
                for (MagneticDetectorEntity magneticDetectorEntity : magneticDetectorEntityPage.getRecords()) {
                    MagneticDetectorRelevanceResultDto magneticDetectorRelevanceResultDto = new MagneticDetectorRelevanceResultDto();
                    //停车场
                    parkingGetRequestDto.setId(magneticDetectorEntity.getParkingId());
                    ObjectResultDto<ParkingResultDto> parking = parkingInfoService.getParkingInfo(parkingGetRequestDto);
                    if (null != parking.getData()) {
                        magneticDetectorRelevanceResultDto.setParkingName(parking.getData().getFullName());
                    }
                    //泊位
                    if (0 != magneticDetectorEntity.getParkingLotId()) {
                        parkingLotListGetRequestDto.setParkingId(parking.getData().getId());
                        parkingLotListGetRequestDto.setId(magneticDetectorEntity.getParkingLotId());
                        ListResultDto<ParkingLotResultDto> parkingLotResultDtoListResultDto = parkingLotInfoService.getParkingLotList(parkingLotListGetRequestDto);
                        magneticDetectorRelevanceResultDto.setParkingLotNumber(parkingLotResultDtoListResultDto.getItems().get(0).getNumber());
                    }
                    //厂商
                    String provider = getProvider(magneticDetectorEntity);
                    magneticDetectorRelevanceResultDto.setProvider(provider);
                    magneticDetectorRelevanceResultDto.setCode(magneticDetectorEntity.getCode());
                    magneticDetectorRelevanceResultDto.setElectricity(magneticDetectorEntity.getElectricity());
                    magneticDetectorRelevanceResultDto.setSerialNumber(magneticDetectorEntity.getSerialNumber());
                    //状态
                    if (null != magneticDetectorEntity.getStatus()) {
                        String status = getMagneticDetectorStatus(magneticDetectorEntity);
                        magneticDetectorRelevanceResultDto.setStatus(status);
                        magneticDetectorRelevanceResultDtoList.add(magneticDetectorRelevanceResultDto);
                    }
                }
                pagedResultDto.setPageNo(magneticDetectorEntityPage.getCurrent());
                pagedResultDto.setPageSize(magneticDetectorEntityPage.getSize());
                pagedResultDto.setTotalCount(magneticDetectorEntityPage.getTotal());
                pagedResultDto.setItems(magneticDetectorRelevanceResultDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页查询已关联设备失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 分页获取地磁检测器状态
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<MagneticDetectorStatusQueryPageResultDto> getMagneticDetectorStatusPagedList(MagneticDetectorStatusQueryPageRequestDto requestDto) {
        PagedResultDto<MagneticDetectorStatusQueryPageResultDto> pagedResultDto = new PagedResultDto<>();
        ParkingListGetRequestDto parkingListGetRequestDto = new ParkingListGetRequestDto();
        EntityWrapper<MagneticDetectorEntity> entityWrapper = new EntityWrapper<>();
        try {
            //区域
            if (!StringUtils.isEmpty(requestDto.getAreaCode())) {
                parkingListGetRequestDto.setAreaCode(requestDto.getAreaCode());
            }
            //停车场类型
            if (!StringUtils.isEmpty(requestDto.getLotType())) {
                parkingListGetRequestDto.setLotType(requestDto.getLotType());
            }
            //停车场名称
            if (!StringUtils.isEmpty(requestDto.getParkingName())) {
                parkingListGetRequestDto.setName(requestDto.getParkingName());
            }
            //获取停车场列表
            List<Long> parkingIds = new ArrayList<>();
            ListResultDto<ParkingListGetResultDto> parkingList = parkingInfoService.getParkingList(parkingListGetRequestDto);
            if (CollectionUtils.isNotEmpty(parkingList.getItems())) {
                for (ParkingListGetResultDto parkingListGetResultDto : parkingList.getItems()) {
                    parkingIds.add(parkingListGetResultDto.getId());
                }
                if (CollectionUtils.isNotEmpty(parkingIds)) {
                    entityWrapper.in(ColumnConstant.PARKING_ID, parkingIds);
                }
            } else {
                pagedResultDto = new PagedResultDto(requestDto.getPageNo(), requestDto.getPageSize(), new ArrayList<>(), 0L);
                pagedResultDto.success();
                return pagedResultDto;
            }
            Page<MagneticDetectorEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<MagneticDetectorEntity> pageResultDtoPage = magneticDetectorCrudService.selectPage(page, entityWrapper);
            if (null != pageResultDtoPage) {
                List<MagneticDetectorStatusQueryPageResultDto> magneticDetectorStatusQueryPageResultDtoList = new ArrayList<>();
                for (MagneticDetectorEntity magneticDetectorEntity : pageResultDtoPage.getRecords()) {
                    MagneticDetectorStatusQueryPageResultDto magneticDetectorStatusQueryPageResultDto = buildMagneticDetectorSearch(magneticDetectorEntity);
                    magneticDetectorStatusQueryPageResultDtoList.add(magneticDetectorStatusQueryPageResultDto);
                }
                pagedResultDto.setPageNo(pageResultDtoPage.getCurrent());
                pagedResultDto.setPageSize(pageResultDtoPage.getSize());
                pagedResultDto.setTotalCount(pageResultDtoPage.getTotal());
                pagedResultDto.setItems(magneticDetectorStatusQueryPageResultDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页获取地磁检测器状态失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 分页查询未关联泊位设备
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<MagneticDetectorRelevanceParkingLotResultDto> getNotRelevanceParkingLotMagneticDetectorPagedList(MagneticDetectorNotRelevanceParkingLotQueryPageRequestDto requestDto) {
        PagedResultDto<MagneticDetectorRelevanceParkingLotResultDto> pagedResultDto = new PagedResultDto<>();
        EntityWrapper<MagneticDetectorEntity> entityWrapper = new EntityWrapper<>();
        try {
            entityWrapper.eq(ColumnConstant.PARKING_LOT_ID, 0L);
            entityWrapper.eq(ColumnConstant.PARKING_ID, requestDto.getParkingId());
            if (null != requestDto.getCode()) {
                entityWrapper.like(ColumnConstant.CODE, requestDto.getCode());
            }
            Page<MagneticDetectorEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<MagneticDetectorEntity> magneticDetectorEntityPage = magneticDetectorCrudService.selectPage(page, entityWrapper);
            List<MagneticDetectorRelevanceParkingLotResultDto> magneticDetectorStatusQueryPageResultDtoList = new ArrayList<>();
            if (null != magneticDetectorEntityPage.getRecords()) {
                for (MagneticDetectorEntity magneticDetectorEntity : magneticDetectorEntityPage.getRecords()) {
                    MagneticDetectorRelevanceParkingLotResultDto detector = getDetector(magneticDetectorEntity);
                    magneticDetectorStatusQueryPageResultDtoList.add(detector);
                }
                pagedResultDto.setPageNo(magneticDetectorEntityPage.getCurrent());
                pagedResultDto.setPageSize(magneticDetectorEntityPage.getSize());
                pagedResultDto.setTotalCount(magneticDetectorEntityPage.getTotal());
                pagedResultDto.setItems(magneticDetectorStatusQueryPageResultDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页查询未关联泊位设备失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 分页查询已关联泊位设备
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<MagneticDetectorRelevanceParkingLotResultDto> getRelevanceParkingLotMagneticDetectorPagedList(MagneticDetectorRelevanceParkingLotQueryPageRequestDto requestDto) {
        PagedResultDto<MagneticDetectorRelevanceParkingLotResultDto> pagedResultDto = new PagedResultDto<>();
        EntityWrapper<MagneticDetectorEntity> entityWrapper = new EntityWrapper<>();
        try {
            if (requestDto.getParkingLotId() != 0) {
                entityWrapper.eq(ColumnConstant.PARKING_LOT_ID, requestDto.getParkingLotId());
                entityWrapper.eq(ColumnConstant.PARKING_ID, requestDto.getParkingId());
            } else {
                pagedResultDto = new PagedResultDto(requestDto.getPageNo(), requestDto.getPageSize(), new ArrayList<>(), 0L);
                pagedResultDto.success();
                return pagedResultDto;
            }
            Page<MagneticDetectorEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<MagneticDetectorEntity> magneticDetectorEntityPage = magneticDetectorCrudService.selectPage(page, entityWrapper);
            List<MagneticDetectorRelevanceParkingLotResultDto> magneticDetectorRelevanceResultDtoList = new ArrayList<>();
            if (null != magneticDetectorEntityPage.getRecords()) {
                for (MagneticDetectorEntity magneticDetectorEntity : magneticDetectorEntityPage.getRecords()) {
                    MagneticDetectorRelevanceParkingLotResultDto magneticDetectorRelevanceParkingLotResultDto = getDetector(magneticDetectorEntity);
                    magneticDetectorRelevanceResultDtoList.add(magneticDetectorRelevanceParkingLotResultDto);
                }
                pagedResultDto.setPageNo(magneticDetectorEntityPage.getCurrent());
                pagedResultDto.setPageSize(magneticDetectorEntityPage.getSize());
                pagedResultDto.setTotalCount(magneticDetectorEntityPage.getTotal());
                pagedResultDto.setItems(magneticDetectorRelevanceResultDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页查询已关联设备" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 巡检获取地磁检测器
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<MagneticDetectorGetByInspectResultDto> getMagneticDetectorByInspect(MagneticDetectorGetByInspectRequestDto requestDto) {
        ObjectResultDto<MagneticDetectorGetByInspectResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            Wrapper<MagneticDetectorEntity> entityEntityWrapper = new EntityWrapper<>();
            entityEntityWrapper.eq(ColumnConstant.PARKING_ID, requestDto.getParkingId());
            entityEntityWrapper.eq(ColumnConstant.PARKING_LOT_ID, requestDto.getParkingLotId());
            MagneticDetectorEntity magneticDetectorEntity = magneticDetectorCrudService.selectOne(entityEntityWrapper);
            if (null != magneticDetectorEntity) {
                MagneticDetectorGetByInspectResultDto magneticDetectorGetByInspecResultDto = new MagneticDetectorGetByInspectResultDto();
                magneticDetectorGetByInspecResultDto.setId(magneticDetectorEntity.getId());
                magneticDetectorGetByInspecResultDto.setProvider(magneticDetectorEntity.getProvider());
                magneticDetectorGetByInspecResultDto.setSerialNumber(magneticDetectorEntity.getSerialNumber());
                objectResultDto.setData(magneticDetectorGetByInspecResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("巡检获取地磁检测器" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取检测器厂商，厂商
     *
     * @param magneticDetectorEntity
     * @return
     */
    public MagneticDetectorRelevanceParkingLotResultDto getDetector(MagneticDetectorEntity magneticDetectorEntity) {
        MagneticDetectorRelevanceParkingLotResultDto magneticDetectorRelevanceParkingLotResultDto = new MagneticDetectorRelevanceParkingLotResultDto();
        magneticDetectorRelevanceParkingLotResultDto.setCode(magneticDetectorEntity.getCode());
        magneticDetectorRelevanceParkingLotResultDto.setSerialNumber(magneticDetectorEntity.getSerialNumber());
        //厂商
        String provider = getProvider(magneticDetectorEntity);
        magneticDetectorRelevanceParkingLotResultDto.setProvider(provider);
        //状态
        if (null != magneticDetectorEntity.getStatus()) {
            String status = getMagneticDetectorStatus(magneticDetectorEntity);
            magneticDetectorRelevanceParkingLotResultDto.setStatus(status);
        }
        return magneticDetectorRelevanceParkingLotResultDto;
    }

    /**
     * 查询停车场名称、状态、区域名称
     *
     * @param magneticDetectorEntity
     * @return
     */
    private MagneticDetectorStatusQueryPageResultDto buildMagneticDetectorSearch(MagneticDetectorEntity magneticDetectorEntity) {
        MagneticDetectorStatusQueryPageResultDto magneticDetectorStatusQueryPageResultDto = new MagneticDetectorStatusQueryPageResultDto();
        magneticDetectorStatusQueryPageResultDto.setSerialNumber(magneticDetectorEntity.getSerialNumber());
        //富尚电量状态
        if (magneticDetectorEntity.getProvider().equals(DeviceProviderEnum.FUSHANG.getValue())) {
            BatteryTypeEnum batteryTypeEnum = BatteryTypeEnum.parse(magneticDetectorEntity.getElectricity());
            if (batteryTypeEnum != null) {
                magneticDetectorStatusQueryPageResultDto.setElectricity(batteryTypeEnum.getComment());
            }
        } else {
            if (magneticDetectorEntity.getElectricity() == null) {
                magneticDetectorStatusQueryPageResultDto.setElectricity("0");
            } else {
                magneticDetectorStatusQueryPageResultDto.setElectricity(String.valueOf(magneticDetectorEntity.getElectricity()));
            }
        }
        //停车场
        ParkingGetRequestDto parkingGetRequestDto = new ParkingGetRequestDto();
        parkingGetRequestDto.setId(magneticDetectorEntity.getParkingId());
        ObjectResultDto<ParkingResultDto> parkingObjectResultDto = parkingInfoService.getParkingInfo(parkingGetRequestDto);
        if (null != parkingObjectResultDto.getData() && parkingObjectResultDto.isSuccess()) {
            magneticDetectorStatusQueryPageResultDto.setParkingName(parkingObjectResultDto.getData().getFullName());
        }
        //地磁管理器序列号
        if (0 != magneticDetectorEntity.getManagerId()) {
            MagneticManagerEntity magneticManagerEntity = magneticManagerCrudService.selectById(magneticDetectorEntity.getManagerId());
            magneticDetectorStatusQueryPageResultDto.setManagerSerialNumber(magneticManagerEntity.getSerialNumber());
        }
        //地磁检测器状态
        if (null != magneticDetectorEntity.getStatus()) {
            String status = getMagneticDetectorStatus(magneticDetectorEntity);
            magneticDetectorStatusQueryPageResultDto.setStatus(status);
        }
        //区域
        ParkingInfoGetByIdRequestDto parkingInfoGetByIdRequestDto = new ParkingInfoGetByIdRequestDto();
        parkingInfoGetByIdRequestDto.setId(magneticDetectorEntity.getParkingId());
        ObjectResultDto<ParkingByIdResultDto> parkingById = parkingInfoService.getParkingById(parkingInfoGetByIdRequestDto);
        magneticDetectorStatusQueryPageResultDto.setAreaName(parkingById.getData().getAreaPath());
        //厂家
        String provider = getProvider(magneticDetectorEntity);
        magneticDetectorStatusQueryPageResultDto.setProvider(provider);
        return magneticDetectorStatusQueryPageResultDto;
    }

    /**
     * 获取厂商
     *
     * @param magneticDetectorEntity
     * @return
     */
    public String getProvider(MagneticDetectorEntity magneticDetectorEntity) {
        String comment = null;
        DeviceProviderEnum deviceProviderEnum = DeviceProviderEnum.parse(magneticDetectorEntity.getProvider());
        if (deviceProviderEnum != null) {
            comment = deviceProviderEnum.getComment();
        }
        return comment;
    }

    /**
     * 获取地磁检测器状态
     *
     * @return
     */
    public String getMagneticDetectorStatus(MagneticDetectorEntity magneticDetectorEntity) {
        String parse = null;
        MagneticDetectorStatusEnum statusEnum = MagneticDetectorStatusEnum.parse(magneticDetectorEntity.getStatus());
        if (statusEnum != null) {
            parse = statusEnum.getComment();
        }
        return parse;
    }

    /**
     * 根据泊位id获取设备
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<MagneticDetectorByCodeResultDto> getMagneticDetectorListByParkingLotId(MagneticDetectorParkingLotGetRequestDto requestDto) {
        ListResultDto<MagneticDetectorByCodeResultDto> listResultDto = new ListResultDto<>();
        try {
            EntityWrapper<MagneticDetectorEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parkingLotId", requestDto.getParkingLotId());
            List<MagneticDetectorEntity> list = magneticDetectorCrudService.selectList(entityWrapper);
            List<MagneticDetectorByCodeResultDto> dtos = modelMapper.map(list, new TypeToken<List<MagneticDetectorByCodeResultDto>>() {
            }.getType());
            listResultDto.setItems(dtos);
            listResultDto.success();
        } catch (Exception e) {
            log.error("根据泊位id获取设备失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }
}
