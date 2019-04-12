package com.zoeeasy.cloud.order.parking.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 网页停车订单查询视图模型
 *
 * @Date: 2018/6/25
 * @author: wh
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingOrderSearchDetailResultDto", description = "网页停车订单查询视图模型")
public class ParkingOrderSearchDetailResultDto extends BaseDto {
    private static final long serialVersionUID = 1L;

    /**
     * 停车订单号
     */
    @ApiModelProperty(value = "停车订单号")
    private String orderNo;

    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private String username;

    /**
     * 注册手机
     */
    @ApiModelProperty(value = "注册手机")
    private String phoneNumber;

    /**
     * 停车场名称
     */
    @ApiModelProperty(value = "停车场名称")
    private String parkingName;

    /**
     * 停车场地址
     */
    @ApiModelProperty(value = "停车场地址")
    private String parkingAddress;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String plateNumber;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型")
    private Integer carStyle;

    /**
     * 订单类型
     */
    @ApiModelProperty(value = "订单类型(0 全部 1 正常停车订单 2 预约停车订单 3 限时免费订单)")
    private Integer orderType;

    /**
     * 支付状态
     */
    @ApiModelProperty(value = "支付状态", notes = "0:未支付  1:已支付")
    private Integer payStatus;

    /**
     * 停车状态
     */
    @ApiModelProperty(value = "停车状态")
    private Integer status;

    /**
     * 是否出场
     */
    @ApiModelProperty(value = "是否出场(0 全部 1 未出场 2 已出场),默认0")
    private Integer outed;

    /**
     * 停车时长(XX天XX小时XX分XX秒)
     */
    @ApiModelProperty(value = "停车时长")
    private String parkingLength;

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
     * 应付金额
     */
    @ApiModelProperty(value = "应付金额")
    private String payableAmount;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private String createTime;

    /**
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间")
    private String payTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

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
     * 限免时长(XX天XX小时XX分XX秒)
     */
    @ApiModelProperty(value = "限免时长")
    private String freeLength;

    /**
     * 收费时长(XX天XX小时XX分XX秒)
     */
    @ApiModelProperty(value = "收费时长")
    private String chargeLength;

    /**
     * 编辑人员
     */
    @ApiModelProperty(value = "编辑人员")
    private String editor;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    private Integer payWay;
}
