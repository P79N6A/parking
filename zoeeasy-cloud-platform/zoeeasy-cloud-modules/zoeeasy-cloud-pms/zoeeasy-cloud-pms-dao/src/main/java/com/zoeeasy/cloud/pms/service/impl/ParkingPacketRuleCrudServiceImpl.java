package com.zoeeasy.cloud.pms.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.pms.domain.ParkingPacketRuleEntity;
import com.zoeeasy.cloud.pms.mapper.ParkingPacketRuleMapper;
import com.zoeeasy.cloud.pms.service.ParkingPacketRuleCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @date: 2018/10/13.
 * @author：zm
 */
@Service("parkingPacketRuleCrudServiceImpl")
public class ParkingPacketRuleCrudServiceImpl extends CrudServiceImpl<ParkingPacketRuleMapper, ParkingPacketRuleEntity> implements ParkingPacketRuleCrudService {

    @Override
    public ParkingPacketRuleEntity findByParkingId(Long parkingId) {
        ParkingPacketRuleEntity parkingPacketRuleEntity = new ParkingPacketRuleEntity();
        parkingPacketRuleEntity.setParkingId(parkingId);
        return baseMapper.selectOne(parkingPacketRuleEntity);
    }


    @Override
    public List<ParkingPacketRuleEntity> selectParkingPacketRules(Integer packetType, List<Long> parkingIds) {
        return baseMapper.selectParkingPacketRules(packetType, parkingIds);
    }

    @Override
    public ParkingPacketRuleEntity findByParkingIdAndRuleId(Long parkingId, Long ruleId) {
        ParkingPacketRuleEntity parkingPacketRuleEntity = new ParkingPacketRuleEntity();
        parkingPacketRuleEntity.setRuleId(ruleId);
        parkingPacketRuleEntity.setParkingId(parkingId);
        return baseMapper.selectOne(parkingPacketRuleEntity);
    }
}
