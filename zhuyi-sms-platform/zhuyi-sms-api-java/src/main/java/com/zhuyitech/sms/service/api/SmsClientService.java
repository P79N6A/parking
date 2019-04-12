package com.zhuyitech.sms.service.api;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.sms.dto.request.*;
import com.zhuyitech.sms.dto.result.SmsClientResultDto;

/**
 * 客户短信系统
 *
 * @author walkman
 */
public interface SmsClientService {

    /**
     * 添加短信客户端
     *
     * @param requestDto
     * @return
     */
    ResultDto addClient(ClientAddRequestDto requestDto);

    /**
     * 修改短信客户端
     *
     * @param requestDto
     * @return
     */
    ResultDto updateClient(ClientUpdateRequestDto requestDto);

    /**
     * 删除短信客户端
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteClient(ClientDeleteRequestDto requestDto);

    /**
     * 查询短信客户端
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<SmsClientResultDto> getClient(ClientGetRequestDto requestDto);

    /**
     * 分页查询短信客户端
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<SmsClientResultDto> queryClient(ClientQueryPagedResultRequestDto requestDto);
}
