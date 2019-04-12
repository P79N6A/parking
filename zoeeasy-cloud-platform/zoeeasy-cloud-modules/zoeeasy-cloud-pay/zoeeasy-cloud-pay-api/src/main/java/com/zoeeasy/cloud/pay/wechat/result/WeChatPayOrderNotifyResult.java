package com.zoeeasy.cloud.pay.wechat.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 *     微信支付结果通知
 * </pre>
 *
 * @author walkman
 * @date 2017-07-12-10:39
 */
@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = false)
public class WeChatPayOrderNotifyResult extends WeChatPayBaseResult {

    private static final long serialVersionUID = 5403915269945081741L;
    /**
     * device_info
     */
    @XStreamAlias("device_info")
    private String deviceInfo;

    /**
     * 是否关注公众账号
     */
    @XStreamAlias("is_subscribe")
    private String isSubscribed;

    /**
     * 货币类型
     */
    @XStreamAlias("fee_type")
    private String feeType;

    /**
     * 现金支付货币类型
     */
    @XStreamAlias("cash_fee_type")
    private String cashFeeType;

    /**
     * 代金券金额
     */
    @XStreamAlias("coupon_fee")
    private Integer couponFee;

    /**
     * 代金券数量
     */
    @XStreamAlias("coupon_count")
    private Integer couponCount;

    /**
     * 商家数据包
     */
    @XStreamAlias("attach")
    private String attach;

    /**
     * 用户标识
     */
    @XStreamAlias("openid")
    private String openid;

    /**
     * 交易类型
     * JSAPI、JSAPI、NATIVE、APP
     */
    @XStreamAlias("trade_type")
    private String tradeType;

    /**
     * 付款银行
     * 银行类型，采用字符串类型的银行标识，银行类型见银行列表
     */
    @XStreamAlias("bank_type")
    private String bankType;

    /**
     * 订单总金额，单位为分
     */
    @XStreamAlias("total_fee")
    private Integer totalFee;

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
     * 支付完成时间
     */
    @XStreamAlias("time_end")
    private String timeEnd;

    /**
     * coupon_type
     */
    @XStreamAlias("coupon_type")
    private String couponType;

    /**
     * sign_type
     */
    @XStreamAlias("sign_type")
    private String signType;

    /**
     * settlement_total_fee
     */
    @XStreamAlias("settlement_total_fee")
    private String settlementTotalFee;
}
