package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.pms.domain.ParkingCurrentInfoEntity;
import com.zoeeasy.cloud.pms.mapper.ParkingCurrentInfoMapper;
import com.zoeeasy.cloud.pms.service.ParkingCurrentInfoCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("parkingCurrentInfoCrudService")
public class ParkingCurrentInfoCrudServiceImpl extends ServiceImpl<ParkingCurrentInfoMapper, ParkingCurrentInfoEntity> implements ParkingCurrentInfoCrudService {

    /**
     * 根据id获取车位信息
     *
     * @param parkingCurrentInfoId
     * @return
     */
    @Override
    public ParkingCurrentInfoEntity findById(Long parkingCurrentInfoId) {
        return baseMapper.findById(parkingCurrentInfoId);
    }

    @Override
    public ParkingCurrentInfoEntity findByParkingId(Long parkingId) {
        ParkingCurrentInfoEntity parkingCurrentInfoEntity = new ParkingCurrentInfoEntity();
        parkingCurrentInfoEntity.setParkingId(parkingId);
        return baseMapper.selectOne(parkingCurrentInfoEntity);
    }

    /**
     * 根据停车场id获取车位信息
     *
     * @param parkingId
     * @return
     */
    @Override
    public ParkingCurrentInfoEntity selectByParkingId(Long parkingId) {
        return baseMapper.selectByParkingId(parkingId);
    }

    /**
     * 更新可用车位数量
     *
     * @param parkingId    parkingLotId
     * @param lotAvailable lotAvailable
     * @return
     */
    @Override
    public Integer updateLotAvailable(Long parkingId, Integer lotAvailable) {
        ParkingCurrentInfoEntity parkingInfo = new ParkingCurrentInfoEntity();
        parkingInfo.setLotAvailable(lotAvailable);
        EntityWrapper<ParkingCurrentInfoEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parkingId", parkingId);
        return baseMapper.update(parkingInfo, entityWrapper);
    }

    /**
     * 更新车位信息
     *
     * @param parkingCurrentInfo
     */
    @Override
    public void updateCurrentInfoById(ParkingCurrentInfoEntity parkingCurrentInfo) {
        baseMapper.updateCurrentInfoById(parkingCurrentInfo);
    }

    /**
     * 查询可预约停车场数量
     *
     * @param wrapper
     * @return
     */
    @Override
    public Integer selectAppointParkCount(Wrapper<ParkingCurrentInfoEntity> wrapper) {
        return baseMapper.selectParkCount(wrapper);
    }

    /**
     * 修改可用车位数
     *
     * @param parkingCurrentInfoEntity
     * @return
     */
    @Override
    public boolean updateParkingLotAvailable(ParkingCurrentInfoEntity parkingCurrentInfoEntity) {
        return baseMapper.updateParkingLotAvailable(parkingCurrentInfoEntity);
    }

    /**
     * 添加ParkingCurrentInfoEntity(无租户)
     *
     * @param parkingCurrentInfoEntity
     * @return
     */
    @Override
    public boolean insertParkingCurrentInfoNonTenant(ParkingCurrentInfoEntity parkingCurrentInfoEntity) {
        return baseMapper.insertParkingCurrentInfoNonTenant(parkingCurrentInfoEntity);
    }

    /**
     * 删除ParkingCurrentInfoEntity(无租户)
     *
     * @param parkingId
     * @return
     */
    @Override
    public boolean deleteParkingCurrentInfoNonTenant(Long parkingId) {
        return baseMapper.deleteParkingCurrentInfoNonTenant(parkingId);
    }

    /**
     * 查询可预约停车场列表
     *
     * @param wrapper
     * @return
     */
    @Override
    public List<ParkingCurrentInfoEntity> selectAppointParkList(Wrapper<ParkingCurrentInfoEntity> wrapper) {
        return baseMapper.selectParkList(wrapper);
    }
}
