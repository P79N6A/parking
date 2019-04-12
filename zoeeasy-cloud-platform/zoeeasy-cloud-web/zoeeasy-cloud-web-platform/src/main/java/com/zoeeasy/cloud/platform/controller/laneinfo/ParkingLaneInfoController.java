package com.zoeeasy.cloud.platform.controller.laneinfo;

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
import com.zoeeasy.cloud.pms.lane.ParkingLaneInfoService;
import com.zoeeasy.cloud.pms.lane.dto.request.*;
import com.zoeeasy.cloud.pms.lane.dto.result.ParkingLaneListGetResultDto;
import com.zoeeasy.cloud.pms.lane.dto.result.ParkingLaneQueryPagedResultDto;
import com.zoeeasy.cloud.pms.lane.dto.result.ParkingLaneResultDto;
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
 * 车道控制器
 *
 * @author Kane
 */
@Api(value = "车道Api", tags = "车道Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/parkinglaneinfo")
@ApiVersion(1)
@ApiSigned
@RequiresPermissions(value = {
        PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LANE_CREATE,
        PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LANE_DELETE,
        PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LANE_EDIT,
        PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LANE_VIEW,
}, logical = Logical.OR)
public class ParkingLaneInfoController {

    @Autowired
    private ParkingLaneInfoService parkingLaneInfoService;

    /**
     * 新增车道
     *
     * @param requestDto
     * @return
     */

    @ApiOperation(value = "新增车道", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping("/add")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LANE_CREATE)
    @AuditLog(title = "车道", value = "新增车道", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto addParkingLot(@RequestBody ParkingLaneAddRequestDto requestDto) {
        return parkingLaneInfoService.addParkingLane(requestDto);
    }

    /**
     * 删除车道
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "删除车道", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping("/delete")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LANE_DELETE)
    @AuditLog(title = "车道", value = "删除车道", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteParkingLane(@RequestBody ParkingLaneDeleteRequestDto requestDto) {
        return parkingLaneInfoService.deleteParkingLane(requestDto);
    }

    /**
     * 修改车道
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "修改车道", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping("/update")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LANE_EDIT)
    @AuditLog(title = "车道", value = "修改车道", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto updateParkingLane(@RequestBody ParkingLaneUpdateRequestDto requestDto) {
        return parkingLaneInfoService.updateParkingLane(requestDto);
    }

    /**
     * 获取车道列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取车道列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingLaneListGetResultDto.class)
    @PostMapping("/lanes")
    public ListResultDto<ParkingLaneListGetResultDto> getParkingLaneList(@RequestBody ParkingLaneListGetRequestDto requestDto) {
        return parkingLaneInfoService.getParkingLaneList(requestDto);
    }

    /**
     * 批量获取车道
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取车道", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingLaneResultDto.class)
    @PostMapping("/get")
    @RequiresPermissions(value = {
            PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LANE_VIEW,
            PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LANE_EDIT
    }, logical = Logical.OR)
    public ObjectResultDto<ParkingLaneResultDto> getParkingLane(@RequestBody ParkingLaneGetRequestDto requestDto) {
        return parkingLaneInfoService.getParkingLane(requestDto);
    }

    /**
     * 分页获取出入口列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页获取出入口列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingLaneQueryPagedResultDto.class)
    @PostMapping("/pagelist")
    public PagedResultDto<ParkingLaneQueryPagedResultDto> getGaragePagedList(@RequestBody ParkingLaneQueryPagedResultRequestDto requestDto) {
        return parkingLaneInfoService.getParkingLanePagedList(requestDto);
    }

    /**
     * 批量删除车道
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "批量删除车道", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping("/deletes")
    @AuditLog(title = "车道", value = "批量删除车道", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteParkingLane(@RequestBody ParkingLaneBatchDeleteRequestDto requestDto) {
        return parkingLaneInfoService.deleteBatchParkingLane(requestDto);
    }

    /**
     * 获取车道类型
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取车道类型", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping("/type")
    public ListResultDto<ComboboxItemDto> getParkingLaneType(@RequestBody ParkingLaneTypeGetRequestDto requestDto) {
        return parkingLaneInfoService.getParkingLaneType(requestDto);
    }
}
