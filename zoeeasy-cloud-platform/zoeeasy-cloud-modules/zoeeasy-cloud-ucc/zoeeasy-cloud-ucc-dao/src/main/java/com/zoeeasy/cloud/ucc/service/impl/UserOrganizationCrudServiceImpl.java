package com.zoeeasy.cloud.ucc.service.impl;

import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.ucc.domain.UserOrganizationEntity;
import com.zoeeasy.cloud.ucc.mapper.UserOrganizationMapper;
import com.zoeeasy.cloud.ucc.service.UserOrganizationCrudService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author walkman
 * @date 2018-08-20
 */
@Service("userOrganizationCrudService")
public class UserOrganizationCrudServiceImpl extends CrudServiceImpl<UserOrganizationMapper, UserOrganizationEntity>
        implements UserOrganizationCrudService {

    /**
     * 通过用户查找
     *
     * @param userId
     * @return
     */
    @Override
    public UserOrganizationEntity findByUserId(Long userId) {
        UserOrganizationEntity userOrganizationEntity = new UserOrganizationEntity();
        userOrganizationEntity.setUserId(userId);
        return this.baseMapper.selectOne(userOrganizationEntity);
    }

    /**
     * 通过部门查找
     *
     * @param organizationId
     * @return
     */
    @Override
    public List<UserOrganizationEntity> findByOrganizationId(Long organizationId) {
        EntityWrapper<UserOrganizationEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("organizationId", organizationId);
        return this.baseMapper.selectList(entityWrapper);
    }

    /**
     * 通过用户ID删除
     *
     * @param userId
     * @return
     */
    @Override
    public Integer deleteByUserId(Long userId) {
        EntityWrapper<UserOrganizationEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("userId", userId);
        return this.baseMapper.delete(entityWrapper);
    }

    /**
     * 通过部门ID删除
     *
     * @param organizationId
     * @return
     */
    @Override
    public Integer deleteByOrganizationId(Long organizationId) {
        EntityWrapper<UserOrganizationEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("organizationId", organizationId);
        return this.baseMapper.delete(entityWrapper);
    }

}