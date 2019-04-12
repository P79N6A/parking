package com.zoeeasy.cloud.pms.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.PacketRuleEntity;


/**
 * @date: 2018/10/13.
 * @author：zm
 */
public interface PacketRuleCrudService extends CrudService<PacketRuleEntity> {

    /**
     * 通过包期规则名称查找
     *
     * @param packetName 包期规则名称
     * @return
     */
    PacketRuleEntity findByPacketName(String packetName);


}
