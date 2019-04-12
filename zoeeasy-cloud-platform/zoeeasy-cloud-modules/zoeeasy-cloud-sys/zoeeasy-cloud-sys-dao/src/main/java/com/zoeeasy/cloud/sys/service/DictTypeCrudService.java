package com.zoeeasy.cloud.sys.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.sys.domain.DictTypeEntity;

import java.util.List;

/**
 * 字典类型表(DictType)表服务类
 *
 * @author AkeemSuper
 * @since 2019-02-20 10:37:33
 */
public interface DictTypeCrudService extends CrudService<DictTypeEntity> {

    /**
     * 获取系统内置字典配置
     *
     * @param parentCode
     * @return
     */
    List<DictTypeEntity> findStaticDictType(String parentCode);

    DictTypeEntity findByDictCode(String dictCode);
}