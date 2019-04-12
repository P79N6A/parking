package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.pms.domain.AreaEntity;
import com.zoeeasy.cloud.pms.mapper.AreaMapper;
import com.zoeeasy.cloud.pms.service.AreaCrudService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 2018/9/14.
 */
@Service("areaCrudService")
public class AreaCrudServiceImpl extends ServiceImpl<AreaMapper, AreaEntity> implements AreaCrudService {

    /**
     * @param parentId
     * @return
     */
    @Override
    public List<AreaEntity> selectListByParentId(Long parentId) {
        EntityWrapper<AreaEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parentId", parentId);
        return selectList(entityWrapper);
    }

    @Override
    public int getCountByParentId(Long parentId) {
        EntityWrapper<AreaEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parentId", parentId);
        return selectCount(entityWrapper);
    }

    @Override
    public AreaEntity getLastChildOrNull(Long parentId) {
        EntityWrapper<AreaEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parentId", parentId);
        entityWrapper.orderBy("pathCode", false);
        entityWrapper.last("LIMIT 1");
        List<AreaEntity> areaEntities = selectList(entityWrapper);
        if (areaEntities != null && !areaEntities.isEmpty()) {
            return areaEntities.get(0);
        }
        return null;
    }

    @Override
    public AreaEntity findByCode(String code) {
        AreaEntity areaEntity = new AreaEntity();
        areaEntity.setCode(code);
        return baseMapper.selectOne(areaEntity);
    }

    @Override
    public List<Long> findByParentIds(List<Long> parentIds) {
        EntityWrapper<AreaEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.in("parentId", parentIds);
        List<Long> ids = new ArrayList<>();
        List<AreaEntity> areaEntities = selectList(entityWrapper);
        if (areaEntities != null && !areaEntities.isEmpty()) {
            for (AreaEntity areaEntity : areaEntities) {
                ids.add(areaEntity.getId());
            }
        }
        return ids;
    }

    @Override
    public AreaEntity findByName(String name) {
        AreaEntity areaEntity = new AreaEntity();
        areaEntity.setName(name);
        return baseMapper.selectOne(areaEntity);
    }

    /**
     * 根据areaCode 获取 areaId
     *
     * @param
     * @return
     */
    @Override
    public Long findParentIdByCode(String code) {
        AreaEntity areaEntity = new AreaEntity();

        areaEntity.setCode(code);
        AreaEntity area = baseMapper.selectOne(areaEntity);
        return area.getParentId();
    }

    @Override
    public List<Long> findIdsByCodes(List<String> code) {
        EntityWrapper<AreaEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.in("code", code);
        List<Long> ids = new ArrayList<>();
        List<AreaEntity> areaEntities = selectList(entityWrapper);
        if (areaEntities != null) {
            for (AreaEntity areaEntity : areaEntities) {
                ids.add(areaEntity.getId());
            }
        }
        return ids;
    }

    @Override
    public List<Long> findParentIdsByCodes(List<String> code) {
        return null;
    }

    /**
     * 根据areaCode 获取 areaId
     *
     * @param areaCode
     * @return
     */
    @Override
    public AreaEntity findAreaByAreaCode(String areaCode) {
        AreaEntity areaEntity = new AreaEntity();
        areaEntity.setCode(areaCode);
        return baseMapper.selectOne(areaEntity);
    }

    @Override
    public Long findAreaIdByAreaCode(String areaCode) {
        AreaEntity areaEntity = new AreaEntity();
        areaEntity.setCode(areaCode);
        AreaEntity area = baseMapper.selectOne(areaEntity);
        if (area != null) {
            return area.getId();
        }
        return null;
    }

    @Override
    public String findNameByPathCode(String pathCode) {
        AreaEntity areaEntity = new AreaEntity();
        areaEntity.setPathCode(pathCode);
        AreaEntity area = baseMapper.selectOne(areaEntity);
        if (area != null) {
            return area.getName();
        }
        return null;
    }

    @Override
    public List<AreaEntity> findAreaListByName(String name) {
        EntityWrapper<AreaEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("name", name);
        return baseMapper.selectList(entityWrapper);
    }

    /**
     * 根据code查找(无租户)
     *
     * @param code
     * @return
     */
    @Override
    public AreaEntity findAreaByCodeNonTenant(String code) {
        return baseMapper.findAreaByCodeNonTenant(code);
    }

    /**
     * 根据id查找(无租户)
     *
     * @param id
     * @return
     */
    @Override
    public AreaEntity findAreaByIdNonTenant(Long id) {
        return baseMapper.findAreaByIdNonTenant(id);
    }

    @Override
    public AreaEntity findArea(Wrapper<AreaEntity> ew) {
        return baseMapper.findArea(ew);
    }

}


