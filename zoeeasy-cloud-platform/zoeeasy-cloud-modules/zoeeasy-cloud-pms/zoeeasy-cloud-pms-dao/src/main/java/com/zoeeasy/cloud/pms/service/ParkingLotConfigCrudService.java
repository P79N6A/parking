package com.zoeeasy.cloud.pms.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.ParkingLotConfigEntity;

/**
 * Created by song on 2018/9/14.
 */
public interface ParkingLotConfigCrudService extends CrudService<ParkingLotConfigEntity> {

    /**
     * 删除ParkingLotConfig（无租户）
     *
     * @return
     */
    boolean deleteParkingLotConfigNonTenant(Long parkingId);

}
