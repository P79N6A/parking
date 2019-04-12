package com.zoeeasy.cloud.integration.trade.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 停车账单支付响应结果
 *
 * @author walkman
 * @date 2018-01-11
 */
@ApiModel(value = "ParkingOrderChargeResultDto", description = "停车账单支付响应结果")
@Data
@EqualsAndHashCode(callSuper = true)
public class ParkingOrderChargeResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 支付订单号
     */
    @ApiModelProperty("支付订单号")
    private String orderNo;

    /**
     * 交易类型，取值为：JSAPI，NATIVE，APP等
     */
    @ApiModelProperty("tradeType")
    private String tradeType;

    /**
     * 是否需要支付,如果false，则不需要继续下一步的支付流程
     */
    @ApiModelProperty("是否需要支付")
    private Boolean needPay;

    /**
     * 支付金额
     */
    @ApiModelProperty("支付金额")
    private String paymentAmount;

    /**
     * 收款二维码
     */
    @ApiModelProperty("收款二维码")
    private String qrCode;

    /**
     * 收款二维码URL
     */
    @ApiModelProperty("收款二维码URL")
    private String qrCodeUrl;

}
