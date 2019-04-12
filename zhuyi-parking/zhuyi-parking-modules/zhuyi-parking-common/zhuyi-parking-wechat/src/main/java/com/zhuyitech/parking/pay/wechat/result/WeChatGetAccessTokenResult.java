package com.zhuyitech.parking.pay.wechat.result;


import java.io.Serializable;

/**
 * <pre>
 *     微信登录获取accesstoken
 * </pre>
 *
 * @author zwq
 * @date 2018-02-24-14:14
 */
public class WeChatGetAccessTokenResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 接口调用凭证
     */
    private String accessToken;

    /**
     * access_token接口调用凭证超时时间，单位（秒）
     */
    private String expiresIn;

    /**
     * 用户刷新access_token
     */
    private String refreshToken;

    /**
     * 授权用户唯一标识
     */
    private String openId;

    /**
     * 用户授权的作用域，使用逗号（,）分隔
     */
    private String scope;

    /**
     * 当且仅当该网站应用已获得该用户的userinfo授权时，才会出现该字段。
     */
    private String unionid;

    /**
     * errcode
     */
    private String errCode;

    /**
     * errmsg
     */
    private String errMsg;

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

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

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

    @Override
    public String toString() {
        return "WeChatGetAccessTokenResult{" +
                "accessToken='" + accessToken + '\'' +
                ", expiresIn='" + expiresIn + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", openId='" + openId + '\'' +
                ", scope='" + scope + '\'' +
                ", unionid='" + unionid + '\'' +
                ", errCode='" + errCode + '\'' +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}
