package com.zoeeasy.cloud.sys.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.sys.domain.ParamTypeEntity;
import com.zoeeasy.cloud.sys.mapper.ParamTypeMapper;
import com.zoeeasy.cloud.sys.service.ParamTypeCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 参数类型表(ParamType)表服务实现类
 *
 * @author AkeemSuper
 * @since 2019-02-20 10:37:33
 */
@Service("paramTypeCrudService")
public class ParamTypeCrudServiceImpl extends CrudServiceImpl<ParamTypeMapper, ParamTypeEntity> implements ParamTypeCrudService {
    /**
     * 获取系统内置参数配置
     *
     * @return
     */
    @Override
    public List<ParamTypeEntity> findStaticParamType(String parentCode) {
        return baseMapper.findAllStatic(parentCode);
    }
}