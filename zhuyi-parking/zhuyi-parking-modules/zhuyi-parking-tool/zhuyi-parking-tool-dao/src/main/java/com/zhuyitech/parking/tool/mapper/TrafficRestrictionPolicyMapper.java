package com.zhuyitech.parking.tool.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zhuyitech.parking.tool.domain.TrafficRestrictionPolicy;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author AkeemSuper
 */
@Repository
public interface TrafficRestrictionPolicyMapper extends BaseMapper<TrafficRestrictionPolicy> {

    /**
     * 无坐标查询限行政策
     *
     * @param wrapper wrapper
     * @return TrafficRestrictionPolicy
     */
    TrafficRestrictionPolicy selectOnePolicy(@Param("ew") Wrapper<TrafficRestrictionPolicy> wrapper);

    /**
     * 无坐标查询限行政策
     *
     * @param wrapper wrapper
     * @return List<TrafficRestrictionPolicy>
     */
    List<TrafficRestrictionPolicy> selectListPolicy(@Param("ew") Wrapper<TrafficRestrictionPolicy> wrapper);
}
