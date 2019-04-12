package com.zhuyitech.parking.ucc.trade.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * APP充值响应结果
 *
 * @author walkman
 * @date 2018-01-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RechargePlaceResultDto", description = "充值响应结果")
public class RechargePlaceResultDto extends BaseDto {

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
     * 充值订单号
     */
    @ApiModelProperty("充值订单号")
    private String orderNo;

    /**
     * 支付宝订单信息
     */
    @ApiModelProperty("支付宝订单信息")
    private String alipayOrderInfo;

    /**
     * 微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
     */
    @ApiModelProperty("prepay_id")
    private String prepayId;

    /**
     * 交易类型，取值为：JSAPI，NATIVE，APP等
     */
    @ApiModelProperty("trade_type")
    private String tradeType;

    /**
     * 商户号
     */
    @ApiModelProperty(value = "partnerId", notes = "商户号")
    private String partnerId;

}
