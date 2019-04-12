package com.zoeeasy.cloud.ucc.user;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.ucc.user.dto.request.UserLoginLogSaveRequestDto;

/**
 * 用户登录日志服务
 *
 * @author AkeemSuper
 * @date 2018/11/15 0015
 */
public interface UserLoginLogService {

    /**
     * 保存用户登录日志
     *
     * @param requestDto
     * @return
     */
    ResultDto saveUserLoginLog(UserLoginLogSaveRequestDto requestDto);

}
