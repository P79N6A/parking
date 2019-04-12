package com.zoeeasy.cloud.open.controller.mock;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.integration.open.TerminateParkingOrderService;
import com.zoeeasy.cloud.integration.open.dto.request.AnyParkingOrderPlaceRequestDto;
import com.zoeeasy.cloud.integration.open.dto.request.CloudOrderCallbackRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AkeemSuper
 * @date 2018/12/7 0007
 */
@Api(tags = "测试", value = "测试Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@ApiVersion(1)
@ApiSigned
@RequestMapping("/cloud/open/mock")
public class MockController {

    @Autowired
    private TerminateParkingOrderService terminateParkingOrderService;

    @ApiOperation(value = "账单回调", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/orderCallBack")
    public ResultDto orderCallBack(CloudOrderCallbackRequestDto requestDto) {
        return terminateParkingOrderService.orderCallback(requestDto);
    }

    @ApiOperation(value = "添加账单", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/anyAddOrder")
    public ResultDto anyAddOrder(AnyParkingOrderPlaceRequestDto requestDto) {
        return terminateParkingOrderService.placeAynParkingOrder(requestDto);
    }
}
