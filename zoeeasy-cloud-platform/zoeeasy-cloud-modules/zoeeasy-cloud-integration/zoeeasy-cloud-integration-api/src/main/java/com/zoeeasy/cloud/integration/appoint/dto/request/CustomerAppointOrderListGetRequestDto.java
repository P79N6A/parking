package com.zoeeasy.cloud.integration.appoint.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import com.zoeeasy.cloud.integration.cts.IntegrationConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 获取用户预约订单列表请求参数
 *
 * @author walkman
 * @since 2018-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CustomerAppointOrderListGetRequestDto", description = "获取用户预约订单列表请求参数")
public class CustomerAppointOrderListGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * customerUserId
     */
    @ApiModelProperty(value = "customerUserId", required = true)
    @NotNull(message = IntegrationConstant.CUSTOMER_USERID_NOT_EMPTY)
    private Long customerUserId;

}
