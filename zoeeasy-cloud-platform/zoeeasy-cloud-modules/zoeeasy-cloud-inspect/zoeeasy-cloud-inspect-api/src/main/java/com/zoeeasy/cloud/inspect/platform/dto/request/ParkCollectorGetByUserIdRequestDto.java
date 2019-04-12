package com.zoeeasy.cloud.inspect.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/11/12 0012
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkCollectorGetByUserIdRequestDto", description = "无租户通过userId获取收费请求参数")
public class ParkCollectorGetByUserIdRequestDto extends BaseDto {
}
