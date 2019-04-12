package com.zhuyitech.parking.tool.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuyitech.parking.tool.domain.TrafficRestriction;
import com.zhuyitech.parking.tool.mapper.TrafficRestrictionMapper;
import com.zhuyitech.parking.tool.service.TrafficRestrictionCrudService;
import org.springframework.stereotype.Service;
import java.util.Date;

/**
 * 查询限行的crudService
 *
 * @author AkeemSuper
 */
@Service("trafficRestrictionCrudService")
public class TrafficRestrictionCrudServiceImpl extends ServiceImpl<TrafficRestrictionMapper, TrafficRestriction> implements TrafficRestrictionCrudService {

    /**
     * 获取限行信息
     */
    @Override
    public TrafficRestriction findOneTrafficRestriction(String cityPinyin, String date) {
        EntityWrapper<TrafficRestriction> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("city", cityPinyin);
        entityWrapper.eq("date", date);
        return baseMapper.findOneTrafficRestriction(entityWrapper);
    }

    /**
     * 删除限行记录
     */
    @Override
    public void deletedTrafficLimit(Date date) {
        baseMapper.deletedTrafficLimit(date);
    }
}
