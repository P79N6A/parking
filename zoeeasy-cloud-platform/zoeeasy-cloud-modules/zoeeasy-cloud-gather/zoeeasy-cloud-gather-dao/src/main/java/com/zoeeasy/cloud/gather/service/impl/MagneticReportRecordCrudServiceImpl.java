package com.zoeeasy.cloud.gather.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.gather.domain.MagneticReportRecordEntity;
import com.zoeeasy.cloud.gather.mapper.MagneticReportRecordMapper;
import com.zoeeasy.cloud.gather.service.MagneticReportRecordCrudService;
import org.springframework.stereotype.Service;

/**
 * @Date: 2018/9/26
 * @author: lhj
 */
@Service("magneticReportRecordCrudService")
public class MagneticReportRecordCrudServiceImpl extends ServiceImpl<MagneticReportRecordMapper, MagneticReportRecordEntity> implements MagneticReportRecordCrudService {

    /**
     * 添加地磁检测记录
     *
     * @param magneticReportRecordEntity
     * @return
     */
    @Override
    public boolean addMagneticReportRecord(MagneticReportRecordEntity magneticReportRecordEntity) {
        return baseMapper.addMagneticReportRecord(magneticReportRecordEntity);
    }
}
