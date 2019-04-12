package com.zhuyitech.parking.data.domain;

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
 * 平台停车记录
 *
 * @author walkman
 */
@TableName("up_parking_record")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
public class ParkingRecord extends FullAuditedEntity<Long> implements Serializable, IVersion<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 停车记录流水号
     */
    @TableField(value = "recordNo")
    private String recordNo;

    /**
     * 海康平台停车记录ID
     */
    @TableField(value = "hikParkingRecordId")
    private String hikParkingRecordId;

    /**
     * 阿里平台停车记录ID
     */
    @TableField(value = "aliParkingRecordId")
    private String aliParkingRecordId;

    /**
     * 停车场ID
     */
    @TableField(value = "parkingId")
    private Long parkingId;

    /**
     * 海康平台停车场ID
     */
    @TableField(value = "hikParkingId")
    private String hikParkingId;

    /**
     * 支付宝平台停车场ID
     */
    @TableField(value = "aliParkingId")
    private Long aliParkingId;

    /**
     * 泊位ID
     */
    @TableField(value = "parkingLotId")
    private Long parkingLotId;

    /**
     * 海康平台泊位ID
     */
    @TableField(value = "hikParkingLotId")
    private String hikParkingLotId;

    /**
     * 支付宝平台泊位ID
     */
    @TableField(value = "aliParkingLotId")
    private String aliParkingLotId;

    /**
     * 入车记录ID
     */
    @TableField(value = "intoRecordId")
    private Long intoRecordId;

    /**
     * 出车记录ID
     */
    @TableField(value = "outRecordId")
    private Long outRecordId;

    /**
     * 车牌号
     */
    @TableField(value = "plateNumber")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @TableField(value = "plateColor")
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @TableField(value = "carStyle")
    private Integer carStyle;

    /**
     * 停车开始时间
     */
    @TableField(value = "startTime")
    private Date startTime;

    /**
     * 停车结束时间
     */
    @TableField(value = "endTime")
    private Date endTime;

    /**
     * 状态
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 是否预约停车
     */
    @TableField(value = "appointed")
    private Boolean appointed;

    /**
     * 预约订单号
     */
    @TableField(value = "appointOrderNo")
    private String appointOrderNo;

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

}
