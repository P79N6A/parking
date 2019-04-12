package com.zhuyitech.parking.pms.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.parking.pms.domain.CarBrand;


/**
 * @author walkman
 */
public interface CarBrandCrudService extends CrudService<CarBrand> {

    /**
     * 通过名称查找
     *
     * @param code
     * @return
     */
    CarBrand findByName(String code);

    /**
     * 查找父ID下的记录数
     *
     * @param parentId
     * @return
     */
    Integer getCountByParentId(Long parentId);

}
