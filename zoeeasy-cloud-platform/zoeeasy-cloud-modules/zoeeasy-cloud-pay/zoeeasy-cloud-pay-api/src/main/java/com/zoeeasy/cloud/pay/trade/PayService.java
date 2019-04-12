package com.zoeeasy.cloud.pay.trade;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pay.trade.dto.request.alipay.AlipayBillDownRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.weixin.WeixinPayBillDownRequestDto;

import java.util.Map;

/**
 * 支付服务
 *
 * @author walkman
 * @date 2018-01-17
 */
public interface PayService {

    /**
     * 支付宝异步通知验签
     *
     * @param map map
     * @return
     */
    ResultDto checkAlipaySignature(Map<String, String> map, String publicKey);

    /**
     * 支付宝账单下载
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto downloadAlipayBill(AlipayBillDownRequestDto requestDto);

    /**
     * 微信支付账单下载
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto downloadWeixinPayBill(WeixinPayBillDownRequestDto requestDto);
}
