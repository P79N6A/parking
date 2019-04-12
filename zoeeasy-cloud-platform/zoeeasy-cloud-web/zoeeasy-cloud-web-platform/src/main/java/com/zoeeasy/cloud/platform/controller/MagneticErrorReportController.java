package com.zoeeasy.cloud.platform.controller;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.core.cst.PermissionDefinitions;
import com.zoeeasy.cloud.pds.magneticerrorreport.MagneticErrorReportService;
import com.zoeeasy.cloud.pds.magneticerrorreport.dto.request.MagneticErrorReportListResultRequestDto;
import com.zoeeasy.cloud.pds.magneticerrorreport.dto.request.MagneticErrorReportQueryPagedResultRequestDto;
import com.zoeeasy.cloud.pds.magneticerrorreport.dto.result.MagneticErrorReportResultDto;
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
 * 地磁误报处理API
 *
 * @Date: 2018/10/18
 * @author: lhj
 */
@Api(value = "地磁误报处理API", tags = "地磁误报处理API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@ApiVersion(1)
@ApiSigned
@RequestMapping("/cloud/magneticErrorReport")
@RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_DEVICE_OPERATION_DETECTOREXCEPTION)
public class MagneticErrorReportController {

    @Autowired
    private MagneticErrorReportService magneticErrorReportService;

    /**
     * 分页查询地磁误报记录列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页查询地磁误报记录列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = MagneticErrorReportResultDto.class)
    @PostMapping(value = "/getPageList")
    public PagedResultDto<MagneticErrorReportResultDto> getMagneticErrorReportPagedList(@RequestBody MagneticErrorReportQueryPagedResultRequestDto requestDto) {
        return magneticErrorReportService.getMagneticErrorReportPagedList(requestDto);
    }

    /**
     * 查询地磁误报记录列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "查询地磁误报记录列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = MagneticErrorReportResultDto.class)
    @PostMapping(value = "/getList")
    public ListResultDto<MagneticErrorReportResultDto> getMagneticErrorReportList(@RequestBody MagneticErrorReportListResultRequestDto requestDto) {
        return magneticErrorReportService.getMagneticErrorReportList(requestDto);
    }
}
