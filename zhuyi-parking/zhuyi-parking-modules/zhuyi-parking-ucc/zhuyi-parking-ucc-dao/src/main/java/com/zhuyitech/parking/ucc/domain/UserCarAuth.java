package com.zhuyitech.parking.ucc.domain;

import com.baomidou.mybatisplus.annotations.*;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.IVersion;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户车辆认证表实体类
 *
 * @author AkeemSuper
 * @date 2018/4/19 0019
 */
@TableName("up_user_car_auth")
public class UserCarAuth extends FullAuditedEntity<Long> implements IVersion<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 车辆id
     */
    @TableField("carId")
    private Long carId;

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
     * 车牌号
     */
    @TableField("plateNumber")
    private String plateNumber;

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
     * 处理状态 1认证中 2已认证 3 认证不通过
     */
    @TableField("status")
    private Integer status;

    /**
     * 备注
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

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
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

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getFullPlateNumber() {
        return plateNumber;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
