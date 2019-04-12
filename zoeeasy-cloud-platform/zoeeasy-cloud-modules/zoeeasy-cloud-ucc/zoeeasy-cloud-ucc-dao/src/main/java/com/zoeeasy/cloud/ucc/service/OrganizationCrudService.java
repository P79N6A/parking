package com.zoeeasy.cloud.ucc.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.ucc.domain.OrganizationEntity;

import java.util.List;

/**
 * @author walkman
 * @since 2018-08-20
 */
public interface OrganizationCrudService extends CrudService<OrganizationEntity> {

    /**
     * 获取根部门
     *
     * @return
     */
    OrganizationEntity getRoot();

    /**
     * 获取所有部门
     *
     * @return
     */
    List<OrganizationEntity> getAll();

    /**
     * 通过父ID查找最后一个子部门
     *
     * @param parentId
     * @return
     */
    OrganizationEntity getLastChildOrNull(Long parentId);

    /**
     * 获取所有子部门
     *
     * @return
     */
    List<OrganizationEntity> getChildren(String pathCode);

    /**
     * @param code
     * @return
     */
    OrganizationEntity getByCode(String code);

    /**
     * @param name
     * @return
     */
    OrganizationEntity getByName(String name);

}
