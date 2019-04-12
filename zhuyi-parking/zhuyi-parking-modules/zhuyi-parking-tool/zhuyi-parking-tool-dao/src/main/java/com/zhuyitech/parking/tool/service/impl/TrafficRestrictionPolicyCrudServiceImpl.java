package com.zhuyitech.parking.tool.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuyitech.parking.tool.domain.TrafficRestrictionPolicy;
import com.zhuyitech.parking.tool.mapper.TrafficRestrictionPolicyMapper;
import com.zhuyitech.parking.tool.service.TrafficRestrictionPolicyCrudService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 查询限行城市的crudService
 *
 * @author AkeemSuper
 */
@Service("trafficRestrictionPolicyCrudService")
public class TrafficRestrictionPolicyCrudServiceImpl extends ServiceImpl<TrafficRestrictionPolicyMapper, TrafficRestrictionPolicy> implements TrafficRestrictionPolicyCrudService {

    /**
     * 去坐标查询限行政策
     *
     * @param wrapper wrapper
     * @return List<TrafficRestrictionPolicy>
     */
    @Override
    public List<TrafficRestrictionPolicy> selectListPolicy(Wrapper<TrafficRestrictionPolicy> wrapper) {
        return baseMapper.selectListPolicy(wrapper);
    }

    /**
     * 去坐标查询限行政策
     *
     * @param wrapper wrapper
     * @return TrafficRestrictionPolicy
     */
    @Override
    public TrafficRestrictionPolicy selectOnePolicy(Wrapper<TrafficRestrictionPolicy> wrapper) {
        return baseMapper.selectOnePolicy(wrapper);
    }
}
