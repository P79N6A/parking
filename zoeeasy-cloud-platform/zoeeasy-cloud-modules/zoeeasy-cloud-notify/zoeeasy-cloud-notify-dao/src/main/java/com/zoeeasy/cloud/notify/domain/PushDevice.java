package com.zoeeasy.cloud.notify.domain;

import com.baomidou.mybatisplus.annotations.*;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.IVersion;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息推送设备表
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("not_push_device")
@SuppressWarnings("serial")
public class PushDevice extends FullAuditedEntity<Long> implements Serializable, IVersion<Long> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户编号
     */
    @TableField(value = "userId")
    private Long userId;

    /**
     * 设备唯一识别码
     */
    @TableField(value = "deviceToken")
    private String deviceToken;

    /**
     * 极光推送的设备唯一标识
     */
    @TableField(value = "registrationId")
    private String registrationId;

    /**
     * 终端类型
     */
    @TableField(value = "terminateType")
    private Integer terminateType;

    /**
     * 渠道编码
     */
    @TableField(value = "channelCode")
    private String channelCode;

    /**
     * imei
     */
    @TableField(value = "imei")
    private String imei;

    /**
     * 品牌
     */
    @TableField(value = "buildBrand")
    private String buildBrand;

    /**
     * 机器型号
     */
    @TableField(value = "buildModel")
    private String buildModel;

    /**
     * 版本号
     */
    @TableField(value = "buildVersionRelease")
    private String buildVersionRelease;

    /**
     * SDK版本
     */
    @TableField(value = "buildVersionSdkInt")
    private Integer buildVersionSdkInt;

    /**
     * 屏幕宽度
     */
    @TableField(value = "screenWidth")
    private Integer screenWidth;

    /**
     * 屏幕高度
     */
    @TableField(value = "screenHeight")
    private Integer screenHeight;

    /**
     * 屏幕的dpi
     */
    @TableField(value = "densityDpi")
    private Integer densityDpi;

    /**
     * 应用包名
     */
    @TableField(value = "packageName")
    private String packageName;

    /**
     * 应用名称
     */
    @TableField(value = "appName")
    private String appName;

    /**
     * 应用版本名称
     */
    @TableField(value = "appVersionName")
    private String appVersionName;

    /**
     * 应用版本号
     */
    @TableField(value = "appVersionCode")
    private Integer appVersionCode;

    /**
     * 使用状态(1-使用中 2-已卸载)
     */
    @TableField(value = "status")
    private Integer status;

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

    /**
     * 版本号
     */
    @Version
    private Long version;

}
