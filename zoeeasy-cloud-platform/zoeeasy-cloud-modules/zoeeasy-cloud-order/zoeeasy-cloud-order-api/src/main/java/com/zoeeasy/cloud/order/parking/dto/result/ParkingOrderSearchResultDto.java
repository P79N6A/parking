package com.zoeeasy.cloud.order.parking.dto.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 页面停车订单查询视图模型
 *
 * @Date: 2018/6/22
 * @author: wh
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingOrderSearchResultDto", description = "页面停车订单查询视图模型")
public class ParkingOrderSearchResultDto extends BaseDto {
    private static final long serialVersionUID = 1L;

    /**
     * 停车订单号
     */
    @ApiModelProperty(value = "停车订单号")
    private String orderNo;

    /**
     * 订单金额
     */
    @ApiModelProperty(value = "订单金额")
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#0.00")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal payableAmount;

    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private String username;

    /**
     * 停车状态
     */
    @ApiModelProperty(value = "停车状态")
    private Integer status;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String plateNumber;

    /**
     * 停车场名称
     */
    @ApiModelProperty(value = "停车场名称")
    private String parkingName;

    /**
     * 支付状态
     */
    @ApiModelProperty(value = "支付状态")
    private Integer payStatus;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式", hidden = true)
    private Integer payWay;

    /**
     * 是否出场
     */
    @ApiModelProperty(value = "是否出场(0 全部 1 未出场 2 已出场),默认0")
    private Integer outed;

    /**
     * 是否手工单
     */
    @ApiModelProperty(value = "是否出场(0 否 1 是 )")
    private Integer artificial;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
