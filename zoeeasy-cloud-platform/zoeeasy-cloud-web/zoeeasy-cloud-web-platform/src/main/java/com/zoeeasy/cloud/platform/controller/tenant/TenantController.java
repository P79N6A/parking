package com.zoeeasy.cloud.platform.controller.tenant;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.core.annotation.AuditLog;
import com.zoeeasy.cloud.core.cst.PermissionDefinitions;
import com.zoeeasy.cloud.core.enums.BusinessType;
import com.zoeeasy.cloud.core.enums.OperatorType;
import com.zoeeasy.cloud.ucc.tenant.TenantService;
import com.zoeeasy.cloud.ucc.tenant.dto.request.*;
import com.zoeeasy.cloud.ucc.tenant.dto.result.TenantAccountCheckResultDto;
import com.zoeeasy.cloud.ucc.tenant.dto.result.TenantCheckResultDto;
import com.zoeeasy.cloud.ucc.tenant.dto.result.TenantListResultDto;
import com.zoeeasy.cloud.ucc.tenant.dto.result.TenantResultDto;
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
 * 商户管理API
 */
@RestController
@RequestMapping("/cloud/tenant")
@Api(tags = "商户管理", value = "商户管理API", produces = MediaType.APPLICATION_JSON_VALUE)
@ApiVersion(1)
@ApiSigned
@RequiresPermissions(value = {
        PermissionDefinitions.Host.ADMIN_TENANT,
        PermissionDefinitions.Host.ADMIN_TENANT_CREATE,
        PermissionDefinitions.Host.ADMIN_TENANT_EDIT,
        PermissionDefinitions.Host.ADMIN_TENANT_VIEW,
        PermissionDefinitions.Host.ADMIN_TENANT_DELETE,
        PermissionDefinitions.Host.ADMIN_TENANT_LOCK,
        PermissionDefinitions.Host.ADMIN_TENANT_RESET,
        PermissionDefinitions.Host.ADMIN_TENANT_UNLOCK,
        PermissionDefinitions.Host.ADMIN_TENANT_CANCEL
}, logical = Logical.OR)
public class TenantController {

    @Autowired
    private TenantService tenantService;

    /**
     * 检查商户名称是否可用
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "检查商户名称是否可用", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = TenantCheckResultDto.class)
    @PostMapping(value = "/checkTenant")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(PermissionDefinitions.Host.ADMIN_TENANT_CREATE)
    public ObjectResultDto<TenantCheckResultDto> checkTenant(@RequestBody TenantCheckRequestDto requestDto) {
        return this.tenantService.checkTenant(requestDto);
    }

    /**
     * 检查商户管理员是否可用
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "检查商户管理员是否可用", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = TenantAccountCheckResultDto.class)
    @PostMapping(value = "/checkAccount")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(PermissionDefinitions.Host.ADMIN_TENANT_CREATE)
    public ObjectResultDto<TenantAccountCheckResultDto> checkTenantAccount(@RequestBody TenantAccountCheckRequestDto requestDto) {
        return this.tenantService.checkTenantAccount(requestDto);
    }

    /**
     * 商户列表
     *
     * @param requestDto requestDto
     * @return
     */
    @ApiOperation(value = "商户列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = TenantListResultDto.class)
    @PostMapping(value = "/list")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public PagedResultDto<TenantListResultDto> getTenantList(@RequestBody TenantPagedListRequestDto requestDto) {
        return tenantService.getTenantList(requestDto);
    }

    /**
     * 商户详情
     *
     * @param requestDto requestDto
     * @return
     */
    @ApiOperation(value = "商户详情", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = TenantResultDto.class)
    @PostMapping(value = "/get")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(value = {
            PermissionDefinitions.Host.ADMIN_TENANT_VIEW,
            PermissionDefinitions.Host.ADMIN_TENANT_EDIT
    }, logical = Logical.OR)
    public ObjectResultDto<TenantResultDto> getTenant(@RequestBody TenantGetRequestDto requestDto) {
        return tenantService.getTenant(requestDto);
    }

    /**
     * 创建商户
     *
     * @param requestDto requestDto
     * @return
     */
    @ApiOperation(value = "创建商户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/create")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(PermissionDefinitions.Host.ADMIN_TENANT_CREATE)
    @AuditLog(title = "商户管理", value = "创建商户", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto createTenant(@RequestBody TenantCreateRequestDto requestDto) {
        return tenantService.createTenant(requestDto);
    }

    /**
     * 编辑商户
     *
     * @param requestDto requestDto
     * @return
     */
    @ApiOperation(value = "编辑商户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/edit")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(PermissionDefinitions.Host.ADMIN_TENANT_EDIT)
    @AuditLog(title = "商户管理", value = "编辑商户", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto editTenant(@RequestBody TenantEditRequestDto requestDto) {
        return tenantService.editTenant(requestDto);
    }

    /**
     * 删除商户
     *
     * @param requestDto requestDto
     * @return
     */
    @ApiOperation(value = "删除商户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/delete")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(PermissionDefinitions.Host.ADMIN_TENANT_DELETE)
    @AuditLog(title = "商户管理", value = "删除商户", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteTenant(@RequestBody TenantDeleteRequestDto requestDto) {
        return tenantService.deleteTenant(requestDto);
    }

    /**
     * 锁定商户
     *
     * @param requestDto requestDto
     * @return
     */
    @ApiOperation(value = "锁定商户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/lock")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(PermissionDefinitions.Host.ADMIN_TENANT_LOCK)
    @AuditLog(title = "商户管理", value = "锁定商户", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto lockTenant(@RequestBody TenantLockRequestDto requestDto) {
        return this.tenantService.lockTenant(requestDto);
    }

    /**
     * 解锁商户
     *
     * @param requestDto requestDto
     * @return
     */
    @ApiOperation(value = "解锁商户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/unlock")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(PermissionDefinitions.Host.ADMIN_TENANT_UNLOCK)
    @AuditLog(title = "商户管理", value = "解锁商户", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto unlockTenant(@RequestBody TenantUnlockRequestDto requestDto) {
        return this.tenantService.unlockTenant(requestDto);
    }

    /**
     * 注销商户
     *
     * @param requestDto requestDto
     * @return
     */
    @ApiOperation(value = "注销商户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/cancel")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(PermissionDefinitions.Host.ADMIN_TENANT_CANCEL)
    @AuditLog(title = "商户管理", value = "注销商户", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto cancelTenant(@RequestBody TenantCancelRequestDto requestDto) {
        return this.tenantService.cancelTenant(requestDto);
    }

    /**
     * 重置租户管理员密码
     *
     * @param requestDto requestDto
     * @return
     */
    @ApiOperation(value = "重置租户管理员密码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/reset")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(PermissionDefinitions.Host.ADMIN_TENANT_RESET)
    @AuditLog(title = "商户管理", value = "重置租户管理员密码", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto resetTenantPassword(@RequestBody TenantPasswordResetRequestDto requestDto) {
        return this.tenantService.resetTenantPassword(requestDto);
    }

}
