package com.zoeeasy.cloud.pay.trade.dto.result.order;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 支付订单下单响应结果
 *
 * @author walkman
 * @date 2018-03-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PlacePaymentOrderResultDto", description = "支付订单下单响应结果")
public class PlacePaymentOrderResultDto extends BaseDto {

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
     * 业务订单号
     */
    @ApiModelProperty("业务订单号")
    private String orderNo;

    /**
     * 支付订单号
     */
    @ApiModelProperty("支付订单号")
    private String payOrderNo;

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
    @ApiModelProperty(value = "partnerId", notes = "商户号")
    private String partnerId;

    /**
     * trade_type为NATIVE时有返回，用于生成二维码，展示给用户进行扫码支付
     */
    @ApiModelProperty("qrCode")
    private String qrCode;

    /**
     * trade_type为NATIVE时有返回，用于生成二维码，展示给用户进行扫码支付
     */
    @ApiModelProperty("qrCodeUrl")
    private String qrCodeUrl;

    /**
     * 支付宝订单信息
     */
    @ApiModelProperty("支付宝订单信息")
    private String alipayOrderInfo;
}
