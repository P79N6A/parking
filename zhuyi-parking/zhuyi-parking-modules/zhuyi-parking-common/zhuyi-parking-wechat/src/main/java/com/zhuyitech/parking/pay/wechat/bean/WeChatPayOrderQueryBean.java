package com.zhuyitech.parking.pay.wechat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <pre>
 *   订单查询请求对象
 * </pre>
 *
 * @author walkman
 * @date 2017-07-11-10:39
 */
@XStreamAlias("xml")
public class WeChatPayOrderQueryBean extends WeChatPayBaseBean {

    /**
     * 微信订单号
     */
    @XStreamAlias("transaction_id")
    private String transactionId;

    /**
     * 商户订单号
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

}
