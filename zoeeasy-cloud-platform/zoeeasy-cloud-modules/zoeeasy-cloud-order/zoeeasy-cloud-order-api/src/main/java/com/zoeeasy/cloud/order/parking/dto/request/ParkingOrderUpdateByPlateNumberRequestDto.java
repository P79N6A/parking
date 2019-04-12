package com.zoeeasy.cloud.order.parking.dto.request;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import com.zoeeasy.cloud.order.cts.OrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 根据车牌号修改停车账单请求参数
 *
 * @author walkman
 * @Date: 2018/7/25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingOrderUpdateByPlateNumberRequestDto")
public class ParkingOrderUpdateByPlateNumberRequestDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 平台停车账单流水号
     */
    @ApiModelProperty(value = "平台停车账单流水号", required = true)
    @NotBlank(message = OrderConstant.ORDER_NO_NOT_EMPTY)
    @Length(min = OrderConstant.MIN, max = OrderConstant.MAX, message = OrderConstant.LENGTH_RANGE)
    private String orderNo;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    @Length(max = OrderConstant.MAX, message = OrderConstant.LENGTH_RANGE)
    private String plateNumber;

    /**
     * 平台停车记录编号
     */
    @ApiModelProperty(value = "平台停车记录编号", required = true)
    @NotBlank(message = OrderConstant.RECORD_NO_NOT_EMPTY)
    @Length(min = OrderConstant.MIN, max = OrderConstant.MAX, message = OrderConstant.LENGTH_RANGE)
    private String recordNo;

    /**
     * 平台停车场ID
     */
    @ApiModelProperty(value = "平台停车场ID", required = true)
    @NotNull(message = OrderConstant.PARKING_ID_NOT_EMPTY)
    private Long parkingId;

    /**
     * 停车场编号
     */
    @ApiModelProperty(value = "停车场编号")
    private String parkingCode;

    /**
     * 停车场名称
     */
    @ApiModelProperty(value = "停车场名称")
    @Length(max = OrderConstant.MAX, message = OrderConstant.LENGTH_RANGE)
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
    @Length(max = OrderConstant.MAX, message = OrderConstant.LENGTH_RANGE)
    private String parkingLotCode;

    /**
     * 泊位编号
     */
    @ApiModelProperty(value = "泊位编号")
    private String parkingLotNumber;

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
     * 是否手工单 0 否 1 是
     */
    @ApiModelProperty(value = "是否手工单 0 否 1 是")
    private Boolean artificial;

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
     * 停车场收费信息ID
     */
    @ApiModelProperty(value = "停车场收费信息ID", required = true)
    @NotNull(message = OrderConstant.CHARGE_INFO_ID_NOT_EMPTY)
    private Long chargeInfoId;

    /**
     * 是否预约停车 0 否 1 是
     */
    @ApiModelProperty(value = "是否预约停车 0 否 1 是")
    private Boolean appointed;

    /**
     * 预约订单号
     */
    @ApiModelProperty(value = "预约订单号")
    @Length(max = OrderConstant.MAX, message = OrderConstant.LENGTH_RANGE)
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
     * 免于支付说明
     */
    @ApiModelProperty(value = "免于支付说明")
    @Length(max = OrderConstant.MAX, message = OrderConstant.LENGTH_RANGE)
    private String freePayReason;

    /**
     * 平台账单编号
     */
    @ApiModelProperty(value = "平台账单编号")
    private String thirdBillNo;

    /**
     * 第三方平台账单数据源
     */
    @ApiModelProperty(value = "第三方平台账单数据源")
    private Integer thirdBillSourceType;

    /**
     * 账单同步状态(0 未创建 1 已创建 2 已支付确认)
     */
    @ApiModelProperty(value = "账单同步状态(0 未创建 1 已创建 2 已支付确认)")
    private Integer thirdBillSyncStatus;

    /**
     * 应付金额
     */
    @ApiModelProperty(value = "应付金额")
    private Integer payableAmount;

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
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    @Length(max = OrderConstant.MAX, message = OrderConstant.LENGTH_RANGE)
    private String payTypeName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Length(max = OrderConstant.MAX, message = OrderConstant.LENGTH_RANGE)
    private String remark;

    /**
     * 编辑人员
     */
    @ApiModelProperty(value = "编辑人员")
    @Length(max = OrderConstant.MAX, message = OrderConstant.LENGTH_RANGE)
    private String editor;

}
