package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 更新用户请求参数
 *
 * @author walkman
 */
@ApiModel(description = "更新用户请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserUpdateRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String portrait;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String phoneNumber;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String emailAddress;

    /**
     * 用户状态
     */
    @ApiModelProperty(value = "用户状态")
    private Integer status;

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
