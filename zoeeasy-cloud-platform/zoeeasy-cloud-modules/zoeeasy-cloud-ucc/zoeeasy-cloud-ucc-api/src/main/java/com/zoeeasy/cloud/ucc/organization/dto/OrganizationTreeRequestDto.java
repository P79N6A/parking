package com.zoeeasy.cloud.ucc.organization.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门树请求参数
 */
@ApiModel(value = "OrganizationTreeRequestDto", description = "部门树请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class OrganizationTreeRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("上级部门ID")
    private Long parentId;
}
