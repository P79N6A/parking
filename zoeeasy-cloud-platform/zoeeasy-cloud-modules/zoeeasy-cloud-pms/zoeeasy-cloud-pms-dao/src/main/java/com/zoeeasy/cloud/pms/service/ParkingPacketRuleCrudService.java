package com.zoeeasy.cloud.pms.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.ParkingPacketRuleEntity;

import java.util.List;

/**
 * @date: 2018/10/13.
 * @author：zm
 */
public interface ParkingPacketRuleCrudService extends CrudService<ParkingPacketRuleEntity> {

    /**
     * 根据parkingid查
     *
     * @param parkingId
     * @return
     */
    ParkingPacketRuleEntity findByParkingId(Long parkingId);

    /**
     *
     * @param packetType
     * @param parkingIds
     * @return
     */
    List<ParkingPacketRuleEntity> selectParkingPacketRules(Integer packetType, List<Long> parkingIds);

    /**
     * 通过parkingId和ruleId查
     *
     * @param parkingId
     * @param ruleId
     * @return
     */
    ParkingPacketRuleEntity findByParkingIdAndRuleId(Long parkingId, Long ruleId);

}
