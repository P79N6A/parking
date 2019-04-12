package com.zoeeasy.cloud.pms.domain;

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
 * 平台车辆信息
 *
 * @date: 2018/10/12.
 * @author：zm
 */
@TableName("pms_vehicle_record")
@Data
@EqualsAndHashCode(callSuper = false)
public class VehicleRecordEntity extends FullAuditedEntity<Long> implements Serializable, IVersion<Long> {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户ID
     */
    @TableField(value = "tenantId")
    private Long tenantId;

    /**
     * 车辆品牌
     */
    @TableField(value = "carBrand")
    private String carBrand;

    /**
     * 车辆类型
     */
    @TableField(value = "carStyle")
    private String carStyle;

    /**
     * 车牌编号
     */
    @TableField(value = "plateNumber")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @TableField(value = "plateColor")
    private Integer plateColor;

    /**
     * 车牌类型
     */
    @TableField(value = "plateType")
    private String plateType;

    /**
     * 车辆类型
     */
    @TableField(value = "carType")
    private Integer carType;

    /**
     * 车辆颜色
     */
    @TableField(value = "carColor")
    private Integer carColor;

    /**
     * 车架号
     */
    @TableField(value = "vehicleNumber")
    private String vehicleNumber;

    /**
     * 发动机号
     */
    @TableField(value = "engineNumber")
    private String engineNumber;

    /**
     * 车主姓名
     */
    @TableField(value = "ownerName")
    private String ownerName;

    /**
     * 车主手机
     */
    @TableField(value = "ownerPhone")
    private String ownerPhone;

    /**
     * 车主地址
     */
    @TableField(value = "ownerAddress")
    private String ownerAddress;

    /**
     * 车主身份证号
     */
    @TableField(value = "ownerCardNo")
    private String ownerCardNo;

    /**
     * 创建者
     */
    @TableField(value = "creatorUserId", fill = FieldFill.INSERT)
    private Long creatorUserId;

    /**
     * 创建时间
     */
    @TableField(value = "creationTime", fill = FieldFill.INSERT)
    private Date creationTime;

    /**
     * 更新者
     */
    @TableField(value = "lastModifierUserId", fill = FieldFill.INSERT_UPDATE)
    private Long lastModifierUserId;

    /**
     * 更新时间
     */
    @TableField(value = "lastModificationTime", fill = FieldFill.INSERT_UPDATE)
    private Date lastModificationTime;

    /**
     * 删除者
     */
    @TableField(value = "deleterUserId", fill = FieldFill.UPDATE)
    private Long deleterUserId;

    /**
     * 删除时间
     */
    @TableField(value = "deletionTime", fill = FieldFill.UPDATE)
    private Date deletionTime;

    /**
     * 删除标记
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
