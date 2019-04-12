package com.zoeeasy.cloud.gather.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.gather.domain.HikvisionVehicleLogEntity;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface HikvisionVehicleLogMapper extends BaseMapper<HikvisionVehicleLogEntity> {

    /**
     * 查询上次同步最大时间
     */
    Date selectLastSyncEndTime();

    /**
     * 查询上次最大校对时间
     *
     * @return
     */
    Date selectLastCollateTime();
}
