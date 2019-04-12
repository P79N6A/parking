package com.zhuyitech.parking.pay.alipay.service.impl;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.pay.alipay.config.AlipayConfig;
import com.zhuyitech.parking.pay.alipay.constant.AlipayConstants;
import com.zhuyitech.parking.pay.alipay.enums.AlipayErrEnum;
import com.zhuyitech.parking.pay.alipay.params.*;
import com.zhuyitech.parking.pay.alipay.result.*;
import com.zhuyitech.parking.pay.alipay.service.AliPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.net.URLEncoder;
import java.util.*;

/**
 * <pre>
 *     支付宝支付接口实现
 * </pre>
 *
 * @author zwq
 * @date 2018-01-11-13:13
 */
@Service("aliPayService")
@Slf4j
public class AliPayServiceImpl implements AliPayService {

    @Autowired
    private AlipayConfig alipayConfig;

    @Override
    public ObjectResultDto<AlipayGetAccessTokenResult> getAccessToken(AlipayGetAccessTokenParam params) {
        ObjectResultDto<AlipayGetAccessTokenResult> objectResultDto = new ObjectResultDto<>();
        if (StringUtils.isEmpty(params.getCode()) && StringUtils.isEmpty(params.getRefreshToken())) {
            return objectResultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }

        try {
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConstants.ALIPAY_GATEWAY_URL,
                    alipayConfig.getAppId(), alipayConfig.getAppPrivateKey(), AlipayConstants.FORMAT, AlipayConstants.CHARSET, alipayConfig.getAlipayPublicKey(), AlipayConstants.SIGN_TYPE);
            AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
            if (StringUtils.isNotEmpty(params.getCode()) && StringUtils.isEmpty(params.getRefreshToken())) {
                request.setCode(params.getCode());
                request.setGrantType("authorization_code");
            } else if (StringUtils.isEmpty(params.getCode()) && StringUtils.isNotEmpty(params.getRefreshToken())) {
                request.setRefreshToken(params.getRefreshToken());
                request.setGrantType("refresh_token");
            } else {
                return objectResultDto.makeResult(AlipayErrEnum.PARAM_ERR.getValue(), AlipayErrEnum.PARAM_ERR.getComment());
            }
            AlipaySystemOauthTokenResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                AlipayGetAccessTokenResult result = new AlipayGetAccessTokenResult();
                result.setAccessToken(response.getAccessToken());
                result.setAlipayUserId(response.getUserId());
                result.setRefreshToken(response.getRefreshToken());
                result.setTokenExpiredSeconds(response.getExpiresIn());
                result.setRefreshTokenExpiredSeconds(response.getReExpiresIn());
                objectResultDto.setData(result);
                objectResultDto.success();
            } else {
                objectResultDto.makeResult(Integer.parseInt(response.getCode()), response.getMsg());
            }
        } catch (Exception e) {
            log.error("获取AccessToken失败" + e.getMessage());
            objectResultDto.makeResult(AlipayErrEnum.GET_ACCESSTOKEN_ERR.getValue(), AlipayErrEnum.GET_ACCESSTOKEN_ERR.getComment());
        }
        return objectResultDto;
    }

    @Override
    public ObjectResultDto<AlipayGetUserInfoResult> getUserInfo(AlipayGetUserInfoParam params) {
        ObjectResultDto<AlipayGetUserInfoResult> objectResultDto = new ObjectResultDto<>();
        if (StringUtils.isEmpty(params.getAccessToken())) {
            return objectResultDto.makeResult(AlipayErrEnum.PARAM_EMPTY.getValue(), AlipayErrEnum.PARAM_EMPTY.getComment());
        }

        try {
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConstants.ALIPAY_GATEWAY_URL,
                    alipayConfig.getAppId(), alipayConfig.getAppPrivateKey(), AlipayConstants.FORMAT, AlipayConstants.CHARSET, alipayConfig.getAlipayPublicKey(), AlipayConstants.SIGN_TYPE);
            AlipayUserUserinfoShareRequest request = new AlipayUserUserinfoShareRequest();
            AlipayUserUserinfoShareResponse response = alipayClient.execute(request, params.getAccessToken());
            if (response.isSuccess()) {
                AlipayGetUserInfoResult result = new AlipayGetUserInfoResult();
                result.setAlipayUserId(response.getUserId());
                result.setAvatar(response.getAvatar());
                result.setCity(response.getCity());
                result.setProvince(response.getProvince());
                result.setGender(response.getGender());
                result.setIsCertified(response.getIsCertified());
                result.setIsStudentCertified(response.getIsStudentCertified());
                result.setUserType(response.getUserTypeValue());
                result.setNickName(response.getNickName());
                result.setAlipayUserId(response.getAlipayUserId());
                result.setUserStatus(response.getUserStatus());
                objectResultDto.setData(result);
                objectResultDto.success();
            } else {
                objectResultDto.makeResult(Integer.parseInt(response.getCode()), response.getMsg());
                objectResultDto.failed();
            }
        } catch (Exception e) {
            log.error("获取用户信息失败" + e.getMessage());
            objectResultDto.makeResult(AlipayErrEnum.GET_USERINFO_ERR.getValue(), AlipayErrEnum.GET_USERINFO_ERR.getComment());
        }
        return objectResultDto;
    }

    @Override
    public ObjectResultDto<AlipayAuthSdkCodeGetResult> authSdkCodeGet(AlipayAuthSdkCodeGetParam params) {
        ObjectResultDto<AlipayAuthSdkCodeGetResult> objectResultDto = new ObjectResultDto<>();
        SortedMap<String, String> map = new TreeMap<>();
        map.put("apiname", "com.alipay.account.auth");
        map.put("method", "alipay.open.auth.sdk.code.get");
        map.put("app_id", alipayConfig.getAppId());
        map.put("app_name", "mc");
        map.put("biz_type", "openservice");
        map.put("pid", alipayConfig.getSellerId());
        map.put("product_id", "APP_FAST_LOGIN");
        map.put("scope", "kuaijie");
        map.put("target_id", StringUtils.getUUID());
        map.put("auth_type", "AUTHACCOUNT");
        map.put("sign_type", "RSA");
        String signStr = AlipaySignature.getSignContent(map);
        String sign = null;
        try {
            sign = AlipaySignature.rsaSign(signStr, alipayConfig.getAppPrivateKey(), "UTF-8");

            String urlNeeded = "";
            for (Map.Entry<String, String> vo : map.entrySet()) {
                urlNeeded = urlNeeded + vo.getKey() + "=" + vo.getValue() + "&";
            }
            urlNeeded = urlNeeded + "sign=" + URLEncoder.encode(sign, "UTF-8");
            AlipayAuthSdkCodeGetResult result = new AlipayAuthSdkCodeGetResult();
            result.setUrlNeeded(urlNeeded);
            objectResultDto.setData(result);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("支付宝关闭订单失败" + e.getMessage());
            objectResultDto.makeResult(AlipayErrEnum.MANAGE_REQUEST_STR_ERR.getValue(), AlipayErrEnum.MANAGE_REQUEST_STR_ERR.getComment());
        }
        return objectResultDto;
    }

}
