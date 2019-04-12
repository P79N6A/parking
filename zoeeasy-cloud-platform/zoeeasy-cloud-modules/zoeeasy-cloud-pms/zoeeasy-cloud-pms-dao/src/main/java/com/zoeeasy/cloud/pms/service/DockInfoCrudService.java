package com.zoeeasy.cloud.pms.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.DockInfoEntity;

/**
 * @author walkman
 */
public interface DockInfoCrudService extends CrudService<DockInfoEntity> {

    boolean save(DockInfoEntity dockInfoEntity);

    DockInfoEntity findById(Long id);

    DockInfoEntity findByCloudCode(String cloudCode, Long tenantId);

    DockInfoEntity findOne(EntityWrapper<DockInfoEntity> entityWrapper);
}