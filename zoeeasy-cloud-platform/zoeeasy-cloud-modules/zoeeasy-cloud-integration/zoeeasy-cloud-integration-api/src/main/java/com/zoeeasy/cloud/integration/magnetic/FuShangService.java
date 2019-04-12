package com.zoeeasy.cloud.integration.magnetic;

import com.zoeeasy.cloud.gather.magnetic.dto.request.fushang.FuShangHeartBeatPushRequestDto;
import com.zoeeasy.cloud.gather.magnetic.dto.request.fushang.FuShangParkStatusPushRequestDto;
import com.zoeeasy.cloud.gather.magnetic.dto.request.fushang.FuShangRegisterPushRequestDto;
import com.zoeeasy.cloud.gather.magnetic.dto.result.SendResultSto;

/**
 * 富尚
 *
 * @Date: 2018/12/5
 * @author: lhj
 */
public interface FuShangService {

    /**
     * 推送车位状态数据处理
     *
     * @param requestDto
     * @return
     */
    SendResultSto fuShangParkStatusPush(FuShangParkStatusPushRequestDto requestDto);

    /**
     * 推送地磁心跳数据
     *
     * @param requestDto
     * @return
     */
    SendResultSto fuShangHeartBeatPush(FuShangHeartBeatPushRequestDto requestDto);

    /**
     * 推送注册数据
     *
     * @param requestDto
     * @return
     */
    SendResultSto fuShangRegisterPush(FuShangRegisterPushRequestDto requestDto);
}
