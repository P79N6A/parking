package com.zhuyitech.parking.tool.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.parking.tool.domain.TrafficRestrictionPolicy;
import java.util.List;

/**
 * @author AkeemSuper
 */
public interface TrafficRestrictionPolicyCrudService extends CrudService<TrafficRestrictionPolicy> {

    /**
     * 去坐标查询限行政策
     *
     * @param wrapper wrapper
     * @return List<TrafficRestrictionPolicy>
     */
    List<TrafficRestrictionPolicy> selectListPolicy(Wrapper<TrafficRestrictionPolicy> wrapper);

    /**
     * 去坐标查询限行政策
     *
     * @param wrapper wrapper
     * @return TrafficRestrictionPolicy
     */
    TrafficRestrictionPolicy selectOnePolicy(Wrapper<TrafficRestrictionPolicy> wrapper);
}
