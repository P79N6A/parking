package com.zoeeasy.cloud.pay.trade.dto.request.message;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取支付宝通知参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AlipayMessageLogAddRequestDto", description = "支付宝通知参数添加")
public class AlipayMessageLogGetRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 通知校验ID
     */
    @ApiModelProperty("notifyId")
    private String notifyId;

    /**
     * 支付宝分配给开发者的应用Id
     */
    @ApiModelProperty("appId")
    private String appId;

    /**
     * 支付宝交易号
     */
    @ApiModelProperty("tradeNo")
    private String tradeNo;

    /**
     * 商户订单号
     */
    @ApiModelProperty("outTradeNo")
    private String outTradeNo;

    /**
     * 商户业务号
     */
    @ApiModelProperty("outBizNo")
    private String outBizNo;

    /**
     * 消息处理状态
     */
    @ApiModelProperty("status")
    private Integer status;
}
