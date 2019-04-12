package com.zoeeasy.cloud.platform.controller.parkinfo;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.core.annotation.AuditLog;
import com.zoeeasy.cloud.core.enums.BusinessType;
import com.zoeeasy.cloud.core.enums.OperatorType;
import com.zoeeasy.cloud.pms.park.ManagementParkingInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.*;
import com.zoeeasy.cloud.pms.park.dto.result.ManagementParkingPagedResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ManagementParkingResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理端停车场控制器
 */
@Api(value = "管理端停车场Api", tags = "管理端停车场Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/management/parkinfo")
@ApiVersion(1)
@ApiSigned
public class ManagementParkingInfoController {

    @Autowired
    private ManagementParkingInfoService managementParkingInfoService;

    @ApiOperation(value = "添加第三方停车场信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/add")
    @AuditLog(title = "管理端停车场Api", value = "添加第三方停车场信息", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto addThirdPartyParking(@RequestBody ThirdPartyParkingAddRequestDto requestDto) {
        return managementParkingInfoService.addThirdPartyParking(requestDto);
    }

    @ApiOperation(value = "修改第三方停车场信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/update")
    @AuditLog(title = "管理端停车场Api", value = "修改第三方停车场信息", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto updateThirdPartyParking(@RequestBody ThirdPartyParkingUpdateRequestDto requestDto) {
        return managementParkingInfoService.updateThirdPartyParking(requestDto);
    }

    @ApiOperation(value = "获取停车场信息(无租户)", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ManagementParkingResultDto.class)
    @PostMapping(value = "/get")
    public ObjectResultDto<ManagementParkingResultDto> getManagementParking(@RequestBody ParkingGetRequestDto requestDto) {
        return managementParkingInfoService.getManagementParking(requestDto);
    }

    @ApiOperation(value = "删除停车场信息(无租户)", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/delete")
    @AuditLog(title = "管理端停车场Api", value = "删除停车场信息(无租户)", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteManagementParking(@RequestBody ParkingDeleteRequestDto requestDto) {
        return managementParkingInfoService.deleteManagementParking(requestDto);
    }

    @ApiOperation(value = "分页查询已审核通过停车场", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ManagementParkingPagedResultDto.class)
    @PostMapping(value = "/pagelist")
    public PagedResultDto<ManagementParkingPagedResultDto> getManagementParkingPagedList(@RequestBody ManagementParkingPagedRequestDto requestDto) {
        return managementParkingInfoService.getManagementParkingPagedList(requestDto);
    }

    @ApiOperation(value = "停车场上下架", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ManagementParkingPagedResultDto.class)
    @PostMapping(value = "/putaway")
    @AuditLog(title = "管理端停车场Api", value = "停车场上下架", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto parkingPutAway(@RequestBody ParkingPutAwayRequestDto requestDto) {
        return managementParkingInfoService.parkingPutAway(requestDto);
    }

}
