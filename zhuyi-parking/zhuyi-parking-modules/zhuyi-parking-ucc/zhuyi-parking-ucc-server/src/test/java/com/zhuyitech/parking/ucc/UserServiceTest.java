package com.zhuyitech.parking.ucc;


import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.ucc.dto.result.user.UserResultDto;
import com.zhuyitech.parking.ucc.service.api.UserService;
import com.zhuyitech.parking.ucc.user.dto.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
//@ScapegoatDubboApplicationConfiguration(QuickDubboBootstrap.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void userAddTest() {

        UserAddRequestDto userAddRequestDto = new UserAddRequestDto();
        userAddRequestDto.setUsername("cacotopia");
        userAddRequestDto.setNickname("CACOTOPIA");
        userAddRequestDto.setEmailAddress("cacotopia@126.com");
        userAddRequestDto.setPhoneNumber("13588361944");
        userAddRequestDto.setPortrait("porta");
        userAddRequestDto.setPassword("123456");
        ResultDto resultDto = userService.addUser(userAddRequestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void userGetTest() {

        UserGetRequestDto userGetRequestDto = new UserGetRequestDto();
        userGetRequestDto.setUsername("cacotopia");
        ObjectResultDto<UserResultDto> resultDto = userService.getUser(userGetRequestDto);
        assertTrue(resultDto.isSuccess());
        assertNotNull(resultDto.getData());
    }

    @Test
    public void userUpdateTest() {

        UserGetRequestDto userGetRequestDto = new UserGetRequestDto();
        userGetRequestDto.setUsername("cacotopia");
        ObjectResultDto<UserResultDto> resultDto = userService.getUser(userGetRequestDto);
        assertTrue(resultDto.isSuccess());
        assertNotNull(resultDto.getData());
        if (resultDto.getData() != null) {

            UserResultDto userResultDto = resultDto.getData();
            UserUpdateRequestDto userUpdateRequestDto = new UserUpdateRequestDto();
            userUpdateRequestDto.setId(userResultDto.getId());
            userUpdateRequestDto.setPortrait("");
            userUpdateRequestDto.setNickname("测试昵称");
            ResultDto resultDtoUpdate = userService.updateUser(userUpdateRequestDto);
            assertTrue(resultDtoUpdate.isSuccess());
        }
    }

    @Test
    public void userListGetTest() {

        UserListGetRequestDto userListGetRequestDto = new UserListGetRequestDto();
        ListResultDto<UserResultDto> listResultDto = userService.getUserList(userListGetRequestDto);
        assertTrue(listResultDto.isSuccess());
        assertFalse(listResultDto.getItems().isEmpty());
    }

    @Test
    public void userPagedListGetTest() {
        UserQueryPagedResultRequestDto userQueryPagedResultRequestDto = new UserQueryPagedResultRequestDto();
        PagedResultDto<UserResultDto> pagedResultDto = userService.getUserPagedList(userQueryPagedResultRequestDto);
        assertTrue(pagedResultDto.isSuccess());
        assertFalse(pagedResultDto.getItems().isEmpty());
    }

    /**
     * 修改密码
     */
    @Test
    public void modifyPasswordTest() {

        UserPasswordModifyRequestDto userPasswordModifyRequestDto = new UserPasswordModifyRequestDto();
//        userPasswordModifyRequestDto.setUserId(25L);
        userPasswordModifyRequestDto.setOldPassword("123456");
        userPasswordModifyRequestDto.setNewPassword("888888");
        ResultDto resultDto = userService.modifyPassword(userPasswordModifyRequestDto);
        assertTrue(resultDto.isSuccess());
    }

    /**
     * 获取用户所有父菜单列表
     */
    @Test
    public void getUserMenuParentListTest() {
//        ListResultDto<UserMenuResultDto> getUserMenuParentList(UserMenuListGetRequestDto requestDto);
    }

    @Test
    public void userDeleteTest() {

        UserDeleteRequestDto requestDto = new UserDeleteRequestDto();
        requestDto.setId(2L);
        ResultDto resultDto = userService.deleteUser(requestDto);
        assertTrue(resultDto.isSuccess());
        UserGetRequestDto userGetRequestDto = new UserGetRequestDto();
        userGetRequestDto.setId(2L);
        ObjectResultDto<UserResultDto> resultDto2 = userService.getUser(userGetRequestDto);
        assertTrue(resultDto2.isSuccess());
    }

    /**
     * 测试用户修改头像的方法
     */
    @Test
    public void userUpdateProtrait() {
        UserPortraitUpdateRequestDto user = new UserPortraitUpdateRequestDto();
        user.setPortraitUrl("123");
//        user.setUserId(21L);
        ResultDto resultDto = userService.updateUserPortrait(user);
        System.out.println(resultDto);
    }

}
