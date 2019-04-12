package com.zhuyitech.parking.tool.dto.result.region;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 市列表
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CityListGetResultDto", description = "市列表")
public class CityListGetResultDto extends EntityDto<Long> {

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
    private List<CountyListGetResultDto> list;

}
