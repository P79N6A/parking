package com.zhuyitech.parking.platform.controller.license;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zhuyitech.parking.pms.dto.result.liscense.LicensePrefixFirstResultDto;
import com.zhuyitech.parking.tool.dto.request.license.LicenseOrganizationPrefixFirstGetRequestDto;
import com.zhuyitech.parking.tool.dto.request.license.LicenseOrganizationPrefixListGetRequestDto;
import com.zhuyitech.parking.tool.dto.request.license.LicenseOrganizationPrefixPagedResultRequestDto;
import com.zhuyitech.parking.tool.dto.request.license.LicenseOrganizationPrefixRequestDto;
import com.zhuyitech.parking.tool.dto.result.license.LicenseOrganizationPrefixFirstResultDto;
import com.zhuyitech.parking.tool.dto.result.license.LicenseOrganizationPrefixResultDto;
import com.zhuyitech.parking.tool.service.api.LicenseOrganizationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 车管局Api
 *
 * @author AkeemSuper
 */
@Api(value = "车管局Api", description = "车管局Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/licenseOrganization")
public class LicenseOrganizationController {

    @Autowired
    private LicenseOrganizationService licenseOrganizationService;

    /**
     * 获取车牌前缀
     */
    @ApiOperation(value = "获取车牌前缀", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ObjectResultDto.class)
    @RequestMapping(value = "/getLicenseOrganizationPrefix", method = RequestMethod.POST)
    public ObjectResultDto<LicenseOrganizationPrefixResultDto> getPrefix(LicenseOrganizationPrefixRequestDto requestDto) {
        return licenseOrganizationService.getPrefix(requestDto);
    }

    /**
     * 获取车牌前缀列表
     */
    @ApiOperation(value = "获取车牌前缀列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ListResultDto.class)
    @RequestMapping(value = "/getPrefixList", method = RequestMethod.POST)
    public ListResultDto<LicenseOrganizationPrefixResultDto> getPrefixList(LicenseOrganizationPrefixListGetRequestDto requestDto) {
        return licenseOrganizationService.getPrefixList(requestDto);
    }

    /**
     * 分页获取车牌前缀列表
     */
    @ApiOperation(value = "分页获取车牌前缀列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = PagedResultDto.class)
    @RequestMapping(value = "/getPrefixPagedList", method = RequestMethod.POST)
    public PagedResultDto<LicenseOrganizationPrefixResultDto> getPrefixPagedList(LicenseOrganizationPrefixPagedResultRequestDto requestDto) {
        return licenseOrganizationService.getPrefixPagedList(requestDto);
    }


    /**
     * 获取前缀首字母
     */
    @ApiOperation(value = "获取前缀首字母", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = LicensePrefixFirstResultDto.class)
    @RequestMapping(value = "/getPrefixLetter", method = RequestMethod.POST)
    public ListResultDto<LicenseOrganizationPrefixFirstResultDto> getPrefixLetter(LicenseOrganizationPrefixFirstGetRequestDto requestDto) {
        return licenseOrganizationService.getPrefixLetter(requestDto);
    }

}
