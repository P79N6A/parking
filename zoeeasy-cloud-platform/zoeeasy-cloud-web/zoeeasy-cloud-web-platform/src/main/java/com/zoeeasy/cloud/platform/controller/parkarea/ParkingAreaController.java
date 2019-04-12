package com.zoeeasy.cloud.platform.controller.parkarea;

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
import com.zoeeasy.cloud.pms.parkingarea.ParkingAreaService;
import com.zoeeasy.cloud.pms.parkingarea.dto.request.*;
import com.zoeeasy.cloud.pms.parkingarea.dto.result.ParkingAreaListResultDto;
import com.zoeeasy.cloud.pms.parkingarea.dto.result.ParkingAreaQueryPagedResultDto;
import com.zoeeasy.cloud.pms.parkingarea.dto.result.ParkingAreaResultDto;
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
 * 泊车区域控制器
 * Created by song on 2018/9/21.
 */
@Api(value = "泊车区域Api", tags = "泊车区域Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/parkingArea")
@ApiVersion(1)
@ApiSigned
@RequiresPermissions(value = {
        PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_AREA_CREATE,
        PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_AREA_EDIT,
        PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_AREA_VIEW,
        PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_AREA_DELETE
}, logical = Logical.OR)
public class ParkingAreaController {

    @Autowired
    private ParkingAreaService parkingAreaService;

    @ApiOperation(value = "添加泊车区域", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/add")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_AREA_CREATE)
    @AuditLog(title = "泊车区域Api", value = "添加泊车区域", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto addParkingArea(@RequestBody ParkingAreaAddRequestDto requestDto) {
        return parkingAreaService.addParkingArea(requestDto);
    }

    @ApiOperation(value = "删除泊车区域", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/delete")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_AREA_DELETE)
    @AuditLog(title = "泊车区域Api", value = "删除泊车区域", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteParkingArea(@RequestBody ParkingAreaDeleteRequestDto requestDto) {
        return parkingAreaService.deleteParkingArea(requestDto);
    }

    @ApiOperation(value = "修改泊车区域", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/update")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_AREA_EDIT)
    @AuditLog(title = "泊车区域Api", value = "修改泊车区域", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto updateParkingArea(@RequestBody ParkingAreaUpdateRequestDto requestDto) {
        return parkingAreaService.updateParkingArea(requestDto);
    }

    @ApiOperation(value = "分页获取泊车区域", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingAreaQueryPagedResultDto.class)
    @PostMapping(value = "/listpage")
    public PagedResultDto<ParkingAreaQueryPagedResultDto> getParkingAreaPagedList(@RequestBody ParkingAreaQueryPagedRequestDto requestDto) {
        return parkingAreaService.getParkingAreaPagedList(requestDto);
    }

    @ApiOperation(value = "获取泊车区域", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingAreaResultDto.class)
    @PostMapping(value = "/get")
    @RequiresPermissions(value = {
            PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_AREA_VIEW,
            PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_AREA_EDIT}, logical = Logical.OR)
    public ObjectResultDto<ParkingAreaResultDto> getParkingArea(@RequestBody ParkingAreaGetRequestDto requestDto) {
        return parkingAreaService.getParkingArea(requestDto);
    }

    @ApiOperation(value = "获取泊车区域列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingAreaListResultDto.class)
    @PostMapping(value = "/list")
    public ListResultDto<ParkingAreaListResultDto> getParkingAreaList(@RequestBody ParkingAreaListRequestDto requestDto) {
        return parkingAreaService.getParkingAreaList(requestDto);
    }

}
