package com.zoeeasy.cloud.tool.region.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取所有所有地区列表请求参数
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RegionListTotalGetRequestDto", description = "获取所有所有地区列表请求参数")
public class RegionListTotalGetRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;
}
