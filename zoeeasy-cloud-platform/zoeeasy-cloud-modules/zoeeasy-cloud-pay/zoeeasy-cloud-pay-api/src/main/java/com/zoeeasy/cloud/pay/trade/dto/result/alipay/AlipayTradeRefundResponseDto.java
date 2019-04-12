package com.zoeeasy.cloud.pay.trade.dto.result.alipay;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 支付宝订单退款响应参数
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AlipayTradeRefundResponseDto", description = "支付宝订单退款响应参数")
public class AlipayTradeRefundResponseDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 网关返回码
     */
    @ApiModelProperty(value = "code", required = true)
    private String code;

    /**
     * 网关返回码描述
     */
    @ApiModelProperty(value = "msg", required = true)
    private String msg;

    /**
     * 业务返回码
     */
    @ApiModelProperty(value = "subCode")
    private String subCode;

    /**
     * 业务返回码描述
     */
    @ApiModelProperty(value = "subMsg")
    private String subMsg;

    /**
     * 支付宝交易号
     */
    @ApiModelProperty(value = "tradeNo", required = true)
    private String tradeNo;

    /**
     * 商家订单号
     */
    @ApiModelProperty(value = "outTradeNo", required = true)
    private String outTradeNo;

    /**
     * 用户的登录id
     */
    @ApiModelProperty(value = "buyerLogonId", required = true)
    private String buyerLogonId;

    /**
     * 本次退款是否发生了资金变化
     */
    @ApiModelProperty(value = "fundChange", required = true)
    private String fundChange;

    /**
     * 退款总金额
     */
    @ApiModelProperty(value = "refundFee", required = true)
    private String refundFee;

    /**
     * 退款支付时间
     */
    @ApiModelProperty(value = "gmtRefundPay", required = true)
    private Date gmtRefundPay;

    /**
     * 退款使用的资金渠道
     */
    @ApiModelProperty(value = "refundDetailItemList")
    private List refundDetailItemList;

    /**
     * 交易在支付时候的门店名称
     */
    @ApiModelProperty(value = "storeName")
    private String storeName;

    /**
     * 买家在支付宝的用户id
     */
    @ApiModelProperty(value = "buyerUserId", required = true)
    private String buyerUserId;

    /**
     * 本次退款金额中买家退款金额
     */
    @ApiModelProperty(value = "presentRefundBuyerAmount")
    private String presentRefundBuyerAmount;

    /**
     * 本次退款金额中平台优惠退款金额
     */
    @ApiModelProperty(value = "presentRefundDiscountAmount")
    private String presentRefundDiscountAmount;

    /**
     * 本次退款金额中商家优惠退款金额
     */
    @ApiModelProperty(value = "presentRefundMdiscountAmount")
    private String presentRefundMdiscountAmount;
}
