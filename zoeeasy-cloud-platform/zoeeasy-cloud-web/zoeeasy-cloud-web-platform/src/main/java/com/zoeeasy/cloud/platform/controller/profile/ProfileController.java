package com.zoeeasy.cloud.platform.controller.profile;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.shiro.util.ShiroUtils;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.core.annotation.AuditLog;
import com.zoeeasy.cloud.core.enums.BusinessType;
import com.zoeeasy.cloud.core.enums.OperatorType;
import com.zoeeasy.cloud.ucc.user.UserService;
import com.zoeeasy.cloud.ucc.user.dto.UserPermissionListGetRequestDto;
import com.zoeeasy.cloud.ucc.user.dto.request.UserPasswordModifyRequestDto;
import com.zoeeasy.cloud.ucc.user.dto.request.UserPortraitUpdateRequestDto;
import com.zoeeasy.cloud.ucc.user.dto.request.UserProfileGetRequestDto;
import com.zoeeasy.cloud.ucc.user.dto.result.UserPermissionListResultDto;
import com.zoeeasy.cloud.ucc.user.dto.result.UserProfileResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录用户个人信息控制器
 *
 * @author walkman
 */
@Api(tags = "用户信息", value = "用户信息Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/profile")
@ApiVersion(1)
@ApiSigned
@RequiresAuthentication
public class ProfileController {

    @Autowired
    private UserService userService;

    /**
     * 修改密码
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "修改个人密码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/modifyPassword")
    @AuditLog(title = "用户信息", value = "修改个人密码", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto modifyPassword(@RequestBody UserPasswordModifyRequestDto requestDto) {
        ResultDto resultDto = userService.modifyPassword(requestDto);
        if (resultDto.isSuccess()) {
            //修改成功后退出登录
            Subject subject = ShiroUtils.getSubject();
            subject.logout();
        }
        return resultDto;
    }

    /**
     * 用户更新个人头像
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "更新个人头像", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/portrait")
    @AuditLog(title = "用户信息", value = "更新个人头像", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto updateUserPortrait(@RequestBody UserPortraitUpdateRequestDto requestDto) {
        return this.userService.updateUserPortrait(requestDto);
    }

    /**
     * 获取用户个人信息
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取用户个人信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/me")
    public ObjectResultDto<UserProfileResultDto> getUserProfile(@RequestBody UserProfileGetRequestDto requestDto) {
        return this.userService.getUserProfile(requestDto);
    }

    /**
     * 获取登录用户权限列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取用户权限列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/permissions")
    public ObjectResultDto<UserPermissionListResultDto> getUserPermissionList(@RequestBody UserPermissionListGetRequestDto requestDto) {
        return this.userService.getUserPermissionList(requestDto);
    }

}
