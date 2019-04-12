package com.zoeeasy.cloud.order.domain;

import com.baomidou.mybatisplus.annotations.*;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.IVersion;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;


/**
 * 收款订单
 *
 * @author walkman
 * @since 2019-01-10
 */
@TableName("ord_charge_order")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class ChargeOrderEntity extends FullAuditedEntity<Long> implements Serializable, IVersion<Long> {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * tenantId
     */
    @TableField(value = "tenantId")
    private Long tenantId;

    /**
     * 平台停车场ID
     */
    @TableField(value = "parkingId")
    private Long parkingId;

    /**
     * 业务订单类型
     */
    @TableField(value = "orderType")
    private Integer orderType;

    /**
     * 平台停车账单流水号
     */
    @TableField(value = "orderNo")
    private String orderNo;

    /**
     * 平台收款流水号
     */
    @TableField(value = "chargeNo")
    private String chargeNo;

    /**
     * 平台支付流水号
     */
    @TableField(value = "payOrderNo")
    private String payOrderNo;

    /**
     * 收费模式 1:离场前收费 2: 离场后收费
     */
    @TableField(value = "chargeMode")
    private Integer chargeMode;

    /**
     * 收费时间
     */
    @TableField(value = "parkingId")
    private Date chargeTime;

    /**
     * 应收金额(分)
     */
    @TableField(value = "chargeableAmount")
    private Integer chargeableAmount;

    /**
     * 实收金额(分)
     */
    @TableField(value = "chargedAmount")
    private Integer chargedAmount;

    /**
     * 支付状态 0 :未支付 1:已支付  2:支付中
     */
    @TableField(value = "payStatus")
    private Integer payStatus;

    /**
     * 应付付金额(分)
     */
    @TableField(value = "payableAmount")
    private Integer payableAmount;

    /**
     * 实付金额(分)
     */
    @TableField(value = "payedAmount")
    private Integer payedAmount;

    /**
     * 支付时间
     */
    @TableField(value = "payTime")
    private Date payTime;

    /**
     * 支付用户ID
     */
    @TableField(value = "payedUserId")
    private Long payedUserId;

    /**
     * 支付用户名称
     */
    @TableField(value = "payedUserName")
    private Long payedUserName;

    /**
     * 支付方式(根据PayWayEnum)
     */
    @TableField(value = "parkingId")
    private Integer payWay;

    /**
     * 支付类型(根据PayTypeEnum)
     */
    @TableField(value = "parkingId")
    private Integer payType;

    /**
     * 收款人ID
     */
    @TableField(value = "chargeUserId")
    private Long chargeUserId;

    /**
     * 收款人姓名
     */
    @TableField(value = "chargeUserName")
    private Long chargeUserName;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * TCC事务状态标志
     */
    @TableField(value = "tccStatus")
    private Integer tccStatus;

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
}