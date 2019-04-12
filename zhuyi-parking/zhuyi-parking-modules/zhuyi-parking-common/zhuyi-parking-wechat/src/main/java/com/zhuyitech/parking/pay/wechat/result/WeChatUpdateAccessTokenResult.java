package com.zhuyitech.parking.pay.wechat.result;



import java.io.Serializable;

/**
 * <pre>
 *     微信检验AccessToken
 * </pre>
 *
 * @author zwq
 * @date 2018-02-24-14:14
 */
public class WeChatUpdateAccessTokenResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * errcode
     */
    private String errCode;

    /**
     * errmsg
     */
    private String errMsg;

    /**
     * access_token
     */
    private String accessToken;

    /**
     * expires_in
     */
    private String expiresIn;

    /**
     * refresh_token
     */
    private String refreshToken;

    /**
     * openid
     */
    private String openId;

    /**
     * scope
     */
    private String scope;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
