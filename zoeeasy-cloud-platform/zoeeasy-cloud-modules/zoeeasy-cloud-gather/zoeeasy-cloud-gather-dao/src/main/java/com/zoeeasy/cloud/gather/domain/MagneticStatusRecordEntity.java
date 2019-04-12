package com.zoeeasy.cloud.gather.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.auditing.CreationAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 地磁检测器状态变更
 *
 * @Date: 2018/9/25
 * @author: lhj
 */
@TableName("gat_magnetic_status_record")
@Data
@EqualsAndHashCode(callSuper = false)
public class MagneticStatusRecordEntity extends CreationAuditedEntity<Long> implements Serializable {
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
     * 检测器ID
     */
    @TableField(value = "detectorId")
    private Long detectorId;

    /**
     * 地磁管理器类型(厂商) 数据字典
     */
    @TableField(value = "provider")
    private Integer provider;

    /**
     * (厂商)设备序列号
     */
    @TableField(value = "serialNumber")
    private String serialNumber;

    /**
     * 变更时间，地磁检测到车位状态变更上传数据的时间
     */
    @TableField(value = "changeTime")
    private Date changeTime;

    /**
     * 泊位业务变更原因(1-车辆到达, 2-车辆离开)
     */
    @TableField(value = "changeType")
    private Integer changeType;

    /**
     * 信号强度
     */
    @TableField(value = "rssi")
    private Integer rssi;

    /**
     * 车位状态产生时长
     */
    @TableField(value = "passTime")
    private Integer passTime;

    /**
     * 车位状态序号
     */
    @TableField(value = "sequence")
    private Long sequence;

    /**
     * 电量
     */
    @TableField(value = "battery")
    private Integer battery;

    /**
     * 占用状态
     */
    @TableField(value = "occupyStatus")
    private Integer occupyStatus;

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
}
