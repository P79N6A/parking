package com.zhuyitech.parking.ucc.domain;

import com.baomidou.mybatisplus.annotations.*;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.IVersion;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 用户账户信息
 *
 * @author walkman
 */
@TableName("up_user_asset")
public class UserAsset extends FullAuditedEntity<Long> implements IVersion<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField(value = "userId")
    private Long userId;

    /**
     * 账户余额
     */
    @TableField(value = "balance")
    private BigDecimal balance;

    /**
     * 用户积分
     */
    @TableField(value = "point")
    private BigDecimal point;

    /**
     * 可用优惠券数量
     */
    @TableField(value = "couponCount")
    private Integer couponCount;

    /**
     * 可用优惠券金额
     */
    @TableField(value = "couponBalance")
    private BigDecimal couponBalance;

    /**
     * 红包数量
     */
    @TableField(value = "packetCount")
    private Integer packetCount;

    /**
     * 红包金额
     */
    @TableField(value = "packetBalance")
    private BigDecimal packetBalance;

    /**
     * 未支付订单数
     */
    @TableField(value = "unPaidCount")
    private Integer unPaidCount;

    /**
     * 未支付金额
     */
    @TableField(value = "unPaidBalance")
    private BigDecimal unPaidBalance;

    /**
     * 违章停车数
     */
    @TableField(value = "violationCount")
    private Integer violationCount;

    /**
     * 违章停车金额
     */
    @TableField(value = "violationBalance")
    private BigDecimal violationBalance;

    /**
     * 创建者
     */
    @TableField(value = "creatorUserId", fill = FieldFill.INSERT)
    protected Long creatorUserId;

    /**
     * 创建日期
     */
    @TableField(value = "creationTime", fill = FieldFill.INSERT)
    protected Date creationTime;

    /**
     * 更新者
     */
    @TableField(value = "lastModifierUserId", fill = FieldFill.INSERT_UPDATE)
    protected Long lastModifierUserId;

    /**
     * 更新日期
     */
    @TableField(value = "lastModificationTime", fill = FieldFill.INSERT_UPDATE)
    protected Date lastModificationTime;

    /**
     * 删除者
     */
    @TableField(value = "deleterUserId", fill = FieldFill.UPDATE)
    protected Long deleterUserId;

    /**
     * 删除日期
     */
    @TableField(value = "deletionTime", fill = FieldFill.UPDATE)
    protected Date deletionTime;

    /**
     * 删除标记（0：正常；1：删除 ）
     */
    @TableField(value = "deleted", fill = FieldFill.INSERT)
    @TableLogic
    protected Integer deleted;

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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    public BigDecimal getCouponBalance() {
        return couponBalance;
    }

    public void setCouponBalance(BigDecimal couponBalance) {
        this.couponBalance = couponBalance;
    }

    public Integer getPacketCount() {
        return packetCount;
    }

    public void setPacketCount(Integer packetCount) {
        this.packetCount = packetCount;
    }

    public BigDecimal getPacketBalance() {
        return packetBalance;
    }

    public void setPacketBalance(BigDecimal packetBalance) {
        this.packetBalance = packetBalance;
    }

    public Integer getUnPaidCount() {
        return unPaidCount;
    }

    public void setUnPaidCount(Integer unPaidCount) {
        this.unPaidCount = unPaidCount;
    }

    public BigDecimal getUnPaidBalance() {
        return unPaidBalance;
    }

    public void setUnPaidBalance(BigDecimal unPaidBalance) {
        this.unPaidBalance = unPaidBalance;
    }

    public Integer getViolationCount() {
        return violationCount;
    }

    public void setViolationCount(Integer violationCount) {
        this.violationCount = violationCount;
    }

    public BigDecimal getViolationBalance() {
        return violationBalance;
    }

    public void setViolationBalance(BigDecimal violationBalance) {
        this.violationBalance = violationBalance;
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