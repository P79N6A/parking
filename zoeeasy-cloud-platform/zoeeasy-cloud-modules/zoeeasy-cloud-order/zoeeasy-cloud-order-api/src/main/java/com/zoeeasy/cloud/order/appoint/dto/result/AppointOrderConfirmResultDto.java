package com.zoeeasy.cloud.order.appoint.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 预约订单确认结果
 *
 * @author walkman
 * @date 2018-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointOrderConfirmResultDto", description = "预约订单确认结果")
public class AppointOrderConfirmResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 预约订单号
     */
    @ApiModelProperty("预约订单号")
    private String orderNo;

    /**
     * 支付金额
     */
    @ApiModelProperty("支付金额")
    private BigDecimal totalAmount;

    /**
     * 是否支付成功
     */
    @ApiModelProperty("是否支付成功")
    private Boolean succeed;

    /**
     * 支付成功时间
     */
    @ApiModelProperty("支付成功时间")
    private Date succeedTime;
}
