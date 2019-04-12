package com.zoeeasy.cloud.ucc.navigation;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.multitenancy.TenancyHostSide;
import com.zoeeasy.cloud.ucc.menu.MenuService;
import com.zoeeasy.cloud.ucc.menu.dto.MenuResultDto;
import com.zoeeasy.cloud.ucc.menu.dto.request.AllStaticMenuListGetRequestDto;
import com.zoeeasy.cloud.ucc.navigation.dto.MenuItemDefinition;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * AppNavigationProvider
 *
 * @author walkman
 */
public class DatabaseNavigationProvider extends NavigationProvider {

    @Autowired
    private MenuService menuService;

    @Override
    public void setNavigation(INavigationProviderContext context) {

        //根节点列表
        MenuResultDto rootMenu = null;
        //查询所有静态菜单
        AllStaticMenuListGetRequestDto requestDto = new AllStaticMenuListGetRequestDto();
        ListResultDto<MenuResultDto> allMenuResultDto = this.menuService.getAllStaticMenu(requestDto);
        if (allMenuResultDto.isSuccess() && CollectionUtils.isNotEmpty(allMenuResultDto.getItems())) {
            List<MenuResultDto> allMenus = allMenuResultDto.getItems();
            for (MenuResultDto menuResultDto : allMenus) {
                if (menuResultDto.getParentId() == null || menuResultDto.getParentId().equals(0L)) {
                    //找出所有的根节点
                    rootMenu = menuResultDto;
                }
            }
            if (rootMenu != null) {
                //为根节点设置子节点，getChild是递归调用的
                /* 获取根节点下的所有子节点 使用getChild方法*/
                List<MenuItemDefinition> childList = getChild(rootMenu.getId(), allMenus);
                //给根节点设置子节点
                context.getNavigationManager().getMainMenu().setItems(childList);
            }
        }
    }

    /**
     * 获取子节点
     *
     * @param parentId 父节点id
     * @param allMenus 所有菜单列表
     * @return 每个根节点下，所有子部门列表
     */
    private List<MenuItemDefinition> getChild(Long parentId, List<MenuResultDto> allMenus) {
        //子菜单
        List<MenuItemDefinition> childList = new ArrayList<>();
        for (MenuResultDto menuResultDto : allMenus) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点
            if (menuResultDto.getParentId().equals(parentId)) {

                childList.add(MenuItemDefinition.builder()
                        .id(menuResultDto.getId())
                        .code(menuResultDto.getCode())
                        .name(menuResultDto.getName())
                        .url(menuResultDto.getUrl())
                        .order(menuResultDto.getSort())
                        .component(menuResultDto.getComponent())
                        .icon(menuResultDto.getIcon())
                        .visible(menuResultDto.getShown())
                        .level(menuResultDto.getLevel())
                        .pathCode(menuResultDto.getPathCode())
                        .tenancyHostSide(TenancyHostSide.parse(menuResultDto.getTenancyHostSide()))
                        .build());
            }
        }
        //递归
        for (MenuItemDefinition menuItemDefinition : childList) {
            menuItemDefinition.setItems(getChild(menuItemDefinition.getId(), allMenus));
        }
        //如果节点下没有子节点，返回一个空List（递归退出）
        if (CollectionUtils.isEmpty(childList)) {
            return new ArrayList<>();
        }
        //排序
        Collections.sort(childList, sortByPathCode());
        return childList;
    }

    /*
     * 排序,根据order排序
     */
    private Comparator<MenuItemDefinition> sortByPathCode() {

        return new Comparator<MenuItemDefinition>() {

            /**
             *
             * @param menuItemDefinition1 menuItemDefinition1
             * @param menuItemDefinition2 menuItemDefinition2
             * @return
             */
            @Override
            public int compare(MenuItemDefinition menuItemDefinition1, MenuItemDefinition menuItemDefinition2) {
                String pathCode1 = menuItemDefinition1.getPathCode();
                String pathCode2 = menuItemDefinition2.getPathCode();
                int result = 0;
                if (StringUtils.isNotEmpty(pathCode1) && StringUtils.isNotEmpty(pathCode2)) {
                    result = pathCode1.compareTo(pathCode2);
                } else {
                    if (StringUtils.isEmpty(pathCode1) && StringUtils.isNotEmpty(pathCode2)) {
                        result = -1;
                    } else if (StringUtils.isNotEmpty(pathCode2) && StringUtils.isEmpty(pathCode2)) {
                        result = 1;
                    }
                }
                return result;
            }
        };
    }
}
