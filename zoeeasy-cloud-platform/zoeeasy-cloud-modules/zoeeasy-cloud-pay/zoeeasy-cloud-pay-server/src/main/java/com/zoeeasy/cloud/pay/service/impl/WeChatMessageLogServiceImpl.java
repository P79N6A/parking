package com.zoeeasy.cloud.pay.service.impl;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pay.domain.WeixinMessageLogEntity;
import com.zoeeasy.cloud.pay.service.WeixinMessageLogCrudService;
import com.zoeeasy.cloud.pay.trade.WeChatMessageLogService;
import com.zoeeasy.cloud.pay.trade.dto.request.message.WeChatMessageLogAddRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zwq
 */
@Service(value = "weChatMessageLogService")
@Slf4j
public class WeChatMessageLogServiceImpl implements WeChatMessageLogService {

    @Autowired
    private WeixinMessageLogCrudService weixinMessageLogCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 新增通知日志
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addMessageLog(WeChatMessageLogAddRequestDto requestDto) {

        ResultDto resultDto = new ResultDto();
        try {

            WeixinMessageLogEntity auditLog = modelMapper.map(requestDto, WeixinMessageLogEntity.class);
            weixinMessageLogCrudService.insert(auditLog);
            resultDto.success();
        } catch (Exception e) {
            log.error("操作日志添加失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;

    }
}
