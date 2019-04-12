package com.zoeeasy.cloud.charge.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.auditing.AuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 收费时段实体
 *
 * @author AkeemSuper
 * @date 2018/9/13 0013
 */
@TableName("chg_charge_rule_time")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class ChargeRuleTimeEntity extends AuditedEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户ID
     */
    @TableField(value = "tenantId")
    private Long tenantId;

    /**
     * 规则Id
     */
    @TableField(value = "ruleId")
    private Long ruleId;

    /**
     * 时间段
     */
    @TableField(value = "timePart")
    private Integer timePart;

    /**
     * 时间段的单价
     */
    @TableField(value = "price")
    private Integer price;

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
}
