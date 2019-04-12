package com.zoeeasy.cloud.pms.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.ParkingRecordHistoryEntity;

/**
 * @author AkeemSuper
 * @date 2018/9/12 0012
 */
public interface ParkingRecordHistoryCrudService extends CrudService<ParkingRecordHistoryEntity> {

    /**
     * 保存停车历史记录
     *
     * @param parkingRecordHistoryEntity
     * @return
     */
    boolean save(ParkingRecordHistoryEntity parkingRecordHistoryEntity);
}
