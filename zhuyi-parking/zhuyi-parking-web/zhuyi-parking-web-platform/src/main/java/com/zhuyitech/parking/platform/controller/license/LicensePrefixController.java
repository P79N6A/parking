package com.zhuyitech.parking.platform.controller.license;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.common.annotation.SystemLog;
import com.zhuyitech.parking.pms.dto.request.license.*;
import com.zhuyitech.parking.pms.dto.result.liscense.LicensePrefixFirstResultDto;
import com.zhuyitech.parking.pms.dto.result.liscense.LicensePrefixResultDto;
import com.zhuyitech.parking.pms.dto.result.liscense.LicensePrefixViewResultDto;
import com.zhuyitech.parking.pms.service.api.LicensePrefixService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 车牌前置首字母获取
 *
 * @author zwq
 */
@Api(value = "车牌前缀Api", description = "车牌前缀Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/licensePrefix")
public class LicensePrefixController {

    @Autowired
    private LicensePrefixService licensePrefixAppService;

    /**
     * 新增车牌前缀
     *
     * @param requestDto
     * @return
     */
    @SystemLog("新增车牌前缀")
    @ApiOperation(value = "新增车牌前缀", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultDto addLicensePrefix(LicensePrefixAddRequestDto requestDto) {
        return licensePrefixAppService.addLicensePrefix(requestDto);
    }

    /**
     * 删除车型
     *
     * @param requestDto
     * @return
     */
    @SystemLog("删除车牌前缀")
    @ApiOperation(value = "删除车牌前缀", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultDto deleteLicensePrefix(LicensePrefixDeleteRequestDto requestDto) {
        return licensePrefixAppService.deleteLicensePrefix(requestDto);
    }

    /**
     * 更新车型
     *
     * @param requestDto
     * @return
     */
    @SystemLog("更新车牌前缀")
    @ApiOperation(value = "更新车牌前缀", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultDto updateLicensePrefix(LicensePrefixUpdateRequestDto requestDto) {
        return licensePrefixAppService.updateLicensePrefix(requestDto);
    }

    /**
     * 获取车牌前缀
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取车牌前缀", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ObjectResultDto.class)
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ObjectResultDto<LicensePrefixResultDto> getLicensePrefix(LicensePrefixGetRequestDto requestDto) {
        return licensePrefixAppService.getLicensePrefix(requestDto);
    }

    /**
     * 获取车牌前缀列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取车牌前缀列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ListResultDto.class)
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ListResultDto<LicensePrefixResultDto> getLicensePrefixList(LicensePrefixListGetRequestDto requestDto) {
        return licensePrefixAppService.getLicensePrefixList(requestDto);
    }

    /**
     * 分页获取车牌前缀列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页获取车牌前缀列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = PagedResultDto.class)
    @RequestMapping(value = "/listpage", method = RequestMethod.POST)
    public PagedResultDto<LicensePrefixResultDto> getLicensePrefixPagedList(LicensePrefixQueryPagedResultRequestDto requestDto) {
        return licensePrefixAppService.getLicensePrefixPagedList(requestDto);
    }

    /**
     * 获取车牌前缀归属地
     *
     * @return
     */
    @ApiOperation(value = "获取车牌前缀归属地", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = LicensePrefixViewResultDto.class)
    @RequestMapping(value = "/licenseprefix", method = RequestMethod.POST)
    public ListResultDto<LicensePrefixViewResultDto> getLicensePrefixViewList(LicensePrefixViewGetRequestDto requestDto) {
        return licensePrefixAppService.getLicensePrefixViewList(requestDto);
    }

    /**
     * 获取前缀首字母
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取前缀首字母", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = LicensePrefixFirstResultDto.class)
    @RequestMapping(value = "/prefixfirst", method = RequestMethod.POST)
    public ListResultDto<LicensePrefixFirstResultDto> getLicencePrefixFirstList(LicensePrefixFirstGetRequestDto requestDto) {
        return licensePrefixAppService.getLicencePrefixFirstList(requestDto);
    }

}
