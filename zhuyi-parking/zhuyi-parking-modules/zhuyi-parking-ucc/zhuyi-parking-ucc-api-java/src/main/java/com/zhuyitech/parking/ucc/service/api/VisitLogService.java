package com.zhuyitech.parking.ucc.service.api;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.ucc.dto.request.visitlog.*;
import com.zhuyitech.parking.ucc.dto.result.VisitLogResultDto;

/**
 * @author walkman
 */
public interface VisitLogService {

    /**
     * 新增登录日志
     *
     * @param requestDto
     * @return
     */
    ResultDto addVisitLog(VisitLogAddRequestDto requestDto);

    /**
     * 删除登录日志
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteVisitLog(VisitLogDeleteRequestDto requestDto);

    /**
     * 更新登录日志
     *
     * @param requestDto
     * @return
     */
    ResultDto updateVisitLog(VisitLogUpdateRequestDto requestDto);

    /**
     * 获取登录日志
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<VisitLogResultDto> getVisitLog(VisitLogGetRequestDto requestDto);

    /**
     * 获取登录日志列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<VisitLogResultDto> getVisitLogList(VisitLogListGetRequestDto requestDto);

    /**
     * 分页获取登录日志列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<VisitLogResultDto> getVisitLogPagedList(VisitLogQueryPagedResultRequestDto requestDto);

}
