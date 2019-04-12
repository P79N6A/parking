package com.zhuyitech.parking.tool.dto.request.region;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;


/**
 * 获取所有所有地区列表请求参数
 *
 * @author zwq
 */
@ApiModel(value = "RegionListTotalGetRequestDto", description = "获取所有所有地区列表请求参数")
public class RegionListTotalGetRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;
}
