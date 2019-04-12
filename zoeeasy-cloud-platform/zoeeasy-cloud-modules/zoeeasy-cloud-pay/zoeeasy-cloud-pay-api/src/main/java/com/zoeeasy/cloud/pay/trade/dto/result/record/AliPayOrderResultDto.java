package com.zoeeasy.cloud.pay.trade.dto.result.record;

import com.scapegoat.infrastructure.core.dto.result.FullAuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付宝支付记录视图
 *
 * @Date: 2018/3/2
 * @author: yuzhicheng
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AliPayRecordResultDto", description = "支付宝支付记录视图")
public class AliPayOrderResultDto extends FullAuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 用户ID
     */
    private Long customerUserId;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "tradeNo")
    private String tradeNo;

    /**
     * 外部订单号
     */
    @ApiModelProperty(value = "outTradeNo")
    private String outTradeNo;

    /**
     * 商户业务号
     */
    @ApiModelProperty(value = "outBizNo")
    private String outBizNo;

    /**
     * 订单名称
     */
    @ApiModelProperty(value = "subject")
    private String subject;

    /**
     * 交易状态
     */
    @ApiModelProperty(value = "tradeStatus")
    private Integer tradeStatus;

    /**
     * 订单金额
     */
    @ApiModelProperty(value = "totalAmount")
    private BigDecimal totalAmount;

    /**
     * 实收金额
     */
    @ApiModelProperty(value = "receiptAmount")
    private BigDecimal receiptAmount;

    /**
     * 实收金额
     */
    @ApiModelProperty(value = "invoiceAmount")
    private BigDecimal invoiceAmount;

    /**
     * 用户实际支付金额
     */
    @ApiModelProperty(value = "buyerPayAmount")
    private BigDecimal buyerPayAmount;

    /**
     * 集分宝金额
     */
    @ApiModelProperty(value = "pointAmount")
    private BigDecimal pointAmount;

    /**
     * 总退款金额
     */
    @ApiModelProperty(value = "refundFee")
    private BigDecimal refundFee;

    /**
     * 货币类型
     */
    @ApiModelProperty(value = "currencyType")
    private String currencyType;

    /**
     * 商品描述
     */
    @ApiModelProperty(value = "body")
    private String body;

    /**
     * 交易创建时间
     */
    @ApiModelProperty(value = "gmtCreate")
    private Date gmtCreate;

    /**
     * 交易付款时间
     */
    @ApiModelProperty(value = "gmtPayment")
    private Date gmtPayment;

    /**
     * 交易退款时间
     */
    @ApiModelProperty(value = "gmtRefund")
    private Date gmtRefund;

    /**
     * 交易结束时间
     */
    @ApiModelProperty(value = "gmtClose")
    private Date gmtClose;

    /**
     * 支付金额信息
     */
    @ApiModelProperty(value = "fundBillList")
    private String fundBillList;

    /**
     * 回传参数
     */
    @ApiModelProperty(value = "passbackParams")
    private String passbackParams;

    /**
     * 优惠券信息
     */
    @ApiModelProperty(value = "voucherDetailList")
    private String voucherDetailList;

    /**
     * 交易过期时间
     */
    @ApiModelProperty(value = "timeoutExpress")
    private String timeoutExpress;

    /**
     * 商品主类型：0—虚拟类商品，1—实物类商品
     */
    @ApiModelProperty(value = "goodsType")
    private Integer goodsType;

    /**
     * 优惠参数
     */
    @ApiModelProperty(value = "promoParams")
    private String promoParams;

    /**
     * 业务扩展参数
     */
    @ApiModelProperty(value = "extendParams")
    private String extendParams;

    /**
     * 可用渠道
     */
    @ApiModelProperty(value = "enablePayChannels")
    private String enablePayChannels;

    /**
     * 禁用渠道
     */
    @ApiModelProperty(value = "disablePayChannels")
    private String disablePayChannels;

    /**
     * 商户门店编号
     */
    @ApiModelProperty(value = "storeId")
    private String storeId;

    /**
     * 外部指定买家
     */
    @ApiModelProperty(value = "extUserInfo")
    private String extUserInfo;

    /**
     * 买家支付宝用户号
     */
    @ApiModelProperty(value = "buyerId")
    private String buyerId;

    /**
     * 买家支付宝账号
     */
    @ApiModelProperty(value = "buyerLogonId")
    private String buyerLogonId;

    /**
     * 卖家支付宝用户号
     */
    @ApiModelProperty(value = "sellerId")
    private String sellerId;

    /**
     * 卖家支付宝账号
     */
    @ApiModelProperty(value = "sellerEmail")
    private String sellerEmail;
}
