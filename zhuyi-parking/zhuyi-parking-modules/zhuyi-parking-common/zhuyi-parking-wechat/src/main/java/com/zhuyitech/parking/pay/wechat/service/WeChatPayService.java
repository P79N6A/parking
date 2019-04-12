package com.zhuyitech.parking.pay.wechat.service;


import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.pay.wechat.params.*;
import com.zhuyitech.parking.pay.wechat.result.*;

/**
 * <pre>
 *     微信公众号支付接口
 * </pre>
 *
 * @author walkman
 * @date 2017-07-11-10:22
 */
public interface WeChatPayService {

    /**
     * 获取accessToken
     *
     * @param weChatGetAccessTokenParams
     * @return
     */
    ObjectResultDto<WeChatGetAccessTokenResult> getAccessToken(WeChatGetAccessTokenParams weChatGetAccessTokenParams);

    /**
     * 获取用户信息
     *
     * @param weChatGetUserInfoParams
     * @return
     */
    ObjectResultDto<WeChatGetUserInfoResult> getUserInfo(WeChatGetUserInfoParams weChatGetUserInfoParams);

    /**
     * 检验AcessToken
     *
     * @param weChatCheckAccessTokenParams
     * @return
     */
    ObjectResultDto<WeChatAccessTokenIsValidResult> checkAccessToken(WeChatCheckAccessTokenParams weChatCheckAccessTokenParams);

    /**
     * 刷新AccessToken
     *
     * @param weChatUpdateAccessTokenParams
     * @return
     */
    ObjectResultDto<WeChatUpdateAccessTokenResult> updateAccessToken(WeChatUpdateAccessTokenParams weChatUpdateAccessTokenParams);

}
