package com.zhuyitech.parking.ucc.dto.result;

import com.scapegoat.infrastructure.core.dto.result.FullAuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付记录视图
 *
 * @Date: 2018/3/13
 * @author: yuzhicheng
 */
@ApiModel(value = "UserPayOrderResultDto", description = "支付记录视图")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPayOrderResultDto extends FullAuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty("userId")
    private Long userId;

    /**
     * 订单UUID
     */
    @ApiModelProperty("orderUuid")
    private String orderUuid;

    /**
     * 系统支付订单号
     */
    @ApiModelProperty("orderNo")
    private String orderNo;

    /**
     * 业务订单号（ 分别为充值订单号、缴费订单号）
     */
    @ApiModelProperty("bizOrderNo")
    private String bizOrderNo;

    /**
     * 订单类型（1.充值，2，付款）
     */
    @ApiModelProperty("订单类型（1.充值，2，付款）")
    private Integer bizOrderType;

    /**
     * 支付方式（1. 支付宝，2，微信）
     */
    @ApiModelProperty("支付方式（1. 支付宝，2，微信）")
    private Integer payWay;

    /**
     * 支付金额
     */
    @ApiModelProperty("payAmount")
    private BigDecimal payAmount;

    /**
     * 实际支付金额
     */
    @ApiModelProperty("payAmountActual")
    private BigDecimal payAmountActual;

    /**
     * 支付状态
     */
    @ApiModelProperty("payStatus")
    private Integer payStatus;

    /**
     * 支付时间
     */
    @ApiModelProperty("payTime")
    private Date payTime;

    /**
     * 支付成功时间
     */
    @ApiModelProperty("succeedPayTime")
    private Date succeedPayTime;

}
