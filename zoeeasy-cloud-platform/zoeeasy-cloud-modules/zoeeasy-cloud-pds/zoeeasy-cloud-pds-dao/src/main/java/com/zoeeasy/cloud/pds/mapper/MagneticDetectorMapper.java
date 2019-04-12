package com.zoeeasy.cloud.pds.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zoeeasy.cloud.pds.domain.MagneticDetectorEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 地磁检测器基础数据
 *
 * @author lhj
 */
@Repository("magneticDetectorMapper")
public interface MagneticDetectorMapper extends BaseMapper<MagneticDetectorEntity> {
    @SqlParser(filter = true)
    MagneticDetectorEntity selectMagneticDetectorList(@Param("serialNumber") String serialNumber, @Param("provider") Integer provider);

    @SqlParser(filter = true)
    boolean updateMagneticDetectorLastHeartbeatTime(MagneticDetectorEntity magneticDetectorEntity);

    @SqlParser(filter = true)
    boolean updateMagneticDetectorStatus(MagneticDetectorEntity magneticDetectorEntity);

    @SqlParser(filter = true)
    MagneticDetectorEntity findByParkingId(@Param("ew") Wrapper<MagneticDetectorEntity> entityEntityWrapper);

    @SqlParser(filter = true)
    MagneticDetectorEntity selectMagneticDetectorById(@Param("id") Long id);

    /**
     * 修改地磁检测器
     *
     * @param magneticDetectorEntity
     * @return
     */
    boolean updateMagneticDetector(MagneticDetectorEntity magneticDetectorEntity);
}
