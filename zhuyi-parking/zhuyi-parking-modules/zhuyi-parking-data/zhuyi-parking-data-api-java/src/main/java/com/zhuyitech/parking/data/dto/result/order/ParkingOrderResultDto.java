package com.zhuyitech.parking.data.dto.result.order;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 云平台停车账单数据
 *
 * @author wangfeng
 * @since 2018/12/3 13:27
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class ParkingOrderResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 平台停车记录编号
     */
    private String recordNo;

    /**
     * 平台停车账单流水号
     */
    private String orderNo;

    /**
     * 平台停车场ID
     */
    private Long parkingId;

    /**
     * 停车场编号
     */
    private String parkingCode;

    /**
     * 停车场名称
     */
    private String parkingName;

    /**
     * 平台泊位ID
     */
    private Long parkingLotId;

    /**
     * 泊位号
     */
    private String parkingLotNumber;

    /**
     * 车牌号
     */
    private String plateNumber;

    /**
     * 车牌颜色
     */
    private Integer plateColor;

    /**
     * 车辆类型
     */
    private Integer carStyle;

    /**
     * 停车开始时间
     */
    private Date startTime;

    /**
     * 停车结束时间
     */
    private Date endTime;

    /**
     * 停车时长
     */
    private Long parkingLength;

    /**
     * 免费停车时长
     */
    private Long freeLength;

    /**
     * 收费停车时长
     */
    private Long chargeLength;

    /**
     * 停车场收费信息ID
     */
    private Long chargeInfoId;

    /**
     * 收费模式 1:离场前收费 2: 离场后收费
     */
    private Integer chargeMode;

    /**
     * 停车状态
     */
    private Integer status;

    /**
     * 第三方平台账单编号
     */
    private String thirdBillNo;

    /**
     * 第三方平台账单数据源
     */
    private Integer thirdBillSourceType;

    /**
     * 第三方平台账单同步状态(0 未创建 1 已创建 2 已支付确认)
     */
    private Integer thirdBillSyncStatus;

    /**
     * 是否预约停车 0 否 1 是
     */
    private Boolean appointed;

    /**
     * 预约订单号
     */
    private String appointOrderNo;

    /**
     * 是否已结算 0 未结算 1 已结算
     */
    private Boolean settle;

    /**
     * 结算模式(1 离场前结算 2 离场后结算 )
     */
    private Integer settleMode;

    /**
     * 结算金额
     */
    private Integer settleAmount;

    /**
     * 结算时间
     */
    private Date settleTime;

    /**
     * 是否限免停车
     */
    private Boolean limitFree;

    /**
     * 是否可以支付0 不可支付 1 可以支付
     */
    private Boolean payable;

    /**
     * 是否需要支付0 无需支付 1 需要支付
     */
    private Boolean needPay;

    /**
     * 应付金额
     */
    private Integer payableAmount;

    /**
     * 免于支付说明
     */
    private String freePayReason;

    /**
     * 支付状态 0 :未支付 1:已支付  2:支付中
     */
    private Integer payStatus;

    /**
     * 实付金额
     */
    private Integer actualPayAmount;

    /**
     * 支付方式
     */
    private Integer payWay;

    /**
     * 支付类型
     */
    private Integer payType;

    /**
     * 支付用户ID
     */
    private Long payedUserId;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 是否手工单 0 否 1 是
     */
    private Boolean artificial;

    /**
     * 编辑人员
     */
    private String editor;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建者
     */
    protected Long creatorUserId;

    /**
     * 创建日期
     */
    protected Date creationTime;

    /**
     * 更新者
     */
    protected Long lastModifierUserId;

    /**
     * 更新日期
     */
    protected Date lastModificationTime;

    /**
     * 删除者
     */
    protected Long deleterUserId;

    /**
     * 删除日期
     */
    protected Date deletionTime;

    /**
     * 删除标记（0：正常；1：删除 ）
     */
    protected Integer deleted;

    /**
     * 版本号
     */
    private Long version;


}

