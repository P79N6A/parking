package com.zoeeasy.cloud.pms.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.auditing.CreationAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:停车场当前状态表
 * @Autor: Kane
 * @Date: 2018/9/14
 */
@TableName("pms_parking_current_info")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingCurrentInfoEntity extends CreationAuditedEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 运营商ID
     */
    @TableField("tenantId")
    private Long tenantId;

    /**
     * 停车场ID
     */
    @TableField("parkingId")
    private Long parkingId;

    /**
     * 运营状态
     * 0.正常营业  1.暂停营业
     */
    @TableField("operationState")
    private Integer operationState;

    /**
     * 可用车位数
     */
    @TableField("lotAvailable")
    private Integer lotAvailable;

    /**
     * 可预订剩余车位数
     */
    @TableField("lotAppointmentAvailable")
    private Integer lotAppointmentAvailable;
    
    /**
     * 剩余可包期车的数量
     */
    @TableField("lotBagAvailable")
    private Integer lotBagAvailable;

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
     * 创建者
     */
    @TableField(value = "creatorUserId", fill = FieldFill.INSERT)
    private Long creatorUserId;

    /**
     * 创建时间
     */
    @TableField(value = "creationTime", fill = FieldFill.INSERT)
    private Date creationTime;

}
