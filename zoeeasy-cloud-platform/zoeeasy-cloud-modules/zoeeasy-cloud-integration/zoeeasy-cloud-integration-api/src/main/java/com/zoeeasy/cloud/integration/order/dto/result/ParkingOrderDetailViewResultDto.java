package com.zoeeasy.cloud.integration.order.dto.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.jackson.annotation.SensitiveInfo;
import com.scapegoat.infrastructure.jackson.enums.SensitiveType;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingImageViewResultDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 停车账单详情视图模型
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingOrderDetailViewResultDto", description = "停车账单详情视图模型")
public class ParkingOrderDetailViewResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 记录流水号
     */
    @ApiModelProperty(value = "记录流水号")
    private String recordNo;

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
    private String parkingLotCode;

    /**
     * 泊位号
     */
    @ApiModelProperty(value = "泊位号")
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
     * 是否需要支付
     */
    @ApiModelProperty(value = "是否需要支付(payable==true并且payableAMount>0)")
    private Boolean needPay;

    /**
     * 是否预约停车
     */
    @ApiModelProperty(value = "是否预约停车")
    private Boolean appointed;

    /**
     * 预约费用
     */
    @ApiModelProperty(value = "预约费用")
    private String appointFee;

    /**
     * 预约时间
     */
    @ApiModelProperty(value = "预约时间")
    private String appointTime;

    /**
     * 是否限免停车
     */
    @ApiModelProperty(value = "是否限免停车")
    private Boolean limitFree;

    /**
     * 停车免费时长
     */
    @ApiModelProperty(value = "停车免费时长")
    private String freeLength;

    /**
     * 停车收费时长
     */
    @ApiModelProperty(value = "停车收费时长")
    private String chargeLength;

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
    @ApiModelProperty(value = "停车状态")
    private Integer status;

    /**
     * 支付状态
     */
    @ApiModelProperty(value = "支付状态", notes = "0:未支付  1:已支付")
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
    @SensitiveInfo(value = SensitiveType.PLATE_NUMBER)
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
     * 停车场地址
     */
    @ApiModelProperty(value = "停车场地址")
    private String parkingAddress;

    /**
     * 停车场收费标准
     */
    @ApiModelProperty(value = "收费标准")
    private String chargeDescription;

    /**
     * 停车图像URL列表
     */
    @ApiModelProperty(value = "停车图像URL列表")
    private List<ParkingImageViewResultDto> images;
}
