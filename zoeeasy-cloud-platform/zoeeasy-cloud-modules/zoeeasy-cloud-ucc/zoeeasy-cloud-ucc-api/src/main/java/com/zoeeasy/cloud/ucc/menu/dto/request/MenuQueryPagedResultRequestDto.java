package com.zoeeasy.cloud.ucc.menu.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 分页查询菜单请求参数
 *
 * @author walkman
 */
@ApiModel(value = "MenuQueryPagedResultRequestDto", description = "分页查询菜单请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuQueryPagedResultRequestDto extends SessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID列表
     */
    @ApiModelProperty(value = "菜单ID列表", dataType = "List<Long>")
    private List<Long> menuIds;

    /**
     * 菜单代码
     */
    @ApiModelProperty("菜单代码")
    private String code;

    /**
     * 菜单名称
     */
    @ApiModelProperty("菜单名称")
    private String name;

    /**
     * 菜单URL
     */
    @ApiModelProperty("菜单URL")
    private String url;

    /**
     * 菜单类型
     */
    @ApiModelProperty("菜单URL")
    private String type;

    /**
     * 是否显示 1,显示;2,不显示
     */
    @ApiModelProperty("是否显示")
    private Boolean shown;

    /**
     * 上级菜单id
     */
    @ApiModelProperty("上级菜单id")
    private Long parentId;

}
