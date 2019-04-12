package com.zoeeasy.cloud.order.inspect.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.order.cts.OrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author zwq
 * @date 2018/11/21 0021
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "InspectUpdateParkingOrderRequestDto")
public class InspectUpdateParkingOrderRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * orderNo
     */
    @ApiModelProperty(value = "orderNo", required = true)
    @NotBlank(message = OrderConstant.ORDER_NO_NOT_EMPTY)
    private String orderNo;

    /**
     * payWay
     */
    @ApiModelProperty(value = "payWay", required = true)
    @NotNull(message = OrderConstant.PAYWAY_NOT_EMPTY)
    private Integer payWay;

    /**
     * paymentAmount
     */
    @ApiModelProperty(value = "paymentAmount", required = true)
    @NotNull(message = OrderConstant.PAYMENTAMOUNT_NOT_EMPTY)
    @Digits(message = "支付金额无效", integer = 6, fraction = 2)
    @DecimalMin(value = "0", message = "支付金额无效")
    @DecimalMax(value = "100000", message = "支付金额无效")
    private BigDecimal paymentAmount;
}
