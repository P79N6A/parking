package com.zhuyitech.parking.ucc.dto.result.user;


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
@ApiModel(value = "WeixinGetAccessTokenResultDto", description = " 微信登录获取accessToken")
@Data
@EqualsAndHashCode(callSuper = true)
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
