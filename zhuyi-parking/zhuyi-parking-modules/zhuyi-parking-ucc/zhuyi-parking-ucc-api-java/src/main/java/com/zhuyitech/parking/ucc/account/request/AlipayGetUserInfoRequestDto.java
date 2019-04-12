package com.zhuyitech.parking.ucc.account.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 支付宝获取用户信息
 *
 * @author zwq
 */
@ApiModel(value = "AlipayGetUserInfoRequestDto", description = "支付宝获取用户信息")
@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayGetUserInfoRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * access_token
     */
    private String accessToken;

    /**
     * 刷新令牌
     */
    private String refreshToken;

}
