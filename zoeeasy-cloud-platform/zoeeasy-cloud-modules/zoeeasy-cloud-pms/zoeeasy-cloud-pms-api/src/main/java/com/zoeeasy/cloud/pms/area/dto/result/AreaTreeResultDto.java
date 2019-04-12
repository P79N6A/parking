package com.zoeeasy.cloud.pms.area.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 区域树参数
 *
 * @author Kane
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AreaTreeResultDto", description = "区域树参数")
public class AreaTreeResultDto extends EntityDto<Long> {

    /**
     * 父ID
     */
    @ApiModelProperty(value = "父ID")
    private Long parentId;

    /**
     * 简称
     */
    @ApiModelProperty(value = "区域名称")
    private String name;

    /**
     * 编号
     */
    @ApiModelProperty(value = "区域编号")
    private String code;

    /**
     * 层级
     */
    @ApiModelProperty(value = "层级")
    private Integer level;

    /**
     * 下级树
     */
    @ApiModelProperty(value = "下级列表")
    private List child;

}

