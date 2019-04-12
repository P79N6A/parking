package com.zoeeasy.cloud.platform.controller.mock;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.collect.core.CollectOperateService;
import com.zoeeasy.cloud.collect.dto.request.OpenStrobeRequestDto;
import com.zoeeasy.cloud.collect.dto.request.PaymentNotifyRequestDto;
import com.zoeeasy.cloud.collect.dto.request.QueryPriceRequestDto;
import com.zoeeasy.cloud.integration.order.ParkingOrderIntegrationService;
import com.zoeeasy.cloud.message.payload.PaymentNotifyCallBackPayload;
import com.zoeeasy.cloud.message.payload.QueryPriceCallBackPayload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wf
 */
@Api(tags = "Test No Bug", value = "Test No Bug API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/noBug")
public class MockTestController {
    @Autowired
    private CollectOperateService collectOperateService;

    @Autowired
    private ParkingOrderIntegrationService parkingOrderIntegrationService;

    @ApiOperation(value = "查询价格", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/queryPrice")
    public ResultDto queryPrice(@RequestBody QueryPriceRequestDto requestDto) {
        return collectOperateService.queryPrice(requestDto);
    }

    @ApiOperation(value = "支付回传", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/paymentNotify")
    public ResultDto paymentNotify(@RequestBody PaymentNotifyRequestDto requestDto) {
        return collectOperateService.paymentNotify(requestDto);
    }

    @ApiOperation(value = "远程开闸", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/openStrobe")
    public ResultDto openStrobe(@RequestBody OpenStrobeRequestDto requestDto) {
        return collectOperateService.openStrobe(requestDto);
    }

    @ApiOperation(value = "同步第三方平台账单状态", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/test")
    public ResultDto syncOrderStatus(PaymentNotifyCallBackPayload paymentNotifyPayload){
        return parkingOrderIntegrationService.syncOrderStatus(paymentNotifyPayload);
    }

    @ApiOperation(value = "根据道闸查询价格返回消息更新账单", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/test1")
    public ResultDto updateOrderStatus(@RequestBody QueryPriceCallBackPayload payload){
        return parkingOrderIntegrationService.updateOrderStatus(payload);
    }

}
