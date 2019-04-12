package com.zoeeasy.cloud.tool.domain;

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
 * 区域实体对象
 *
 * @author walkman
 */
@TableName("region")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class RegionEntity extends FullAuditedEntity<Long> implements Serializable {

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 地区代码
     */
    @TableField("code")
    private String code;

    /**
     * 地区名称
     */
    @TableField("name")
    private String name;

    /**
     * 全称
     */
    @TableField("fullName")
    private String fullName;

    /**
     * 拼音名称
     */
    @TableField("pinyinName")
    private String pinyinName;

    /**
     * 英语名称
     */
    @TableField("nameEn")
    private String nameEn;

    /**
     * 英语简称
     */
    @TableField("shortNameEn")
    private String shortNameEn;

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
     * 地区类型-标记特殊的地区
     */
    @TableField("type")
    private Integer type;

    /**
     * 邮编
     */
    @TableField("zipCode")
    private String zipCode;

    /**
     * 区号
     */
    @TableField("areaCode")
    private String areaCode;

    /**
     * 上级Code
     */
    @TableField("parentCode")
    private String parentCode;

    /**
     * 状态
     */
    @TableField("status")
    private String status;

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
