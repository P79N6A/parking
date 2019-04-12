package com.zhuyitech.parking.platform.controller.share;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zhuyitech.parking.tool.dto.request.share.ShareRequestDto;
import com.zhuyitech.parking.tool.dto.result.share.ShareResultDto;
import com.zhuyitech.parking.tool.service.api.ShareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分享API
 *
 * @author zwq
 */
@Api(value = "分享API", description = "分享API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/share")
public class ShareController {

    @Autowired
    private ShareService shareService;

    /**
     * 分享
     */
    @ApiOperation(value = "分享", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ShareResultDto.class)
    @RequestMapping(value = "/share", method = RequestMethod.POST)
    @ApiVersion(2)
    public ObjectResultDto<ShareResultDto> share(ShareRequestDto requestDto) {
        return shareService.share(requestDto);
    }

}
