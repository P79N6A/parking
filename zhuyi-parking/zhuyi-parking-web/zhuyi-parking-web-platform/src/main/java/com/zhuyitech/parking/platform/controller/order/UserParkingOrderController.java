package com.zhuyitech.parking.platform.controller.order;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zhuyitech.parking.integration.order.UserParkingOrderIntegrationService;
import com.zhuyitech.parking.integration.order.dto.request.UserParkingOrderGetRequestDto;
import com.zhuyitech.parking.integration.order.dto.request.UserParkingOrderListGetRequestDto;
import com.zhuyitech.parking.integration.order.dto.request.UserParkingOrderPagedResultRequestDto;
import com.zoeeasy.cloud.integration.order.dto.result.ParkingOrderDetailViewResultDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderViewResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户停车账单
 *
 * @author walkman
 */
@Api(value = "用户停车账单API", description = "用户停车账单API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/order")
public class UserParkingOrderController {

    @Autowired
    private UserParkingOrderIntegrationService userParkingOrderService;

    /**
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取用户停车账单列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingOrderViewResultDto.class)
    @RequestMapping(value = "/list", name = "获取用户停车账单列表", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ListResultDto<ParkingOrderViewResultDto> getUserParkingOrderList(UserParkingOrderListGetRequestDto requestDto) {
        return userParkingOrderService.getUserParkingOrderList(requestDto);
    }

    /**
     * 获取用户停车账单列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取用户停车账单列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingOrderViewResultDto.class)
    @RequestMapping(value = "/listpage", name = "获取用户停车账单列表", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public PagedResultDto<ParkingOrderViewResultDto> getUserParkingOrderPageList(UserParkingOrderPagedResultRequestDto requestDto) {
        return userParkingOrderService.getUserParkingOrderPageList(requestDto);
    }

    /**
     * 获取停车账单详情
     */
    @ApiOperation(value = "获取停车账单详情", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingOrderDetailViewResultDto.class)
    @RequestMapping(value = "/detail", name = "获取停车账单详情", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<ParkingOrderDetailViewResultDto> getUserParkingOrder(UserParkingOrderGetRequestDto requestDto) {
        return userParkingOrderService.getUserParkingOrderView(requestDto);
    }
}
