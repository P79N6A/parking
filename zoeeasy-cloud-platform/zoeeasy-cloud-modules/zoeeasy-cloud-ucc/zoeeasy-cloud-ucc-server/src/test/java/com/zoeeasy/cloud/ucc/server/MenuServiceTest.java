package com.zoeeasy.cloud.ucc.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.zoeeasy.cloud.ucc.menu.MenuService;
import com.zoeeasy.cloud.ucc.menu.dto.request.MenuCreateRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
public class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void testAddMenu() {

        MenuCreateRequestDto requestDto = new MenuCreateRequestDto();
        requestDto.setCode("system.menu");
        requestDto.setUrl("/system/");
        requestDto.setType("menu");
        requestDto.setName("systemSetting");
        requestDto.setParentId(null);
        requestDto.setIcon("fa fa-font");
        requestDto.setRemarks("系统设置");
        requestDto.setShown(Boolean.TRUE);
//        menuService.addMenu(requestDto);
    }

    @Test
    public void testMenuGet() {

    }

    @Test
    public void testMenuDelete() {

    }

    @Test
    public void testMenuUpdate() {

    }
}
