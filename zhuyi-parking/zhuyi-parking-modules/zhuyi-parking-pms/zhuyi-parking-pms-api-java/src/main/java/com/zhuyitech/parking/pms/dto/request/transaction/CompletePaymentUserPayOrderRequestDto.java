package com.zhuyitech.parking.pms.dto.request.transaction;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 用户支付完成请求参数
 *
 * @author zwq
 * @date 2018-08-08
 */
@ApiModel(value = "CompletePaymentUserPayOrderRequestDto", description = "用户支付完成请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class CompletePaymentUserPayOrderRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 业务订单号
     */
    @ApiModelProperty(value = "业务订单号")
    private String orderNo;

    /**
     * 支付订单号
     */
    @ApiModelProperty(value = "支付订单号")
    private String payOrderNo;

    /**
     * 交易订单号
     */
    @ApiModelProperty(value = "交易订单号")
    private String transactionNo;

    /**
     * 支付成功时间
     */
    @ApiModelProperty(value = "支付成功时间")
    private Date succeedPayTime;

    /**
     * 实际支付金额(元)
     */
    @ApiModelProperty(value = "实际支付金额")
    private BigDecimal actualAmount;

}
