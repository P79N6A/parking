package com.zhuyitech.parking.ucc.dto.result.user;


import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户是否注册微信
 *
 * @author walkman
 */
@ApiModel(value = "WechatUserHasRegisteredResultDto", description = "用户是否注册微信")
@Data
@EqualsAndHashCode(callSuper = true)
public class WechatUserHasRegisteredResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 是否注册微信
     */
    @ApiModelProperty("是否注册微信")
    private Boolean registered;

}
