package com.zoeeasy.cloud.collect.dto.request;

import com.scapegoat.infrastructure.core.dto.request.RequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Date: 2019-03-01
 * @author: wf
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PaymentNotifyRequestDto", description = "支付结果通知请求参数")
public class PaymentNotifyRequestDto extends RequestDto {

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
