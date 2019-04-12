package com.zoeeasy.cloud.pms.specialvehicle;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.WhiteListQueryPagedResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.WhiteListResultDto;

/**
 * 白名单
 *
 * @date: 2018/10/16.
 * @author：zm
 */
public interface WhiteListService {

    /**
     * 添加白名单
     *
     * @param requestDto
     * @return
     */
    ResultDto addWhiteList(WhiteListAddRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 删除白名单
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteWhiteList(WhiteListDeleteRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 修改白名单
     *
     * @param requestDto
     * @return
     */
    ResultDto updateWhiteList(WhiteListUpdateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 获取白名单
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<WhiteListResultDto> getWhiteList(WhiteListGetRequestDto requestDto);

    /**
     * 分页获取白名单列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<WhiteListQueryPagedResultDto> getWhiteListPagedList(WhiteListQueryPagedRequestDto requestDto);

}
