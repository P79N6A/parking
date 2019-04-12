package com.zoeeasy.cloud.ucc.menu.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 分页查询所有父菜单请求参数
 *
 * @author walkman
 */
@ApiModel(value = "MenuParentQueryPagedResultRequestDto", description = "分页查询菜单请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuParentQueryPagedResultRequestDto extends SessionPagedRequestDto {

    private static final long serialVersionUID = 1L;
}
