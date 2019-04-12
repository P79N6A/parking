package com.zoeeasy.cloud.platform.controller.organization;

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
import com.zoeeasy.cloud.ucc.organization.OrganizationService;
import com.zoeeasy.cloud.ucc.organization.dto.*;
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
@RequestMapping("/cloud/organization")
@Api(tags = "部门管理", value = "部门管理API", produces = MediaType.APPLICATION_JSON_VALUE)
@ApiVersion(1)
@ApiSigned
@RequiresPermissions(value = {
        PermissionDefinitions.Common.ADMIN_USER_ORGANIZATION,
        PermissionDefinitions.Common.ADMIN_USER_ORGANIZATION_CREATE,
        PermissionDefinitions.Common.ADMIN_USER_ORGANIZATION_EDIT,
        PermissionDefinitions.Common.ADMIN_USER_ORGANIZATION_DELETE,
        PermissionDefinitions.Common.ADMIN_USER_ORGANIZATION_VIEW
}, logical = Logical.OR)
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    /**
     * 获取部门列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取部门列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = OrganizationListResultDto.class)
    @PostMapping(value = "/list")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public PagedResultDto<OrganizationListResultDto> getOrganizationList(@RequestBody OrganizationPagedRequestDto requestDto) {
        return this.organizationService.getOrganizationList(requestDto);
    }

    /**
     * 获取部门
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取部门列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = OrganizationResultDto.class)
    @PostMapping(value = "/get")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(value = {
            PermissionDefinitions.Common.ADMIN_USER_ORGANIZATION_VIEW,
            PermissionDefinitions.Common.ADMIN_USER_ORGANIZATION_EDIT
    }, logical = Logical.OR)
    public ObjectResultDto<OrganizationResultDto> getOrganization(@RequestBody OrganizationGetRequestDto requestDto) {
        return this.organizationService.getOrganization(requestDto);
    }

    /**
     * 创建部门
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "创建部门", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/create")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(PermissionDefinitions.Common.ADMIN_USER_ORGANIZATION_CREATE)
    @AuditLog(title = "部门管理", value = "创建部门", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto createOrganization(@RequestBody OrganizationCreateRequestDto requestDto) {
        return this.organizationService.createOrganization(requestDto);
    }

    /**
     * 编辑部门
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "编辑部门", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/edit")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(PermissionDefinitions.Common.ADMIN_USER_ORGANIZATION_EDIT)
    @AuditLog(title = "部门管理", value = "编辑部门", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto editOrganization(@RequestBody OrganizationEditRequestDto requestDto) {
        return this.organizationService.editOrganization(requestDto);
    }

    /**
     * 删除部门
     *
     * @param requestDto
     * @return
     */
    @PostMapping(value = "/delete")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @RequiresPermissions(PermissionDefinitions.Common.ADMIN_USER_ORGANIZATION_DELETE)
    @AuditLog(title = "部门管理", value = "删除部门", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteOrganization(@RequestBody OrganizationDeleteRequestDto requestDto) {
        return this.organizationService.deleteOrganization(requestDto);
    }

    /**
     * 获取部门树列表
     *
     * @param requestDto
     * @return
     */
    @PostMapping(value = "/tree")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ListResultDto<OrganizationTreeResultDto> getOrganizationTree(@RequestBody OrganizationTreeRequestDto requestDto) {
        return this.organizationService.getOrganizationTree(requestDto);
    }

}
