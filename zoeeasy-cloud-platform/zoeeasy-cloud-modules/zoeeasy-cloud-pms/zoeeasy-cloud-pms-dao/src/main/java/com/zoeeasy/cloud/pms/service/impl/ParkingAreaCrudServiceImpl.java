package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.pms.domain.ParkingAreaEntity;
import com.zoeeasy.cloud.pms.mapper.ParkingAreaMapper;
import com.zoeeasy.cloud.pms.service.ParkingAreaCrudService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by song on 2018/9/21.
 */
@Service("parkingAreaCrudService")
public class ParkingAreaCrudServiceImpl extends ServiceImpl<ParkingAreaMapper, ParkingAreaEntity> implements ParkingAreaCrudService {
    @Override
    public ParkingAreaEntity findByName(String name) {
        ParkingAreaEntity parkingAreaEntity = new ParkingAreaEntity();
        parkingAreaEntity.setName(name);
        return baseMapper.selectOne(parkingAreaEntity);
    }

    @Override
    public ParkingAreaEntity findByCode(String code) {
        ParkingAreaEntity parkingAreaEntity = new ParkingAreaEntity();
        parkingAreaEntity.setCode(code);
        return baseMapper.selectOne(parkingAreaEntity);
    }

    @Override
    public Integer findLotTotalByParkingId(Long parkingId) {
        return baseMapper.selectLotTotalByParkingId(parkingId);
    }

    @Override
    public Integer findLotFixedTotalByParkingId(Long parkingId) {
        return baseMapper.selectLotFixedTotalByParkingId(parkingId);
    }

    @Override
    public Integer findCountByGarageId(Long garageId) {
        EntityWrapper<ParkingAreaEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("garageId", garageId);
        return baseMapper.selectCount(entityWrapper);
    }

    @Override
    public ParkingAreaEntity findByNameAndGarageId(String name, Long garageId) {
        ParkingAreaEntity parkingAreaEntity = new ParkingAreaEntity();
        parkingAreaEntity.setName(name);
        parkingAreaEntity.setGarageId(garageId);
        return baseMapper.selectOne(parkingAreaEntity);
    }

    @Override
    public ParkingAreaEntity findByNameAndParkingId(String name, Long parkingId) {
        EntityWrapper<ParkingAreaEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("name", name);
        entityWrapper.eq("parkingId", parkingId);
        entityWrapper.isNull("garageId");
        List<ParkingAreaEntity> list = baseMapper.selectList(entityWrapper);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public ParkingAreaEntity findByCodeAndParkingId(String code, Long parkingId) {
        ParkingAreaEntity parkingAreaEntity = new ParkingAreaEntity();
        parkingAreaEntity.setCode(code);
        parkingAreaEntity.setParkingId(parkingId);
        return baseMapper.selectOne(parkingAreaEntity);
    }

    @Override
    public ParkingAreaEntity getParkingAreaInfo(Long id) {
        return baseMapper.getParkingAreaInfo(id);
    }

    /**
     * 删除泊车区域(无租户)
     *
     * @param parkingId
     * @return
     */
    @Override
    public boolean deleteParkingAreaNonTenant(Long parkingId) {
        return baseMapper.deleteParkingAreaNonTenant(parkingId);
    }

    @Override
    public ParkingAreaEntity selectParkingAreaNonTenant(String code) {
        EntityWrapper<ParkingAreaEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("code", code);
        return baseMapper.selectParkingAreaNonTenant(entityWrapper);
    }

    @Override
    public ParkingAreaEntity selectByNameAndGarageId(String name, Long garageId) {
        EntityWrapper<ParkingAreaEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("name", name);
        entityWrapper.eq("garageId", garageId);
        return baseMapper.selectParkingAreaNonTenant(entityWrapper);
    }

    @Override
    public ParkingAreaEntity selectByNameAndParkingId(String name, Long parkingId) {
        EntityWrapper<ParkingAreaEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("name", name);
        entityWrapper.eq("parkingId", parkingId);
        return baseMapper.selectParkingAreaNonTenant(entityWrapper);
    }

    @Override
    public ParkingAreaEntity selectByCodeAndParkingId(String code, Long parkingId) {
        EntityWrapper<ParkingAreaEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("code", code);
        entityWrapper.eq("parkingId", parkingId);
        return baseMapper.selectParkingAreaNonTenant(entityWrapper);
    }

    @Override
    public ParkingAreaEntity selectByCode(String code) {
        EntityWrapper<ParkingAreaEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("code", code);
        return baseMapper.selectParkingAreaNonTenant(entityWrapper);
    }

    @Override
    public Integer findLotTotalByParkingIdNonTenant(Long parkingId) {
        return baseMapper.selectLotTotalByParkingIdNonTenant(parkingId);
    }

    @Override
    public Integer findLotFixedTotalByParkingIdNonTenant(Long parkingId) {
        return baseMapper.selectLotFixedTotalByParkingIdNonTenant(parkingId);
    }

    @Override
    public boolean updateParkingAreaNonTenant(ParkingAreaEntity parkingAreaEntity) {
        return baseMapper.updateParkingAreaNonTenant(parkingAreaEntity);
    }
}
