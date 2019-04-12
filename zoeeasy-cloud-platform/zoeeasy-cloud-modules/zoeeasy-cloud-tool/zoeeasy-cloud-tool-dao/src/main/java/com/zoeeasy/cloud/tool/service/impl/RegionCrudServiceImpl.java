package com.zoeeasy.cloud.tool.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.tool.domain.RegionEntity;
import com.zoeeasy.cloud.tool.mapper.RegionMapper;
import com.zoeeasy.cloud.tool.service.RegionCrudService;
import org.springframework.stereotype.Service;

/**
 * @author walkman
 */
@Service("regionCrudService")
public class RegionCrudServiceImpl extends ServiceImpl<RegionMapper, RegionEntity> implements RegionCrudService {

    /**
     * @param code code
     * @return RegionEntity
     */
    @Override
    public RegionEntity findByCode(String code) {
        EntityWrapper<RegionEntity> entityWrapper = new EntityWrapper<>(RegionEntity.class);
        entityWrapper.eq("code", code);
        //东莞、中山、儋州三个地区有重复code
        entityWrapper.and("code != parentCode");
        return selectOne(entityWrapper);
    }

    /**
     * 获取region-无租户
     *
     * @param wrapper Wrapper
     * @return RegionEntity
     */
    @Override
    public RegionEntity getRegion(Wrapper<RegionEntity> wrapper) {
        return baseMapper.getRegion(wrapper);
    }
}
