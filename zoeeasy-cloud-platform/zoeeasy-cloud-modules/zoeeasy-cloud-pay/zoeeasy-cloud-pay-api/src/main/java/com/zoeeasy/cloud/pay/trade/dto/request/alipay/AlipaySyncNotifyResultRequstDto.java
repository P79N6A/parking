package com.zoeeasy.cloud.pay.trade.dto.request.alipay;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.pay.trade.dto.result.alipay.AlipayTradeAppPayResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AlipaySyncNotifyResultRequstDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    @JsonProperty("alipay_trade_app_pay_response")
    private AlipayTradeAppPayResponseDto alipayTradeAppPayResponse;

    private String sing;

    @JsonProperty("sign_type")
    private String singType;
}
