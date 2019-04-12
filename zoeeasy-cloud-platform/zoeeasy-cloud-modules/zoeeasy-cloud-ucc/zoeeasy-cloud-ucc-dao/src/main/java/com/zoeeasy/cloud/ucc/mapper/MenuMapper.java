package com.zoeeasy.cloud.ucc.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.ucc.domain.MenuEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author walkman
 * @since 2017-11-21
 */
@Repository("menuMapper")
public interface MenuMapper extends BaseMapper<MenuEntity> {

    /**
     * 获取所有静态菜单
     *
     * @return
     */
    @SqlParser(filter = true)
    List<MenuEntity> getAllStaticMenu();

    /**
     * 获取所有宿主端静态菜单
     *
     * @return
     */
    @SqlParser(filter = true)
    List<MenuEntity> getAllHostSideStaticMenu();

    /**
     * 获取所有租户端静态菜单
     *
     * @return
     */
    @SqlParser(filter = true)
    List<MenuEntity> getAllTenancySideStaticMenu();

}
