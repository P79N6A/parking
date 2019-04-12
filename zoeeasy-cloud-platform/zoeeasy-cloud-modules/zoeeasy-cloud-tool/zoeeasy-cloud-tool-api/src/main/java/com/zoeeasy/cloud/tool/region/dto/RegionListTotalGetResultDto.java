package com.zoeeasy.cloud.tool.region.dto;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 获取所有所有地区列表响应参数
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RegionListTotalGetResultDto", description = "获取所有所有地区列表响应参数")
public class RegionListTotalGetResultDto extends EntityDto<Long> {

    /**
     * 区域编码
     */
    @ApiModelProperty(value = "区域编码")
    private String adCode;

    /**
     * 简称
     */
    @ApiModelProperty(value = "简称")
    private String fullName;

    /**
     * 层级
     */
    @ApiModelProperty(value = "层级")
    private String level;

    /**
     * 省列表
     */
    @ApiModelProperty(value = "省列表")
    private List<ProvinceListGetResultDto> list;

}
