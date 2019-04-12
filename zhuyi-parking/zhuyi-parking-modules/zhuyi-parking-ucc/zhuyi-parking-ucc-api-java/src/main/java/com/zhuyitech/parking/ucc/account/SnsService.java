package com.zhuyitech.parking.ucc.account;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.ucc.dto.result.user.AlipayGetAccessTokenResultDto;
import com.zhuyitech.parking.ucc.dto.result.user.AlipayGetUserInfoResultDto;
import com.zhuyitech.parking.ucc.dto.result.user.WeixinGetAccessTokenResultDto;
import com.zhuyitech.parking.ucc.dto.result.user.WeixinGetUserInfoResultDto;
import com.zhuyitech.parking.ucc.account.request.AlipayAuthCodeGetRequestDto;
import com.zhuyitech.parking.ucc.account.request.AlipayGetAccessTokenRequestDto;
import com.zhuyitech.parking.ucc.account.request.AlipayGetUserInfoRequestDto;
import com.zhuyitech.parking.ucc.account.result.AlipayAuthCodeResultDto;
import com.zhuyitech.parking.ucc.account.result.WeixinGetAccessTokenRequestDto;
import com.zhuyitech.parking.ucc.account.result.WeixinGetUserInfoRequestDto;

public interface SnsService {

    /**
     * 授权请求参数
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<AlipayAuthCodeResultDto> authSdkCodeGet(AlipayAuthCodeGetRequestDto requestDto);

    /**
     * 获得accessToken
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<AlipayGetAccessTokenResultDto> getAccessToken(AlipayGetAccessTokenRequestDto requestDto);

    /**
     * 获取支付宝账号信息
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<AlipayGetUserInfoResultDto> getAlipayInfo(AlipayGetUserInfoRequestDto requestDto);

    /**
     * 获得accessToken
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<WeixinGetAccessTokenResultDto> getAccessToken(WeixinGetAccessTokenRequestDto requestDto);

    /**
     * 获取微信账号信息
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<WeixinGetUserInfoResultDto> getWechatInfo(WeixinGetUserInfoRequestDto requestDto);

}
