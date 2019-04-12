package com.zoeeasy.cloud.gather.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.gather.domain.MagneticHeartBeatEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by song on 2018/8/28.
 */
@Repository("magneticHeartBeatMapper")
public interface MagneticHeartBeatMapper extends BaseMapper<MagneticHeartBeatEntity> {

    @SqlParser(filter = true)
    List<MagneticHeartBeatEntity> getMagneticHeartBeatList(@Param("detectorId") Long detectorId, @Param("provider") Integer provider);

    
    @SqlParser(filter = true)
    boolean addMagneticHeartBeat(MagneticHeartBeatEntity magneticHeartBeatEntity);
}
