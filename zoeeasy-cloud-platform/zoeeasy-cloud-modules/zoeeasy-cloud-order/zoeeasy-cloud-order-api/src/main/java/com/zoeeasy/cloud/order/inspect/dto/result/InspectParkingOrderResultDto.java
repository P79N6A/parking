package com.zoeeasy.cloud.order.inspect.dto.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 巡检停车订单视图模型
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "InspectParkingOrderResultDto", description = "巡检停车订单视图模型")
public class InspectParkingOrderResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车订单号
     */
    @ApiModelProperty(value = "停车订单号")
    private String orderNo;

    /**
     * 停车场名称
     */
    @ApiModelProperty(value = "停车场名称")
    private String parkingName;

    /**
     * 车位编号
     */
    @ApiModelProperty(value = "车位编号")
    private String parkingLotNumber;

    /**
     * 停车日期
     */
    @ApiModelProperty(value = "停车日期")
    private String parkingTime;

    /**
     * 停车开始时间
     */
    @ApiModelProperty(value = "停车开始时间")
    private String startTime;

    /**
     * 停车结束时间
     */
    @ApiModelProperty(value = "停车结束时间")
    private String endTime;

    /**
     * 停车时长(XX天XX小时XX分XX秒)
     */
    @ApiModelProperty(value = "停车时长")
    private String parkingLength;

    /**
     * 是否可以支付
     */
    @ApiModelProperty(value = "是否可以支付")
    private Boolean payable;

    /**
     * 应付金额
     */
    @ApiModelProperty(value = "应付金额")
    private String payableAmount;

    /**
     * 实付金额
     */
    @ApiModelProperty(value = "实付金额")
    private String actualPayAmount;

    /**
     * 停车状态
     */
    @ApiModelProperty(value = "停车状态(1 : 在停中 2 已离场)")
    private Integer status;

    /**
     * 支付状态
     */
    @ApiModelProperty(value = "支付状态")
    private Integer payStatus;

    /**
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间")
    private String payTime;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String plateNumber;

    /**
     * 车牌颜色
     * LicensePlateColorEnum
     */
    @ApiModelProperty(value = "车牌颜色")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer carStyle;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    private Integer payWay;

    /**
     * 支付类型
     */
    @ApiModelProperty(value = "支付类型")
    private Integer payType;
}
