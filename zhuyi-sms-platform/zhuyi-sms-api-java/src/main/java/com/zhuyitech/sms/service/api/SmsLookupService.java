package com.zhuyitech.sms.service.api;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;

/**
 * 短信公共服务类
 *
 * @author walkman
 */
public interface SmsLookupService {

    /**
     * 获取所有短信客户端选择项
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> getClientComboboxList();

    /**
     * 获取模板类型选择项
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> getTemplateTypeComboboxList();

    /**
     * 获取模板类型选择项
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> getVerifyCodeTypeComboboxList();
}
