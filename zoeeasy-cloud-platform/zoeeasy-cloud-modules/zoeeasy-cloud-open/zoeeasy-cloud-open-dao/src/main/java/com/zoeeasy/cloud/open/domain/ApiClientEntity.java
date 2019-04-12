package com.zoeeasy.cloud.open.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedTenancyEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * @author walkman
 */
@TableName("open_api_client")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiClientEntity extends FullAuditedTenancyEntity<Long> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户ID
     */
    @TableField(value = "tenantId")
    private Long tenantId;

    /**
     * 客户端ID
     */
    @TableField(value = "accessKey")
    private String accessKey;

    /**
     * 客户端秘钥
     */
    @TableField(value = "accessSecrete")
    private String accessSecrete;

    /**
     * scope
     */
    @TableField(value = "scope")
    private String scope;

    /**
     * 备注
     */
    @TableField(value = "remarks")
    private String remarks;

    /**
     * 有效开始时间
     */
    @TableField(value = "beginTime")
    private Date beginTime;

    /**
     * 有效结束时间
     */
    @TableField(value = "endTime")
    private Date endTime;

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
}
