package com.zoeeasy.cloud.ucc.permission.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 权限添加请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PermissionAddRequestDto", description = "添加菜单请求参数")
public class PermissionAddRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 权限编码
     */
    @ApiModelProperty(required = true, value = "权限编码")
    @NotEmpty(message = "权限编码不能为空")
    private String code;

    /**
     * 权限名称
     */
    @ApiModelProperty(required = true, value = "权限名称")
    @NotEmpty(message = "权限名称不能为空")
    private String name;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;

}




