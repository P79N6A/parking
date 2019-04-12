package com.zoeeasy.cloud.collect.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 支付通知第三方，结果回传处理RequestDto
 *
 * @author Inmier
 * @date 2019-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PaymentNotifyCallBackRequestDto", description = "支付通知第三方，结果回传处理RequestDto")
public class PaymentNotifyCallBackRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 本地停车场Code
     */
    @ApiModelProperty("本地停车场Code")
    private String localCode;

    /**
     * 本地停车场订单流水号
     */
    @ApiModelProperty("本地停车场订单流水号")
    private String parkingOrderNo;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String plateNumber;

    /**
     * 出场通道Code
     */
    @ApiModelProperty("出场通道Code")
    private String gateCode;

    /**
     * 支付金额
     */
    @ApiModelProperty("支付金额")
    private String payAmount;

    /**
     * 支付时间
     */
    @ApiModelProperty("支付时间")
    private String payTime;

}
