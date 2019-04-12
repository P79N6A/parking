package com.zoeeasy.cloud.pay.trade.dto.result.alipay;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 */

@EqualsAndHashCode(callSuper = false)
public class AlipayTradeAppPayResponseDto extends BaseDto {

    @JsonProperty(value = "code")
    private String code;

    @JsonProperty(value = "msg")
    private String msg;

    @JsonProperty(value = "app_id")
    private String appId;

    @JsonProperty(value = "out_trade_no")
    private String outTradeNo;

    @JsonProperty(value = "trade_no")
    private String tradeNo;

    @JsonProperty(value = "total_amount")
    private BigDecimal totalAmount;

    @JsonProperty(value = "seller_id")
    private String sellerId;

    @JsonProperty("charset")
    private String charset;

    @JsonProperty("timestamp")
    private Date timestamp;

}
