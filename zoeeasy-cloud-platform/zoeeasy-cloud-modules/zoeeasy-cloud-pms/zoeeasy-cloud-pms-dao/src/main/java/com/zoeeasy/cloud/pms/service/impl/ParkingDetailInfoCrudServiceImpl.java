package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.pms.domain.ParkingDetailInfoEntity;
import com.zoeeasy.cloud.pms.mapper.ParkingDetailInfoMapper;
import com.zoeeasy.cloud.pms.service.ParkingDetailInfoCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("parkingDetailInfoCrudService")
public class ParkingDetailInfoCrudServiceImpl extends ServiceImpl<ParkingDetailInfoMapper, ParkingDetailInfoEntity> implements ParkingDetailInfoCrudService {

    @Override
    public ParkingDetailInfoEntity findByParkingId(Long parkingId) {
        ParkingDetailInfoEntity parkingDetailInfoEntity = new ParkingDetailInfoEntity();
        parkingDetailInfoEntity.setParkingId(parkingId);
        return baseMapper.selectOne(parkingDetailInfoEntity);
    }

    /**
     * 根据停车场id获取停车场详细信息(无talentId)
     *
     * @param parkingId
     * @return
     */
    @Override
    public ParkingDetailInfoEntity findByParkingIdTenantless(Long parkingId) {
        return baseMapper.findByParkingId(parkingId);
    }

    /**
     * 添加ParkingDetailInfo(无租户)
     *
     * @param parkingDetailInfoEntity
     * @return
     */
    @Override
    public boolean insertParkingDetailInfoTenantless(ParkingDetailInfoEntity parkingDetailInfoEntity) {
        return baseMapper.insertParkingDetailInfoNonTenant(parkingDetailInfoEntity);
    }

    /**
     * 修改ParkingDetailInfo(无租户)
     *
     * @param parkingDetailInfoEntity
     * @return
     */
    @Override
    public boolean updateParkingDetailInfoTenantless(ParkingDetailInfoEntity parkingDetailInfoEntity) {
        return baseMapper.updateParkingDetailInfoNonTenant(parkingDetailInfoEntity);
    }

    /**
     * 删除ParkingDetailInfo(无租户)
     *
     * @param parkingId
     * @return
     */
    @Override
    public boolean deleteParkingDetailInfoNonTenant(Long parkingId) {
        return baseMapper.deleteParkingDetailInfoNonTenant(parkingId);
    }

    @Override
    public List<ParkingDetailInfoEntity> findParkingDetailNonTenant(Wrapper<ParkingDetailInfoEntity> ew) {
        return baseMapper.findParkingDetailNonTenant(ew);
    }

    @Override
    public ParkingDetailInfoEntity selectParkingDetailNonTenant(Long parkingId, Long tenantId) {
        EntityWrapper<ParkingDetailInfoEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parkingId", parkingId);
        entityWrapper.eq("tenantId", tenantId);
        return baseMapper.selectParkingDetailNonTenant(entityWrapper);
    }

    /**
     * 获取停车场地址
     *
     * @param parkingId
     * @return
     */
    @Override
    public String selectParkingAddress(Long parkingId) {
        return baseMapper.selectParkingAddress(parkingId);
    }

    /**
     * 分页获取
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Override
    public Page<ParkingDetailInfoEntity> selectListPage(Page<ParkingDetailInfoEntity> page, Wrapper<ParkingDetailInfoEntity> wrapper) {
        return page.setRecords(baseMapper.selectListPage(page, wrapper));
    }

}
