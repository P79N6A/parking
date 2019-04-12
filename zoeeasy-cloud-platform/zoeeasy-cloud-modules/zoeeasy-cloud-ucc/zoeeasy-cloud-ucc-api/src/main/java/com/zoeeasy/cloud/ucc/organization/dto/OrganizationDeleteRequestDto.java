package com.zoeeasy.cloud.ucc.organization.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 添加组织结构请求参数
 *
 * @author walkman
 */
@ApiModel(value = "OrganizationDeleteRequestDto", description = "添加组织结构请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class OrganizationDeleteRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty
    private String code;

}