package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 获取用户请求参数
 *
 * @author walkman
 */
@ApiModel(value = "UserGetRequestDto", description = "获取用户请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserGetRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * uuid
     */
    @ApiModelProperty(value = "uuid")
    private String uuid;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phoneNumber;

    /**
     * 邮箱地址
     */
    @ApiModelProperty(value = "邮箱地址")
    private String emailAddress;

}
