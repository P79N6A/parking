package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 微信OpenID获取用户信息
 *
 * @author walkman
 */
@ApiModel(value = "UserGetByWxOpenIdRequestDto", description = "获取用户请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserGetByWxOpenIdRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * openId
     */
    @ApiModelProperty(value = "openId")
    private String openId;

}