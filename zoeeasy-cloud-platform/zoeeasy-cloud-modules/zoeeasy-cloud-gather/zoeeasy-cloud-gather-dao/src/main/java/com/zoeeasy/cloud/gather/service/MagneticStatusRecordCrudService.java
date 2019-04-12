package com.zoeeasy.cloud.gather.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.gather.domain.MagneticStatusRecordEntity;

/**
 * 地磁检测器状态变更
 *
 * @Date: 2018/9/25
 * @author: lhj
 */
public interface MagneticStatusRecordCrudService extends CrudService<MagneticStatusRecordEntity> {
    /**
     * 地磁检测器状态变更推送数据添加
     *
     * @param magneticStatusRecordEntity
     */
    boolean addMagneticStatusRecord(MagneticStatusRecordEntity magneticStatusRecordEntity);
}
