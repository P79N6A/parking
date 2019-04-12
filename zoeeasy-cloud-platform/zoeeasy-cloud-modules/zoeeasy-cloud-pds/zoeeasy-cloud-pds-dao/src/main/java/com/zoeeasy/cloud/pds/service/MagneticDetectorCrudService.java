package com.zoeeasy.cloud.pds.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pds.domain.MagneticDetectorEntity;

/**
 * 设备服务
 *
 * @author lhj
 */
public interface MagneticDetectorCrudService extends CrudService<MagneticDetectorEntity> {
    /**
     * 通过编码查找设备
     *
     * @param code
     * @return
     */
    MagneticDetectorEntity findMagneticDetectorByCode(String code);

    /**
     * 通过id查找设备
     *
     * @param id
     * @return
     */
    MagneticDetectorEntity findMagneticDetectorById(Long id);

    /**
     * 根据厂商和设备序列号查询对应的地磁检测器
     *
     * @param serialNumber
     * @param provider
     * @return
     */
    MagneticDetectorEntity findMagneticDetectorByProviderAndSerialNumber(String serialNumber, Integer provider);

    /**
     * 维护地磁检测器心跳时间及注册状态
     *
     * @param magneticDetectorEntity
     * @return
     */
    boolean updateMagneticDetectorLastHeartbeatTime(MagneticDetectorEntity magneticDetectorEntity);

    /**
     * 修改地磁检测器状态
     *
     * @param magneticDetectorEntity
     * @return
     */
    boolean updateMagneticDetectorStatus(MagneticDetectorEntity magneticDetectorEntity);

    /**
     * 根据停车场和泊位获取地磁检测器
     *
     * @param entityEntityWrapper
     * @return
     */
    MagneticDetectorEntity findByParkingId(Wrapper<MagneticDetectorEntity> entityEntityWrapper);

    boolean updateMagneticDetector(MagneticDetectorEntity magneticDetectorEntity);
}
