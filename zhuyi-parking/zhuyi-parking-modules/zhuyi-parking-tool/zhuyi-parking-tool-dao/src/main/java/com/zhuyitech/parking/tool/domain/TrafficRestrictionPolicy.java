package com.zhuyitech.parking.tool.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;
import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/5/21 0021
 */
@TableName("up_traffic_restriction_policy")
public class TrafficRestrictionPolicy extends FullAuditedEntity<Long> {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 限行城市Id
     */
    @TableField(value = "cityId")
    private Long cityId;

    /**
     * carType
     */
    @TableField(value = "carType")
    private Integer carType;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 限行时间
     */
    @TableField(value = "restrictionTime")
    private String restrictionTime;

    /**
     * 限行区域
     */
    @TableField(value = "restrictionArea")
    private String restrictionArea;

    /**
     * 限行规则
     */
    @TableField(value = "restrictionRule")
    private String restrictionRule;

    /**
     * 限行坐标
     */
    @TableField(value = "restrictionDetail")
    private String restrictionDetail;

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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Integer getCarType() {
        return carType;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public String getRestrictionTime() {
        return restrictionTime;
    }

    public void setRestrictionTime(String restrictionTime) {
        this.restrictionTime = restrictionTime;
    }

    public String getRestrictionArea() {
        return restrictionArea;
    }

    public void setRestrictionArea(String restrictionArea) {
        this.restrictionArea = restrictionArea;
    }

    public String getRestrictionRule() {
        return restrictionRule;
    }

    public void setRestrictionRule(String restrictionRule) {
        this.restrictionRule = restrictionRule;
    }

    public String getRestrictionDetail() {
        return restrictionDetail;
    }

    public void setRestrictionDetail(String restrictionDetail) {
        this.restrictionDetail = restrictionDetail;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
