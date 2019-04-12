package com.zhuyitech.parking.pay.wechat.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <pre>
 *     关闭订单结果
 * </pre>
 *
 * @author walkman
 * @date 2017-07-11-11:28
 */
@XStreamAlias("xml")
public class WeChatPayCloseOrderResult extends WeChatPayBaseResult {

    /**
     * 微信订单号
     */
    @XStreamAlias("result_msg")
    private String resultMsg;

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
