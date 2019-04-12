package com.zoeeasy.cloud.pay.trade.dto.request.order;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 充值成功请求参数
 *
 * @author walkman
 * @date 2018-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RechargeSuccessRequestDto", description = "充值成功请求参数")
public class RechargeSuccessRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "userId")
    private Long customerUserId;

    /**
     * 充值单号
     */
    @ApiModelProperty(value = "充值单号", required = true)
    @NotBlank(message = "充值单号不能为空")
    private String orderNo;

    /**
     * 支付方式
     * 1  支付宝
     * 2  微信
     * 3  钱包余额
     */
    @ApiModelProperty(value = "支付方式,(1支付宝, 2 微信 3  钱包余额)", required = true, dataType = "Integer", allowableValues = "1,2,3")
    private Integer payWay;

    /**
     * SpbillCreateIp
     */
    @ApiModelProperty(value = "ip", hidden = true, dataType = "String", notes = "请求IP")
    private String spbillCreateIp;

    /**
     * 支付业务订单号,对应支付宝tradeNo或微信支付outTradeNo
     */
    @ApiModelProperty(value = "transactionNo")
    private String transactionNo;

    /**
     * 订单来源
     */
    @ApiModelProperty(value = "orderFrom")
    private String orderFrom;

    /**
     * 交易业务类型 ：消费、充值等
     */
    @ApiModelProperty(value = "bizOrderType")
    private Integer bizOrderType;

    /**
     * 交易业务订单号
     */
    @ApiModelProperty(value = "bizOrderNo")
    private String bizOrderNo;

    /**
     * 订单金额
     */
    @ApiModelProperty(value = "orderAmount")
    private BigDecimal orderAmount;

    /**
     * 订单日期
     */
    @ApiModelProperty(value = "orderDate")
    private Date orderDate;

    /**
     * 支付成功时间
     */
    @ApiModelProperty(value = "succeedPayTime")
    private Date succeedPayTime;

    /**
     * 状态(参考枚举:PayStatusEnum)
     */
    @ApiModelProperty(value = "status")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "remark")
    private String remark;

    /**
     * 实际支付金额
     */
    @ApiModelProperty(value = "totalAmount")
    private BigDecimal totalAmount;
}
