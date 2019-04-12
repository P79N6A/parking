package com.zoeeasy.cloud.sys.domain;

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
 * 字典数据表(sys_dict_item)表实体类
 *
 * @author AkeemSuper
 * @date 2019-02-20 17:39:00
 */
@TableName("sys_dict_item")
@Data
@EqualsAndHashCode(callSuper = true)
public class DictItemEntity extends FullAuditedEntity<Long> implements Serializable {

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
     * 字典编码
     */
    @TableField("dictCode")
    private String dictCode;

    /**
     * 字典标签
     */
    @TableField("dictLabel")
    private String dictLabel;

    /**
     * 字典键值
     */
    @TableField("dictValue")
    private String dictValue;

    /**
     * 字典排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 样式属性（其他样式扩展）
     */
    @TableField("cssClass")
    private String cssClass;

    /**
     * 表格回显样式
     */
    @TableField("listClass")
    private String listClass;

    /**
     * 是否默认
     */
    @TableField("defaultItem")
    private Boolean defaultItem;

    /**
     * 状态（1正常 0停用)
     */
    @TableField("status")
    private Integer status;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

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