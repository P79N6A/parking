package com.zoeeasy.cloud.platform.controller.role;

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
import com.zoeeasy.cloud.ucc.role.dto.request.*;
import com.zoeeasy.cloud.ucc.role.dto.result.*;
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

@RestController
@RequestMapping("/cloud/role")
@Api(tags = "角色管理", value = "角色管理API", produces = MediaType.APPLICATION_JSON_VALUE)
@ApiVersion(1)
@ApiSigned
@RequiresPermissions(value = {
        PermissionDefinitions.Common.ADMIN_USER_ROLE_CREATE,
        PermissionDefinitions.Common.ADMIN_USER_ROLE_EDIT,
        PermissionDefinitions.Common.ADMIN_USER_ROLE_DELETE,
        PermissionDefinitions.Common.ADMIN_USER_ROLE_VIEW,
        PermissionDefinitions.Common.ADMIN_USER_ROLE_AUTHORIZE,
        PermissionDefinitions.Common.ADMIN_USER_ROLE_MEMBER
})
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 创建角色
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "创建角色", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/create")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(PermissionDefinitions.Common.ADMIN_USER_ROLE_CREATE)
    @AuditLog(title = "角色管理", value = "创建角色", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto createRole(@RequestBody RoleCreateRequestDto requestDto) {
        return this.roleService.createRole(requestDto);
    }

    /**
     * 编辑角色
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "编辑角色", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/edit")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(PermissionDefinitions.Common.ADMIN_USER_ROLE_EDIT)
    @AuditLog(title = "角色管理", value = "编辑角色", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto editRole(@RequestBody RoleEditRequestDto requestDto) {
        return this.roleService.editRole(requestDto);
    }

    /**
     * 删除角色
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "删除角色", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/delete")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(PermissionDefinitions.Common.ADMIN_USER_ROLE_DELETE)
    @AuditLog(title = "角色管理", value = "删除角色", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteRole(@RequestBody RoleDeleteRequestDto requestDto) {
        return this.roleService.deleteRole(requestDto);
    }

    /**
     * 获取角色
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取角色", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = RoleResultDto.class)
    @PostMapping(value = "/get")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(value = {
            PermissionDefinitions.Common.ADMIN_USER_ROLE_VIEW,
            PermissionDefinitions.Common.ADMIN_USER_ROLE_EDIT
    }, logical = Logical.OR)
    public ObjectResultDto<RoleResultDto> getRole(@RequestBody RoleGetRequestDto requestDto) {
        return this.roleService.getRole(requestDto);
    }

    /**
     * 分页获取角色列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取角色列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/pagelist")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public PagedResultDto<RoleListResultDto> getRolePagedList(@RequestBody RolePagedRequestDto requestDto) {
        return this.roleService.getRolePagedList(requestDto);
    }

    /**
     * 获取角色授予菜单树状视图
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取角色授予菜单树", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/menus")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ListResultDto<RoleMenuTreeItemResultDto> getRoleMenuTreeView(@RequestBody RoleMenuTreeViewGetRequestDto requestDto) {
        return this.roleService.getRoleMenuTreeView(requestDto);
    }

    /**
     * 获取角色授予权限树状视图
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取角色授予权限树", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/permissions")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ListResultDto<RolePermissionTreeItemResultDto> getRolePermissionTreeView(@RequestBody RolePermissionTreeViewGetRequestDto requestDto) {
        return this.roleService.getRolePermissionTreeView(requestDto);
    }

    /**
     * 角色授权
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "角色授权", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/authorize")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(PermissionDefinitions.Common.ADMIN_USER_ROLE_AUTHORIZE)
    @AuditLog(title = "角色管理", value = "角色授权", businessType = BusinessType.GRANT, operatorType = OperatorType.MANAGE)
    public ResultDto authorize(@RequestBody RoleAuthorizeRequestDto requestDto) {
        return this.roleService.authorize(requestDto);
    }

    /**
     * 获取角色成员
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取角色成员", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/members")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(PermissionDefinitions.Common.ADMIN_USER_ROLE_MEMBER)
    public PagedResultDto<RoleMemberListResultDto> getRolePermissionTreeView(@RequestBody RoleMemberListGetRequestDto requestDto) {
        return this.roleService.getRoleMemberList(requestDto);
    }
}
