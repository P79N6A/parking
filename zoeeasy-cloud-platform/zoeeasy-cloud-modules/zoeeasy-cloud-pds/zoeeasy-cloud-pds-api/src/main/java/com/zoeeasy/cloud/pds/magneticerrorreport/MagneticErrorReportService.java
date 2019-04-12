package com.zoeeasy.cloud.pds.magneticerrorreport;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pds.magneticerrorreport.dto.request.MagneticErrorReportListResultRequestDto;
import com.zoeeasy.cloud.pds.magneticerrorreport.dto.request.MagneticErrorReportQueryPagedResultRequestDto;
import com.zoeeasy.cloud.pds.magneticerrorreport.dto.request.MagneticErrorSaveRequestDto;
import com.zoeeasy.cloud.pds.magneticerrorreport.dto.result.MagneticErrorReportResultDto;

/**
 * 地磁误报处理记录
 *
 * @Date: 2018/10/18
 * @author: lhj
 */
public interface MagneticErrorReportService {
    /**
     * 获取地磁误报处理记录列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<MagneticErrorReportResultDto> getMagneticErrorReportList(MagneticErrorReportListResultRequestDto requestDto);

    /**
     * 分页查询误报处理记录列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<MagneticErrorReportResultDto> getMagneticErrorReportPagedList(MagneticErrorReportQueryPagedResultRequestDto requestDto);

    /**
     * 保存地磁误报记录
     *
     * @param requestDto
     * @return
     */
    ResultDto save(MagneticErrorSaveRequestDto requestDto);
}
