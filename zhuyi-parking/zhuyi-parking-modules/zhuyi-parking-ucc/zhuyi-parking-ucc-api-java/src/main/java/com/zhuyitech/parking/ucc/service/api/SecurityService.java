package com.zhuyitech.parking.ucc.service.api;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.ucc.account.request.UserLoginRequestDto;
import com.zhuyitech.parking.ucc.account.request.UserPhoneRegisterRequestDto;
import com.zhuyitech.parking.ucc.account.request.UserVerifyCodeLoginRequestDto;
import com.zhuyitech.parking.ucc.dto.request.CurrentLoginUserRequestDto;
import com.zhuyitech.parking.ucc.dto.result.user.CurrentUserResultDto;
import com.zhuyitech.parking.ucc.dto.result.user.UserLoginResultDto;
import com.zhuyitech.parking.ucc.user.dto.*;

/**
 * 用户安全服务
 *
 * @author walkman
 */
public interface SecurityService {

    /**
     * 用户注册
     *
     * @param requestDto
     * @return
     */
    ResultDto signUp(UserPhoneRegisterRequestDto requestDto);

    /**
     * 用户验证码注册
     *
     * @param requestDto UserVerifyCodeLoginRequestDto
     * @return ResultDto
     */
    ResultDto signUpByVerifyCode(UserVerifyCodeLoginRequestDto requestDto);

    /**
     * 用户登录
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserLoginResultDto> signIn(UserLoginRequestDto requestDto);

    /**
     * 登录用户信息
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<CurrentUserResultDto> currentUser(CurrentLoginUserRequestDto requestDto);

    /**
     * 用户退出
     *
     * @param requestDto
     * @return
     */
    ResultDto signOut(UserLogoutRequestDto requestDto);

    /**
     * 用户密码找回之更新密码
     *
     * @param requestDto
     * @return
     */
    ResultDto retrievePassword(UserRetrievePasswordRequestDto requestDto);

}
