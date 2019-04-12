package com.zoeeasy.cloud.inspect.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.inspect.domain.ParkCollectorEntity;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/9/20 0020
 */
public interface ParkCollectorCrudService extends CrudService<ParkCollectorEntity> {

    List<ParkCollectorEntity> findByParking(Long parkingId, Long tenantId);

    /**
     * 无租户通过userId获取收费员
     *
     * @param userId
     * @return
     */
    ParkCollectorEntity findByUserId(Long userId);
}
