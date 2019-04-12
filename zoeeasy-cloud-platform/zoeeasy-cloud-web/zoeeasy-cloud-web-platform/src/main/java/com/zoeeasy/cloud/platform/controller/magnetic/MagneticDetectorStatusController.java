package com.zoeeasy.cloud.platform.controller.magnetic;

import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.core.cst.PermissionDefinitions;
import com.zoeeasy.cloud.pds.magneticdetector.MagneticDetectorService;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.park.MagneticDetectorStatusQueryPageRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.result.park.MagneticDetectorStatusQueryPageResultDto;
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
@Api(value = "地磁检测器状态API", tags = "地磁检测器状态API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@ApiVersion(1)
@ApiSigned
@RequestMapping("/cloud/magneticStatus")
@RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_DEVICE_OPERATION_DETECTORSTATUS)
public class MagneticDetectorStatusController {

    @Autowired
    private MagneticDetectorService magneticDetectorService;

    /**
     * 分页查询地磁检测器状态
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页查询地磁检测器状态", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = MagneticDetectorStatusQueryPageResultDto.class)
    @PostMapping(value = "/getStatusPagedList")
    public PagedResultDto<MagneticDetectorStatusQueryPageResultDto> getMagneticDetectorStatusPagedList(@RequestBody MagneticDetectorStatusQueryPageRequestDto requestDto) {
        return magneticDetectorService.getMagneticDetectorStatusPagedList(requestDto);
    }
}
