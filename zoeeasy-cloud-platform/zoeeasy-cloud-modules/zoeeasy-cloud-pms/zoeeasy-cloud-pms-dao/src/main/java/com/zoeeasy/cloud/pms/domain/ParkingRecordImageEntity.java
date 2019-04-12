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
 * @author AkeemSuper
 * @date 2018/9/20 0020
 */
@TableName("pms_parking_record_image")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingRecordImageEntity extends CreationAuditedEntity<Long> implements Serializable {

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
     * 停车记录ID
     */
    @TableField(value = "bizId")
    private Long bizId;

    /**
     * 停车记录ID
     */
    @TableField(value = "bizNo")
    private String bizNo;

    /**
     * 业务类型  1.过车 2.停车
     */
    @TableField(value = "bizType")
    private Integer bizType;

    /**
     * 图片唯一ID
     */
    @TableField(value = "uuid")
    private String uuid;

    /**
     * 图片URL
     */
    @TableField(value = "url")
    private String url;

    /**
     * 图片文件类型
     */
    @TableField(value = "type")
    private String type;

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
