package com.zoeeasy.cloud.ucc.permission.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * * 权限列表请求参数表
 *
 * @author walkman
 */
@ApiModel(value = "PermissionListGetRequestDto", description = "权限列表请求参数表")
@Data
@EqualsAndHashCode(callSuper = false)
public class PermissionListGetRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 权限ID列表
     */
    @ApiModelProperty(value = "权限ID列表")
    private List<Long> permissionIds;

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
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

}
