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
import com.zoeeasy.cloud.pms.specialvehicle.PacketApproveService;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketApproveGetResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketApproveResultDto;
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
@Api(value = "包期取消Api", tags = "包期取消Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/packetApprove")
@ApiVersion(1)
@ApiSigned
@RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETCANCEL)
public class PacketApproveController {

    @Autowired
    private PacketApproveService packetApproveService;

    @ApiOperation(value = "申请取消包期", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/add")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETCANCEL_APPLY)
    @AuditLog(title = "包期取消Api", value = "申请取消包期", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto applyCancelPacket(@RequestBody ApplyCancelPacketRequestDto requestDto) {
        return packetApproveService.applyCancelPacket(requestDto);
    }

    @ApiOperation(value = "获取取消包期车信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = PacketApproveGetResultDto.class)
    @PostMapping(value = "/get")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETCANCEL_VIEW)
    public ObjectResultDto<PacketApproveGetResultDto> getPacketApprove(@RequestBody PacketApproveGetRequestDto requestDto) {
        return packetApproveService.getPacketApprove(requestDto);
    }

    @ApiOperation(value = "审核取消包期", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/check")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETCANCEL_APPROVE)
    @AuditLog(title = "包期取消Api", value = "申请取消包期", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto checkPacketApprove(@RequestBody CheckPacketApproveRequestDto requestDto) {
        return packetApproveService.checkPacketApprove(requestDto);
    }

    @ApiOperation(value = "分页获取取消包期申请", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = PacketApproveResultDto.class)
    @PostMapping(value = "/listpage")
    public PagedResultDto<PacketApproveResultDto> getPacketApprovePagedList(@RequestBody CancelPacketApplyQueryPagedRequestDto requestDto) {
        return packetApproveService.getPacketApprovePagedList(requestDto);
    }

    @ApiOperation(value = "删除取消包期申请", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/delete")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETCANCEL_DELETE)
    @AuditLog(title = "包期取消Api", value = "删除取消包期申请", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deletePacketApprove(@RequestBody PacketApproveDeleteRequestDto requestDto) {
        return packetApproveService.deletePacketApprove(requestDto);
    }

}
