package com.zoeeasy.cloud.integration.order.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 停车账单支付准备结果
 *
 * @author walkman
 * @since 2018-06-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ParkingOrderPreparePaymentResultDto", description = "停车账单支付准备结果")
public class ParkingOrderPreparePaymentResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 是否需要支付
     */
    private Boolean needPay;

    /**
     * 是否可以支付
     */
    private Boolean payable;

    /**
     * 可支付金额
     */
    private Integer payableAmount;
}