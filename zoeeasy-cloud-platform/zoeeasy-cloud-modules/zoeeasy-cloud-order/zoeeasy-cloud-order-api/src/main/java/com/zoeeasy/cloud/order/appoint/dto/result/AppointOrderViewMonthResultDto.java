package com.zoeeasy.cloud.order.appoint.dto.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 预约订单详情视图
 *
 * @author zwq
 * @date 2018-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointOrderViewMonthResultDto", description = "预约订单详情视图")
public class AppointOrderViewMonthResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    private Long parkingId;

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
     * 停车场经度
     */
    @ApiModelProperty(value = "停车场经度")
    private Double longitude;

    /**
     * 停车场纬度
     */
    @ApiModelProperty(value = "停车场纬度")
    private Double latitude;

    /**
     * 距离
     */
    @ApiModelProperty(value = "距离(单位：米)")
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "##.##")
    @JsonSerialize(using = ToStringSerializer.class)
    private Double distance;

    /**
     * 距离单位(小于1km，单位m;d大于1km，单位km)
     */
    @ApiModelProperty(value = "距离单位")
    private String distanceUnit;

    /**
     * 车位编号
     */
    @ApiModelProperty(value = "车位编号")
    private String parkingLotCode;

    /**
     * 平台平台预约记录流水号
     */
    @ApiModelProperty(value = "平台平台预约记录流水号")
    private String orderNo;

    /**
     * 预约收费说明
     */
    @ApiModelProperty(value = "预约收费说明")
    private String appointDescription;

    /**
     * 收费说明
     */
    @ApiModelProperty(value = "收费说明")
    private String chargeDescription;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String plateNumber;

    /**
     * 车牌颜色
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
     * 预约下单日期
     */
    @ApiModelProperty(value = "预约下单日期")
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
    @ApiModelProperty(value = "支付时限")
    private Integer payLimit;

    /**
     * 支付截止时间
     */
    @ApiModelProperty(value = "支付截止时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date payLimitTime;

    /**
     * 预约状态
     */
    @ApiModelProperty(value = "预约状态")
    private Integer appointStatus;

    /**
     * 支付状态
     */
    @ApiModelProperty(value = "支付状态")
    private Integer payStatus;

    /**
     * 预约支付金额(元)
     */
    @ApiModelProperty(value = "预约支付金额")
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#0.00")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal payAmount;

    /**
     * 是否允许用户手动取消
     */
    @ApiModelProperty(value = "是否允许用户手动取消")
    private Boolean canCancel;

    /**
     * 取消费用
     */
    @ApiModelProperty(value = "取消费用")
    private BigDecimal cancelFee;

    /**
     * 取消原因
     */
    @ApiModelProperty(value = "取消原因")
    private String cancelReason;
}
