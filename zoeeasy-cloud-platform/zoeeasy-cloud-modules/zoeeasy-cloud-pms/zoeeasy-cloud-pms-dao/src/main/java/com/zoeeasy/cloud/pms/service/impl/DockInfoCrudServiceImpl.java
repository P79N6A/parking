package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.pms.domain.DockInfoEntity;
import com.zoeeasy.cloud.pms.mapper.DockInfoMapper;
import com.zoeeasy.cloud.pms.service.DockInfoCrudService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author walkman
 */
@Service("dockInfoCrudService")
public class DockInfoCrudServiceImpl extends ServiceImpl<DockInfoMapper, DockInfoEntity> implements DockInfoCrudService {

    @Override
    public boolean save(DockInfoEntity dockInfoEntity) {
        return baseMapper.save(dockInfoEntity);
    }

    @Override
    public DockInfoEntity findById(Long id) {
        return baseMapper.findById(id);
    }

    @Override
    public DockInfoEntity findByCloudCode(String cloudCode, Long tenantId) {
        return baseMapper.findByCloudCode(cloudCode, tenantId);
    }

    @Override
    public DockInfoEntity findOne(EntityWrapper<DockInfoEntity> entityWrapper) {
        List<DockInfoEntity> dockInfoEntities = baseMapper.findOne(entityWrapper);
        if (CollectionUtils.isNotEmpty(dockInfoEntities)) {
            return dockInfoEntities.get(0);
        }
        return null;
    }
}