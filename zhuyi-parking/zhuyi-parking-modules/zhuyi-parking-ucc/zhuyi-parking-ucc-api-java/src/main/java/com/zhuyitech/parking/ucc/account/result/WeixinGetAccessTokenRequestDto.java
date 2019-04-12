package com.zhuyitech.parking.ucc.account.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信登录请求参数
 *
 * @author zwq
 */
@ApiModel(value = "WeixinGetAccessTokenRequestDto", description = "微信登录请求参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class WeixinGetAccessTokenRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * code
     */
    @ApiModelProperty(value = "code")
    private String code;

    /**
     * goway
     */
    @ApiModelProperty(value = "goway", hidden = true)
    private Boolean goway;

}
