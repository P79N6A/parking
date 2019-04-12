package com.zoeeasy.cloud.order.parking.dto.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.result.FullAuditedEntityDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/10/7 0007
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingOrderResultDto", description = "停车订单返回结果")
public class ParkingOrderResultDto extends FullAuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "租户ID")
    private Long tenantId;

    /**
     * 平台停车记录编号
     */
    @ApiModelProperty(value = "平台停车记录编号")
    private String recordNo;

    /**
     * 平台停车账单流水号
     */
    @ApiModelProperty(value = "平台停车账单流水号")
    private String orderNo;

    /**
     * 平台停车场ID
     */
    @ApiModelProperty(value = "平台停车场ID")
    private Long parkingId;

    /**
     * 停车场名称
     */
    @ApiModelProperty(value = "停车场名称")
    private String parkingName;

    /**
     * 平台泊位ID
     */
    @ApiModelProperty(value = "平台泊位ID")
    private Long parkingLotId;

    /**
     * 车位编号
     */
    @ApiModelProperty(value = "车位编号")
    private String parkingLotNumber;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色")
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型")
    private Integer carStyle;

    /**
     * 停车开始时间
     */
    @ApiModelProperty(value = "停车开始时间")
    private Date startTime;

    /**
     * 停车结束时间
     */
    @ApiModelProperty(value = "停车结束时间")
    private Date endTime;

    /**
     * 停车时长
     */
    @ApiModelProperty(value = "停车时长")
    private Long parkingLength;

    /**
     * 免费停车时长
     */
    @ApiModelProperty(value = "免费停车时长")
    private Long freeLength;

    /**
     * 收费停车时长
     */
    @ApiModelProperty(value = "收费停车时长")
    private Long chargeLength;

    /**
     * 停车场收费信息ID
     */
    @ApiModelProperty(value = "停车场收费信息ID")
    private Long chargeInfoId;

    /**
     * 收费模式 1:离场前收费 2: 离场后收费
     */
    @ApiModelProperty(value = "收费模式 1:离场前收费 2: 离场后收费")
    private Integer chargeMode;

    /**
     * 停车状态
     */
    @ApiModelProperty(value = "停车状态")
    private Integer status;

    /**
     * 第三方平台账单编号
     */
    @ApiModelProperty(value = "海康平台账单编号")
    private String thirdBillNo;

    /**
     * 第三方平台账单数据源
     */
    @ApiModelProperty(value = "第三方平台账单数据源")
    private Integer thirdBillSourceType;

    /**
     * 第三方平台账单同步状态(0 未创建 1 已创建 2 已支付确认)
     */
    @ApiModelProperty(value = "第三方平台账单同步状态(0 未创建 1 已创建 2 已支付确认)")
    private Integer thirdBillSyncStatus;

    /**
     * 是否预约停车 0 否 1 是
     */
    @ApiModelProperty(value = "是否预约停车 0 否 1 是")
    private Boolean appointed;

    /**
     * 预约订单号
     */
    @ApiModelProperty(value = "预约订单号")
    private String appointOrderNo;

    /**
     * 是否已结算 0 未结算 1 已结算
     */
    @ApiModelProperty(value = "是否已结算 0 未结算 1 已结算")
    private Boolean settle;

    /**
     * 结算金额
     */
    @ApiModelProperty(value = "结算金额")
    private Integer settleAmount;

    /**
     * 结算时间
     */
    @ApiModelProperty(value = "结算时间")
    private Date settleTime;

    /**
     * 是否限免停车 0 未结算 1 已结算
     */
    @ApiModelProperty(value = "是否限免停车 0 未结算 1 已结算")
    private Boolean limitFree;

    /**
     * 是否可以支付0 不可支付 1 可以支付
     */
    @ApiModelProperty(value = "是否可以支付0 不可支付 1 可以支付")
    private Boolean payable;

    /**
     * 是否需要支付0 无需支付 1 需要支付
     */
    @ApiModelProperty(value = "是否需要支付0 无需支付 1 需要支付")
    private Boolean needPay;

    /**
     * 应付金额
     */
    @ApiModelProperty(value = "应付金额")
    private Integer payableAmount;

    /**
     * 免于支付说明
     */
    @ApiModelProperty(value = "免于支付说明")
    private String freePayReason;

    /**
     * 支付状态 0 :未支付 1:已支付  2:支付中
     */
    @ApiModelProperty(value = "支付状态 0 :未支付 1:已支付  2:支付中")
    private Integer payStatus;

    /**
     * 实付金额
     */
    @ApiModelProperty(value = "实付金额")
    private Integer actualPayAmount;

    /**
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date payTime;

    /**
     * 支付用户ID
     */
    @ApiModelProperty(value = "支付用户ID")
    private Long payedUserId;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    private Integer payWay;

    /**
     * 支付类型
     */
    @ApiModelProperty(value = "支付类型")
    private Integer payType;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 是否手工单 0 否 1 是
     */
    @ApiModelProperty(value = "是否手工单 0 否 1 是")
    private Boolean artificial;

    /**
     * 编辑人员
     */
    @ApiModelProperty(value = "编辑人员")
    private String editor;

}
