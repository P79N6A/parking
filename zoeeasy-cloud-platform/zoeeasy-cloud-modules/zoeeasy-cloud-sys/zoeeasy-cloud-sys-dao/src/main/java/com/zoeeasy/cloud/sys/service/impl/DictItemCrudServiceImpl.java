package com.zoeeasy.cloud.sys.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.sys.domain.DictItemEntity;
import com.zoeeasy.cloud.sys.mapper.DictItemMapper;
import com.zoeeasy.cloud.sys.service.DictItemCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典数据表(DictItem)表服务实现类
 *
 * @author AkeemSuper
 * @since 2019-02-20 10:37:33
 */
@Service("dictItemCrudService")
public class DictItemCrudServiceImpl extends CrudServiceImpl<DictItemMapper, DictItemEntity> implements DictItemCrudService {

    @Override
    public List<DictItemEntity> findByDictCode(String dictCode) {
        return baseMapper.findByDictCode(dictCode);
    }

    @Override
    public DictItemEntity findById(Long id) {
        return baseMapper.findById(id);
    }

    @Override
    public DictItemEntity findByEntityWrapper(Wrapper<DictItemEntity> entityWrapper) {
        return baseMapper.findByEntityWrapper(entityWrapper);
    }

    @Override
    public DictItemEntity findByDictLabel(String dictLabel,String dictCode) {
        DictItemEntity dictItemEntity = new DictItemEntity();
        dictItemEntity.setDictLabel(dictLabel);
        dictItemEntity.setDictCode(dictCode);
        return baseMapper.selectOne(dictItemEntity);
    }

    @Override
    public DictItemEntity findByDictValue(String dictValue,String dictCode) {
        DictItemEntity dictItemEntity = new DictItemEntity();
        dictItemEntity.setDictValue(dictValue);
        dictItemEntity.setDictCode(dictCode);
        return baseMapper.selectOne(dictItemEntity);
    }
}