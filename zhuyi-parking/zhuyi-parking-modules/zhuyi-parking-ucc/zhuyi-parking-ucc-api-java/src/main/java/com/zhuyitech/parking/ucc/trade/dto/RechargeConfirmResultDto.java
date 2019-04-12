package com.zhuyitech.parking.ucc.trade.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * APP充值确认结果
 *
 * @author walkman
 * @date 2018-01-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RechargeConfirmResultDto", description = "APP充值确认结果")
public class RechargeConfirmResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 充值订单号
     */
    @ApiModelProperty("充值订单号")
    private String orderNo;

    /**
     * 充值金额
     */
    @ApiModelProperty("充值金额")
    private BigDecimal totalAmount;

    /**
     * 是否充值成功
     */
    @ApiModelProperty("是否充值成功")
    private Boolean succeed;

    /**
     * 充值成功时间
     */
    @ApiModelProperty("充值成功时间")
    private Date succeedTime;
}
