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
 * 平台支付记录表
 *
 * @author zwq
 */
@TableName("pay_trade_payment_record")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class TradePaymentRecordEntity extends FullAuditedEntity<Long> implements Serializable, IVersion<Long> {

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
     * 车主用户ID
     */
    @TableField(value = "customerUserId")
    private Long customerUserId;

    /**
     * 交易订单号
     */
    @TableField(value = "orderNo")
    private String orderNo;

    /**
     * 支付业务订单号,对应支付宝tradeNo或微信支付outTradeNo
     */
    @TableField(value = "transactionNo")
    private String transactionNo;

    /**
     * 订单来源
     */
    @TableField(value = "orderFrom")
    private String orderFrom;

    /**
     * 交易业务类型 ：消费、充值等
     */
    @TableField(value = "bizOrderType")
    private Integer bizOrderType;

    /**
     * 交易业务订单号
     */
    @TableField(value = "bizOrderNo")
    private String bizOrderNo;


    /**
     * 订单金额
     */
    @TableField(value = "orderAmount")
    private Integer orderAmount;

    /**
     * 商品名称
     */
    @TableField(value = "productName")
    private String productName;

    /**
     * 付款人ID
     */
    @TableField(value = "payerUserId")
    private Long payerUserId;

    /**
     * 付款人名称
     */
    @TableField(value = "payerUsername")
    private String payerUsername;

    /**
     * 付款方支付金额
     */
    @TableField(value = "payerPayAmount")
    private Integer payerPayAmount;

    /**
     * 付款方手续费
     */
    @TableField(value = "payerFee")
    private Integer payerFee;

    /**
     * 付款方账户类型(参考账户类型枚举:AccountTypeEnum)
     */
    @TableField(value = "payerAccountType")
    private String payerAccountType;

    /**
     * 收款人ID
     */
    @TableField(value = "receiverUserId")
    private Long receiverUserId;

    /**
     * 收款人名称
     */
    @TableField(value = "receiverUsername")
    private String receiverUsername;

    /**
     * 收款方支付金额
     */
    @TableField(value = "receiverPayAmount")
    private Integer receiverPayAmount;

    /**
     * 收款方手续费
     */
    @TableField(value = "receiverFee")
    private Integer receiverFee;

    /**
     * 收款方账户类型(参考账户类型枚举:AccountTypeEnum)
     */
    @TableField(value = "receiverAccountType")
    private String receiverAccountType;

    /**
     * 订单日期
     */
    @TableField(value = "orderDate")
    private Date orderDate;

    /**
     * 订单时间
     */
    @TableField(value = "orderTime")
    private Date orderTime;

    /**
     * 下单IP(客户端IP,在网关页面获取)
     */
    @TableField(value = "orderIp")
    private String orderIp;

    /**
     * 从哪个页面链接过来的(可用于防诈骗)
     */
    @TableField(value = "orderRefererUrl")
    private String orderRefererUrl;

    /**
     * 页面回调通知URL
     */
    @TableField(value = "returnUrl")
    private String returnUrl;

    /**
     * 后台异步通知URL
     */
    @TableField(value = "notifyUrl")
    private String notifyUrl;

    /**
     * 二维码支付URL
     */
    @TableField(value = "qrCodeUrl")
    private String qrCodeUrl;

    /**
     * 平台收入
     */
    @TableField(value = "platformIncome")
    private Integer platformIncome;

    /**
     * 平台成本
     */
    @TableField(value = "platformCost")
    private Integer platformCost;

    /**
     * 平台利润
     */
    @TableField(value = "platformProfit")
    private Integer platformProfit;

    /**
     * 费率
     */
    @TableField(value = "feeRate")
    private BigDecimal feeRate;

    /**
     * 支付成功时间
     */
    @TableField(value = "succeedPayTime")
    private Date succeedPayTime;

    /**
     * 完成时间
     */
    @TableField(value = "completeTime")
    private Date completeTime;

    /**
     * 支付通道编号
     */
    @TableField(value = "payWay")
    private Integer payWay;

    /**
     * 支付方式类型编号
     */
    @TableField(value = "payType")
    private Integer payType;

    /**
     * 资金流入类型
     */
    @TableField(value = "fundIntoType")
    private Integer fundIntoType;

    /**
     * 是否退款(0:否,1: 是,默认值为:0)
     */
    @TableField(value = "refunded")
    private Boolean refunded;

    /**
     * 退款次数(默认值为:0)
     */
    @TableField(value = "refundTimes")
    private Long refundTimes;

    /**
     * 成功退款总金额
     */
    @TableField(value = "successRefundAmount")
    private Integer successRefundAmount;

    /**
     * 退款成功时间
     */
    @TableField(value = "succeedRefundTime")
    private Date succeedRefundTime;

    /**
     * 状态(参考枚举:PayStatusEnum)
     */
    @TableField(value = "status")
    private Integer status;

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