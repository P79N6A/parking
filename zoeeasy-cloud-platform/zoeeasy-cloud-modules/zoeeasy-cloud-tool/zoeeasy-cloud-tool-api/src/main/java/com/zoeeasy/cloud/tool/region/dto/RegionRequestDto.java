package com.zoeeasy.cloud.tool.region.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取地区请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RegionRequestDto", description = "获取地区请求参数")
public class RegionRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * code
     */
    @ApiModelProperty("code")
    private String code;
}
