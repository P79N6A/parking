package com.zoeeasy.cloud.pay.wechat.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 *     查询订单
 * </pre>
 *
 * @author walkman
 * @date 2017-07-11-11:17
 */
@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = false)
public class WeChatPayOrderQueryResult extends WeChatPayBaseResult {

    /**
     * 用户标识(用户在商户appid下的唯一标识)
     */
    @XStreamAlias("openid")
    private String openid;

    /**
     * 是否关注公众账号
     * (用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效)
     */
    @XStreamAlias("is_subscribe")
    private String isSubscribe;

    /**
     * 交易类型
     */
    @XStreamAlias("trade_type")
    private String tradeType;

    /**
     * 交易状态
     */
    @XStreamAlias("trade_state")
    private String tradeState;

    /**
     * 付款银行
     */
    @XStreamAlias("bank_type")
    private String bankType;

    /**
     * 订单金额
     */
    @XStreamAlias("total_fee")
    private Integer totalFee;

    /**
     * 应结订单金额
     * (应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。)
     */
    @XStreamAlias("settlement_total_fee")
    private Integer settlementTotalFee;

    /**
     * 现金支付金额
     */
    @XStreamAlias("cash_fee")
    private Integer cashFee;

    /**
     * 微信支付订单号
     */
    @XStreamAlias("transaction_id")
    private String transactionId;
    /**
     * 商户订单号
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    /**
     * 附加数据，原样返回
     */
    @XStreamAlias("attach")
    private String attach;

    /**
     * 支付完成时间
     */
    @XStreamAlias("time_end")
    private String timeEnd;

    /**
     * 交易状态描述
     * (支付失败，请重新下单支付，对当前查询订单状态的描述和下一步操作的指引)
     */
    @XStreamAlias("trade_state_desc")
    private String tradeStateDesc;
}
