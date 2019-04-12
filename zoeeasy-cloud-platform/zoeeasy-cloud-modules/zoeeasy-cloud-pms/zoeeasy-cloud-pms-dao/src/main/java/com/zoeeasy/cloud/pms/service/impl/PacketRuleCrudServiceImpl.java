package com.zoeeasy.cloud.pms.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.pms.domain.PacketRuleEntity;
import com.zoeeasy.cloud.pms.mapper.PacketRuleMapper;
import com.zoeeasy.cloud.pms.service.PacketRuleCrudService;
import org.springframework.stereotype.Service;


/**
 * @date: 2018/10/13.
 * @author：zm
 */
@Service("packetRuleCrudService")
public class PacketRuleCrudServiceImpl extends CrudServiceImpl<PacketRuleMapper, PacketRuleEntity> implements PacketRuleCrudService {
    @Override
    public PacketRuleEntity findByPacketName(String packetName) {
        PacketRuleEntity packetRule = new PacketRuleEntity();
        packetRule.setPacketName(packetName);
        return baseMapper.selectOne(packetRule);
    }


}
