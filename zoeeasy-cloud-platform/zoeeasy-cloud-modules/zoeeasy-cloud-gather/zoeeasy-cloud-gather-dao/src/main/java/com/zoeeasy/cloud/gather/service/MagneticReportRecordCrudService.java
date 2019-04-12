package com.zoeeasy.cloud.gather.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.gather.domain.MagneticReportRecordEntity;

/**
 * 地磁检测器
 *
 * @Date: 2018/9/26
 * @author: lhj
 */
public interface MagneticReportRecordCrudService extends CrudService<MagneticReportRecordEntity> {
    /**
     * 添加地磁检测记录
     *
     * @param magneticReportRecordEntity
     * @return
     */
    boolean addMagneticReportRecord(MagneticReportRecordEntity magneticReportRecordEntity);
}
