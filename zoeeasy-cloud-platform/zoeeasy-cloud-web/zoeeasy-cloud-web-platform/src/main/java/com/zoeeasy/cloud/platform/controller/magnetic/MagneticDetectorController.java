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
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.park.*;
import com.zoeeasy.cloud.pds.magneticdetector.dto.result.park.MagneticDetectorByCodeResultDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.result.park.MagneticDetectorResultDto;
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
 * 设备API
 *
 * @author lhj
 */
@Api(value = "设备API", tags = "设备API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@ApiVersion(1)
@ApiSigned
@RequestMapping("/cloud/magnetic")
@RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_DEVICE_MANAGER_DETECTOR)
public class MagneticDetectorController {

    @Autowired
    private MagneticDetectorService magneticDetectorService;

    /**
     * 添加设备
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "添加设备", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/add")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_DEVICE_MANAGER_DETECTOR_CREATE)
    @AuditLog(title = "设备", value = "添加设备", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto addDevice(@RequestBody MagneticDetectorAddRequestDto requestDto) {
        return magneticDetectorService.addMagneticDetector(requestDto);
    }

    /**
     * 更新设备
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "更新设备", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/update")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_DEVICE_MANAGER_DETECTOR_EDIT)
    @AuditLog(title = "设备", value = "更新设备", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto updateDevice(@RequestBody MagneticDetectorUpdateRequestDto requestDto) {
        return magneticDetectorService.updateMagneticDetector(requestDto);
    }

    /**
     * 删除设备
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "删除设备", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/delete")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_DEVICE_MANAGER_DETECTOR_DELETE)
    @AuditLog(title = "设备", value = "删除设备", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteDevice(@RequestBody MagneticDetectorDeleteRequestDto requestDto) {
        return magneticDetectorService.deleteMagneticDetector(requestDto);
    }

    /**
     * 分页查询设备列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页查询设备列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = MagneticDetectorByCodeResultDto.class)
    @PostMapping(value = "/pagelist")
    public PagedResultDto<MagneticDetectorByCodeResultDto> getMagneticDetectorPagedList(@RequestBody MagneticDetectorQueryPagedResultRequestDto requestDto) {
        return magneticDetectorService.getMagneticDetectorPagedList(requestDto);
    }

    /**
     * 根据id查询设备
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "根据id查询设备", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = MagneticDetectorResultDto.class)
    @PostMapping(value = "/selectById")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_DEVICE_MANAGER_DETECTOR_VIEW)
    public ObjectResultDto<MagneticDetectorResultDto> getMagneticDetectorById(@RequestBody MagneticDetectorGetRequestByIdDto requestDto) {
        return magneticDetectorService.getMagneticDetectorById(requestDto);
    }

    /**
     * 根据编号查询设备
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "根据编号查询设备", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = MagneticDetectorByCodeResultDto.class)
    @PostMapping(value = "/selectByCode")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_DEVICE_MANAGER_DETECTOR_VIEW)
    public ObjectResultDto<MagneticDetectorByCodeResultDto> getMagneticDetector(@RequestBody MagneticDetectorGetRequestDto requestDto) {
        return magneticDetectorService.getMagneticDetector(requestDto);
    }
}

