package com.zoeeasy.cloud.ucc.organization.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 部门树视图模型
 *
 * @author walkman
 */
@ApiModel(value = "OrganizationTreeResultDto", description = "部门树视图模型")
@Data
@EqualsAndHashCode(callSuper = true)
public class OrganizationTreeResultDto extends EntityDto<Long> {

    /**
     * 机构代码
     */
    @ApiModelProperty(value = "code")
    private String code;

    /**
     * 机构名称
     */
    @ApiModelProperty(value = "name")
    private String name;

    /**
     * 节点路径
     */
    @ApiModelProperty(value = "pathCode")
    @JsonIgnore
    private String pathCode;

    /**
     * 子部门列表
     */
    @ApiModelProperty(value = "children")
    private List<OrganizationTreeResultDto> children;

}
