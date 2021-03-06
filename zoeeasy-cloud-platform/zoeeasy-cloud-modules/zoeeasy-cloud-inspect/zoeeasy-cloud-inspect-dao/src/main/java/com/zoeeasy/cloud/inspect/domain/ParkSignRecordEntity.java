package com.zoeeasy.cloud.inspect.domain;

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
 * @author AkeemSuper
 * @date 2018/9/20 0020
 */
@TableName("spe_park_sign_record")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkSignRecordEntity extends CreationAuditedEntity<Long> implements Serializable {

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
     * 停车场ID
     */
    @TableField(value = "parkingId")
    private Long parkingId;

    /**
     * 巡检员收费员ID
     */
    @TableField(value = "userId")
    private Long userId;

    /**
     * 巡检员收费员编号
     */
    @TableField(value = "userName")
    private Long userName;

    /**
     * 操作类型：1签到；2签退
     */
    @TableField(value = "signType")
    private Integer signType;

    /**
     * 操作时间
     */
    @TableField(value = "signDate")
    private Date signDate;

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
