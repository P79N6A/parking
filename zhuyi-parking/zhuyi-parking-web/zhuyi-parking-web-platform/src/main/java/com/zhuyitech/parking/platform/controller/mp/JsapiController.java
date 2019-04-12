package com.zhuyitech.parking.platform.controller.mp;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zhuyitech.parking.ucc.dto.request.jsapi.JsapiParkingOrderGetRequestDto;
import com.zhuyitech.parking.ucc.dto.request.jsapi.JsapiParkingOrderPagedRequestDto;
import com.zhuyitech.parking.ucc.service.api.JsapiRelatedService;
import com.zoeeasy.cloud.integration.order.dto.result.ParkingOrderDetailViewResultDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderViewResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公众号相关服务
 *
 * @author AkeemSuper
 * @date 2018/11/23 0023
 */
@Api(value = "公众号相关服务Api", description = "公众号相关服务Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/jsapi")
@Slf4j
@ApiVersion(1)
public class JsapiController {

    @Autowired
    private JsapiRelatedService jsapiRelatedService;

    /**
     * 获取未支付停车账单列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取未支付停车账单列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingOrderViewResultDto.class)
    @RequestMapping(value = "/listpage", name = "获取未支付停车账单列表", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public PagedResultDto<ParkingOrderViewResultDto> getParkingOrderPageList(JsapiParkingOrderPagedRequestDto requestDto) {
        return jsapiRelatedService.getParkingOrderPageList(requestDto);
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
    public ObjectResultDto<ParkingOrderDetailViewResultDto> getParkingOrder(JsapiParkingOrderGetRequestDto requestDto) {
        return jsapiRelatedService.getParkingOrderView(requestDto);
    }

}
