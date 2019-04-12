package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.pms.domain.PacketVehicleEntity;
import com.zoeeasy.cloud.pms.enums.EffectedStatusEnum;
import com.zoeeasy.cloud.pms.mapper.PacketVehicleMapper;
import com.zoeeasy.cloud.pms.park.cst.ColumnConstant;
import com.zoeeasy.cloud.pms.service.PacketVehicleCrudService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @date: 2018/10/13.
 * @author：zm
 */
@Service("packetVehicleCrudService")
public class PacketVehicleCrudServiceImpl extends CrudServiceImpl<PacketVehicleMapper, PacketVehicleEntity> implements PacketVehicleCrudService {

    @Override
    public Integer findByParkingIdAndRuleId(Long parkingId, Long ruleId) {
        EntityWrapper<PacketVehicleEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq(ColumnConstant.PARKING_ID, parkingId);
        entityWrapper.eq(ColumnConstant.RULE_ID, ruleId);
        entityWrapper.ne(ColumnConstant.EFFECTED_STATUS, EffectedStatusEnum.LOSE_EFFECTED.getValue());

        return baseMapper.selectCount(entityWrapper);
    }

    @Override
    public Integer findByRuleId(Long ruleId) {
        EntityWrapper<PacketVehicleEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq(ColumnConstant.RULE_ID, ruleId);
        entityWrapper.ne(ColumnConstant.EFFECTED_STATUS, EffectedStatusEnum.LOSE_EFFECTED.getValue());
        return baseMapper.selectCount(entityWrapper);
    }

    @Override
    public PacketVehicleEntity findByPlateNumberAndParkingIdAndRuleId(String plateNumber, Long parkingId, Long ruleId) {
        EntityWrapper<PacketVehicleEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq(ColumnConstant.PLATE_NUMBER, plateNumber);
        entityWrapper.eq(ColumnConstant.PARKING_ID, parkingId);
        entityWrapper.eq(ColumnConstant.RULE_ID, ruleId);
        entityWrapper.ne(ColumnConstant.EFFECTED_STATUS, EffectedStatusEnum.LOSE_EFFECTED.getValue());
        List<PacketVehicleEntity> list = baseMapper.selectList(entityWrapper);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public PacketVehicleEntity findByPlateNoAndParkingId(String plateNumber, Long parkingId) {
        return baseMapper.findByPlateNoAndParkingId(plateNumber, parkingId);
    }
}
