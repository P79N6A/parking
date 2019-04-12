package com.zoeeasy.cloud.gather.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.gather.domain.MagneticStatusRecordEntity;
import org.springframework.stereotype.Repository;

/**
 * 地磁检测器状态变更
 *
 * @Date: 2018/9/25
 * @author: lhj
 */
@Repository("magneticStatusRecordMapper")
public interface MagneticStatusRecordMapper extends BaseMapper<MagneticStatusRecordEntity> {

    /**
     * 地磁检测器状态变更推送数据添加
     *
     * @param magneticStatusRecordEntity
     */
    @SqlParser(filter = true)
    boolean addMagneticStatusRecord(MagneticStatusRecordEntity magneticStatusRecordEntity);
}
