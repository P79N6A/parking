package com.zoeeasy.cloud.ucc.platform;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.ucc.platform.dto.request.UserGetByUserIdRequestDto;
import com.zoeeasy.cloud.ucc.platform.dto.result.UserInfoResultDto;

/**
 * 平台用户服务
 *
 * @author AkeemSuper
 * @date 2018/11/6 0006
 */
public interface PlatformUccService {

    /**
     * 通过userid获取用户信息
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserInfoResultDto> getUserByUserId(UserGetByUserIdRequestDto requestDto);

}
