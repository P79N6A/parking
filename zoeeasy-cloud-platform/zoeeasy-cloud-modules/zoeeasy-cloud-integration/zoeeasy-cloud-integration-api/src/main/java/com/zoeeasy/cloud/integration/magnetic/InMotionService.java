package com.zoeeasy.cloud.integration.magnetic;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.gather.magnetic.dto.request.inmotion.InMotionHeartBeatPushRequestDto;

/**
 * 易姆讯
 *
 * @Date: 2018/9/26
 * @author: zwq
 */
public interface InMotionService {

    /**
     * 地磁检测器心跳处理
     *
     * @param requestDto
     * @return
     */
    ResultDto inMotionHeartBeat(InMotionHeartBeatPushRequestDto requestDto);
}
