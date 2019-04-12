package com.zoeeasy.cloud.pay.wechat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 *     关闭订单
 * </pre>
 *
 * @author walkman
 * @date 2017-07-12-11:23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WeChatPayCloseOrderBean extends WeChatPayBaseBean {

    /**
     * 商户订单号
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

}
