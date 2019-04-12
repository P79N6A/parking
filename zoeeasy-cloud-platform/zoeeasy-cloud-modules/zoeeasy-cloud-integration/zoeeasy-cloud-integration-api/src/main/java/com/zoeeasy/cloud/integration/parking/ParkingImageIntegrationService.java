package com.zoeeasy.cloud.integration.parking;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.message.payload.HikPassingImageFetchPayload;

/**
 * 停车图像集成服务
 *
 * @author walkman
 * @since 2018/11/20
 */
public interface ParkingImageIntegrationService {

    /**
     * 处理海康过车图像消息
     *
     * @param payload
     * @return
     */
    ResultDto processHikPassingImage(HikPassingImageFetchPayload payload);
}
