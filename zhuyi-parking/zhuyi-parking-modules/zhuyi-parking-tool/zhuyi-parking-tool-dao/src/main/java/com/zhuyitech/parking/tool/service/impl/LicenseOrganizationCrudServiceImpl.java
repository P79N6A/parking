package com.zhuyitech.parking.tool.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zhuyitech.parking.tool.domain.LicenseOrganization;
import com.zhuyitech.parking.tool.mapper.LicenseOrganizationMapper;
import com.zhuyitech.parking.tool.service.LicenseOrganizationCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date: 2018/4/14
 * @author: yuzhicheng
 */
@Service("licenseOrganizationCrudService")
public class LicenseOrganizationCrudServiceImpl extends ServiceImpl<LicenseOrganizationMapper, LicenseOrganization> implements LicenseOrganizationCrudService {

    /**
     * 根据首字母和父id查询
     *
     * @param name
     * @param parentId
     * @return
     */
    @Override
    public LicenseOrganization selectByParentIdAndName(String name, Long parentId) {
        LicenseOrganization licenseOrganization = new LicenseOrganization();
        licenseOrganization.setName(name);
        licenseOrganization.setParentId(parentId);
        return baseMapper.selectOne(licenseOrganization);
    }

    /**
     * 根据前缀查询
     *
     * @param name
     * @return
     */
    @Override
    public LicenseOrganization selectByName(String name) {
        LicenseOrganization licenseOrganization = new LicenseOrganization();
        licenseOrganization.setName(name);
        return baseMapper.selectOne(licenseOrganization);
    }

    /**
     * 根据前缀查询
     *
     * @param cityPrefix 前缀
     * @return
     */
    @Override
    public LicenseOrganization findByCityPrefix(String cityPrefix) {
        LicenseOrganization licenseOrganization = new LicenseOrganization();
        licenseOrganization.setCityPrefix(cityPrefix);
        return baseMapper.selectOne(licenseOrganization);
    }

    /**
     * 根据父id查找
     *
     * @param parentId
     * @return
     */
    @Override
    public List<LicenseOrganization> findListByByParent(Long parentId) {
        EntityWrapper<LicenseOrganization> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parentId", parentId);
        return baseMapper.selectList(entityWrapper);
    }
}
