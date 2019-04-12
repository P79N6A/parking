package com.zoeeasy.cloud.pay.wechat.params;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * <pre>
 *     关闭订单参数
 * </pre>
 *
 * @author walkman
 * @date 2017-07-12-11:25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WeChatCloseOrderParams extends WeChatPayBaseParam {

    /**
     * 商户订单号
     */
    @NotNull
    private String outTradeNo;

    /**
     * 随机字符串
     */
    @NotNull
    private String nonceStr;
}
