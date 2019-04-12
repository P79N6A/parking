package com.zhuyitech.parking.tool.service.api;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.tool.dto.request.share.ShareRequestDto;
import com.zhuyitech.parking.tool.dto.result.share.ShareResultDto;

/**
 * @author AkeemSuper
 * @date 2018/5/24 0024
 */
public interface ShareService {

    /**
     * 分享接口
     *
     * @param requestDto ShareRequestDto
     * @return ShareResultDto
     */
    ObjectResultDto<ShareResultDto> share(ShareRequestDto requestDto);
}
