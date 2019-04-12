package com.zoeeasy.cloud.pay.wechat.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 *   统一下单 (在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"返回的结果)
 * </pre>
 *
 * @author walkman
 * @date 2017-07-11-11:14
 */
@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = false)
public class WeChatPayUnifiedOrderResult extends WeChatPayBaseResult {

    /**
     * 微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
     */
    @XStreamAlias("prepay_id")
    private String prepayId;

    /**
     * 交易类型，取值为：JSAPI，NATIVE，APP等
     */
    @XStreamAlias("trade_type")
    private String tradeType;

    /**
     * trade_type为NATIVE时有返回，用于生成二维码，展示给用户进行扫码支付
     */
    @XStreamAlias("code_url")
    private String codeURL;

    /**
     * 设备号
     */
    @XStreamAlias("device_info")
    private String deviceInfo;

    /**
     * 时间戳
     */
    @XStreamAlias("timeStamp")
    private String timeStamp;

    /**
     * 固定值
     */
    @XStreamAlias("pack")
    private String pack;
}
