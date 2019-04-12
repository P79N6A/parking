package com.zoeeasy.cloud.ucc.domain;

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
@TableName("ucc_menu")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuEntity extends FullAuditedTenancyEntity<Long> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户ID
     */
    @TableField(value = "tenantId")
    private Long tenantId;

    /**
     * 菜单代码
     */
    @TableField(value = "code")
    private String code;

    /**
     * 菜单名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 资源类型
     */
    @TableField(value = "type")
    private String type;

    /**
     * 点击后前往的地址
     */
    @TableField(value = "url")
    private String url;

    /**
     * 组件名称
     */
    @TableField(value = "component")
    private String component;

    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * level
     */
    @TableField(value = "level")
    private String level;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 父ID
     */
    @TableField(value = "parentId")
    private Long parentId;

    /**
     * 父路径
     */
    @TableField(value = "pathCode")
    private String pathCode;

    /**
     * 是否静态菜单
     */
    @TableField(value = "staticMenu")
    private Boolean staticMenu;

    /**
     * 是否显示
     */
    @TableField(value = "shown")
    private Boolean shown;

    /**
     * 权限字符串
     */
    @TableField(value = "permission")
    private String permission;

    /**
     * 状态
     */
    @TableField(value = "status")
    private Integer status;

    /**
     *
     */
    @TableField(value = "tenancyHostSide")
    private Integer tenancyHostSide;

    /**
     * 备注
     */
    @TableField(value = "remarks")
    private String remarks;

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
