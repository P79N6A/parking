package com.zhuyitech.parking.pay.wechat.params;


/**
 * <pre>
 *     微信检验AccessToken请求参数
 * </pre>
 *
 * @author zwq
 * @date 2018-02-24-09:44
 */
public class WeChatUpdateAccessTokenParams {

    /**
     * refresh_token
     */
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
