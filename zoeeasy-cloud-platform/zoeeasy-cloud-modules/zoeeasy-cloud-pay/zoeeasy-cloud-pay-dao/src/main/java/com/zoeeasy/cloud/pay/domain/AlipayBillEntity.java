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
 * 支付宝对账单
 *
 * @author zwq
 */
@TableName("pay_alipay_bill")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class AlipayBillEntity extends FullAuditedEntity<Long> implements Serializable {

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
     * 对账单日期
     */
    @TableField(value = "billDate")
    private Date billDate;

    /**
     * 业务类型
     */
    @TableField(value = "payType")
    private String payType;

    /**
     * 订单号
     */
    @TableField(value = "orderNo")
    private String orderNo;

    /**
     * 外部订单号
     */
    @TableField(value = "outTradeNo")
    private String outTradeNo;

    /**
     * 商品名称
     */
    @TableField(value = "subject")
    private String subject;

    /**
     * 订单金额
     */
    @TableField(value = "amount")
    private BigDecimal amount;

    /**
     * 实收金额
     */
    @TableField(value = "receiptAmount")
    private BigDecimal receiptAmount;

    /**
     * 商户的门店编号
     */
    @TableField(value = "storeId")
    private String storeId;

    /**
     * 商户的门店名称
     */
    @TableField(value = "storeName")
    private String storeName;

    /**
     * 商户的操作员编号
     */
    @TableField(value = "operatorId")
    private String operatorId;

    /**
     * 商户的终端编号
     */
    @TableField(value = "terminalId")
    private String terminalId;

    /**
     * 支付宝红包
     */
    @TableField(value = "zfbPacket")
    private BigDecimal zfbPacket;

    /**
     * 集分宝
     */
    @TableField(value = "setPointTreasure")
    private BigDecimal setPointTreasure;

    /**
     * 支付宝优惠
     */
    @TableField(value = "zfbDiscount")
    private BigDecimal zfbDiscount;

    /**
     * 商家优惠
     */
    @TableField(value = "storeDiscount")
    private BigDecimal storeDiscount;

    /**
     * 券优惠
     */
    @TableField(value = "ticketDiscount")
    private BigDecimal ticketDiscount;

    /**
     * 券名称
     */
    @TableField(value = "ticketName")
    private String ticketName;

    /**
     * 商家红包消费金额
     */
    @TableField(value = "storePacketAmount")
    private BigDecimal storePacketAmount;

    /**
     * 卡消费金额
     */
    @TableField(value = "cardPacketAmount")
    private BigDecimal cardPacketAmount;

    /**
     * 服务费
     */
    @TableField(value = "serviceCharge")
    private BigDecimal serviceCharge;

    /**
     * 分润
     */
    @TableField(value = "feeSplitting")
    private BigDecimal feeSplitting;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 退款批次号/请求号
     */
    @TableField(value = "outRequestNo")
    private String outRequestNo;

    /**
     * 订单开始时间
     */
    @TableField(value = "startTime")
    private Date startTime;

    /**
     * 订单结束时间
     */
    @TableField(value = "endTime")
    private Date endTime;

    /**
     * 买家支付宝账号
     */
    @TableField(value = "buyerLogonId")
    private String buyerLogonId;

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