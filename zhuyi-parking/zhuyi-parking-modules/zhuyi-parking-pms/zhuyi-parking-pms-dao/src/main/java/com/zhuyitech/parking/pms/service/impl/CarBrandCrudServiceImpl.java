package com.zhuyitech.parking.pms.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zhuyitech.parking.pms.domain.CarBrand;
import com.zhuyitech.parking.pms.mapper.CarBrandMapper;
import com.zhuyitech.parking.pms.service.CarBrandCrudService;
import org.springframework.stereotype.Service;

/**
 * @author walkman
 */
@Service("carBrandCrudService")
public class CarBrandCrudServiceImpl extends ServiceImpl<CarBrandMapper, CarBrand> implements CarBrandCrudService {

    /**
     * 通过名称查找
     *
     * @param name
     * @return
     */
    @Override
    public CarBrand findByName(String name) {
        CarBrand carBrand = new CarBrand();
        carBrand.setName(name);
        return baseMapper.selectOne(carBrand);
    }

    /**
     * 查找父ID下的记录数
     *
     * @param parentId
     * @return
     */
    @Override
    public Integer getCountByParentId(Long parentId) {
        EntityWrapper<CarBrand> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parentId", parentId);
        return baseMapper.selectCount(entityWrapper);
    }
}