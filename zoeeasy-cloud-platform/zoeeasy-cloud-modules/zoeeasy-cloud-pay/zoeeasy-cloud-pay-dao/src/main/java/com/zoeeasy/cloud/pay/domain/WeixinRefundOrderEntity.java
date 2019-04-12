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
 * 微信退款订单
 *
 * @author zwq
 */
@TableName("pay_wxpay_refund_order")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class WeixinRefundOrderEntity extends FullAuditedEntity<Long> implements Serializable, IVersion<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField(value = "userId")
    private Long userId;

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
     * 偏移量
     */
    @TableField(value = "offset")
    private Integer offset;

    /**
     * 退款金额
     */
    @TableField(value = "refundFee")
    private BigDecimal refundFee;

    /**
     * 货币种类
     */
    @TableField(value = "refundFeeType")
    private String refundFeeType;

    /**
     * 退款原因
     */
    @TableField(value = "refundDesc")
    private String refundDesc;

    /**
     * 应结退款金额
     */
    @TableField(value = "settlementRefundFee")
    private BigDecimal settlementRefundFee;

    /**
     * 标价金额
     */
    @TableField(value = "totalFee")
    private BigDecimal totalFee;

    /**
     * 应结订单金额
     */
    @TableField(value = "settlementTotalFee")
    private BigDecimal settlementTotalFee;

    /**
     * 标价币种
     */
    @TableField(value = "feeType")
    private String feeType;

    /**
     * 现金支付金额
     */
    @TableField(value = "cashFee")
    private BigDecimal cashFee;

    /**
     * 现金支付币种
     */
    @TableField(value = "cashFeeType")
    private String cashFeeType;

    /**
     * 现金退款金额
     */
    @TableField(value = "cashRefundFee")
    private BigDecimal cashRefundFee;

    /**
     * 退款状态
     */
    @TableField(value = "refundStatus")
    private Integer refundStatus;

    /**
     * 退款状态
     */
    @TableField(value = "refundStatusDesc")
    private String refundStatusDesc;

    /**
     * 退款资金来源
     */
    @TableField(value = "refundAccount")
    private String refundAccount;

    /**
     * 退款入账账户
     */
    @TableField(value = "refundReceiveAccount")
    private String refundReceiveAccount;

    /**
     * 退款成功时间
     */
    @TableField(value = "refundSuccessTime")
    private Date refundSuccessTime;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

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