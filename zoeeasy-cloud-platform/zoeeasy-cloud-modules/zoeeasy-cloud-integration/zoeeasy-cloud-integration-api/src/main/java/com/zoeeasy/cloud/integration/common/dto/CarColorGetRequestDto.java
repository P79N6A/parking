package com.zoeeasy.cloud.integration.common.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @date: 2018/10/20.
 * @author：zm
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CarColorGetRequestDto", description = "车辆颜色获取请求参数")
public class CarColorGetRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

}
