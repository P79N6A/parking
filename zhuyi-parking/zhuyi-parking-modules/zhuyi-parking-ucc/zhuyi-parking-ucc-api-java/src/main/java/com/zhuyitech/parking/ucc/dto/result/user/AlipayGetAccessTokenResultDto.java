package com.zhuyitech.parking.ucc.dto.result.user;


import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 支付宝登录获取accessToken
 *
 * @author zwq
 * @date 2018-03-19
 */
@ApiModel(value = "AlipayGetAccessTokenResultDto", description = " 支付宝登录获取accessToken")
@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayGetAccessTokenResultDto extends BaseDto {

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
