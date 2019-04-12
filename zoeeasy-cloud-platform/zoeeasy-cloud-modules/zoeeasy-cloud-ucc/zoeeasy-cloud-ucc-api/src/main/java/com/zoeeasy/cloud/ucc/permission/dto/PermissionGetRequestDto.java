package com.zoeeasy.cloud.ucc.permission.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PermissionGetRequestDto", description = "获取权限请求参数")
public class PermissionGetRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 权限编码
     */
    @ApiModelProperty(value = "权限编码")
    private String code;

    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称")
    private String name;

    /**
     * 状态 0、正常 1、禁用
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

}
