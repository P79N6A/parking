package com.zoeeasy.cloud.order.appoint.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import com.zoeeasy.cloud.order.cts.AppointOrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/10/25 0025
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointOrderUpdateEnterRequestDto", description = "预约订单入场请求参数")
public class AppointOrderUpdateEnterRequestDto extends SessionDto {

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
