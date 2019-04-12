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
 * 自定义区域
 * Created by song on 2018/9/14.
 */
@TableName("pms_area")
@Data
@EqualsAndHashCode(callSuper = false)
public class AreaEntity extends FullAuditedEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户Id
     */
    @TableField("tenantId")
    private Long tenantId;

    /**
     * 地区代码
     */
    @TableField("code")
    private String code;

    /**
     * 客户端编号
     */
    @TableField("localCode")
    private String localCode;

    /**
     * 地区名称
     */
    @TableField("name")
    private String name;

    /**
     * 节点路径
     */
    @TableField(value = "pathCode")
    private String pathCode;

    /**
     * 上级ID
     */
    @TableField("parentId")
    private Long parentId;

    /**
     * 层级
     */
    @TableField("level")
    private Integer level;

    /**
     * 排序
     */
    @TableField("order")
    private Integer order;

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
