package com.zoeeasy.cloud.platform.controller.appoint;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.core.annotation.AuditLog;
import com.zoeeasy.cloud.core.cst.PermissionDefinitions;
import com.zoeeasy.cloud.core.enums.BusinessType;
import com.zoeeasy.cloud.core.enums.OperatorType;
import com.zoeeasy.cloud.integration.appoint.AppointOrderManagerIntegrationService;
import com.zoeeasy.cloud.integration.appoint.AppointOrderPlatformIntegrationService;
import com.zoeeasy.cloud.order.appoint.AppointmentOrderManagerService;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderGetDetailRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderPagedRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderRemarkAddRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointOrderGetDetailResultDto;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointOrderPageResultDto;
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
 * 预约订单后台API
 *
 * @author zwq
 */
@Api(value = "预约订单后台API", tags = "预约订单后台API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@ApiVersion(1)
@ApiSigned
@RequestMapping("/cloud/appoint")
@RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_APPOINT_ORDER)
public class ParkingAppointmentOrderController {

    @Autowired
    private AppointmentOrderManagerService parkingAppointmentOrderService;

    @Autowired
    private AppointOrderManagerIntegrationService appointmentManagerService;

    @Autowired
    private AppointOrderPlatformIntegrationService appointIntegrationService;

    /**
     * 查询预约订单
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "查询预约订单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AppointOrderPageResultDto.class)
    @PostMapping(value = "/getPageList")
    public PagedResultDto<AppointOrderPageResultDto> getPageList(@RequestBody AppointOrderPagedRequestDto requestDto) {
        return appointIntegrationService.getOrderPagedListAdmin(requestDto);
    }

    /**
     * 预约订单详情
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "预约订单详情", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AppointOrderGetDetailResultDto.class)
    @PostMapping(value = "/getOrderDetail")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_APPOINT_ORDER_VIEW)
    public ObjectResultDto<AppointOrderGetDetailResultDto> getOrderDetail(@RequestBody AppointOrderGetDetailRequestDto requestDto) {
        return appointmentManagerService.getOrderDetail(requestDto);
    }

    /**
     * 添加备注
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "添加备注", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/addRemark")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_APPOINT_ORDER_EDIT)
    @AuditLog(title = "预约订单后台", value = "添加备注", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto addRemark(@RequestBody AppointOrderRemarkAddRequestDto requestDto) {
        return parkingAppointmentOrderService.addRemark(requestDto);
    }

}
