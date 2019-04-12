package com.zoeeasy.cloud.sys.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.sys.domain.DictTypeEntity;
import com.zoeeasy.cloud.sys.mapper.DictTypeMapper;
import com.zoeeasy.cloud.sys.service.DictTypeCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典类型表(DictType)表服务实现类
 *
 * @author AkeemSuper
 * @since 2019-02-20 10:37:33
 */
@Service("dictTypeCrudService")
public class DictTypeCrudServiceImpl extends CrudServiceImpl<DictTypeMapper, DictTypeEntity> implements DictTypeCrudService {

    /**
     * 获取系统内置字典配置
     *
     * @param parentCode
     * @return
     */
    @Override
    public List<DictTypeEntity> findStaticDictType(String parentCode) {
        return baseMapper.findStatic(parentCode);
    }

    @Override
    public DictTypeEntity findByDictCode(String dictCode) {
        return baseMapper.findByDictCode(dictCode);
    }
}