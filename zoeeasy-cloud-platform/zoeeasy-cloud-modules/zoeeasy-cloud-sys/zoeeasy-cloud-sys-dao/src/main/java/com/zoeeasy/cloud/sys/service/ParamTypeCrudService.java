package com.zoeeasy.cloud.sys.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.sys.domain.ParamTypeEntity;

import java.util.List;

/**
 * 参数类型表(ParamType)表服务类
 *
 * @author AkeemSuper
 * @since 2019-02-20 10:37:33
 */
public interface ParamTypeCrudService extends CrudService<ParamTypeEntity> {

    /**
     * 获取系统内置参数配置
     *
     * @param s
     * @return
     */
    List<ParamTypeEntity> findStaticParamType(String s);
}