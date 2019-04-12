package com.zoeeasy.cloud.gather.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.gather.domain.HikvisionVehicleRecordEntity;
import com.zoeeasy.cloud.gather.mapper.HikvisionVehicleRecordMapper;
import com.zoeeasy.cloud.gather.service.HikvisionVehicleRecordCrudService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author walkman
 */
@Service("hikvisionVehicleRecordCrudService")
public class HikvisionVehicleRecordCrudServiceImpl extends ServiceImpl<HikvisionVehicleRecordMapper, HikvisionVehicleRecordEntity> implements HikvisionVehicleRecordCrudService {

    /**
     * 通过passingUuid查找
     *
     * @param passingUuid passingUuid
     * @return
     */
    @Override
    public HikvisionVehicleRecordEntity findByPassingUuid(String passingUuid) {
        HikvisionVehicleRecordEntity hikvisionVehicleRecord = new HikvisionVehicleRecordEntity();
        hikvisionVehicleRecord.setPassingUuid(passingUuid);
        return baseMapper.selectOne(hikvisionVehicleRecord);
    }

    /**
     * 查找唯一的过车记录
     *
     * @param uuid uuid
     * @return 过车记录
     */
    @Override
    public HikvisionVehicleRecordEntity findOne(String uuid) {
        if (StringUtils.isEmpty(uuid)) {
            return null;
        }
        EntityWrapper<HikvisionVehicleRecordEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("uuid", uuid);
        entityWrapper.last("LIMIT 1");
        List<HikvisionVehicleRecordEntity> vehicleRecords = baseMapper.selectList(entityWrapper);
        if (CollectionUtils.isNotEmpty(vehicleRecords)) {
            return vehicleRecords.get(0);
        }
        return null;
    }

    /**
     * 查找唯一的过车记录
     *
     * @param uuid        uuid
     * @param parkCode    parkCode
     * @param plateNumber plateNumber
     * @param passDirect  passDirect
     * @param passTime    passTime
     * @return
     */
    @Override
    public HikvisionVehicleRecordEntity findOne(String uuid, String parkCode, String plateNumber, Integer passDirect, Date passTime) {
        if (StringUtils.isEmpty(uuid)) {
            return null;
        }
        EntityWrapper<HikvisionVehicleRecordEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("uuid", uuid);
        if (StringUtils.isNotEmpty(parkCode)) {
            entityWrapper.eq("parkCode", parkCode);
        }
        if (StringUtils.isNotEmpty(plateNumber)) {
            entityWrapper.eq("plateNumber", plateNumber);
        }
        if (passDirect != null) {
            entityWrapper.eq("passDirect", passDirect);
        }
        if (passTime != null) {
            entityWrapper.eq("passTime", passTime);
        }
        entityWrapper.last("LIMIT 1");
        List<HikvisionVehicleRecordEntity> vehicleRecords = baseMapper.selectList(entityWrapper);
        if (CollectionUtils.isNotEmpty(vehicleRecords)) {
            return vehicleRecords.get(0);
        }
        return null;
    }

    /**
     * 查找唯一的过车记录
     *
     * @param uuid        uuid
     * @param parkCode    parkCode
     * @param plateNumber plateNumber
     * @param passDirect  passDirect
     * @param passTime    passTime
     * @return
     */
    @Override
    public Integer findCount(String uuid, String parkCode, String plateNumber, Integer passDirect, Date passTime) {
        if (StringUtils.isEmpty(uuid)) {
            return 0;
        }
        EntityWrapper<HikvisionVehicleRecordEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("uuid", uuid);
        if (StringUtils.isNotEmpty(parkCode)) {
            entityWrapper.eq("parkCode", parkCode);
        }
        if (StringUtils.isNotEmpty(plateNumber)) {
            entityWrapper.eq("plateNumber", plateNumber);
        }
        if (passDirect != null) {
            entityWrapper.eq("passDirect", passDirect);
        }
        if (passTime != null) {
            entityWrapper.eq("passTime", passTime);
        }
        return baseMapper.selectCount(entityWrapper);
    }

    /**
     * 更新处理状态
     *
     * @param passingUuid   passingUuid
     * @param processStatus processStatus
     * @param processRemark processRemark
     * @return
     */
    @Override
    public Integer updateProcessStatus(String passingUuid, Integer processStatus, String processRemark) {

        if (StringUtils.isEmpty(passingUuid)) {
            return 0;
        }
        return baseMapper.updateProcessStatus(passingUuid, processStatus, processRemark);
    }
}
