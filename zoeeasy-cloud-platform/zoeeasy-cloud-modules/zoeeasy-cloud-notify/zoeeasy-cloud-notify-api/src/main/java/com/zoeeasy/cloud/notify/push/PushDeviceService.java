package com.zoeeasy.cloud.notify.push;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.notify.push.dto.request.InvalidPushDeviceRequestDto;
import com.zoeeasy.cloud.notify.push.dto.request.RegisterDeviceRequestDto;
import com.zoeeasy.cloud.notify.push.dto.request.UserBindDeviceRequestDto;

/**
 * 消息推送设备服务
 *
 * @author walkamn
 */
public interface PushDeviceService {

    /**
     * 注册设备号
     *
     * @param requestDto requestDto
     * @return ResultDto
     * @description 注册设备号
     */
    ResultDto registerDevice(RegisterDeviceRequestDto requestDto);

    /**
     * 登录绑定用户设备号
     *
     * @param requestDto requestDto
     * @return ResultDto
     * @description
     */
    ResultDto bindDevice(UserBindDeviceRequestDto requestDto);

    /**
     * 登出时将推送令牌设为已失效
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto invalidDevice(InvalidPushDeviceRequestDto requestDto);

}
