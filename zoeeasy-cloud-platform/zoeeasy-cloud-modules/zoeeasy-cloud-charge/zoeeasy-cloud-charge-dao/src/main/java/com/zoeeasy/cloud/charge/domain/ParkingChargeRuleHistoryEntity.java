package com.zoeeasy.cloud.charge.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.auditing.CreationAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 停车场历史收费规则实体
 *
 * @author AkeemSuper
 * @date 2018/9/13 0013
 */
@TableName("chg_parking_charge_rule_history")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingChargeRuleHistoryEntity extends CreationAuditedEntity<Long> {

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
     * 停车场Id
     */
    @TableField(value = "parkingId")
    private Long parkingId;

    /**
     * 规则Id
     */
    @TableField(value = "ruleId")
    private Long ruleId;

    /**
     * 上线时间
     */
    @TableField(value = "onlineTime")
    private Date onlineTime;

    /**
     * 下线时间
     */
    @TableField(value = "offLineTime")
    private Date offlineTime;

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

}
