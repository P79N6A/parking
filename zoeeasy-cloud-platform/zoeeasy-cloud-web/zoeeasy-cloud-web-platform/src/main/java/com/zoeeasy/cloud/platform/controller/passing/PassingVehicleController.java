package com.zoeeasy.cloud.platform.controller.passing;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.core.annotation.AuditLog;
import com.zoeeasy.cloud.core.cst.PermissionDefinitions;
import com.zoeeasy.cloud.core.enums.BusinessType;
import com.zoeeasy.cloud.core.enums.OperatorType;
import com.zoeeasy.cloud.pms.passing.PassingVehicleRecordService;
import com.zoeeasy.cloud.pms.passing.dto.request.PassVehicleRecordAddRemarkRequestDto;
import com.zoeeasy.cloud.pms.passing.dto.request.PassVehicleRecordProofreadRequestDto;
import com.zoeeasy.cloud.pms.passing.dto.request.PassingVehicleRecordGetByPassNoRequestDto;
import com.zoeeasy.cloud.pms.passing.dto.request.PassingVehicleRecordQueryPageRequestDto;
import com.zoeeasy.cloud.pms.passing.dto.result.PassingVehicleRecordViewResultDto;
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
 * @author AkeemSuper
 * @date 2018/11/21 0021
 */
@Api(tags = "过车记录APi", value = "过车记录APi", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@ApiVersion(1)
@ApiSigned
@RequestMapping("/cloud/passRecord")
@RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_BUSINESS_PASSING)
public class PassingVehicleController {

    @Autowired
    private PassingVehicleRecordService passingVehicleRecordService;

    /**
     * 分页获取过车记录
     */
    @ApiOperation(value = "分页获取过车记录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = PassingVehicleRecordViewResultDto.class)
    @PostMapping(value = "/listPage")
    public PagedResultDto<PassingVehicleRecordViewResultDto> getPassVehicleRecordListPage(@RequestBody PassingVehicleRecordQueryPageRequestDto requestDto) {
        return passingVehicleRecordService.getPassVehicleRecordListPage(requestDto);
    }

    /**
     * 根据过车记录号获取过车记录
     */
    @ApiOperation(value = "根据过车记录号获取过车记录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = PassingVehicleRecordViewResultDto.class)
    @PostMapping(value = "/getByPassNo")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_BUSINESS_PASSING_VIEW)
    public ObjectResultDto<PassingVehicleRecordViewResultDto> getPassRecordByPassNo(@RequestBody PassingVehicleRecordGetByPassNoRequestDto requestDto) {
        return passingVehicleRecordService.getPassRecordByPassNo(requestDto);
    }

    /**
     * 人工校对过车记录
     */
    @ApiOperation(value = "人工校对过车记录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/proof")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_BUSINESS_PASSING_EDIT)
    @AuditLog(title = "过车记录APi", value = "人工校对过车记录", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto proofreadPassVehicleRecord(@RequestBody PassVehicleRecordProofreadRequestDto requestDto) {
        return passingVehicleRecordService.proofreadPassVehicleRecord(requestDto);
    }

    /**
     * 添加过车记录备注
     */
    @ApiOperation(value = "添加过车记录备注", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/addRemark")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_BUSINESS_PASSING_EDIT)
    @AuditLog(title = "过车记录APi", value = "添加过车记录备注", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto addPassVehicleRecordRemark(@RequestBody PassVehicleRecordAddRemarkRequestDto requestDto) {
        return passingVehicleRecordService.addPassVehicleRecordRemark(requestDto);
    }

}
