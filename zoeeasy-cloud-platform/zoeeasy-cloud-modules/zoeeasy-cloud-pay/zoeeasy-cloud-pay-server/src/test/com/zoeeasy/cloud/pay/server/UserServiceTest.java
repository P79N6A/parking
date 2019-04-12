package com.zoeeasy.cloud.ucc.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
public class UserServiceTest {

//    @Autowired
//    private SysUserService sysUserService;

}
