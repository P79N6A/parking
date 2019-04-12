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
import com.zoeeasy.cloud.pms.specialvehicle.VisitVehicleService;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.VisitVehicleQueryPagedResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.VisitVehicleResultDto;
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
 * @date: 2018/10/18.
 * @author：zm
 */
@Api(value = "访客车Api", tags = "访客车Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/visit")
@ApiVersion(1)
@ApiSigned
@RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_VISITLIST)
public class VisitVehicleController {

    @Autowired
    private VisitVehicleService visitVehicleService;

    @ApiOperation(value = "添加访客车", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/add")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_VISITLIST_CREATE)
    @AuditLog(title = "访客车Api", value = "添加访客车", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto addVisitVehicle(@RequestBody VisitVehicleAddRequestDto requestDto) {
        return visitVehicleService.addVisitVehicle(requestDto);
    }

    @ApiOperation(value = "删除访客车", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/delete")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_VISITLIST_DELETE)
    @AuditLog(title = "访客车Api", value = "删除访客车", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteVisitVehicle(@RequestBody VisitVehicleDeleteRequestDto requestDto) {
        return visitVehicleService.deleteVisitVehicle(requestDto);
    }

    @ApiOperation(value = "修改访客车", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/update")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_VISITLIST_EDIT)
    @AuditLog(title = "访客车Api", value = "修改访客车", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto updateVisitVehicle(@RequestBody VisitVehicleUpdateRequestDto requestDto) {
        return visitVehicleService.updateVisitVehicle(requestDto);
    }

    @ApiOperation(value = "获取访客车", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = VisitVehicleResultDto.class)
    @PostMapping(value = "/get")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_VISITLIST_VIEW)
    public ObjectResultDto<VisitVehicleResultDto> getVisitVehicle(@RequestBody VisitVehicleGetRequestDto requestDto) {
        return visitVehicleService.getVisitVehicle(requestDto);
    }

    @ApiOperation(value = "分页获取访客车", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = VisitVehicleQueryPagedResultDto.class)
    @PostMapping(value = "/listpage")
    public PagedResultDto<VisitVehicleQueryPagedResultDto> getVisitVehiclePagedList(@RequestBody VisitVehicleQueryPagedRequestDto requestDto) {
        return visitVehicleService.getVisitVehiclePagedList(requestDto);
    }
}
