package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.pms.domain.ParkingLotConfigEntity;
import com.zoeeasy.cloud.pms.mapper.ParkingLotConfigMapper;
import com.zoeeasy.cloud.pms.service.ParkingLotConfigCrudService;
import org.springframework.stereotype.Service;

/**
 * Created by song on 2018/9/14.
 */
@Service("parkingLotConfigCrudService")
public class ParkingLotConfigCrudServiceImpl extends ServiceImpl<ParkingLotConfigMapper, ParkingLotConfigEntity> implements ParkingLotConfigCrudService {
    @Override
    public boolean deleteParkingLotConfigNonTenant(Long parkingId) {
        return baseMapper.deleteParkingLotConfigNonTenant(parkingId);
    }
}
