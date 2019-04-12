package com.zoeeasy.cloud.ucc.navigation.dto;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * Provides infrastructure to set navigation.
 *
 * @author walkman
 */
public interface IHasMenuItemDefinitions {

    /**
     * List of menu items
     */
    List<MenuItemDefinition> getItems();

    /**
     * 通过菜单编号搜索
     *
     * @param source
     * @param code
     * @return
     */
    static MenuItemDefinition getItemByCode(IHasMenuItemDefinitions source, String code) {
        MenuItemDefinition item = getItemByNameOrNull(source, code);
        if (item == null) {
            throw new IllegalArgumentException("source");
        }
        return item;
    }

    /**
     * 通过菜单编号搜索
     *
     * @param source
     * @param code
     * @return
     */
    static MenuItemDefinition getItemByNameOrNull(IHasMenuItemDefinitions source, String code) {
        if (source == null) {
            throw new IllegalArgumentException("source");
        }
        if (CollectionUtils.isEmpty(source.getItems())) {
            return null;
        }
        for (MenuItemDefinition subItem : source.getItems()) {
            if (subItem.getCode().equals(code)) {
                return subItem;
            }
            MenuItemDefinition subItemSearchResult = getItemByNameOrNull(subItem, code);
            if (subItemSearchResult != null) {
                return subItemSearchResult;
            }
        }
        return null;
    }

}
