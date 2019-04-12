package com.zhuyitech.parking.tool.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zhuyitech.parking.common.constant.Const;
import com.zhuyitech.parking.tool.domain.OilPrice;
import com.zhuyitech.parking.tool.enums.OilPriceTypeEnum;
import com.zhuyitech.parking.tool.mapper.OilPriceMapper;
import com.zhuyitech.parking.tool.service.OilPriceCrudService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author zwq
 * @date 2018-04-12
 */
@Service("oilPriceCrudService")
public class OilPriceCrudServiceImpl extends ServiceImpl<OilPriceMapper, OilPrice> implements OilPriceCrudService {

    /**
     * 根据code查询某省当天油价
     *
     * @param code 省份
     */
    @Override
    public OilPrice selectCurrentOilPrice(String code) {
        EntityWrapper<OilPrice> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("code", code);
        entityWrapper.eq("priceType", OilPriceTypeEnum.REAL_PRICE.getValue());
        entityWrapper.eq("oilDate", DateUtils.formatDate(DateUtils.now(), Const.FORMAT_DATE));
        entityWrapper.last("limit 1");
        List<OilPrice> oilPrices = baseMapper.selectList(entityWrapper);
        if (!oilPrices.isEmpty()) {
            return oilPrices.get(0);
        }
        return null;
    }

    /**
     * 获取最近几月油价的均值
     *
     * @param code  省份cde
     * @param limit 几个月
     * @return List<OilPrice>
     */
    @Override
    public List<OilPrice> getAvgPrice(String code, Integer limit) {
        EntityWrapper<OilPrice> entityWrapper = new EntityWrapper<>();
        entityWrapper.like("code", code);
        entityWrapper.eq("priceType", OilPriceTypeEnum.AVG_PRICE.getValue());
        entityWrapper.orderBy("oilDate", false);
        entityWrapper.last("limit " + limit);
        return baseMapper.selectList(entityWrapper);
    }

    /**
     * 获取所有省份当日油价
     */
    @Override
    public List<OilPrice> getCurrentOilPrice(String oilDate, Integer priceType) {
        EntityWrapper<OilPrice> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("oilDate", oilDate);
        entityWrapper.eq("priceType", priceType);
        return baseMapper.selectList(entityWrapper);
    }

}

