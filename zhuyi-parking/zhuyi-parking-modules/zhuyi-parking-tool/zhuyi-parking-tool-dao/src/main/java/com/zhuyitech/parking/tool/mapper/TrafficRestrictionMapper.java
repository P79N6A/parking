package com.zhuyitech.parking.tool.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zhuyitech.parking.tool.domain.TrafficRestriction;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;

/**
 * 限行记录mapper
 *
 * @author AkeemSuper
 * @date 2018/4/10 0010
 */
@Repository
public interface TrafficRestrictionMapper extends BaseMapper<TrafficRestriction> {

    /**
     * 获取限行信息
     */
    TrafficRestriction findOneTrafficRestriction(@Param("ew") Wrapper<TrafficRestriction> wrapper);

    /**
     * 删除限行记录
     */
    void deletedTrafficLimit(@Param("date") Date date);
}
