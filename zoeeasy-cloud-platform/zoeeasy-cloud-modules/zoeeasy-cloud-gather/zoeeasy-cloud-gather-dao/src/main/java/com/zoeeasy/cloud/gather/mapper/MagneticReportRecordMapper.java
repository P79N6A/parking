package com.zoeeasy.cloud.gather.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.gather.domain.MagneticReportRecordEntity;
import org.springframework.stereotype.Repository;

/**
 * @Date: 2018/9/26
 * @author: lhj
 */
@Repository("magneticReportRecordMapper")
public interface MagneticReportRecordMapper extends BaseMapper<MagneticReportRecordEntity> {
    @SqlParser(filter = true)
    boolean addMagneticReportRecord(MagneticReportRecordEntity magneticReportRecordEntity);
}
