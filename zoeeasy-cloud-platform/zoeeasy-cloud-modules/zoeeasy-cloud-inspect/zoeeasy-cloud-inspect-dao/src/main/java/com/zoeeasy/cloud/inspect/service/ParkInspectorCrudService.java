package com.zoeeasy.cloud.inspect.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.inspect.domain.ParkInspectorEntity;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/9/20 0020
 */
public interface ParkInspectorCrudService extends CrudService<ParkInspectorEntity> {

    /**
     * 通过停车场获取巡检id
     *
     * @param parkingId
     * @param tenantId
     * @return List<ParkInspectorEntity>
     */
    List<ParkInspectorEntity> findByParking(Long parkingId, Long tenantId);

    /**
     * 通过userId获取巡检员
     *
     * @param userId 用户id
     * @return ParkInspectorEntity
     */
    ParkInspectorEntity findByUserId(Long userId);

    /**
     * 根据用户获取停车场
     *
     * @param userId 用户id
     * @return List<ParkInspectorEntity>
     */
    List<ParkInspectorEntity> findParkingByUserId(Long userId);
}
