package com.zhuyitech.parking.pay.wechat.params;


/**
 * <pre>
 *     微信获取用户信息请求参数
 * </pre>
 *
 * @author zwq
 * @date 2018-02-24-09:44
 */
public class WeChatGetUserInfoParams {

    /**
     * openid
     */
    private String openId;

    /**
     * access_token
     */
    private String accessToken;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
