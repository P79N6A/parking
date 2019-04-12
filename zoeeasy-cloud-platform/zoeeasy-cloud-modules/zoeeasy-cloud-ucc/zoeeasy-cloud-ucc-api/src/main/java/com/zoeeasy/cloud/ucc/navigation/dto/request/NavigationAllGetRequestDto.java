package com.zoeeasy.cloud.ucc.navigation.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 添加菜单请求参数
 *
 * @author walkman
 */
@ApiModel(value = "NavigationAllGetRequestDto", description = "添加菜单请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class NavigationAllGetRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;
}
