package com.zoeeasy.cloud.platform.controller.auditlog;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.core.annotation.AuditLog;
import com.zoeeasy.cloud.core.enums.BusinessType;
import com.zoeeasy.cloud.core.enums.OperatorType;
import com.zoeeasy.cloud.sys.auditlog.AuditLogService;
import com.zoeeasy.cloud.sys.auditlog.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作日志控制器
 *
 * @author walkman
 */
@Api(value = "操作日志Api", tags = "操作日志Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/auditlog")
@ApiVersion(1)
@ApiSigned
@RequiresAuthentication
public class AuditLogController {

    @Autowired
    private AuditLogService auditLogService;

    /**
     * 分页获取操作日志列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页获取操作日志列表", notes = "", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = PagedResultDto.class)
    @PostMapping(value = "/listpage")
    public PagedResultDto<AuditLogResultDto> getAuditLogPagedList(@RequestBody AuditLogPagedRequestDto requestDto) {
        return auditLogService.getAuditLogPagedList(requestDto);
    }

    /**
     * 获取操作日志
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取操作日志", notes = "", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ObjectResultDto.class)
    @PostMapping(value = "/get")
    public ObjectResultDto<AuditLogResultDto> getAuditLog(@RequestBody AuditLogGetRequestDto requestDto) {
        return auditLogService.getAuditLog(requestDto);
    }

    /**
     * 新增操作日志
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "新增操作日志", notes = "", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/add")
    @AuditLog(title = "操作日志", value = "新增操作日志", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto addAuditLog(@RequestBody AuditLogAddRequestDto requestDto) {
        return auditLogService.addAuditLog(requestDto);
    }

    /**
     * 更新操作日志
     *
     * @param requestDto
     * @return
     */
    @AuditLog(title = "操作日志", value = "更新操作日志", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    @ApiOperation(value = "更新操作日志", notes = "", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/update")
    public ResultDto updateAuditLog(@RequestBody AuditLogUpdateRequestDto requestDto) {
        return auditLogService.updateAuditLog(requestDto);
    }

    /**
     * 删除操作日志
     *
     * @param requestDto
     * @return
     */
    @AuditLog(title ="操作日志", value = "删除操作日志", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    @ApiOperation(value = "删除操作日志", notes = "", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/delete")
    public ResultDto deleteAuditLog(@RequestBody AuditLogDeleteRequestDto requestDto) {
        return auditLogService.deleteAuditLog(requestDto);
    }

    /**
     * 获取操作类型下拉菜单
     */
    @ApiOperation(value = "获取操作类型下拉菜单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/getType")
    public ListResultDto<ComboboxItemDto> getPayMode(@RequestBody AuditLogTypeGetRequestDto requestDto) {
        return auditLogService.getType(requestDto);
    }
}
