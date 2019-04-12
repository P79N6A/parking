package com.zoeeasy.cloud.ucc.role.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import com.zoeeasy.cloud.ucc.role.cst.RoleConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * 修改角色请求参数
 *
 * @author walkman
 */
@ApiModel(value = "RoleEditRequestDto", description = "修改角色请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleEditRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    @ApiModelProperty(required = true, value = "角色名称")
    @NotBlank(message = RoleConstant.ROLE_NAME_NOT_EMPTY)
    @Length(min = RoleConstant.ROLE_NAME_MINI_LENGTH,
            max = RoleConstant.ROLE_NAME_MAX_LENGTH,
            message = RoleConstant.ROLE_NAME_LENGTH_RANGE)
    @Pattern(regexp = RoleConstant.ROLE_NAME_PATTERN,
            message = RoleConstant.ROLE_NAME_ILLEGAL)
    private String name;

    /**
     * 是否默认角色
     */
    @ApiModelProperty(value = "是否默认角色")
    private Boolean defaultRole;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;

}

