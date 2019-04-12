package com.zoeeasy.cloud.tool.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.zoeeasy.cloud.tool.vesta.intf.IdService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
public class IdServiceTest {

    @Autowired
    private IdService idService;

    @Test
    public void testGenId() {

        int x = 0;
        do {
            x++;
            long id = idService.genId();
            System.out.print("value of x : " + id);
        } while (x < 1000);

    }

}
