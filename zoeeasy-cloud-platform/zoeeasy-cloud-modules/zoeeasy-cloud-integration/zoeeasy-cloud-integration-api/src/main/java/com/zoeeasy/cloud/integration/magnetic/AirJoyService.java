package com.zoeeasy.cloud.integration.magnetic;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.gather.magnetic.dto.request.airjoy.AirJoyPushRequestDto;

/**
 * 艾佳
 *
 * @Date: 2018/9/26
 * @author: zwq
 */
public interface AirJoyService {

    /**
     * 地磁检测器状态变更推送处理
     *
     * @param requestDto
     * @return
     */
    ResultDto airJoyHeartChangePush(AirJoyPushRequestDto requestDto);
}
