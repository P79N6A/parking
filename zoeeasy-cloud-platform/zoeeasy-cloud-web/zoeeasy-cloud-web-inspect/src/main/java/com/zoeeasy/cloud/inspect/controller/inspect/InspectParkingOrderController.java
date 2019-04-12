package com.zoeeasy.cloud.inspect.controller.inspect;

import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.integration.inspect.InspectParkOrderIntegrationService;
import com.zoeeasy.cloud.order.inspect.InspectParkingOrderService;
import com.zoeeasy.cloud.order.inspect.dto.request.InspectParkingOrderListPageRequestDto;
import com.zoeeasy.cloud.order.inspect.dto.request.InspectUpdateParkingOrderRequestDto;
import com.zoeeasy.cloud.order.inspect.dto.result.InspectParkingOrderResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 巡检停车订单API
 *
 * @author zwq
 */
@Api(tags = "巡检停车订单API", value = "巡检停车订单API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@ApiVersion(1)
@ApiSigned
@RequestMapping("/cloud/inspect")
public class InspectParkingOrderController {

    @Autowired
    private InspectParkOrderIntegrationService inspectParkOrderIntegrationService;

    @Autowired
    private InspectParkingOrderService inspectParkingOrderService;

    /**
     * 获取巡检停车账单列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取巡检停车账单列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = InspectParkingOrderResultDto.class)
    @PostMapping(value = "/orderListPage", name = "获取用户停车账单列表")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "inspect_parking_id", required = true, paramType = "header",
                            dataType = "string", value = "parkingId header", defaultValue = "inspect_parking_id:")
            }
    )
    public PagedResultDto<InspectParkingOrderResultDto> getParkingOrderPageList(@RequestBody InspectParkingOrderListPageRequestDto requestDto) {
        return inspectParkOrderIntegrationService.getParkingOrderPageList(requestDto);
    }

    /**
     * 更新未支付订单支付渠道
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "更新未支付订单支付渠道", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/updateOrder", name = "更新未支付订单支付渠道")
    public ResultDto updateParkingOrder(@RequestBody InspectUpdateParkingOrderRequestDto requestDto) {
        return inspectParkOrderIntegrationService.payParkingOrderInspect(requestDto);
    }
}
