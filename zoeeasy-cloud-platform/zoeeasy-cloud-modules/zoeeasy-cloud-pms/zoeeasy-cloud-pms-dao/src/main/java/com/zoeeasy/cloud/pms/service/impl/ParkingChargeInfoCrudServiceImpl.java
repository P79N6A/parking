package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.pms.domain.ParkingChargeInfoEntity;
import com.zoeeasy.cloud.pms.mapper.ParkingChargeInfoMapper;
import com.zoeeasy.cloud.pms.service.ParkingChargeInfoCrudService;
import org.springframework.stereotype.Service;

@Service("parkingChargeInfoCrudService")
public class ParkingChargeInfoCrudServiceImpl extends ServiceImpl<ParkingChargeInfoMapper, ParkingChargeInfoEntity> implements ParkingChargeInfoCrudService {

    @Override
    public ParkingChargeInfoEntity findByParkingId(Long parkingId) {
        Wrapper<ParkingChargeInfoEntity> entityEntityWrapper = new EntityWrapper<>();
        entityEntityWrapper.eq("parkingId", parkingId);
        entityEntityWrapper.eq("active", Boolean.TRUE);
        return baseMapper.findByParkingId(entityEntityWrapper);
    }

    @Override
    public ParkingChargeInfoEntity findById(Long id) {
        return baseMapper.findById(id);
    }

    /**
     * 添加ParkingChargeInfoEntity(无租户)
     *
     * @param parkingChargeInfoEntity
     * @return
     */
    @Override
    public boolean insertParkingChargeInfoNonTenant(ParkingChargeInfoEntity parkingChargeInfoEntity) {
        return baseMapper.insertParkingChargeInfoNonTenant(parkingChargeInfoEntity);
    }

    /**
     * 修改ParkingChargeInfoEntity(无租户)
     *
     * @param parkingChargeInfoEntity
     * @return
     */
    @Override
    public boolean updateParkingChargeInfoNonTenant(ParkingChargeInfoEntity parkingChargeInfoEntity) {
        return baseMapper.updateParkingChargeInfoNonTenant(parkingChargeInfoEntity);
    }

    /**
     * 修改ParkingChargeInfoEntity(无租户)
     *
     * @param parkingId
     * @return
     */
    @Override
    public boolean deleteParkingChargeInfoNonTenant(Long parkingId) {
        return baseMapper.deleteParkingChargeInfoNonTenant(parkingId);
    }
}
