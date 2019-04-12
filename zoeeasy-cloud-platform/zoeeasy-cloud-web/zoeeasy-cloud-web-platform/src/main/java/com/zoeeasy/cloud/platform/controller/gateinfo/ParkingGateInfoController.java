package com.zoeeasy.cloud.platform.controller.gateinfo;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
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
import com.zoeeasy.cloud.pms.gate.ParkingGateInfoService;
import com.zoeeasy.cloud.pms.gate.dto.request.*;
import com.zoeeasy.cloud.pms.gate.dto.result.ParkingGateListGetResultDto;
import com.zoeeasy.cloud.pms.gate.dto.result.ParkingGateQueryPagedResultDto;
import com.zoeeasy.cloud.pms.gate.dto.result.ParkingGateResultDto;
import io.swagger.annotations.Api;
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
 * 出入口控制器
 *
 * @author Kane
 */
@Api(value = "出入口Api", tags = "出入口Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/parkinggateinfo")
@ApiVersion(1)
@ApiSigned
@RequiresPermissions(value = {
        PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GATE,
        PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GATE_CREATE,
        PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GATE_EDIT,
        PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GATE_VIEW,
        PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GATE_DELETE
}, logical = Logical.OR)
public class ParkingGateInfoController {

    @Autowired
    private ParkingGateInfoService parkingGateInfoService;

    /**
     * 新增出入口
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "新增出入口", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping("/add")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GATE_CREATE)
    @AuditLog(title = "出入口", value = "新增出入口", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto addParkingGate(@RequestBody ParkingGateAddRequestDto requestDto) {
        return parkingGateInfoService.addParkingGate(requestDto);
    }

    /**
     * 删除出入口
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "删除出入口", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping("/delete")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GATE_DELETE)
    @AuditLog(title = "出入口", value = "删除出入口", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteParkingGate(@RequestBody ParkingGateDeleteRequestDto requestDto) {
        return parkingGateInfoService.deleteParkingGate(requestDto);
    }

    /**
     * 修改出入口
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "修改出入口", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping("/update")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GATE_EDIT)
    @AuditLog(title = "出入口", value = "修改出入口", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto updateParkingGate(@RequestBody ParkingGateUpdateRequestDto requestDto) {
        return parkingGateInfoService.updateParkingGate(requestDto);
    }

    /**
     * 获取出入口
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取出入口", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingGateResultDto.class)
    @PostMapping("/get")
    @RequiresPermissions(value = {
            PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GATE_VIEW,
            PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GATE_EDIT
    }, logical = Logical.OR)
    public ObjectResultDto<ParkingGateResultDto> getParkingGate(@RequestBody ParkingGateGetRequestDto requestDto) {
        return parkingGateInfoService.getParkingGate(requestDto);
    }

    /**
     * 获取出入口列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取出入口列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingGateListGetResultDto.class)
    @PostMapping("/gates")
    public ListResultDto<ParkingGateListGetResultDto> getParkingGateList(@RequestBody ParkingGateListGetRequestDto requestDto) {
        return parkingGateInfoService.getParkingGateList(requestDto);
    }

    /**
     * 分页获取出入口列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页获取出入口列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingGateQueryPagedResultDto.class)
    @PostMapping("/pagelist")
    public PagedResultDto<ParkingGateQueryPagedResultDto> getGaragePagedList(@RequestBody ParkingGateQueryPagedResultRequestDto requestDto) {
        return parkingGateInfoService.getParkingGatePagedList(requestDto);
    }

    /**
     * 批量删除出入口
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "批量删除出入口", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping("/deletes")
    @AuditLog(title = "出入口", value = "批量删除出入口", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteParkingGate(@RequestBody ParkingGateBatchDeleteRequestDto requestDto) {
        return parkingGateInfoService.deleteBatchParkingGate(requestDto);
    }

    /**
     * 获取出入口类型
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取出入口类型", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping("/type")
    public ListResultDto<ComboboxItemDto> getParkingGateType(@RequestBody ParkingGateTypeGetRequestDto requestDto) {
        return parkingGateInfoService.getParkingGateType(requestDto);
    }
}
