package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.pms.domain.PacketApproveEntity;
import com.zoeeasy.cloud.pms.mapper.PacketApproveMapper;
import com.zoeeasy.cloud.pms.service.PacketApproveCrudService;
import org.springframework.stereotype.Service;

/**
 * @date: 2018/10/13.
 * @author：zm
 */
@Service("packetApproveCrudService")
public class PacketApproveCrudServiceImpl extends ServiceImpl<PacketApproveMapper, PacketApproveEntity> implements PacketApproveCrudService {
}
