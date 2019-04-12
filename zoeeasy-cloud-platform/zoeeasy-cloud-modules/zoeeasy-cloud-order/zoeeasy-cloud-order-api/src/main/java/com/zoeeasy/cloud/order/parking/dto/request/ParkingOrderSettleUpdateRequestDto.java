package com.zoeeasy.cloud.order.parking.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.order.cts.OrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * 停车订单结算信息修改请求参数
 *
 * @author AkeemSuper
 * @date 2018/11/8 0008
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingOrderSettleUpdateRequestDto", description = "停车订单结算信息修改请求参数")
public class ParkingOrderSettleUpdateRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号", required = true)
    @NotBlank(message = OrderConstant.ORDER_NO_NOT_EMPTY)
    private String orderNo;

    /**
     * 停车记录号
     */
    @ApiModelProperty("停车记录号")
    private String recordNo;

    /**
     * 结算开始时间
     */
    @ApiModelProperty("结算开始时间")
    private Date startTime;

    /**
     * 结算结束时间
     */
    @ApiModelProperty("结算结束时间")
    private Date endTime;

    /**
     * 结算状态
     */
    @ApiModelProperty("结算状态")
    private Integer status;

    /**
     * 结算时间
     */
    @ApiModelProperty("结算时间")
    private Date settleTime;

    /**
     * 结算金额
     */
    @ApiModelProperty("结算金额")
    private Integer settleAmount;
}
