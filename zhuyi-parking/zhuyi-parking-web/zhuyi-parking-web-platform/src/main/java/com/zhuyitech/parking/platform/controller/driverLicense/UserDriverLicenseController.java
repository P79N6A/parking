package com.zhuyitech.parking.platform.controller.driverLicense;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zhuyitech.parking.ucc.dto.request.driverlicense.*;
import com.zhuyitech.parking.ucc.dto.result.UserDriverLicenseExistResultDto;
import com.zhuyitech.parking.ucc.dto.result.UserDriverLicenseResultDto;
import com.zhuyitech.parking.ucc.service.api.UserDriverLicenseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户驾驶证
 *
 * @Date: 2018/1/15
 * @author: yuzhicheng
 */
@Api(value = "用户驾驶证Api", description = "用户驾驶证Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/driverLicense")
public class UserDriverLicenseController {

    @Autowired
    private UserDriverLicenseService userDriverLicenseService;

    /**
     * 获取驾驶人准驾车型列表
     *
     * @return
     */
    @ApiOperation(value = "获取驾驶人准驾车型列表", notes = "获取驾驶人准驾车型列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @RequestMapping(value = "/driverclass", name = "获取驾驶人准驾车型列表", method = RequestMethod.POST)
    @ApiVersion(2)
    public ListResultDto<ComboboxItemDto> getDriverClassList() {
        return userDriverLicenseService.getDriverClassList();
    }

    /**
     * 用户驾驶证是否存在
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "用户驾驶证是否存在", notes = "用户驾驶证是否存在", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserDriverLicenseExistResultDto.class)
    @RequestMapping(value = "/exist", name = "用户驾驶证是否存在", method = RequestMethod.POST)
    public ObjectResultDto<UserDriverLicenseExistResultDto> existUserDriverLicense(UserDriverLicenseExistRequestDto requestDto) {
        return userDriverLicenseService.existUserDriverLicense(requestDto);
    }

    /**
     * 新增用户驾驶证
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "新增用户驾驶证", notes = "新增用户驾驶证", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/add", name = "新增用户驾驶证", method = RequestMethod.POST)
    @ApiVersion(2)
    public ResultDto addUserDriverLicense(UserDriverLicenseAddRequestDto requestDto) {
        return userDriverLicenseService.addUserDriverLicense(requestDto);
    }

    /**
     * 删除用户驾驶证
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "删除用户驾驶证", notes = "删除用户驾驶证", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/delete", name = "删除用户驾驶证", method = RequestMethod.POST)
    @ApiVersion(2)
    public ResultDto deleteUserDriverLicense(UserDriverLicenseDeleteRequestDto requestDto) {
        return userDriverLicenseService.deleteUserDriverLicense(requestDto);
    }

    /**
     * 修改用户驾驶证
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "修改用户驾驶证", notes = "修改用户驾驶证", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/update", name = "修改用户驾驶证", method = RequestMethod.POST)
    @ApiVersion(2)
    public ResultDto updateUserDriverLicense(UserDriverLicenseUpdateRequestDto requestDto) {
        return userDriverLicenseService.updateUserDriverLicense(requestDto);
    }

    /**
     * 查看用户驾驶证
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "查看用户驾驶证", notes = "查看用户驾驶证", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserDriverLicenseResultDto.class)
    @RequestMapping(value = "/get", name = "查看用户驾驶证", method = RequestMethod.POST)
    @ApiVersion(2)
    public ObjectResultDto<UserDriverLicenseResultDto> getUserDriverLicense(UserDriverLicenseGetRequestDto requestDto) {
        return userDriverLicenseService.getUserDriverLicense(requestDto);
    }

}
