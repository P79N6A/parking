package com.zoeeasy.cloud.ucc.menu.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取菜单请求参数
 *
 * @author walkman
 */
@ApiModel(value = "MenuGetRequestDto", description = "获取菜单请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuGetRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单代码
     */
    @ApiModelProperty("菜单代码")
    private String code;

}
