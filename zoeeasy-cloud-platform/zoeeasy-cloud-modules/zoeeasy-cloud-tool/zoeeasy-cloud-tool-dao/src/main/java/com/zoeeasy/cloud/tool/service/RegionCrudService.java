package com.zoeeasy.cloud.tool.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.tool.domain.RegionEntity;

/**
 * @author walkman
 */
public interface RegionCrudService extends CrudService<RegionEntity> {

    /**
     * @param code Code
     * @return RegionEntity
     */
    RegionEntity findByCode(String code);

    /**
     * 获取region
     *
     * @param wrapper Wrapper
     * @return RegionEntity
     */
    RegionEntity getRegion(Wrapper<RegionEntity> wrapper);

}
