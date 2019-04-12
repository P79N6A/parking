package com.zoeeasy.cloud.platform.controller.push;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.core.annotation.AuditLog;
import com.zoeeasy.cloud.core.enums.BusinessType;
import com.zoeeasy.cloud.core.enums.OperatorType;
import com.zoeeasy.cloud.notify.push.PushDeviceService;
import com.zoeeasy.cloud.notify.push.dto.request.RegisterDeviceRequestDto;
import com.zoeeasy.cloud.notify.push.dto.request.UserBindDeviceRequestDto;
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
 * 推送控制器
 *
 * @author walkman
 */
@RestController
@ApiVersion(1)
@ApiSigned
@Api(value = "推送Api", tags = "推送Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/cloud/push")
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
    @PostMapping(value = "/register", name = "极光推送设备注册")
    @ApiVersion(2)
    @AuditLog(title = "推送Api", value = "极光推送设备注册", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto registerDevice(@RequestBody RegisterDeviceRequestDto requestDto) {
        return pushDeviceService.registerDevice(requestDto);
    }

    /**
     * 登录绑定用户设备号
     *
     * @param requestDto requestDto
     * @description
     */
    @ApiOperation(value = "登录绑定用户设备号", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/bind", name = "登录绑定用户设备号")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ResultDto bindDevice(@RequestBody UserBindDeviceRequestDto requestDto) {
        return pushDeviceService.bindDevice(requestDto);
    }
}
