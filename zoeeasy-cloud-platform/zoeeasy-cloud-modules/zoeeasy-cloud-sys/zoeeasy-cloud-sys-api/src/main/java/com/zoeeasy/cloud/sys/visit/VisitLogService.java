package com.zoeeasy.cloud.sys.visit;

import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.sys.visit.dto.request.VisitLogAddRequestDto;
import com.zoeeasy.cloud.sys.visit.dto.request.VisitLogQueryPageRequestDto;
import com.zoeeasy.cloud.sys.visit.dto.result.VisitLogResultDto;

/**
 * 用户登录日志服务
 *
 * @author AkeemSuper
 * @date 2019/2/21 0021
 */
public interface VisitLogService {

    /**
     * 添加用户登录日志
     *
     * @param requestDto
     * @return
     */
    ResultDto addVisitLog(VisitLogAddRequestDto requestDto);

    /**
     * 分页获取登录日志
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<VisitLogResultDto> queryPage(VisitLogQueryPageRequestDto requestDto);
}
