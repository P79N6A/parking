package com.zoeeasy.cloud.sys.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.sys.domain.ParamItemEntity;

import java.util.List;

/**
 * 参数数据表(ParamItem)表服务类
 *
 * @author AkeemSuper
 * @since 2019-02-20 10:37:33
 */
public interface ParamItemCrudService extends CrudService<ParamItemEntity> {

    /**
     * 获取静态参数项
     *
     * @param paramCode
     * @return
     */
    List<ParamItemEntity> findByParamCode(String paramCode);

    /**
     * 根据id获取参数项
     *
     * @param id
     * @return
     */
    ParamItemEntity findById(Long id);

    /**
     * 根据entityWrapper搜索
     *
     * @param entityWrapper
     * @return
     */
    ParamItemEntity findByEntityWrapper(Wrapper<ParamItemEntity> entityWrapper);
}