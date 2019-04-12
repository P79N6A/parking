package com.zoeeasy.cloud.integration.pay;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.pay.trade.dto.request.alipay.AlipayAsyncNotifyResultRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.weixin.WeixinAsyncNotifyResultRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.result.alipay.AlipayAsyncNotifyResultDto;
import com.zoeeasy.cloud.pay.trade.dto.result.weixin.WeixinAsyncNotifyResultDto;

/**
 * 支付通知回调服务
 *
 * @author walkman
 * @since 2018-01-17
 */
public interface PayNotifyService {

    /**
     * 支付宝异步通知处理
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<AlipayAsyncNotifyResultDto> processAliPayNotify(AlipayAsyncNotifyResultRequestDto requestDto);

    /**
     * 微信异步通知处理
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<WeixinAsyncNotifyResultDto> processWeixinNotify(WeixinAsyncNotifyResultRequestDto requestDto);
}
