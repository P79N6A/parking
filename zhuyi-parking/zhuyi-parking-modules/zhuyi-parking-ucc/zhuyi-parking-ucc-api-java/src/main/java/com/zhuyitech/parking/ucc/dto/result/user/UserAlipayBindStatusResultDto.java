package com.zhuyitech.parking.ucc.dto.result.user;


import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户支付宝绑定状态
 *
 * @author zwq
 */
@ApiModel(value = "UserAlipayBindStatusResultDto", description = "用户支付宝绑定状态")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserAlipayBindStatusResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 是否绑定支付宝
     */
    @ApiModelProperty("是否绑定支付宝")
    private Boolean bind;

    /**
     * 支付宝昵称
     */
    @ApiModelProperty("支付宝昵称")
    private String zfbNickname;

}
