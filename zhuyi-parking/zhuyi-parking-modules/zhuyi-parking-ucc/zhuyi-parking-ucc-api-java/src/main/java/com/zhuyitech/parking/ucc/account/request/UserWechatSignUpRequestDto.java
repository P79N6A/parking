package com.zhuyitech.parking.ucc.account.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信注册
 *
 * @author zwq
 */
@ApiModel(value = "UserWechatSignUpRequestDto", description = "微信注册")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserWechatSignUpRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 授权用户唯一标识
     */
    @ApiModelProperty(value = "openId", required = true, notes = "openId")
    private String openId;

    /**
     * nickname
     */
    @ApiModelProperty(value = "nickName", required = true, notes = "nickName")
    private String nickName;

    /**
     * sex
     */
    @ApiModelProperty(value = "sex", required = true, notes = "sex")
    private String sex;

    /**
     * province
     */
    @ApiModelProperty(value = "province", required = true, notes = "province")
    private String province;

    /**
     * city
     */
    @ApiModelProperty(value = "city", required = true, notes = "city")
    private String city;

    /**
     * country
     */
    @ApiModelProperty(value = "country", required = true, notes = "country")
    private String country;

    /**
     * headImgUrl
     */
    @ApiModelProperty(value = "headImgUrl", required = true, notes = "headImgUrl")
    private String headImgUrl;

    /**
     * unionid
     */
    @ApiModelProperty(value = "unionid", required = true, notes = "unionid")
    private String unionid;

    /**
     * phoneNumber
     */
    @ApiModelProperty(value = "phoneNumber", required = true, notes = "手机号")
    private String phoneNumber;

    /**
     * 短信回执ID
     */
    @ApiModelProperty(value = "短信回执ID")
    private String smsRequestId;

    /**
     * verifyCode
     */
    @ApiModelProperty(value = "verifyCode")
    private String verifyCode;

}
