package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.pms.domain.ParkingAppointInfoEntity;
import com.zoeeasy.cloud.pms.mapper.ParkingAppointInfoMapper;
import com.zoeeasy.cloud.pms.service.ParkingAppointInfoCrudService;
import org.springframework.stereotype.Service;

@Service("parkingAppointInfoCrudService")
public class ParkingAppointInfoCrudServiceImpl extends ServiceImpl<ParkingAppointInfoMapper, ParkingAppointInfoEntity> implements ParkingAppointInfoCrudService {

    @Override
    public ParkingAppointInfoEntity findByParkingId(Long parkingId) {
        ParkingAppointInfoEntity parkingAppointInfoEntity = new ParkingAppointInfoEntity();
        parkingAppointInfoEntity.setParkingId(parkingId);
        parkingAppointInfoEntity.setActive(Boolean.TRUE);
        return baseMapper.selectOne(parkingAppointInfoEntity);
    }

    /**
     * 根据parkingId查找
     *
     * @param parkingId
     * @return
     */
    @Override
    public ParkingAppointInfoEntity selectByParkingId(Long parkingId) {
        EntityWrapper<ParkingAppointInfoEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("active", Boolean.TRUE);
        entityWrapper.eq("parkingId", parkingId);
        return baseMapper.selectByParkingId(entityWrapper);
    }

    /**
     * 添加ParkingAppointInfoEntity(无租户)
     *
     * @param parkingAppointInfoEntity
     * @return
     */
    @Override
    public boolean insertParkingAppointInfoNonTenant(ParkingAppointInfoEntity parkingAppointInfoEntity) {
        return baseMapper.insertParkingAppointInfoNonTenant(parkingAppointInfoEntity);
    }

    /**
     * 修改ParkingAppointInfoEntity(无租户)
     *
     * @param parkingAppointInfoEntity
     * @return
     */
    @Override
    public boolean updateParkingAppointInfoNonTenant(ParkingAppointInfoEntity parkingAppointInfoEntity) {
        return baseMapper.updateParkingAppointInfoNonTenant(parkingAppointInfoEntity);
    }

    /**
     * 删除ParkingAppointInfoEntity(无租户)
     *
     * @param parkingId
     * @return
     */
    @Override
    public boolean deleteParkingAppointInfoNonTenant(Long parkingId) {
        return baseMapper.deleteParkingAppointInfoNonTenant(parkingId);
    }

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    @Override
    public ParkingAppointInfoEntity findById(Long id) {
        return baseMapper.findById(id);
    }
}
