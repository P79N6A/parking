package com.zoeeasy.cloud.platform.controller.collector;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.gather.magnetic.dto.request.inmotion.InMotionHeartBeatPushRequestDto;
import com.zoeeasy.cloud.integration.magnetic.InMotionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "易姆迅地磁数据采集接口", value = "易姆迅地磁数据采集接口Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/collector/inmotion")
public class InMotionController {

    @Autowired
    private InMotionService inMotionService;

    @ApiOperation(value = "易姆迅推送", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/inmotion")
    public ResultDto inMotionHeartBeat(@RequestBody InMotionHeartBeatPushRequestDto requestDto) {
        return inMotionService.inMotionHeartBeat(requestDto);
    }
}
