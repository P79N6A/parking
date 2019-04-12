package com.zhuyitech.parking.pay.alipay.params;


import java.io.Serializable;

/**
 * 获取用户信息参数
 *
 * @author zwq
 */
public class AlipayGetUserInfoParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * accessToken
     */
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
