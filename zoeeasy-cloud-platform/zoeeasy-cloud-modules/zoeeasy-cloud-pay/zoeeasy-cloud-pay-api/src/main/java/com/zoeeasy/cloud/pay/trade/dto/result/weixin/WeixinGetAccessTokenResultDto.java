package com.zoeeasy.cloud.pay.trade.dto.result.weixin;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信登录获取accessToken
 *
 * @author zwq
 * @date 2018-01-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "WeixinGetAccessTokenResultDto", description = " 微信登录获取accessToken")
public class WeixinGetAccessTokenResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * openid
     */
    private String openId;

    /**
     * unionid
     */
    private String unionId;

    /**
     * 访问令牌
     */
    private String accessToken;

    /**
     * 令牌有效期
     */
    private Integer tokenExpiredSeconds;

    /**
     * 刷新令牌
     */
    private String refreshToken;

    /**
     * 刷新令牌有效期
     */
    private Integer refreshTokenExpiredSeconds;

    /**
     * scope
     */
    private String scope;
}
