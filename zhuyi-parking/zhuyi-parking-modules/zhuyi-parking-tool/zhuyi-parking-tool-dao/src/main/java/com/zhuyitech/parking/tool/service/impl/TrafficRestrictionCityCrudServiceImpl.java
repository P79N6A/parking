package com.zhuyitech.parking.tool.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuyitech.parking.tool.domain.TrafficRestrictionCity;
import com.zhuyitech.parking.tool.mapper.TrafficRestrictionCityMapper;
import com.zhuyitech.parking.tool.service.TrafficRestrictionCityCrudService;
import org.springframework.stereotype.Service;

/**
 * 查询限行城市的crudService
 *
 * @author AkeemSuper
 */
@Service("trafficRestrictionCityCrudService")
public class TrafficRestrictionCityCrudServiceImpl extends ServiceImpl<TrafficRestrictionCityMapper, TrafficRestrictionCity> implements TrafficRestrictionCityCrudService {

}
