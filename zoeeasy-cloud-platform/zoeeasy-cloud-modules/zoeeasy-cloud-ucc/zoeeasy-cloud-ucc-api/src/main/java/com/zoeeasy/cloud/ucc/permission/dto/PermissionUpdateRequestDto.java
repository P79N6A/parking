package com.zoeeasy.cloud.ucc.permission.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 添加菜单请求参数
 *
 * @author walkman
 */
@ApiModel(value = "MenuUpdateRequestDto", description = "更新菜单请求参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class PermissionUpdateRequestDto extends SessionEntityDto<Long> {

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
     * 父节点
     */
    @ApiModelProperty(value = "父节点")
    private Long parentId;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 状态 0、正常 1、禁用
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

}
