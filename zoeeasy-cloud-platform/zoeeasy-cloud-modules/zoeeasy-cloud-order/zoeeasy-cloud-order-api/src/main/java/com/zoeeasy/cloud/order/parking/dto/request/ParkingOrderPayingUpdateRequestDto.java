package com.zoeeasy.cloud.order.parking.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.order.cts.OrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * 停车订单准备支付更新请求参数(根据订单号、车牌号)
 *
 * @author walkman
 * @since 2018-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingOrderPayingUpdateRequestDto", description = "停车订单准备支付更新请求参数")
public class ParkingOrderPayingUpdateRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 平台停车账单号
     */
    @ApiModelProperty(value = "平台停车账单号", required = true)
    @NotBlank(message = OrderConstant.ORDER_NO_NOT_EMPTY)
    @Length(min = OrderConstant.MIN, max = OrderConstant.MAX, message = OrderConstant.LENGTH_RANGE)
    private String orderNo;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    @NotEmpty(message = OrderConstant.PLATE_NUMBER_NOT_EMPTY)
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
    @ApiModelProperty(value = "第三方平台账单编号")
    private String thirdBillNo;

    /**
     * 第三方账单数据源
     */
    @ApiModelProperty(value = "第三方账单数据源")
    private Integer thirdSourceType;

    /**
     * 第三方平台账单同步状态(0 未创建 1 已创建 2 已支付确认)
     */
    @ApiModelProperty(value = "第三方平台账单同步状态(0 未创建 1 已创建 2 已支付确认)")
    private Integer thirdSyncStatus;

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
     * 支付类型
     */
    @ApiModelProperty(value = "支付类型")
    private Integer payType;

}
