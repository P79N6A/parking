package com.zoeeasy.cloud.pds.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.pds.domain.MagneticDetectorEntity;
import com.zoeeasy.cloud.pds.mapper.MagneticDetectorMapper;
import com.zoeeasy.cloud.pds.service.MagneticDetectorCrudService;
import org.springframework.stereotype.Service;

/**
 * 地磁检测设备
 *
 * @author lhj
 */
@Service("magneticDetectorCrudService")
public class MagneticDetectorCrudServiceImpl extends CrudServiceImpl<MagneticDetectorMapper, MagneticDetectorEntity> implements MagneticDetectorCrudService {
    /**
     * 通过编码查找设备
     *
     * @param code
     * @return
     */
    @Override
    public MagneticDetectorEntity findMagneticDetectorByCode(String code) {
        MagneticDetectorEntity magneticDetectorEntity = new MagneticDetectorEntity();
        magneticDetectorEntity.setCode(code);
        return baseMapper.selectOne(magneticDetectorEntity);
    }

    @Override
    public MagneticDetectorEntity findMagneticDetectorById(Long id) {
        MagneticDetectorEntity magneticDetectorEntity = new MagneticDetectorEntity();
        magneticDetectorEntity.setId(id);
        return baseMapper.selectMagneticDetectorById(id);
    }

    @Override
    public MagneticDetectorEntity findMagneticDetectorByProviderAndSerialNumber(String serialNumber, Integer provider) {
        return baseMapper.selectMagneticDetectorList(serialNumber, provider);
    }

    /**
     * 维护地磁检测器心跳时间及注册状态
     *
     * @param magneticDetectorEntity
     * @return
     */
    @Override
    public boolean updateMagneticDetectorLastHeartbeatTime(MagneticDetectorEntity magneticDetectorEntity) {
        return baseMapper.updateMagneticDetectorLastHeartbeatTime(magneticDetectorEntity);
    }

    /**
     * 修改地磁检测器状态
     *
     * @param magneticDetectorEntity
     * @return
     */
    @Override
    public boolean updateMagneticDetectorStatus(MagneticDetectorEntity magneticDetectorEntity) {
        return baseMapper.updateMagneticDetectorStatus(magneticDetectorEntity);
    }

    /**
     * 根据停车场和泊位获取地磁检测器
     *
     * @param entityEntityWrapper
     * @return
     */
    @Override
    public MagneticDetectorEntity findByParkingId(Wrapper<MagneticDetectorEntity> entityEntityWrapper) {
        return baseMapper.findByParkingId(entityEntityWrapper);
    }

    /**
     * 修改地磁
     *
     * @param magneticDetectorEntity
     * @return
     */
    @Override
    public boolean updateMagneticDetector(MagneticDetectorEntity magneticDetectorEntity) {
        return baseMapper.updateMagneticDetector(magneticDetectorEntity);
    }


}
