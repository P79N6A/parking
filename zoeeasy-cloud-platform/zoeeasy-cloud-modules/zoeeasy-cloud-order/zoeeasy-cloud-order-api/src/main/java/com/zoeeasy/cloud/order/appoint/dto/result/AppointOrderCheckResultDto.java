package com.zoeeasy.cloud.order.appoint.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 预约订单支付判断
 *
 * @author zwq
 * @date 2018-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointOrderCheckResultDto", description = "预约订单支付判断")
public class AppointOrderCheckResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 是否可支付
     */
    @ApiModelProperty("是否可支付")
    private Boolean payCheck;
}
