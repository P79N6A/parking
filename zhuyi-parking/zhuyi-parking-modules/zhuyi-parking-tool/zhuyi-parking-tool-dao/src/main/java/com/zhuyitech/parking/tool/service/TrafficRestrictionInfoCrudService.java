package com.zhuyitech.parking.tool.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.parking.tool.domain.TrafficRestrictionInfo;
import java.util.Date;

/**
 * @author AkeemSuper
 */
public interface TrafficRestrictionInfoCrudService extends CrudService<TrafficRestrictionInfo> {

    /**
     * 删出7天前的限行详情
     * @param date
     */
    void deleteTrafficInfo(Date date);

}
