package com.zoeeasy.cloud.sys.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.sys.domain.DictItemEntity;

import java.util.List;

/**
 * 字典数据表(DictItem)表服务类
 *
 * @author AkeemSuper
 * @since 2019-02-20 10:37:33
 */
public interface DictItemCrudService extends CrudService<DictItemEntity> {

    /**
     * 获取静态参数项
     *
     * @param dictCode
     * @return
     */
    List<DictItemEntity> findByDictCode(String dictCode);

    /**
     * 根据id获取字典项
     *
     * @param id
     * @return
     */
    DictItemEntity findById(Long id);

    /**
     * 根据entityWrapper搜索
     *
     * @param entityWrapper
     * @return
     */
    DictItemEntity findByEntityWrapper(Wrapper<DictItemEntity> entityWrapper);

    DictItemEntity findByDictLabel(String dictLabel,String dictCode);

    DictItemEntity findByDictValue(String dictValue,String dictCode);
}