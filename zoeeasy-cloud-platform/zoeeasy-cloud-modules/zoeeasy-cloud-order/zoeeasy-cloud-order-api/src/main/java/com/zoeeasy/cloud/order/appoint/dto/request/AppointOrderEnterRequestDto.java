package com.zoeeasy.cloud.order.appoint.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.zoeeasy.cloud.order.cts.AppointOrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * 预约订单入场请求参数
 *
 * @author walkman
 * @date 2018-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointOrderEnterRequestDto", description = "预约订单入场请求参数")
public class AppointOrderEnterRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 订单流水号
     */
    @ApiModelProperty("订单流水号")
    @NotEmpty(message = AppointOrderConstant.ORDERNO_NOT_EMPTY)
    private String orderNo;

    /**
     * 入场时间
     */
    @ApiModelProperty("入场时间")
    private Date enterTime;
}