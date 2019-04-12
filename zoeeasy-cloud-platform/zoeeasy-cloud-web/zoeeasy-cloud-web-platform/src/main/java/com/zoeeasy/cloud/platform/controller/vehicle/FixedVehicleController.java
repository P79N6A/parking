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
import com.zoeeasy.cloud.pms.specialvehicle.FixedVehicleService;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.FixedVehicleQueryPagedResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.FixedVehicleResultDto;
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
 * 固定车
 *
 * @date: 2018/10/17.
 * @author：zm
 */
@Api(value = "固定车Api", tags = "固定车Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/fixed")
@ApiVersion(1)
@ApiSigned
@RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_FIXED)
public class FixedVehicleController {

    @Autowired
    private FixedVehicleService fixedVehicleService;

    @ApiOperation(value = "添加固定车", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/add")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_FIXED_CREATE)
    @AuditLog(title = "固定车Api", value = "添加固定车", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto addFixedVehicle(@RequestBody FixedVehicleAddRequestDto requestDto) {
        return fixedVehicleService.addFixedVehicle(requestDto);
    }

    @ApiOperation(value = "删除固定车", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/delete")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_FIXED_DELETE)
    @AuditLog(title = "固定车Api", value = "删除固定车", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteFixedVehicle(@RequestBody FixedVehicleDeleteRequestDto requestDto) {
        return fixedVehicleService.deleteFixedVehicle(requestDto);
    }

    @ApiOperation(value = "修改固定车", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/update")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_FIXED_EDIT)
    @AuditLog(title = "固定车Api", value = "修改固定车", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto updateFixedVehicle(@RequestBody FixedVehicleUpdateRequestDto requestDto) {
        return fixedVehicleService.updateFixedVehicle(requestDto);
    }

    @ApiOperation(value = "关联泊位", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/relevance")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_FIXED_RELEVANCE)
    @AuditLog(title = "固定车Api", value = "关联泊位", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto relevanceParkingLotId(@RequestBody RelevanceParkingLotIdRequestDto requestDto) {
        return fixedVehicleService.relevanceParkingLotId(requestDto);
    }

    @ApiOperation(value = "获取固定车", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = FixedVehicleResultDto.class)
    @PostMapping(value = "/get")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_FIXED_VIEW)
    public ObjectResultDto<FixedVehicleResultDto> getFixedVehicle(@RequestBody FixedVehicleGetRequestDto requestDto) {
        return fixedVehicleService.getFixedVehicle(requestDto);
    }

    @ApiOperation(value = "分页获取固定车", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = FixedVehicleQueryPagedResultDto.class)
    @PostMapping(value = "/listpage")
    public PagedResultDto<FixedVehicleQueryPagedResultDto> getWhiteListPagedList(@RequestBody FixedVehicleQueryPagedRequestDto requestDto) {
        return fixedVehicleService.getFixedVehiclePagedList(requestDto);
    }

}
