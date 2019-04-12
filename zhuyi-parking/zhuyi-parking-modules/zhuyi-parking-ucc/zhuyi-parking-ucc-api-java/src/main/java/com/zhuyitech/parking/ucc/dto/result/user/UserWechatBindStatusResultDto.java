package com.zhuyitech.parking.ucc.dto.result.user;


import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户微信绑定状态
 *
 * @author zwq
 */
@ApiModel(value = "UserWechatBindStatusResultDto", description = "用户微信绑定状态")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserWechatBindStatusResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 是否绑定微信
     */
    @ApiModelProperty("是否绑定微信")
    private Boolean bind;

    /**
     * 微信昵称
     */
    @ApiModelProperty("微信昵称")
    private String wxNickname;

}
