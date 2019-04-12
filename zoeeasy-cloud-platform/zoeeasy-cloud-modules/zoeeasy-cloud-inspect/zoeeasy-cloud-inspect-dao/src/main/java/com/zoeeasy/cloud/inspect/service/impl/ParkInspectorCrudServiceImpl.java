package com.zoeeasy.cloud.inspect.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.inspect.domain.ParkInspectorEntity;
import com.zoeeasy.cloud.inspect.mapper.ParkInspectorMapper;
import com.zoeeasy.cloud.inspect.service.ParkInspectorCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/9/20 0020
 */
@Service("parkInspectorCrudService")
public class ParkInspectorCrudServiceImpl extends CrudServiceImpl<ParkInspectorMapper, ParkInspectorEntity> implements ParkInspectorCrudService {

    /**
     * 通过停车场获取巡检id
     *
     * @param parkingId
     * @param tenantId
     * @return
     */
    @Override
    public List<ParkInspectorEntity> findByParking(Long parkingId, Long tenantId) {
        EntityWrapper<ParkInspectorEntity> entityEntityWrapper = new EntityWrapper<>();
        entityEntityWrapper.eq("parkingId", parkingId);
        return baseMapper.selectList(entityEntityWrapper);
    }

    /**
     * 通过userId获取巡检员
     *
     * @param userId
     * @return
     */
    @Override
    public ParkInspectorEntity findByUserId(Long userId) {
        ParkInspectorEntity parkInspectorEntity = new ParkInspectorEntity();
        parkInspectorEntity.setUserId(userId);
        return baseMapper.selectOne(parkInspectorEntity);
    }

    /**
     * 根据用户获取停车场
     *
     * @param userId
     * @return
     */
    @Override
    public List<ParkInspectorEntity> findParkingByUserId(Long userId) {
        EntityWrapper<ParkInspectorEntity> entityEntityWrapper = new EntityWrapper<>();
        entityEntityWrapper.eq("userId", userId);
        return baseMapper.selectList(entityEntityWrapper);
    }
}
