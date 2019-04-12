package com.zoeeasy.cloud.integration.common.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/10/29 0029
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CarTypeGetRequestDto", description = "获取车辆类型枚举请求参数")
public class CarTypeGetRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;
}
