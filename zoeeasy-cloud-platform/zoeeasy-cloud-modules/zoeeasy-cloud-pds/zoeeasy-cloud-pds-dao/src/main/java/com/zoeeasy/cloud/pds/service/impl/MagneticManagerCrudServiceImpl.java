package com.zoeeasy.cloud.pds.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.pds.domain.MagneticManagerEntity;
import com.zoeeasy.cloud.pds.mapper.MagneticManagerMapper;
import com.zoeeasy.cloud.pds.service.MagneticManagerCrudService;
import org.springframework.stereotype.Service;

/**
 * 地磁管理器
 *
 * @Date: 2018/9/20
 * @author: lhj
 */
@Service("magneticManagerCrudService")
public class MagneticManagerCrudServiceImpl extends CrudServiceImpl<MagneticManagerMapper, MagneticManagerEntity> implements MagneticManagerCrudService {

    @Override
    public MagneticManagerEntity findManagerByCode(String code) {
        MagneticManagerEntity magneticManagerEntity = new MagneticManagerEntity();
        magneticManagerEntity.setCode(code);
        return baseMapper.selectOne(magneticManagerEntity);
    }
}
