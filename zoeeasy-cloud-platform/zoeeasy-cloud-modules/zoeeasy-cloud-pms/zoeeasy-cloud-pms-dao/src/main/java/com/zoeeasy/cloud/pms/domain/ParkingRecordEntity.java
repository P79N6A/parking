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
 * @author AkeemSuper
 */
@TableName("pms_parking_record")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingRecordEntity extends FullAuditedEntity<Long> implements Serializable, IVersion<Long> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户ID
     */
    @TableField(value = "tenantId")
    private Long tenantId;

    /**
     * 停车记录流水号
     */
    @TableField(value = "recordNo")
    private String recordNo;

    /**
     * 第三方平台停车记录ID
     */
    @TableField(value = "thirdParkingRecordId")
    private String thirdParkingRecordId;

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
     * 泊位code
     */
    @TableField(value = "parkingLotNumber")
    private String parkingLotNumber;

    /**
     * 入车记录流水
     */
    @TableField(value = "intoRecordNo")
    private String intoRecordNo;

    /**
     * 入车记录流水
     */
    @TableField(value = "outRecordNo")
    private String outRecordNo;

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
     * 停车类型
     */
    @TableField(value = "parkingType")
    private Integer parkingType;

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
     * 停车时长：分钟
     */
    @TableField(value = "periodLength")
    private Integer periodLength;

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
     * 预约收费规则ID：入车时的预约规则
     */
    @TableField(value = "appointRuleId")
    private Long appointRuleId;

    /**
     * 收费规则ID：入车时的收费规则
     */
    @TableField(value = "chargeId")
    private Long chargeId;

    /**
     * 总金额：单位分
     */
    @TableField(value = "payableAmount")
    private Integer payableAmount;

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

    /**
     * 版本号
     */
    @Version
    private Long version;

}
