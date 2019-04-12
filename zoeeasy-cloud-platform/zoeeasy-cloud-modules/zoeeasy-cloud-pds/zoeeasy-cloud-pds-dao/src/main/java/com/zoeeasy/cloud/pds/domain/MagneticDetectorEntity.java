package com.zoeeasy.cloud.pds.domain;

import com.baomidou.mybatisplus.annotations.*;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.auditing.CreationAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 地磁检测器基础数据
 *
 * @author lhj
 */
@TableName("pds_magnetic_detector")
@Data
@EqualsAndHashCode(callSuper = false)
public class MagneticDetectorEntity extends CreationAuditedEntity<Long> implements Serializable {

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
     * 所属的区域ID
     */
    @TableField(value = "parkingAreaId")
    private Long parkingAreaId;

    /**
     * 泊位ID
     */
    @TableField(value = "parkingLotId")
    private Long parkingLotId;

    /**
     * 关联地磁管理器id
     */
    @TableField(value = "managerId")
    private Long managerId;

    /**
     * 地磁检测器编号(平台唯一)
     */
    @TableField(value = "code")
    private String code;

    /**
     * 地磁管理器类型(厂商) 数据字典
     */
    @TableField(value = "provider")
    private Integer provider;

    /**
     * ip地址
     */
    @TableField(value = "ipAddress")
    private String ipAddress;

    /**
     * 端口号
     */
    @TableField(value = "port")
    private Integer port;

    /**
     * SIM卡号
     */
    @TableField(value = "simNo")
    private String simNo;

    /**
     * 是否已注册 0-未注册，1-已注册
     */
    @TableField(value = "registered")
    private Boolean registered;

    /**
     * 最后心跳时间
     */
    @TableField(value = "lastHeartbeatTime")
    private Date lastHeartbeatTime;

    /**
     * 心跳间隔时间(秒)
     */
    @TableField(value = "heartBeatInterval")
    private Integer heartBeatInterval;

    /**
     * 安装位置
     */
    @TableField(value = "installPosition")
    private String installPosition;

    /**
     * 经度
     */
    @TableField(value = "longitude")
    private Double longitude;

    /**
     * 纬度
     */
    @TableField(value = "latitude")
    private Double latitude;

    /**
     * 设备版本号
     */
    @TableField(value = "versionNumber")
    private String versionNumber;

    /**
     * (厂商)设备序列号
     */
    @TableField(value = "serialNumber")
    private String serialNumber;

    /**
     * 电量
     */
    @TableField(value = "electricity")
    private Integer electricity;

    /**
     * 地磁检测器当前状态，-1：未知，0：正常，4：失联
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 安装时间
     */
    @TableField(value = "installationTime")
    private Date installationTime;

    /**
     * 注册时间
     */
    @TableField(value = "registerTime")
    private Date registerTime;

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
