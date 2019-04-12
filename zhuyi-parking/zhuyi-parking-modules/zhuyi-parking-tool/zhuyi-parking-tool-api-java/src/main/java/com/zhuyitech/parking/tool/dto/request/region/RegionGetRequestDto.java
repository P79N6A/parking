package com.zhuyitech.parking.tool.dto.request.region;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;


/**
 * 获取地区请求参数
 *
 * @author walkman
 */
@ApiModel(value = "RegionGetRequestDto", description = "获取地区请求参数")
public class RegionGetRequestDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;
}
