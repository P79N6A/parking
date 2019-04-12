package com.zoeeasy.cloud.ucc.menu;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.ucc.menu.dto.*;
import com.zoeeasy.cloud.ucc.menu.dto.request.*;


/**
 * 菜单服务
 *
 * @author walkman
 */
public interface MenuService {

    /**
     * 获取所有静态菜单
     *
     * @return
     */
    ListResultDto<MenuResultDto> getAllStaticMenu(AllStaticMenuListGetRequestDto requestDto);

    /**
     * 获取所有租户端静态菜单
     *
     * @return
     */
    ListResultDto<MenuResultDto> getAllTenancySideStaticMenu(AllTenancyStaticMenuListGetRequestDto requestDto);

    /**
     * 获取所有宿主端静态菜单
     *
     * @return
     */
    ListResultDto<MenuResultDto> getAllHostSideStaticMenu(AllHostStaticMenuListGetRequestDto requestDto);

    /**
     * 新增菜单
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto createMenu(MenuCreateRequestDto requestDto);

    /**
     * 更新菜单
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto updateMenu(MenuEditRequestDto requestDto);

    /**
     * 删除菜单
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto deleteMenu(MenuDeleteRequestDto requestDto);

    /**
     * 获取菜单
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<MenuResultDto> getMenu(MenuGetRequestDto requestDto);

    /**
     * 分页获取菜单列表
     *
     * @param requestDto requestDto
     * @return
     */
    PagedResultDto<MenuResultDto> getMenuPagedList(MenuQueryPagedResultRequestDto requestDto);

}
