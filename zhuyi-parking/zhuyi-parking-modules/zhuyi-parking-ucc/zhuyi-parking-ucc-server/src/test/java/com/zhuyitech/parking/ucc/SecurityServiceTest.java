package com.zhuyitech.parking.ucc;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.common.enums.TerminateType;
import com.zhuyitech.parking.ucc.account.request.UserLoginRequestDto;
import com.zhuyitech.parking.ucc.user.dto.UserRetrievePasswordRequestDto;
import com.zhuyitech.parking.ucc.dto.result.user.UserLoginResultDto;
import com.zhuyitech.parking.ucc.service.api.SecurityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
//@ScapegoatDubboApplicationConfiguration(QuickDubboBootstrap.class)
public class SecurityServiceTest {

    @Autowired
    private SecurityService securityService;

    /**
     * 用户登录
     *
     * @return
     */
    @Test
    public void signInTest() {

        UserLoginRequestDto userLoginRequestDto = new UserLoginRequestDto();
        userLoginRequestDto.setUsername("administrator");
        userLoginRequestDto.setPassword("123456");
        userLoginRequestDto.setTerminateType(TerminateType.WEB.getValue());
        ObjectResultDto<UserLoginResultDto> objectResultDto = securityService.signIn(userLoginRequestDto);
        assertTrue(objectResultDto.isSuccess());
    }

    /**
     * 登录用户信息
     *
     * @return
     */
    @Test
    public void currentUserTest() {
//        ObjectResultDto<CurrentLoginUserResultDto> currentUser(CurrentLoginUserRequestDto requestDto);
    }

    /**
     * 用户退出
     *
     * @return
     */
    @Test
    public void signOutTest() {

//        ResultDto signOut(UserLogoutRequestDto requestDto);
    }

    /**
     * 测试用户找回密码时修改密码的方法
     */
    @Test
    public void retrievePassword() {
        UserRetrievePasswordRequestDto userRetrievePasswordRequestDto = new UserRetrievePasswordRequestDto();
        userRetrievePasswordRequestDto.setPhoneNumber("13111111111");
        userRetrievePasswordRequestDto.setPassword("456789");
        ResultDto resultDto = securityService.retrievePassword(userRetrievePasswordRequestDto);
        System.out.println(resultDto);
    }
}