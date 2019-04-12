package com.zoeeasy.cloud.ucc.navigation.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class MenuDefinition extends BaseDto implements IHasMenuItemDefinitions {

    /**
     * Unique code of the menu item in the application.
     * Can be used to find this menu item later.
     */
    private String code;

    /**
     * name of the menu item. Required.
     */
    private String name;

    /**
     * Can be used to store a custom object related to this menu item. Optional.
     */
    private Object customData;

    /**
     * Sub items of this menu item. Optional.
     */
    private List<MenuItemDefinition> items;

    /**
     * @param menuItem
     * @return
     */
    public MenuDefinition addItem(MenuItemDefinition menuItem) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(menuItem);
        return this;
    }

    /**
     * @param code
     */
    public void removeItem(String code) {
        items.removeIf(m -> m.getCode().equals(code));
    }

}
