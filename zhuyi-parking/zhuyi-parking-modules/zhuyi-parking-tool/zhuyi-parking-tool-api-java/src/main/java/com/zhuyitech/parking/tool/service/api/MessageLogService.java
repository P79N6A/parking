package com.zhuyitech.parking.tool.service.api;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.tool.dto.request.messagelog.MessageLogRequestAddDto;

/**
 * 第三方接口调用日志
 *
 * @author AkeemSuper
 * @date 2018/4/10 0010
 */
public interface MessageLogService {

    /**
     * 添加第三方接口调用日志
     *
     * @param requestAddDto 请求参数
     * @return 返回
     */
    ResultDto addMessageLog(MessageLogRequestAddDto requestAddDto);
}
