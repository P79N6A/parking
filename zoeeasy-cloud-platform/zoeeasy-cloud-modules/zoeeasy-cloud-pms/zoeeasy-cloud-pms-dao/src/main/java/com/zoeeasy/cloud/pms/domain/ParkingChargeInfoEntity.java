package com.zoeeasy.cloud.pms.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.auditing.CreationAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author AkeemSuper
 */
@TableName("pms_parking_charge_info")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingChargeInfoEntity extends CreationAuditedEntity<Long> implements Serializable {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户ID
     */
    @TableField("tenantId")
    private Long tenantId;

    /**
     * 停车场ID
     */
    @TableField("parkingId")
    private Long parkingId;

    /**
     * 收费规则的简短描述
     */
    @TableField("chargeRule")
    private String chargeRule;

    /**
     * 收费描述信息
     */
    @TableField("chargeDescription")
    private String chargeDescription;

    /**
     * 是否当前有效的收费信息
     */
    @TableField("relatedRule")
    private Boolean relatedRule;

    /**
     * 是否当前有效的收费信息
     */
    @TableField("active")
    private Boolean active;

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

}
