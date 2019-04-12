package com.zhuyitech.parking.platform.controller.setting;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zhuyitech.parking.tool.dto.request.version.*;
import com.zhuyitech.parking.tool.dto.result.version.AppVersionCheckResultDto;
import com.zhuyitech.parking.tool.dto.result.version.AppVersionResultDto;
import com.zhuyitech.parking.tool.service.api.VersionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 设置API
 *
 * @author zwq
 */
@Api(value = "设置API", description = "设置API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/setting")
public class SettingController {

    @Autowired
    private VersionService versionService;

    /**
     * 新版本检查
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "新版本检查", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AppVersionCheckResultDto.class)
    @RequestMapping(value = "/versioncheck", method = RequestMethod.POST)
    @ApiVersion(2)
    public ObjectResultDto<AppVersionCheckResultDto> versionCheck(VersionCheckRequestDto requestDto) {
        return versionService.versionCheck(requestDto);
    }

    /**
     * 版本号添加
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "版本号添加", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiVersion(2)
    public ResultDto add(AppVersionAddRequestDto requestDto) {
        return versionService.versionAdd(requestDto);
    }

    /**
     * 版本号更新
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "版本号更新", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiVersion(2)
    public ResultDto update(AppVersionUpdateRequestDto requestDto) {
        return versionService.versionUpdate(requestDto);
    }

    /**
     * 版本发布
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "版本发布", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public ResultDto publish(AppVersionPublishRequestDto requestDto) {
        return versionService.versionPublish(requestDto);
    }

    /**
     * 版本下架
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "版本下架", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/unshelve", method = RequestMethod.POST)
    public ResultDto unshelve(AppVersionWithdrawRequestDto requestDto) {
        return versionService.versionWithdraw(requestDto);
    }

    /**
     * 获取版本列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取版本列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AppVersionResultDto.class)
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiVersion(2)
    public ListResultDto<AppVersionResultDto> list(AppVersionListGetRequestDto requestDto) {
        return versionService.getAppVersionList(requestDto);
    }

    /**
     * 分页获取版本列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页获取版本列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = AppVersionResultDto.class)
    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    @ApiVersion(2)
    public PagedResultDto<AppVersionResultDto> pageList(AppVersionPageListGetRequestDto requestDto) {
        return versionService.getAppVersionPageList(requestDto);
    }
}
