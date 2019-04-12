package com.zoeeasy.cloud.tool.license;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.zoeeasy.cloud.tool.license.dto.request.LicensePrefixViewGetRequestDto;
import com.zoeeasy.cloud.tool.license.dto.result.LicensePrefixViewResultDto;

/**
 * @author zwq
 */
public interface LicensePrefixService {

    /**
     * 获取车牌前缀
     *
     * @param requestDto
     * @return
     */
    ListResultDto<LicensePrefixViewResultDto> getLicensePrefixViewList(LicensePrefixViewGetRequestDto requestDto);

}
