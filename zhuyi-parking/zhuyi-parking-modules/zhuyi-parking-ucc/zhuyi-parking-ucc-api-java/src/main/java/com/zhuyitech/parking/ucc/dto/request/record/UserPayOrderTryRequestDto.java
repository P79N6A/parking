package com.zhuyitech.parking.ucc.dto.request.record;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * tcc-try阶段维护userPayOrder表
 *
 * @author zwq
 * @date 2018-08-03
 */
@ApiModel(value = "UserPayOrderTryRequestDto", description = "tcc-try阶段维护userPayOrder表")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPayOrderTryRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 支付订单号
     */
    @ApiModelProperty(value = "支付订单号")
    private String orderNo;

    /**
     * code TCC各阶段标识
     */
    @ApiModelProperty(value = "支付订单号")
    private Integer payStatus;

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

}
