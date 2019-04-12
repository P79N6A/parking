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
 * @author AkeemSuper
 */
@TableName("pms_parking_vehicle_record")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingVehicleRecordEntity extends CreationAuditedEntity<Long> implements Serializable {

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
     * 停车场ID
     */
    @TableField(value = "parkingId")
    private Long parkingId;

    /**
     * 停车场名称
     */
    @TableField(value = "parkingName")
    private String parkingName;

    /**
     * 停车场编号
     */
    @TableField(value = "parkingCode")
    private String parkingCode;

    /**
     * 泊位ID
     */
    @TableField(value = "parkingLotId")
    private Long parkingLotId;

    /**
     * 泊位code
     */
    @TableField(value = "parkingLotCode")
    private String parkingLotCode;

    /**
     * 泊位Number
     */
    @TableField(value = "parkingLotNumber")
    private String parkingLotNumber;

    /**
     * 入车记录流水号
     */
    @TableField(value = "intoRecordNo")
    private String intoRecordNo;

    /**
     * 停车卡号
     */
    @TableField(value = "cardNumber")
    private String cardNumber;

    /**
     * 停车码
     */
    @TableField(value = "codeNumber")
    private String codeNumber;

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
    @TableField(value = "carType")
    private Integer carType;

    /**
     * 停车开始时间
     */
    @TableField(value = "startTime")
    private Date startTime;

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

}
