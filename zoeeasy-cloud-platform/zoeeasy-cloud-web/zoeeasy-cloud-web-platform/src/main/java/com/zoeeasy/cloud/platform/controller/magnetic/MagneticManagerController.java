package com.zoeeasy.cloud.platform.controller.magnetic;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.core.annotation.AuditLog;
import com.zoeeasy.cloud.core.cst.PermissionDefinitions;
import com.zoeeasy.cloud.core.enums.BusinessType;
import com.zoeeasy.cloud.core.enums.OperatorType;
import com.zoeeasy.cloud.pds.magneticdetector.MagneticDetectorService;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.park.MagneticDetectorNotRelevanceQueryPageRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.park.MagneticDetectorRelevanceQueryPageRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.result.park.MagneticDetectorRelevanceResultDto;
import com.zoeeasy.cloud.pds.magneticmanager.MagneticManagerService;
import com.zoeeasy.cloud.pds.magneticmanager.dto.request.*;
import com.zoeeasy.cloud.pds.magneticmanager.dto.result.MagneticManagerByIdResultDto;
import com.zoeeasy.cloud.pds.magneticmanager.dto.result.MagneticManagerResultDto;
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
 * 停车设备管理器Api
 *
 * @Date: 2018/8/23
 * @author: wh
 */
@Api(value = "停车设备管理器Api", tags = "停车设备管理器Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@ApiVersion(1)
@ApiSigned
@RequestMapping("/cloud/parkingDeviceSupervisor")
@RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_DEVICE_MANAGER_MAGNETIC)
public class MagneticManagerController {

    @Autowired
    private MagneticManagerService magneticManagerService;

    @Autowired
    private MagneticDetectorService magneticDetectorService;

    /**
     * 添加停车设备管理器
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "添加停车设备管理", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/add")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_DEVICE_MANAGER_MAGNETIC_CREATE)
    @AuditLog(title = "停车设备管理器Api", value = "添加停车设备管理", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto addParkingDeviceSupervisor(@RequestBody MagneticManagerAddRequestDto requestDto) {
        return magneticManagerService.addMagneticManager(requestDto);
    }

    /**
     * 删除停车设备管理器
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "删除停车设备管理", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/delete")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_DEVICE_MANAGER_MAGNETIC_DELETE)
    @AuditLog(title = "停车设备管理器Api", value = "删除停车设备管理", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteParkingDeviceSupervisor(@RequestBody MagneticManagerDeleteRequestDto requestDto) {
        return magneticManagerService.deleteMagneticManager(requestDto);
    }

    /**
     * 修改停车设备管理器
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "修改停车设备管理", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/update")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_DEVICE_MANAGER_MAGNETIC_EDIT)
    @AuditLog(title = "停车设备管理器Api", value = "修改停车设备管理", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto updateParkingDeviceSupervisor(@RequestBody MagneticManagerUpdateRequestDto requestDto) {
        return magneticManagerService.updateMagneticManager(requestDto);
    }

    /**
     * 获取停车设备管理器
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取停车设备管理", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = MagneticManagerByIdResultDto.class)
    @PostMapping(value = "/get")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_DEVICE_MANAGER_MAGNETIC_VIEW)
    public ObjectResultDto<MagneticManagerByIdResultDto> getParkingDeviceSupervisor(@RequestBody MagneticManagerGetRequestDto requestDto) {
        return magneticManagerService.getMagneticManager(requestDto);
    }

    /**
     * 分页查询停车设备管理器
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页查询停车设备管理器", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = MagneticManagerResultDto.class)
    @PostMapping(value = "/getPageList")
    public PagedResultDto<MagneticManagerResultDto> getParkingDeviceSupervisorPageList(@RequestBody MagneticManagerQueryPageRequestDto requestDto) {
        return magneticManagerService.getMagneticManagerPageList(requestDto);
    }

    /**
     * 关联地磁检测器
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "关联地磁检测器", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/batchUpdateSupervisorId")
    @AuditLog(title = "停车设备管理器Api", value = "关联地磁检测器", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto batchUpdateManagerId(@RequestBody MagneticManagerIdBatchUpdateRequestDto requestDto) {
        return magneticDetectorService.batchUpdateMagneticManagerId(requestDto);
    }

    /**
     * 分页查询未关联管理器设备列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页查询未关联管理器设备列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = MagneticDetectorRelevanceResultDto.class)
    @PostMapping(value = "/getNotRelevancePagedList")
    public PagedResultDto<MagneticDetectorRelevanceResultDto> getNotRelevanceDevicePagedList(@RequestBody MagneticDetectorNotRelevanceQueryPageRequestDto requestDto) {
        return magneticDetectorService.getNotRelevanceMagneticDetectorPagedList(requestDto);
    }

    /**
     * 分页查询已关联管理器设备列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页查询已关联管理器设备列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = MagneticDetectorRelevanceResultDto.class)
    @PostMapping(value = "/getRelevancePagedList")
    public PagedResultDto<MagneticDetectorRelevanceResultDto> getRelevanceMagneticDetectorPagedList(@RequestBody MagneticDetectorRelevanceQueryPageRequestDto requestDto) {
        return magneticDetectorService.getRelevanceMagneticDetectorPagedList(requestDto);
    }
}
