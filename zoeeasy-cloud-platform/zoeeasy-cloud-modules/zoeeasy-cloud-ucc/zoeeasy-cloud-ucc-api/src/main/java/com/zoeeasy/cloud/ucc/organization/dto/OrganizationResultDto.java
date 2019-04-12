package com.zoeeasy.cloud.ucc.organization.dto;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 */
@ApiModel(value = "OrganizationResultDto", description = "部门详情请求结果")
@Data
@EqualsAndHashCode(callSuper = true)
public class OrganizationResultDto extends AuditedEntityDto<Long> {

    /**
     * 代码
     */
    @ApiModelProperty(value = "代码")
    private String code;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 说明
     */
    @ApiModelProperty(value = "说明")
    private String remarks;
}
