package com.zoeeasy.cloud.order.domain;

import com.baomidou.mybatisplus.annotations.*;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.IVersion;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户停车预约
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ord_parking_appointment_order")
public class ParkingAppointmentOrderEntity extends FullAuditedEntity<Long> implements IVersion<Long> {

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
     * 泊位ID
     */
    @TableField(value = "parkingLotId")
    private Long parkingLotId;

    /**
     * 车位编号
     */
    @TableField(value = "parkingLotNumber")
    private String parkingLotNumber;

    /**
     * 停车场收费信息ID
     */
    @TableField(value = "chargeInfoId")
    private Long chargeInfoId;

    /**
     * 停车场预约信息ID
     */
    @TableField(value = "appointInfoId")
    private Long appointInfoId;

    /**
     * 平台平台预约记录流水号
     */
    @TableField(value = "orderNo")
    private String orderNo;

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
     * 预约下单日期
     */
    @TableField(value = "scheduleDate")
    private Date scheduleDate;

    /**
     * 预约预计开始时间
     */
    @TableField(value = "scheduleTime")
    private Date scheduleTime;

    /**
     * 预计停车时长(分钟)
     */
    @TableField(value = "scheduleLength")
    private Integer scheduleLength;

    /**
     * 预约有效截止时间
     */
    @TableField(value = "deadlineTime")
    private Date deadlineTime;

    /**
     * 支付时限(分钟)
     */
    @TableField(value = "payLimit")
    private Integer payLimit;

    /**
     * 支付截止时间
     */
    @TableField(value = "payLimitTime")
    private Date payLimitTime;

    /**
     * 预约状态
     */
    @TableField(value = "appointStatus")
    private Integer appointStatus;

    /**
     * 支付状态
     */
    @TableField(value = "payStatus")
    private Integer payStatus;

    /**
     * 支付方式(根据PayWayEnum)
     */
    @TableField(value = "payWay")
    private Integer payWay;

    /**
     * 支付类型(根据PayTypeEnum)
     */
    @TableField(value = "payType")
    private Integer payType;

    /**
     * 支付时间
     */
    @TableField(value = "payTime")
    private Date payTime;

    /**
     * 预约支付金额(分)
     */
    @TableField(value = "payAmount")
    private Integer payAmount;

    /**
     * 预约实付金额(分)
     */
    @TableField(value = "actualPayAmount")
    private Integer actualPayAmount;

    /**
     * 退还金额(分)
     */
    @TableField(value = "refundAmount")
    private Integer refundAmount;

    /**
     * 是否允许用户手动取消
     */
    @TableField(value = "canCancel")
    private Boolean canCancel;

    /**
     * 是否超时取消
     */
    @TableField(value = "overTimeCancel")
    private Boolean overTimeCancel;

    /**
     * 取消时限
     */
    @TableField(value = "cancelTimeLimit")
    private Date cancelTimeLimit;

    /**
     * 取消时间
     */
    @TableField(value = "cancelTime")
    private Date cancelTime;

    /**
     * 取消费用
     */
    @TableField(value = "cancelFee")
    private Integer cancelFee;

    /**
     * 取消原因
     */
    @TableField(value = "cancelReason")
    private String cancelReason;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 是否入场(0 未入场 1 入场)
     */
    @TableField(value = "entrance")
    private Boolean entrance;

    /**
     * 车主用户ID
     */
    @TableField(value = "customerUserId")
    private Long customerUserId;

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
