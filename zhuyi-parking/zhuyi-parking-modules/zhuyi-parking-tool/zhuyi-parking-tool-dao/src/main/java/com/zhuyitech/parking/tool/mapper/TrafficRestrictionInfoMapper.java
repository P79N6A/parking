package com.zhuyitech.parking.tool.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhuyitech.parking.tool.domain.TrafficRestrictionInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;

/**
 * 第三方接口消息请求表mapper
 *
 * @author AkeemSuper
 * @date 2018/4/10 0010
 */
@Repository
public interface TrafficRestrictionInfoMapper extends BaseMapper<TrafficRestrictionInfo> {

    /**
     * 删除7天前的限行详情
     * @param date
     */
    void deleteTrafficInfo(@Param("date") Date date);
}
