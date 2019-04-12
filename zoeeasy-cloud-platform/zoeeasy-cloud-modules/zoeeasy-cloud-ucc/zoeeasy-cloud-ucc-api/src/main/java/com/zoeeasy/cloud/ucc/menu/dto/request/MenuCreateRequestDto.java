package com.zoeeasy.cloud.ucc.menu.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import com.zoeeasy.cloud.ucc.menu.cst.MenuConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 添加菜单请求参数
 *
 * @author walkman
 */
@ApiModel(value = "MenuCreateRequestDto", description = "添加菜单请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuCreateRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单代码
     */
    @NotBlank(message = "菜单代码不能为空")
    @ApiModelProperty("菜单代码")
    private String code;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    @Length(min = MenuConstant.MENU_NAME_MINI_LENGTH, max = MenuConstant.MENU_NAME_MAX_LENGTH, message = "菜单名称{min}-{max}个字符")
    @ApiModelProperty("菜单名称")
    private String name;

    /**
     * 菜单URL
     */
    @NotBlank(message = "菜单URL不能为空")
    @ApiModelProperty("菜单URL")
    private String url;

    /**
     * 菜单类型
     */
    @ApiModelProperty("菜单类型")
    private String type;

    /**
     * 是否显示 0,不显示 1,显示;
     */
    @ApiModelProperty("是否显示")
    private Boolean shown;

    /**
     * 上级菜单id
     */
    @ApiModelProperty("上级菜单id")
    private Long parentId;

    /**
     * 菜单图标
     */
    @ApiModelProperty("菜单图标")
    private String icon;

    /**
     * 权限标识
     */
    @ApiModelProperty("权限标识")
    private String permission;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;

}
