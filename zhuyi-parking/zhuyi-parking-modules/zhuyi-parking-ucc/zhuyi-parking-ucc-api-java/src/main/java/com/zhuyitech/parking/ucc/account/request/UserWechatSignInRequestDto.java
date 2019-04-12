package com.zhuyitech.parking.ucc.account.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;


/**
 * 微信账号登录请求参数
 *
 * @author walkman
 */
@ApiModel(value = "UserWeixinSignInRequestDto", description = "微信账号登录")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserWechatSignInRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * openId
     */
    @ApiModelProperty(value = "openId", required = true, notes = "微信OpenId")
    private String openId;

    /**
     * 客户端类型
     * 0:未知 1:ANDROID 2:APPLE 3:H5 4:WEB
     */
    @NotNull
    @ApiModelProperty(value = "客户端类型", required = true, notes = " 1:ANDROID 2:APPLE 3:H5 4:WEB")
    private Integer terminateType;

}
