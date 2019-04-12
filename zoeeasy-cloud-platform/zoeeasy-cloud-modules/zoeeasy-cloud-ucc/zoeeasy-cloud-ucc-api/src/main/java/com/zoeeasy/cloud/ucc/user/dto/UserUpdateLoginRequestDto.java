package com.zoeeasy.cloud.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author AkeemSuper
 */
@ApiModel(value = "UserUpdateLoginRequestDto", description = "用户登录时更新请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserUpdateLoginRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 失败登录次数
     */
    @ApiModelProperty(value = "失败登录次数")
    private Integer accessAttemptCount;

    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间")
    protected Date lastLoginTime;

}
