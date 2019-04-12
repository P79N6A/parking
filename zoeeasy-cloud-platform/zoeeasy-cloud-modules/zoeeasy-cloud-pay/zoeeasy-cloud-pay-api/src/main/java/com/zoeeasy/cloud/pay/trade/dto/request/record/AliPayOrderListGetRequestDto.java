package com.zoeeasy.cloud.pay.trade.dto.request.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 获取支付宝支付记录列表
 *
 * @Date: 2018/09/12
 * @author: zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AliPayOrderListGetRequestDto", description = "获取支付宝支付记录列表")
public class AliPayOrderListGetRequestDto extends BaseDto {

    public static final long serialVersionUID = 1L;

    /**
     * 支付宝交易号
     */
    @ApiModelProperty("支付宝交易号")
    private String tradeNo;

    /**
     * 商户订单号
     */
    @ApiModelProperty("商户订单号")
    private String outTradeNo;

    /**
     * 商户返回退款申请的流水号
     */
    @ApiModelProperty("商户返回退款申请的流水号")
    private String outBizNo;

    /**
     * 订单标题
     */
    @ApiModelProperty("订单标题")
    private String subject;

    /**
     * 订单金额
     */
    @ApiModelProperty("订单金额")
    private BigDecimal totalAmount;

    /**
     * 实收金额
     */
    @ApiModelProperty("实收金额")
    private BigDecimal receiptAmount;

    /**
     * 开票金额
     */
    @ApiModelProperty("开票金额")
    private BigDecimal invoiceAmount;

    /**
     * 用户实际支付金额
     */
    @ApiModelProperty("用户实际支付金额")
    private BigDecimal buyerPayAmount;

    /**
     * 集分宝金额
     */
    @ApiModelProperty("集分宝金额")
    private BigDecimal pointAmount;

    /**
     * 总退款金额
     */
    @ApiModelProperty("总退款金额")
    private BigDecimal refundFee;

    /**
     * 商品描述
     */
    @ApiModelProperty("商品描述")
    private String body;

    /**
     * 货币类型
     */
    @ApiModelProperty("货币类型")
    private String currencyType;

    /**
     * 交易创建时间
     */
    @ApiModelProperty("交易创建时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date gmtCreate;

    /**
     * 交易付款时间
     */
    @ApiModelProperty("交易付款时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date gmtPayment;

    /**
     * 交易退款时间
     */
    @ApiModelProperty("商户订单号")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date gmtRefund;

    /**
     * 交易结束时间
     */
    @ApiModelProperty("交易结束时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date gmtClose;
}
