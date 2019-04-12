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
import com.zoeeasy.cloud.pms.specialvehicle.WhiteListService;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.WhiteListQueryPagedResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.WhiteListResultDto;
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
 * 白名单
 *
 * @date: 2018/10/16.
 * @author：zm
 */
@Api(value = "白名单Api", tags = "白名单Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/white")
@ApiVersion(1)
@ApiSigned
@RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_WHITELIST)
public class WhiteListController {

    @Autowired
    private WhiteListService whiteListService;

    @ApiOperation(value = "添加白名单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/add")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_WHITELIST_CREATE)
    @AuditLog(title = "白名单Api", value = "添加白名单", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto addWhiteList(@RequestBody WhiteListAddRequestDto requestDto) {
        return whiteListService.addWhiteList(requestDto);
    }

    @ApiOperation(value = "删除白名单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/delete")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_WHITELIST_DELETE)
    @AuditLog(title = "白名单Api", value = "删除白名单", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteWhiteList(@RequestBody WhiteListDeleteRequestDto requestDto) {
        return whiteListService.deleteWhiteList(requestDto);
    }

    @ApiOperation(value = "修改白名单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/update")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_WHITELIST_EDIT)
    @AuditLog(title = "白名单Api", value = "修改白名单", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto updateWhiteList(@RequestBody WhiteListUpdateRequestDto requestDto) {
        return whiteListService.updateWhiteList(requestDto);
    }

    @ApiOperation(value = "获取白名单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = WhiteListResultDto.class)
    @PostMapping(value = "/get")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_WHITELIST_VIEW)
    public ObjectResultDto<WhiteListResultDto> getWhiteList(@RequestBody WhiteListGetRequestDto requestDto) {
        return whiteListService.getWhiteList(requestDto);
    }

    @ApiOperation(value = "分页获取白名单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = WhiteListQueryPagedResultDto.class)
    @PostMapping(value = "/listpage")
    public PagedResultDto<WhiteListQueryPagedResultDto> getGaragePagedList(@RequestBody WhiteListQueryPagedRequestDto requestDto) {
        return whiteListService.getWhiteListPagedList(requestDto);
    }

}
