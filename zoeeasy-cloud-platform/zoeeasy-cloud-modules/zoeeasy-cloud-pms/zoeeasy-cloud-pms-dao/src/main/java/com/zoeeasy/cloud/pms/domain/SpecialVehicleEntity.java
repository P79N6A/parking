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
 * 特殊车辆实体
 *
 * @date: 2018/10/13.
 * @author：zm
 */
@TableName("pms_special_vehicle")
@Data
@EqualsAndHashCode(callSuper = false)
public class SpecialVehicleEntity extends FullAuditedEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户ID
     */
    @TableField(value = "tenantId")
    private Long tenantId;

    /**
     * 停车场ID
     */
    @TableField(value = "parkingId")
    private Long parkingId;

    /**
     * 泊位ID
     */
    @TableField(value = "parkingLotId")
    private Long parkingLotId;

    /**
     * 泊位编号
     */
    @TableField(value = "parkingLotNumber")
    private String parkingLotNumber;

    /**
     * 特殊车辆类型(1 : 白名单 2 黑名单 3 固定车 4 访客车)
     */
    @TableField(value = "specialType")
    private Integer specialType;

    /**
     * 固定车类型 1 业主车, 2 内部车
     */
    @TableField(value = "fixedType")
    private Integer fixedType;

    /**
     * 访客车类型 1 固定车访客车
     */
    @TableField(value = "visitType")
    private Integer visitType;

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
     * 开始时间
     */
    @TableField(value = "beginTime")
    private Date beginTime;

    /**
     * 结束时间
     */
    @TableField(value = "endTime")
    private Date endTime;

    /**
     * 生效状态(1 : 未生效 2 已生效 3 已失效)
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

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
}
