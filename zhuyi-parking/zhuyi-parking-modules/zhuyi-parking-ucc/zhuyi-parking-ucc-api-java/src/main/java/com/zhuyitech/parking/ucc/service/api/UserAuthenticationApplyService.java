package com.zhuyitech.parking.ucc.service.api;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.ucc.dto.request.realname.*;
import com.zhuyitech.parking.ucc.dto.result.UserAuthApplyResultDto;
import com.zhuyitech.parking.ucc.dto.result.UserAuthStatusResultDto;
import com.zhuyitech.parking.ucc.dto.result.UserRealNameAuthApplyResultDto;

/**
 * 用户实名认证申请服务
 *
 * @author walkman
 * @date 2017-12-01
 */
public interface UserAuthenticationApplyService {

    /**
     * 获取实名认证申请
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserAuthApplyResultDto> getUserAuthApply(UserAuthApplyGetRequestDto requestDto);

    /**
     * 获取实名认证申请
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserRealNameAuthApplyResultDto> getCurrentUserAuthApply(CurrentUserAuthApplyGetRequestDto requestDto);

    /**
     * 获取实名状态
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserAuthStatusResultDto> getCurrentUserAuthStatus(CurrentUserAuthStatusGetRequestDto requestDto);

    /**
     * 更新实名认证申请
     *
     * @param requestDto
     * @return
     */
    ResultDto updateAuthApply(CurrentUserAuthApplyUpdateRequestDto requestDto);

    /**
     * 实名认证申请审核
     *
     * @param requestDto
     * @return
     */
    ResultDto approveAuthApply(UserAuthApproveRequestDto requestDto);

    /**
     * 获取实名认证申请列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<UserAuthApplyResultDto> getAuthApplyList(UserAuthApplyListGetRequestDto requestDto);

    /**
     * 分页获取实名认证申请列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<UserAuthApplyResultDto> getAuthApplyPagedList(UserAuthApplyQueryPagedResultRequestDto requestDto);

    /**
     * 根据用户Id获取用户实名认证申请
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserAuthApplyResultDto> getUserAuthApplyByUserId(UserAuthApplyGetByUserIdRequestDto requestDto);

    /**
     * 添加用户认证申请
     * @param requestDto
     * @return
     */
    ResultDto insertUserAuthApply(UserAuthApplyInsertRequestDto requestDto);

    /**
     * 用户实名认证驳回下拉
     * @return
     */
    ListResultDto<ComboboxItemDto> getRejectReason();
}
