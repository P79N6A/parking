package com.zoeeasy.cloud.tool.region.dto;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 删除地区请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RegionDeleteRequestDto", description = "删除地区请求参数")
public class RegionDeleteRequestDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;
}
