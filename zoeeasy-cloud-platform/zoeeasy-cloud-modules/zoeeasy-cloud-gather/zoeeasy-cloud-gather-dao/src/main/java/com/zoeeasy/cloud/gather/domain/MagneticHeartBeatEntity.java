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
 * 地磁检测器心跳基础数据
 *
 * @Date: 2018/9/20
 * @author: lhj
 */
@TableName("gat_magnetic_heart_beat")
@Data
@EqualsAndHashCode(callSuper = false)
public class MagneticHeartBeatEntity extends CreationAuditedEntity<Long> implements Serializable {

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
     * 指令编码
     */
    @TableField(value = "commandCode")
    private String commandCode;

    /**
     * 心跳时间
     */
    @TableField(value = "beatTime")
    private Date beatTime;

    /**
     * 设备状态异常码
     */
    @TableField(value = "errorCode")
    private Integer errorCode;

    /**
     * 电量  默认单位是%
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
