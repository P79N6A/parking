package com.zoeeasy.cloud.platform.controller.appoint;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.charge.appointrule.AppointRuleService;
import com.zoeeasy.cloud.charge.appointrule.dto.request.*;
import com.zoeeasy.cloud.charge.appointrule.dto.result.AppointRuleResultDto;
import com.zoeeasy.cloud.core.annotation.AuditLog;
import com.zoeeasy.cloud.core.cst.PermissionDefinitions;
import com.zoeeasy.cloud.core.enums.BusinessType;
import com.zoeeasy.cloud.core.enums.OperatorType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "预约规则Api", tags = "预约规则Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@ApiVersion(1)
@ApiSigned
@RequestMapping("/cloud/appointrule")
@RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_APPOINT_RULE)
public class AppointRuleController {

    @Autowired
    private AppointRuleService appointRuleService;

    /**
     * 添加预约规则
     *
     * @return
     */
    @ApiOperation(value = "添加预约规则", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/add")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_APPOINT_RULE_CREATE)
    @AuditLog(title = "预约规则", value = "添加预约规则", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto addAppointRule(@RequestBody AppointRuleAddRequestDto requestDto) {
        return appointRuleService.addAppointRule(requestDto);
    }

    /**
     * 根据ID获取预约规则
     */
    @ApiOperation(value = "根据ID获取预约规则", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AppointRuleResultDto.class)
    @PostMapping(value = "/get")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_APPOINT_RULE_VIEW)
    public ObjectResultDto<AppointRuleResultDto> getAppointRule(@RequestBody AppointRuleGetRequestDto requestDto) {
        return appointRuleService.getAppointRule(requestDto);
    }

    /**
     * 修改预约规则
     */
    @ApiOperation(value = "修改预约规则", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/update")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_APPOINT_RULE_EDIT)
    @AuditLog(title = "预约规则", value = "修改预约规则", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto updateAppointRule(@RequestBody AppointRuleUpdateRequestDto requestDto) {
        return appointRuleService.updateAppointRule(requestDto);
    }

    /**
     * 删除预约规则
     */
    @ApiOperation(value = "删除预约规则", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/delete")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_APPOINT_RULE_DELETE)
    @AuditLog(title = "预约规则", value = "删除预约规则", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteAppointRule(@RequestBody AppointRuleDeleteRequestDto requestDto) {
        return appointRuleService.deleteAppointRule(requestDto);
    }

    /**
     * 获取预约规则列表
     */
    @ApiOperation(value = "获取预约规则列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AppointRuleResultDto.class)
    @PostMapping(value = "/list")
    public ListResultDto<AppointRuleResultDto> getAppointRuleList(@RequestBody AppointRuleGetListRequestDto requestDto) {
        return appointRuleService.getAppointRuleList(requestDto);
    }

    /**
     * 分页获取预约规则列表
     */
    @ApiOperation(value = "分页获取预约规则列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AppointRuleResultDto.class)
    @PostMapping(value = "/listpage")
    public PagedResultDto<AppointRuleResultDto> getAppointRulePageList(@RequestBody AppointRuleGetPageListRequestDto requestDto) {
        return appointRuleService.getAppointRulePageList(requestDto);
    }
}
