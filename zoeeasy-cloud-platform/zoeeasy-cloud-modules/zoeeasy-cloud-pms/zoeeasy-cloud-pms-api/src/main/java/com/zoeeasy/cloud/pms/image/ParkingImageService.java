package com.zoeeasy.cloud.pms.image;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.image.dto.ParkingImageSaveRequestDto;
import com.zoeeasy.cloud.pms.image.dto.ParkingRecordImageRequestDto;

/**
 * 停车过车图像处理服务
 *
 * @author walkman
 */
public interface ParkingImageService {

    /**
     * 保存过车图像
     *
     * @param requestDto
     * @return
     */
    ResultDto savePassingImages(ParkingImageSaveRequestDto requestDto);

    /**
     * 添加停车图片
     *
     * @param requestDto
     * @return
     */
    ResultDto addParkingRecordImage(ParkingRecordImageRequestDto requestDto);

}
