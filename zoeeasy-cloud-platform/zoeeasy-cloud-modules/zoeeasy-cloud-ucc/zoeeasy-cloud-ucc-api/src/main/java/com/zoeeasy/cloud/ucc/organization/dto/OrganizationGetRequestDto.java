package com.zoeeasy.cloud.ucc.organization.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门获取请求参数
 */
@ApiModel(value = "OrganizationGetRequestDto", description = "部门获取请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class OrganizationGetRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;
}
