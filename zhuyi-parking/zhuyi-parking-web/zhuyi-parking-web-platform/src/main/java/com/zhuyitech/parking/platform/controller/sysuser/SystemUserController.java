package com.zhuyitech.parking.platform.controller.sysuser;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.common.annotation.SystemLog;
import com.zhuyitech.parking.ucc.dto.result.user.UserResultDto;
import com.zhuyitech.parking.ucc.service.api.UserService;
import com.zhuyitech.parking.ucc.user.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统用户控制器
 *
 * @author walkman
 */
@Api(value = "用户Api", description = "系统用户Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/sysuser")
public class SystemUserController {

    @Autowired
    private UserService userService;

    /**
     * 新增用户
     *
     * @param requestDto
     * @return
     */
    @SystemLog("新增用户")
    @ApiOperation(value = "新增用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultDto addUser(UserAddRequestDto requestDto) {
        return userService.addUser(requestDto);
    }

    /**
     * 更新用户
     *
     * @param requestDto
     * @return
     */
    @SystemLog("更新用户")
    @ApiOperation(value = "更新用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultDto updateUser(UserUpdateRequestDto requestDto) {
        return userService.updateUser(requestDto);
    }

    /**
     * 删除用户
     *
     * @param requestDto
     * @return
     */
    @SystemLog("更新用户")
    @ApiOperation(value = "更新用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultDto deleteUser(UserDeleteRequestDto requestDto) {
        return userService.deleteUser(requestDto);
    }

    /**
     * 获取用户
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserResultDto.class)
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ObjectResultDto<UserResultDto> getUser(UserGetRequestDto requestDto) {
        return userService.getUser(requestDto);
    }

    /**
     * 获取用户列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserResultDto.class)
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ListResultDto<UserResultDto> getUserList(UserListGetRequestDto requestDto) {
        return userService.getUserList(requestDto);
    }

    /**
     * 分页查询用户列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页查询用户列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserResultDto.class)
    @RequestMapping(value = "/listpage", method = RequestMethod.POST)
    public PagedResultDto<UserResultDto> getUserPagedList(UserQueryPagedResultRequestDto requestDto) {
        return userService.getUserPagedList(requestDto);
    }

}
