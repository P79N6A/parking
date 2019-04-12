package com.zoeeasy.cloud.order.domain;

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
 * 订单
 *
 * @author AkeemSuper
 * @since 2018/10/7 0007
 */
@TableName("ord_parking_order")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingOrderEntity extends FullAuditedEntity<Long> implements Serializable, IVersion<Long> {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * tenantId
     */
    @TableField(value = "tenantId")
    private Long tenantId;

    /**
     * 平台停车记录编号
     */
    @TableField(value = "recordNo")
    private String recordNo;

    /**
     * 平台停车账单流水号
     */
    @TableField(value = "orderNo")
    private String orderNo;

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
     * 泊位号
     */
    @TableField(value = "parkingLotNumber")
    private String parkingLotNumber;

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
     * 停车时长
     */
    @TableField(value = "parkingLength")
    private Long parkingLength;

    /**
     * 免费停车时长
     */
    @TableField(value = "freeLength")
    private Long freeLength;

    /**
     * 收费停车时长
     */
    @TableField(value = "chargeLength")
    private Long chargeLength;

    /**
     * 停车场收费信息ID
     */
    @TableField(value = "chargeInfoId")
    private Long chargeInfoId;

    /**
     * 收费模式 1:离场前收费 2: 离场后收费
     */
    @TableField(value = "chargeMode")
    private Integer chargeMode;

    /**
     * 收费类型 1:用户线上支付 2: 巡检POS收费 3:巡检现金收费 ４: 岗亭收费 5:岗亭现金收费
     */
    @TableField(value = "chargeType")
    private Integer chargeType;

    /**
     * 停车状态
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 停车订单类型　1　正常订单,2　切换车位订单，3　车位被占订单；4　点灯举报订单；5　物业举报订单　6补缴订单； 7 追缴订单　8:临时停车订单
     */
    @TableField(value = "orderType")
    private Integer orderType;

    /**
     * 停车订单来源
     */
    @TableField(value = "orderSource")
    private Integer orderSource;

    /**
     * 父订单号
     */
    @TableField(value = "parentOrderNo")
    private String parentOrderNo;

    /**
     * 是否子订单
     */
    @TableField(value = "childOrder")
    private Boolean childOrder;

    /**
     * 是否允许预缴
     */
    @TableField(value = "prepayable")
    private Boolean prepayable;

    /**
     * 第三方平台账单编号
     */
    @TableField(value = "thirdBillNo")
    private String thirdBillNo;

    /**
     * 第三方平台账单数据源
     */
    @TableField(value = "thirdBillSourceType")
    private Integer thirdBillSourceType;

    /**
     * 第三方平台账单同步状态(0 未创建 1 已创建 2 已支付确认)
     */
    @TableField(value = "thirdBillSyncStatus")
    private Integer thirdBillSyncStatus;

    /**
     * 是否预约停车 0 否 1 是
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
     * 结算金额
     */
    @TableField(value = "settleAmount")
    private Integer settleAmount;

    /**
     * 结算时间
     */
    @TableField(value = "settleTime")
    private Date settleTime;

    /**
     * 是否限免停车
     */
    @TableField(value = "limitFree ")
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
     * 应付金额
     */
    @TableField(value = "payableAmount")
    private Integer payableAmount;

    /**
     * 免于支付说明
     */
    @TableField(value = "freePayReason")
    private String freePayReason;

    /**
     * 支付状态 0 :未支付 1:已支付  2:支付中
     */
    @TableField(value = "payStatus")
    private Integer payStatus;

    /**
     * 实付金额
     */
    @TableField(value = "actualPayAmount")
    private Integer actualPayAmount;

    /**
     * 预付金额
     */
    @TableField(value = "prepaidAmount")
    private Integer prepaidAmount;

    /**
     * 剩余金额
     */
    @TableField(value = "surplusAmount")
    private Integer surplusAmount;

    /**
     * 未付金额
     */
    @TableField(value = "unpaidAmount")
    private Integer unpaidAmount;

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
     * 支付方式
     */
    @TableField(value = "payWay")
    private Integer payWay;

    /**
     * 支付类型
     */
    @TableField(value = "payType")
    private Integer payType;

    /**
     * 是否手工单 0 否 1 是
     */
    @TableField(value = "artificial")
    private Boolean artificial;

    /**
     * 编辑人员
     */
    @TableField(value = "editor")
    private String editor;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 停车订单结束类型 0:正常结束　1:巡检人员结束　2:用户结束 3:系统处理结束
     */
    @TableField(value = "terminateType")
    private Integer terminateType;

    /**
     * 停车订单结束时间
     */
    @TableField(value = "terminateType")
    private Integer terminateTime;

    /**
     * 结束停车用户ID
     */
    @TableField(value = "terminateUserId")
    private Long terminateUserId;

    /**
     * 结束停车用户姓名
     */
    @TableField(value = "terminateUserName")
    private String terminateUserName;

    /**
     * 开票状态 0 不可开票 1 未开票
     */
    @TableField(value = "invoiceStatus")
    private Integer invoiceStatus;

    /**
     * 发票申请订单号
     */
    @TableField(value = "invoiceApplyNo")
    private String invoiceApplyNo;

    /**
     * TCC事务状态标志
     */
    @TableField(value = "tccStatus")
    private Integer tccStatus;

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
