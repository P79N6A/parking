package com.zoeeasy.cloud.pms.area.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 区域树请求参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AreaTreeRequestDto", description = "区域树请求参数")
public class AreaTreeRequestDto extends SignedSessionRequestDto {
    private static final long serialVersionUID = 1L;
}
