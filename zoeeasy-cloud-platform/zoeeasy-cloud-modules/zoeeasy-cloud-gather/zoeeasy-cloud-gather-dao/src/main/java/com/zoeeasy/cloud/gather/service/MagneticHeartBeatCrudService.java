package com.zoeeasy.cloud.gather.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.gather.domain.MagneticHeartBeatEntity;

import java.util.List;

/**
 * 地磁检测器心跳
 *
 * @Date: 2018/9/20
 * @author: lhj
 */
public interface MagneticHeartBeatCrudService extends CrudService<MagneticHeartBeatEntity> {
    /**
     * 查询当前地磁检测器最近一条心跳数据
     *
     * @param detectorId
     * @param provider
     * @return
     */
    List<MagneticHeartBeatEntity> getMagneticHeartBeatList(Long detectorId, Integer provider);

    /**
     * 地磁心跳数据添加
     *
     * @param magneticHeartBeatEntity
     * @return
     */
    boolean addMagneticHeartBeat(MagneticHeartBeatEntity magneticHeartBeatEntity);

}
