package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 支付宝aliUserId获取用户信息
 *
 * @author zwq
 */
@ApiModel(value = "UserGetByAliUserIdRequestDto", description = "获取用户请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserGetByAliUserIdRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * aliUserId
     */
    @ApiModelProperty(value = "aliUserId")
    private String aliUserId;

}