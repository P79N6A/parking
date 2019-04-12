package com.zoeeasy.cloud.sys.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.sys.domain.ParamItemEntity;
import com.zoeeasy.cloud.sys.mapper.ParamItemMapper;
import com.zoeeasy.cloud.sys.service.ParamItemCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 参数数据表(ParamItem)表服务实现类
 *
 * @author AkeemSuper
 * @since 2019-02-20 10:37:33
 */
@Service("paramItemCrudService")
public class ParamItemCrudServiceImpl extends CrudServiceImpl<ParamItemMapper, ParamItemEntity> implements ParamItemCrudService {
    /**
     * 获取静态参数项
     *
     * @param paramCode
     * @return
     */
    @Override
    public List<ParamItemEntity> findByParamCode(String paramCode) {
        return baseMapper.findByParamCode(paramCode);
    }

    /**
     * 根据id获取参数项
     *
     * @param id
     * @return
     */
    @Override
    public ParamItemEntity findById(Long id) {
        return baseMapper.findById(id);
    }

    /**
     * 根据entityWrapper搜索
     *
     * @param entityWrapper
     * @return
     */
    @Override
    public ParamItemEntity findByEntityWrapper(Wrapper<ParamItemEntity> entityWrapper) {
        return baseMapper.findByEntityWrapper(entityWrapper);
    }
}