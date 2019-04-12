package com.zoeeasy.cloud.order.appoint.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 预约订单创建请求参数
 *
 * @author walkman
 * @since 2018-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointOrderCreateRequestDto", description = "预约订单创建请求参数")
public class AppointOrderCreateRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车主用户ID
     */
    @ApiModelProperty(value = "车主用户ID")
    private Long customerUserId;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    private Long parkingId;

    /**
     * tenantId
     */
    @ApiModelProperty(value = "tenantId", hidden = true)
    private Long tenantId;

    /**
     * 停车场名称
     */
    @ApiModelProperty(value = "停车场名称")
    private String parkingName;

    /**
     * 泊位ID
     */
    @ApiModelProperty(value = "泊位ID")
    private Long parkingLotId;

    /**
     * 车位编号
     */
    @ApiModelProperty(value = "车位编号")
    private String parkingLotCode;

    /**
     * 停车场预约信息ID
     */
    @ApiModelProperty(value = "停车场预约信息ID")
    private Long appointInfoId;

    /**
     * 停车场收费信息ID
     */
    @ApiModelProperty(value = "停车场收费信息ID")
    private Long chargeInfoId;

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
     * 订单日期
     */
    @ApiModelProperty(value = "scheduleDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATE, timezone = Const.TIMEZONE_GMT8)
    private Date scheduleDate;

    /**
     * 预约预计开始时间
     */
    @ApiModelProperty(value = "预约预计开始时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date scheduleTime;

    /**
     * 预计停车时长(分钟)
     */
    @ApiModelProperty(value = "预计停车时长")
    private Integer scheduleLength;

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
     * 预约有效截止时间
     */
    @ApiModelProperty(value = "预约有效截止时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date deadlineTime;

    /**
     * 预定取消时限(分钟),以下单时间开始
     */
    @ApiModelProperty(value = "预定取消时限(分钟),以下单时间开始")
    private Integer cancelLimit;

    /**
     * 取消时限
     */
    @ApiModelProperty(value = "取消时限")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date cancelTimeLimit;

    /**
     * 预约支付金额(元)
     */
    @ApiModelProperty(value = "预约支付金额(元)")
    private BigDecimal payAmount;

    /**
     * 预约实付金额(元)
     */
    @ApiModelProperty(value = " 预约实付金额(元)")
    private BigDecimal actualPayAmount;

    /**
     * 是否允许用户手动取消
     */
    @ApiModelProperty(value = "是否允许用户手动取消")
    private Boolean canCancel;

    /**
     * 是否超时取消
     */
    @ApiModelProperty(value = "是否超时取消")
    private Boolean overTimeCancel;
}
