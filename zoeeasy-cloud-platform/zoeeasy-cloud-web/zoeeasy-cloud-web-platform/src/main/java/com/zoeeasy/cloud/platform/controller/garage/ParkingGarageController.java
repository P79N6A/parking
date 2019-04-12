package com.zoeeasy.cloud.platform.controller.garage;

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
import com.zoeeasy.cloud.pms.garage.ParkingGarageService;
import com.zoeeasy.cloud.pms.garage.dto.request.*;
import com.zoeeasy.cloud.pms.garage.dto.result.GarageListGetResultDto;
import com.zoeeasy.cloud.pms.garage.dto.result.GarageQueryPagedResultDto;
import com.zoeeasy.cloud.pms.garage.dto.result.GarageResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 车库控制器
 * Created by song on 2018/9/20.
 */
@Api(value = "车库Api", tags = "车库Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/garage")
@ApiVersion(1)
@ApiSigned
@RequiresPermissions(value = {
        PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GARAGE,
        PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GARAGE_CREATE,
        PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GARAGE_EDIT,
        PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GARAGE_VIEW,
        PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GARAGE_DELETE
}, logical = Logical.OR)
@Slf4j
public class ParkingGarageController {

    @Autowired
    private ParkingGarageService parkingGarageService;

    @ApiOperation(value = "添加车库", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/add")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GARAGE_CREATE)
    @AuditLog(title = "车库", value = "添加车库", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto addGarage(@RequestBody GarageAddRequestDto requestDto) {
        return parkingGarageService.addGarage(requestDto);
    }

    @ApiOperation(value = "修改车库", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/update")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GARAGE_EDIT)
    @AuditLog(title = "车库", value = "修改车库", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto updateGarage(@RequestBody GarageUpdateRequestDto requestDto) {
        return parkingGarageService.updateGarage(requestDto);
    }

    @ApiOperation(value = "删除车库", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/delete")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GARAGE_DELETE)
    @AuditLog(title = "车库", value = "删除车库", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteGarage(@RequestBody GarageDeleteRequestDto requestDto) {
        return parkingGarageService.deleteGarage(requestDto);
    }

    @ApiOperation(value = "获取车库", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = GarageResultDto.class)
    @PostMapping(value = "/get")
    @RequiresPermissions(value = {
            PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GARAGE_VIEW,
            PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GARAGE_EDIT
    }, logical = Logical.OR)
    public ObjectResultDto<GarageResultDto> getGarage(@RequestBody GarageGetRequestDto requestDto) {
        return parkingGarageService.getGarage(requestDto);
    }

    @ApiOperation(value = "分页获取车库", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = GarageQueryPagedResultDto.class)
    @PostMapping(value = "/listpage")
    public PagedResultDto<GarageQueryPagedResultDto> getGaragePagedList(@RequestBody GarageQueryPagedRequestDto requestDto) {
        return parkingGarageService.getGaragePagedList(requestDto);
    }

    @ApiOperation(value = "获取车库列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = GarageListGetResultDto.class)
    @PostMapping(value = "/list")
    public ListResultDto<GarageListGetResultDto> getGarageList(@RequestBody GarageListGetRequestDto requestDto) {
        return parkingGarageService.getGarageList(requestDto);
    }

}
