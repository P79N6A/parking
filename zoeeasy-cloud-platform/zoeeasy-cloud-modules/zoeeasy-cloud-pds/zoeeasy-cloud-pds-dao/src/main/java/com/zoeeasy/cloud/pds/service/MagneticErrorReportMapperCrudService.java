package com.zoeeasy.cloud.pds.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pds.domain.MagneticErrorReportEntity;

/**
 * 地磁误报处理记录服务
 *
 * @Date: 2018/10/18
 * @author: lhj
 */
public interface MagneticErrorReportMapperCrudService extends CrudService<MagneticErrorReportEntity> {

    Boolean save(MagneticErrorReportEntity magneticErrorReportEntity);
}
