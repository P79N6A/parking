package com.zoeeasy.cloud.ucc.organization.dto;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门列表视图模型
 *
 * @author walkman
 */
@ApiModel(value = "OrganizationListResultDto", description = "部门列表视图模型")
@Data
@EqualsAndHashCode(callSuper = true)
public class OrganizationListResultDto extends AuditedEntityDto<Long> {

    /**
     * 部门代码
     */
    @ApiModelProperty(value = "部门代码")
    private String code;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String name;

    /**
     * 说明
     */
    @ApiModelProperty(value = "remarks")
    private String remarks;
}
