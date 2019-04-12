package com.zhuyitech.parking.pay.alipay.params;


import java.io.Serializable;

/**
 * 获取accessToken参数
 *
 * @author zwq
 */
public class AlipayGetAccessTokenParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * code
     */
    private String code;

    /**
     * refreshToken
     */
    private String refreshToken;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
