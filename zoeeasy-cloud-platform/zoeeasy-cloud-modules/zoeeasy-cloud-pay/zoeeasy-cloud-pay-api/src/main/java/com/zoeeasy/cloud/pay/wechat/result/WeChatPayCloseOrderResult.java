package com.zoeeasy.cloud.pay.wechat.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 *     关闭订单结果
 * </pre>
 *
 * @author walkman
 * @date 2017-07-11-11:28
 */
@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = false)
public class WeChatPayCloseOrderResult extends WeChatPayBaseResult {

    /**
     * 微信订单号
     */
    @XStreamAlias("result_msg")
    private String resultMsg;
}
