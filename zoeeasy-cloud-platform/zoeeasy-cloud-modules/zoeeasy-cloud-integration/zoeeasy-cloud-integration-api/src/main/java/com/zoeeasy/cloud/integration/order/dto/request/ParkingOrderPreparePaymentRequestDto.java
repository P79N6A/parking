package com.zoeeasy.cloud.integration.order.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.order.cts.OrderConstant;
import com.zoeeasy.cloud.pay.constant.PaymentConst;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 停车账单支付准备请求参数
 *
 * @author walkman
 * @since 2018-06-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PreparePaymentParkingOrderRequestDto", description = "停车账单预支付请求参数")
public class ParkingOrderPreparePaymentRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车记录流水号
     */
    @ApiModelProperty(value = "停车记录流水号")
    private String recordNo;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    @NotEmpty(message = OrderConstant.PLATE_NUMBER_NOT_EMPTY)
    private String plateNumber;

    /**
     * 停车订单号
     */
    @ApiModelProperty(value = "停车订单号")
    @NotEmpty(message = OrderConstant.ORDER_NO_NOT_EMPTY)
    private String orderNo;

    /**
     * 支付用户ID
     */
    @ApiModelProperty(value = "支付用户ID")
    @NotNull(message = OrderConstant.ORDER_PAY_USER_NOT_NULL)
    private Long payedUserId;

    /**
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间")
    @NotNull(message = OrderConstant.ORDER_PAY_TIME_NOT_NULL)
    private Date payTime;

    /**
     * 支付金额
     */
    @ApiModelProperty(value = "支付金额(分)")
    @NotNull(message = OrderConstant.ORDER_PAY_AMOUNT_NOT_NULL)
    private Integer payAmount;

    @NotNull(message = PaymentConst.PAYMENT_PAY_WAY_INVALID)
    private Integer payWay;

    @NotNull(message = PaymentConst.PAYMENT_PAY_TYPE_NOT_NULL)
    private Integer payType;
}
