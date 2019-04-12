package com.zoeeasy.cloud.platform.controller.user;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.core.annotation.AuditLog;
import com.zoeeasy.cloud.core.cst.PermissionDefinitions;
import com.zoeeasy.cloud.core.enums.BusinessType;
import com.zoeeasy.cloud.core.enums.OperatorType;
import com.zoeeasy.cloud.ucc.role.RoleService;
import com.zoeeasy.cloud.ucc.role.dto.RoleLookupListGetRequestDto;
import com.zoeeasy.cloud.ucc.user.UserService;
import com.zoeeasy.cloud.ucc.user.dto.request.*;
import com.zoeeasy.cloud.ucc.user.dto.result.UserDetailResultDto;
import com.zoeeasy.cloud.ucc.user.dto.result.UserListResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理API
 */
@RestController
@RequestMapping("/cloud/user")
@Api(tags = "用户管理", value = "用户管理API", produces = MediaType.APPLICATION_JSON_VALUE)
@ApiVersion(1)
@ApiSigned
@RequiresPermissions(value = {
        PermissionDefinitions.Common.ADMIN_USER_EMPLOYEE_CREATE,
        PermissionDefinitions.Common.ADMIN_USER_EMPLOYEE_EDIT,
        PermissionDefinitions.Common.ADMIN_USER_EMPLOYEE_VIEW,
        PermissionDefinitions.Common.ADMIN_USER_EMPLOYEE_DELETE,
        PermissionDefinitions.Common.ADMIN_USER_EMPLOYEE_LOCK,
        PermissionDefinitions.Common.ADMIN_USER_EMPLOYEE_UNLOCK,
        PermissionDefinitions.Common.ADMIN_USER_EMPLOYEE_RESET,
        PermissionDefinitions.Common.ADMIN_USER_EMPLOYEE_CANCEL,
}, logical = Logical.OR)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 创建用户
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "创建用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/create")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(PermissionDefinitions.Common.ADMIN_USER_EMPLOYEE_CREATE)
    @AuditLog(title = "用户管理", value = "创建用户", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto createUser(@RequestBody UserCreateRequestDto requestDto) {
        return this.userService.createUser(requestDto);
    }

    /**
     * 编辑用户
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "编辑用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/edit")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(PermissionDefinitions.Common.ADMIN_USER_EMPLOYEE_EDIT)
    @AuditLog(title = "用户管理", value = "编辑用户", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto editUser(@RequestBody UserEditRequestDto requestDto) {
        return this.userService.editUser(requestDto);
    }

    /**
     * 重置用户密码
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "重置用户密码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/resetPassword")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(PermissionDefinitions.Common.ADMIN_USER_EMPLOYEE_RESET)
    @AuditLog(title = "用户管理", value = "重置用户密码", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto resetUserPassword(@RequestBody UserPasswordResetRequestDto requestDto) {
        return this.userService.resetUserPassword(requestDto);
    }

    /**
     * 锁定用户
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "锁定用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/lock")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(PermissionDefinitions.Common.ADMIN_USER_EMPLOYEE_LOCK)
    @AuditLog(title = "用户管理", value = "锁定用户", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto lockUser(@RequestBody UserLockRequestDto requestDto) {
        return this.userService.lockUser(requestDto);
    }

    /**
     * 解锁用户
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "解锁用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/unlock")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(PermissionDefinitions.Common.ADMIN_USER_EMPLOYEE_UNLOCK)
    @AuditLog(title = "用户管理", value = "解锁用户", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto unlockUser(@RequestBody UserUnlockRequestDto requestDto) {
        return this.userService.unlockUser(requestDto);
    }

    /**
     * 注销用户
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "注销用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/close")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(PermissionDefinitions.Common.ADMIN_USER_EMPLOYEE_CANCEL)
    @AuditLog(title = "用户管理", value = "注销用户", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto closeUser(@RequestBody UserCloseRequestDto requestDto) {
        return this.userService.closeUser(requestDto);
    }

    /**
     * 删除用户
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "删除用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/delete")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(PermissionDefinitions.Common.ADMIN_USER_EMPLOYEE_DELETE)
    @AuditLog(title = "用户管理", value = "删除用户", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteUser(@RequestBody UserDeleteRequestDto requestDto) {
        return this.userService.deleteUser(requestDto);
    }

    /**
     * 获取用户
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserDetailResultDto.class)
    @PostMapping(value = "/get")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(value = {
            PermissionDefinitions.Common.ADMIN_USER_EMPLOYEE_VIEW,
            PermissionDefinitions.Common.ADMIN_USER_EMPLOYEE_EDIT
    }, logical = Logical.OR)
    public ObjectResultDto<UserDetailResultDto> getUser(@RequestBody UserGetRequestDto requestDto) {
        return this.userService.getUser(requestDto);
    }

    /**
     * 分页查询用户列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页查询用户列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserListResultDto.class)
    @PostMapping(value = "/pagelist")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public PagedResultDto<UserListResultDto> getUserPagedList(@RequestBody UserPagedListRequestDto requestDto) {
        return this.userService.getUserPagedList(requestDto);
    }

    /**
     * 获取角色列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取角色列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/roles")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(value = {PermissionDefinitions.Common.ADMIN_USER_EMPLOYEE_VIEW,
            PermissionDefinitions.Common.ADMIN_USER_EMPLOYEE_EDIT}, logical = Logical.OR)
    public ListResultDto<ComboboxItemDto> getRoleList(@RequestBody RoleLookupListGetRequestDto requestDto) {
        return this.roleService.getRoleLookupList(requestDto);
    }

}
