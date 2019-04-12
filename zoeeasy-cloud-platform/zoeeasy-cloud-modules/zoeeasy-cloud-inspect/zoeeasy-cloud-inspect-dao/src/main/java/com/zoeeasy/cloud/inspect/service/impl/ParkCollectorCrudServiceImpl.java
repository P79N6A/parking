package com.zoeeasy.cloud.inspect.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.inspect.domain.ParkCollectorEntity;
import com.zoeeasy.cloud.inspect.mapper.ParkCollectorMapper;
import com.zoeeasy.cloud.inspect.service.ParkCollectorCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/9/20 0020
 */
@Service("parkCollectorCrudService")
public class ParkCollectorCrudServiceImpl extends CrudServiceImpl<ParkCollectorMapper, ParkCollectorEntity> implements ParkCollectorCrudService {
    @Override
    public List<ParkCollectorEntity> findByParking(Long parkingId, Long tenantId) {
        return baseMapper.findByParking(parkingId, tenantId);
    }

    /**
     * 通过userId获取收费员
     *
     * @param userId 用户id
     * @return 收费员
     */
    @Override
    public ParkCollectorEntity findByUserId(Long userId) {
        ParkCollectorEntity parkCollectorEntity = new ParkCollectorEntity();
        parkCollectorEntity.setUserId(userId);
        return baseMapper.selectOne(parkCollectorEntity);
    }
}
