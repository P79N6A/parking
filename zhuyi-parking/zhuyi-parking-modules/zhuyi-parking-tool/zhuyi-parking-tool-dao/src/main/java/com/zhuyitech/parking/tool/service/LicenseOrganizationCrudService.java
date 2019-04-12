package com.zhuyitech.parking.tool.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.parking.tool.domain.LicenseOrganization;

import java.util.List;

/**
 * @Date: 2018/4/14
 * @author: yuzhicheng
 */
public interface LicenseOrganizationCrudService extends CrudService<LicenseOrganization> {

    /**
     * 根据首字母和父id查询
     *
     * @return
     */
    LicenseOrganization selectByParentIdAndName(String name, Long parentId);

    /**
     * 根据前缀查询
     *
     * @param name
     * @return
     */
    LicenseOrganization selectByName(String name);

    /**
     * 根据前缀查询
     *
     * @param cityPrefix 前缀
     * @return
     */
    LicenseOrganization findByCityPrefix(String cityPrefix);


    /**
     * 根据parentId查找
     *
     * @param parentId
     * @return
     */
    List<LicenseOrganization> findListByByParent(Long parentId);
}
