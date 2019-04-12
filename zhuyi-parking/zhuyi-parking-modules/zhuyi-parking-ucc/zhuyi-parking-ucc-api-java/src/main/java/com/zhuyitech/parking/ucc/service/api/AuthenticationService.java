package com.zhuyitech.parking.ucc.service.api;


import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.ucc.dto.request.token.AccessTokenRequestDto;
import com.zhuyitech.parking.ucc.dto.result.user.AccessTokenResponseDto;

/**
 * 认证服务
 *
 * @author walkman
 */
public interface AuthenticationService {

    /**
     * @param requestDto
     * @return
     */
    ObjectResultDto<AccessTokenResponseDto> requestToken(AccessTokenRequestDto requestDto);
}
