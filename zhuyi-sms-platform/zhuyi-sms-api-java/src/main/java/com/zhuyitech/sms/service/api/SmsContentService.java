package com.zhuyitech.sms.service.api;

import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zhuyitech.sms.dto.request.ContentQueryPagedResultRequestDto;
import com.zhuyitech.sms.dto.result.SmsContentResultDto;

/**
 * 短信内容服务
 *
 * @author walkman
 */
public interface SmsContentService {

    /**
     * 分页查询短信发送内容
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<SmsContentResultDto> querySmsContent(ContentQueryPagedResultRequestDto requestDto);

}
