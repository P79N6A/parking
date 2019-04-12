package com.zoeeasy.cloud.pay.trade.dto.result.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.result.FullAuditedEntityDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 根据订单查询
 *
 * @Date: 2018/06/27
 * @author: zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PaymentOrderGetByBizOrderResultDto", description = "根据订单查询")
public class PaymentOrderGetByBizOrderResultDto extends FullAuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "userId")
    private Long userId;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "bizOrderNo")
    private String bizOrderNo;

    /**
     * 订单金额
     */
    @ApiModelProperty(value = "orderAmount")
    private BigDecimal orderAmount;

    /**
     * 支付通道编号
     */
    @ApiModelProperty(value = "payWay")
    private Integer payWay;

    /**
     * 支付方式类型编号
     */
    @ApiModelProperty(value = "payType")
    private Integer payType;

    /**
     * 订单时间
     */
    @ApiModelProperty(value = "订单时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date orderTime;
}
