package com.zoeeasy.cloud.pms.specialvehicle;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.BlackListGetResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.BlackListPagedResultDto;

/**
 * 黑名单服务
 * Created by song on 2018/10/18.
 */
public interface BlackListService {

    /**
     * 添加黑名单
     *
     * @param requestDto
     * @return
     */
    ResultDto addBlackList(BlackListAddRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 修改黑名单
     *
     * @param requestDto
     * @return
     */
    ResultDto updateBlackList(BlackListUpdateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 获取黑名单详情
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<BlackListGetResultDto> getBlackList(BlackListGetRequestDto requestDto);

    /**
     * 删除黑名单
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteBlackList(BlackListDeleteRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 分页获取黑名单
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<BlackListPagedResultDto> getBlackListPagedList(BlackListQueryPagedRequestDto requestDto);

}
