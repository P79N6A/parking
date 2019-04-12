package com.zoeeasy.cloud.ucc.navigation;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.zoeeasy.cloud.ucc.navigation.dto.UserMenu;
import com.zoeeasy.cloud.ucc.navigation.dto.request.NavigationAllGetRequestDto;

/**
 * 导航菜单服务
 *
 * @author walkman
 */
public interface NavigationService {

    /**
     * 获取用户所有菜单列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<UserMenu> getAllNavigationList(NavigationAllGetRequestDto requestDto);
}
