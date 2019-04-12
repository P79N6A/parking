package com.zhuyitech.parking.tool.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuyitech.parking.tool.domain.TrafficRestrictionInfo;
import com.zhuyitech.parking.tool.mapper.TrafficRestrictionInfoMapper;
import com.zhuyitech.parking.tool.service.TrafficRestrictionInfoCrudService;
import org.springframework.stereotype.Service;
import java.util.Date;

/**
 * @author AkeenSuper
 */
@Service("trafficRestrictionInfoCrudService")
public class TrafficRestrictionInfoCrudServiceImpl extends ServiceImpl<TrafficRestrictionInfoMapper, TrafficRestrictionInfo> implements TrafficRestrictionInfoCrudService {

    /**
     * 删出7天前的限行详情
     */
    @Override
    public void deleteTrafficInfo(Date date) {
        baseMapper.deleteTrafficInfo(date);
    }
}
