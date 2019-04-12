package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户列表请求参数表
 *
 * @author walkman
 */
@ApiModel(value = "UserListGetRequestDto", description = "菜单列表请求参数表")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserListGetRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String nickname;

    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String phoneNumber;

    /**
     * 手机号是否确认
     */
    @ApiModelProperty("手机号是否确认")
    private Boolean phoneNumberConfirmed;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String emailAddress;

    /**
     * 邮箱是否确认
     */
    @ApiModelProperty("邮箱是否确认")
    private Boolean emailAddressConfirmed;

    /**
     * 用户状态
     */
    @ApiModelProperty("用户状态")
    private String status;

    /**
     * 创建时间-开始
     */
    @ApiModelProperty("创建时间-开始")
    protected Date creationTimeStart;

    /**
     * 创建时间-结束
     */
    @ApiModelProperty("创建时间-结束")
    protected Date creationTimeEnd;

    /**
     * 最后登录时间-开始
     */
    @ApiModelProperty("最后登录时间开始")
    protected Date lastLoginTimeStart;

    /**
     * 最后登录时间-结束
     */
    @ApiModelProperty("最后登录时间-结束")
    protected Date lastLoginTimeEnd;

}
