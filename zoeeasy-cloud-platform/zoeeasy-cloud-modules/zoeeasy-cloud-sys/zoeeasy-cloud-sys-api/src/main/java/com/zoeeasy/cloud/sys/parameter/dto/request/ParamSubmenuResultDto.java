package com.zoeeasy.cloud.sys.parameter.dto.request;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 子菜单栏返回参数
 *
 * @author walkman
 */
@ApiModel(value = "ParamSubmenuResultDto", description = "子菜单栏返回参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamSubmenuResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 父ID
     */
    @ApiModelProperty(value = "父ID")
    private Long parentId;

    /**
     * 菜单栏名
     */
    @ApiModelProperty(value = "菜单栏名")
    private String name;

    /**
     * 参数类型
     */
    @ApiModelProperty(value = "参数类型")
    private Integer type;
}