package com.zoeeasy.cloud.tool.region.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RegionTreeGetRequestDto", description = "区域树请求参数")
public class RegionTreeGetRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

}
