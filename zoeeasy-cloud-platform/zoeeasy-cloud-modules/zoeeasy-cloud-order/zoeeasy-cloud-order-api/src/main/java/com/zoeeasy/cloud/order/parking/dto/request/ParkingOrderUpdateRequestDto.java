package com.zoeeasy.cloud.order.parking.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.order.cts.OrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * 修改停车账单请求参数
 *
 * @Date: 2018/7/25
 * @author: wh
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingOrderUpdateRequestDto")
public class ParkingOrderUpdateRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    @NotEmpty(message = OrderConstant.ORDER_NO_NOT_EMPTY)
    private String orderNo;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String plateNumber;

    /**
     * 订单金额
     */
    @ApiModelProperty(value = "订单金额")
    private String payableAmount;

    /**
     * 是否出场
     */
    @ApiModelProperty(value = "是否出场(0 全部 1 未出场 2 已出场),默认0")
    private Integer outed;

    /**
     * 入场时间
     */
    @ApiModelProperty(value = "入场时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date startTime;

    /**
     * 出场时间
     */
    @ApiModelProperty(value = "出场时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date endTime;

    /**
     * 编辑人员
     */
    @ApiModelProperty(value = "editor")
    private String editor;
}
