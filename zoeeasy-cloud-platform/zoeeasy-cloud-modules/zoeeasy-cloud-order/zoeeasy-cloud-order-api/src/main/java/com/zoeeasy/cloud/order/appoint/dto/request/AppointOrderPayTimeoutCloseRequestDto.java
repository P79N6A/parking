package com.zoeeasy.cloud.order.appoint.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 超时未支付取消订单请求参数
 *
 * @author zwq
 * @date 2018-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointOrderPayTimeoutCloseRequestDto", description = "超时未支付取消订单请求参数")
public class AppointOrderPayTimeoutCloseRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

}
