package com.zoeeasy.cloud.order.transaction.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.order.cts.OrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 停车账单支付完成请求参数
 *
 * @author walkman
 * @date 2018-03-22
 */
@Data
@ApiModel(value = "CompletePaymentParkingOrderRequestDto", description = "停车账单支付完成请求参数")
public class CompletePaymentParkingOrderRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车订单号
     */
    @ApiModelProperty(value = "停车订单号")
    @NotNull(message = OrderConstant.ORDER_NO_NOT_EMPTY)
    private String orderNo;

    /**
     * 支付订单号
     */
    @ApiModelProperty(value = "支付订单号")
    @NotNull(message = OrderConstant.PAY_ORDER_NO_NOT_EMPTY)
    private String payOrderNo;

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
     * 支付成功时间
     */
    @ApiModelProperty(value = "支付成功时间")
    private Date succeedPayTime;

    /**
     * 实际支付金额
     */
    @ApiModelProperty(value = "实际支付金额")
    private Integer actualAmount;

}
