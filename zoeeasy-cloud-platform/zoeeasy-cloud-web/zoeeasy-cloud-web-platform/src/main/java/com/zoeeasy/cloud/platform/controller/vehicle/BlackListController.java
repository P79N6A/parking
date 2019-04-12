package com.zoeeasy.cloud.platform.controller.vehicle;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.core.annotation.AuditLog;
import com.zoeeasy.cloud.core.cst.PermissionDefinitions;
import com.zoeeasy.cloud.core.enums.BusinessType;
import com.zoeeasy.cloud.core.enums.OperatorType;
import com.zoeeasy.cloud.pms.specialvehicle.BlackListService;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.BlackListGetResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.BlackListPagedResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by song on 2018/10/18.
 */
@Api(value = "黑名单Api", tags = "黑名单Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/blackList")
@ApiVersion(1)
@ApiSigned
@RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_BLACKLIST)
public class BlackListController {

    @Autowired
    private BlackListService blackListService;

    @ApiOperation(value = "添加黑名单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/add")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_BLACKLIST_CREATE)
    @AuditLog(title = "黑名单Api", value = "添加黑名单", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto addBlackList(@RequestBody BlackListAddRequestDto requestDto) {
        return blackListService.addBlackList(requestDto);
    }

    @ApiOperation(value = "修改黑名单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/update")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_BLACKLIST_EDIT)
    @AuditLog(title = "黑名单Api", value = "修改黑名单", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto updateBlackList(@RequestBody BlackListUpdateRequestDto requestDto) {
        return blackListService.updateBlackList(requestDto);
    }

    @ApiOperation(value = "获取黑名单详情", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = BlackListGetResultDto.class)
    @PostMapping(value = "/get")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_BLACKLIST_VIEW)
    public ObjectResultDto<BlackListGetResultDto> getBlackList(@RequestBody BlackListGetRequestDto requestDto) {
        return blackListService.getBlackList(requestDto);
    }

    @ApiOperation(value = "删除黑名单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/delete")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_BLACKLIST_DELETE)
    @AuditLog(title = "黑名单Api", value = "删除黑名单", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteBlackList(@RequestBody BlackListDeleteRequestDto requestDto) {
        return blackListService.deleteBlackList(requestDto);
    }

    @ApiOperation(value = "分页获取黑名单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = BlackListPagedResultDto.class)
    @PostMapping(value = "/pagelist")
    public PagedResultDto<BlackListPagedResultDto> getBlackListPagedList(@RequestBody BlackListQueryPagedRequestDto requestDto) {
        return blackListService.getBlackListPagedList(requestDto);
    }

}
