package com.zhuyitech.parking.tool.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.parking.tool.domain.TrafficRestriction;

import java.util.Date;

/**
 * @author AkeemSuper
 */
public interface TrafficRestrictionCrudService extends CrudService<TrafficRestriction> {

    /**
     * 根据日期和城市拼音获取限行
     */
    TrafficRestriction findOneTrafficRestriction(String cityPinyin, String date);

    /**
     * 删除限行记录
     *
     * @param date date
     */
    void deletedTrafficLimit(Date date);

}
