package com.zhuyitech.parking.ucc.account;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.ucc.dto.result.user.*;
import com.zhuyitech.parking.ucc.account.request.UserWechatSignUpRequestDto;
import com.zhuyitech.parking.ucc.account.request.WechatUserHasRegisteredRequestDto;
import com.zhuyitech.parking.ucc.account.request.AlipayUserHasRegisteredRequestDto;
import com.zhuyitech.parking.ucc.account.request.UserAlipayRegisterRequestDto;

/**
 * 账号注册服务服务
 *
 * @author zwq
 */
public interface AccountService {

    /**
     * 支付宝账号是否注册
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<AlipayUserHasRegisteredResultDto> alipayUserHasRegistered(AlipayUserHasRegisteredRequestDto requestDto);

    /**
     * 支付宝账号注册
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<AlipayRegisterSuccessStatusResultDto> alipayRegister(UserAlipayRegisterRequestDto requestDto);

    /**
     * 微信账号是否注册
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<WechatUserHasRegisteredResultDto> wechatUserHasRegistered(WechatUserHasRegisteredRequestDto requestDto);

    /**
     * 微信账号注册
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<WechatRegisterSuccessStatusResultDto> wechatRegister(UserWechatSignUpRequestDto requestDto);

    /**
     * 微信公众号账号注册
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<WechatRegisterSuccessStatusResultDto> jsapiRegister(UserWechatSignUpRequestDto requestDto);

    /**
     * 微信公众号账号注册-扫码注册
     *
     * @param requestDto UserWechatSignUpRequestDto
     * @return WechatRegisterSuccessStatusResultDto
     */
    ObjectResultDto<WechatRegisterSuccessStatusResultDto> jsapiRegisterByScanQRCode(UserWechatSignUpRequestDto requestDto);
}
