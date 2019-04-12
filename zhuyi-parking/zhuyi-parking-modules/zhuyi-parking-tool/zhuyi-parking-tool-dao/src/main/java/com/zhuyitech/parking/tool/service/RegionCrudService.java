package com.zhuyitech.parking.tool.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.parking.tool.domain.Region;

import java.util.List;

/**
 * @author walkman
 */
public interface RegionCrudService extends CrudService<Region> {

    /**
     * @param parentId
     * @return
     */
    List<Region> selectListByParentId(Long parentId);

    /**
     * @param code
     * @return
     */
    Region findByCode(String code);

}
