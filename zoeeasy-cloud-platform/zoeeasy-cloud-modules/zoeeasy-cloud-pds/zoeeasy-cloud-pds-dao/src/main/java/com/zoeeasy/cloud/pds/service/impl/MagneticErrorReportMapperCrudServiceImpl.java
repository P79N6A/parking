package com.zoeeasy.cloud.pds.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.pds.domain.MagneticErrorReportEntity;
import com.zoeeasy.cloud.pds.mapper.MagneticErrorReportMapper;
import com.zoeeasy.cloud.pds.service.MagneticErrorReportMapperCrudService;
import org.springframework.stereotype.Service;

/**
 * 地磁误报处理记录
 *
 * @Date: 2018/10/18
 * @author: lhj
 */
@Service("magneticErrorReportMapperCrudService")
public class MagneticErrorReportMapperCrudServiceImpl extends CrudServiceImpl<MagneticErrorReportMapper, MagneticErrorReportEntity> implements MagneticErrorReportMapperCrudService {
    @Override
    public Boolean save(MagneticErrorReportEntity magneticErrorReportEntity) {
        return baseMapper.save(magneticErrorReportEntity);
    }
}
