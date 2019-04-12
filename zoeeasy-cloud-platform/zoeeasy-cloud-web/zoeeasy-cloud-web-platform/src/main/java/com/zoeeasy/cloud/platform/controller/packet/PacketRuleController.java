package com.zoeeasy.cloud.platform.controller.packet;

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
import com.zoeeasy.cloud.pms.specialvehicle.PacketRuleService;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketRuleListGetResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketRuleQueryPagedResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketRuleResultDto;
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
 * 包期规则
 *
 * @date: 2018/10/13.
 * @author：zm
 */
@Api(value = "包期规则Api", tags = "包期规则Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/rule")
@ApiVersion(1)
@ApiSigned
@RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETRULE)
public class PacketRuleController {

    @Autowired
    private PacketRuleService packetRuleService;

    @ApiOperation(value = "添加包期规则", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/add")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETRULE_CREATE)
    @AuditLog(title = "包期规则Api", value = "添加包期规则", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto addPacketRule(@RequestBody PacketRuleAddRequestDto requestDto) {
        return packetRuleService.addPacketRule(requestDto);
    }

    @ApiOperation(value = "删除包期规则", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/delete")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETRULE_DELETE)
    @AuditLog(title = "包期规则Api", value = "删除包期规则", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deletePacketRule(@RequestBody PacketRuleDeleteRequestDto requestDto) {
        return packetRuleService.deletePacketRule(requestDto);
    }

    @ApiOperation(value = "修改包期规则", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/update")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETRULE_EDIT)
    @AuditLog(title = "包期规则Api", value = "修改包期规则", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto updatePacketRule(@RequestBody PacketRuleUpdateRequestDto requestDto) {
        return packetRuleService.updatePacketRule(requestDto);
    }

    @ApiOperation(value = "获取包期规则", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = PacketRuleResultDto.class)
    @PostMapping(value = "/get")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETRULE_VIEW)
    public ObjectResultDto<PacketRuleResultDto> getPacketRule(@RequestBody PacketRuleGetRequestDto requestDto) {
        return packetRuleService.getPacketRule(requestDto);
    }

    @ApiOperation(value = "获取包期规则列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = PacketRuleListGetResultDto.class)
    @PostMapping(value = "/list")
    public ListResultDto<PacketRuleListGetResultDto> getPacketRule(@RequestBody PacketRuleListGetRequestDto requestDto) {
        return packetRuleService.getPacketRuleList(requestDto);
    }

    @ApiOperation(value = "分页获取包期规则列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = PacketRuleQueryPagedResultDto.class)
    @PostMapping(value = "/listpage")
    public PagedResultDto<PacketRuleQueryPagedResultDto> getGaragePagedList(@RequestBody PacketRuleQueryPagedRequestDto requestDto) {
        return packetRuleService.getPacketRulePagedList(requestDto);
    }

}
