package com.zoeeasy.cloud.integration.order.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 客户端账单下单结果
 *
 * @author walkman
 * @since 2018-12-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TerminateOrderPlaceResultDto", description = "客户端账单下单结果")
public class TerminateOrderPlaceResultDto extends BaseDto {

}