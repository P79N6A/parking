package com.zhuyitech.parking.ucc.domain;

import com.baomidou.mybatisplus.annotations.*;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.entities.IVersion;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户车辆认证实体对象
 *
 * @Date: 2018/1/4 0004
 * @author: AkeemSuper
 */
@TableName("up_user_car_info")
public class UserCarInfo extends FullAuditedEntity<Long> implements IVersion<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField("userId")
    private Long userId;

    /**
     * 车辆品牌
     */
    @TableField("carBrand")
    private String carBrand;

    /**
     * 车辆型号
     */
    @TableField("carType")
    private String carType;

    /**
     * 车辆颜色
     */
    @TableField("carColor")
    private Integer carColor;

    /**
     * 车辆等级
     */
    @TableField("carLevel")
    private Integer carLevel;

    /**
     * 车牌类型
     */
    @TableField("plateType")
    private String plateType;

    /**
     * 车牌颜色
     */
    @TableField("plateColor")
    private Integer plateColor;

    /**
     * 车牌第一个中文
     */
    @TableField("platePrefix")
    private String platePrefix;

    /**
     * 车牌首字母
     */
    @TableField("plateInitial")
    private String plateInitial;

    /**
     * 车牌号尾部
     */
    @TableField("plateNumber")
    private String plateNumber;

    /**
     * 车牌最后一位数字
     */
    @TableField("plateLastNumber")
    private String plateLastNumber;

    /**
     * 车牌号
     */
    @TableField(exist = false)
    private String fullPlateNumber;

    /**
     * 车架号
     */
    @TableField("vehicleNumber")
    private String vehicleNumber;

    /**
     * 发动机号
     */
    @TableField("engineNumber")
    private String engineNumber;

    /**
     * 车辆图片URL
     */
    @TableField("carImageUrl")
    private String carImageUrl;

    /**
     * 行驶证正面图片
     */
    @TableField("licensePhotoFront")
    private String licensePhotoFront;

    /**
     * 行驶证反面图片
     */
    @TableField("licensePhotoContrary")
    private String licensePhotoContrary;

    /**
     * 注册日期
     */
    @TableField(value = "registerDate")
    private Date registerDate;

    /**
     * 是否默认车辆
     */
    @TableField(value = "defaultCar")
    private Boolean defaultCar;

    /**
     * 处理状态 1认证中 2已认证 3 认证不通过
     */
    @TableField("status")
    private Integer status;

    /**
     * 是否提醒限行
     */
    @TableField("hintTrafficLimit")
    private Boolean hintTrafficLimit;

    /**
     * 是否提醒违章
     */
    @TableField("hintViolation")
    private Boolean hintViolation;

    /**
     * 是否提醒年检
     */
    @TableField("hintYearCheck")
    private Boolean hintYearCheck;

    /**
     * 说明
     */
    @TableField("remark")
    private String remark;

    /**
     * 申请时间
     */
    @TableField(value = "applyTime")
    private Date applyTime;

    /**
     * 审核用户ID
     */
    @TableField(value = "authUserId")
    private Long authUserId;

    /**
     * 审核时间
     */
    @TableField(value = "authTime")
    private Date authTime;

    /**
     * 未处理违章总罚款
     */
    @TableField("totalFine")
    private BigDecimal totalFine;

    /**
     * 未处理违章总扣分
     */
    @TableField("totalPoints")
    private Integer totalPoints;

    /**
     * 未处理违章条数
     */
    @TableField("untreatedCount")
    private Integer untreatedCount;

    /**
     * 违章总条数
     */
    @TableField("totalCount")
    private Integer totalCount;

    /**
     * 最后一次违章同步时间
     */
    @TableField("violationUpdateTime")
    private Date violationUpdateTime;

    /**
     * 一段时间内违章同步次数
     */
    @TableField("violationUpdateCount")
    private Integer violationUpdateCount;

    /**
     * 创建者
     */
    @TableField("creatorUserId")
    private Long creatorUserId;

    /**
     * 创建时间
     */
    @TableField("creationTime")
    private Date creationTime;

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
    @TableField("deleterUserId")
    private Long deleterUserId;

    /**
     * 删除时间
     */
    @TableField("deletionTime")
    private Date deletionTime;

    /**
     * 删除标记
     */
    @TableField("deleted")
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

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public Integer getCarColor() {
        return carColor;
    }

    public void setCarColor(Integer carColor) {
        this.carColor = carColor;
    }

    public Integer getCarLevel() {
        return carLevel;
    }

    public void setCarLevel(Integer carLevel) {
        this.carLevel = carLevel;
    }

    public String getPlateType() {
        return plateType;
    }

    public void setPlateType(String plateType) {
        this.plateType = plateType;
    }

    public Integer getPlateColor() {
        return plateColor;
    }

    public void setPlateColor(Integer plateColor) {
        this.plateColor = plateColor;
    }

    public String getPlatePrefix() {
        return platePrefix;
    }

    public void setPlatePrefix(String platePrefix) {
        this.platePrefix = platePrefix;
    }

    public String getPlateInitial() {
        return plateInitial;
    }

    public void setPlateInitial(String plateInitial) {
        this.plateInitial = plateInitial;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getPlateLastNumber() {
        return plateLastNumber;
    }

    public void setPlateLastNumber(String plateLastNumber) {
        this.plateLastNumber = plateLastNumber;
    }

    /**
     * 获取完整车牌号
     */
    public String getFullPlateNumber() {

        String plateNumberFull = (StringUtils.isEmpty(platePrefix) ? "" : platePrefix) +
                (StringUtils.isEmpty(plateInitial) ? "" : plateInitial) +
                (StringUtils.isEmpty(plateNumber) ? "" : plateNumber);
        return plateNumberFull;
    }

    public void setFullPlateNumber(String fullPlateNumber) {
        this.fullPlateNumber = fullPlateNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getCarImageUrl() {
        return carImageUrl;
    }

    public void setCarImageUrl(String carImageUrl) {
        this.carImageUrl = carImageUrl;
    }

    public String getLicensePhotoFront() {
        return licensePhotoFront;
    }

    public void setLicensePhotoFront(String licensePhotoFront) {
        this.licensePhotoFront = licensePhotoFront;
    }

    public String getLicensePhotoContrary() {
        return licensePhotoContrary;
    }

    public void setLicensePhotoContrary(String licensePhotoContrary) {
        this.licensePhotoContrary = licensePhotoContrary;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Boolean getDefaultCar() {
        return defaultCar;
    }

    public void setDefaultCar(Boolean defaultCar) {
        this.defaultCar = defaultCar;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getHintTrafficLimit() {
        return hintTrafficLimit;
    }

    public void setHintTrafficLimit(Boolean hintTrafficLimit) {
        this.hintTrafficLimit = hintTrafficLimit;
    }

    public Boolean getHintViolation() {
        return hintViolation;
    }

    public void setHintViolation(Boolean hintViolation) {
        this.hintViolation = hintViolation;
    }

    public Boolean getHintYearCheck() {
        return hintYearCheck;
    }

    public void setHintYearCheck(Boolean hintYearCheck) {
        this.hintYearCheck = hintYearCheck;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Long getAuthUserId() {
        return authUserId;
    }

    public void setAuthUserId(Long authUserId) {
        this.authUserId = authUserId;
    }

    public Date getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
    }

    public BigDecimal getTotalFine() {
        return totalFine;
    }

    public void setTotalFine(BigDecimal totalFine) {
        this.totalFine = totalFine;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Integer getUntreatedCount() {
        return untreatedCount;
    }

    public void setUntreatedCount(Integer untreatedCount) {
        this.untreatedCount = untreatedCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Date getViolationUpdateTime() {
        return violationUpdateTime;
    }

    public void setViolationUpdateTime(Date violationUpdateTime) {
        this.violationUpdateTime = violationUpdateTime;
    }

    public Integer getViolationUpdateCount() {
        return violationUpdateCount;
    }

    public void setViolationUpdateCount(Integer violationUpdateCount) {
        this.violationUpdateCount = violationUpdateCount;
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

