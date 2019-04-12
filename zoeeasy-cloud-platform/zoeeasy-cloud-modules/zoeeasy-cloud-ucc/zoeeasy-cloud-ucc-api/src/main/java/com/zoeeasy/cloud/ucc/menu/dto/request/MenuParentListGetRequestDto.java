package com.zoeeasy.cloud.ucc.menu.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询所有父菜单请求参数
 *
 * @author walkman
 */
@ApiModel(value = "MenuParentListGetRequestDto", description = "所有父菜单请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuParentListGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;
}
