package com.zoeeasy.cloud.platform.controller.mock;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.integration.order.ParkingOrderIntegrationService;
import com.zoeeasy.cloud.integration.trade.TradeChargeIntegrationService;
import com.zoeeasy.cloud.integration.trade.dto.ParkingOrderChargeRequestDto;
import com.zoeeasy.cloud.message.payload.OrderConfirmCallbackPayload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试", value = "测试Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/mock")
public class MockTradeController {

    @Autowired
    private TradeChargeIntegrationService tradeChargeIntegrationService;

    @Autowired
    private ParkingOrderIntegrationService parkingOrderIntegrationService;

    @ApiOperation(value = "扫码收款测试", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/chargeOrder")
    public ResultDto chargeParkingOrder(ParkingOrderChargeRequestDto requestDto) {
        return tradeChargeIntegrationService.chargeParkingOrder(requestDto);
    }

    @ApiOperation(value = "第三方支付通知", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/thirdNotify")
    public ResultDto thirdOrderNotify(@RequestBody OrderConfirmCallbackPayload requestDto) {
        return parkingOrderIntegrationService.processOrderCallbackMessage(requestDto);
    }

}
