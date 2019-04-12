package com.zoeeasy.cloud.pds.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pds.domain.MagneticManagerEntity;

/**
 * 地磁管理器
 *
 * @Date: 2018/9/20
 * @author: lhj
 */
public interface MagneticManagerCrudService extends CrudService<MagneticManagerEntity> {
    /**
     * 根据地磁管理器编号查找
     *
     * @param code
     * @return
     */
    MagneticManagerEntity findManagerByCode(String code);
}
