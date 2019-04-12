package com.zhuyitech.parking.ucc.domain;


import com.baomidou.mybatisplus.annotations.*;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.IVersion;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户支付记录
 *
 * @author walkman
 */
@TableName("up_user_pay_order")
@SuppressWarnings("serial")
public class UserPayOrder extends FullAuditedEntity<Long> implements Serializable, IVersion<Long> {

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
     * 订单UUID
     */
    @TableField("orderUuid")
    private String orderUuid;

    /**
     * 系统支付订单号
     */
    @TableField("orderNo")
    private String orderNo;

    /**
     * 交易订单号
     */
    @TableField("transactionNo")
    private String transactionNo;

    /**
     * 订单类型（1.充值，2，付款）
     */
    @TableField("bizOrderType")
    private Integer bizOrderType;

    /**
     * 业务订单号（ 分别为充值订单号、缴费订单号）
     */
    @TableField("bizOrderNo")
    private String bizOrderNo;

    /**
     * 支付方式（1. 支付宝，2，微信）
     */
    @TableField("payWay")
    private Integer payWay;

    /**
     * 支付金额
     */
    @TableField("payAmount")
    private BigDecimal payAmount;

    /**
     * 实际支付金额
     */
    @TableField("payAmountActual")
    private BigDecimal payAmountActual;

    /**
     * 支付状态
     */
    @TableField("payStatus")
    private Integer payStatus;

    /**
     * 支付时间
     */
    @TableField("payTime")
    private Date payTime;

    /**
     * 支付成功时间
     */
    @TableField("succeedPayTime")
    private Date succeedPayTime;

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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderUuid() {
        return orderUuid;
    }

    public void setOrderUuid(String orderUuid) {
        this.orderUuid = orderUuid;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public Integer getBizOrderType() {
        return bizOrderType;
    }

    public void setBizOrderType(Integer bizOrderType) {
        this.bizOrderType = bizOrderType;
    }

    public String getBizOrderNo() {
        return bizOrderNo;
    }

    public void setBizOrderNo(String bizOrderNo) {
        this.bizOrderNo = bizOrderNo;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getPayAmountActual() {
        return payAmountActual;
    }

    public void setPayAmountActual(BigDecimal payAmountActual) {
        this.payAmountActual = payAmountActual;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getSucceedPayTime() {
        return succeedPayTime;
    }

    public void setSucceedPayTime(Date succeedPayTime) {
        this.succeedPayTime = succeedPayTime;
    }

    @Override
    public Long getCreatorUserId() {
        return creatorUserId;
    }

    @Override
    public void setCreatorUserId(Long creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    @Override
    public Date getCreationTime() {
        return creationTime;
    }

    @Override
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public Long getLastModifierUserId() {
        return lastModifierUserId;
    }

    @Override
    public void setLastModifierUserId(Long lastModifierUserId) {
        this.lastModifierUserId = lastModifierUserId;
    }

    @Override
    public Date getLastModificationTime() {
        return lastModificationTime;
    }

    @Override
    public void setLastModificationTime(Date lastModificationTime) {
        this.lastModificationTime = lastModificationTime;
    }

    @Override
    public Long getDeleterUserId() {
        return deleterUserId;
    }

    @Override
    public void setDeleterUserId(Long deleterUserId) {
        this.deleterUserId = deleterUserId;
    }

    @Override
    public Date getDeletionTime() {
        return deletionTime;
    }

    @Override
    public void setDeletionTime(Date deletionTime) {
        this.deletionTime = deletionTime;
    }

    @Override
    public Integer getDeleted() {
        return deleted;
    }

    @Override
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public Long getVersion() {
        return version;
    }

    @Override
    public void setVersion(Long version) {
        this.version = version;
    }
}
