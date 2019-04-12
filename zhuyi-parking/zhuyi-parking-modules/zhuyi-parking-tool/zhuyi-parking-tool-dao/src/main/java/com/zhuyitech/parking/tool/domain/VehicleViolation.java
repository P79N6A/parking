package com.zhuyitech.parking.tool.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Date: 2018/4/12
 * @author: yuzhicheng
 */
@TableName("up_vehicle_violation")
public class VehicleViolation extends FullAuditedEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "carId")
    private Long carId;

    /**
     * 违章编码,唯一，非违章条例码
     */
    @TableField(value = "code")
    private String code;

    /**
     * 违章时间
     */
    @TableField(value = "violationTime")
    private Date violationTime;

    /**
     * 罚款金额
     */
    @TableField(value = "fine")
    private BigDecimal fine;

    /**
     * 违章地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 违章处理原因
     */
    @TableField(value = "reason")
    private String reason;

    /**
     * 违章扣分
     */
    @TableField(value = "point")
    private Integer point;

    /**
     * 违章发生城市，可能为空
     */
    @TableField(value = "violationCity")
    private String violationCity;

    /**
     * 省
     */
    @TableField(value = "province")
    private String province;

    /**
     * 城市
     */
    @TableField(value = "city")
    private String city;

    /**
     * 服务费（与代缴有关，用户可忽略本字段）
     */
    @TableField(value = "serviceFee")
    private BigDecimal serviceFee;

    /**
     * 代扣分费用（与代缴有关，用户可忽略本字段）
     */
    @TableField(value = "markFee")
    private BigDecimal markFee;

    /**
     * 能否勾选办理：0不可勾选, 1可勾选。（与代缴有关，用户可忽略本字段）
     */
    @TableField(value = "canSelect")
    private Integer canSelect;

    /**
     * 违章处理状态：1：未处理，2：处理中，3：已处理，4：不支持（与代缴有关，用户可忽略本字段）
     */
    @TableField(value = "processStatus")
    private Integer processStatus;

    /**
     * 违章官方编码
     */
    @TableField(value = "violationNum")
    private String violationNum;

    /**
     * 执法机构
     */
    @TableField(value = "organName")
    private String organName;

    /**
     * 违章缴费状态 不返回表示无法获取该信息，1-未缴费 2-已缴
     */
    @TableField(value = "paymentStatus")
    private Integer paymentStatus;

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

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public Date getViolationTime() {
        return violationTime;
    }

    public void setViolationTime(Date violationTime) {
        this.violationTime = violationTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getFine() {
        return fine;
    }

    public void setFine(BigDecimal fine) {
        this.fine = fine;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getViolationCity() {
        return violationCity;
    }

    public void setViolationCity(String violationCity) {
        this.violationCity = violationCity;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public BigDecimal getMarkFee() {
        return markFee;
    }

    public void setMarkFee(BigDecimal markFee) {
        this.markFee = markFee;
    }

    public Integer getCanSelect() {
        return canSelect;
    }

    public void setCanSelect(Integer canSelect) {
        this.canSelect = canSelect;
    }

    public Integer getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Integer processStatus) {
        this.processStatus = processStatus;
    }

    public String getViolationNum() {
        return violationNum;
    }

    public void setViolationNum(String violationNum) {
        this.violationNum = violationNum;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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


}
