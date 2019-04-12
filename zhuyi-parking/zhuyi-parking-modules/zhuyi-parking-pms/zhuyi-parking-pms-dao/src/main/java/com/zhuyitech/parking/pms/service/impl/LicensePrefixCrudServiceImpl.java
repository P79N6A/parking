package com.zhuyitech.parking.pms.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zhuyitech.parking.pms.domain.LicensePrefix;
import com.zhuyitech.parking.pms.mapper.LicensePrefixMapper;
import com.zhuyitech.parking.pms.service.LicensePrefixCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zwq
 */
@Service("licensePrefixCrudService")
public class LicensePrefixCrudServiceImpl extends ServiceImpl<LicensePrefixMapper, LicensePrefix> implements LicensePrefixCrudService {

    @Override
    public List<LicensePrefix> findByType(int type) {
        EntityWrapper<LicensePrefix> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("type", type);
        return baseMapper.selectList(entityWrapper);
    }

    @Override
    public List<LicensePrefix> findByParentIdAndType(Long parentId, int type) {
        EntityWrapper<LicensePrefix> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parentId", parentId);
        entityWrapper.eq("type", type);
        return baseMapper.selectList(entityWrapper);
    }

    @Override
    public List<LicensePrefix> findByLicensePrefix(LicensePrefix licensePrefix) {
        EntityWrapper<LicensePrefix> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parentId", licensePrefix.getParentId());
        entityWrapper.eq("type", licensePrefix.getType());
        entityWrapper.eq("name", licensePrefix.getName());
        return baseMapper.selectList(entityWrapper);
    }

    @Override
    public Integer getCountByParentId(Long parentId) {
        EntityWrapper<LicensePrefix> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parentId", parentId);
        return baseMapper.selectCount(entityWrapper);
    }

    @Override
    public LicensePrefix findByName(String name) {
        LicensePrefix licensePrefix = new LicensePrefix();
        licensePrefix.setName(name);
        return baseMapper.selectOne(licensePrefix);
    }
}
