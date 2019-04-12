package com.zoeeasy.cloud.ucc.platform.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/11/6 0006
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UserInfoResultDto", description = "用户信息返回视图")
public class UserInfoResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String userName;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phoneNumber;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String portrait;
}
