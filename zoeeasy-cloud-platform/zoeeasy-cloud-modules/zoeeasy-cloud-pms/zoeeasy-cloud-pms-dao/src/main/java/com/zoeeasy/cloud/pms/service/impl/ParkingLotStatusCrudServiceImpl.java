package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.zoeeasy.cloud.pms.domain.ParkingLotStatusEntity;
import com.zoeeasy.cloud.pms.enums.ParkingLotStatusEnum;
import com.zoeeasy.cloud.pms.mapper.ParkingLotStatusMapper;
import com.zoeeasy.cloud.pms.park.cst.ColumnConstant;
import com.zoeeasy.cloud.pms.service.ParkingLotStatusCrudService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by song on 2018/9/14.
 */
@Service("parkingLotStatusCrudService")
public class ParkingLotStatusCrudServiceImpl extends ServiceImpl<ParkingLotStatusMapper, ParkingLotStatusEntity> implements ParkingLotStatusCrudService {
    /**
     * 根据停车场id和泊位id查询
     *
     * @param parkingId
     * @param parkingLotId
     * @return
     */
    @Override
    public ParkingLotStatusEntity findByParkingIdAndParkingLotId(Long parkingId, Long parkingLotId, Long tenantId) {
        return baseMapper.findByParkingIdAndParkingLotId(parkingId, parkingLotId, tenantId);
    }

    /**
     * 根据泊位ID查询
     *
     * @param parkingLotId
     * @return
     */
    @Override
    public ParkingLotStatusEntity findByParkingLotId(Long parkingLotId) {
        ParkingLotStatusEntity parkingLotStatusEntity = new ParkingLotStatusEntity();
        parkingLotStatusEntity.setParkingLotId(parkingLotId);
        return baseMapper.selectOne(parkingLotStatusEntity);
    }

    /**
     * 修改泊位信息
     *
     * @param id
     * @param status
     * @return
     */
    @Override
    public Integer updateParkingLotStatus(Long id, Integer status, Date occupyTime) {
        ParkingLotStatusEntity parkingLotStatusEntity = new ParkingLotStatusEntity();
        parkingLotStatusEntity.setId(id);
        parkingLotStatusEntity.setStatus(status);
        if (null != occupyTime) {
            parkingLotStatusEntity.setOccupyTime(occupyTime);
        }
        parkingLotStatusEntity.setLastModificationTime(DateUtils.now());
        return baseMapper.updateStatusById(parkingLotStatusEntity);
    }

    /**
     * 根据停车场ID查找
     *
     * @param parkingId
     * @return
     */
    @Override
    public List<ParkingLotStatusEntity> findByParkingId(Long parkingId) {
        EntityWrapper<ParkingLotStatusEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq(ColumnConstant.PARKING_ID, parkingId);
        return baseMapper.selectList(entityWrapper);
    }

    /**
     * 根据停车场id查找被占用泊位条数
     *
     * @param parkingId
     * @return
     */
    @Override
    public Integer findCountByParkingId(Long parkingId) {
        EntityWrapper<ParkingLotStatusEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq(ColumnConstant.PARKING_ID, parkingId);
        entityWrapper.eq(ColumnConstant.STATUS, ParkingLotStatusEnum.USED.getValue());
        return baseMapper.selectCount(entityWrapper);
    }

    @Override
    public Integer findCountByParkingIdNonTenant(Long parkingId) {
        EntityWrapper<ParkingLotStatusEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq(ColumnConstant.PARKING_ID, parkingId);
        entityWrapper.eq(ColumnConstant.STATUS, ParkingLotStatusEnum.USED.getValue());
        return baseMapper.findCountByParkingIdNonTenant(entityWrapper);
    }

    /**
     * 删除ParkingLotStatus（无租户）
     *
     * @param parkingId
     * @return
     */
    @Override
    public boolean deleteParkingLotStatusNonTenant(Long parkingId) {
        return baseMapper.deleteParkingLotStatusNonTenant(parkingId);
    }
}
