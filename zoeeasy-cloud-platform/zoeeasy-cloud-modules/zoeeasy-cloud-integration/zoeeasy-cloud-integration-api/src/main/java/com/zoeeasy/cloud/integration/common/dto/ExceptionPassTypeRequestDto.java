package com.zoeeasy.cloud.integration.common.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/12/24 0024
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ExceptionPassTypeRequestDto", description = "异常过车类型枚举")
public class ExceptionPassTypeRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

}