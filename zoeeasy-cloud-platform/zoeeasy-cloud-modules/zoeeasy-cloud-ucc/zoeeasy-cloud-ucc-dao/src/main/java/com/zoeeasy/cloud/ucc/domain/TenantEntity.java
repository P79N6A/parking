package com.zoeeasy.cloud.ucc.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 租户表
 *
 * @author walkman
 * @since 2018-08-28
 */
@TableName("ucc_tenant")
@Data
@EqualsAndHashCode(callSuper = true)
public class TenantEntity extends FullAuditedEntity<Long> {

    /**
     * 租户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户编码
     */
    @TableField("code")
    private String code;

    /**
     * 租户名称
     */
    @TableField("name")
    private String name;

    /**
     * 商户类型：1 企业； 2 其他组织
     */
    @TableField("type")
    private Integer type;

    /**
     * 运营商状态：0 新增；1 待审核；2 审核不通过；3 上线；4 下线；5 已删除
     */
    @TableField("status")
    private Integer status;

    /**
     * 域名
     */
    @TableField("domain")
    private String domain;

    /**
     * logo图片地址
     */
    @TableField("logo")
    private String logo;

    /**
     * 网址
     */
    @TableField("url")
    private String url;

    /**
     * 简介
     */
    @TableField("description")
    private String description;

    /**
     * 超级管理员用户ID
     */
    @TableField("adminUserId")
    private Long adminUserId;

    /**
     * 租户生效时间
     */
    @TableField("beginTime")
    private Date beginTime;

    /**
     * 租户到期时间
     */
    @TableField("endTime")
    private Date endTime;

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
    @TableField(value = "deleted", fill = FieldFill.INSERT_UPDATE)
    @TableLogic
    protected Integer deleted;

}
