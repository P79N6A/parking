package com.zhuyitech.parking.data.domain;

import com.baomidou.mybatisplus.annotations.*;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.IVersion;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 平台账单记录
 *
 * @author walkman
 */
@TableName("up_parking_order")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
public class ParkingOrder extends FullAuditedEntity<Long> implements Serializable, IVersion<Long> {

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
     * 平台停车账单流水号
     */
    @TableField(value = "orderNo")
    private String orderNo;

    /**
     * 海康平台账单编号
     */
    @TableField(value = "hikBillNo")
    private String hikBillNo;

    /**
     * 平台停车场ID
     */
    @TableField(value = "parkingId")
    private Long parkingId;

    /**
     * 停车场名称
     */
    @TableField(value = "parkingName")
    private String parkingName;

    /**
     * 平台泊位ID
     */
    @TableField(value = "parkingLotId")
    private Long parkingLotId;

    /**
     * 车位编号
     */
    @TableField(value = "parkingLotCode")
    private String parkingLotCode;

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
     * 是否手工单
     */
    @TableField(value = "artificial")
    private Boolean artificial;

    /**
     * 停车时长(秒)
     */
    @TableField(value = "parkingLength")
    private Long parkingLength;

    /**
     * 免费停车时长(秒)
     */
    @TableField(value = "freeLength")
    private Long freeLength;

    /**
     * 免费停车时长(秒)
     */
    @TableField(value = "chargeLength")
    private Long chargeLength;

    /**
     * 收费详情
     */
    @TableField(value = "chargeDescription")
    private String chargeDescription;

    /**
     * 收费模式 1:离场前收费 2: 离场后收费
     */
    @TableField(value = "chargeMode")
    private Integer chargeMode;

    /**
     * 停车状态
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 海康平台账单同步状态(0 未创建 1 已创建 2 已支付确认)
     */
    @TableField(value = "hikSyncStatus")
    private Integer hikSyncStatus;

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
     * 是否已结算 0 未结算 1 已结算
     */
    @TableField(value = "settle")
    private Boolean settle;

    /**
     * 结算订单号
     */
    @TableField(value = "settleOrderNo")
    private String settleOrderNo;

    /**
     * 结算时间
     */
    @TableField(value = "settleTime")
    private Date settleTime;

    /**
     * 结算金额(元)
     */
    @TableField(value = "settleAmount")
    private BigDecimal settleAmount;

    /**
     * 是否限免停车
     */
    @TableField(value = "limitFree")
    private Boolean limitFree;

    /**
     * 是否可以支付0 不可支付 1 可以支付
     */
    @TableField(value = "payable")
    private Boolean payable;

    /**
     * 是否需要支付0 无需支付 1 需要支付
     */
    @TableField(value = "needPay")
    private Boolean needPay;

    /**
     * 免于支付说明
     */
    @TableField(value = "freePayReason")
    private String freePayReason;

    /**
     * 应付金额(元)
     */
    @TableField(value = "payableAmount")
    private BigDecimal payableAmount;

    /**
     * 实付金额(元)
     */
    @TableField(value = "actualPayAmount")
    private BigDecimal actualPayAmount;

    /**
     * 支付状态 0 :未支付 1:已支付  2:支付中
     */
    @TableField(value = "payStatus")
    private Integer payStatus;

    /**
     * 支付时间
     */
    @TableField(value = "payTime")
    private Date payTime;

    /**
     * 支付用户ID
     */
    @TableField(value = "payedUserId")
    private Long payedUserId;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 编辑人员
     */
    @TableField(value = "editor")
    private String editor;
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
