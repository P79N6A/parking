package com.zoeeasy.cloud.ucc.service.impl;

import com.scapegoat.infrastructure.core.UserIdentifier;
import com.scapegoat.infrastructure.core.base.FundamentalRequestContext;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.session.ISessionIdentity;
import com.zoeeasy.cloud.ucc.navigation.IUserNavigationManager;
import com.zoeeasy.cloud.ucc.navigation.dto.request.NavigationAllGetRequestDto;
import com.zoeeasy.cloud.ucc.navigation.NavigationService;
import com.zoeeasy.cloud.ucc.navigation.dto.UserMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 导航菜单服务
 */
@Service("navigationService")
@Slf4j
public class NavigationServiceImpl implements NavigationService {

    @Autowired
    private IUserNavigationManager userNavigationManager;

    /**
     * 获取用户所有菜单列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<UserMenu> getAllNavigationList(NavigationAllGetRequestDto requestDto) {
        ListResultDto<UserMenu> listResultDto = new ListResultDto<>();
        ISessionIdentity sessionIdentity = FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity();
        if (sessionIdentity != null) {
            UserIdentifier userIdentifier = (UserIdentifier) sessionIdentity.toUserIdentifier();
            listResultDto.setItems(userNavigationManager.getAllUserMenus(userIdentifier));
        }
        return listResultDto.success();
    }
}
