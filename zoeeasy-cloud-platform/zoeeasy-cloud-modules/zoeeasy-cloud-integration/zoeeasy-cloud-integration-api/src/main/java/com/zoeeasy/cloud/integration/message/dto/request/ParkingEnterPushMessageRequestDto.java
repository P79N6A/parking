package com.zoeeasy.cloud.integration.message.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.order.cts.OrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/10/19 0019
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingEnterPushMessageRequestDto", description = "发送入场停车推送消息")
public class ParkingEnterPushMessageRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号", required = true)
    @NotBlank(message = OrderConstant.ORDER_NO_NOT_EMPTY)
    @Length(min = OrderConstant.MIN, max = OrderConstant.MAX, message = OrderConstant.LENGTH_RANGE)
    private String orderNo;

    /**
     * 订单ID
     */
    @ApiModelProperty(value = "订单ID", required = true)
    @NotNull(message = OrderConstant.ORDER_ID_NOT_EMPTY)
    private Long orderId;

    /**
     * 停车场名称
     */
    @ApiModelProperty(value = "停车场名称", required = true)
    @NotBlank(message = OrderConstant.PARKING_NAME_NOT_EMPTY)
    @Length(min = OrderConstant.MIN, max = OrderConstant.MAX, message = OrderConstant.LENGTH_RANGE)
    private String parkingName;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间", required = true)
    @NotNull(message = OrderConstant.PARKING_START_TIME_NOT_EMPTY)
    private Date startTime;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色", required = true)
    @NotNull(message = OrderConstant.PLATE_COLOR_NOT_EMPTY)
    private Integer plateColor;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号", required = true)
    @NotBlank(message = OrderConstant.PLATE_NUMBER_NOT_EMPTY)
    @Length(min = OrderConstant.MIN, max = OrderConstant.MAX, message = OrderConstant.LENGTH_RANGE)
    private String plateNumber;
}
