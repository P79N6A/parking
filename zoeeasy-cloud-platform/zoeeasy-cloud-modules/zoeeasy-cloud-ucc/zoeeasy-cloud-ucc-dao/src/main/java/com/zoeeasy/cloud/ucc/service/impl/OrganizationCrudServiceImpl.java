package com.zoeeasy.cloud.ucc.service.impl;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.ucc.domain.OrganizationEntity;
import com.zoeeasy.cloud.ucc.mapper.OrganizationMapper;
import com.zoeeasy.cloud.ucc.service.OrganizationCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author walkman
 * @date 2018-08-20
 */
@Service("organizationCrudService")
public class OrganizationCrudServiceImpl extends CrudServiceImpl<OrganizationMapper, OrganizationEntity>
        implements OrganizationCrudService {

    /**
     * 获取跟部门
     *
     * @return
     */
    @Override
    public OrganizationEntity getRoot() {
        EntityWrapper<OrganizationEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parentId", 0);
        return this.selectOne(entityWrapper);
    }

    /**
     * 获取所有部门
     *
     * @return
     */
    @Override
    public List<OrganizationEntity> getAll() {
        EntityWrapper<OrganizationEntity> entityWrapper = new EntityWrapper<>();
        return this.baseMapper.selectList(entityWrapper);
    }

    /**
     * 通过父ID查找最后一个子部门
     *
     * @param parentId
     * @return
     */
    @Override
    public OrganizationEntity getLastChildOrNull(Long parentId) {
        EntityWrapper<OrganizationEntity> entityEntityWrapper = new EntityWrapper<>();
        entityEntityWrapper.eq("parentId", parentId);
        entityEntityWrapper.orderBy("pathCode", false);
        entityEntityWrapper.last("LIMIT 1");
        List<OrganizationEntity> entityList = this.baseMapper.selectList(entityEntityWrapper);
        if (!entityList.isEmpty()) {
            return entityList.get(0);
        }
        return null;
    }

    /**
     * 获取所有子部门
     *
     * @return
     */
    @Override
    public List<OrganizationEntity> getChildren(String pathCode) {
        EntityWrapper<OrganizationEntity> entityEntityWrapper = new EntityWrapper<>();
        entityEntityWrapper.like("pathCode", pathCode + ".", SqlLike.RIGHT);
        entityEntityWrapper.orderBy("pathCode", false);
        return this.baseMapper.selectList(entityEntityWrapper);
    }

    /**
     * @param code
     * @return
     */
    @Override
    public OrganizationEntity getByCode(String code) {
        OrganizationEntity entity = new OrganizationEntity();
        entity.setCode(code);
        return baseMapper.selectOne(entity);
    }

    /**
     * @param name
     * @return
     */
    @Override
    public OrganizationEntity getByName(String name) {
        OrganizationEntity entity = new OrganizationEntity();
        entity.setName(name);
        return baseMapper.selectOne(entity);
    }

}
