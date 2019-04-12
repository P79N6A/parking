package com.zhuyitech.sms.service.api;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.sms.dto.request.*;
import com.zhuyitech.sms.dto.result.SmsTemplateResultDto;

/**
 * 短信模板服务
 *
 * @author walkman
 */
public interface SmsTemplateService {

    /**
     * 添加短信模板
     *
     * @param requestDto
     * @return
     */
    ResultDto addTemplate(TemplateAddRequestDto requestDto);

    /**
     * 修改短信模板
     *
     * @param requestDto
     * @return
     */
    ResultDto updateTemplate(TemplateUpdateRequestDto requestDto);

    /**
     * 删除短信模板
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteTemplate(TemplateDeleteRequestDto requestDto);

    /**
     * 查询短信模板
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<SmsTemplateResultDto> getTemplate(TemplateGetRequestDto requestDto);

    /**
     * 分页查询短信模板
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<SmsTemplateResultDto> queryTemplate(TemplateQueryPagedResultRequestDto requestDto);

}
