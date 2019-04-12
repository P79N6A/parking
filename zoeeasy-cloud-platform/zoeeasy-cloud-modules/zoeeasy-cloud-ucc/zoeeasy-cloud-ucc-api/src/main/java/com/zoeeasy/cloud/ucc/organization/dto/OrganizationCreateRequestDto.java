package com.zoeeasy.cloud.ucc.organization.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.ucc.organization.cst.OrganizationConstant;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 添加组织结构请求参数
 *
 * @author walkman
 */
@ApiModel(value = "OrganizationCreateRequestDto", description = "添加组织结构请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class OrganizationCreateRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 上级部门ID
     */
    private Long parentId;

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
