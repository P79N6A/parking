package com.zoeeasy.cloud.integration.mock;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.gather.hikvision.dto.request.HikVehicleRecordSyncRequestDto;
import com.zoeeasy.cloud.integration.mock.dto.request.PusHikPassRecordRequestDto;
import com.zoeeasy.cloud.integration.mock.dto.request.PushMagneticPassRecordRequestDto;

/**
 * @author AkeemSuper
 * @date 2018/11/2 0002
 */
public interface MockPushPassRecordService {

    /**
     * 模拟推送海康过车记录
     *
     * @param requestDto
     * @return
     */
    ResultDto pushHikPassRecord(PusHikPassRecordRequestDto requestDto);

    /**
     * 模拟推送地磁过车数据
     *
     * @param requestDto
     * @return
     */
    ResultDto pushMagneticPassRecord(PushMagneticPassRecordRequestDto requestDto);

    /**
     * 模拟同步海康过车数据
     *
     * @param requestDto
     * @return
     */
    ResultDto mockSyncHikVehicleRecord(HikVehicleRecordSyncRequestDto requestDto);
}
