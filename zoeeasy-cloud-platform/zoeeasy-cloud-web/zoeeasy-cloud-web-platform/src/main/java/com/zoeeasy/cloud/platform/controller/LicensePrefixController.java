package com.zoeeasy.cloud.platform.controller;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.tool.license.LicensePrefixService;
import com.zoeeasy.cloud.tool.license.dto.request.LicensePrefixViewGetRequestDto;
import com.zoeeasy.cloud.tool.license.dto.result.LicensePrefixViewResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by song on 2018/10/23.
 */
@Api(value = "车牌前缀Api", tags = "车牌前缀Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/licensePrefix")
@ApiVersion(1)
@ApiSigned
public class LicensePrefixController {

    @Autowired
    private LicensePrefixService licensePrefixService;

    /**
     * 获取车牌前缀归属地
     *
     * @return
     */
    @ApiOperation(value = "获取车牌前缀归属地", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = LicensePrefixViewResultDto.class)
    @PostMapping(value = "/licenseprefix")
    public ListResultDto<LicensePrefixViewResultDto> getLicensePrefixViewList(@RequestBody LicensePrefixViewGetRequestDto requestDto) {
        return licensePrefixService.getLicensePrefixViewList(requestDto);
    }

}
