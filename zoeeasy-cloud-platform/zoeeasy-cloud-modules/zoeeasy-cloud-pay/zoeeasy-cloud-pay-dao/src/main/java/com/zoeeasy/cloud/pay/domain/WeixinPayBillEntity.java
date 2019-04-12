package com.zoeeasy.cloud.pay.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 微信支付对账单
 *
 * @author zwq
 */
@TableName("pay_wxpay_bill")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class WeixinPayBillEntity extends FullAuditedEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * tenantId
     */
    @TableField(value = "tenantId")
    private Long tenantId;

    /**
     * 商户订单号
     */
    @TableField(value = "outTradeNo")
    private String outTradeNo;

    /**
     * 微信支付订单号
     */
    @TableField(value = "transactionId")
    private String transactionId;

    /**
     * 商户退款单号
     */
    @TableField(value = "outRefundNo")
    private String outRefundNo;

    /**
     * 微信退款单号
     */
    @TableField(value = "refundId")
    private String refundId;

    /**
     * 用户标识
     */
    @TableField(value = "openid")
    private String openid;

    /**
     * 设备号
     */
    @TableField(value = "deviceInfo")
    private String deviceInfo;

    /**
     * 对账单日期
     */
    @TableField(value = "billDate")
    private Date billDate;

    /**
     * 账单类型
     */
    @TableField(value = "billType")
    private String billType;

    /**
     * 压缩账单
     */
    @TableField(value = "tarType")
    private String tarType;

    /**
     * 交易时间
     */
    @TableField(value = "tradeDate")
    private Date tradeDate;

    /**
     * 交易类型
     */
    @TableField(value = "tradeType")
    private String tradeType;

    /**
     * 交易状态
     */
    @TableField(value = "tradeState")
    private Integer tradeState;

    /**
     * 付款银行
     */
    @TableField(value = "bankType")
    private String bankType;

    /**
     * 货币种类
     */
    @TableField(value = "feeType")
    private String feeType;

    /**
     * 总金额
     */
    @TableField(value = "totalAmount")
    private Integer totalAmount;

    /**
     * 代金券或立减优惠金额
     */
    @TableField(value = "couponAmount")
    private Integer couponAmount;

    /**
     * 退款金额
     */
    @TableField(value = "refundAmount")
    private Integer refundAmount;

    /**
     * 代金券或立减优惠退款金额
     */
    @TableField(value = "couponRefundAmount")
    private Integer couponRefundAmount;

    /**
     * 退款类型
     */
    @TableField(value = "refundType")
    private String refundType;

    /**
     * 退款状态
     */
    @TableField(value = "refundState")
    private Integer refundState;

    /**
     * 手续费
     */
    @TableField(value = "feeRate")
    private Integer feeRate;

    /**
     * 费率
     */
    @TableField(value = "poundageRate")
    private BigDecimal poundageRate;

    /**
     * 商品名称
     */
    @TableField(value = "productName")
    private String productName;

    /**
     * 商户数据包
     */
    @TableField(value = "body")
    private String body;

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
}