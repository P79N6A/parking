package com.zhuyitech.parking.integration.order.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 停车账单支付确认判断
 *
 * @author zwq
 * @date 2018-08-03
 */
@ApiModel(value = "PaymentCheckRequestDto", description = "停车账单支付确认判断")
@Data
@EqualsAndHashCode(callSuper = true)
public class PaymentCheckRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 订单流水号
     */
    @ApiModelProperty("订单流水号")
    @NotEmpty(message = "订单流水号不能为空")
    private String orderNo;

}
