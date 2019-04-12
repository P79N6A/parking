package com.zoeeasy.cloud.ucc.organization.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门列表请求参数
 *
 * @author walkman
 */
@ApiModel(value = "OrganizationPagedRequestDto", description = "部门列表请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class OrganizationPagedRequestDto extends SignedSessionPagedRequestDto {

    @ApiModelProperty("上级部门ID")
    private Long parentId;

    @ApiModelProperty("部门名称")
    private String name;
}
