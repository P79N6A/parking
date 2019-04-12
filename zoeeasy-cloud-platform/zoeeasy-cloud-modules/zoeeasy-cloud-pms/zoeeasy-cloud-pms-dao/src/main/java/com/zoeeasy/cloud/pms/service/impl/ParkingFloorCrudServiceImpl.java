package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.pms.domain.ParkingFloorEntity;
import com.zoeeasy.cloud.pms.mapper.ParkingFloorMapper;
import com.zoeeasy.cloud.pms.service.ParkingFloorCrudService;
import org.springframework.stereotype.Service;

/**
 * Created by song on 2018/9/14.
 */
@Service("parkingFloorCrudService")
public class ParkingFloorCrudServiceImpl extends ServiceImpl<ParkingFloorMapper, ParkingFloorEntity> implements ParkingFloorCrudService {
    @Override
    public Integer findLotCountByParkingId(Long parkingId) {
        return baseMapper.findLotCountByParkingId(parkingId);
    }

    @Override
    public Integer findLotFixedTotalByParkingId(Long parkingId) {
        return baseMapper.findLotFixedTotalByParkingId(parkingId);
    }

    @Override
    public Integer findCountByGarageId(Long garageId) {
        EntityWrapper<ParkingFloorEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("garageId", garageId);
        return baseMapper.selectCount(entityWrapper);
    }

    @Override
    public ParkingFloorEntity getparkingFloor(Long garageId) {
        return baseMapper.getparkingFloor(garageId);
    }
}


