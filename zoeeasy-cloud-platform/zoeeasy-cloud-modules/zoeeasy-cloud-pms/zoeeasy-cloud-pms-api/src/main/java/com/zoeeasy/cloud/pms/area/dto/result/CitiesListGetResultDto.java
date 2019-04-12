package com.zoeeasy.cloud.pms.area.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 市列表
 *
 * @author Kane
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CitiesListGetResultDto", description = "市列表")
public class CitiesListGetResultDto extends EntityDto<Long> {
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
     * 区县列表
     */
    @ApiModelProperty(value = "区县列表")
    private List<CountiesListGetResultDto> list;

}
