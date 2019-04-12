package com.zoeeasy.cloud.order.appoint.dto.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 预约停车订单视图模型
 *
 * @author walkman
 * @date 2018-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAppointmentOrderResultDto", description = "预约停车订单视图模型")
public class ParkingAppointmentOrderResultDto extends EntityDto<Long> {

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
     * 车位编号
     */
    @ApiModelProperty(value = "车位编号")
    private String parkingLotNumber;

    /**
     * 预约订单号
     */
    @ApiModelProperty(value = "预约订单号")
    private String orderNo;

    /**
     * 预约规则
     */
    @ApiModelProperty(value = "预约规则")
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
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

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
     * 支付截止时间
     */
    @ApiModelProperty(value = "支付截止时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date payLimitTime;

    /**
     * 支付时限(分钟)
     */
    @ApiModelProperty(value = "支付时限")
    private Integer payLimit;

    /**
     * 预约有效截止时间
     */
    @ApiModelProperty(value = "预约有效截止时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date deadlineTime;

    /**
     * 取消时限
     */
    @ApiModelProperty(value = "取消时限")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date cancelTimeLimit;

    /**
     * 预约状态
     */
    @ApiModelProperty(value = "预约状态")
    private Integer appointStatus;

    /**
     * 支付方式(根据PayWayEnum)
     */
    @ApiModelProperty(value = "支付方式")
    private Integer payWay;

    /**
     * 支付类型(根据PayTypeEnum)
     */
    @ApiModelProperty(value = "支付类型")
    private Integer payType;

    /**
     * 支付状态
     */
    @ApiModelProperty(value = "支付状态")
    private Integer payStatus;

    /**
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date payTime;

    /**
     * 预约支付金额(元)
     */
    @ApiModelProperty(value = "预约支付金额(分)")
    private Integer payAmount;

    /**
     * 预约实付金额(元)
     */
    @ApiModelProperty(value = " 预约实付金额(分)")
    private Integer actualPayAmount;

    /**
     * 退还金额(元)
     */
    @ApiModelProperty(value = "退还金额(分)")
    private Integer refundAmount;

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

    /**
     * 取消时间
     */
    @ApiModelProperty(value = "取消时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date cancelTime;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date creationTime;

    /**
     * 取消费用
     */
    @ApiModelProperty(value = "取消费用")
    private Integer cancelFee;

    /**
     * 取消原因
     */
    @ApiModelProperty(value = "取消原因")
    private String cancelReason;
}

