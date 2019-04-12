package com.zoeeasy.cloud.pay.trade.dto.request.alipay;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 支付宝异步通知参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AlipayAsyncNotifyResultRequestDto", description = "支付宝异步通知参数")
public class AlipayAsyncNotifyResultRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * ip
     */
    private String ip;

    /**
     * url
     */
    private String url;

    /**
     * content
     */
    private String content;

    /**
     * 通知时间 通知的发送时间。格式为yyyy-MM-dd HH:mm:ss
     */
    private Date notifyTime;

    /**
     * 通知类型
     */
    private String notifyType;

    /**
     * 通知校验ID
     */
    private String notifyId;

    /**
     * 支付宝分配给开发者的应用Id
     */
    private String appId;

    /**
     * 编码格式
     */
    private String charset;

    /**
     * 接口版本
     */
    private String version;

    /**
     * 签名类型商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，
     * 推荐使用RSA2
     */
    private String signType;

    /**
     * 签名
     */
    private String sign;

    /**
     * 支付宝交易号
     */
    private String tradeNo;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 商户业务号,主要是退款通知中返回退款申请的流水号
     */
    private String outBizNo;

    /**
     * 买家支付宝用户号
     */
    private String buyerId;

    /**
     * 买家支付宝账号
     */
    private String buyerLogonId;

    /**
     * 卖家支付宝用户号
     */
    private String sellerId;

    /**
     * 卖家支付宝账号
     */
    private String sellerEmail;

    /**
     * 交易状态
     */
    private String tradeStatus;

    /**
     * 本次交易支付的订单金额，单位为人民币（元）
     */
    private BigDecimal totalAmount;

    /**
     * 商家在交易中实际收到的款项，单位为元
     */
    private BigDecimal receiptAmount;

    /**
     * 商家在交易中实际收到的款项，单位为元
     */
    private BigDecimal invoiceAmount;

    /**
     * 用户在交易中支付的金额
     */
    private BigDecimal buyerPayamount;

    /**
     * 使用集分宝支付的金额
     */
    private BigDecimal pointAmount;

    /**
     * 总退款金额,退款通知中，返回总退款金额，单位为元，支持两位小数
     */
    private BigDecimal refundFee;

    /**
     * 订单标题
     */
    private String subject;

    /**
     * 商品描述
     */
    private String body;

    /**
     * 交易创建时间 该笔交易创建的时间。格式为yyyy-MM-
     * dd HH:mm:ss
     */
    private Date gmtCreate;

    /**
     * 交易付款时间 该笔交易的买家付款时间。格式为yyyy-MM-
     * dd HH:mm:ss
     */
    private Date gmtPayment;

    /**
     * 交易退款时间 该笔交易的退款时间。格式为yyyy-MM-
     * dd HH:mm:ss.S
     */
    private Date gmtRefund;

    /**
     * 交易结束时间 该笔交易结束时间。格式为yyyy-MM-
     * dd HH:mm:ss
     */
    private Date gmtClose;

    /**
     * 支付金额信息
     */
    private String fundBillList;

    /**
     * 回传参数,公共回传参数，如果请求时传递了该参数，则返回给商户时会在异步通知时将该参数原样返回。本参数必须进行UrlEncode之后才可以发送给支付宝
     */
    private String passbackParams;

    /**
     * 本交易支付时所使用的所有优惠券信息，详见优惠券信息说明
     */
    private String voucherDetailList;
}