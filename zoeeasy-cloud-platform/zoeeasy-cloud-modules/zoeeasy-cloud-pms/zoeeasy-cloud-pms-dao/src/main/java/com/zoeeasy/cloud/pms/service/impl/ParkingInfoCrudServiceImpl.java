package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoAroundEntity;
import com.zoeeasy.cloud.pms.enums.ParkingStatusEnum;
import com.zoeeasy.cloud.pms.mapper.ParkingInfoMapper;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by song on 2018/9/18.
 */
@Service("parkingInfoCrudService")
public class ParkingInfoCrudServiceImpl extends ServiceImpl<ParkingInfoMapper, ParkingInfoEntity> implements ParkingInfoCrudService {

    @Override
    public ParkingInfoEntity findByCode(String code) {
        ParkingInfoEntity parkingInfoEntity = new ParkingInfoEntity();
        parkingInfoEntity.setCode(code);
        return baseMapper.selectOne(parkingInfoEntity);
    }

    @Override
    public ParkingInfoEntity selectByCode(String code) {
        return baseMapper.findByCode(code);
    }

    @Override
    public ParkingInfoEntity findByFullName(String fullName) {
        ParkingInfoEntity parkingInfoEntity = new ParkingInfoEntity();
        parkingInfoEntity.setFullName(fullName);
        return baseMapper.selectOne(parkingInfoEntity);
    }

    @Override
    public List<ParkingInfoEntity> selectAllParkingInfoList() {
        EntityWrapper<ParkingInfoEntity> entityWrapper = new EntityWrapper<>();
        return baseMapper.selectList(entityWrapper);
    }

    /*
     * ***********************************************************
     * 租户无关操作
     * ***********************************************************
     */

    /**
     * 根据停车场id获取停车场信息
     *
     * @param parkingId
     * @return
     */
    @Override
    public ParkingInfoEntity getParkInfoById(Long parkingId) {
        return baseMapper.findByParkingId(parkingId);
    }

    /**
     * 通过海康平台ID查找
     *
     * @param hikParkId 海康平台ID
     * @return
     */
    @Override
    public ParkingInfoEntity findByHikParkId(String hikParkId) {
        return baseMapper.findByHikParkId(hikParkId);
    }

    @Override
    public ParkingInfoEntity selectByLocalCode(String localCode) {
        return baseMapper.findByLocalCode(localCode);
    }

    /**
     * 通过位置查询
     *
     * @param wrapper
     * @param minLongitude
     * @param maxLongitude
     * @param minLatitude
     * @param maxLatitude
     * @return
     */
    @Override
    public List<ParkingInfoEntity> selectListByPosition(Wrapper<ParkingInfoEntity> wrapper, Double minLongitude, Double maxLongitude, Double minLatitude, Double maxLatitude) {

        wrapper.ge("longitude", minLongitude);
        wrapper.le("longitude", maxLongitude);
        wrapper.ge("latitude", minLatitude);
        wrapper.le("latitude", maxLatitude);
        wrapper.eq("status", ParkingStatusEnum.ON_LINE.getValue());
        return baseMapper.selectListTenantLess(wrapper);
    }

    /**
     * 停车场附近功能查询
     *
     * @param minLongitude
     * @param maxLongitude
     * @param minLatitude
     * @param maxLatitude
     * @return
     */
    @Override
    public List<ParkingInfoAroundEntity> selectAroundListByPosition(Wrapper<ParkingInfoEntity> wrapper, Double minLongitude, Double maxLongitude, Double minLatitude, Double maxLatitude) {
        wrapper.ge("longitude", minLongitude);
        wrapper.le("longitude", maxLongitude);
        wrapper.ge("latitude", minLatitude);
        wrapper.le("latitude", maxLatitude);
        wrapper.eq("status", ParkingStatusEnum.ON_LINE.getValue());
        return baseMapper.selectAroundListTenantLess(wrapper);
    }


    @Override
    public ParkingInfoEntity selectPlatformParkingInfo(EntityWrapper<ParkingInfoEntity> wrapper) {
        List<ParkingInfoEntity> parkingInfoEntityList = baseMapper.selectListTenantLess(wrapper);
        if (!parkingInfoEntityList.isEmpty()) {
            return parkingInfoEntityList.get(0);
        }
        return null;
    }

    @Override
    public List<ParkingInfoEntity> selectPlatformParkingInfoList(EntityWrapper<ParkingInfoEntity> ew) {
        return baseMapper.selectListTenantLess(ew);
    }

    @Override
    public List<ParkingInfoEntity> selectPlatformParkingInfoPageList(Pagination page, Wrapper<ParkingInfoEntity> ew) {
        return baseMapper.selectPageListTenantLess(page, ew);
    }

    @Override
    public boolean updateParkInfoById(ParkingInfoEntity parkingInfoEntity) {
        return baseMapper.updateParkInfoNonTenant(parkingInfoEntity);
    }

    @Override
    public boolean updateParkingAuditStatus(Long parkingId) {
        return baseMapper.updateParkingAuditStatus(parkingId);
    }

    /**
     * 修改停车场
     *
     * @param parkingInfoEntity
     * @param entityWrapper
     * @return
     */
    @Override
    public boolean updateParkingInfo(ParkingInfoEntity parkingInfoEntity, EntityWrapper<ParkingInfoEntity> entityWrapper) {
        return baseMapper.updateParkingInfo(parkingInfoEntity, entityWrapper);
    }

    /**
     * 停车场管理系统查询
     *
     * @param code
     * @param tenantId
     * @return
     */
    @Override
    public ParkingInfoEntity findByCodeAndTenantId(String code, Long tenantId) {
        EntityWrapper<ParkingInfoEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("tenantId", tenantId);
        entityWrapper.eq("code", code);
        List<ParkingInfoEntity> parkingInfoEntityList = baseMapper.selectListTenantLess(entityWrapper);
        if (!parkingInfoEntityList.isEmpty()) {
            return parkingInfoEntityList.get(0);
        }
        return null;
    }
}
