package com.zoeeasy.cloud.tool.region.dto;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 区县列表
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CountyListGetResultDto", description = "区县列表")
public class CountyListGetResultDto extends EntityDto<Long> {

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

}
