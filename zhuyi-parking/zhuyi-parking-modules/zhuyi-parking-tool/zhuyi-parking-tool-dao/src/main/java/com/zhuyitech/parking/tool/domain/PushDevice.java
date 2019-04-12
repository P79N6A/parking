package com.zhuyitech.parking.tool.domain;

import com.baomidou.mybatisplus.annotations.*;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.IVersion;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息推送设备表
 *
 * @author walkman
 */
@TableName("up_push_device")
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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public Integer getTerminateType() {
        return terminateType;
    }

    public void setTerminateType(Integer terminateType) {
        this.terminateType = terminateType;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getBuildBrand() {
        return buildBrand;
    }

    public void setBuildBrand(String buildBrand) {
        this.buildBrand = buildBrand;
    }

    public String getBuildModel() {
        return buildModel;
    }

    public void setBuildModel(String buildModel) {
        this.buildModel = buildModel;
    }

    public String getBuildVersionRelease() {
        return buildVersionRelease;
    }

    public void setBuildVersionRelease(String buildVersionRelease) {
        this.buildVersionRelease = buildVersionRelease;
    }

    public Integer getBuildVersionSdkInt() {
        return buildVersionSdkInt;
    }

    public void setBuildVersionSdkInt(Integer buildVersionSdkInt) {
        this.buildVersionSdkInt = buildVersionSdkInt;
    }

    public Integer getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(Integer screenWidth) {
        this.screenWidth = screenWidth;
    }

    public Integer getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(Integer screenHeight) {
        this.screenHeight = screenHeight;
    }

    public Integer getDensityDpi() {
        return densityDpi;
    }

    public void setDensityDpi(Integer densityDpi) {
        this.densityDpi = densityDpi;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersionName() {
        return appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }

    public Integer getAppVersionCode() {
        return appVersionCode;
    }

    public void setAppVersionCode(Integer appVersionCode) {
        this.appVersionCode = appVersionCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public Long getCreatorUserId() {
        return creatorUserId;
    }

    @Override
    public void setCreatorUserId(Long creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    @Override
    public Date getCreationTime() {
        return creationTime;
    }

    @Override
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public Long getLastModifierUserId() {
        return lastModifierUserId;
    }

    @Override
    public void setLastModifierUserId(Long lastModifierUserId) {
        this.lastModifierUserId = lastModifierUserId;
    }

    @Override
    public Date getLastModificationTime() {
        return lastModificationTime;
    }

    @Override
    public void setLastModificationTime(Date lastModificationTime) {
        this.lastModificationTime = lastModificationTime;
    }

    @Override
    public Long getDeleterUserId() {
        return deleterUserId;
    }

    @Override
    public void setDeleterUserId(Long deleterUserId) {
        this.deleterUserId = deleterUserId;
    }

    @Override
    public Date getDeletionTime() {
        return deletionTime;
    }

    @Override
    public void setDeletionTime(Date deletionTime) {
        this.deletionTime = deletionTime;
    }

    @Override
    public Integer getDeleted() {
        return deleted;
    }

    @Override
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public Long getVersion() {
        return version;
    }

    @Override
    public void setVersion(Long version) {
        this.version = version;
    }
}
