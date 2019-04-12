package com.zoeeasy.cloud.pds.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
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
import com.zoeeasy.cloud.pds.domain.MagneticManagerEntity;
import com.zoeeasy.cloud.pds.enums.MagneticDetectorRegisteredStatusEnum;
import com.zoeeasy.cloud.pds.enums.MagneticManagerEnum;
import com.zoeeasy.cloud.pds.magneticdetector.cst.ColumnConstant;
import com.zoeeasy.cloud.pds.magneticmanager.MagneticManagerService;
import com.zoeeasy.cloud.pds.magneticmanager.dto.request.*;
import com.zoeeasy.cloud.pds.magneticmanager.dto.result.MagneticManagerByIdResultDto;
import com.zoeeasy.cloud.pds.magneticmanager.dto.result.MagneticManagerResultDto;
import com.zoeeasy.cloud.pds.magneticmanager.validator.MagneticManagerAddRequestDtoValidator;
import com.zoeeasy.cloud.pds.magneticmanager.validator.MagneticManagerDeleteRequestDtoValidator;
import com.zoeeasy.cloud.pds.magneticmanager.validator.MagneticManagerGetRequestDtoValidator;
import com.zoeeasy.cloud.pds.magneticmanager.validator.MagneticManagerUpdateRequestDtoValidator;
import com.zoeeasy.cloud.pds.service.MagneticManagerCrudService;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingListGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingListGetResultDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备管理器服务
 *
 * @Date: 2018/10/19
 * @author: lhj
 */
@Service("magneticManagerService")
@Slf4j
public class MagneticManagerServiceImpl implements MagneticManagerService {

    @Autowired
    private MagneticManagerCrudService magneticManagerCrudService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Autowired
    private LockFactory lockFactory;

    /**
     * 新增停车设备管理器
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addMagneticManager(@FluentValid({MagneticManagerAddRequestDtoValidator.class}) MagneticManagerAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        LockInfo lockInfo = new LockInfo();
        lockInfo.setType(LockType.Fair);
        lockInfo.setName(getMagneticLockKey(requestDto.getCode()));
        lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
        lockInfo.setLeaseTime(LockInfo.DEFAULT_LOCK_LEASE_TIME);
        Lock lock = lockFactory.getLock(lockInfo);
        boolean lockAcquired = false;
        try {
            MagneticManagerEntity managerEntity = modelMapper.map(requestDto, MagneticManagerEntity.class);
            //是否注册，默认未注册
            managerEntity.setRegistered(Boolean.FALSE);
            lockAcquired = lock.acquire();
            if (lockAcquired) {
                magneticManagerCrudService.insert(managerEntity);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("设备管理器添加失败" + e.getMessage());
            resultDto.failed();
        } finally {
            if (lockAcquired) {
                lock.release();
            }
        }
        return resultDto;
    }

    private String getMagneticLockKey(String code) {
        return "lock:zoeeasy.pds.manager.manager_add_" + code;
    }

    /**
     * 删除停车设备管理器
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto deleteMagneticManager(@FluentValid({MagneticManagerDeleteRequestDtoValidator.class}) MagneticManagerDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            magneticManagerCrudService.deleteById(requestDto.getId());
            resultDto.success();
        } catch (Exception e) {
            log.error("设备管理器删除失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 更新设备管理器
     */
    @Override
    public ResultDto updateMagneticManager(@FluentValid({MagneticManagerUpdateRequestDtoValidator.class}) MagneticManagerUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            MagneticManagerEntity managerEntity = modelMapper.map(requestDto, MagneticManagerEntity.class);
            EntityWrapper<MagneticManagerEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq(ColumnConstant.ID, requestDto.getId());
            magneticManagerCrudService.update(managerEntity, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("设备管理器更新失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 根据ID获取停车设备管理器
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<MagneticManagerByIdResultDto> getMagneticManager(@FluentValid({MagneticManagerGetRequestDtoValidator.class}) MagneticManagerGetRequestDto requestDto) {
        ObjectResultDto<MagneticManagerByIdResultDto> objectResultDto = new ObjectResultDto();
        try {
            MagneticManagerEntity parkingDeviceSupervisor = magneticManagerCrudService.selectById(requestDto.getId());
            if (null == parkingDeviceSupervisor) {
                return objectResultDto.makeResult(MagneticManagerEnum.MAGNETIC_MANAGER_NOT_FOUND.getValue(),
                        MagneticManagerEnum.MAGNETIC_MANAGER_NOT_FOUND.getComment());
            }
            MagneticManagerByIdResultDto magneticManagerResultDto = modelMapper.map(parkingDeviceSupervisor, MagneticManagerByIdResultDto.class);
            //厂商
            DeviceProviderEnum deviceProviderEnum = DeviceProviderEnum.parse(parkingDeviceSupervisor.getProvider());
            if (deviceProviderEnum != null) {
                magneticManagerResultDto.setProvider(deviceProviderEnum.getComment());
            }
            objectResultDto.setData(magneticManagerResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("查询设备管理器失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 分页获取设备管理器列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<MagneticManagerResultDto> getMagneticManagerPageList(MagneticManagerQueryPageRequestDto requestDto) {
        PagedResultDto<MagneticManagerResultDto> pagedResultDto = new PagedResultDto<>();
        EntityWrapper<MagneticManagerEntity> entityWrapper = new EntityWrapper<>();
        try {
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
            //编号
            if (!StringUtils.isEmpty(requestDto.getCode())) {
                entityWrapper.like(ColumnConstant.CODE, requestDto.getCode());
            }
            Page<MagneticManagerEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<MagneticManagerEntity> magneticManagerEntityPage = magneticManagerCrudService.selectPage(page, entityWrapper);
            if (magneticManagerEntityPage.getRecords() != null) {
                List<MagneticManagerResultDto> magneticManagerResultDtos = new ArrayList<>();
                for (MagneticManagerEntity magneticManagerEntity : magneticManagerEntityPage.getRecords()) {
                    MagneticManagerResultDto magneticManagerResultDto = new MagneticManagerResultDto();
                    magneticManagerResultDto.setId(magneticManagerEntity.getId());
                    magneticManagerResultDto.setInstallPosition(magneticManagerEntity.getInstallPosition());
                    magneticManagerResultDto.setIpAddress(magneticManagerEntity.getIpAddress());
                    magneticManagerResultDto.setPort(magneticManagerEntity.getPort());
                    magneticManagerResultDto.setSimNo(magneticManagerEntity.getSimNo());
                    magneticManagerResultDto.setVersionNumber(magneticManagerEntity.getVersionNumber());
                    magneticManagerResultDto.setSerialNumber(magneticManagerEntity.getSerialNumber());
                    magneticManagerResultDto.setInstallationTime(magneticManagerEntity.getInstallationTime());
                    magneticManagerResultDto.setCode(magneticManagerEntity.getCode());
                    magneticManagerResultDto.setParkingId(magneticManagerEntity.getParkingId());
                    //厂商
                    String provider = getProvider(magneticManagerEntity);
                    magneticManagerResultDto.setProvider(provider);
                    //注册状态
                    if (magneticManagerEntity.getRegistered().equals(Boolean.TRUE)) {
                        magneticManagerResultDto.setRegistered(MagneticDetectorRegisteredStatusEnum.REGISTERED.getComment());
                    } else {
                        magneticManagerResultDto.setRegistered(MagneticDetectorRegisteredStatusEnum.UNREGISTERED.getComment());
                    }
                    magneticManagerResultDtos.add(magneticManagerResultDto);
                }
                pagedResultDto.setPageNo(magneticManagerEntityPage.getCurrent());
                pagedResultDto.setPageSize(magneticManagerEntityPage.getSize());
                pagedResultDto.setTotalCount(magneticManagerEntityPage.getTotal());
                pagedResultDto.setItems(magneticManagerResultDtos);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页获取设备管理器列表失败", e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 获取厂商
     *
     * @param magneticManagerEntity
     * @return
     */
    public String getProvider(MagneticManagerEntity magneticManagerEntity) {
        String comment = null;
        DeviceProviderEnum deviceProviderEnum = DeviceProviderEnum.parse(magneticManagerEntity.getProvider());
        if (deviceProviderEnum != null) {
            comment = deviceProviderEnum.getComment();
        }
        return comment;
    }
}
