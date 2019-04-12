package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.pms.domain.SpecialVehicleEntity;
import com.zoeeasy.cloud.pms.enums.EffectedStatusEnum;
import com.zoeeasy.cloud.pms.enums.SpecialTypeEnum;
import com.zoeeasy.cloud.pms.mapper.SpecialVehicleMapper;
import com.zoeeasy.cloud.pms.service.SpecialVehicleCrudService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @date: 2018/10/13.
 * @author：zm
 */
@Service("specialVehicleCrudService")
public class SpecialVehicleCrudServiceImpl extends CrudServiceImpl<SpecialVehicleMapper, SpecialVehicleEntity> implements SpecialVehicleCrudService {
    @Override
    public SpecialVehicleEntity findByPlateNumberAndParkingId(String plateNumber, Long parkingId) {
        EntityWrapper<SpecialVehicleEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("plateNumber", plateNumber);
        entityWrapper.ne("status", EffectedStatusEnum.LOSE_EFFECTED.getValue());
        entityWrapper.eq("parkingId", parkingId);
        List<SpecialVehicleEntity> list = baseMapper.selectList(entityWrapper);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * @param plateNumber
     * @param parkingId
     */
    @Override
    public SpecialVehicleEntity findByPlateNumber(String plateNumber, Long parkingId) {
        return baseMapper.findByPlateNumber(plateNumber, parkingId);
    }

    @Override
    public SpecialVehicleEntity findByIdAndSpecialType(Long id, Integer specialType) {
        SpecialVehicleEntity specialVehicleEntity = new SpecialVehicleEntity();
        specialVehicleEntity.setId(id);
        specialVehicleEntity.setSpecialType(SpecialTypeEnum.FIXED_CAR.getValue());
        return baseMapper.selectOne(specialVehicleEntity);
    }

    @Override
    public SpecialVehicleEntity findByParkingLotId(Long parkingLotId, String parkingLotNumber) {
        EntityWrapper<SpecialVehicleEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parkingLotId", parkingLotId);
        entityWrapper.eq("parkingLotNumber", parkingLotNumber);
        entityWrapper.eq("specialType", SpecialTypeEnum.FIXED_CAR.getValue());
        entityWrapper.ne("status", EffectedStatusEnum.LOSE_EFFECTED.getValue());
        List<SpecialVehicleEntity> list = baseMapper.selectList(entityWrapper);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

}
