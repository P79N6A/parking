package com.zoeeasy.cloud.order.appoint.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 分页获取预约订单列表请求参数
 *
 * @author walkman
 * @since 2018-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointOrderPagedResultRequestDto", description = "分页获取预约订单列表请求参数")
public class ParkingAppointOrderPagedResultRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long customerUserId;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    private Long parkingId;

    /**
     * 泊位ID
     */
    @ApiModelProperty(value = "泊位ID")
    private Long parkingLotId;

    /**
     * 预约订单号
     */
    @ApiModelProperty(value = "预约订单号")
    private String orderNo;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色")
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型")
    private Integer carStyle;

    /**
     * 开始下单日期
     */
    @ApiModelProperty(value = "startOrderTime")
    private Date startOrderTime;

    /**
     * 结束下单日期
     */
    @ApiModelProperty(value = "结束下单日期")
    private Date endOrderTime;
}
