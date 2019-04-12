package com.zoeeasy.cloud.platform.controller.lotoinfo;

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
import com.zoeeasy.cloud.pms.park.ParkingLotInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.*;
import com.zoeeasy.cloud.pms.park.dto.result.MagneticDetectorByParkingLotQueryPageResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingLotPagedResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingLotResultDto;
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
 * 车位控制器
 *
 * @author walkman
 */
@Api(value = "车位Api", tags = "车位Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/parklotinfo")
@ApiVersion(1)
@ApiSigned
@Slf4j
@RequiresPermissions(value = {
        PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LOT_CREATE,
        PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LOT_DELETE,
        PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LOT_EDIT,
        PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LOT_VIEW
}, logical = Logical.OR)
public class ParkingLotInfoController {

    @Autowired
    private ParkingLotInfoService parkingLotInfoService;

    /**
     * 新增车位
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "新增车位", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/add")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LOT_CREATE)
    @AuditLog(title = "车位", value = "新增车位", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto addParkingLot(@RequestBody ParkingLotAddRequestDto requestDto) {
        return parkingLotInfoService.addParkingLot(requestDto);
    }

    /**
     * 删除车位
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "删除车位", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/delete")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LOT_DELETE)
    @AuditLog(title = "车位", value = "删除车位", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteParkingLot(@RequestBody ParkingLotDeleteRequestDto requestDto) {
        return parkingLotInfoService.deleteParkingLot(requestDto);
    }

    /**
     * 更新车位
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "更新车位", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/update")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LOT_EDIT)
    @AuditLog(title = "车位", value = "更新车位", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto updateParkingLot(@RequestBody ParkingLotUpdateRequestDto requestDto) {
        return parkingLotInfoService.updateParkingLot(requestDto);
    }

    /**
     * 获取车位
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取车位", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingLotResultDto.class)
    @PostMapping(value = "/get")
    @RequiresPermissions(value = {
            PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LOT_VIEW,
            PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LOT_EDIT
    }, logical = Logical.OR)
    public ObjectResultDto<ParkingLotResultDto> getParkingLot(@RequestBody ParkingLotGetRequestDto requestDto) {
        return parkingLotInfoService.getParkingLot(requestDto);
    }

    /**
     * 分页获取车位列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页获取车位列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingLotResultDto.class)
    @PostMapping(value = "/listpage")
    public PagedResultDto<ParkingLotPagedResultDto> getParkingLotPagedList(@RequestBody ParkingLotQueryPagedResultRequestDto requestDto) {
        return parkingLotInfoService.getParkingLotPagedList(requestDto);
    }

    /**
     * 根据泊位编号获取泊位列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "根据泊位编号获取泊位列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = MagneticDetectorByParkingLotQueryPageResultDto.class)
    @PostMapping(value = "/getParkingLotByCodeList")
    public ListResultDto<ParkingLotResultDto> getParkingLotByCodeList(@RequestBody ParkingLotByCodeListGetRequestDto requestDto) {
        return parkingLotInfoService.getParkingLotByCodeList(requestDto);
    }

}
