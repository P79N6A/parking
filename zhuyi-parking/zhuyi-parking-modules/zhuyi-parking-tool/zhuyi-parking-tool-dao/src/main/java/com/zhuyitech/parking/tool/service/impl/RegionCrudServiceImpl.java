package com.zhuyitech.parking.tool.service.impl;

import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuyitech.parking.tool.service.RegionCrudService;
import com.zhuyitech.parking.tool.domain.Region;
import com.zhuyitech.parking.tool.mapper.RegionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author walkman
 */
@Service("regionCrudService")
public class RegionCrudServiceImpl extends ServiceImpl<RegionMapper, Region> implements RegionCrudService {

    /**
     * @param parentId
     * @return
     */
    @Override
    public List<Region> selectListByParentId(Long parentId) {
        EntityWrapper<Region> entityWrapper = new EntityWrapper<>(Region.class);
        entityWrapper.eq("parentId", parentId);
        entityWrapper.orderBy("order");
        List<Region> regionList = selectList(entityWrapper);
        return regionList;
    }

    /**
     * @param code
     * @return
     */
    @Override
    public Region findByCode(String code) {
        EntityWrapper<Region> entityWrapper = new EntityWrapper<>(Region.class);
        entityWrapper.eq("code", code);
        return selectOne(entityWrapper);
    }
}
