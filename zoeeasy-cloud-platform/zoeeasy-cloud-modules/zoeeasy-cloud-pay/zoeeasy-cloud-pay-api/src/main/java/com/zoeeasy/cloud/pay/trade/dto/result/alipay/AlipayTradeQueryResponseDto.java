package com.zoeeasy.cloud.pay.trade.dto.result.alipay;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 支付宝查询订单响应参数
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AlipayTradeQueryResponseDto", description = "支付宝查询订单响应参数")
public class AlipayTradeQueryResponseDto extends EntityDto<Long> {

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
     * 用户支付宝号
     */
    @ApiModelProperty(value = "buyerLogonId", required = true)
    private String buyerLogonId;

    /**
     * 交易状态
     */
    @ApiModelProperty(value = "tradeStatus", required = true)
    private String tradeStatus;

    /**
     * 交易的订单金额
     */
    @ApiModelProperty(value = "totalAmount", required = true)
    private String totalAmount;

    /**
     * 实收金额
     */
    @ApiModelProperty(value = "receiptAmount")
    private String receiptAmount;

    /**
     * 买家实付金额
     */
    @ApiModelProperty(value = "buyerPayAmount")
    private String buyerPayAmount;

    /**
     * 积分支付的金额
     */
    @ApiModelProperty(value = "pointAmount")
    private String pointAmount;

    /**
     * 交易中用户支付的可开具发票的金额
     */
    @ApiModelProperty(value = "invoiceAmount")
    private String invoiceAmount;

    /**
     * 本次交易打款给卖家的时间
     */
    @ApiModelProperty(value = "sendPayDate")
    private Date sendPayDate;

    /**
     * 商户门店编号
     */
    @ApiModelProperty(value = "storeId")
    private String storeId;

    /**
     * 商户机具终端编号
     */
    @ApiModelProperty(value = "terminalId")
    private String terminalId;

    /**
     * 请求交易支付中的商户店铺的名称
     */
    @ApiModelProperty(value = "storeName")
    private String storeName;

    /**
     * 买家在支付宝的用户id
     */
    @ApiModelProperty(value = "buyerUserId", required = true)
    private String buyerUserId;

    /**
     * 买家用户类型
     */
    @ApiModelProperty(value = "buyerUserType")
    private String buyerUserType;

    /**
     * 交易支付使用的资金渠道
     */
    @ApiModelProperty(value = "fundBillList")
    private List fundBillList;
}
