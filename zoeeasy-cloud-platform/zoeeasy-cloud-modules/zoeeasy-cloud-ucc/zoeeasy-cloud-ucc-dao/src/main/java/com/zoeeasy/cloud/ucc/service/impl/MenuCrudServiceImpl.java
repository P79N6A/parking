package com.zoeeasy.cloud.ucc.service.impl;


import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.ucc.domain.MenuEntity;
import com.zoeeasy.cloud.ucc.mapper.MenuMapper;
import com.zoeeasy.cloud.ucc.service.MenuCrudService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author walkman
 * @since 2018-08-20
 */
@Service("menuCrudService")
public class MenuCrudServiceImpl extends CrudServiceImpl<MenuMapper, MenuEntity> implements MenuCrudService {

    /**
     * 获取所有静态菜单
     *
     * @return
     */
    @Override
    public List<MenuEntity> getAllStaticMenu() {
        return this.baseMapper.getAllStaticMenu();
    }

    /**
     * 获取所有宿主端静态菜单
     *
     * @return
     */
    @Override
    public List<MenuEntity> getAllHostSideStaticMenu() {
        return this.baseMapper.getAllHostSideStaticMenu();
    }

    /**
     * 获取所有租户端静态菜单
     *
     * @return
     */
    @Override
    public List<MenuEntity> getAllTenancySideStaticMenu() {
        return this.baseMapper.getAllTenancySideStaticMenu();
    }


}
