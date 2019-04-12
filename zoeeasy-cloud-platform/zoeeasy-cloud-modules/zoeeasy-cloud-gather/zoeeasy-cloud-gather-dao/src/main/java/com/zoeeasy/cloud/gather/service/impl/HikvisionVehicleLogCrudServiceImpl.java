package com.zoeeasy.cloud.gather.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.gather.domain.HikvisionVehicleLogEntity;
import com.zoeeasy.cloud.gather.mapper.HikvisionVehicleLogMapper;
import com.zoeeasy.cloud.gather.service.HikvisionVehicleLogCrudService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author walkman
 */
@Service("hikvisionVehicleLogCrudService")
public class HikvisionVehicleLogCrudServiceImpl extends ServiceImpl<HikvisionVehicleLogMapper, HikvisionVehicleLogEntity> implements HikvisionVehicleLogCrudService {

    /**
     * 获取上次同步时间
     *
     * @return
     */
    @Override
    public Date getLastSyncEndTime() {
        return baseMapper.selectLastSyncEndTime();
    }

    @Override
    public Date selectLastCollateTime() {
        return baseMapper.selectLastCollateTime();
    }

}
