package com.zoeeasy.cloud.sys.auditlog;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.sys.auditlog.dto.*;

/**
 * @author walkman
 */
public interface AuditLogService {

    /**
     * 分页获取操作日志列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<AuditLogResultDto> getAuditLogPagedList(AuditLogPagedRequestDto requestDto);

    /**
     * 新增操作日志
     *
     * @param requestDto
     * @return
     */
    ResultDto addAuditLog(AuditLogAddRequestDto requestDto);

    /**
     * 更新操作日志
     *
     * @param requestDto
     * @return
     */
    ResultDto updateAuditLog(AuditLogUpdateRequestDto requestDto);

    /**
     * 删除操作日志
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteAuditLog(AuditLogDeleteRequestDto requestDto);

    /**
     * 获取操作日志
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<AuditLogResultDto> getAuditLog(AuditLogGetRequestDto requestDto);

    /**
     * 获取操作类型
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ComboboxItemDto> getType(AuditLogTypeGetRequestDto requestDto);


}
