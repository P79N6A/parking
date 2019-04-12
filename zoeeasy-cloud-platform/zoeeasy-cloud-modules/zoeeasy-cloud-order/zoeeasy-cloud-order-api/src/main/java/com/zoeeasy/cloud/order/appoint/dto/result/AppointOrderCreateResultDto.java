package com.zoeeasy.cloud.order.appoint.dto.result;

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
 * 预约订单创建结果
 *
 * @author walkman
 * @since 2018-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointOrderCreateResultDto", description = "预约订单创建结果")
public class AppointOrderCreateResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 预约订单号
     */
    @ApiModelProperty(value = "预约订单号")
    private String orderNo;

    /**
     * 预约金额
     */
    @ApiModelProperty(value = "预约金额")
    private BigDecimal payAmount;

    /**
     * 支付时限(分钟)
     */
    @ApiModelProperty(value = "支付时限(分钟)")
    private Integer payLimit;

    /**
     * 支付截止时间
     */
    @ApiModelProperty(value = "支付截止时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date payLimitTime;

    /**
     * 是否需要支付
     */
    @ApiModelProperty(value = "是否需要支付")
    private Boolean needPay;
}
