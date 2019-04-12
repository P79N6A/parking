package com.zoeeasy.cloud.platform.controller.magnetic;

import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.core.annotation.AuditLog;
import com.zoeeasy.cloud.core.cst.PermissionDefinitions;
import com.zoeeasy.cloud.core.enums.BusinessType;
import com.zoeeasy.cloud.core.enums.OperatorType;
import com.zoeeasy.cloud.pds.magneticdetector.MagneticDetectorService;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.park.DetectorParkingLotIdBatchUpdateRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.park.MagneticDetectorNotRelevanceParkingLotQueryPageRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.park.MagneticDetectorRelevanceParkingLotQueryPageRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.result.park.MagneticDetectorRelevanceParkingLotResultDto;
import com.zoeeasy.cloud.pms.park.ParkingLotInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.MagneticDetectorByParkingLotQueryPageRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.MagneticDetectorByParkingLotQueryPageResultDto;
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
 * @Date: 2018/11/21
 * @author: lhj
 */
@Api(value = "泊位关联地磁检测器API", tags = "泊位关联地磁检测器API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@ApiVersion(1)
@ApiSigned
@RequestMapping("/cloud/RelevanceParkingLot")
@RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_DEVICE_SETTING_PARKINGLOT)
public class MagneticRelevanceParkingLotController {

    @Autowired
    private MagneticDetectorService magneticDetectorService;

    @Autowired
    private ParkingLotInfoService parkingLotInfoService;

    /**
     * 分页查询未关联泊位设备
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页查询未关联泊位设备", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = MagneticDetectorRelevanceParkingLotResultDto.class)
    @PostMapping(value = "/getNotRelevanceParkingLotPagedList")
    public PagedResultDto<MagneticDetectorRelevanceParkingLotResultDto> getNotRelevanceParkingLotMagneticDetectorPagedList(@RequestBody MagneticDetectorNotRelevanceParkingLotQueryPageRequestDto requestDto) {
        return magneticDetectorService.getNotRelevanceParkingLotMagneticDetectorPagedList(requestDto);
    }

    /**
     * 分页查询已关联泊位设备
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页查询已关联泊位设备", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = MagneticDetectorRelevanceParkingLotResultDto.class)
    @PostMapping(value = "/getRelevanceParkingLotPagedList")
    public PagedResultDto<MagneticDetectorRelevanceParkingLotResultDto> getRelevanceParkingLotMagneticDetectorPagedList(@RequestBody MagneticDetectorRelevanceParkingLotQueryPageRequestDto requestDto) {
        return magneticDetectorService.getRelevanceParkingLotMagneticDetectorPagedList(requestDto);
    }

    /**
     * 更新设备泊位
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "更新设备泊位", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/updateParkingLotId")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_DEVICE_SETTING_PARKINGLOT_RELEVANCE)
    @AuditLog(title = "泊位关联地磁检测器API", value = "更新设备泊位", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto updateDetectorParkingLotId(@RequestBody DetectorParkingLotIdBatchUpdateRequestDto requestDto) {
        return magneticDetectorService.updateDetectorParkingLotId(requestDto);
    }

    /**
     * 分页获取泊位配置相关
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页获取泊位配置相关", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = MagneticDetectorByParkingLotQueryPageResultDto.class)
    @PostMapping(value = "/getDetectorByParkingLotPagedList")
    public PagedResultDto<MagneticDetectorByParkingLotQueryPageResultDto> getMagneticDetectorByParkingLotPagedList(@RequestBody MagneticDetectorByParkingLotQueryPageRequestDto requestDto) {
        return parkingLotInfoService.getMagneticDetectorByParkingLotPagedList(requestDto);
    }

}
