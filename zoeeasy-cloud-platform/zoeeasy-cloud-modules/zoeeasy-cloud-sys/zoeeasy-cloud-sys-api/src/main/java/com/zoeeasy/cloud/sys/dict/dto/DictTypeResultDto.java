package com.zoeeasy.cloud.sys.dict.dto;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import com.scapegoat.infrastructure.core.dto.result.FullAuditedEntityDto;
import com.zoeeasy.cloud.sys.parameter.dto.result.ParamTypeResultDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 字典类型视图
 *
 * @author walkman
 * @since 2018-02-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DictTypeResultDto", description = "字典类型视图")
public class DictTypeResultDto extends EntityDto<Long> {

    /**
     * 字典编码
     */
    @ApiModelProperty(value = "字典编码")
    private String dictCode;

    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称")
    private String dictName;

    /**
     * 父参数编码
     */
    @ApiModelProperty(value = "父字典编码")
    private String parentCode;

    /**
     * 子参数类型
     */
    @ApiModelProperty(value = "子字典类型")
    private List<DictTypeResultDto> child;

    /**
     * 是否静态
     */
  /*  @ApiModelProperty(value = "是否静态")
    private Boolean staticDict;*/

    /**
     * 租户或宿主
     */
    /*@ApiModelProperty(value = "租户或宿主")
    private Integer tenancyHostSide;*/

    /**
     * 状态（1正常 0停用
     */
 /*   @ApiModelProperty(value = "状态")
    private Integer status;*/

    /**
     * 备注
     */
  /*  @ApiModelProperty(value = "备注")
    private String remark;*/

}
