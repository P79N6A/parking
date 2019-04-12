package com.zoeeasy.cloud.charge.domain;

import com.baomidou.mybatisplus.annotations.*;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.IVersion;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 停车场预定规则实体对象
 *
 * @author zwq
 */
@TableName("chg_appointment_rule")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class AppointmentRuleEntity extends FullAuditedEntity<Long> implements IVersion<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户ID
     */
    @TableField(value = "tenantId")
    private Long tenantId;

    /**
     * 预约规则编号
     */
    @TableField(value = "ruleCode")
    private String ruleCode;

    /**
     * 预约规则名称
     */
    @TableField(value = "ruleName")
    private String ruleName;

    /**
     * 开放预约开始时间
     */
    @TableField(value = "startTime")
    private String startTime;

    /**
     * 开放预约结束时间
     */
    @TableField(value = "endTime")
    private String endTime;

    /**
     * 预定单位时长(分钟)
     */
    @TableField(value = "unitAppointLength")
    private Integer unitAppointLength;

    /**
     * 预定最大时长(分钟)
     */
    @TableField(value = "maxAppointLength")
    private Integer maxAppointLength;

    /**
     * 单位时长(分钟)收取金额
     */
    @TableField(value = "unitPrice")
    private Integer unitPrice;

    /**
     * 预定最大收费金额
     */
    @TableField(value = "maxAppointAmount")
    private Integer maxAppointAmount;

    /**
     * 单次收取金额
     */
    @TableField(value = "countAppointPrice")
    private Integer countAppointPrice;

    /**
     * 预定收取手续费
     */
    @TableField(value = "fee")
    private Integer fee;

    /**
     * 免收手续费时长
     */
    @TableField(value = "feeFreeLength")
    private Integer feeFreeLength;

    /**
     * 预定支付时限(分钟)
     */
    @TableField(value = "payLimit")
    private Integer payLimit;

    /**
     * 预定取消时限(分钟),以下单时间开始
     */
    @TableField(value = "cancelLimit")
    private Integer cancelLimit;

    /**
     * 预定取消单位时长(分钟)
     */
    @TableField(value = "unitCancelLength")
    private Integer unitCancelLength;

    /**
     * 单位时长(分钟)收取金额
     */
    @TableField(value = "unitCancelPrice")
    private Integer unitCancelPrice;

    /**
     * 预定取消最大收费金额
     */
    @TableField(value = "maxCancelAmount")
    private Integer maxCancelAmount;

    /**
     * 单次取消收取金额
     */
    @TableField(value = "countCancelPrice")
    private Integer countCancelPrice;

    /**
     * 超时时限(分钟),以预计入场时间开始
     */
    @TableField(value = "overTimeLimit")
    private Integer overTimeLimit;

    /**
     * 超时是否取消
     */
    @TableField(value = "overTimeCancel")
    private Boolean overTimeCancel;

    /**
     * 预定取消单位时长(分钟)
     */
    @TableField(value = "unitOvertimeCancelLength")
    private Integer unitOvertimeCancelLength;

    /**
     * 单位时长(分钟)收取金额
     */
    @TableField(value = "unitOvertimeCancelPrice")
    private Integer unitOvertimeCancelPrice;

    /**
     * 预定取消最大收费金额
     */
    @TableField(value = "maxOvertimeCancelAmount")
    private Integer maxOvertimeCancelAmount;

    /**
     * 单次取消收取金额
     */
    @TableField(value = "countOvertimeCancelPrice")
    private Integer countOvertimeCancelPrice;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 预约弹性入场时间
     */
    @TableField(value = "flexTime")
    private Integer flexTime;

    /**
     * 已支付可退款时长
     */
    @TableField(value = "refundLimit")
    private Integer refundLimit;

    /**
     * 是否退款
     */
    @TableField(value = "canRefund")
    private Boolean canRefund;

    /**
     * 收费类型
     */
    @TableField(value = "chargeType")
    private Integer chargeType;

    /**
     * 是否关联
     */
    @TableField(value = "used")
    private Boolean used;

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
