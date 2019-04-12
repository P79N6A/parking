package com.zoeeasy.cloud.order.appoint.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 预约订单预支付金额计算结果
 *
 * @author walkman
 * @date 2018-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointOrderAmountResultDto", description = "预约订单预支付金额计算结果")
public class AppointOrderAmountResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;
}
