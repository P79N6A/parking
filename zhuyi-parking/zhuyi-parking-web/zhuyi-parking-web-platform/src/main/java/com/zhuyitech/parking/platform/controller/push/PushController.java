package com.zhuyitech.parking.platform.controller.push;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zhuyitech.parking.tool.dto.request.device.RegisterDeviceRequestDto;
import com.zhuyitech.parking.tool.dto.request.device.UserBindDeviceRequestDto;
import com.zhuyitech.parking.tool.service.api.PushDeviceService;
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
 * 推送控制器
 *
 * @author walkman
 */
@Api(value = "推送Api", description = "推送Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/push")
public class PushController {

    @Autowired
    private PushDeviceService pushDeviceService;

    /**
     * 极光推送设备注册
     *
     * @param requestDto requestDto
     * @return
     */
    @ApiOperation(value = "极光推送设备注册", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/register", name = "极光推送设备注册", method = RequestMethod.POST)
    @ApiVersion(2)
    public ResultDto registerDevice(RegisterDeviceRequestDto requestDto) {
        return pushDeviceService.registerDevice(requestDto);
    }

    /**
     * 登录绑定用户设备号
     *
     * @param requestDto requestDto
     * @description
     */
    @ApiOperation(value = "登录绑定用户设备号", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/bind", name = "登录绑定用户设备号", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ResultDto bindDevice(UserBindDeviceRequestDto requestDto) {
        return pushDeviceService.bindDevice(requestDto);
    }

}
