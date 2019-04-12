package com.zoeeasy.cloud.pay.trade.dto.result.weixin;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信登录获取userInfo
 *
 * @author zwq
 * @date 2018-01-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "WeixinGetUserInfoResultDto", description = " 微信登录获取userInfo")
public class WeixinGetUserInfoResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 授权用户唯一标识
     */
    private String openId;

    /**
     * nickname
     */
    private String nickName;

    /**
     * sex
     */
    private String sex;

    /**
     * province
     */
    private String province;

    /**
     * city
     */
    private String city;

    /**
     * country
     */
    private String country;

    /**
     * headimgurl
     */
    private String headimgurl;

    /**
     * unionid
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
}
