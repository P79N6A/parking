package com.zhuyitech.parking.tool.service.api;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.tool.dto.request.oilprice.*;
import com.zhuyitech.parking.tool.dto.result.oilprice.OilPriceDetailResultDto;
import com.zhuyitech.parking.tool.dto.result.oilprice.OilPriceListResultDto;
import com.zhuyitech.parking.tool.dto.result.oilprice.OilPriceResultDto;

/**
 * 油价服务
 *
 * @author zwq
 */
public interface OilPriceService {

    /**
     * 获取城市油价
     *
     * @param requestDto OilPriceRequestDto
     * @return OilPriceResultDto
     */
    ObjectResultDto<OilPriceResultDto> oilPriceRequest(OilPriceRequestDto requestDto);

    /**
     * 城市油价入库
     *
     * @param requestDto OilPricePutInRequestDto
     * @return ResultDto
     */
    ResultDto oilPricePutIn(OilPricePutInRequestDto requestDto);

    /**
     * 获取油价列表
     *
     * @param requestDto OilPriceListRequestDto
     * @return OilPriceListResultDto
     */
    ObjectResultDto<OilPriceListResultDto> getOliPriceList(OilPriceListRequestDto requestDto);

    /**
     * 获取油价详情
     *
     * @param requestDto OilPriceDetailRequestDto
     * @return OilPriceDetailResultDto
     */
    ObjectResultDto<OilPriceDetailResultDto> getOilPriceDetail(OilPriceDetailRequestDto requestDto);

    /**
     * 油价均值计算
     *
     * @return
     */
    ResultDto calculateOilPriceAvg();

    /**
     *
     * @param requestDto
     * @return
     */
    ResultDto sysOilPriceData(OilPricePutInRequestDto requestDto);
}
