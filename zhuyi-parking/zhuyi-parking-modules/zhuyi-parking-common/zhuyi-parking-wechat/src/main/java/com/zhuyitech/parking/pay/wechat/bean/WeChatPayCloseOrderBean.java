package com.zhuyitech.parking.pay.wechat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <pre>
 *     关闭订单
 * </pre>
 *
 * @author walkman
 * @date 2017-07-12-11:23
 */
public class WeChatPayCloseOrderBean extends WeChatPayBaseBean {

    /**
     * 商户订单号
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
}
