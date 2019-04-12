package com.zoeeasy.cloud.integration.pay;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.pay.trade.dto.request.alipay.AlipayConfigGetRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.weixin.JsapiConfigGetRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.weixin.WeixinConfigGetRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.result.alipay.AlipayConfigResultDto;
import com.zoeeasy.cloud.pay.trade.dto.result.weixin.JsapiConfigResultDto;
import com.zoeeasy.cloud.pay.trade.dto.result.weixin.WeixinConfigResultDto;

/**
 * 支付配置集成服务
 *
 * @author walkman
 */
public interface PayConfigIntegrationService {

    /**
     * 获取支付宝必要参数
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<AlipayConfigResultDto> getAlipayParams(AlipayConfigGetRequestDto requestDto);

    /**
     * 获取微信必要参数
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<WeixinConfigResultDto> getWeixinParams(WeixinConfigGetRequestDto requestDto);

    /**
     * 获取微信公众号必要参数
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<JsapiConfigResultDto> getJsapiParams(JsapiConfigGetRequestDto requestDto);
}
