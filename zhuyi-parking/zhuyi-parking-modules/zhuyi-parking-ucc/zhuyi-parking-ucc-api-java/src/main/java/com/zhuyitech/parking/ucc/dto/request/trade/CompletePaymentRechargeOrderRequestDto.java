package com.zhuyitech.parking.ucc.dto.request.trade;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 充值支付完成请求参数
 *
 * @author walkman
 * @date 2018-03-22
 */
@ApiModel(value = "CompleteRechargePaymentRequestDto", description = "充值支付完成请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class CompletePaymentRechargeOrderRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long userId;


    /**
     * 充值订单号
     */
    @ApiModelProperty(value = "充值订单号")
    private String orderNo;

    /**
     * 支付订单号
     */
    @ApiModelProperty(value = "支付订单号")
    private String payOrderNo;

    /**
     *
     */
    @ApiModelProperty("交易订单号")
    private String transactionNo;

    /**
     * 支付成功时间
     */
    @ApiModelProperty(value = "支付成功时间")
    private Date succeedPayTime;

    /**
     * 实际支付金额
     */
    @ApiModelProperty(value = "实际支付金额")
    private BigDecimal actualAmount;

    /**
     * 充值方式(1.支付宝;2.微信)
     */
    @ApiModelProperty(value = "充值方式")
    private Integer rechargeType;

}
