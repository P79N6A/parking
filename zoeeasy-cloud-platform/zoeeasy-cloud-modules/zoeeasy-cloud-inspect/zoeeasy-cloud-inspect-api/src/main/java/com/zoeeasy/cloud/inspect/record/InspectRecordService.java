package com.zoeeasy.cloud.inspect.record;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.inspect.record.dto.request.InspectRecordSaveRequestDto;

/**
 * 巡检记录服务
 *
 * @author zwq
 */
public interface InspectRecordService {

    /**
     * 保存巡检记录
     *
     * @param requestDto
     * @return
     */
    ResultDto saveInspectRecord(InspectRecordSaveRequestDto requestDto);

}
