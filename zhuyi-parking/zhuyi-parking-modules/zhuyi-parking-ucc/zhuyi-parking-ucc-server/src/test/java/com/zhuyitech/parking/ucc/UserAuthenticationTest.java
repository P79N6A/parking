package com.zhuyitech.parking.ucc;

import com.zhuyitech.parking.ucc.service.api.UserAuthenticationApplyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//@ScapegoatDubboApplicationConfiguration(QuickDubboBootstrap.class)
public class UserAuthenticationTest {

    @Autowired
    private UserAuthenticationApplyService userAuthenticationApplyService;

    /**
     * 用户身份第三方认证
     */
    @Test
    public void authTest() {
//        UserAuthCheckRequestDto userAuthRequestDto = new UserAuthCheckRequestDto();
//        userAuthRequestDto.setRealName("余志诚");
//        userAuthRequestDto.setCardNo("41152619910201389X");
//        userAuthenticationApplyService.checkAuthApply(userAuthRequestDto);
    }
}
