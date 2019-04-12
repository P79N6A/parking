package com.zhuyitech.parking.tool.service.impl;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.tool.domain.MessageLog;
import com.zhuyitech.parking.tool.dto.request.messagelog.MessageLogRequestAddDto;
import com.zhuyitech.parking.tool.service.MessageLogCrudService;
import com.zhuyitech.parking.tool.service.api.MessageLogService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author AkeemSuper
 * @date 2018/4/10 0010
 */
@Service("messageLogService")
@Slf4j
public class MessageLogServiceImpl implements MessageLogService {

    @Autowired
    private MessageLogCrudService messageLogCrudService;

    /**
     * 添加第三方接口调用日志
     *
     * @param requestAddDto 请求参数
     * @return 返回
     */
    @Override
    public ResultDto addMessageLog(MessageLogRequestAddDto requestAddDto) {
        ResultDto resultDto = new ResultDto();
        try {
            MessageLog messageLog = new MessageLog();
            if (StringUtils.isNotBlank(requestAddDto.getIp())) {
                messageLog.setIp(requestAddDto.getIp());
            }
            if (StringUtils.isNotBlank(requestAddDto.getMessageType())) {
                messageLog.setMessageType(requestAddDto.getMessageType());
            }
            if (StringUtils.isNotBlank(requestAddDto.getParameter())) {
                messageLog.setParameter(requestAddDto.getParameter());
            }
            if (null != requestAddDto.getRequestTime()) {
                messageLog.setRequestTime(requestAddDto.getRequestTime());
            }
            if (null != requestAddDto.getResponseTime()) {
                messageLog.setResponseTime(requestAddDto.getResponseTime());
            }
            if (StringUtils.isNotBlank(requestAddDto.getResult())) {
                messageLog.setResult(requestAddDto.getResult());
            }
            if (null != requestAddDto.getStatus()) {
                messageLog.setStatus(requestAddDto.getStatus());
            }
            if (StringUtils.isNotBlank(requestAddDto.getUrl())) {
                messageLog.setUrl(requestAddDto.getUrl());
            }
            messageLogCrudService.insert(messageLog);
            resultDto.success();
        } catch (Exception e) {
            log.error("添加第三方接口日志调用失败" + e.getMessage());
            resultDto.failed(e.getMessage());
        }
        return resultDto;
    }
}
