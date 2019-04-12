package com.zhuyitech.parking.tool.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.parking.tool.domain.OilPrice;

import java.util.List;

/**
 * @author zwq
 */
public interface OilPriceCrudService extends CrudService<OilPrice> {

    /**
     * 根据code查询某省当天油价
     *
     * @param code 省份
     * @return OilPrice
     */
    OilPrice selectCurrentOilPrice(String code);

    /**
     * 根据code获取最近几月油价的均值
     *
     * @param code  省份
     * @param limit 几个
     * @return List<OilPrice>
     */
    List<OilPrice> getAvgPrice(String code, Integer limit);

    /**
     * 获取所有省份当日油价
     * @param oilDate
     * @param priceType
     * @return
     */
    List<OilPrice> getCurrentOilPrice(String oilDate, Integer priceType);
}