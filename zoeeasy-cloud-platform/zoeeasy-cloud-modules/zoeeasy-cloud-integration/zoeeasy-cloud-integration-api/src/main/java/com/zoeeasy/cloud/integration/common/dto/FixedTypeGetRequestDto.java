package com.zoeeasy.cloud.integration.common.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 固定车类型获取请求参数
 *
 * @date: 2018/10/17.
 * @author：zm
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "FixedTypeGetRequestDto", description = "固定车类型获取请求参数")
public class FixedTypeGetRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

}
