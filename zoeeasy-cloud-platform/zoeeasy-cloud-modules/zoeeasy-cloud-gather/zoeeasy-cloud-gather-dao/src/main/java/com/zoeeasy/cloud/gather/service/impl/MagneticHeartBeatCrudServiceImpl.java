package com.zoeeasy.cloud.gather.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.gather.domain.MagneticHeartBeatEntity;
import com.zoeeasy.cloud.gather.mapper.MagneticHeartBeatMapper;
import com.zoeeasy.cloud.gather.service.MagneticHeartBeatCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地磁检测器心跳
 *
 * @Date: 2018/9/20
 * @author: lhj
 */
@Service("magneticHeartBeatCrudService")
public class MagneticHeartBeatCrudServiceImpl extends ServiceImpl<MagneticHeartBeatMapper, MagneticHeartBeatEntity> implements MagneticHeartBeatCrudService {

    @Override
    public List<MagneticHeartBeatEntity> getMagneticHeartBeatList(Long detectorId, Integer provider){
        MagneticHeartBeatEntity magneticHeartBeatEntity = new MagneticHeartBeatEntity();
        magneticHeartBeatEntity.setDetectorId(detectorId);
        magneticHeartBeatEntity.setProvider(provider);
        return baseMapper.getMagneticHeartBeatList(detectorId,provider);
    }

    /**
     * 地磁心跳数据添加
     *
     * @param magneticHeartBeatEntity
     * @return
     */
    @Override
    public boolean addMagneticHeartBeat(MagneticHeartBeatEntity magneticHeartBeatEntity) {
        return baseMapper.addMagneticHeartBeat(magneticHeartBeatEntity);
    }

}
