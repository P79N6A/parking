package com.zoeeasy.cloud.platform.controller.parkinfo;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.charge.chg.ChargeRuleService;
import com.zoeeasy.cloud.charge.chg.dto.request.ChargeRuleGetRequestDto;
import com.zoeeasy.cloud.charge.chg.dto.request.ChargeRuleNonTenantGetRequestDto;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleNonTenantResultDto;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleViewResultDto;
import com.zoeeasy.cloud.core.annotation.AuditLog;
import com.zoeeasy.cloud.core.enums.BusinessType;
import com.zoeeasy.cloud.core.enums.OperatorType;
import com.zoeeasy.cloud.pms.park.ParkingApplyInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.ApplyParkingPagedRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.AuditParkingRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ApplyParkingPagedResultDto;
import com.zoeeasy.cloud.ucc.user.UserService;
import com.zoeeasy.cloud.ucc.user.dto.request.AuditUserListGetRequestDto;
import com.zoeeasy.cloud.ucc.user.dto.result.UserListResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 停车场审核控制器
 */
@Api(value = "停车场审核Api", tags = "停车场审核Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/parkinfo/apply")
@ApiVersion(1)
@ApiSigned
@Slf4j
public class ParkingApplyInfoController {

    @Autowired
    private ParkingApplyInfoService parkingApplyInfoService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChargeRuleService chargeRuleService;

    @ApiOperation(value = "审核停车场", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/audit")
    @AuditLog(title = "停车场审核Api", value = "审核停车场", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto auditParking(@RequestBody AuditParkingRequestDto requestDto) {
        return parkingApplyInfoService.auditParking(requestDto);
    }

    @ApiOperation(value = "分页获取审核停车场列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/pagelist")
    public PagedResultDto<ApplyParkingPagedResultDto> getApplyParkingPagedList(@RequestBody ApplyParkingPagedRequestDto requestDto) {
        return parkingApplyInfoService.getApplyParkingPagedList(requestDto);
    }

    @ApiOperation(value = "获取审核停车场人员列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/getAuditUserList")
    public ListResultDto<UserListResultDto> getAuditUserList(@RequestBody AuditUserListGetRequestDto requestDto) {
        return userService.getAuditUserList(requestDto);
    }

    /**
     * 分页获取收费规则列表(无租户)
     */
    @ApiOperation(value = "分页获取收费规则列表(无租户)", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ChargeRuleNonTenantResultDto.class)
    @PostMapping(value = "/getChargeRulePagedList")
    public PagedResultDto<ChargeRuleNonTenantResultDto> getChargeRuleNonTenant(@RequestBody ChargeRuleNonTenantGetRequestDto requestDto) {
        return chargeRuleService.getChargeRuleNonTenant(requestDto);
    }

    /**
     * 根据id获取收费规则（无租户）
     */
    @ApiOperation(value = "根据id获取收费规则（无租户）", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ChargeRuleViewResultDto.class)
    @PostMapping(value = "/getChargeRule")
    public ObjectResultDto<ChargeRuleViewResultDto> getChargeRuleByIdNonTenant(@RequestBody ChargeRuleGetRequestDto requestDto) {
        return chargeRuleService.getChargeRuleByIdNonTenant(requestDto);
    }

}
