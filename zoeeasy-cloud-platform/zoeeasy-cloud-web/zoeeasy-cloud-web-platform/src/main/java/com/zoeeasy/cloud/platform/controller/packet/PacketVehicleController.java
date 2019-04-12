package com.zoeeasy.cloud.platform.controller.packet;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.core.annotation.AuditLog;
import com.zoeeasy.cloud.core.cst.PermissionDefinitions;
import com.zoeeasy.cloud.core.enums.BusinessType;
import com.zoeeasy.cloud.core.enums.OperatorType;
import com.zoeeasy.cloud.pms.specialvehicle.PacketVehicleService;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.EndDateResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketReceiptResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketVehicleGetResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketVehiclePagedResultDto;
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
@Api(value = "包期车Api", tags = "包期车Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/packetVehicle")
@ApiVersion(1)
@ApiSigned
@RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETMANAGER)
public class PacketVehicleController {

    @Autowired
    private PacketVehicleService packetVehicleService;

    @ApiOperation(value = "添加包期车", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = PacketReceiptResultDto.class)
    @PostMapping(value = "/add")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETMANAGER_CREATE)
    @AuditLog(title = "包期车Api", value = "添加包期车", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ObjectResultDto<PacketReceiptResultDto> addPacketVehicle(@RequestBody PacketVehicleAddRequestDto requestDto) {
        return packetVehicleService.addPacketVehicle(requestDto);
    }

    @ApiOperation(value = "分页查询包期车", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = PacketVehiclePagedResultDto.class)
    @PostMapping(value = "/pagelist")
    public PagedResultDto<PacketVehiclePagedResultDto> getPacketVehiclePagedList(@RequestBody PacketVehiclePagedRequestDto requestDto) {
        return packetVehicleService.getPacketVehiclePagedList(requestDto);
    }

    @ApiOperation(value = "更改支付状态", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/updateTopUp")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETMANAGER_TOP_UP)
    @AuditLog(title = "包期车Api", value = "更改支付状态", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto updateTopUp(@RequestBody TopUpUpdateRequestDto requestDto) {
        return packetVehicleService.updateTopUp(requestDto);
    }

    @ApiOperation(value = "删除包期车", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/delete")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETMANAGER_DELETE)
    @AuditLog(title = "包期车Api", value = "删除包期车", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deletePacketVehicle(@RequestBody PacketVehicleDeleteRequestDto requestDto) {
        return packetVehicleService.deletePacketVehicle(requestDto);
    }

    @ApiOperation(value = "获取包期车详情", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = PacketVehicleGetResultDto.class)
    @PostMapping(value = "/get")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETMANAGER_VIEW)
    public ObjectResultDto<PacketVehicleGetResultDto> getPacketVehicle(@RequestBody PacketVehicleGetRequestDto requestDto) {
        return packetVehicleService.getPacketVehicle(requestDto);
    }

    @ApiOperation(value = "获取结束时间", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = EndDateResultDto.class)
    @PostMapping(value = "/getEndDate")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETMANAGER_VIEW)
    public ObjectResultDto<EndDateResultDto> getEndDate(@RequestBody EndDateRequestDto requestDto) {
        return packetVehicleService.getEndDate(requestDto);
    }

}
