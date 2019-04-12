package com.zoeeasy.cloud.pay.domain;

import com.baomidou.mybatisplus.annotations.*;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.IVersion;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付宝支付订单
 *
 * @author zwq
 */
@TableName("pay_alipay_order")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class AlipayOrderEntity extends FullAuditedEntity<Long> implements Serializable, IVersion<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * tenantId
     */
    @TableId(value = "tenantId")
    private Long tenantId;

    /**
     * 车主用户ID
     */
    @TableField(value = "customerUserId")
    private Long customerUserId;

    /**
     * 订单号
     */
    @TableField(value = "tradeNo")
    private String tradeNo;

    /**
     * 外部订单号
     */
    @TableField(value = "outTradeNo")
    private String outTradeNo;

    /**
     * 商户业务号
     */
    @TableField(value = "outBizNo")
    private String outBizNo;

    /**
     * 二维码支付URL
     */
    @TableField(value = "qrCodeUrl")
    private String qrCodeUrl;

    /**
     * 订单名称
     */
    @TableField(value = "subject")
    private String subject;

    /**
     * 交易状态
     */
    @TableField(value = "tradeStatus")
    private Integer tradeStatus;

    /**
     * 订单金额
     */
    @TableField(value = "totalAmount")
    private BigDecimal totalAmount;

    /**
     * 实收金额
     */
    @TableField(value = "receiptAmount")
    private BigDecimal receiptAmount;

    /**
     * 实收金额
     */
    @TableField(value = "invoiceAmount")
    private BigDecimal invoiceAmount;

    /**
     * 用户实际支付金额
     */
    @TableField(value = "buyerPayAmount")
    private BigDecimal buyerPayAmount;

    /**
     * 集分宝金额
     */
    @TableField(value = "pointAmount")
    private BigDecimal pointAmount;

    /**
     * 总退款金额
     */
    @TableField(value = "refundFee")
    private BigDecimal refundFee;

    /**
     * 交易创建时间
     */
    @TableField(value = "gmtCreate")
    private Date gmtCreate;

    /**
     * 交易付款时间
     */
    @TableField(value = "gmtPayment")
    private Date gmtPayment;

    /**
     * 交易退款时间
     */
    @TableField(value = "gmtRefund")
    private Date gmtRefund;

    /**
     * 交易结束时间
     */
    @TableField(value = "gmtClose")
    private Date gmtClose;

    /**
     * 货币类型
     */
    @TableField(value = "currencyType")
    private String currencyType;

    /**
     * 商品描述
     */
    @TableField(value = "body")
    private String body;

    /**
     * 支付金额信息
     */
    @TableField(value = "fundBillList")
    private String fundBillList;

    /**
     * 回传参数
     */
    @TableField(value = "passbackParams")
    private String passbackParams;

    /**
     * 优惠券信息
     */
    @TableField(value = "voucherDetailList")
    private String voucherDetailList;

    /**
     * 交易过期时间
     */
    @TableField(value = "timeoutExpress")
    private String timeoutExpress;

    /**
     * 商品主类型：0—虚拟类商品，1—实物类商品
     */
    @TableField(value = "goodsType")
    private Integer goodsType;

    /**
     * 优惠参数
     */
    @TableField(value = "promoParams")
    private String promoParams;

    /**
     * 业务扩展参数
     */
    @TableField(value = "extendParams")
    private String extendParams;

    /**
     * 可用渠道
     */
    @TableField(value = "enablePayChannels")
    private String enablePayChannels;

    /**
     * 禁用渠道
     */
    @TableField(value = "disablePayChannels")
    private String disablePayChannels;

    /**
     * 商户门店编号
     */
    @TableField(value = "storeId")
    private String storeId;

    /**
     * 外部指定买家
     */
    @TableField(value = "extUserInfo")
    private String extUserInfo;

    /**
     * 买家支付宝用户号
     */
    @TableField(value = "buyerId")
    private String buyerId;

    /**
     * 买家支付宝账号
     */
    @TableField(value = "buyerLogonId")
    private String buyerLogonId;

    /**
     * 卖家支付宝用户号
     */
    @TableField(value = "sellerId")
    private String sellerId;

    /**
     * 卖家支付宝账号
     */
    @TableField(value = "sellerEmail")
    private String sellerEmail;

    /**
     * 创建者
     */
    @TableField(value = "creatorUserId", fill = FieldFill.INSERT)
    private Long creatorUserId;

    /**
     * 创建日期
     */
    @TableField(value = "creationTime", fill = FieldFill.INSERT)
    private Date creationTime;

    /**
     * 更新者
     */
    @TableField(value = "lastModifierUserId", fill = FieldFill.INSERT_UPDATE)
    private Long lastModifierUserId;

    /**
     * 更新日期
     */
    @TableField(value = "lastModificationTime", fill = FieldFill.INSERT_UPDATE)
    private Date lastModificationTime;

    /**
     * 删除者
     */
    @TableField(value = "deleterUserId", fill = FieldFill.UPDATE)
    private Long deleterUserId;

    /**
     * 删除日期
     */
    @TableField(value = "deletionTime", fill = FieldFill.UPDATE)
    private Date deletionTime;

    /**
     * 删除标记（0：正常；1：删除 ）
     */
    @TableField(value = "deleted", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleted;

    /**
     * 版本号
     */
    @Version
    private Long version;
}
