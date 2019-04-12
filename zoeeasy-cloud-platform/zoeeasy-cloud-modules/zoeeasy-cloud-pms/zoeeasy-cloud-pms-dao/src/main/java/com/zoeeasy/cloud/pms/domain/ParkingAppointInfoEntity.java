package com.zoeeasy.cloud.pms.domain;

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
import java.util.Date;

/**
 * @Description:停车场预约信息表
 * @Autor: Kane
 * @Date: 2018/9/14
 */
@TableName("pms_parking_appoint_info")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingAppointInfoEntity extends FullAuditedEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 是否支持将停车预定定金作为停车费的一部分
     * 0.否   1.是
     */
    @TableField("supportDeposit")
    private Boolean supportDeposit;

    /**
     * 支持预约车位总数
     */
    @TableField("lotAppointmentTotal")
    private Integer lotAppointmentTotal;

    /**
     * 预约规则的简短描述
     */
    @TableField("appointmentRule")
    private String appointmentRule;

    /**
     * 是否关联预约规则
     * 0.否  1.是
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
    private Long creatorUserId;

    /**
     * 创建时间
     */
    @TableField(value = "creationTime", fill = FieldFill.INSERT)
    private Date creationTime;

    /**
     * 更新者
     */
    @TableField(value = "lastModifierUserId", fill = FieldFill.INSERT_UPDATE)
    private Long lastModifierUserId;

    /**
     * 更新时间
     */
    @TableField(value = "lastModificationTime", fill = FieldFill.INSERT_UPDATE)
    private Date lastModificationTime;

    /**
     * 删除者
     */
    @TableField(value = "deleterUserId", fill = FieldFill.INSERT)
    private Long deleterUserId;

    /**
     * 删除时间
     */
    @TableField(value = "deletionTime", fill = FieldFill.UPDATE)
    private Date deletionTime;
    
    /**
     * 删除标记
     */
    @TableField(value = "deleted", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleted;

}
