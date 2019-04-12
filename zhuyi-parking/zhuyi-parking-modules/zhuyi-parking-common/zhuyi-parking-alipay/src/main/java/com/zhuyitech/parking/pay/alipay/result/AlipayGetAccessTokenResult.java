package com.zhuyitech.parking.pay.alipay.result;

import lombok.Data;

import java.io.Serializable;

/**
 * 获取accessToken结果
 *
 * @author zwq
 */
@Data
public class AlipayGetAccessTokenResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 支付宝用户的唯一userId
     */
    private String alipayUserId;

    /**
     * 访问令牌
     */
    private String accessToken;

    /**
     * 令牌有效期
     */
    private String tokenExpiredSeconds;

    /**
     * 刷新令牌
     */
    private String refreshToken;

    /**
     * 刷新令牌有效期
     */
    private String refreshTokenExpiredSeconds;

}
