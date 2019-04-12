package com.zoeeasy.cloud.order.appoint.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.order.cts.AppointOrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 预约订单取消请求参数
 *
 * @author zwq
 * @date 2018-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointOrderUpdateByOrderNoRequestDto", description = "预约订单取消请求参数")
public class AppointOrderUpdateByOrderNoRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 订单流水号
     */
    @ApiModelProperty("订单流水号")
    @NotEmpty(message = AppointOrderConstant.ORDERNO_NOT_EMPTY)
    private String orderNo;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "userId")
    private Long userId;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "parkingId")
    private Long parkingId;

    /**
     * 停车场名称
     */
    @ApiModelProperty(value = "parkingName")
    private String parkingName;

    /**
     * 泊位ID
     */
    @ApiModelProperty(value = "parkingLotId")
    private Long parkingLotId;

    /**
     * 车位编号
     */
    @ApiModelProperty(value = "parkingLotNumber")
    private String parkingLotCode;

    /**
     * 预约收费说明
     */
    @ApiModelProperty(value = "appointDescription")
    private String appointDescription;

    /**
     * 收费说明
     */
    @ApiModelProperty(value = "chargeDescription")
    private String chargeDescription;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "plateNumber")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "plateColor")
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "carStyle")
    private Integer carStyle;

    /**
     * 预约下单日期
     */
    @ApiModelProperty(value = "scheduleDate")
    private Date scheduleDate;

    /**
     * 预约预计开始时间
     */
    @ApiModelProperty(value = "scheduleTime")
    private Date scheduleTime;

    /**
     * 预计停车时长(分钟)
     */
    @ApiModelProperty(value = "scheduleLength")
    private Integer scheduleLength;

    /**
     * 预约有效截止时间
     */
    @ApiModelProperty(value = "deadlineTime")
    private Date deadlineTime;

    /**
     * 支付时限(分钟)
     */
    @ApiModelProperty(value = "payLimit")
    private Integer payLimit;

    /**
     * 支付截止时间
     */
    @ApiModelProperty(value = "payLimitTime")
    private Date payLimitTime;

    /**
     * 预约状态
     */
    @ApiModelProperty(value = "appointStatus")
    private Integer appointStatus;

    /**
     * 支付状态
     */
    @ApiModelProperty(value = "payStatus")
    private Integer payStatus;

    /**
     * 支付时间
     */
    @ApiModelProperty(value = "payTime")
    private Date payTime;

    /**
     * 预约支付金额(元)
     */
    @ApiModelProperty(value = "payAmount")
    private BigDecimal payAmount;

    /**
     * 预约实付金额(元)
     */
    @ApiModelProperty(value = "actualPayAmount")
    private BigDecimal actualPayAmount;

    /**
     * 退还金额(元)
     */
    @ApiModelProperty(value = "refundAmount")
    private BigDecimal refundAmount;

    /**
     * 是否允许用户手动取消
     */
    @ApiModelProperty(value = "canCancel")
    private Boolean canCancel;

    /**
     * 是否超时取消
     */
    @ApiModelProperty(value = "overTimeCancel")
    private Boolean overTimeCancel;

    /**
     * 取消时限
     */
    @ApiModelProperty(value = "cancelTimeLimit")
    private Date cancelTimeLimit;

    /**
     * 取消时间
     */
    @ApiModelProperty(value = "cancelTime")
    private Date cancelTime;

    /**
     * 取消费用
     */
    @ApiModelProperty(value = "cancelFee")
    private BigDecimal cancelFee;

    /**
     * 取消原因
     */
    @ApiModelProperty(value = "cancelReason")
    private String cancelReason;

    /**
     * 备注
     */
    @ApiModelProperty(value = "remark")
    private String remark;
}
