package com.zhuyitech.parking.ucc.dto.result.user;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户是否设置交易密码
 *
 * @author walkman
 */
@ApiModel(value = "UserTradePwdSetStatusResultDto", description = "用户是否设置交易密码")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserTradePwdSetStatusResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户是否设置交易密码
     */
    @ApiModelProperty("用户是否设置交易密码")
    private Boolean set;

}
