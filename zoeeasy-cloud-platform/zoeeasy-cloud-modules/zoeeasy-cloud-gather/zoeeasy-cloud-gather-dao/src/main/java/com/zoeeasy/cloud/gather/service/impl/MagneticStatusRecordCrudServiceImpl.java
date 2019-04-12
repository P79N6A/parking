package com.zoeeasy.cloud.gather.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.gather.domain.MagneticStatusRecordEntity;
import com.zoeeasy.cloud.gather.mapper.MagneticStatusRecordMapper;
import com.zoeeasy.cloud.gather.service.MagneticStatusRecordCrudService;
import org.springframework.stereotype.Service;

/**
 * @Date: 2018/9/25
 * @author: lhj
 */
@Service("magneticStatusRecordCrudService")
public class MagneticStatusRecordCrudServiceImpl extends ServiceImpl<MagneticStatusRecordMapper, MagneticStatusRecordEntity> implements MagneticStatusRecordCrudService {

    /**
     * 地磁检测器状态变更推送数据添加
     *
     * @param magneticStatusRecordEntity
     */
    @Override
    public boolean addMagneticStatusRecord(MagneticStatusRecordEntity magneticStatusRecordEntity) {
        return baseMapper.addMagneticStatusRecord(magneticStatusRecordEntity);
    }
}
