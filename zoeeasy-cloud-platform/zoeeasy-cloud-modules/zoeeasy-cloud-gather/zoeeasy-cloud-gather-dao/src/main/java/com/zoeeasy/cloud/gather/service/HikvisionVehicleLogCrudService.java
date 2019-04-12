package com.zoeeasy.cloud.gather.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.gather.domain.HikvisionVehicleLogEntity;

import java.util.Date;

/**
 * @author walkman
 */
public interface HikvisionVehicleLogCrudService extends CrudService<HikvisionVehicleLogEntity> {

    /**
     * 获取上次同步时间
     *
     * @return
     */
    Date getLastSyncEndTime();

    /**
     * 查询上次最大校对时间
     *
     * @return
     */
    Date selectLastCollateTime();

}
