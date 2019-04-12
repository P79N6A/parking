package com.zhuyitech.parking.tool.dto.request.region;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;

/**
 * 删除地区请求参数
 *
 * @author walkman
 */
@ApiModel(value = "RegionDeleteRequestDto", description = "删除地区请求参数")
public class RegionDeleteRequestDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;
}
