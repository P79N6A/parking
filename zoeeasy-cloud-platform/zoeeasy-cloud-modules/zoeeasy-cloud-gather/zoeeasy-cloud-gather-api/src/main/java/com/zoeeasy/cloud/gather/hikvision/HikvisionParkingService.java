package com.zoeeasy.cloud.gather.hikvision;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.gather.hikvision.dto.request.*;
import com.zoeeasy.cloud.gather.hikvision.dto.result.HikvisionVehicleRecordResultDto;
import com.zoeeasy.cloud.gather.hikvision.dto.result.PassingImageFetchResultDto;
import com.zoeeasy.cloud.gather.hikvision.dto.result.PassingVehicleCollateTimeResultDto;
import com.zoeeasy.cloud.gather.hikvision.dto.result.PassingVehicleSyncTimeResultDto;

/**
 * 海康平台数据相关服务
 *
 * @author walkman
 */
public interface HikvisionParkingService {

    /**
     * 获取最后一次海康接口同步结束时间
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<PassingVehicleSyncTimeResultDto> getLastSyncEndTime(PassingVehicleSyncTimeGetRequestDto requestDto);

    /**
     * 获取最后一次海康接口校对结束时间
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<PassingVehicleCollateTimeResultDto> getLastCollateEndTime(PassingVehicleCollateTimeGetRequestDto requestDto);

    /**
     * 同步海康平台过车数据
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    ResultDto syncHikvisionVehicleRecord(HikVehicleRecordSyncRequestDto requestDto);

    /**
     * 查询海康过车记录
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<HikvisionVehicleRecordResultDto> getHikvisionVehicleRecord(HikvisionVehicleRecordGetRequestDto requestDto);

    /**
     * 添加海康过车记录
     *
     * @return
     */
    ResultDto addHikvisionVehicleRecord(HikvisionVehicleRecordAddRequestDto requestDto);

    /**
     * 更新海康过车记录处理状态
     */
    ResultDto updateProcessStatus(HikVehicleRecordUpProcessStatusDateRequestDto requestDto);

    /**
     * 获取海康过车图像
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<PassingImageFetchResultDto> fetchPassingImage(PassingImageFetchRequestDto requestDto);

}
