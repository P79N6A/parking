package com.zhuyitech.parking.tool.service.impl;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.tool.config.ShareConfig;
import com.zhuyitech.parking.tool.dto.request.share.ShareRequestDto;
import com.zhuyitech.parking.tool.dto.result.share.ShareResultDto;
import com.zhuyitech.parking.tool.service.api.ShareService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 分享服务
 *
 * @author AkeemSuper
 * @date 2018/5/24 0024
 */
@Service("shareService")
@Slf4j
public class ShareServiceImpl implements ShareService {

    @Autowired
    private ShareConfig shareConfig;

    /**
     * 分享接口
     *
     * @param requestDto ShareRequestDto
     * @return ShareResultDto
     */
    @Override
    public ObjectResultDto<ShareResultDto> share(ShareRequestDto requestDto) {
        ObjectResultDto<ShareResultDto> resultDto = new ObjectResultDto<>();
        try {
            ShareResultDto shareResultDto = new ShareResultDto();
            shareResultDto.setTitle(shareConfig.getTitle());
            shareResultDto.setContent(shareConfig.getContent());
            shareResultDto.setImageUrl(shareConfig.getImageUrl());
            shareResultDto.setLinkUrl(shareConfig.getLinkUrl());
            resultDto.setData(shareResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("分享接口失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }
}
