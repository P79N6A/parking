package com.zoeeasy.cloud.sys.dict.dto;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取字典数据返回参数
 *
 * @date: 2019/02/25.
 * @author：zm
 */
@ApiModel(value = "DictTypeItemListResultDto", description = "获取字典数据返回参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class DictTypeItemListResultDto extends EntityDto<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 字典编码
     */
    @ApiModelProperty("字典编码")
    private String dictCode;

    /**
     * 字典标签
     */
    @ApiModelProperty("字典标签")
    private String dictLabel;

    /**
     * 字典值
     */
    @ApiModelProperty("字典值")
    private String dictValue;

    /**
     * 字典排序
     */
    @ApiModelProperty("字典排序")
    private Integer sort;

    /**
     * 是否默认
     */
    @ApiModelProperty("是否默认")
    private Boolean defaultItem;

    /**
     * 状态(1正常 0停用)
     */
    @ApiModelProperty("状态")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;
}
