package com.zhuyitech.parking.tool.service.api;


import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.tool.dto.request.version.*;
import com.zhuyitech.parking.tool.dto.result.version.AppVersionResultDto;
import com.zhuyitech.parking.tool.dto.result.version.AppVersionCheckResultDto;


/**
 * 版本号服务
 *
 * @author zwq
 */
public interface VersionService {

    /**
     * 版本号比对
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<AppVersionCheckResultDto> versionCheck(VersionCheckRequestDto requestDto);

    /**
     * 版本号添加
     *
     * @param requestDto
     * @return
     */
    ResultDto versionAdd(AppVersionAddRequestDto requestDto);

    /**
     * 版本号更新
     *
     * @param requestDto
     * @return
     */
    ResultDto versionUpdate(AppVersionUpdateRequestDto requestDto);

    /**
     * 版本发布
     *
     * @param requestDto
     * @return
     */
    ResultDto versionPublish(AppVersionPublishRequestDto requestDto);

    /**
     * 版本下架
     *
     * @param requestDto
     * @return
     */
    ResultDto versionWithdraw(AppVersionWithdrawRequestDto requestDto);

    /**
     * 获取版本列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<AppVersionResultDto> getAppVersionList(AppVersionListGetRequestDto requestDto);

    /**
     * 分页获取版本列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<AppVersionResultDto> getAppVersionPageList(AppVersionPageListGetRequestDto requestDto);
}