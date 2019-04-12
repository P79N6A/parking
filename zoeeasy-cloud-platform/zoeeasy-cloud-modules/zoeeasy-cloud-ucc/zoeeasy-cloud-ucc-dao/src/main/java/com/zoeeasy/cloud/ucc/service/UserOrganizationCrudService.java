package com.zoeeasy.cloud.ucc.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.ucc.domain.UserOrganizationEntity;

import java.util.List;

/**
 * @author walkman
 * @since 2018-08-20
 */
public interface UserOrganizationCrudService extends CrudService<UserOrganizationEntity> {

    /**
     * 通过用户查找
     *
     * @param userId
     * @return
     */
    UserOrganizationEntity findByUserId(Long userId);

    /**
     * 通过部门查找
     *
     * @param organizationId
     * @return
     */
    List<UserOrganizationEntity> findByOrganizationId(Long organizationId);

    /**
     * 通过用户ID删除
     *
     * @param userId
     * @return
     */
    Integer deleteByUserId(Long userId);

    /**
     * 通过部门ID删除
     *
     * @param organizationId
     * @return
     */
    Integer deleteByOrganizationId(Long organizationId);

}