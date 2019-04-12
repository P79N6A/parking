package com.zoeeasy.cloud.pms.domain;

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
 * 停车场泊位设备配置信息
 * Created by song on 2018/9/14.
 */
@TableName("pms_parking_lot_config")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingLotConfigEntity extends CreationAuditedEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户ID
     */
    @TableField("tenantId")
    private Long tenantId;

    /**
     * 停车场ID
     */
    @TableField("parkingId")
    private Long parkingId;

    /**
     * 泊位ID
     */
    @TableField("parkingLotId")
    private Long parkingLotId;

    /**
     * 枪球ID
     */
    @TableField("cameraId")
    private Long cameraId;

    /**
     * 枪设备编号
     */
    @TableField("cameraNo")
    private String cameraNo;

    /**
     * 枪设备名称
     */
    @TableField("cameraName")
    private String cameraName;

    /**
     * 地磁检测器ID
     */
    @TableField("detectorId")
    private Long detectorId;

    /**
     * 对接网关ID
     */
    @TableField("dockingId")
    private Long dockingId;

    /**
     * 车位锁ID
     */
    @TableField("lockId")
    private Long lockId;

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

}
