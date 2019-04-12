package com.zoeeasy.cloud.pds.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.pds.domain.MagneticErrorReportEntity;
import org.springframework.stereotype.Repository;

/**
 * 地磁误报处理记录基础数据
 *
 * @Date: 2018/10/18
 * @author: lhj
 */
@Repository("magneticErrorReportMapper")
public interface MagneticErrorReportMapper extends BaseMapper<MagneticErrorReportEntity> {

    @SqlParser(filter = true)
    Boolean save(MagneticErrorReportEntity magneticErrorReportEntity);
}
