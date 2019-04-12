package com.zoeeasy.cloud.order.parking.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.order.cts.OrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * 修改停车账单请求参数
 *
 * @Date: 2018/7/25
 * @author: zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingOrderUpdateForPayRequestDto")
public class ParkingOrderPayStatusUpdateRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车牌号
     */
//    @ApiModelProperty(value = "车牌号")
//    @Length(max = OrderConstant.MAX, message = OrderConstant.LENGTH_RANGE)
//    private String plateNumber;

    /**
     * 平台停车账单流水号
     */
    @ApiModelProperty(value = "平台停车账单流水号", required = true)
    @NotBlank(message = OrderConstant.ORDER_NO_NOT_EMPTY)
    @Length(min = OrderConstant.MIN, max = OrderConstant.MAX, message = OrderConstant.LENGTH_RANGE)
    private String orderNo;

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
    private Integer payType;

    /**
     * 是否结算
     */
    private Boolean settle;

    /**
     * 结算时间
     */
    private Date settleTime;

    /**
     * 第三方平台账单同步状态
     */
    private Integer thirdBillSyncStatus;

    /**
     * 停车时长
     */
    private Long parkingLength;

    /**
     * 第三方平台账单编号
     */
    private String thirdBillNo;

}
