package com.zoeeasy.cloud.platform.controller.charge;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.charge.chg.CalculateAmountService;
import com.zoeeasy.cloud.charge.chg.ChargeRuleService;
import com.zoeeasy.cloud.charge.chg.dto.request.*;
import com.zoeeasy.cloud.charge.chg.dto.result.CalculateAmountViewResultDto;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleInfoResultDto;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleViewResultDto;
import com.zoeeasy.cloud.charge.park.ParkChargeRuleService;
import com.zoeeasy.cloud.charge.park.dto.request.ParkChargeRuleCalculateTryRequestDto;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingChargeRuleDeleteRequestDto;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingChargeRuleQueryPageRequestDto;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingChargeRuleUpdateRequestDto;
import com.zoeeasy.cloud.charge.park.dto.result.ParkingChargeRuleViewResultDto;
import com.zoeeasy.cloud.core.annotation.AuditLog;
import com.zoeeasy.cloud.core.cst.PermissionDefinitions;
import com.zoeeasy.cloud.core.enums.BusinessType;
import com.zoeeasy.cloud.core.enums.OperatorType;
import com.zoeeasy.cloud.integration.park.CalculateIntegrationService;
import com.zoeeasy.cloud.integration.park.ParkChargeRuleIntegrationService;
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
 * @date 2018/10/10 0010
 */
@RestController
@ApiVersion(1)
@ApiSigned
@Api(tags = "收费配置API", value = "收费配置相关 API", description = "收费配置相关API", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/cloud/chargerule")
@RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_RULE)
public class ChargeRuleController {

    @Autowired
    private ParkChargeRuleService parkChargeRuleService;

    @Autowired
    private ChargeRuleService chargeRuleService;

    @Autowired
    private CalculateAmountService calculateAmountService;

    @Autowired
    private CalculateIntegrationService calculateIntegrationService;

    @Autowired
    private ParkChargeRuleIntegrationService parkChargeRuleIntegrationService;

    /**
     * 添加收费规则
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "添加收费规则", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/add")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_RULE_CREATE)
    @AuditLog(title = "收费配置", value = "添加收费规则", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto addChargeRule(@RequestBody ChargeRuleAddRequestDto requestDto) {
        return chargeRuleService.addChargeRule(requestDto);
    }

    /**
     * 分页获取收费规则列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页获取收费规则列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ChargeRuleViewResultDto.class)
    @PostMapping(value = "/listpage")
    public PagedResultDto<ChargeRuleViewResultDto> getChargeRulePageList(@RequestBody ChargeRuleGetPageListRequestDto requestDto) {
        return chargeRuleService.getChargeRulePageList(requestDto);
    }

    /**
     * 根据ID获取收费规则
     */
    @ApiOperation(value = "根据ID获取收费规则", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ChargeRuleViewResultDto.class)
    @PostMapping(value = "/get")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_RULE_VIEW)
    public ObjectResultDto<ChargeRuleViewResultDto> getChargeRuleById(@RequestBody ChargeRuleGetRequestDto requestDto) {
        return chargeRuleService.getChargeRule(requestDto);
    }

    /**
     * 获取规则最小计时单位
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取规则最小计时单位", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/getUnitTime")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_RULE_VIEW)
    ListResultDto<ComboboxItemDto> getUnitTime(@RequestBody GetUnitTimeRequestDto requestDto) {
        return chargeRuleService.getUnitTime(requestDto);
    }

    /**
     * 修改收费规则
     */
    @ApiOperation(value = "修改收费规则", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/update")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_RULE_EDIT)
    @AuditLog(title = "收费配置", value = "修改收费规则", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto updateChargeRule(@RequestBody ChargeRuleUpdateRequestDto requestDto) {
        return chargeRuleService.updateChargeRule(requestDto);
    }

    /**
     * 删除收费规则
     */
    @ApiOperation(value = "删除收费规则", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/delete")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_RULE_DELETE)
    @AuditLog(title = "收费配置", value = "删除收费规则", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteChargeRule(@RequestBody ChargeRuleDeleteRequestDto requestDto) {
        return parkChargeRuleService.deleteChargeRule(requestDto);
    }

    /**
     * 添加收费规则试算
     */
    @ApiOperation(value = "添加收费规则试算", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = CalculateAmountViewResultDto.class)
    @PostMapping(value = "/try")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_RULE_DELETE)
    @AuditLog(title = "收费配置", value = "添加收费规则试算", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ObjectResultDto<CalculateAmountViewResultDto> chargeRuleCalculateTry(@RequestBody ChargeRuleCalculateTryRequestDto requestDto) {
        return calculateAmountService.chargeRuleCalculateTry(requestDto);
    }

    /**
     * 停车场和收费规则关联
     */
    @ApiOperation(value = "停车场和收费规则关联", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/addParkingChargeRule")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_RULE_EDIT)
    @AuditLog(title = "收费配置", value = "停车场和收费规则关联", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto addParkingChargeRule(@RequestBody ParkingChargeRuleUpdateRequestDto requestDto) {
        return parkChargeRuleIntegrationService.addParkingChargeRule(requestDto);
    }

    /**
     * 停车场和收费规则去关联
     */
    @ApiOperation(value = "停车场和收费规则去关联", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/deleteParkingChargeRule")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_RULE_DELETE)
    @AuditLog(title = "收费配置", value = "停车场和收费规则关联", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteParkingChargeRule(@RequestBody ParkingChargeRuleDeleteRequestDto requestDto) {
        return parkChargeRuleService.deleteParkingChargeRule(requestDto);
    }

    /**
     * 分页获取停车场收费规则
     */
    @ApiOperation(value = "分页获取停车场收费规则", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingChargeRuleViewResultDto.class)
    @PostMapping(value = "/queryParkChargeRule")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_RULE_VIEW)
    public PagedResultDto<ParkingChargeRuleViewResultDto> queryParkingChargeRulePage(@RequestBody ParkingChargeRuleQueryPageRequestDto requestDto) {
        return parkChargeRuleIntegrationService.queryParkingChargeRulePage(requestDto);
    }

    /**
     * 分页获取停车场历史收费规则
     */
    @ApiOperation(value = "分页获取停车场历史收费规则", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingChargeRuleViewResultDto.class)
    @PostMapping(value = "/queryHistoryParkChargeRule")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_RULE_VIEW)
    public PagedResultDto<ParkingChargeRuleViewResultDto> queryHistoryParkingChargeRulePage(@RequestBody ParkingChargeRuleQueryPageRequestDto requestDto) {
        return parkChargeRuleIntegrationService.queryHistoryParkingChargeRulePage(requestDto);
    }

    /**
     * 添加关联时获取收费规则列表
     */
    @ApiOperation(value = "添加关联时获取收费规则列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ChargeRuleInfoResultDto.class)
    @PostMapping(value = "/chargeRuleList")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_RULE_VIEW)
    public ListResultDto<ChargeRuleInfoResultDto> chargeRuleList(@RequestBody ChargeRuleGetListRequestDto requestDto) {
        return parkChargeRuleIntegrationService.chargeRuleList(requestDto);
    }

    /**
     * 停车场试算收费规则
     */
    @ApiOperation(value = "停车场试算收费规则", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = CalculateAmountViewResultDto.class)
    @PostMapping(value = "/parkChargeRuleCalculateTry")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_RULE_VIEW)
    public ObjectResultDto<CalculateAmountViewResultDto> parkChargeRuleCalculateTry(@RequestBody ParkChargeRuleCalculateTryRequestDto requestDto) {
        return calculateIntegrationService.parkChargeRuleCalculateTry(requestDto);
    }

}
