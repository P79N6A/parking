package com.zoeeasy.cloud.platform.controller;

import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.core.cst.PermissionDefinitions;
import com.zoeeasy.cloud.gather.magnetic.MagneticHeartBeatService;
import com.zoeeasy.cloud.gather.magnetic.dto.request.magneticdetector.MagneticReportRecordQueryPageRequestDto;
import com.zoeeasy.cloud.gather.magnetic.dto.result.MagneticReportRecordQueryPageResultDto;
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
 * 地磁检测记录API
 *
 * @Date: 2018/9/30
 * @author: lhj
 */
@Api(value = "地磁检测记录API", tags = "地磁检测记录API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@ApiVersion(1)
@ApiSigned
@RequestMapping("/cloud/magneticReportRecord")
@RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_DEVICE_OPERATION_DETECTRECORD)
public class MagneticReportRecordController {

    @Autowired
    private MagneticHeartBeatService magneticHeartBeatService;

    /**
     * 分页查询地磁检测记录
     *
     * @return
     */
    @ApiOperation(value = "分页查询地磁检测记录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = MagneticReportRecordQueryPageResultDto.class)
    @PostMapping(value = "/getReportRecord")
    public PagedResultDto<MagneticReportRecordQueryPageResultDto> getMagneticReportRecordPageList(@RequestBody MagneticReportRecordQueryPageRequestDto requestDto) {
        return magneticHeartBeatService.getMagneticReportRecordPageList(requestDto);
    }
}
