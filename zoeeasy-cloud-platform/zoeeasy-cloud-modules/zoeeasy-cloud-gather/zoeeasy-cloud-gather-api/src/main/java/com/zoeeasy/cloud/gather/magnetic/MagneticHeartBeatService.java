package com.zoeeasy.cloud.gather.magnetic;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.gather.magnetic.dto.request.magneticdetector.*;
import com.zoeeasy.cloud.gather.magnetic.dto.result.ChangeTypeResultDto;
import com.zoeeasy.cloud.gather.magnetic.dto.result.GetLastMagneticHeartBeatAddResultDto;
import com.zoeeasy.cloud.gather.magnetic.dto.result.MagneticReportRecordQueryPageResultDto;

/**
 * 设备心跳服务
 *
 * @Date: 2018/9/21
 * @author: lhj
 */
public interface MagneticHeartBeatService {

    /**
     * 地磁心跳数据添加
     *
     * @param requestDto
     * @return
     */
    ResultDto addMagneticHeartBeat(MagneticHeartBeatAddRequestDto requestDto);

    /**
     * 地磁检测记录添加
     *
     * @param requestDto
     * @return
     */
    ResultDto magneticReportRecordAdd(MagneticReportRecordAddRequestDto requestDto);

    /**
     * 分页查询地磁检测记录
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<MagneticReportRecordQueryPageResultDto> getMagneticReportRecordPageList(MagneticReportRecordQueryPageRequestDto requestDto);

    /**
     * 出车类型列表
     */
    ListResultDto<ChangeTypeResultDto> getPassingTypeList(ChangeTypeListRequestDto requestDto);

    /**
     * 地磁检测器状态变更推送数据添加
     *
     * @param requestDto
     * @return
     */
    ResultDto addMagneticStatusRecord(MagneticStatusRecordAddRequestDto requestDto);

    /**
     * 查询当前地磁检测器最近一条心跳数据
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<GetLastMagneticHeartBeatAddResultDto> getLastMagneticHeartBeat(GetLastMagneticHeartBeatRequestDto requestDto);

}
