package com.zoeeasy.cloud.ucc.organization.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import com.zoeeasy.cloud.ucc.organization.cst.OrganizationConstant;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 部门编辑请求参数
 */
@ApiModel(value = "OrganizationEditRequestDto", description = "部门编辑请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class OrganizationEditRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 组织名称
     */
    @NotNull(message = OrganizationConstant.ORGANIZATION_NAME_NOT_EMPTY)
    @Length(min = OrganizationConstant.ORGANIZATION_NAME_MINI_LENGTH,
            max = OrganizationConstant.ORGANIZATION_NAME_MAX_LENGTH,
            message = OrganizationConstant.ORGANIZATION_NAME_LENGTH_RANGE)
    private String name;

    /**
     * 组织描述
     */
    private String description;

}