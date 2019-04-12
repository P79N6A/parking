package com.zoeeasy.cloud.platform.controller.order;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.core.annotation.AuditLog;
import com.zoeeasy.cloud.core.cst.PermissionDefinitions;
import com.zoeeasy.cloud.core.enums.BusinessType;
import com.zoeeasy.cloud.core.enums.OperatorType;
import com.zoeeasy.cloud.integration.common.CommonIntegrationService;
import com.zoeeasy.cloud.integration.order.ParkingOrderManagerIntegrationService;
import com.zoeeasy.cloud.order.parking.ParkingOrderManagerService;
import com.zoeeasy.cloud.order.parking.dto.request.*;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderSearchDetailResultDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderSearchResultDto;
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
 * 停车账单API
 *
 * @Date: 2018/6/27
 * @author: wh
 */
@Api(value = "停车账单API", description = "停车账单API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@ApiVersion(1)
@ApiSigned
@RequestMapping("/cloud/parkingOrder")
@RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_ORDER)
public class ParkingOrderController {

    @Autowired
    private ParkingOrderManagerIntegrationService parkingOrderManagerIntegrationService;

    @Autowired
    private ParkingOrderManagerService parkingOrderManagerService;

    @Autowired
    private CommonIntegrationService commonIntegrationService;

    /**
     * 停车账单查询
     *
     * @param requestDto ParkingOrderSearchRequestDto
     * @return PagedResultDto<ParkingOrderSearchResultDto>
     */
    @ApiOperation(value = "停车账单查询", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingOrderSearchResultDto.class)
    @PostMapping(value = "/parkingOrderSearch")
    public PagedResultDto<ParkingOrderSearchResultDto> parkingOrderSearch(@RequestBody ParkingOrderSearchRequestDto requestDto) {
        return parkingOrderManagerIntegrationService.getParkingOrderPageList(requestDto);
    }

    /**
     * 停车账单详情
     *
     * @param requestDto ParkingOrderDetailGetRequestDto
     * @return ObjectResultDto<ParkingOrderSearchDetailResultDto>
     */
    @ApiOperation(value = "停车账单详情", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingOrderDetailGetRequestDto.class)
    @PostMapping(value = "/parkingOrderDetail")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_ORDER_VIEW)
    public ObjectResultDto<ParkingOrderSearchDetailResultDto> parkingOrderDetail(@RequestBody ParkingOrderDetailGetRequestDto requestDto) {
        return parkingOrderManagerIntegrationService.getParkingOrderSearchDetailView(requestDto);
    }

    /**
     * 停车账单添加备注
     *
     * @param requestDto ParkingOrderAddRemarkRequestDto
     * @return ResultDto
     */
    @ApiOperation(value = "停车账单添加备注", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/setRemark")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_ORDER_EDIT)
    @AuditLog(title = "停车账单API", value = "停车账单添加备注", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto setRemark(@RequestBody ParkingOrderAddRemarkRequestDto requestDto) {
        return parkingOrderManagerService.setParkingOrderRemark(requestDto);
    }

    /**
     * 订单状态列表
     */
    @ApiOperation(value = "订单状态列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/paringOrderStatus")
    public ListResultDto<ComboboxItemDto> paringOrderStatus(@RequestBody ParkingOrderStatusTypeRequestDto requestDto) {
        return commonIntegrationService.paringOrderStatus(requestDto);
    }

    /**
     * 订单类型列表
     */
    @ApiOperation(value = "订单类型列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/paringOrderType")
    public ListResultDto<ComboboxItemDto> paringOrderType(@RequestBody ParkingOrderTypeRequestDto requestDto) {
        return commonIntegrationService.paringOrderType(requestDto);
    }

    /**
     * 人工添加停车账单
     */
    @ApiOperation(value = "人工添加停车账单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/addParkingOrder")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_ORDER_CREATE)
    @AuditLog(title = "停车账单API", value = "人工添加停车账单", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto addParkingOrder(@RequestBody ParkingOrderAddRequestDto requestDto) {
        return parkingOrderManagerIntegrationService.addParkingOrder(requestDto);
    }

    /**
     * 修改停车账单
     */
    @ApiOperation(value = "人工修改停车账单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/updateParkingOrder")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_ORDER_EDIT)
    @AuditLog(title = "停车账单API", value = "人工修改停车账单", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto updateParkingOrder(@RequestBody ParkingOrderUpdateRequestDto requestDto) {
        return parkingOrderManagerService.updateParkingOrder(requestDto);
    }

    /**
     * 删除停车账单
     */
    @ApiOperation(value = "删除停车账单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/deleteParkingOrder")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_ORDER_DELETE)
    @AuditLog(title = "停车账单API", value = "删除停车账单", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteParkingOrder(@RequestBody ParkingOrderDeleteRequestDto requestDto) {
        return parkingOrderManagerService.deleteParkingOrder(requestDto);
    }
}
