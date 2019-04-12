package com.zhuyitech.sms.service.api;

import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zhuyitech.sms.dto.request.VerifyCodeQueryPagedResultRequestDto;
import com.zhuyitech.sms.dto.result.VerifyCodeResultDto;

/**
 * @author walkman
 */
public interface SmsVerifyCodeService {

    /**
     * 分页查询验证码
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<VerifyCodeResultDto> queryVerifyCode(VerifyCodeQueryPagedResultRequestDto requestDto);

}
