package com.zhuyitech.parking.pay.alipay.service;


import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.pay.alipay.params.*;
import com.zhuyitech.parking.pay.alipay.result.*;

/**
 * <pre>
 *    支付宝支付接口
 * </pre>
 *
 * @author zwq
 * @date 2018-1-11-10:22
 */
public interface AliPayService {

    /**
     * 获取accessToken
     *
     * @param params
     * @return
     */
    ObjectResultDto<AlipayGetAccessTokenResult> getAccessToken(AlipayGetAccessTokenParam params);

    /**
     * 获取用户信息
     *
     * @param params
     * @return
     */
    ObjectResultDto<AlipayGetUserInfoResult> getUserInfo(AlipayGetUserInfoParam params);

    /**
     * 授权请求参数
     *
     * @param params
     * @return
     */
    ObjectResultDto<AlipayAuthSdkCodeGetResult> authSdkCodeGet(AlipayAuthSdkCodeGetParam params);
}
