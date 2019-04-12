package com.zoeeasy.cloud.ucc.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.ucc.domain.MenuEntity;

import java.util.List;

/**
 * @author walkman
 * @since 2018-08-20
 */
public interface MenuCrudService extends CrudService<MenuEntity> {

    /**
     * 获取所有静态菜单
     *
     * @return
     */
    List<MenuEntity> getAllStaticMenu();

    /**
     * 获取所有宿主端静态菜单
     *
     * @return
     */
    List<MenuEntity> getAllHostSideStaticMenu();

    /**
     * 获取所有租户端静态菜单
     *
     * @return
     */
    List<MenuEntity> getAllTenancySideStaticMenu();

}
