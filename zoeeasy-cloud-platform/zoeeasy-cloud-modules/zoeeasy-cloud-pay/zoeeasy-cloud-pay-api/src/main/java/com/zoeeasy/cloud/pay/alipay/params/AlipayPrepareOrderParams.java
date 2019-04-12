package com.zoeeasy.cloud.pay.alipay.params;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 支付宝下单请求参数
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayPrepareOrderParams extends AlipayPayBaseParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
     */
    private String body;

    /**
     * 商品的标题/交易标题/订单标题/订单关键字等
     */
    private String subject;

    /**
     * 商户网站唯一订单号
     */
    private String outTradeNo;

    /**
     * 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
     * 注：若为空，则默认为15d。
     */
    private String timeoutExpress;

    /**
     * 支付金额
     */
    private BigDecimal totalAmount;

    /**
     * 商品主类型：0—虚拟类商品，1—实物类商品
     * 注：虚拟类商品不支持使用花呗渠道
     */
    private Integer goodsType;

    /**
     * 公用回传参数，如果请求时传递了该参数，则返回给商户时会回传该参数。支付宝会在异步通知时将该参数原样返回。本参数必须进行UrlEncode之后才可以发送给支付宝
     */
    private String passbackParams;

    /**
     * 优惠参数
     */
    private String promoParams;

    /**
     * 业务扩展参数，详见下面的“业务扩展参数说明”
     */
    private String extendParams;

    /**
     * 可用渠道，用户只能在指定渠道范围内支付
     * 当有多个渠道时用“,”分隔
     * 注：与disable_pay_channels互斥
     */
    private String enablePayChannels;

    /**
     * 禁用渠道，用户不可用指定渠道支付
     * 当有多个渠道时用“,”分隔
     * 注：与enable_pay_channels互斥
     */
    private String disablePayChannels;

    /**
     * 商户门店编号。该参数用于请求参数中以区分各门店，非必传项。
     */
    private String storeId;
}
