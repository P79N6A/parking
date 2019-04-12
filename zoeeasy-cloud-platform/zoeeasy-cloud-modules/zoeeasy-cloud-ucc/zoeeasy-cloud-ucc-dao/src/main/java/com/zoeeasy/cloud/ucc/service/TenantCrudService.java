package com.zoeeasy.cloud.ucc.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.ucc.domain.TenantEntity;

/**
 * @author walkman
 * @date 2018-08-20
 */
public interface TenantCrudService extends CrudService<TenantEntity> {

    /**
     * 通过编码查找
     *
     * @param code code
     * @return TenantEntity
     */
    TenantEntity findByTenantCode(String code);

    /**
     * 通过编码查找
     *
     * @param name name
     * @return TenantEntity
     */
    TenantEntity findByTenantName(String name);
}
