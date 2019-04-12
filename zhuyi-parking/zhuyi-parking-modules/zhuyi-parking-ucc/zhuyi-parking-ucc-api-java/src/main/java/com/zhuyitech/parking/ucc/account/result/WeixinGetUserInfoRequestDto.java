package com.zhuyitech.parking.ucc.account.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信登录获取用户信息
 *
 * @author zwq
 */
@ApiModel(value = "WeixinGetUserInfoRequestDto", description = "微信登录获取用户信息")
@Data
@EqualsAndHashCode(callSuper = false)
public class WeixinGetUserInfoRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * openid
     */
    private String openId;

    /**
     * access_token
     */
    private String accessToken;

    /**
     * 刷新令牌
     */
    private String refreshToken;

}
