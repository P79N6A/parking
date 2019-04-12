package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.pms.domain.ParkingApplyInfoEntity;
import com.zoeeasy.cloud.pms.mapper.ParkingApplyInfoMapper;
import com.zoeeasy.cloud.pms.service.ParkingApplyInfoCrudService;
import org.springframework.stereotype.Service;

@Service("parkingApplyInfoCrudService")
public class ParkingApplyInfoCrudServiceImpl extends ServiceImpl<ParkingApplyInfoMapper, ParkingApplyInfoEntity> implements ParkingApplyInfoCrudService {
    @Override
    public ParkingApplyInfoEntity selectParkingApplyByParkingId(Long parkingId) {
        return baseMapper.selectParkingApplyByParkingId(parkingId);
    }

    @Override
    public boolean updateParkingApply(ParkingApplyInfoEntity parkingApplyInfoEntity) {
        return baseMapper.updateParkingApply(parkingApplyInfoEntity);
    }
}
