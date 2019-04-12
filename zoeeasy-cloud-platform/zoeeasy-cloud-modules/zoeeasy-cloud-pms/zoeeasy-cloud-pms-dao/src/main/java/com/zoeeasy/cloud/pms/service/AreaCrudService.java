package com.zoeeasy.cloud.pms.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.AreaEntity;

import java.util.List;

/**
 *
 */
public interface AreaCrudService extends CrudService<AreaEntity> {

    /**
     * @param
     * @return
     */
    List<AreaEntity> selectListByParentId(Long parentId);

    /**
     * 获取parentId节点下所有节点个数
     *
     * @param parentId 父节点
     * @return 子节点个数
     */
    int getCountByParentId(Long parentId);

    /**
     * 通过父ID查找最后一个子节点
     *
     * @param parentId 父节点
     * @return code
     */
    AreaEntity getLastChildOrNull(Long parentId);

    /**
     * @param code
     * @return
     */
    AreaEntity findByCode(String code);

    List<Long> findByParentIds(List<Long> parentIds);

    AreaEntity findByName(String name);

    Long findParentIdByCode(String code);

    List<Long> findIdsByCodes(List<String> code);

    List<Long> findParentIdsByCodes(List<String> code);

    AreaEntity findAreaByAreaCode(String areaCode);

    /**
     * 根据areaCode 获取 areaId
     */
    Long findAreaIdByAreaCode(String areaCode);

    String findNameByPathCode(String pathCode);

    List<AreaEntity> findAreaListByName(String name);

    /**
     * 根据code查找(无租户)
     *
     * @param code
     * @return
     */
    AreaEntity findAreaByCodeNonTenant(String code);

    /**
     * 根据id查找(无租户)
     *
     * @param id
     * @return
     */
    AreaEntity findAreaByIdNonTenant(Long id);

    AreaEntity findArea(Wrapper<AreaEntity> ew);

}


