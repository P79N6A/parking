package com.zhuyitech.parking.integration.order.dto.result;

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
@ApiModel(value = "ParkingOrderPaymentResultDto", description = "停车账单支付响应结果")
@Data
@EqualsAndHashCode(callSuper = true)
public class ParkingOrderPaymentResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private String appId;

    /**
     *
     */
    private String timeStamp;

    /**
     *
     */
    private String nonceStr;

    /**
     *
     */
    private String packages;

    /**
     *
     */
    private String signType;

    /**
     *
     */
    private String paySign;

    /**
     * 支付订单号
     */
    @ApiModelProperty("支付订单号")
    private String orderNo;

    /**
     * 支付宝订单信息
     */
    @ApiModelProperty("支付宝订单信息")
    private String alipayOrderInfo;

    /**
     * 微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
     */
    @ApiModelProperty("prepayId")
    private String prepayId;

    /**
     * 交易类型，取值为：JSAPI，NATIVE，APP等
     */
    @ApiModelProperty("tradeType")
    private String tradeType;

    /**
     * 商户号
     */
    private String partnerId;

    /**
     * 是否需要支付,如果false，则不需要继续下一步的支付流程
     */
    @ApiModelProperty("needPay")
    private Boolean needPay;

    /**
     * 支付金额
     */
    @ApiModelProperty("支付金额")
    private String paymentAmount;

}
