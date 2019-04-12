package com.zoeeasy.cloud.platform.controller.collector;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.gather.magnetic.dto.request.airjoy.AirJoyPushRequestDto;
import com.zoeeasy.cloud.integration.magnetic.AirJoyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "艾佳地磁数据采集接口", value = "艾佳地磁数据采集接口Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/update/status")
public class AirJoyController {

    @Autowired
    private AirJoyService airJoyService;

    @ApiOperation(value = "艾佳推送", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/businessCarports")
    public ResultDto airJoyHeartChangePush(@RequestBody AirJoyPushRequestDto requestDto) {
        return airJoyService.airJoyHeartChangePush(requestDto);
    }
}
