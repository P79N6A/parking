package com.zoeeasy.cloud.platform.controller.collector;

import com.zoeeasy.cloud.gather.magnetic.dto.request.fushang.FuShangHeartBeatPushRequestDto;
import com.zoeeasy.cloud.gather.magnetic.dto.request.fushang.FuShangParkStatusPushRequestDto;
import com.zoeeasy.cloud.gather.magnetic.dto.request.fushang.FuShangRegisterPushRequestDto;
import com.zoeeasy.cloud.gather.magnetic.dto.result.SendResultSto;
import com.zoeeasy.cloud.integration.magnetic.FuShangService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date: 2018/12/5
 * @author: lhj
 */
@Api(tags = "富尚地磁数据采集接口", value = "富尚地磁数据采集接口Api", description = "富尚地磁数据采集接口Api",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/collector/fushang")
public class FuShangController {

    @Autowired
    private FuShangService fuShangService;

    /**
     * 富尚推送车位状态数据
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "富尚推送车位状态数据", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = SendResultSto.class)
    @PostMapping(value = "/parkStatus")
    public SendResultSto fuShangParkStatusPush(@RequestBody FuShangParkStatusPushRequestDto requestDto) {
        return fuShangService.fuShangParkStatusPush(requestDto);
    }

    /**
     * 富尚推送心跳数据
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "富尚推送心跳数据", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = SendResultSto.class)
    @PostMapping(value = "/heartBeat")
    public SendResultSto fuShangHeartBeatPush(@RequestBody FuShangHeartBeatPushRequestDto requestDto) {
        return fuShangService.fuShangHeartBeatPush(requestDto);
    }

    /**
     * 富尚推送注册数据
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "富尚推送注册数据", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = SendResultSto.class)
    @PostMapping(value = "/register")
    public SendResultSto fuShangRegisterPush(@RequestBody FuShangRegisterPushRequestDto requestDto) {
        return fuShangService.fuShangRegisterPush(requestDto);
    }
}
